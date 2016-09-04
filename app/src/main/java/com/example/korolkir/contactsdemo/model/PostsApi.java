package com.example.korolkir.contactsdemo.model;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by korolkir on 05.09.16.
 */
public interface PostsApi {

    @GET("/posts")
    Observable<List<Post>> getPostList();
}
