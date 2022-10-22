package com.sanurah.one.service;

import com.sanurah.one.entity.User;
import com.sanurah.one.entity.VerificationToken;
import com.sanurah.one.exception.EntityNotFoundException;
import com.sanurah.one.exception.VerificationTokenExpiredException;
import java.util.UUID;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(User user, UUID token);

    VerificationToken findByToken(UUID token) throws EntityNotFoundException, VerificationTokenExpiredException;

    VerificationToken refreshVerificationToken(UUID token)
            throws EntityNotFoundException, VerificationTokenExpiredException;

    void deleteVerificationToken(VerificationToken verificationToken);
}
