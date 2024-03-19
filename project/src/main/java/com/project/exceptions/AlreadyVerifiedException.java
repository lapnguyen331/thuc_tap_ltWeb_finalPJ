package com.project.exceptions;

public class AlreadyVerifiedException extends Exception {
    public AlreadyVerifiedException() {
        super();
    }

    public AlreadyVerifiedException(String message) {
        super(message);
    }

    public AlreadyVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyVerifiedException(Throwable cause) {
        super(cause);
    }

    protected AlreadyVerifiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
