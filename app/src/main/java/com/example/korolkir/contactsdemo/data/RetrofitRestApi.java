package com.example.korolkir.contactsdemo.data;

import com.example.korolkir.contactsdemo.domain.ContactApi;
import com.example.korolkir.contactsdemo.domain.PostApi;
import com.example.korolkir.contactsdemo.domain.entity.Contact;
import com.example.korolkir.contactsdemo.domain.entity.Post;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 11.09.16.
 */
public class RetrofitRestApi implements PostApi, ContactApi {

    private Retrofit retrofit;
    private RetrofitRestService restApi;

    public RetrofitRestApi(String baseUrl) {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .build();
        restApi = retrofit.create(RetrofitRestService.class);
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return restApi.getPosts();
    }

    @Override
    public Observable<Contact> getContact(int id) {
        return restApi.getContact(id);
    }
}
