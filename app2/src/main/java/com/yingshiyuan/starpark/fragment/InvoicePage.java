package com.yingshiyuan.starpark.fragment;

import android.app.Activity;
import android.view.View;

/**
 * 蜂巢页面
 */

public class InvoicePage {
    private static InvoicePage instance;
    private View view = null;
    private Activity activity = null;



    private InvoicePage() {


    }

    /**
     * 开始加载蜂巢界面
     */
    public void start(Activity activity, View view) {
        if (view != null && activity != null && (this.view == null && this.activity == null)) {
            this.activity = activity;
            this.view = view;
            initView();
            initData();
        }
    }
    public View getView(){
        return view;
    }

    private void initData() {

    }

    private void initView() {



    }

    public static InvoicePage getInstance() {
        if (instance == null) {
            synchronized (InvoicePage.class) {
                if (instance == null) {
                    instance = new InvoicePage();
                }
            }
        }

        return instance;
    }
}
