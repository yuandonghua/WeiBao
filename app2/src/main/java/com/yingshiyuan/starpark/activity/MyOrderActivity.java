package com.yingshiyuan.starpark.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.CustomServiceAdapter;
import com.yingshiyuan.starpark.data.CustomServiceContent;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.recyclerview.FullyLinearLayoutManager;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 *@description:我的订单界面
 *@author:袁东华
 *created at 2016/7/8 0008 下午 2:52
 */
@ContentView(R.layout.activity_myorder)
public class MyOrderActivity extends BaseActivity {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private CustomServiceAdapter customServiceAdapter;
    private ArrayList<CustomServiceContent> list = new ArrayList<>();

    @Override
    protected void receiveIntentData()  {

    }

    @Override
    protected void initTopView()  {

        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.action_custom_service);

    }

    @Override
    protected void initView()  {


        recyclerView.setLayoutManager(new FullyLinearLayoutManager(activity));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(activity)
                .color(R.color.black_14)
                .size(activity.getResources().getDimensionPixelSize(
                        R.dimen.divider_1px))
                .build());
        customServiceAdapter = new CustomServiceAdapter(activity);
        recyclerView.setAdapter(customServiceAdapter);
    }

    @Override
    protected void initListener()  {
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        //点击返回
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        customServiceAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData()  {
        list.add(new CustomServiceContent("[工商]一个人可以注册多个公司吗?"));
        list.add(new CustomServiceContent("[工商]注册公司有什么要求吗?"));
        list.add(new CustomServiceContent("[业务]提供公司注册地址要收费吗?"));
        list.add(new CustomServiceContent("[业务]注册公司有那些好处?"));
        list.add(new CustomServiceContent("[财税]报销票据都有那些要求?"));
        list.add(new CustomServiceContent("[财税]普通发票与专票有什么不同?"));
        list.add(new CustomServiceContent("[工商]一个人可以注册多个公司吗?"));
        list.add(new CustomServiceContent("[工商]注册公司有什么要求吗?"));
        list.add(new CustomServiceContent("[业务]提供公司注册地址要收费吗?"));
        list.add(new CustomServiceContent("[业务]注册公司有那些好处?"));
        list.add(new CustomServiceContent("[财税]报销票据都有那些要求?"));
        list.add(new CustomServiceContent("[财税]普通发票与专票有什么不同?"));

        customServiceAdapter.setList(list);
        customServiceAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
