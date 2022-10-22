package com.sanurah.one.listner;

import com.sanurah.one.event.RegistrationCompletedEvent;
import com.sanurah.one.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCompletedEventListener implements ApplicationListener<RegistrationCompletedEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompletedEvent event) {
        UUID token = UUID.randomUUID();
        userService.createUserVerificationToken(event.getUser(), token);
        sendEmail(event.getAppicationUrl(), token);
    }

    private void sendEmail(String applicationUrl, UUID token) {
        String verificationUrl = String.format("%s/api/v1/user/verify?token=%s", applicationUrl, token.toString());
        System.out.println(verificationUrl);
    }
}
