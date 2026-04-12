Cài đặt Chứng chỉ Gốc (Root CA) Đầu tiên, bạn cần cấp quyền tin cậy cho máy ảo địa phương. Mở Command Prompt (CMD) với quyền Administrator. Thực thi lệnh sau:  
mkcert -install  

Cấu hình để kết nối với CSDL MySQL:  
src/main/resources/META-INF/persistence.xml

Khởi chạy dự án với HTTPS Bạn không cần cài đặt Tomcat thủ công hay dùng Docker phức tạp.
Chỉ cần thực thi hai tập lệnh sau trong Terminal ở IDE (vd Intellij):  
Tạo tệp lưu trữ khóa bảo mật (Keystore):  
.\scripts\create-local-keystore.ps1  

Khởi chạy Server với giao thức HTTPS:  
.\scripts\run-local-https.ps1  

Chờ hibenate rồi sẽ được tự động chuyển hướng đến: https://localhost:8443/uteshop/home
