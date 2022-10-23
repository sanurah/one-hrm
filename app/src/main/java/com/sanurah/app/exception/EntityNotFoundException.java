package com.sanurah.app.exception;

public class EntityNotFoundException extends OneBusinessException {

    private static final String MESSAGE = "Unable to find %s with id: %s";

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(Class className, String id) {
        super(String.format(MESSAGE, className, id));
    }
}
