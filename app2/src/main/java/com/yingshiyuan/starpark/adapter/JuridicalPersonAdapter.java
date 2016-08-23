package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.activity.ModifyCompanyOtherInfoActivity;
import com.yingshiyuan.starpark.data.SupplementaryCompanyInfo;
import com.yingshiyuan.starpark.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * @description:补充法人信息
 * @author:袁东华 created at 2016/7/29 0029 下午 5:47
 */
public class JuridicalPersonAdapter extends RecyclerView.Adapter<JuridicalPersonAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    //功能模块集合
    private ArrayList<SupplementaryCompanyInfo> mList = new ArrayList<SupplementaryCompanyInfo>();
    private Activity activity;
    private Handler handler;

    public JuridicalPersonAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setList(ArrayList<SupplementaryCompanyInfo> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juridical_person, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.et_1.setTag(position);
        int i = (int) holder.et_1.getTag();
        holder.et_1.setText(mList.get(i).getValue());

        holder.til_1.setTag(position);
        int j = (int) holder.til_1.getTag();
        holder.til_1.setHint(mList.get(j).getLabel());

        holder.et_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = (int) holder.et_1.getTag();
                mList.get(i).setValue(s.toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public ArrayList<SupplementaryCompanyInfo> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EditText et_1;
        private TextInputLayout til_1;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            et_1 = (EditText) itemView.findViewById(R.id.et_1);
            til_1 = (TextInputLayout) itemView.findViewById(R.id.til_1);
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
