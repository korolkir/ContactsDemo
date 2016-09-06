package com.example.korolkir.contactsdemo.view;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by korolkir on 05.09.16.
 */
public interface ContactsView {
    void showUserName(String name);

    void showUserNickname(String username);

    void showUserEmail(String email);

    void showUserWebsite(String website);

    void showUserPhone(String phone);

    void showUserCity(String city);

    void showPostId();

    void openMap(double lat, double lng);

    void savedSuccessfully();

    void saveError();
}
