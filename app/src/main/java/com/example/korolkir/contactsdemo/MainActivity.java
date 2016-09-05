package com.example.korolkir.contactsdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.korolkir.contactsdemo.model.Post;
import com.example.korolkir.contactsdemo.presenter.ContactsPresenter;
import com.example.korolkir.contactsdemo.presenter.Presenter;
import com.example.korolkir.contactsdemo.view.PostItemsPagerAdapter;
import com.example.korolkir.contactsdemo.view.PostPageFragment;
import com.example.korolkir.contactsdemo.view.ShowingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements ShowingView {

    private PostItemsPagerAdapter pagerAdapter;
    private Presenter presenter;
    List<Post> postList;

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
        postList = new ArrayList<>();
        pagerAdapter = new PostItemsPagerAdapter(getSupportFragmentManager(), postList);
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);
        presenter = new ContactsPresenter(this);
        presenter.onViewCreated();

    }

    @OnClick(R.id.save_logcat_button)
    public void saveLog(View view) {
        presenter.onSaveLogcatButtonClick();
    }

    @Override
    public void showPosts(List<Post> posts) {
        postList.clear();
        pagerAdapter.addItems(posts);
        pagerAdapter.notifyDataSetChanged();
        indicator.getDataSetObserver().onChanged();
    }

    @Override
    public void showLogFileName(String fileName) {
        Toast.makeText(this, R.string.log_saved + " " + fileName, Toast.LENGTH_SHORT).show();
    }
}
