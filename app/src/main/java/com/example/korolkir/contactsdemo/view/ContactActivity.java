package com.example.korolkir.contactsdemo.view;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.model.Contact;
import com.example.korolkir.contactsdemo.presenter.ContactsPresenter;
import com.example.korolkir.contactsdemo.presenter.MainContactsPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.name_layout)
    LinearLayout nameLinear;
    @BindView(R.id.details_layout)
    LinearLayout detailsLinear;
    @BindView(R.id.progress_view)
    CircularProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int userId = intent.getIntExtra(MainActivity.USER_ID, 0);
        postId = intent.getIntExtra(MainActivity.POST_ID, 0);
        toolbar.setTitle(String.format(getResources().getString(R.string.toolbar_title), userId));
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

    @Override
    public void savedSuccessfully() {
        Toast.makeText(this, getResources().getString(R.string.save_successfull), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveError() {
        Toast.makeText(this, getResources().getString(R.string.save_error
        ), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContactInfo(Contact contact) {
        detailsLinear.setVisibility(View.VISIBLE);
        nameLinear.setVisibility(View.VISIBLE);
        postIdView.setVisibility(View.VISIBLE);
        Resources res = getResources();
        name.setText(contact.getName());
        nickname.setText(contact.getUsername());
        email.setText(String.format(res.getString(R.string.email_text), contact.getEmail()));
        phone.setText(String.format(res.getString(R.string.phone_text), contact.getPhone().split(" ")[0]));
        Linkify.addLinks(this.phone, Patterns.PHONE, "tel:");
        website.setText(String.format(res.getString(R.string.website_text), contact.getWebsite()));
        city.setText(String.format(res.getString(R.string.city_text), contact.getAddress().getCity()));
    }

    @Override
    public void stopShowingProgress() {
        progressView.stopAnimation();
        progressView.setVisibility(View.GONE);
    }

    @OnClick(R.id.save_bd)
    public void saveLog(View view) {
        contactsPresenter.onSaveBdButtonClick();
    }

}
