package com.example.korolkir.contactsdemo.domain;

import com.example.korolkir.contactsdemo.domain.entity.Contact;

import rx.Observable;

/**
 * Created by korolkir on 11.09.16.
 */
public interface ContactApi {
    Observable<Contact> getContact(int id);
}
