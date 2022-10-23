package com.sanurah.app.service;

import com.sanurah.app.entity.User;
import com.sanurah.app.entity.VerificationToken;
import com.sanurah.app.exception.OneBusinessException;
import java.util.UUID;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(User user, UUID token);

    VerificationToken findByToken(UUID token) throws OneBusinessException;

    VerificationToken createVerificationToken(UUID token) throws OneBusinessException;

    VerificationToken deleteVerificationToken(UUID verificationToken) throws OneBusinessException;
}
