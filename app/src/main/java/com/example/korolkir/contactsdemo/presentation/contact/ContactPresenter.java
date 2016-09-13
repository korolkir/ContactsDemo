package com.example.korolkir.contactsdemo.presentation.contact;

import android.content.Context;
import android.util.Log;

import com.example.korolkir.contactsdemo.domain.ContactUnit;
import com.example.korolkir.contactsdemo.domain.entity.Contact;
import com.example.korolkir.contactsdemo.data.DataStorage;
import com.example.korolkir.contactsdemo.domain.entity.Geo;
import com.example.korolkir.contactsdemo.presentation.common.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by korolkir on 05.09.16.
 */
public class ContactPresenter extends BasePresenter<ContactActivity> {

    private Contact currentContact;
    private Context context;
    private ContactUnit contactUnit;
    private DataStorage dataStorage;

    @Inject
    public ContactPresenter(ContactUnit contactUnit, Context context, DataStorage dataStorage) {
        this.contactUnit = contactUnit;
        this.context = context;
        this.dataStorage = dataStorage;
    }


    public void onViewCreate(int userId) {
        mainView.showPostId();
        contactUnit.getContact(userId)
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
                mainView.showContactInfo(contact);
                mainView.stopShowingProgress();
            }
        });
    }


    public void cityClicked() {
        Geo geo = currentContact.getAddress().getGeo();
        mainView.openMap(geo.getLat(), geo.getLng());
    }


    public void onSaveBdButtonClick() {
        if(dataStorage == null) {
            dataStorage = new DataStorage(context);
        }
        if(dataStorage.saveContact(currentContact)) {
            mainView.savedSuccessfully();
        } else {
            mainView.saveError();
        }
    }
}
