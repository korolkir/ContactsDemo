package com.example.korolkir.contactsdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.korolkir.contactsdemo.view.PostItemsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;

    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pagerAdapter = new PostItemsPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);
    }
}
