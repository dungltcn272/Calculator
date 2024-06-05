Đây là 1 app máy tính 1 màn hình có đủ chức năng cơ bản

App sử dụng: 
 - Jetpack Compose UI
 - ViewModel để xử lí dữ liệu logic và bảo toàn trạng thái
 - SharedPrefences để lưu phép tính và kết quả lại cho lần khởi động tiếp theo 

Logic app: 
 - có 2 biến: chainOfCalculations để lưu chuỗi phép tính và result để lưu kết quả
 - viewmodel khởi tạo, lưu trữ và xử lí chuỗi phép tính chuẩn logic "nhân chia trước cộng trừ sau" ví dụ chainOfCalculations = "9+5x2" => result = "19"
 - hàm tính toán và lọc ngoại lệ đặt tại class trong ultil package, các đầu vào phi logic hay dễ hây lỗi sẽ bị chặn không thể nhập liệu

Demo: 

https://github.com/dungltcn272/Calculator/assets/136708965/6a31c308-cd4c-43f9-bdd8-0658820abeee




https://github.com/dungltcn272/Calculator/assets/136708965/813d8043-a1b0-4ebe-9772-d72bbe578ce8



