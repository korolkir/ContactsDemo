package com.example.korolkir.contactsdemo.presenter;

import android.util.Log;

import com.example.korolkir.contactsdemo.model.Contact;
import com.example.korolkir.contactsdemo.model.UserDataModel;
import com.example.korolkir.contactsdemo.model.UserDataRepository;
import com.example.korolkir.contactsdemo.view.ContactsView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 05.09.16.
 */
public class MainContactsPresenter implements ContactsPresenter {

    private ContactsView contactsView;
    private UserDataModel userDataModel;

    public MainContactsPresenter(ContactsView contactsView) {
        this.contactsView = contactsView;
        userDataModel = new UserDataRepository();
    }

    @Override
    public void onViewCreate(int userId) {
        userDataModel.getContact(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Contact>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Contact contact) {
                Log.i("Contact", contact.getName());
            }
        });
    }
}
