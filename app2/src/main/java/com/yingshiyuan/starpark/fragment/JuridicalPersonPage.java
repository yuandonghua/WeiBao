package com.yingshiyuan.starpark.fragment;

import android.app.Activity;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.JuridicalPersonAdapter;
import com.yingshiyuan.starpark.adapter.ShareHolderAdapter;
import com.yingshiyuan.starpark.data.SupplementaryCompanyInfo;
import com.yingshiyuan.starpark.http.CompanyHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.utils.ManageUserDataUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * @description:法人信息界面
 * @author:袁东华 created at 2016/7/29 0029 下午 2:15
 */
public class JuridicalPersonPage {
    private static JuridicalPersonPage instance;
    private View view = null;
    private Activity activity = null;
    private JuridicalPersonAdapter juridicalPersonAdapter;

    private ArrayList<SupplementaryCompanyInfo> list;

    public void setData(ArrayList<SupplementaryCompanyInfo> list) {
        this.list = list;
        initData();
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
        if (list != null && juridicalPersonAdapter != null)
            juridicalPersonAdapter.setList(list);
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
        juridicalPersonAdapter = new JuridicalPersonAdapter(activity);
        recyclerView.setAdapter(juridicalPersonAdapter);
    }

    public void attemptSubmit(Handler handler) {
        String content = "";
        for (SupplementaryCompanyInfo s : list) {
            if (!"".equals(s.getValue())) {
                content += s.getName() + "=" + s.getValue() + "&";
            }
        }
        if (!"".equals(content)) {
            content = content.substring(0, content.length() - 1);
            content = "?user_id=" + ManageUserDataUtil.getInstance().getUserId(activity) + "&" + content;

            CompanyHttp.getInstance().supplementaryJuridicalPerson(handler, content,
                    HttpIdentifyingCodeUtil.SUPPLEMENTARY_JURIDICAL_PERSON_S,
                    HttpIdentifyingCodeUtil.SUPPLEMENTARY_JURIDICAL_PERSON_F);
        }
    }

    public static JuridicalPersonPage getInstance() {
        if (instance == null) {
            synchronized (JuridicalPersonPage.class) {
                if (instance == null) {
                    instance = new JuridicalPersonPage();
                }
            }
        }

        return instance;
    }

    private JuridicalPersonPage() {


    }
}
