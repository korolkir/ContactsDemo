package com.example.korolkir.contactsdemo.presentation.common;

/**
 * Created by korolkir on 11.09.16.
 */
public class BasePresenter <V extends MainView> implements Presenter<V> {

    protected V mainView;

    @Override
    public void attachView(V view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;
    }
}
