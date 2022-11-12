package com.sanurah.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sanurah.app.constants.Gender;
import com.sanurah.app.entity.Contact;
import com.sanurah.app.entity.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class UserDetailModel {

    private Long id;
    private User user;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private BigDecimal salary;
    private Contact contact;
    private List<DepartmentModel> departments;
    private ContactModel emergencyContact;

    public UserDetailModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<DepartmentModel> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentModel> departments) {
        this.departments = departments;
    }

    public ContactModel getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(ContactModel emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}
