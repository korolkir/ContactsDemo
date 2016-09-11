package com.example.korolkir.contactsdemo.domain;

import com.example.korolkir.contactsdemo.domain.entity.Contact;

import rx.Observable;

/**
 * Created by korolkir on 11.09.16.
 */
public class ContactUnit extends BaseUnit {

    private final ContactApi contactApi;

    public ContactUnit(ContactApi contactApi) {
        this.contactApi = contactApi;
    }

    public Observable<Contact> getContact(int id) {
        return contactApi.getContact(id).compose(this.<Contact>applySchedulers());
    }
}
