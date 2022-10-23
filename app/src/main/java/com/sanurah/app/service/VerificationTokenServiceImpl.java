package com.sanurah.app.service;

import com.sanurah.app.entity.User;
import com.sanurah.app.entity.VerificationToken;
import com.sanurah.app.exception.EntityNotFoundException;
import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.exception.VerificationTokenExpiredException;
import com.sanurah.app.repository.VerificationTokenRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createVerificationToken(User user, UUID token) {
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

    public VerificationToken deleteVerificationToken(UUID token) throws OneBusinessException {
        VerificationToken verificationToken = findByToken(token);
        verificationTokenRepository.delete(verificationToken);
        return verificationToken;
    }

    public VerificationToken createVerificationToken(UUID token) throws OneBusinessException {
        VerificationToken verificationToken = findByToken(token);
        User user = verificationToken.getUser();
        return createVerificationToken(user, UUID.randomUUID());
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
