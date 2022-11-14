package com.sanurah.app.listner;

import com.sanurah.app.entity.VerificationToken;
import com.sanurah.app.event.SendVerificationTokenEvent;
import com.sanurah.app.service.VerificationTokenService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendVerificationTokenEventListener implements ApplicationListener<SendVerificationTokenEvent> {

    private VerificationTokenService verificationTokenService;

    @Autowired
    public SendVerificationTokenEventListener(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    @Override
    public void onApplicationEvent(SendVerificationTokenEvent event) {
        UUID token = UUID.randomUUID();
        VerificationToken token1 = verificationTokenService.createVerificationToken(event.getUser(), token);
        sendEmail(event.getApplicationUrl().toString(), token);
    }

    private void sendEmail(String applicationUrl, UUID token) {
        String verificationUrl = String.format("%s/api/v1/user/verification/verify?token=%s", applicationUrl,
                token.toString());
        System.out.println(verificationUrl);
    }
}
