package com.example.korolkir.contactsdemo.domain;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 11.09.16.
 */
public class BaseUnit {

    protected final Scheduler workerThreadScheduler;
    protected final Scheduler mainThreadScheduler;
    private final Observable.Transformer<Object, Object> schedulersTransformer;

    public BaseUnit() {
        this.workerThreadScheduler = Schedulers.io();
        this.mainThreadScheduler = AndroidSchedulers.mainThread();

        this.schedulersTransformer = new Observable.Transformer<Object, Object>() {
            @Override
            public Observable<Object> call(Observable<Object> objectObservable) {
                return objectObservable.subscribeOn(workerThreadScheduler).observeOn(mainThreadScheduler);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public <T>Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
