param(
    [string]$StorePassword = "changeit",
    [int]$Port = 8443,
    [int]$RmiPort = 8205,
    [int]$MaxWaitSeconds = 300,
    [int]$RetryDelaySec = 5,
    [switch]$Clean
)

$ErrorActionPreference = "Stop"

$root = Resolve-Path (Join-Path $PSScriptRoot "..")
Push-Location $root

$watcherJob = $null

try {
    $keystorePath = ".local/tls/tomcat-keystore.p12"
    if (-not (Test-Path (Join-Path $root $keystorePath))) {
        & (Join-Path $PSScriptRoot "create-local-keystore.ps1") -StorePassword $StorePassword -KeystorePath $keystorePath
    }

    function Test-ListenerOnPort {
        param([int]$PortNum)

        $listener = Get-NetTCPConnection -LocalPort $PortNum -State Listen -ErrorAction SilentlyContinue
        return ($null -ne $listener)
    }

    function Invoke-MavenCommand {
        param([string[]]$Arguments)

        & mvn @Arguments
        if ($LASTEXITCODE -ne 0) {
            throw "Maven command failed: mvn $($Arguments -join ' ')"
        }
    }

    $httpsRunning = Test-ListenerOnPort -PortNum $Port
    $rmiRunning = Test-ListenerOnPort -PortNum $RmiPort
    $serverRunning = $httpsRunning -or $rmiRunning

    $mavenArgs = @(
        "-DskipTests",
        "-Plocal-https",
        "-Dcargo.https.port=$Port",
        "-Dcargo.rmi.port=$RmiPort",
        "-Dcargo.keystore.password=$StorePassword"
    )

    if ($Clean) {
        $mavenArgs += "clean"
    }

    # --- Giai phong port chi khi can start server moi ---
    function Stop-ProcessOnPort {
        param([int]$PortNum)
        # Dung Get-NetTCPConnection chinh xac hon netstat parsing
        $conns = Get-NetTCPConnection -LocalPort $PortNum -ErrorAction SilentlyContinue
        if (-not $conns) {
            Write-Host "  [OK] Port $PortNum da trong." -ForegroundColor Green
            return
        }
        $owningPids = $conns | Select-Object -ExpandProperty OwningProcess | Sort-Object -Unique
        foreach ($p in $owningPids) {
            try {
                $proc = Get-Process -Id $p -ErrorAction SilentlyContinue
                if ($proc) {
                    Write-Host "  [!] Dang tat '$($proc.Name)' (PID $p) dang chiem port $PortNum..." -ForegroundColor Yellow
                    # Kill toan bo process tree (kem ca child process)
                    $children = Get-CimInstance Win32_Process -Filter "ParentProcessId=$p" -ErrorAction SilentlyContinue
                    foreach ($child in $children) {
                        Stop-Process -Id $child.ProcessId -Force -ErrorAction SilentlyContinue
                    }
                    Stop-Process -Id $p -Force -ErrorAction SilentlyContinue
                }
            } catch { }
        }
    }

    function Wait-PortFree {
        param([int]$PortNum, [int]$TimeoutSec = 20)
        $deadline = (Get-Date).AddSeconds($TimeoutSec)
        while ((Get-Date) -lt $deadline) {
            $conn = Get-NetTCPConnection -LocalPort $PortNum -ErrorAction SilentlyContinue
            if (-not $conn) {
                Write-Host "  [OK] Port $PortNum da duoc giai phong hoan toan." -ForegroundColor Green
                return $true
            }
            Start-Sleep -Milliseconds 500
        }
        Write-Host "  [WARN] Port $PortNum VAN bi chiem sau $TimeoutSec giay! Thu giai phong lan cuoi..." -ForegroundColor Red
        Stop-ProcessOnPort -PortNum $PortNum
        Start-Sleep -Seconds 2
        return $false
    }

    $url = "https://localhost:$Port/uteshop/home"
    if ($serverRunning) {
        Write-Host "================================================================" -ForegroundColor Cyan
        Write-Host "  Tomcat dang chay. Thuc hien package + redeploy..." -ForegroundColor Green
        Write-Host "  URL: " -NoNewline
        Write-Host $url -ForegroundColor Yellow
        Write-Host "================================================================" -ForegroundColor Cyan
        Write-Host ""

        Invoke-MavenCommand -Arguments ($mavenArgs + @("package", "cargo:redeploy"))
        return
    }

    Write-Host "  [*] Kiem tra va giai phong cung port $Port (HTTPS) va $RmiPort (RMI)..." -ForegroundColor DarkGray
    Stop-ProcessOnPort -PortNum $Port
    Stop-ProcessOnPort -PortNum $RmiPort
    Wait-PortFree -PortNum $Port    | Out-Null
    Wait-PortFree -PortNum $RmiPort | Out-Null
    Start-Sleep -Seconds 1
    # --- Ket thuc giai phong port ---

    Write-Host "================================================================" -ForegroundColor Cyan
    Write-Host "  Khoi dong UTESHOP voi HTTPS qua Cargo..." -ForegroundColor Green
    Write-Host "  Trang web dang chay tai: " -NoNewline
    Write-Host $url -ForegroundColor Yellow
    Write-Host "================================================================" -ForegroundColor Cyan

    # Background job: doi server san sang -> warm up Hibernate -> mo trinh duyet
    $watcherJob = Start-Job -ScriptBlock {
        param([string]$Url, [int]$MaxWait, [int]$Delay)

        # Bo qua loi chung chi tu ky (self-signed cert) cho Windows PS 5.1
        Add-Type @"
using System.Net;
using System.Security.Cryptography.X509Certificates;
public class WaseTrustAll : ICertificatePolicy {
    public bool CheckValidationResult(ServicePoint sp, X509Certificate cert,
        WebRequest req, int problem) { return true; }
}
"@
        [System.Net.ServicePointManager]::CertificatePolicy    = New-Object WaseTrustAll
        [System.Net.ServicePointManager]::SecurityProtocol     = [System.Net.SecurityProtocolType]::Tls12

        $deadline = (Get-Date).AddSeconds($MaxWait)

        # Buoc 1: Cho den khi server phan hoi (khong bi loi ket noi)
        while ((Get-Date) -lt $deadline) {
            try {
                $resp = Invoke-WebRequest -Uri $Url -TimeoutSec 5 -UseBasicParsing -ErrorAction Stop
                if ($resp.StatusCode -lt 500) { break }
            } catch { }
            Start-Sleep -Seconds $Delay
        }

        if ((Get-Date) -ge $deadline) { return }

        # Buoc 2: Warm-up Hibernate - gui them 1 request de khoi tao lazy entities
        Start-Sleep -Seconds 2
        try {
            Invoke-WebRequest -Uri $Url -TimeoutSec 20 -UseBasicParsing -ErrorAction SilentlyContinue | Out-Null
        } catch { }

        # Buoc 3: Mo trinh duyet sau khi backend da san sang hoan toan
        Start-Process $Url

    } -ArgumentList $url, $MaxWaitSeconds, $RetryDelaySec

    Write-Host "  [*] Watcher dang chay ngam: se warm-up Hibernate va tu dong mo trinh duyet khi san sang." -ForegroundColor DarkGray
    Write-Host ""

    # Chay Cargo o foreground (giu nguyen output, giu server song)
    Invoke-MavenCommand -Arguments ($mavenArgs + @("package", "cargo:run"))
}
finally {
    if ($null -ne $watcherJob) {
        Stop-Job   $watcherJob -ErrorAction SilentlyContinue
        Remove-Job $watcherJob -ErrorAction SilentlyContinue
    }
    Pop-Location
}