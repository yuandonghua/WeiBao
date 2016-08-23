package com.yingshiyuan.starpark.fragment;

import android.app.Activity;
import android.view.View;
import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.HoneycombAdapter;
import com.yingshiyuan.starpark.data.HoneycombContent;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.recyclerview.FlexibleDividerDecoration;
import com.yingshiyuan.starpark.view.recyclerview.GridDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 蜂巢页面
 */

public class HoneycombPage {
    private static HoneycombPage instance;
    private View view = null;
    private Activity activity = null;
//    private RecyclerView recyclerView;
//    private HoneycombAdapter honeycombAdapter;
    private List<HoneycombContent> list = new ArrayList<>();

    private HoneycombPage() {


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
//        list.add(new HoneycombContent("服务套餐", R.drawable.service_package_selector));
//        list.add(new HoneycombContent("公司新设", R.drawable.set_company_selector));
//        list.add(new HoneycombContent("公司入驻", R.drawable.company_enter_selector));
//        list.add(new HoneycombContent("工商", R.drawable.commerce_selector));
//        list.add(new HoneycombContent("记账报税", R.drawable.tax_accounting_selector));
//        list.add(new HoneycombContent("社保公积金", R.drawable.social_security_fund_selector));
//        list.add(new HoneycombContent("公司档案", R.drawable.archive_selector));
//        list.add(new HoneycombContent("政策法规", R.drawable.law_selector));
//        list.add(new HoneycombContent("工具箱", R.drawable.tool_case_selector));
//        honeycombAdapter.setList(list);
//        honeycombAdapter.notifyDataSetChanged();
    }

    private void initView() {
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
//        recyclerView.addItemDecoration(new GridDividerItemDecoration(activity));
//        honeycombAdapter = new HoneycombAdapter(activity);
//        recyclerView.setAdapter(honeycombAdapter);
//        honeycombAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(activity, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    public static HoneycombPage getInstance() {
        if (instance == null) {
            synchronized (HoneycombPage.class) {
                if (instance == null) {
                    instance = new HoneycombPage();
                }
            }
        }

        return instance;
    }
}
