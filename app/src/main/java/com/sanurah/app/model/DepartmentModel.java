package com.sanurah.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sanurah.app.entity.Contact;
import com.sanurah.app.entity.User;
import javax.persistence.OneToOne;

@JsonInclude(Include.NON_NULL)
public class DepartmentModel {

    private Long id;
    private String name;
    private String description;
    @OneToOne
    private Contact contact;
    @OneToOne
    private User head;

    public DepartmentModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public User getHead() {
        return head;
    }

    public void setHead(User head) {
        this.head = head;
    }
}
