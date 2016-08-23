package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.fragment.InvoiceTabFragment;
import com.yingshiyuan.starpark.fragment.SupplementaryTabFragment;

/**
 * @description:补充公司资料的适配器
 * @author:袁东华 created at 2016/7/29 0029 下午 1:56
 */
public class SupplementaryPagerAdapter extends FragmentPagerAdapter {
    private Activity activity;

    public SupplementaryPagerAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return SupplementaryTabFragment.newInstance(position + 1);
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
                return "法人";
            case 1:
                return "股东";
            case 2:
                return "职工";
        }
        return null;
    }
}
