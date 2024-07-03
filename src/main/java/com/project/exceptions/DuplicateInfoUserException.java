package com.project.exceptions;

public class DuplicateInfoUserException extends Exception {
    public DuplicateInfoUserException() {
        super();
    }

    public DuplicateInfoUserException(String message) {
        super(message);
    }

    public DuplicateInfoUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateInfoUserException(Throwable cause) {
        super(cause);
    }

    protected DuplicateInfoUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
