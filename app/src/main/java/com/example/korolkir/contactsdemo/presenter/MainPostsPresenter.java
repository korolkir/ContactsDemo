package com.example.korolkir.contactsdemo.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.korolkir.contactsdemo.model.Post;
import com.example.korolkir.contactsdemo.model.UserDataModel;
import com.example.korolkir.contactsdemo.model.UserDataRepository;
import com.example.korolkir.contactsdemo.view.PostsView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 04.09.16.
 */
public class MainPostsPresenter implements PostsPresenter {

    private PostsView postsView;
    private Context context;
    private UserDataModel userDataModel;
    private List<Post> postList;
    private String logFileName;

    public MainPostsPresenter(final PostsView postsView) {
        this.postsView = postsView;
        this.context = (Context) postsView;
        postList = new ArrayList<>();
        userDataModel = new UserDataRepository();
    }

    @Override
    public void onSaveLog() {
        saveLog().subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                unsubscribe();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    postsView.showLogSavingResult(logFileName);
                } else {
                    postsView.showLogSavingResult(null);
                }
            }
        });
    }

    private Observable<Boolean> saveLog() {
        Observable<Boolean> save = Observable.create(
                new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        subscriber.onNext(saveLogInFile());
                    }
                })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        return save;
    }

    private Boolean saveLogInFile() {
        logFileName = "logcat_"+System.currentTimeMillis()+".txt";
        File outputFile = new File(context.getExternalCacheDir(), logFileName);
        try {
            @SuppressWarnings("unused")
            Process process = Runtime.getRuntime().exec("logcat -f "+outputFile.getAbsolutePath());
            return true;
        } catch (IOException e) {
            Log.i("FileSav", e.toString());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void loadPostData() {
        if(isNetworkConnected()) {
            userDataModel.getPostList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Post>>() {
                        @Override
                        public void onCompleted() {
                            unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<Post> posts) {
                            postList.addAll(posts);
                            postsView.showPosts(posts);
                            postsView.stopShowingProgress();
                        }
                    });
        } else {
            postsView.askToEnableNetwork();
        }
    }

    @Override
    public void onPostItemClicked(int id) {

        postsView.showPostWithId(id,getUserId(id));
    }

    private int getUserId(int id) {
        int userId = 0;
        for(Post post: postList) {
            if (post.getId() == id) {
                userId = post.getUserId();
            }
        }
        return userId;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}


