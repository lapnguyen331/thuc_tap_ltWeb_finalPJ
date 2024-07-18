package com.project.exceptions.custom_exception;

public class StockKeepingException extends MyServletException {
    public StockKeepingException(String responseMessage, int errorCode) {
        super(responseMessage, errorCode);
    }
}
