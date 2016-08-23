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

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:组织结构界面
 * @author:袁东华 created at 2016/8/1 0001 下午 1:38
 */
public class OrganizationStructureAdapter extends RecyclerView.Adapter<OrganizationStructureAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<CompanyFile> mList = new ArrayList<CompanyFile>();
    private Activity activity;

    public OrganizationStructureAdapter(Activity activity) {
        this.activity = activity;

    }

    public void setList(List<CompanyFile> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organization_structure, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogUtil.e("position:" + position);
//        holder.shareholding_tv.setText(mList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name_tv, shareholding_tv;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            name_tv = (TextView) itemView.findViewById(R.id.name_tv);
            shareholding_tv = (TextView) itemView.findViewById(R.id.shareholding_tv);
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
