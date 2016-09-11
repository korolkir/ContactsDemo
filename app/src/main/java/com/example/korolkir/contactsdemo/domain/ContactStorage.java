package com.example.korolkir.contactsdemo.domain;

import com.example.korolkir.contactsdemo.domain.entity.Contact;

import java.util.List;

/**
 * Created by korolkir on 11.09.16.
 */
public interface ContactStorage {

    boolean saveContact(Contact contact);

    List<Contact> getContacts();

}
