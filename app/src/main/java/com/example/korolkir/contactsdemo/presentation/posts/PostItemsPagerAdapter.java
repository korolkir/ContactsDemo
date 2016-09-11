package com.example.korolkir.contactsdemo.presentation.posts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.korolkir.contactsdemo.domain.entity.Post;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by korolkir on 04.09.16.
 */
   // new Instance for fragments
public class PostItemsPagerAdapter extends FragmentPagerAdapter {

    private static final int ITEMS_PER_PAGE = 6;
    private List<Post> postList;
    private List<PostsPageFragment> fragmentList;

    public PostItemsPagerAdapter(FragmentManager fm) {
        super(fm);
        this.postList = new ArrayList<>();
        this.fragmentList = new ArrayList<>();
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
        fragmentList.add(PostsPageFragment.newInstance(getValuesForFragment(numberOfItemsInFragment)));
        notifyDataSetChanged();
    }

    private ArrayList<Post> getValuesForFragment(int size) {
        ArrayList<Post> postArrayList = new ArrayList<>();
        int first = 0;
        for (int index = 0; index < size; index++) {
            postArrayList.add(postList.get(first));
            postList.remove(first);
        }
        return postArrayList;
    }
}
