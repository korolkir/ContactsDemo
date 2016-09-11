package com.example.korolkir.contactsdemo.injection.components;

import com.example.korolkir.contactsdemo.injection.modules.AppModule;
import com.example.korolkir.contactsdemo.injection.modules.ContactModule;
import com.example.korolkir.contactsdemo.injection.modules.DataModule;
import com.example.korolkir.contactsdemo.injection.modules.PostsModule;
import com.example.korolkir.contactsdemo.injection.modules.StorageModule;
import com.example.korolkir.contactsdemo.presentation.contact.ContactActivity;
import com.example.korolkir.contactsdemo.presentation.posts.PostsActivity;
import com.example.korolkir.contactsdemo.presentation.posts.PostsPageFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by korolkir on 10.09.16.
 */
@Singleton
@Component (modules = {
        AppModule.class,
        PostsModule.class,
        ContactModule.class,
        DataModule.class,
        StorageModule.class
})
public interface AppComponent {
     void inject(PostsActivity postsActivity);
     void inject(PostsPageFragment postsPageFragment);
     void inject(ContactActivity contactActivity);
}
