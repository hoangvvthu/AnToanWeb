param(
    [string]$KeystorePath = ".local/tls/tomcat-keystore.p12",
    [string]$StorePassword = "changeit",
    [string]$Alias = "uteshop-dev"
)

$ErrorActionPreference = "Stop"

$root = Resolve-Path (Join-Path $PSScriptRoot "..")
$keystoreAbsolutePath = Join-Path $root $KeystorePath
$keystoreDir = Split-Path $keystoreAbsolutePath -Parent

if (-not (Test-Path $keystoreDir)) {
    New-Item -ItemType Directory -Path $keystoreDir -Force | Out-Null
}

if (Test-Path $keystoreAbsolutePath) {
    Write-Host "Keystore da ton tai: $keystoreAbsolutePath"
    exit 0
}

$mkcert = Get-Command mkcert -ErrorAction SilentlyContinue
if ($mkcert) {
    Write-Host "Tim thay mkcert, tao chung chi dang tin cay (PKCS12) cho localhost..."
    Push-Location $keystoreDir
    try {
        & mkcert -pkcs12 localhost 127.0.0.1 ::1
        if ($LASTEXITCODE -eq 0) {
            # mkcert sinh ra file co ten dang "localhost+2.p12"
            $generatedP12 = Get-ChildItem -Filter "*.p12" | Select-Object -First 1
            if ($generatedP12) {
                Rename-Item -Path $generatedP12.FullName -NewName (Split-Path $keystoreAbsolutePath -Leaf) -Force
                Write-Host "Da tao keystore HTTPS qua mkcert tai: $keystoreAbsolutePath"
                exit 0
            }
        }
    }
    finally {
        Pop-Location
    }
}

Write-Host "Khong the dung mkcert, se dung self-signed keytool..."

$keytool = Get-Command keytool -ErrorAction SilentlyContinue
if (-not $keytool) {
    throw "Khong tim thay keytool. Hay cai JDK va dam bao keytool co trong PATH."
}

& $keytool.Source -genkeypair `
    -alias $Alias `
    -keyalg RSA `
    -keysize 2048 `
    -storetype PKCS12 `
    -keystore $keystoreAbsolutePath `
    -validity 3650 `
    -storepass $StorePassword `
    -keypass $StorePassword `
    -dname "CN=localhost, OU=Dev, O=UTESHOP, L=HCM, ST=HCM, C=VN" `
    -ext "SAN=dns:localhost,ip:127.0.0.1"

if ($LASTEXITCODE -ne 0) {
    throw "Tao keystore that bai."
}

Write-Host "Da tao keystore HTTPS bang keytool: $keystoreAbsolutePath"
