package com.checkers.bartolini.checkersModel;

public class IncorrectMoveException extends Exception {
    public IncorrectMoveException() {
        super();
    }

    public IncorrectMoveException(String message) {
        super(message);
    }

    public IncorrectMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectMoveException(Throwable cause) {
        super(cause);
    }

    public IncorrectMoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
