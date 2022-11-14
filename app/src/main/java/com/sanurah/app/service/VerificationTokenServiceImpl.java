package com.sanurah.app.service;

import com.sanurah.app.entity.User;
import com.sanurah.app.entity.VerificationToken;
import com.sanurah.app.exception.EntityNotFoundException;
import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.exception.VerificationTokenExpiredException;
import com.sanurah.app.model.UserModel;
import com.sanurah.app.repository.VerificationTokenRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;
    private UserServiceImpl userService;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository,
            UserServiceImpl userService) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userService = userService;
    }

    public VerificationToken createVerificationToken(UserModel userModel, UUID token) {
        User user = userService.map(userModel);
        VerificationToken verificationToken = new VerificationToken(user, token);
        return verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken findByToken(UUID token) throws OneBusinessException {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken.isEmpty()) {
            throw new EntityNotFoundException(VerificationToken.class, token.toString());
        }
        return validateToken(verificationToken.get());
    }

    public UserModel deleteVerificationToken(UUID token) throws OneBusinessException {
        VerificationToken verificationToken = findByToken(token);
        verificationTokenRepository.delete(verificationToken);
        return userService.map(verificationToken.getUser());
    }

    private VerificationToken validateToken(VerificationToken verificationToken)
            throws VerificationTokenExpiredException {
        boolean isExpired = verificationToken.getExpirationTime().isBefore(OffsetDateTime.now());
        if (isExpired) {
            throw new VerificationTokenExpiredException(verificationToken.getToken(),
                    verificationToken.getExpirationTime());
        }
        return verificationToken;
    }
}
