package com.example.korolkir.contactsdemo.model;

import android.util.Log;

import com.example.korolkir.contactsdemo.presenter.Presenter;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 05.09.16.
 */
public class PostsRepository  implements PostsModel {

    private Retrofit retrofit;
    private PostsApi postsApi;
    private Presenter presenter;

    public PostsRepository(Presenter presenter) {
        this.presenter = presenter;
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .build();

        postsApi = retrofit.create(PostsApi.class);
    }

    @Override
    public Observable<List<Post>> getPostList() {
        return postsApi.getPostList();
    }

}
