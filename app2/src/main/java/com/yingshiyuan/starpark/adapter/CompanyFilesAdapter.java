package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.data.CompanyFile;
import com.yingshiyuan.starpark.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 *@description:公司档案的适配器
 *@author:袁东华
 *created at 2016/7/15 0015 上午 11:52
 */
public class CompanyFilesAdapter extends RecyclerView.Adapter<CompanyFilesAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    //功能模块集合
    private List<CompanyFile> mList = new ArrayList<CompanyFile>();
    private Activity activity;

    public CompanyFilesAdapter(Activity activity) {
        this.activity = activity;

    }

    public void setList(List<CompanyFile> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company_files, parent, false);
        viewHolder = new ViewHolder(view,mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
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
