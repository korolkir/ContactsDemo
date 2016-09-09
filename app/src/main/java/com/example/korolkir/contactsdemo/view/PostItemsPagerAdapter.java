package com.example.korolkir.contactsdemo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.korolkir.contactsdemo.model.Post;

import com.example.korolkir.contactsdemo.presenter.PostsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korolkir on 04.09.16.
 */
   // new Instance for fragments
public class PostItemsPagerAdapter extends FragmentPagerAdapter {

    private static final int ITEMS_PER_PAGE = 6;
    protected static final String TITLE_KEY  = "T";
    private List<Post> postList;
    private List<PostPageFragment> fragmentList;
    private PostsPresenter presenter;

    public PostItemsPagerAdapter(FragmentManager fm, List<Post> postList, PostsPresenter presenter) {
        super(fm);
        this.presenter = presenter;
        this.postList = postList;
        fragmentList = new ArrayList<>();
        for (int i = 0; i < postList.size(); i++) {
            fragmentList.add(new PostPageFragment());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return  fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = fragmentList.get(position);
        int internalPosition = fragmentList.indexOf(fragment);
        return super.instantiateItem(container, internalPosition);
    }

    public void addItems(List<Post> posts) {
        postList.clear();
        postList.addAll(posts);
        while (postList.size() > ITEMS_PER_PAGE) {
            addFragmentToList(ITEMS_PER_PAGE);
        }
        addFragmentToList(postList.size());
    }

    private void addFragmentToList(int numberOfItemsInFragment) {
        fragmentList.add(PostPageFragment.newInstance(getValuesForFragment(numberOfItemsInFragment)));
        notifyDataSetChanged();
    }

    private ArrayList<Post> getValuesForFragment(int size) {
        ArrayList<Post> postArrayList = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            postArrayList.add(postList.get(0));
            postList.remove(0);
        }
        return postArrayList;
    }
}
