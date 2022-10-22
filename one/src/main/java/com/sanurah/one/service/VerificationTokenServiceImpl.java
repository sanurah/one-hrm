package com.sanurah.one.service;

import com.sanurah.one.entity.User;
import com.sanurah.one.entity.VerificationToken;
import com.sanurah.one.exception.EntityNotFoundException;
import com.sanurah.one.exception.VerificationTokenExpiredException;
import com.sanurah.one.repository.VerificationTokenRepository;
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

    public VerificationToken findByToken(UUID token)
            throws EntityNotFoundException, VerificationTokenExpiredException {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken.isEmpty()) {
            throw new EntityNotFoundException(VerificationToken.class, token.toString());
        }
        return validateToken(verificationToken.get());
    }

    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }

    public VerificationToken refreshVerificationToken(UUID token)
            throws EntityNotFoundException, VerificationTokenExpiredException {
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
