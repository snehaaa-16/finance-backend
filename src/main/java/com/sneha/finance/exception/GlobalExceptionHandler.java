package com.sneha.finance.exception;

import com.sneha.finance.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<?> handleNotFound(ResourceNotFoundException ex) {
        return ApiResponse.builder()
                .success(false)
                .data(null)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ApiResponse<?> handleBadRequest(BadRequestException ex) {
        return ApiResponse.builder()
                .success(false)
                .data(null)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneric(Exception ex) {
        return ApiResponse.builder()
                .success(false)
                .data(null)
                .message("Something went wrong")
                .build();
    }
}