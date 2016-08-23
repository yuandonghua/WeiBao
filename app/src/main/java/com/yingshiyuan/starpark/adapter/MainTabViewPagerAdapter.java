package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.fragment.MainTabFragment;

/**
 * Created by Administrator on 2016/6/22 0022.
 */

public class MainTabViewPagerAdapter extends FragmentPagerAdapter {
    private Activity activity;
    public MainTabViewPagerAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity=activity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return MainTabFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return activity.getString(R.string.tab_honeycomb);
            case 1:
                return activity.getString(R.string.tab_workorder);
            case 2:
                return activity.getString(R.string.tab_myplaces);
        }
        return null;
    }
}
