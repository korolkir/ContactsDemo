package com.example.korolkir.contactsdemo.presentation.common;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.korolkir.contactsdemo.ContactsApp;
import com.example.korolkir.contactsdemo.injection.components.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by korolkir on 11.09.16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public AppComponent getAppComponent() {
        ContactsApp app = (ContactsApp) getApplication();
        return app.getAppComponent();
    }

}


