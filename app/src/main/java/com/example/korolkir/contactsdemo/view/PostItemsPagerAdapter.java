package com.example.korolkir.contactsdemo.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by korolkir on 04.09.16.
 */
public class PostItemsPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfPages;

    public PostItemsPagerAdapter(FragmentManager fm, int numberOfPages) {
        super(fm);
        this.numberOfPages = numberOfPages;
    }

    @Override
    public Fragment getItem(int position) {
        return  new PostPageFragment();
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }
}
