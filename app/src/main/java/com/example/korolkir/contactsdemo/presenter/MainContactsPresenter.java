package com.example.korolkir.contactsdemo.presenter;

import com.example.korolkir.contactsdemo.view.ContactsView;

/**
 * Created by korolkir on 05.09.16.
 */
public class MainContactsPresenter implements ContactsPresenter {

    private ContactsView contactsView;

    public MainContactsPresenter(ContactsView contactsView) {
        this.contactsView = contactsView;
    }

    @Override
    public void onViewCreate() {
    }
}
