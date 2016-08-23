package com.yingshiyuan.starpark.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import org.xutils.x;

/**
 * 基础activity
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**Intent传递的字符串参数名*/
    public final String F="flag";
    public Activity activity=BaseActivity.this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            hideTitle(true);
            x.view().inject(this);
            initTopView();
            initView();
            initListener();
            receiveIntentData();
            initData();
        }catch (Exception e){

        }
    }

    public void hideTitle(boolean b) {
        if (b){
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    protected abstract void receiveIntentData() throws Exception;
    protected abstract void initTopView() throws Exception;
    protected abstract void initView() throws Exception;

    protected abstract void initListener() throws Exception;

    protected abstract void initData() throws Exception;
}
