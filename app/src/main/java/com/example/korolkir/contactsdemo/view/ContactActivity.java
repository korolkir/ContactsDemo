package com.example.korolkir.contactsdemo.view;

import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.presenter.ContactsPresenter;
import com.example.korolkir.contactsdemo.presenter.MainContactsPresenter;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements ContactsView {

    protected static final String LAT = "lat";
    protected static final String LNG = "lng";
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
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsPresenter.cityClicked();
            }
        });
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
        this.email.setText(getResources().getString(R.string.email_text) +  " " + email);
    }

    @Override
    public void showUserWebsite(String website) {
        this.website.setText(getResources().getString(R.string.website_text) + " " + website);
    }

    @Override
    public void showUserPhone(String phone) {
        this.phone.setText(getResources().getString(R.string.phone_text) + " " + phone);
        Linkify.addLinks(this.phone, Patterns.PHONE, "tel:");
    }

    @Override
    public void showUserCity(String city) {
        this.city.setText(getResources().getString(R.string.city_text) + " " + city);
    }

    @Override
    public void showPostId() {
        postIdView.setText(String.valueOf(postId));
    }

    @Override
    public void openMap(double lat, double lng) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(LAT, lat);
        intent.putExtra(LNG, lng);
        startActivity(intent);
    }
}
