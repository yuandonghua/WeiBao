package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.activity.ModifyCompanyNameActivity;
import com.yingshiyuan.starpark.activity.ModifyCompanyOtherInfoActivity;
import com.yingshiyuan.starpark.activity.SupplementaryCompanyInfoActivity;
import com.yingshiyuan.starpark.data.ShareHolder;
import com.yingshiyuan.starpark.data.StewardMessage;
import com.yingshiyuan.starpark.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:补充公司股东的适配器
 * @author:袁东华 created at 2016/7/29 0029 下午 3:07
 */
public class ShareHolderAdapter extends Adapter<ShareHolderAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<ShareHolder> mList = new ArrayList<>();
    private Activity activity;

    public ShareHolderAdapter(Activity activity) {
        this.activity = activity;

    }

    public void setList(ArrayList<ShareHolder> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public ArrayList<ShareHolder> getList() {
        return mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shareholder, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.et_1.setTag(position);
        holder.et_2.setTag(position);
        holder.et_3.setTag(position);

        int i1 = (int) holder.et_1.getTag();
        holder.et_1.setText(mList.get(i1).getName());
        int i2 = (int) holder.et_2.getTag();
        holder.et_2.setText(mList.get(i2).getIdno());
        int i3 = (int) holder.et_3.getTag();
        holder.et_3.setText(mList.get(i3).getCapital());
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
                mList.get(i).setName(s.toString());
            }
        });
        holder.et_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = (int) holder.et_2.getTag();
                mList.get(i).setIdno(s.toString());
            }
        });
        holder.et_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = (int) holder.et_3.getTag();
                mList.get(i).setCapital(s.toString());
            }
        });
        if (position == mList.size() - 1) {
            holder.add_btn.setVisibility(View.VISIBLE);
            //点击新增
            holder.add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.add(new ShareHolder());
                    notifyDataSetChanged();
                }
            });

        } else {
            holder.add_btn.setVisibility(View.GONE);

        }
        if (mList.size() == 1) {
            holder.delete_btn.setVisibility(View.GONE);
        } else {
            holder.delete_btn.setVisibility(View.VISIBLE);
        }
        holder.delete_btn.setTag(position);
//        点击删除
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int) holder.delete_btn.getTag();
                mList.remove(i);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button add_btn;
        private EditText et_1, et_2, et_3;
        private ImageButton delete_btn;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            et_1 = (EditText) itemView.findViewById(R.id.et_1);
            et_2 = (EditText) itemView.findViewById(R.id.et_2);
            et_3 = (EditText) itemView.findViewById(R.id.et_3);
            add_btn = (Button) itemView.findViewById(R.id.add_btn);
            delete_btn = (ImageButton) itemView.findViewById(R.id.delete_btn);
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
