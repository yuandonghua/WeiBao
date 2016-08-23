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
import com.yingshiyuan.starpark.adapter.ModifyCompanyNameAdapter;
import com.yingshiyuan.starpark.http.CompanyHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.utils.ManageUserDataUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * @description:修改公司字号
 * @author:袁东华 created at 2016/7/27 0027 上午 11:45
 */
@ContentView(R.layout.activity_modify_company_name)
public class ModifyCompanyNameActivity extends BaseActivity {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private ModifyCompanyNameAdapter modifyCompanyNameAdapter;
    private ArrayList<String> list = new ArrayList<>();
    @ViewInject(R.id.right_tv)
    private TextView right_tv;
    private String procId = "";

    @Override
    protected void receiveIntentData() {
        procId = getIntent().getStringExtra("procId");
    }

    @Override
    protected void initTopView() {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.modify_company_name);
        right_tv.setText(R.string.add);
        right_tv.setVisibility(View.VISIBLE);
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
        modifyCompanyNameAdapter = new ModifyCompanyNameAdapter(activity, handler);
        recyclerView.setAdapter(modifyCompanyNameAdapter);
    }

    @Override
    protected void initListener() {
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        //点击返回
        back_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        right_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                list = modifyCompanyNameAdapter.getList();
                list.add("");
                modifyCompanyNameAdapter.setList(list);
                modifyCompanyNameAdapter.notifyDataSetChanged();
//                modifyCompanyNameAdapter.notifyItemInserted(list.size()-1);
//                modifyCompanyNameAdapter.notifyItemChanged(list.size()-2);
            }
        });
    }

    @Override
    protected void initData() {
        list.add("");
        list.add("");
        list.add("");

        modifyCompanyNameAdapter.setList(list);
        modifyCompanyNameAdapter.notifyDataSetChanged();
    }


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    String alternativeName = "";
                    String name = "";
                    ArrayList<String> list2 = new ArrayList<>();

                    for (int i = 1; i < list.size(); i++) {
                        if (!"".equals(list.get(i))) {

                            alternativeName += list.get(i)+ ",";
                        }
                    }

                    alternativeName = alternativeName.substring(0, alternativeName.length() - 1);
                    CompanyHttp.getInstance().createCompany(handler,
                            ManageUserDataUtil.getInstance().getUserId(activity), procId,
                            "", "", null, null, "", "",
                            "", alternativeName,
                            HttpIdentifyingCodeUtil.CREATE_COMPANY_S, HttpIdentifyingCodeUtil.CREATE_COMPANY_F);
                    break;
                case 1:
                    Bundle data1 = msg.getData();
                    if (data1 != null) {
                        int position = data1.getInt("position");
//                        modifyCompanyNameAdapter.notifyItemRemoved(position);
                        list.remove(position);
                        modifyCompanyNameAdapter.setList(list);
                        modifyCompanyNameAdapter.notifyDataSetChanged();
                    }

                    break;
                //公司设立成功
                case HttpIdentifyingCodeUtil.CREATE_COMPANY_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        Toast.makeText(activity, R.string.operate_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                //公司设立失败
                case HttpIdentifyingCodeUtil.CREATE_COMPANY_F:
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

