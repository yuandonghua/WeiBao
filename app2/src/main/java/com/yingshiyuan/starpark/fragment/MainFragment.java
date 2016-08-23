package com.yingshiyuan.starpark.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.activity.AccountActivity;
import com.yingshiyuan.starpark.activity.CommerceServiceActivity;
import com.yingshiyuan.starpark.activity.CompanyFilesActivity;
import com.yingshiyuan.starpark.activity.FinancialServiceActivity;
import com.yingshiyuan.starpark.activity.HumanServiceActivity;
import com.yingshiyuan.starpark.activity.InvoiceActivity;
import com.yingshiyuan.starpark.activity.WorkOrderActivity;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * @description:主页的Fragment
 * @author:袁东华
 * @time:2016/7/8 0008 下午 5:27:27
 */
@ContentView(R.layout.fragment_main)
public class MainFragment extends BaseFragment {
    //开票按钮
    @ViewInject(R.id.receipt_tv)
    private Button receipt_tv;
    //记账按钮
    @ViewInject(R.id.account_tv)
    private Button account_tv;
    //我的工单按钮
    @ViewInject(R.id.myWorkerOrder_tv)
    private TextView myWorkerOrder_tv;
    //公司档案按钮
    @ViewInject(R.id.companyFiles_tv)
    private TextView companyFiles_tv;
    //财务服务按钮
    @ViewInject(R.id.financialService_tv)
    private TextView financialService_tv;
    //人力服务按钮
    @ViewInject(R.id.humanService_tv)
    private TextView humanService_tv;
    //工商服务按钮
    @ViewInject(R.id.commerce_tv)
    private TextView commerce_tv;
    @Override
    public void setListener() {

        //点击开票
        receipt_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InvoiceActivity.class);
                startActivity(intent);

            }
        });

        //点击记账
        account_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);

            }
        });
        //点击我的工单
        myWorkerOrder_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WorkOrderActivity.class);
                startActivity(intent);
            }
        });
        //点击公司档案
        companyFiles_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CompanyFilesActivity.class);
                startActivity(intent);
            }
        });
        //点击财务服务
        financialService_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FinancialServiceActivity.class);
                startActivity(intent);
            }
        });
        //点击r人力服务
        humanService_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HumanServiceActivity.class);
                startActivity(intent);
            }
        });
        //点击工商服务
        commerce_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CommerceServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
