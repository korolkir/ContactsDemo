package com.example.korolkir.contactsdemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.presenter.ContactsPresenter;
import com.example.korolkir.contactsdemo.presenter.MainContactsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements ContactsView {

    private ContactsPresenter contactsPresenter;
    private int postId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.website)
    TextView website;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.post_id)
    TextView postIdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int userId = intent.getIntExtra(MainActivity.USER_ID, 0);
        postId = intent.getIntExtra(MainActivity.POST_ID, 0);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title) + String.valueOf(userId));
        setSupportActionBar(toolbar);
        contactsPresenter = new MainContactsPresenter(this);
        contactsPresenter.onViewCreate(userId);
    }

    @Override
    public void showUserName(String name) {
        this.name.setText(name);
    }

    @Override
    public void showUserNickname(String username) {
        nickname.setText(username);
    }

    @Override
    public void showUserEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public void showUserWebsite(String website) {
        this.website.setText(website);
    }

    @Override
    public void showUserPhone(String phone) {
        this.phone.setText(phone);
    }

    @Override
    public void showUserCity(String city) {
        this.city.setText(city);
    }

    @Override
    public void showPostId() {
        postIdView.setText(String.valueOf(postId));
    }
}
