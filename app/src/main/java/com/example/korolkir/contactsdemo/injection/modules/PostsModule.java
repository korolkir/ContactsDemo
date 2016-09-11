package com.example.korolkir.contactsdemo.injection.modules;

import com.example.korolkir.contactsdemo.data.RetrofitRestApi;
import com.example.korolkir.contactsdemo.domain.PostUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korolkir on 11.09.16.
 */
@Module
public class PostsModule {

    @Provides
    @Singleton
    PostUnit providePostUnit(RetrofitRestApi retrofitRestApi) {
        return new PostUnit(retrofitRestApi);
    }

}
