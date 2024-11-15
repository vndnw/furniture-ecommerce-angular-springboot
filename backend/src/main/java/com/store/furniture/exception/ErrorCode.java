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
    EMAIL_EXISTS(1002, "Email already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(1005, "User not exists", HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT(1006, "Password is incorrect", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1009, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1010, "Category not found", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1011, "Order not found", HttpStatus.BAD_REQUEST),
    IMAGE_UPLOAD_FAILED(1012, "Image upload failed", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_EMPTY(1013, "File is empty", HttpStatus.BAD_REQUEST),
    FILE_SIZE_EXCEEDS_LIMIT(1014, "File size exceeds the maximum limit", HttpStatus.BAD_REQUEST),
    INVALID_FILE_TYPE(1015, "Invalid file type. Only image files are allowed", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND(1016, "Customer not found", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1017, "Product not found", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(1018, "Cart not found", HttpStatus.BAD_REQUEST),
    INVALID_ROLE(1019, "Invalid role", HttpStatus.BAD_REQUEST),
    NAME_MANDATORY(1020, "Name is mandatory", HttpStatus.BAD_REQUEST),
    NAME_SIZE(1021, "Name must be between 2 and 50 characters", HttpStatus.BAD_REQUEST),
    EMAIL_MANDATORY(1022, "Email is mandatory", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1023, "Email should be valid", HttpStatus.BAD_REQUEST),
    ADDRESS_MANDATORY(1024, "Address is mandatory", HttpStatus.BAD_REQUEST),
    ADDRESS_SIZE(1025, "Address must be between 5 and 100 characters", HttpStatus.BAD_REQUEST),
    PHONE_MANDATORY(1026, "Phone is mandatory", HttpStatus.BAD_REQUEST),
    PHONE_INVALID(1027, "Phone number is invalid", HttpStatus.BAD_REQUEST),
    USERNAME_MANDATORY(1028, "Username is mandatory", HttpStatus.BAD_REQUEST),
    USERNAME_SIZE(1029, "Username must be between 2 and 50 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_MANDATORY(1030, "Password is mandatory", HttpStatus.BAD_REQUEST),
    PASSWORD_SIZE(1031, "Password must be between 5 and 20 characters", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_MANDATORY(1032, "Name is mandatory", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_SIZE(1033, "Name must be at least 2 characters", HttpStatus.BAD_REQUEST),
    DESCRIPTION_MANDATORY(1034, "Description is mandatory", HttpStatus.BAD_REQUEST),
    DESCRIPTION_SIZE(1035, "Description must be at least 10 characters", HttpStatus.BAD_REQUEST),
    PRICE_MANDATORY(1036, "Price is mandatory", HttpStatus.BAD_REQUEST),
    IMAGE_MANDATORY(1037, "Image is mandatory", HttpStatus.BAD_REQUEST),
    CATEGORY_ID_MANDATORY(1038, "Category ID is mandatory", HttpStatus.BAD_REQUEST),

    ;


    private final int code;
    private final String message;
    private final HttpStatus httpStatus;


}
