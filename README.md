# Realtime Auction System

Một hệ thống đấu giá trực tuyến theo thời gian thực được xây dựng bằng Spring Boot, RabbitMQ và WebSockets. Dự án cho phép người dùng đặt giá và cập nhật giá mới tức thời trên toàn bộ các thiết bị kết nối.

## Công nghệ sử dụng
* **Backend:** Java, Spring Boot (Web, Data JPA, AMQP, WebSocket)
* **Frontend:** HTML, JavaScript, Bootstrap 5, SockJS, STOMP.js
* **Message Broker:** RabbitMQ
* **Cơ sở dữ liệu:** MySQL
* **Triển khai:** Docker Compose (cho MySQL và RabbitMQ)

## Cấu trúc dự án
Dự án được thiết kế theo mô hình xử lý bất đồng bộ:
1. **Frontend:** Giao diện đấu giá, duy trì kết nối WebSocket.
2. **Controller (`BidController`):** Tiếp nhận HTTP POST request đặt giá từ người dùng.
3. **Producer (`BidProducer`):** Đẩy thông tin request vào hàng đợi RabbitMQ (giúp hệ thống không bị quá tải khi có lượng lớn người dùng đặt giá cùng lúc).
4. **Consumer (`BidConsumer`):** Xử lý ngầm, lấy request từ hàng đợi, kiểm tra tính hợp lệ (giá đặt phải lớn hơn giá hiện tại) và lưu vào cơ sở dữ liệu.
5. **WebSocket Broadcast:** Đẩy thông báo cập nhật giá thành công qua kênh (`/topic/items/{id}`) hoặc bắn lỗi không hợp lệ về riêng cho người dùng qua kênh (`/topic/errors/{userId}`).

## Yêu cầu môi trường
* JDK 17+
* Maven 3.x
* Docker & Docker Compose (Để chạy nhanh MySQL & RabbitMQ)

## Hướng dẫn cài đặt

**1. Khởi động Message Broker và Database**
Tại thư mục chứa file `docker-compose.yml`, chạy lệnh:
```bash
docker-compose up -d
```

**2. Kiểm tra cấu hình ứng dụng**
File `src/main/resources/application.yaml` đã được cấu hình sẵn cho các dịch vụ:
*   MySQL: `jdbc:mysql://localhost:3306/auction_db` (root/123456)
*   RabbitMQ: `localhost:5672` (guest/guest)

**3. Chạy ứng dụng**
Chạy class `RealtimeAuctionSystemApplication.java` trên IDE hoặc sử dụng Maven:
```bash
mvn spring-boot:run
```

## Hướng dẫn sử dụng
1. Mở trình duyệt và truy cập: `http://localhost:8080/index.html`.
2. (Tùy chọn) Sử dụng **Ngrok** hoặc IP mạng LAN (vd: `http://192.168.x.x:8080/index.html`) để mở giao diện trên điện thoại và máy tính cùng lúc.
3. Nhập giá đấu giá và nhấn nút **Đặt giá**. Quan sát sự thay đổi giá tức thời trên tất cả các màn hình mà không cần reload trang.
