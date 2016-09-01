package com.yingshiyuan.starpark.activity;

import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.activity.BaseActivity;
import com.yingshiyuan.starpark.activity.ContactInfoActivity;
import com.yingshiyuan.starpark.activity.CustomServiceActivity;
import com.yingshiyuan.starpark.activity.LoginActivity;
import com.yingshiyuan.starpark.activity.ManageAddressActivity;
import com.yingshiyuan.starpark.activity.MyOrderActivity;
import com.yingshiyuan.starpark.activity.WriteIdentifyingCodeActivity;
import com.yingshiyuan.starpark.fragment.MainFragment;
import com.yingshiyuan.starpark.utils.ManageUserDataUtil;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * @description:侧滑主页
 * @author:袁东华 created at 2016/7/8 0008 上午 10:14
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView login_tv;
    private RelativeLayout logined_rl;
    private ImageView logined_iv;
    private TextView name_tv;
    private TextView exit_tv;
    private MainFragment mainFragment;

    @Override
    protected void receiveIntentData() {

    }

    @Override
    protected void initTopView()  {
    }


    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //初始化侧滑菜单的headerView
        View headView = navigationView.getHeaderView(0);
        login_tv = (TextView) headView.findViewById(R.id.login_tv);
        logined_rl = (RelativeLayout) headView.findViewById(R.id.logined_rl);
        logined_iv = (ImageView) headView.findViewById(R.id.logined_iv);
        name_tv = (TextView) headView.findViewById(R.id.name_tv);
        exit_tv = (TextView) headView.findViewById(R.id.exit_tv);

        //加载主页
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.content_main, mainFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void initListener() {
        //点击登录
        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);


            }
        });
        //点击退出
        exit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_tv.setVisibility(View.VISIBLE);
                logined_rl.setVisibility(View.GONE);
                ManageUserDataUtil.getInstance().clearUserInfo(activity);
            }
        });
    }

    @Override
    protected void initData(){

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //点击客服
        if (id == R.id.customService_item) {
            Intent intent1 = new Intent(activity, CustomServiceActivity.class);
            activity.startActivity(intent1);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //点击应用更新
        if (id == R.id.updateApp_item) {

        } else if (id == R.id.about_item) {
            //点击关于微宝

        } else {
            //用户已登录
            if (ManageUserDataUtil.getInstance().isLogin(activity)) {
                switch (id) {
                    //点击我的订单
                    case R.id.myOrder_item:

                        break;
                    //点击地址管理
                    case R.id.manageAddress_item:
                        Intent intent1 = new Intent(activity, ManageAddressActivity.class);
                        activity.startActivity(intent1);
                        break;
                    //点击联系方式
                    case R.id.contactWay_item:
                        Intent intent2 = new Intent(activity, ContactInfoActivity.class);
                        activity.startActivity(intent2);
                        break;
                    //点击修改密码
                    case R.id.modifyPassword_item:
                        Intent intent3 = new Intent(activity, WriteIdentifyingCodeActivity.class);
                        intent3.putExtra("flag", "修改密码");
                        activity.startActivity(intent3);
                        break;
                }
            } else {
                //用户未登录
                Intent intent4 = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent4);

            }
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        String name = SharedPreferencesUtil.getInstance().getName(activity);
        if (!"".equals(name)) {
            name_tv.setText(name);
            login_tv.setVisibility(View.GONE);
            logined_rl.setVisibility(View.VISIBLE);
        }

    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

}