package com.sanurah.one.exception;

import java.time.OffsetDateTime;
import java.util.UUID;

public class VerificationTokenExpiredException extends OneBusinessException {

    private static final String MESSAGE = "Verification token %s was expired on %s";

    public VerificationTokenExpiredException(UUID token, OffsetDateTime expirationTime) {
        super(String.format(MESSAGE, token.toString(), expirationTime));
    }
}
