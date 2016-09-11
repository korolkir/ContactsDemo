package com.example.korolkir.contactsdemo.presentation.contact;

import com.example.korolkir.contactsdemo.domain.entity.Contact;
import com.example.korolkir.contactsdemo.presentation.common.BaseView;

/**
 * Created by korolkir on 05.09.16.
 */
public interface ContactView extends BaseView {

    void showPostId();

    void openMap(double lat, double lng);

    void savedSuccessfully();

    void saveError();

    void showContactInfo(Contact contact);

    void stopShowingProgress();
}
