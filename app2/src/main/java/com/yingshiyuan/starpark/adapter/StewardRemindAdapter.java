package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.activity.ModifyCompanyNameActivity;
import com.yingshiyuan.starpark.activity.SupplementaryCompanyInfoActivity;
import com.yingshiyuan.starpark.data.StewardMessage;
import com.yingshiyuan.starpark.listener.OnItemClickListener;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:管家提醒适配器
 * @author:袁东华 created at 2016/7/26 0026 下午 3:32
 */
public class StewardRemindAdapter extends Adapter<StewardRemindAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<StewardMessage> mList = new ArrayList<>();
    private Activity activity;
    private Handler handler;

    public StewardRemindAdapter(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    public void setList(List<StewardMessage> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_steward_remind, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (mList.get(position) != null) {
            holder.title_tv.setText(mList.get(position).getTitle());
            holder.time_tv.setText(mList.get(position).getTime());
            holder.detail_tv.setText(mList.get(position).getDetail());
            if ("修改字号".equals(mList.get(position).getAction())) {
                holder.operate_btn.setText("修改字号");
                holder.operate_btn.setVisibility(View.VISIBLE);
                holder.operate2_btn.setVisibility(View.GONE);
                holder.operate_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, ModifyCompanyNameActivity.class);
                        intent.putExtra("procId", mList.get(position).getProcId());
                        activity.startActivity(intent);
                    }
                });
            } else if ("分配工位地址".equals(mList.get(position).getAction())) {
                holder.operate_btn.setText("分配工位地址");
                holder.operate_btn.setVisibility(View.GONE);
                holder.operate2_btn.setVisibility(View.GONE);
                holder.operate_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, ModifyCompanyNameActivity.class);
                        intent.putExtra("procId", mList.get(position).getProcId());
                        activity.startActivity(intent);

                    }
                });
            } else if ("补充注册材料".equals(mList.get(position).getAction())) {
                holder.operate_btn.setText("补充注册材料");
                holder.operate_btn.setVisibility(View.VISIBLE);
                holder.operate2_btn.setText("任务完成");
                holder.operate2_btn.setVisibility(View.VISIBLE);
                holder.operate_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, SupplementaryCompanyInfoActivity.class);
                        intent.putExtra("linkId", mList.get(position).getLinkId());
                        intent.putExtra("procId", mList.get(position).getProcId());
                        intent.putExtra("ext", mList.get(position).getExt());
                        activity.startActivity(intent);

                    }
                });

                //点击任务完成
                holder.operate2_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Message msg=new Message();
                        Bundle data=new Bundle();
                        data.putString("procId",mList.get(position).getProcId());
                        msg.setData(data);
                        msg.what=0;
                        handler.sendMessage(msg);
                    }
                });
            } else {
                holder.operate2_btn.setVisibility(View.GONE);
                holder.operate_btn.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button operate_btn, operate2_btn;
        private TextView title_tv;
        private TextView detail_tv;
        private TextView time_tv;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            detail_tv = (TextView) itemView.findViewById(R.id.detail_tv);
            time_tv = (TextView) itemView.findViewById(R.id.time_tv);
            operate_btn = (Button) itemView.findViewById(R.id.operate_btn);
            operate2_btn = (Button) itemView.findViewById(R.id.operate2_btn);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }
    }

    /**
     * @Description:设置条目点击监听,供外部调用
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }
}
