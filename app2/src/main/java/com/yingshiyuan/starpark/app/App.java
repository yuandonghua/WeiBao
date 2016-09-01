package com.yingshiyuan.starpark.app;

import android.app.Application;

import com.yingshiyuan.starpark.BuildConfig;
import com.yingshiyuan.starpark.utils.CrashHandler;

import org.xutils.x;

/**
 *@description:项目的Application
 *@author:袁东华
 *created at 2016/7/4 0004 下午 5:53
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler.getInstance();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.
    }
}
