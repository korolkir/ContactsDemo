package com.example.korolkir.contactsdemo.domain;

import com.example.korolkir.contactsdemo.domain.entity.Post;

import java.util.List;

import rx.Observable;

/**
 * Created by korolkir on 11.09.16.
 */
public interface PostApi {
    Observable<List<Post>> getPosts();
}
