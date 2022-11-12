package com.sanurah.app.service;

import com.sanurah.app.entity.Contact;
import com.sanurah.app.model.ContactModel;
import com.sanurah.app.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTests {

    @MockBean
    ContactRepository contactRepository;
    ContactServiceImpl contactService = new ContactServiceImpl(contactRepository, new ModelMapper());

    @Test
    public void mapContact() {
        Contact contact = getContact();
        ContactModel contactModel = contactService.map(contact);
        contactModel.getFax();
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setCountry("LK");
        contact.setPhone("124567");
        contact.setMobile("124567");
        contact.setFax("124567");
        contact.setApartmentNumber("456");
        contact.setCity("Colombo");
        contact.setBuildingNumber("adsda");
        contact.setStreet("fsfsfdds");
        return contact;
    }
}
