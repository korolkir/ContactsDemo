package com.example.korolkir.contactsdemo;

import android.app.Application;

import com.example.korolkir.contactsdemo.presenter.DaggerPresenterComponent;
import com.example.korolkir.contactsdemo.presenter.PresenterComponent;
import com.example.korolkir.contactsdemo.presenter.PresenterModule;

/**
 * Created by korolkir on 10.09.16.
 */
public class ContactsApplication extends Application {

    private PresenterComponent presenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        presenterComponent = DaggerPresenterComponent.builder().presenterModule(new PresenterModule()).build();
    }

    public PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }
}
