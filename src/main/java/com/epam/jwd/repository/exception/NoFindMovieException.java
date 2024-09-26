package com.epam.jwd.repository.exception;

public class NoFindMovieException extends Exception {
    public NoFindMovieException() {
    }

    public NoFindMovieException(String message) {
        super(message);
    }

    public NoFindMovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFindMovieException(Throwable cause) {
        super(cause);
    }

    public NoFindMovieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
