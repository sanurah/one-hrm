package com.sanurah.app.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sanurah.app.entity.Contact;
import com.sanurah.app.model.ContactModel;
import com.sanurah.app.repository.ContactRepository;
import java.time.OffsetDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTests {

    ContactRepository contactRepository = mock(ContactRepository.class);
    ContactServiceImpl contactService = new ContactServiceImpl(contactRepository, new ModelMapper());

    @Before
    public void setUp() {
        when(contactRepository.save(any())).thenReturn(getContact());
    }

    @Test
    public void createContact() {
        ContactModel contactModel = getContactModel();
        contactModel = contactService.createContact(contactModel);
        
        assertThat(contactModel, is(notNullValue()));
        assertThat(contactModel.getId(), equalTo(1l));
        assertThat(contactModel.getBuildingNumber(), equalTo("89"));
        assertThat(contactModel.getApartmentNumber(), equalTo("2.01.2"));
        assertThat(contactModel.getStreet(), equalTo("Wimbelton strasse"));
        assertThat(contactModel.getCity(), equalTo("Colombo"));
        assertThat(contactModel.getState(), equalTo("Baden"));
        assertThat(contactModel.getPostalCode(), equalTo("78311"));
        assertThat(contactModel.getCountry(), equalTo("LK"));
        assertThat(contactModel.getPhone(), equalTo("+4917823456178"));
        assertThat(contactModel.getMobile(), equalTo("+94779987591"));
        assertThat(contactModel.getFax(), equalTo("+6790281799208"));
        verify(contactRepository, times(1)).save(any());
    }

    private static Contact getContact() {
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setCreatedOn(OffsetDateTime.now());
        contact.setUpdatedOn(OffsetDateTime.now());
        contact.setBuildingNumber("89");
        contact.setApartmentNumber("2.01.2");
        contact.setStreet("Wimbelton strasse");
        contact.setCity("Colombo");
        contact.setState("Baden");
        contact.setPostalCode("78311");
        contact.setCountry("LK");
        contact.setPhone("+4917823456178");
        contact.setMobile("+94779987591");
        contact.setFax("+6790281799208");

        return contact;
    }

    private static ContactModel getContactModel() {
        ContactModel contactModel = new ContactModel();
        contactModel.setId(1l);
        contactModel.setBuildingNumber("89");
        contactModel.setApartmentNumber("2.01.2");
        contactModel.setStreet("Wimbelton strasse");
        contactModel.setCity("Colombo");
        contactModel.setState("Baden");
        contactModel.setPostalCode("78311");
        contactModel.setCountry("LK");
        contactModel.setPhone("+4917823456178");
        contactModel.setMobile("+94779987591");
        contactModel.setFax("+6790281799208");

        return contactModel;
    }
}
