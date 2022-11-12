package com.sanurah.app.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String relationship;
    private String name;
    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public EmergencyContact() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
