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
import com.yingshiyuan.starpark.adapter.WorkOrderAdapter;
import com.yingshiyuan.starpark.data.WorkOrder;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:工单界面
 * @author:袁东华 created at 2016/7/14 0014 下午 2:51
 */
@ContentView(R.layout.activity_workorder)
public class WorkOrderActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private WorkOrderAdapter workerOrderAdapter;
    private List<WorkOrder> list = new ArrayList<>();

    @Override
    protected void receiveIntentData()  {
    }

    @Override
    protected void initTopView()  {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.workorder);

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
        workerOrderAdapter = new WorkOrderAdapter(activity);
        recyclerView.setAdapter(workerOrderAdapter);


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
        workerOrderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    //点击管家提醒
                    case 0:
                        startActivity(new Intent(activity,StewardRemindActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void initData()  {
        list.add(new WorkOrder("管家提醒", R.drawable.steward_selector, "您寄来的单号为'20160528'的邮件已经签收!",
                "06-20 18:35", "管家"));
        list.add(new WorkOrder("记账-5月份", R.drawable.tax_accounting_selector, "等待邮寄记账票据",
                "今天 23:48", "记账"));
        list.add(new WorkOrder("记账", R.drawable.tax_accounting_selector, "收到记账票据,记账中",
                "昨天 18:45", "记账"));
        list.add(new WorkOrder("公司新设立-5月份", R.drawable.company_enter_selector, "核名通过,待申请执照",
                "05-03 14:12", "公司新设"));
        list.add(new WorkOrder("签约付款-4月份", R.drawable.service_package_selector, "已完成",
                "04-18 11:04", "签约"));
        list.add(new WorkOrder("记账-2月份", R.drawable.tax_accounting_selector, "等待邮寄记账票据",
                "02-16 09:34", "记账"));
        list.add(new WorkOrder("记账", R.drawable.tax_accounting_selector, "收到记账票据,记账中",
                "02-22 18:45", "记账"));
        list.add(new WorkOrder("公司新设立-2月份", R.drawable.company_enter_selector, "核名通过,待申请执照",
                "02-03 14:12", "公司新设"));
        list.add(new WorkOrder("签约付款-1月份", R.drawable.service_package_selector, "已完成",
                "01-18 11:04", "签约"));
        workerOrderAdapter.setList(list);
        workerOrderAdapter.notifyDataSetChanged();
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

