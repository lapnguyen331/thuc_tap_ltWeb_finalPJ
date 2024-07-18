package com.project.exceptions.custom_exception;

import jakarta.servlet.ServletException;
import lombok.Builder;
import lombok.Data;

public class ProductException extends MyServletException {
    public ProductException(String responseMessage, int errorCode) {
        super(responseMessage, errorCode);
    }
}
