package com.yingshiyuan.starpark.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.CompanyFilesAdapter;
import com.yingshiyuan.starpark.data.CompanyFile;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:公司档案
 * @author:袁东华 created at 2016/7/15 0015 上午 11:43
 */
@ContentView(R.layout.activity_company_files)
public class CompanyFilesActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CompanyFilesAdapter companyFilesAdapter;
    private List<CompanyFile> list = new ArrayList<>();

    @Override
    protected void receiveIntentData()  {
    }

    @Override
    protected void initTopView()  {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.company_files);

    }

    @Override
    protected void initView()  {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
                activity)
                .color(getResources().getColor(R.color.black_14))
                .size(activity.getResources().getDimensionPixelSize(
                        R.dimen.divider_2dp))
                .build());
        companyFilesAdapter = new CompanyFilesAdapter(activity);
        recyclerView.setAdapter(companyFilesAdapter);



    }

    @Override
    protected void initListener()  {
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        //点击返回
        back_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击条目
        companyFilesAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    //点击营业执照信息
                    case 0:
                        startActivity(new Intent(activity, BusinessLicenceActivity.class));
                        break;
                    //点击法人信息
                    case 1:
                        startActivity(new Intent(activity, JuridicalInfoActivity.class));
                        break;
                    //点击股权结构
                    case 2:
                        startActivity(new Intent(activity, ShareHoldingStructureActivity.class));
                        break;
                    //点击公司组织结构
                    case 3:
                        startActivity(new Intent(activity, OrganizationStructureActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void initData()  {
        list.add(new CompanyFile("营业执照信息"));
        list.add(new CompanyFile("法人信息"));
        list.add(new CompanyFile("股权结构"));
        list.add(new CompanyFile("公司组织结构"));
        list.add(new CompanyFile("银行基本户"));
        list.add(new CompanyFile("财务信息"));
        list.add(new CompanyFile("社会保险登记"));
        list.add(new CompanyFile("公积金登记"));
        companyFilesAdapter.setList(list);
        companyFilesAdapter.notifyDataSetChanged();
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
                //新增地址成功
                case HttpIdentifyingCodeUtil.ADD_ADDRESS_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        Toast.makeText(activity, R.string.operate_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                //新增地址失败
                case HttpIdentifyingCodeUtil.ADD_ADDRESS_F:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


}

