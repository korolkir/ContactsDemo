package com.example.korolkir.contactsdemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;

import com.example.korolkir.contactsdemo.model.Contact;
import com.example.korolkir.contactsdemo.model.ContactsDbHelper;
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
    private ContactsDbHelper contactsDbHelper;


    public MainContactsPresenter() {
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
                contactsView.showContactInfo(contact);
                contactsView.stopShowingProgress();
            }
        });
    }

    @Override
    public void cityClicked() {
        Geo geo = currentContact.getAddress().getGeo();
        contactsView.openMap(geo.getLat(), geo.getLng());
    }

    @Override
    public void onSaveBdButtonClick() {
        if(contactsDbHelper == null) {
            contactsDbHelper = new ContactsDbHelper(context);
        }
        if(contactsDbHelper.addContact(currentContact)) {
            contactsView.savedSuccessfully();
        } else {
            contactsView.saveError();
        }
        Log.i("Database", String.valueOf(contactsDbHelper.getAllContacts().size()));
    }

}
