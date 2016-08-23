package com.yingshiyuan.starpark.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.InvoicePagerAdapter;
import com.yingshiyuan.starpark.view.tab.TabLayout;

/**
 * @description:主页
 * @author:袁东华 created at 2016/8/2 0002 上午 10:55
 */
public class MainActivity extends AppCompatActivity {
    //tab的昵称
    private String[] tabTitles = null;
    //tab的图标
    private int[] tabImage = null;
    private TabLayout tabLayout;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private InvoicePagerAdapter mInvoicePagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabTitles = new String[]{
                this.getString(R.string.tab_honeycomb),
                this.getString(R.string.tab_workorder),
                this.getString(R.string.tab_myplaces)};
        tabImage = new int[]{
                R.drawable.tab_honeycomb_selector,
                R.drawable.tab_workerorder_selector,
                R.drawable.tab_me_selector};
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mInvoicePagerAdapter = new InvoicePagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mInvoicePagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        try {
            //给tab设置自定义View
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                tab.setIcon(tabImage[i]);

            }
        } catch (Exception e) {
            Log.e("异常", e.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}



