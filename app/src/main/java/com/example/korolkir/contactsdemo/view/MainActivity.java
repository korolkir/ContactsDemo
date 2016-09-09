package com.example.korolkir.contactsdemo.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.korolkir.contactsdemo.ContactsApplication;
import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.model.Post;
import com.example.korolkir.contactsdemo.presenter.MainPostsPresenter;
import com.example.korolkir.contactsdemo.presenter.PostsPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements PostsView {

    public static final String USER_ID  = "userId";
    public static final String POST_ID  = "postId";
    private PostItemsPagerAdapter pagerAdapter;
    List<Post> postList;



    @Inject PostsPresenter presenter;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.save_logcat_button)
    CircleButton saveButton;
    @BindView(R.id.progress_view)
    CircularProgressView progressView;
    @BindView(R.id.main_image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((ContactsApplication) getApplication()).getPresenterComponent().inject(this);
        startImageAnimation();
        postList = new ArrayList<>();
        //presenter = new MainPostsPresenter(this);
        pagerAdapter = new PostItemsPagerAdapter(getSupportFragmentManager(), postList, presenter);
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);
        presenter.loadPostData();
    }

    @OnClick(R.id.save_logcat_button)
    public void saveLog(View view) {
        presenter.onSaveLog();
    }

    @Override
    public void showPosts(List<Post> posts) {
        postList.clear();
        pagerAdapter.addItems(posts);
        pagerAdapter.notifyDataSetChanged();
        indicator.getDataSetObserver().onChanged();
    }

    @Override
    public void showLogSavingResult(String fileName) {
        Resources res = getResources();
        if(fileName != null) {
            Toast.makeText(this, String.format(res.getString(R.string.log_saved_success), fileName), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, res.getString(R.string.log_saved_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showPostWithId(int postId, int userId) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra(POST_ID, postId);
        intent.putExtra(USER_ID, userId);
        startActivity(intent);
    }

    @Override
    public void stopShowingProgress() {
        progressView.stopAnimation();
        progressView.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void askToEnableNetwork() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle(getResources().getString(R.string.internet_enable_asking_dialog_title));
        alertdialog.setMessage(getResources().getString(R.string.internet_enable_asking_dialog_message));
        alertdialog.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                presenter.loadPostData();
            }
        });
        alertdialog.setCancelable(false);
        alertdialog.show();
    }

    public void startImageAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        image.startAnimation(rotate);
    }
}
