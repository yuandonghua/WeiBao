package com.yingshiyuan.starpark.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.CreateCompanyProcessAdapter;
import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.http.AddressHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * @description:公司设立流程
 * @author:袁东华 created at 2016/7/22 0022 上午 10:10
 */
@ContentView(R.layout.activity_create_company_process)
public class CreateCompanyProcessActivity extends BaseActivity {

    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    //标题
    @ViewInject(R.id.title_tv)
    private TextView title_tv;
    //客服
    @ViewInject(R.id.right_iv)
    private ImageView right_iv;
    private CreateCompanyProcessAdapter createCompanyProcessAdapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void receiveIntentData()  {
    }

    @Override
    protected void initTopView()  {
        title_tv.setText(R.string.create_company_process);
        right_iv.setImageResource(R.mipmap.ic_customservice_white);
        right_iv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView()  {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));

        createCompanyProcessAdapter = new CreateCompanyProcessAdapter(activity);
        //添加header
        View view = LayoutInflater.from(activity).inflate(R.layout.header_create_company_process, recyclerView, false);
        createCompanyProcessAdapter.setHeaderView(view);
        recyclerView.setAdapter(createCompanyProcessAdapter);
    }

    @Override
    protected void initListener()  {
        //点击客服
        right_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CustomServiceActivity.class);
                startActivity(intent);
            }
        });
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
        list.add("");
        list.add("核名\n核定你注册公司名称");
        list.add("申请登记\n申请登记,取得营业执照");
        list.add("刻章\n制作并备案公章,财务专用章等");
        list.add("税务报到\n到税务部门填报信息,取得纳税授权一证通");
        list.add("银行开设基本户\n到银行开设公司基本户,取得开户许可证");
        list.add("社保开户\n到社保主管部门确定社保登记证");
        createCompanyProcessAdapter.setList(list);

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

