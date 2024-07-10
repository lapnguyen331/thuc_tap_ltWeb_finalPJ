package com.project.exceptions.custom_exception;

import jakarta.servlet.ServletException;
import lombok.Data;

@Data
public class MyServletException extends ServletException {
    private int errorCode = 400;
    private String responseMessage;
    public MyServletException(String responseMessage, int errorCode) {
        this.responseMessage = responseMessage;
        this.errorCode = errorCode;
    }
}
