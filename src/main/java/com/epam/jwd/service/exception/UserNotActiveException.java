package com.epam.jwd.service.exception;

public class UserNotActiveException extends Exception {

    public UserNotActiveException(String message) {
        super(message);
    }

    public UserNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotActiveException(Throwable cause) {
        super(cause);
    }

    public UserNotActiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserNotActiveException() {
    }
}
