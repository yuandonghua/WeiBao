package com.yingshiyuan.starpark.utils;

import android.app.Activity;

import com.yingshiyuan.starpark.R;

import org.xutils.image.ImageOptions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:xutils框架
 * @author:袁东华 created at 2016/7/21 0021 下午 5:14
 */
public class XUtils {
    public ImageOptions getImageOptions(Activity activity) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.bg_loading)
                .setFailureDrawableId(R.drawable.bg_failure)
                .build();
        return imageOptions;
    }
    public ImageOptions getImageOptions(Activity activity,boolean isCache) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setUseMemCache(isCache)
                .setLoadingDrawableId(R.drawable.bg_loading)
                .setFailureDrawableId(R.drawable.bg_failure)
                .build();
        return imageOptions;
    }
    private static XUtils mInstance = null;

    private XUtils() {
    }

    public static XUtils getInstance() {
        if (mInstance == null) {
            synchronized (XUtils.class) {
                if (mInstance == null) {
                    mInstance = new XUtils();
                }
            }

        }
        return mInstance;
    }

}
