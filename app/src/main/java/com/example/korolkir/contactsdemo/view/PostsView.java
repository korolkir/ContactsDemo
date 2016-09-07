package com.example.korolkir.contactsdemo.view;

import com.example.korolkir.contactsdemo.model.Post;

import java.util.List;

/**
 * Created by korolkir on 04.09.16.
 */
public interface PostsView {

    void showPosts(List<Post> posts);

    void showLogSavingResult(String fileName);

    void showPostWithId(int id, int userId);
}
