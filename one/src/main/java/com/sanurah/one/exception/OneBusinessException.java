package com.sanurah.one.exception;

public class OneBusinessException extends Exception {

    public OneBusinessException() {
    }

    public OneBusinessException(String message) {
        super(message);
    }

    public OneBusinessException(Throwable cause) {
        super(cause);
    }

    public OneBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
