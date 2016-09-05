package com.example.korolkir.contactsdemo.presenter;

import com.example.korolkir.contactsdemo.model.Post;

import java.util.List;

/**
 * Created by korolkir on 04.09.16.
 */
public interface Presenter {

    void onSaveLogcatButtonClick();

    void onViewCreated();

    void onPostItemClicked(String id);
}
