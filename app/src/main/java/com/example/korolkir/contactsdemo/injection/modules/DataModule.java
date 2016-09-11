package com.example.korolkir.contactsdemo.injection.modules;

import com.example.korolkir.contactsdemo.data.RetrofitRestApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korolkir on 11.09.16.
 */
@Module
public class DataModule {
    private final String baseUrl;

    public DataModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    RetrofitRestApi provideRetrofitRestApi() {
        return new RetrofitRestApi(baseUrl);
    }
}
