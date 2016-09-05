package com.example.korolkir.contactsdemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.presenter.ContactsPresenter;
import com.example.korolkir.contactsdemo.presenter.MainContactsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements ContactsView {

    private ContactsPresenter contactsPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int userId = intent.getIntExtra(MainActivity.USER_ID, 0);
        int postId = intent.getIntExtra(MainActivity.POST_ID, 0);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title) + String.valueOf(userId));
        setSupportActionBar(toolbar);
        contactsPresenter = new MainContactsPresenter(this);
        contactsPresenter.onViewCreate(userId);

    }
}
