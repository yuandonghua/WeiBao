package com.yingshiyuan.starpark.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.InvoicePagerAdapter;
import com.yingshiyuan.starpark.adapter.SupplementaryPagerAdapter;
import com.yingshiyuan.starpark.data.SupplementaryCompanyInfo;
import com.yingshiyuan.starpark.fragment.JuridicalPersonPage;
import com.yingshiyuan.starpark.fragment.ShareHolderPage;
import com.yingshiyuan.starpark.fragment.WorkerPage;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.view.tab.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * @description:开票界面
 * @author:袁东华 created at 2016/7/13 0013 下午 1:26
 */
@ContentView(R.layout.activity_supplementary_company_info)
public class SupplementaryCompanyInfoActivity extends BaseActivity {

    @ViewInject(R.id.container)
    private ViewPager container;
    @ViewInject(R.id.tabs)
    private TabLayout tabs;
    private SupplementaryPagerAdapter supplementaryPagerAdapter;
    private String ext = "";
    private String procId = "";
    private String linkId = "";
    private ArrayList<SupplementaryCompanyInfo> list = new ArrayList<>();
    //提交
    @ViewInject(R.id.right_tv)
    private TextView right_tv;
    //标题
    @ViewInject(R.id.title_tv)
    private TextView title_tv;

    @Override
    protected void receiveIntentData() {
        linkId= getIntent().getStringExtra("linkId");
        procId = getIntent().getStringExtra("procId");
        ext = getIntent().getStringExtra("ext");
        analyzeJson();
    }

    @Override
    protected void initTopView() {
        title_tv.setText(R.string.supplementary_company_info);
        right_tv.setText(R.string.action_submit);
        right_tv.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initView() {
        supplementaryPagerAdapter = new SupplementaryPagerAdapter(getSupportFragmentManager(), this);
        container.setAdapter(supplementaryPagerAdapter);
        //是否约束TabLayout最低高度
        tabs.restrainHeight(true);
        //设置TabView水平展示内容
        tabs.setupWithViewPager(container);


    }

    @Override
    protected void initListener() {
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        //点击返回
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击提交
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JuridicalPersonPage.getInstance().attemptSubmit(handler);
                ShareHolderPage.getInstance().attemptSubmit(handler,linkId);
                WorkerPage.getInstance().attemptSubmit(handler,linkId);
            }
        });
    }

    @Override
    protected void initData() {
        JuridicalPersonPage.getInstance().setData(list);

    }


    public Handler handler = new Handler() {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //补充公司法人信息成功
                case HttpIdentifyingCodeUtil.SUPPLEMENTARY_JURIDICAL_PERSON_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        LogUtil.e("补充公司法人信息成功");
                    }

                    break;
                //补充公司法人信息失败
                case HttpIdentifyingCodeUtil.SUPPLEMENTARY_JURIDICAL_PERSON_F:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
                //补充公司股东信息成功
                case HttpIdentifyingCodeUtil.SUPPLEMENTARY_SHAREHOLDER_S:
                    Bundle data2 = msg.getData();
                    if (data2 != null) {
                        LogUtil.e("补充公司股东信息成功");
                    }

                    break;
                //补充公司股东信息失败
                case HttpIdentifyingCodeUtil.SUPPLEMENTARY_SHAREHOLDER_F:
                    Bundle errorData2 = msg.getData();
                    if (errorData2 != null) {
                        String message = errorData2.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
                //补充公司职工信息成功
                case HttpIdentifyingCodeUtil.SUPPLEMENTARY_WORKER_S:
                    Bundle data3 = msg.getData();
                    if (data3 != null) {
                        LogUtil.e("补充公司职工信息成功");
                    }

                    break;
                //补充公司职工信息失败
                case HttpIdentifyingCodeUtil.SUPPLEMENTARY_WORKER_F:
                    Bundle errorData3 = msg.getData();
                    if (errorData3 != null) {
                        String message = errorData3.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void analyzeJson() {

        try {
            JSONArray array = new JSONArray(ext);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                SupplementaryCompanyInfo sci = new SupplementaryCompanyInfo();
                sci.setName(jsonObject.optString("name"));
                sci.setType(jsonObject.optString("type"));
                sci.setLabel(jsonObject.optString("label"));
                if ("string".equals(sci.getType())){
                    list.add(sci);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

