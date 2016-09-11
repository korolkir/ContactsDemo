package com.example.korolkir.contactsdemo.domain;

import com.example.korolkir.contactsdemo.domain.entity.Post;

import java.util.List;

import rx.Observable;

/**
 * Created by korolkir on 11.09.16.
 */
public class PostUnit extends BaseUnit {

    private final PostApi postApi;

    public PostUnit(PostApi postApi) {
        this.postApi = postApi;
    }

    public Observable<List<Post>> getPosts() {
        return postApi.getPosts().compose(this.<List<Post>>applySchedulers());
    }
}
