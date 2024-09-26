package com.epam.jwd.repository.exception;

public class UnavailableSaveUserException extends Exception {
    public UnavailableSaveUserException() {
    }

    public UnavailableSaveUserException(String message) {
        super(message);
    }

    public UnavailableSaveUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableSaveUserException(Throwable cause) {
        super(cause);
    }

    public UnavailableSaveUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
