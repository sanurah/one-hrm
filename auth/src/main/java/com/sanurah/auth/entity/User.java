package com.sanurah.auth.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private UUID uuid;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String role;
    private Boolean verified;

    public User() {
    }

    public User(UUID uuid, String username, String password, String role, Boolean verified) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.verified = verified;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
