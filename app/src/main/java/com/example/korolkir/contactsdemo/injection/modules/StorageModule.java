package com.example.korolkir.contactsdemo.injection.modules;

import android.content.Context;

import com.example.korolkir.contactsdemo.data.DataStorage;
import com.example.korolkir.contactsdemo.data.ContactStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korolkir on 11.09.16.
 */
@Module
public class StorageModule {

    private final Context context;

    public StorageModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    ContactStorage provideContactStorage() {
        return new DataStorage(context);
    }
}
