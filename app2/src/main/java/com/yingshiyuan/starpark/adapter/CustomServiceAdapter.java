package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.data.CustomServiceContent;
import com.yingshiyuan.starpark.listener.OnItemClickListener;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

/**
 * @description:客服界面的内容的适配器
 * @author:袁东华 created at 2016/7/8 0008 下午 1:43
 */
public class CustomServiceAdapter extends Adapter<CustomServiceAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<CustomServiceContent> mList ;
    private Activity activity;

    public CustomServiceAdapter(Activity activity) {
        this.activity = activity;

    }

    public void setList(ArrayList<CustomServiceContent> list) {
        this.mList = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customservice, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogUtil.e("mList:"+mList.size());
        holder.textView.setText(mList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mList != null ?mList.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.textView);

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
