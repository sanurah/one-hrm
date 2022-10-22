package com.sanurah.one.entity;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken extends BaseEntity {

    private UUID token;
    private OffsetDateTime expirationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
    private User user;
    @Transient
    private static final int VALIDITY_PERIOD = 30; //minutes

    public VerificationToken() {
    }

    public VerificationToken(User user, UUID token) {
        this.token = token;
        setExpirationTime();
        this.user = user;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public OffsetDateTime getExpirationTime() {
        return expirationTime;
    }

    private void setExpirationTime() {
        this.expirationTime = OffsetDateTime.now().plusMinutes(VALIDITY_PERIOD);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
