package com.example.korolkir.contactsdemo.injection.modules;

import android.app.Application;
import android.content.Context;

import com.example.korolkir.contactsdemo.ContactsApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korolkir on 10.09.16.
 */
@Module
public class AppModule {

    protected final Context context;

    public AppModule(Application application) {
        this.context = application.getApplicationContext();
    }

    @Provides
    Context provideContext() {
        return context;
    }

}
