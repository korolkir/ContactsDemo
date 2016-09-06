package com.example.korolkir.contactsdemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.korolkir.contactsdemo.model.Contact;
import com.example.korolkir.contactsdemo.model.Geo;
import com.example.korolkir.contactsdemo.model.UserDataModel;
import com.example.korolkir.contactsdemo.model.UserDataRepository;
import com.example.korolkir.contactsdemo.view.ContactsView;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 05.09.16.
 */
public class MainContactsPresenter implements ContactsPresenter {

    private ContactsView contactsView;
    private UserDataModel userDataModel;
    private Contact currentContact;
    private Context context;

    public MainContactsPresenter(ContactsView contactsView) {
        this.contactsView = contactsView;
        userDataModel = new UserDataRepository();
        context = (Context) contactsView;
    }

    @Override
    public void onViewCreate(int userId) {
        contactsView.showPostId();
        userDataModel.getContact(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Contact>() {
            @Override
            public void onCompleted() {
                unsubscribe();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Contact contact) {
                currentContact = contact;
                contactsView.showUserName(contact.getName());
                contactsView.showUserNickname(contact.getUsername());
                contactsView.showUserEmail(contact.getEmail());
                contactsView.showUserWebsite(contact.getWebsite());
                contactsView.showUserPhone(contact.getPhone().split(" ")[0]);
                contactsView.showUserCity(contact.getAddress().getCity());
            }
        });
    }

    @Override
    public void cityClicked() {
        Geo geo = currentContact.getAddress().getGeo();
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", geo.getLat(), geo.getLng());
        contactsView.openMap(geo.getLat(), geo.getLng());
    }
}
