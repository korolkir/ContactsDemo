package com.example.korolkir.contactsdemo.view;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.korolkir.contactsdemo.model.Post;

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

    public PostItemsPagerAdapter(FragmentManager fm, List<Post> postList) {
        super(fm);
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
        Bundle values = new Bundle();
        int textViewIndex = 0;
        for (int itemIndex = 0; itemIndex < postList.size(); itemIndex++) {
            if(itemIndex%(ITEMS_PER_PAGE*(numberOfPages + 1) - 1) == 0 && itemIndex != 0) {
                PostPageFragment fragment = new PostPageFragment();
                values.putString(String.valueOf(textViewIndex),String.valueOf(postList.get(itemIndex).getId()));
                values.putString(String.valueOf(textViewIndex) + TITLE_KEY,String.valueOf(postList.get(itemIndex).getTitle()));
                fragment.setArguments(values);
                values = new Bundle();
                textViewIndex = 0;
                fragmentList.add(fragment);
                numberOfPages++;
                notifyDataSetChanged();
            } else if(itemIndex == postList.size() - 1) {
                PostPageFragment fragment = new PostPageFragment();
                values.putString(String.valueOf(textViewIndex),String.valueOf(postList.get(itemIndex).getId()));
                values.putString(String.valueOf(textViewIndex) + TITLE_KEY,String.valueOf(postList.get(itemIndex).getTitle()));

                fragment.setArguments(values);
                fragmentList.add(fragment);
                numberOfPages++;
                notifyDataSetChanged();
            } else {
                values.putString(String.valueOf(textViewIndex), String.valueOf(postList.get(itemIndex).getId()));
                values.putString(String.valueOf(textViewIndex) + TITLE_KEY, postList.get(itemIndex).getTitle());
                textViewIndex++;
            }
        }
    }
}
