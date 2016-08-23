package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.data.HoneycombContent;
import com.yingshiyuan.starpark.data.WorkerOrder;
import com.yingshiyuan.starpark.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 工单界面的适配器
 */

public class WorkerOrderAdapter extends Adapter<WorkerOrderAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    //功能模块集合
    private List<WorkerOrder> mList = new ArrayList<>();
    private Activity activity;

    public WorkerOrderAdapter(Activity activity) {
        this.activity = activity;

    }

    public void setList(List<WorkerOrder> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workerorder_item, parent, false);
        viewHolder = new ViewHolder(view,mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(mList.get(position).getImage());
        holder.title_tv.setText(mList.get(position).getTitle());
        holder.time_tv.setText(mList.get(position).getTime());
        holder.message_tv.setText(mList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView title_tv;
        private TextView time_tv;
        private TextView message_tv;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            time_tv = (TextView) itemView.findViewById(R.id.time_tv);
            message_tv = (TextView) itemView.findViewById(R.id.message_tv);
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
