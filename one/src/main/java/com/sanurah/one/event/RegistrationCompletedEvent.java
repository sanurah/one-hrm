package com.sanurah.one.event;

import com.sanurah.one.model.UserModel;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompletedEvent extends ApplicationEvent {

    private UserModel user;
    private String appicationUrl;

    public RegistrationCompletedEvent(UserModel user, String appicationUrl) {
        super(user);
        this.user = user;
        this.appicationUrl = appicationUrl;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getAppicationUrl() {
        return appicationUrl;
    }

    public void setAppicationUrl(String appicationUrl) {
        this.appicationUrl = appicationUrl;
    }
}
