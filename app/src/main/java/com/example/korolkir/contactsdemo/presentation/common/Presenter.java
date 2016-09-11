package com.example.korolkir.contactsdemo.presentation.common;

/**
 * Created by korolkir on 10.09.16.
 */
public interface Presenter<T> {
    void attachView(T view);
    void detachView();
}
