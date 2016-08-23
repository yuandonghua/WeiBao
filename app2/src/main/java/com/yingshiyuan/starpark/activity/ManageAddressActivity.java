package com.yingshiyuan.starpark.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.ManageAddressAdapter;
import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.http.AddressHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


/**
 * @description: 管理收货地址
 * @author:袁东华
 * @time:2015-9-1上午10:26:01
 */
@ContentView(R.layout.activity_manage_address)
public class ManageAddressActivity extends BaseActivity {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private ManageAddressAdapter mManageAddressAdapter;
    /**
     * 标题
     */
    @ViewInject(R.id.title_tv)
    private TextView title_tv;
    /**
     * 新增按钮
     */
    @ViewInject(R.id.right_tv)
    private TextView right_tv;

    /**
     * 提示
     */
    @ViewInject(R.id.hint_tv)
    private TextView hint_tv;
    private ArrayList<Address> list = new ArrayList<>();


    @Override
    protected void receiveIntentData()  {

    }

    @Override
    protected void initTopView()  {
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setText(R.string.add);
        title_tv.setText(R.string.manage_address);
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mManageAddressAdapter = new ManageAddressAdapter(activity, handler);
        recyclerView.setAdapter(mManageAddressAdapter);
        //添加分割线
        recyclerView
                .addItemDecoration(new HorizontalDividerItemDecoration.Builder(
                        this)
                        .color(getResources().getColor(R.color.black_14))
                        .size(getResources().getDimensionPixelSize(
                                R.dimen.divider_2dp)).build());

    }

    @Override
    protected void initListener()  {
        //点击新增按钮
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, AddAddressActivity.class));
            }
        });
    }

    @Override
    protected void initData()  {
        // TODO Auto-generated method stub
        AddressHttp.getInstance().getAddressList(SharedPreferencesUtil.getInstance().getId(activity),
                handler, HttpIdentifyingCodeUtil.GET_ADDRESS_LIST_S,HttpIdentifyingCodeUtil.GET_ADDRESS_LIST_F);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Event({R.id.back_iv})
    private void onClick(View v) {
        switch (v.getId()) {
            // 点击返回
            case R.id.back_iv:
                finish();
                break;
            // 点击添加
            case R.id.right_tv:

                break;
            default:
                break;
        }
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
                //删除用户地址成功
                case HttpIdentifyingCodeUtil.DELETE_ADDRESS_S:
                    Bundle data3 = msg.getData();
                    if (data3 != null) {
                        Toast.makeText(activity, R.string.operate_success, Toast.LENGTH_SHORT).show();
                        try {
                            initData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                //删除用户地址失败
                case HttpIdentifyingCodeUtil.DELETE_ADDRESS_F:
                    Bundle data4 = msg.getData();
                    if (data4 != null) {
                        String message = data4.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
                //设为默认地址成功
                case HttpIdentifyingCodeUtil.ADD_ADDRESS_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        Toast.makeText(activity, R.string.operate_success, Toast.LENGTH_SHORT).show();
                        try {
                            initData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                //设为默认地址失败
                case HttpIdentifyingCodeUtil.ADD_ADDRESS_F:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    mManageAddressAdapter.notifyDataSetChanged();
                    break;
                //获取用户地址列表成功
                case HttpIdentifyingCodeUtil.GET_ADDRESS_LIST_S:
                    Bundle data1 = msg.getData();
                    if (data1!=null){
                        list = data1.getParcelableArrayList("list");
                        mManageAddressAdapter.setData(list);
                        mManageAddressAdapter.notifyDataSetChanged();
                    }

                    break;
                //获取用户地址列表失败
                case HttpIdentifyingCodeUtil.GET_ADDRESS_LIST_F:
                    Bundle data2 = msg.getData();
                    if (data2 != null) {
                        String message = data2.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

}
