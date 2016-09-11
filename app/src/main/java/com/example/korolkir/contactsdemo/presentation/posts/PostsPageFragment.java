package com.example.korolkir.contactsdemo.presentation.posts;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.ContactsApp;
import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.domain.entity.Post;
import com.example.korolkir.contactsdemo.presentation.common.MainView;
import com.example.korolkir.contactsdemo.presentation.contact.ContactActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by korolkir on 04.09.16.
 */
public class PostsPageFragment extends Fragment implements MainView {

    private static final String POST_KEY  = "posts";
    public static final String USER_ID  = "userId";
    public static final String POST_ID  = "postId";

    private View.OnClickListener idItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClick(idViews.indexOf(v));
        }
    };

    private View.OnClickListener titleItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClick(titleViews.indexOf(v));
        }
    };

    @BindViews({R.id.id_1, R.id.id_2, R.id.id_3, R.id.id_4, R.id.id_5, R.id.id_6})
    List<TextView> idViews;
    @BindViews({R.id.title_1, R.id.title_2, R.id.title_3, R.id.title_4, R.id.title_5, R.id.title_6})
    List<TextView> titleViews;
    List<Post> postList;

    @Inject
    PostsPagePresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ContactsApp) getActivity().getApplication()).getAppComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.item_layout, container, false);
        ButterKnife.bind(this, rootView);
        Bundle contacts = getArguments();
        postList = contacts.getParcelableArrayList(POST_KEY);
        if (!postList.isEmpty()) {
            setIdsAndTitles(postList);
            setOnClickListeners(postList.size());
        }
        return rootView;
    }

    private void setIdsAndTitles(List<Post> posts) {
        int index = 0;
        for (Post post: posts) {
            idViews.get(index).setText(String.valueOf(post.getId()));
            titleViews.get(index).setText(post.getTitle());
            index++;
        }
    }

    private void setOnClickListeners(int number) {
        for (int i = 0; i < number; i++) {
            idViews.get(i).setOnClickListener(idItemListener);
            titleViews.get(i).setOnClickListener(titleItemListener);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onItemClick(int index) {
        int postId = postList.get(index).getId();
        int userId = postList.get(index).getUserId();
        presenter.onPostItemClicked(postId, userId);
    }

    public static PostsPageFragment newInstance(ArrayList<Post> postList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(POST_KEY, postList);
        PostsPageFragment fragment = new PostsPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void showPostWithId(int postId, int userId) {
        Intent intent = new Intent(getContext(), ContactActivity.class);
        intent.putExtra(POST_ID, postId);
        intent.putExtra(USER_ID, userId);
        startActivity(intent);
    }

}


