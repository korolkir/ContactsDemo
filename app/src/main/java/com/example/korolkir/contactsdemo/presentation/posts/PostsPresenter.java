package com.example.korolkir.contactsdemo.presentation.posts;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.korolkir.contactsdemo.domain.PostUnit;
import com.example.korolkir.contactsdemo.domain.entity.Post;
import com.example.korolkir.contactsdemo.presentation.common.BasePresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 04.09.16.
 */
public class PostsPresenter extends BasePresenter<PostsActivity> {

    private Context context;
    private PostUnit postUnit;
    private List<Post> postList;

    @Inject
    public PostsPresenter(PostUnit postUnit, Context context) {
        this.postUnit = postUnit;
        this.context = context;
        postList = new ArrayList<>();
    }

    public void onSaveLog() {
        saveLog().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                unsubscribe();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String filemame) {
                if (filemame != null) {
                    mainView.showLogSavingResult(filemame);
                } else {
                    mainView.showLogSavingResult(null);
                }
            }
        });
    }

    private Observable<String> saveLog() {
        Observable<String> save = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(saveLogInFile());
                    }
                })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        return save;
    }

    private String saveLogInFile() {
        String logFileName = "logcat_"+System.currentTimeMillis()+".txt";
        File outputFile = new File(context.getExternalCacheDir(), logFileName);
        try {
            @SuppressWarnings("unused")
            Process process = Runtime.getRuntime().exec("logcat -f "+outputFile.getAbsolutePath());
            return logFileName;
        } catch (IOException e) {
            Log.i("FileSav", e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public void loadPostData() {
        if(isNetworkConnected()) {
            postUnit.getPosts()
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
                            mainView.showPosts(posts);
                            mainView.stopShowingProgress();
                        }
                    });
        } else {
            mainView.askToEnableNetwork();
        }
    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}


