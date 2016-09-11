package com.example.korolkir.contactsdemo.presentation.posts;

import android.content.Context;

import com.example.korolkir.contactsdemo.domain.entity.Post;
import com.example.korolkir.contactsdemo.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by korolkir on 11.09.16.
 */
public class PostsPagePresenter extends BasePresenter<PostsPageFragment> {

    @Inject
    public PostsPagePresenter(Context context) {
    }

    public void onPostItemClicked(int postId, int userId) {
        mainView.showPostWithId(postId, userId);
    }

}
