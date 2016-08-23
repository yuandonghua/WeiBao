package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.fragment.InvoiceTabFragment;

/**
 *@description:开票界面的适配器
 *@author:袁东华
 *created at 2016/7/14 0014 上午 11:29
 */
public class InvoicePagerAdapter extends FragmentPagerAdapter {
    private Activity activity;
    public InvoicePagerAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity=activity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return InvoiceTabFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return activity.getString(R.string.tab_plain_invoice);
            case 1:
                return activity.getString(R.string.tab_special_invoice);
        }
        return null;
    }
}
