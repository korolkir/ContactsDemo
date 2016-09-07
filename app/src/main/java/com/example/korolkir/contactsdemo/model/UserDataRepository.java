package com.example.korolkir.contactsdemo.model;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 05.09.16.
 */
public class UserDataRepository implements UserDataModel {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private Retrofit retrofit;
    private UserDataApi userDataApi;

    public UserDataRepository() {

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .build();

        userDataApi = retrofit.create(UserDataApi.class);
    }

    @Override
    public Observable<List<Post>> getPostList() {
        return userDataApi.getPostList();
    }

    @Override
    public Observable<Contact> getContact(int id) {
        return userDataApi.getContact(String.valueOf(id));
    }

}
