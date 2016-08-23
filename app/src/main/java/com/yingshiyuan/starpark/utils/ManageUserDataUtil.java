package com.yingshiyuan.starpark.utils;

import android.app.Activity;

/**
 * 管理用户的一些数据
 */

public class ManageUserDataUtil {
    /**
     * 清除用户的数据
     * @return
     */
    public boolean clearUserInfo(Activity activity){
        SharedPreferencesUtil.getInstance().setId(activity,"");
        SharedPreferencesUtil.getInstance().setName(activity,"");
        SharedPreferencesUtil.getInstance().setPassword(activity,"");
        SharedPreferencesUtil.getInstance().setPhone(activity,"");
        SharedPreferencesUtil.getInstance().setEmail(activity,"");
        return true;
    }
    private static ManageUserDataUtil instance;

    private ManageUserDataUtil() {
    }

    public static ManageUserDataUtil getInstance() {
        if (instance == null) {
            synchronized (ManageUserDataUtil.class) {
                if (instance == null) {
                    instance = new ManageUserDataUtil();
                }
            }
        }
        return instance;
    }


}
