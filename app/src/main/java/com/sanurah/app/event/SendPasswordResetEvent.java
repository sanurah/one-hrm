package com.sanurah.app.event;

import com.sanurah.app.model.UserModel;
import java.net.URI;
import org.springframework.context.ApplicationEvent;

public class SendPasswordResetEvent extends ApplicationEvent {

    private UserModel user;
    private URI applicationUrl;

    public SendPasswordResetEvent(UserModel user, URI applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public URI getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(URI applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
