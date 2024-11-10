package com.store.furniture.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(9998, "Invalid Key", HttpStatus.INTERNAL_SERVER_ERROR),

    USERNAME_EXISTS(1001, "Username already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS(1001, "Email already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(1004, "User not exists", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1007, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1008, "Category not found", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1009, "Order not found", HttpStatus.BAD_REQUEST),
    IMAGE_UPLOAD_FAILED(1010, "Image upload failed", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_EMPTY(1011, "File is empty", HttpStatus.BAD_REQUEST),
    FILE_SIZE_EXCEEDS_LIMIT(1012, "File size exceeds the maximum limit", HttpStatus.BAD_REQUEST),
    INVALID_FILE_TYPE(1013, "Invalid file type. Only image files are allowed", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND(1014, "Customer not found", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1015, "Product not found", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(1016, "Cart not found", HttpStatus.BAD_REQUEST),
    ;


    private final int code;
    private final String message;
    private final HttpStatus httpStatus;


}
