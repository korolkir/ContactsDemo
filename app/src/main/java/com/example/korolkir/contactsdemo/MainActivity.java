package com.example.korolkir.contactsdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.korolkir.contactsdemo.presenter.ContactsPresenter;
import com.example.korolkir.contactsdemo.presenter.Presenter;
import com.example.korolkir.contactsdemo.view.PostItemsPagerAdapter;
import com.example.korolkir.contactsdemo.view.ShowingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements ShowingView {

    private PagerAdapter pagerAdapter;
    private Presenter presenter;

    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.save_logcat_button)
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pagerAdapter = new PostItemsPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);
        presenter = new ContactsPresenter(this);

    }

    @OnClick(R.id.save_logcat_button)
    public void saveLog(View view) {
        presenter.onSaveLogcatButtonClick();
    }
}
