package com.example.korolkir.contactsdemo.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.view.ShowingView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by korolkir on 04.09.16.
 */
public class ContactsPresenter implements Presenter {

    private ShowingView showingView;
    private Context context;

    public ContactsPresenter(ShowingView showingView) {
        this.showingView = showingView;
        this.context = (Context) showingView;
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


