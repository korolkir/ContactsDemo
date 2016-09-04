package com.example.korolkir.contactsdemo.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.model.Post;
import com.example.korolkir.contactsdemo.model.PostsRepository;
import com.example.korolkir.contactsdemo.view.ShowingView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by korolkir on 04.09.16.
 */
public class ContactsPresenter implements Presenter {

    private ShowingView showingView;
    private Context context;

    public ContactsPresenter(ShowingView showingView) {
        this.showingView = showingView;
        this.context = (Context) showingView;
        PostsRepository repository = new PostsRepository();
        repository.getPostList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        Log.i("Posts", String.valueOf(posts.size()));
                    }
                });
    }

    @Override
    public void onSaveLogcatButtonClick() {
        String fileName = "logcat_"+System.currentTimeMillis()+".txt";
        File outputFile = new File(context.getExternalCacheDir(),fileName);
        try {
            @SuppressWarnings("unused")
            Process process = Runtime.getRuntime().exec("logcat -f "+outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


