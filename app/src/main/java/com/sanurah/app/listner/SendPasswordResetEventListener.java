package com.sanurah.app.listner;

import com.sanurah.app.event.SendPasswordResetEvent;
import com.sanurah.app.service.VerificationTokenService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class SendPasswordResetEventListener implements ApplicationListener<SendPasswordResetEvent> {

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Override
    public void onApplicationEvent(SendPasswordResetEvent event) {
        UUID token = UUID.randomUUID();
        verificationTokenService.createVerificationToken(event.getUser(), token);
        sendEmail(event.getApplicationUrl().toString(), token);
    }

    private void sendEmail(String applicationUrl, UUID token) {
        //TODO update url to match the reset password from location on client
        String verificationUrl = String.format("%s/api/v1/user/password/reset?token=%s", applicationUrl,
                token.toString());
        System.out.println(verificationUrl);
    }
}
