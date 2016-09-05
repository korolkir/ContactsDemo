package com.example.korolkir.contactsdemo.model;

import java.util.List;

import rx.Observable;

/**
 * Created by korolkir on 05.09.16.
 */
public interface UserDataModel {

    Observable<List<Post>> getPostList();

    Observable<Contact> getContact(int id);
}
