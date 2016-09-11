package com.example.korolkir.contactsdemo.data;

import com.example.korolkir.contactsdemo.domain.entity.Contact;
import com.example.korolkir.contactsdemo.domain.entity.Post;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by korolkir on 11.09.16.
 */
public interface RetrofitRestService {

    @GET("/posts")
    Observable<List<Post>> getPosts();

    @GET("/users/{id}")
    Observable<Contact> getContact(@Path("id") int id);

}
