package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;
    private String errorClass;

    public static ErrorResponse build(String code, String message, String errorClass) {
        return new ErrorResponse(code, message, errorClass);
    }

    public static ErrorResponse build(String code, Throwable e) {
        return build(code, e.getMessage(), e.getClass().getCanonicalName());
    }
}
