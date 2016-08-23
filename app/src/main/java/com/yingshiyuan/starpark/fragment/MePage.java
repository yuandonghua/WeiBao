package com.yingshiyuan.starpark.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.activity.ContactInfoActivity;
import com.yingshiyuan.starpark.activity.LoginActivity;
import com.yingshiyuan.starpark.activity.ManageAddressActivity;
import com.yingshiyuan.starpark.activity.WriteIdentifyingCodeActivity;
import com.yingshiyuan.starpark.adapter.HoneycombAdapter;
import com.yingshiyuan.starpark.data.HoneycombContent;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.utils.ManageUserDataUtil;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;
import com.yingshiyuan.starpark.view.recyclerview.GridDividerItemDecoration;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 我页面
 */

public class MePage {
    private static MePage instance;
    private View view = null;
    private Activity activity = null;
    //注册的RelativeLayout
    private TextView login_tv;
    //登陆后的RelativeLayout
    private RelativeLayout logined_rl;
    //退出登录按钮
    private TextView exit_tv;
    /**
     * 用户名
     */
    private TextView name_tv;


    /**
     * 开始加载蜂巢界面
     */
    public void start(Activity activity, View view) {
        if (view != null && activity != null && (this.view == null && this.activity == null)) {
            this.activity = activity;
            this.view = view;
            initView();
            refresh();
        }
    }

    public View getView() {
        return view;
    }

    public void refresh() {
        String name = SharedPreferencesUtil.getInstance().getName(activity);
        if (!"".equals(name)) {
            name_tv.setText(name);
            login_tv.setVisibility(View.GONE);
            logined_rl.setVisibility(View.VISIBLE);
        }

    }


    private void initView() {
        logined_rl = (RelativeLayout) view.findViewById(R.id.logined_rl);
        login_tv = (TextView) view.findViewById(R.id.login_tv);
        name_tv = (TextView) view.findViewById(R.id.name_tv);
        //点击登录
        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);


            }
        });
        exit_tv = (TextView) view.findViewById(R.id.exit_tv);
        //点击退出
        exit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_tv.setVisibility(View.VISIBLE);
                logined_rl.setVisibility(View.GONE);
                ManageUserDataUtil.getInstance().clearUserInfo(activity);
            }
        });
        TextView modifyPassword_tv = (TextView) view.findViewById(R.id.modifyPassword_tv);
        //点击修改密码
        modifyPassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WriteIdentifyingCodeActivity.class);
                intent.putExtra("flag", "修改密码");
                activity.startActivity(intent);
            }
        });
        TextView contactWay_tv = (TextView) view.findViewById(R.id.contactWay_tv);
        //点击联系方式
        contactWay_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ContactInfoActivity.class);
                activity.startActivity(intent);
            }
        });
        TextView manageAddress_tv = (TextView) view.findViewById(R.id.manageAddress_tv);
        //点击地址管理
        manageAddress_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ManageAddressActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    private MePage() {
    }

    public static MePage getInstance() {
        if (instance == null) {
            synchronized (MePage.class) {
                if (instance == null) {
                    instance = new MePage();
                }
            }
        }

        return instance;
    }
}
