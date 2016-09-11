package com.example.korolkir.contactsdemo.presentation.posts;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.domain.entity.Post;

import com.example.korolkir.contactsdemo.presentation.common.BaseActivity;
import com.example.korolkir.contactsdemo.presentation.contact.ContactActivity;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class PostsActivity extends BaseActivity implements PostsView {

    private PostItemsPagerAdapter pagerAdapter;
    List<Post> postList;

    @Inject
    PostsPresenter presenter;

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
        getAppComponent().inject(this);
        presenter.attachView(this);
        startImageAnimation();
        postList = new ArrayList<>();
        pagerAdapter = new PostItemsPagerAdapter(getSupportFragmentManager());
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
