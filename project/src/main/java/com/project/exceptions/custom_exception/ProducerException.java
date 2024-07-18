package com.project.exceptions.custom_exception;

public class ProducerException extends MyServletException {
    public ProducerException(String responseMessage, int errorCode) {
        super(responseMessage, errorCode);
    }
}
