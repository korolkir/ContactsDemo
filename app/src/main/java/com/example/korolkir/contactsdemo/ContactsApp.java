package com.example.korolkir.contactsdemo;

import android.app.Application;

import com.example.korolkir.contactsdemo.injection.components.AppComponent;
import com.example.korolkir.contactsdemo.injection.components.DaggerAppComponent;
import com.example.korolkir.contactsdemo.injection.modules.AppModule;
import com.example.korolkir.contactsdemo.injection.modules.ContactModule;
import com.example.korolkir.contactsdemo.injection.modules.DataModule;
import com.example.korolkir.contactsdemo.injection.modules.PostsModule;
import com.example.korolkir.contactsdemo.injection.modules.StorageModule;


/**
 * Created by korolkir on 10.09.16.
 */
public class ContactsApp extends Application {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(BASE_URL))
                .postsModule(new PostsModule())
                .contactModule(new ContactModule())
                .storageModule(new StorageModule(this))
                .build();


    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
