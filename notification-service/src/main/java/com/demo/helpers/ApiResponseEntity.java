package com.demo.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseEntity<T> extends ResponseEntity<ApiResponse<T>> {

    public ApiResponseEntity() {
        super((ApiResponse<T>) null, HttpStatus.OK); // hoặc HttpStatus.INTERNAL_SERVER_ERROR
    }


    // Constructor cho phản hồi thành công
    public ApiResponseEntity(T data, String message, HttpStatus status) {
        super(new ApiResponse<>(true, message, data), status);
    }

    // Constructor cho phản hồi thất bại
    public ApiResponseEntity(String message, HttpStatus status) {
        super(new ApiResponse<>(false, message, null), status);
    }

    // Phương thức tạo phản hồi thành công
    public static <T> ApiResponseEntity<T> success(T data, String message) {
        return new ApiResponseEntity<>(data, message, HttpStatus.OK);
    }

    // Phương thức tạo phản hồi thất bại
    public static <T> ApiResponseEntity<T> error(String message, HttpStatus status) {
        return new ApiResponseEntity<>(message, status);
    }

    // Phương thức tạo phản hồi thất bại với mã lỗi 400 (Bad Request)
    public static <T> ApiResponseEntity<T> badRequest(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }

    // Phương thức tạo phản hồi thất bại với mã lỗi 404 (Not Found)
    public static <T> ApiResponseEntity<T> notFound(String message) {
        return error(message, HttpStatus.NOT_FOUND);
    }

}
