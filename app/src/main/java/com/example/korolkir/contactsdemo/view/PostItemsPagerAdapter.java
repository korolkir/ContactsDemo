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
public class PostItemsPagerAdapter extends FragmentPagerAdapter {

    private static final int ITEMS_PER_PAGE = 6;
    protected static final String TITLE_KEY  = "T";
    private int numberOfPages;
    private List<Post> postList;
    private List<PostPageFragment> fragmentList;
    private PostsPresenter presenter;

    public PostItemsPagerAdapter(FragmentManager fm, List<Post> postList, PostsPresenter presenter) {
        super(fm);
        this.presenter = presenter;
        this.postList = postList;
        numberOfPages = postList.size();
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
        return numberOfPages;
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
        PostPageFragment fragment = new PostPageFragment();
        fragment.setArguments(getValuesForFragment(numberOfItemsInFragment));
        fragment.attachPresenter(presenter);
        fragmentList.add(fragment);
        numberOfPages++;
        notifyDataSetChanged();
    }

    private Bundle getValuesForFragment(int size) {
        Bundle values = new Bundle();
        for (int index = 0; index < size; index++) {
            values.putString(String.valueOf(index), String.valueOf(postList.get(0).getId()));
            values.putString(String.valueOf(index) + TITLE_KEY,String.valueOf(postList.get(0).getTitle()));
            postList.remove(0);
        }
        return values;
    }

}
