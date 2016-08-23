package com.yingshiyuan.starpark.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * @description:基础Fragment
 * @author:袁东华 created at 2016/7/8 0008 下午 5:28
 */
public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.e("onCreateView--------------");
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.e("onActivityCreated--------------");
        setListener();

    }

    public void setListener() {

    }
}
