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
import com.yingshiyuan.starpark.adapter.StewardRemindAdapter;
import com.yingshiyuan.starpark.data.StewardMessage;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.http.StewardMessageHttp;
import com.yingshiyuan.starpark.utils.ManageUserDataUtil;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:管家提醒界面
 * @author:袁东华 created at 2016/7/26 0026 下午 3:29
 */
@ContentView(R.layout.activity_steward_remind)
public class StewardRemindActivity extends BaseActivity {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private StewardRemindAdapter stewardRemindAdapter;
    private List<StewardMessage> list = new ArrayList<>();

    @Override
    protected void receiveIntentData() {
    }

    @Override
    protected void initTopView() {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.steward_remind);

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
        stewardRemindAdapter = new StewardRemindAdapter(activity, handler);
        recyclerView.setAdapter(stewardRemindAdapter);


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

    }

    @Override
    protected void initData() {
        StewardMessageHttp.getInstance().getMessageList(ManageUserDataUtil.getInstance().getUserId(activity),
                handler, HttpIdentifyingCodeUtil.GET_MESSAGE_LIST_S, HttpIdentifyingCodeUtil.GET_MESSAGE_LIST_F);
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
                //完成任务成功
                case HttpIdentifyingCodeUtil.FINISH_TASK_S:
                    Bundle data2 = msg.getData();
                    if (data2 != null) {
                        Toast.makeText(activity, "任务已完成", Toast.LENGTH_SHORT).show();
                        initData();
                    }

                    break;
                //完成任务失败
                case HttpIdentifyingCodeUtil.FINISH_TASK_F:
                    Bundle errorData2 = msg.getData();
                    if (errorData2 != null) {
                        String message = errorData2.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 0:
                    Bundle data1 = msg.getData();
                    if (data1 != null) {
                        String procId = data1.getString("procId");
                        StewardMessageHttp.getInstance().finishTask(handler,
                                ManageUserDataUtil.getInstance().getUserName(activity), procId,
                                HttpIdentifyingCodeUtil.FINISH_TASK_S, HttpIdentifyingCodeUtil.FINISH_TASK_F);
                    }
                    break;
                //获取管家消息列表成功
                case HttpIdentifyingCodeUtil.GET_MESSAGE_LIST_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        list = data.getParcelableArrayList("list");
                        LogUtil.e("mList="+list.size());
                        stewardRemindAdapter.setList(list);
                    }

                    break;
                //获取管家消息列表失败
                case HttpIdentifyingCodeUtil.GET_MESSAGE_LIST_F:
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

