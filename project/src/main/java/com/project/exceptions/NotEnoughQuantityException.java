package com.project.exceptions;

public class NotEnoughQuantityException extends Exception {
    public NotEnoughQuantityException() {
        super();
    }

    public NotEnoughQuantityException(String message) {
        super(message);
    }

    public NotEnoughQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughQuantityException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
