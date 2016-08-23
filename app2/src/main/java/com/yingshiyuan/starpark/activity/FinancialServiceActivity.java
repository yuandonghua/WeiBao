package com.yingshiyuan.starpark.activity;

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
import com.yingshiyuan.starpark.adapter.FinancialServiceAdapter;
import com.yingshiyuan.starpark.data.FinancialService;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:财务服务
 * @author:袁东华 created at 2016/7/15 0015 下午 1:49
 */
@ContentView(R.layout.activity_financial)
public class FinancialServiceActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private FinancialServiceAdapter financialServiceAdapter;
    private List<FinancialService> list = new ArrayList<>();

    @Override
    protected void receiveIntentData()  {
    }

    @Override
    protected void initTopView()  {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.financial_services);

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
        financialServiceAdapter = new FinancialServiceAdapter(activity);
        recyclerView.setAdapter(financialServiceAdapter);
        financialServiceAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

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
    }

    @Override
    protected void initData()  {
        list.add(new FinancialService("记账", R.drawable.tax_accounting_selector));
        list.add(new FinancialService("开普通发票", R.drawable.invoice_selector_big));
        list.add(new FinancialService("开专票", R.drawable.special_invoice_selector_big));
        list.add(new FinancialService("账目明细", R.drawable.account_detail_selector));
        list.add(new FinancialService("财务报表", R.drawable.financial_statements_selector));
        list.add(new FinancialService("纳税统计", R.drawable.tax_statistics_selector));
        financialServiceAdapter.setList(list);
        financialServiceAdapter.notifyDataSetChanged();
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

