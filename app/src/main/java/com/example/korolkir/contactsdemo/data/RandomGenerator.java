package com.example.korolkir.contactsdemo.data;

import android.content.Context;

import com.example.korolkir.contactsdemo.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import butterknife.BindColor;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 13.09.16.
 */
public class RandomGenerator {

    public static Observable<Integer> randomIntegerObservable(final int howMany, final int from, final int to) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Random generator = new Random();
                Set<Integer> generatedValues = new HashSet<>();
                for (int i = 0; i < howMany; i++) {
                    int value = from + generator.nextInt(to - from + 1);
                    while (!generatedValues.add(value)) {
                        value = from + generator.nextInt(to - from + 1);
                    }
                    subscriber.onNext(value);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
