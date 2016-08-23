package com.yingshiyuan.starpark.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.yingshiyuan.starpark.R;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * @description:基础activity
 * @author:袁东华 created at 2016/7/14 0014 上午 11:19
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * Intent传递的字符串参数名
     */
    public final String F = "flag";
    public Activity activity = BaseActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



            hideTitle(true);
            x.view().inject(this);
            initTopView();
            initView();
            receiveIntentData();
            initData();
            initListener();

    }


    public void hideTitle(boolean b) {
        if (b) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    protected void receiveIntentData()  {
    }


    protected void initTopView() {
    }

    ;

    protected void initView() {
    }


    protected void initData()  {
    }


    protected void initListener()  {
    }


    public boolean checkPermission(final String permissionName, final int request_permission) {
        //检查权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            return true;
        }
        if (checkSelfPermission(permissionName) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(permissionName)) {
            Snackbar.make(null, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{permissionName}, request_permission);
                        }
                    });
        } else {
            requestPermissions(new String[]{permissionName}, request_permission);
        }
        return false;
    }
}
