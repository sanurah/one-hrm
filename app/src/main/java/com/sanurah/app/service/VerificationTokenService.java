package com.sanurah.app.service;

import com.sanurah.app.entity.VerificationToken;
import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.model.UserModel;
import java.util.UUID;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(UserModel user, UUID token);

    VerificationToken findByToken(UUID token) throws OneBusinessException;

    UserModel deleteVerificationToken(UUID verificationToken) throws OneBusinessException;
}
