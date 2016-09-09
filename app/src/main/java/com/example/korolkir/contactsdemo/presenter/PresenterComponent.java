package com.example.korolkir.contactsdemo.presenter;

import com.example.korolkir.contactsdemo.view.MainActivity;

import dagger.Component;

/**
 * Created by korolkir on 10.09.16.
 */
@Component (modules = PresenterModule.class)
public interface PresenterComponent {
    void inject(MainActivity mainActivity);
}
