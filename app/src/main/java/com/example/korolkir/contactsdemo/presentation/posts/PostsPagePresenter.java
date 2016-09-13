package com.example.korolkir.contactsdemo.presentation.posts;

import android.content.Context;
import android.util.Log;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.data.RandomGenerator;
import com.example.korolkir.contactsdemo.domain.entity.Post;
import com.example.korolkir.contactsdemo.presentation.common.BasePresenter;

import java.util.Random;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by korolkir on 11.09.16.
 */
public class PostsPagePresenter extends BasePresenter<PostsPageFragment> {

    private Context context;

    @Inject
    public PostsPagePresenter(Context context) {
        this.context = context;
    }

    public void onPostItemClicked(int postId, int userId) {
        mainView.showPostWithId(postId, userId);
    }

    @Override
    public void attachView(PostsPageFragment view) {
        super.attachView(view);
        setItemsBackgrounds();
    }

    private void setItemsBackgrounds() {
        final int[] colors = context.getResources().getIntArray(R.array.idItemColors);
        RandomGenerator.randomIntegerObservable(6, 0, colors.length - 1).subscribe(new Subscriber<Integer>() {
            int itemIndex = 0;

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                mainView.setItemColor(itemIndex, colors[integer] );
                itemIndex++;
            }
        });
    }

}
