package com.project.exceptions;

public class NotVerifiedException extends Exception {
    public NotVerifiedException() {
        super();
    }

    public NotVerifiedException(String message) {
        super(message);
    }

    public NotVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotVerifiedException(Throwable cause) {
        super(cause);
    }

    protected NotVerifiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
