package com.example.korolkir.contactsdemo.view;

import com.example.korolkir.contactsdemo.model.Post;

import java.util.List;

/**
 * Created by korolkir on 04.09.16.
 */
public interface ShowingView {

    void showPosts(List<Post> posts);

    void showLogFileName(String fileName);

    void startActivityForId(int id, int userId);
}
