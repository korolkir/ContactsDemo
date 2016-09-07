package com.example.korolkir.contactsdemo.view;

import com.example.korolkir.contactsdemo.model.Contact;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by korolkir on 05.09.16.
 */
public interface ContactsView {

    void showPostId();

    void openMap(double lat, double lng);

    void savedSuccessfully();

    void saveError();


    void showContactInfo(Contact contact);
}
