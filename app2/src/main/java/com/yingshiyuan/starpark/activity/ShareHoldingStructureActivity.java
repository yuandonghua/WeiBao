package com.yingshiyuan.starpark.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.CompanyFilesAdapter;
import com.yingshiyuan.starpark.adapter.ShareholdingStructureAdapter;
import com.yingshiyuan.starpark.data.CompanyFile;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:股权结构界面
 * @author:袁东华 created at 2016/8/1 0001 上午 11:25
 */
@ContentView(R.layout.activity_shareholding_structure)
public class ShareHoldingStructureActivity extends BaseActivity {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private ShareholdingStructureAdapter shareholdingStructureAdapter;
    private List<CompanyFile> mList = new ArrayList<CompanyFile>();
    @Override
    protected void initTopView() {
        TextView title_tv= (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.title_shareholding_structure);
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
//                activity)
//                .color(getResources().getColor(R.color.black_14))
//                .size(activity.getResources().getDimensionPixelSize(
//                        R.dimen.divider_2dp))
//                .build());
        shareholdingStructureAdapter = new ShareholdingStructureAdapter(activity);
        shareholdingStructureAdapter.setList(null);
        recyclerView.setAdapter(shareholdingStructureAdapter);
    }

    @Override
    protected void initListener() {
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
