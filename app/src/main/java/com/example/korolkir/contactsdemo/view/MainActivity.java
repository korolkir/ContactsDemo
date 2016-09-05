package com.example.korolkir.contactsdemo.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.model.Post;
import com.example.korolkir.contactsdemo.presenter.MainPostsPresenter;
import com.example.korolkir.contactsdemo.presenter.PostsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements PostsView {

    public static final String USER_ID  = "userId";
    public static final String POST_ID  = "postId";
    private PostItemsPagerAdapter pagerAdapter;
    private PostsPresenter presenter;
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
        presenter = new MainPostsPresenter(this);
        pagerAdapter = new PostItemsPagerAdapter(getSupportFragmentManager(), postList, presenter);
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);
        presenter.onViewCreate();

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
        Toast.makeText(this,  getResources().getString(R.string.log_saved) + " " + fileName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startActivityForId(int postId, int userId) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra(POST_ID, postId);
        intent.putExtra(USER_ID, userId);
        startActivity(intent);
    }
}
