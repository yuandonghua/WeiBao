package com.yingshiyuan.starpark.fragment;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.HoneycombAdapter;
import com.yingshiyuan.starpark.adapter.WorkerOrderAdapter;
import com.yingshiyuan.starpark.data.HoneycombContent;
import com.yingshiyuan.starpark.data.WorkerOrder;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.recyclerview.GridDividerItemDecoration;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 工单页面
 */

public class WorkerOrderPage {
    private static WorkerOrderPage instance;
    private View view = null;
    private Activity activity = null;
    private RecyclerView recyclerView;
    private WorkerOrderAdapter workerOrderAdapter;
    private List<WorkerOrder> list = new ArrayList<>();

    private WorkerOrderPage() {


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

    public View getView() {
        return view;
    }

    private void initData() {
        list.add(new WorkerOrder("管家提醒", R.drawable.steward_selector, "您寄来的单号为'20160528'的邮件已经签收!",
                "06-20 18:35", "管家"));
        list.add(new WorkerOrder("记账-5月份", R.drawable.tax_accounting_selector, "等待邮寄记账票据",
                "今天 23:48", "记账"));
        list.add(new WorkerOrder("记账", R.drawable.tax_accounting_selector, "收到记账票据,记账中",
                "昨天 18:45", "记账"));
        list.add(new WorkerOrder("公司新设立-5月份", R.drawable.company_enter_selector, "核名通过,待申请执照",
                "05-03 14:12", "公司新设"));
        list.add(new WorkerOrder("签约付款-4月份", R.drawable.service_package_selector, "已完成",
                "04-18 11:04", "签约"));
        list.add(new WorkerOrder("记账-2月份", R.drawable.tax_accounting_selector, "等待邮寄记账票据",
                "02-16 09:34", "记账"));
        list.add(new WorkerOrder("记账", R.drawable.tax_accounting_selector, "收到记账票据,记账中",
                "02-22 18:45", "记账"));
        list.add(new WorkerOrder("公司新设立-2月份", R.drawable.company_enter_selector, "核名通过,待申请执照",
                "02-03 14:12", "公司新设"));
        list.add(new WorkerOrder("签约付款-1月份", R.drawable.service_package_selector, "已完成",
                "01-18 11:04", "签约"));
        workerOrderAdapter.setList(list);
        workerOrderAdapter.notifyDataSetChanged();
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
                activity)
                .color(R.color.black_14)
                .size(activity.getResources().getDimensionPixelSize(
                        R.dimen.divider_1px))
                .build());
        workerOrderAdapter = new WorkerOrderAdapter(activity);
        recyclerView.setAdapter(workerOrderAdapter);
        workerOrderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static WorkerOrderPage getInstance() {
        if (instance == null) {
            synchronized (WorkerOrderPage.class) {
                if (instance == null) {
                    instance = new WorkerOrderPage();
                }
            }
        }

        return instance;
    }
}
