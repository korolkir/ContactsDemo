package com.example.korolkir.contactsdemo.presentation.posts;

import com.example.korolkir.contactsdemo.domain.entity.Post;
import com.example.korolkir.contactsdemo.presentation.common.MainView;

import java.util.List;

/**
 * Created by korolkir on 04.09.16.
 */
public interface PostsView extends MainView {

    void showPosts(List<Post> posts);

    void showLogSavingResult(String fileName);

    void stopShowingProgress();

    void askToEnableNetwork();
}
