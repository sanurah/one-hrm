package com.sanurah.app.listner;

import com.sanurah.app.event.SendVerificationTokenEvent;
import com.sanurah.app.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendVerificationTokenEventListener implements ApplicationListener<SendVerificationTokenEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(SendVerificationTokenEvent event) {
        UUID token = UUID.randomUUID();
        userService.createUserVerificationToken(event.getUser(), token);
        sendEmail(event.getApplicationUrl().toString(), token);
    }

    private void sendEmail(String applicationUrl, UUID token) {
        String verificationUrl = String.format("%s/api/v1/user/verification/verify?token=%s", applicationUrl,
                token.toString());
        System.out.println(verificationUrl);
    }
}
