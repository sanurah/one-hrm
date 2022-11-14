package com.sanurah.app.service;

import com.sanurah.app.entity.Contact;
import com.sanurah.app.model.ContactModel;
import com.sanurah.app.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactServiceImpl {

    ContactRepository contactRepository;
    ModelMapper modelMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    public ContactModel createContact(ContactModel contactModel) {
        Contact contact = map(contactModel);
        return map(contactRepository.save(contact));
    }

    private ContactModel map(Contact contact) {
        return modelMapper.map(contact, ContactModel.class);
    }

    private Contact map(ContactModel contactModel) {
        return modelMapper.map(contactModel, Contact.class);
    }
}
