package com.yingshiyuan.starpark.fragment;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.ShareHolderAdapter;
import com.yingshiyuan.starpark.adapter.StewardRemindAdapter;
import com.yingshiyuan.starpark.data.ShareHolder;
import com.yingshiyuan.starpark.http.CompanyHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.utils.ManageUserDataUtil;

import java.util.ArrayList;

/**
 * @description:股东界面
 * @author:袁东华 created at 2016/7/29 0029 下午 2:15
 */
public class ShareHolderPage {
    private static ShareHolderPage instance;
    private View view = null;
    private Activity activity = null;
    private ShareHolderAdapter shareHolderAdapter;
    private ArrayList<ShareHolder> list = new ArrayList<>();

    private ShareHolderPage() {
    }

    public void start(Activity activity, View view) {
        if (view != null && activity != null && (this.view == null && this.activity == null)) {
            this.activity = activity;
            this.view = view;
            initView();
            initData();
        }
    }

    public View getView() {
        return view;
    }

    private void initData() {
        list.add(new ShareHolder());
        shareHolderAdapter.setList(list);
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
//                activity)
//                .color(getResources().getColor(R.color.black_14))
//                .size(activity.getResources().getDimensionPixelSize(
//                        R.dimen.divider_2dp))
//                .build());
        shareHolderAdapter = new ShareHolderAdapter(activity);
        recyclerView.setAdapter(shareHolderAdapter);


    }

    public void attemptSubmit(Handler handler, String comp_id) {
        list = shareHolderAdapter.getList();
        for (ShareHolder sh : list) {
            if (sh != null && !"".equals(sh.getName()) && !"".equals(sh.getIdno()) && !"".equals(sh.getCapital())) {
                CompanyHttp.getInstance().supplementaryShareHolder(handler,
                        ManageUserDataUtil.getInstance().getUserId(activity), comp_id,
                        sh.getName(), sh.getIdno(), sh.getCapital(),
                        HttpIdentifyingCodeUtil.SUPPLEMENTARY_SHAREHOLDER_S,
                        HttpIdentifyingCodeUtil.SUPPLEMENTARY_SHAREHOLDER_F);
            }
        }
    }

    public static ShareHolderPage getInstance() {
        if (instance == null) {
            synchronized (ShareHolderPage.class) {
                if (instance == null) {
                    instance = new ShareHolderPage();
                }
            }
        }

        return instance;
    }
}
