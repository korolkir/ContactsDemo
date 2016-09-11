package com.example.korolkir.contactsdemo.injection.modules;

import com.example.korolkir.contactsdemo.data.RetrofitRestApi;
import com.example.korolkir.contactsdemo.domain.ContactUnit;
import com.example.korolkir.contactsdemo.domain.entity.Contact;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korolkir on 11.09.16.
 */
@Module
public class ContactModule {

    @Provides
    @Singleton
    ContactUnit provideContactUnit(RetrofitRestApi retrofitRestApi) {
        return new ContactUnit(retrofitRestApi);
    }

}
