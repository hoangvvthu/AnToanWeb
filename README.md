# UTESHOP

Do an web thuong mai dien tu xay dung theo mo hinh Java Web MVC, ho tro dang ky, dang nhap, quan ly tai khoan, gio hang, dat hang, danh gia san pham va khu vuc quan tri.

## Cong nghe chinh

- Backend: Java 22, Jakarta Servlet, JSP, JSTL
- ORM / Persistence: Hibernate ORM, JPA
- Database: MySQL
- Build tool: Maven
- Application server: Tomcat 10 (chay qua Cargo Maven Plugin)
- Frontend: JSP, Bootstrap, HTML/CSS/JavaScript
- Bao mat / ho tro: BCrypt, Hibernate Validator, SiteMesh

## Thu vien / dependency noi bat

- `jakarta.servlet-api`
- `jakarta.servlet.jsp-api`
- `jakarta.servlet.jsp.jstl-api`
- `hibernate-core`
- `hibernate-validator`
- `mysql-connector-java`
- `sitemesh`
- `commons-io`
- `javax.mail`
- `jbcrypt`
- `lombok`

## Cau truc tong quan

- `src/main/java`: controller, service, dao, entity, config
- `src/main/webapp`: JSP views, assets, cau hinh web
- `src/main/resources/META-INF/persistence.xml`: cau hinh ket noi MySQL
- `scripts/`: script tao keystore va chay local HTTPS

## Cau hinh database

Project su dung MySQL, thong tin ket noi nam trong:

- `src/main/resources/META-INF/persistence.xml`

Mac dinh:

- Database: `projectfinal`
- Host: `localhost:3306`
- User: `root`
- Password: cap nhat theo may cua ban trong `persistence.xml`

## Chay project bang HTTPS

1. Cai trust cho chung chi local:

```powershell
mkcert -install
```

2. Tao keystore local:

```powershell
.\scripts\create-local-keystore.ps1
```

3. Chay server HTTPS:

```powershell
.\scripts\run-local-https.ps1
```

URL mac dinh:

- `https://localhost:8443/uteshop/home`

## Tai khoan test

### Project da fix

#### ROLE ADMIN

- Email: `hotunglam26062@gmail.com`
- Password: `Abcd@123`

#### ROLE USER

- Email: `hotunglam266@gmail.com`
- Password: `Lam@1234`

### Project chua fix

#### ROLE ADMIN

- Email: `thuadmin@gmail.com`
- Password: `A@123456`

#### ROLE USER

- Email: `thuuser@gmail.com`
- Password: `A@123456`

## Ghi chu

- Project dong goi dang `war`
- Tomcat duoc Cargo tu dong tai va cau hinh local, khong can cai Tomcat thu cong
- Neu da co Tomcat local dang chay, script co the chi redeploy thay vi start moi
