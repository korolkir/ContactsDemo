package com.example.korolkir.contactsdemo.presenter;

import com.example.korolkir.contactsdemo.model.Post;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korolkir on 10.09.16.
 */
@Module
public class PresenterModule {

    @Provides PostsPresenter providePostsPresenter() {
        return new MainPostsPresenter();
    }

    @Provides ContactsPresenter provideContactsPresenter() {
        return new MainContactsPresenter();
    }

}
