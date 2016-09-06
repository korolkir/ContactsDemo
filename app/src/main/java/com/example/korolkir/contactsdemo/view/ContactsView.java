package com.example.korolkir.contactsdemo.view;

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

    void openMap();
}
