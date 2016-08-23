package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
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
import com.yingshiyuan.starpark.activity.ModifyCompanyOtherInfoActivity;
import com.yingshiyuan.starpark.data.CompanyFile;
import com.yingshiyuan.starpark.listener.OnItemClickListener;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:修改公司字号的适配器
 * @author:袁东华 created at 2016/7/27 0027 上午 11:50
 */
public class ModifyCompanyNameAdapter extends RecyclerView.Adapter<ModifyCompanyNameAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    //功能模块集合
    private ArrayList<String> mList = new ArrayList<String>();
    private Activity activity;
    private Handler handler;

    public ModifyCompanyNameAdapter(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    public void setList(ArrayList<String> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modify_company_name, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.alternativeName_et.setTag(position);
        int i = (int) holder.alternativeName_et.getTag();
        holder.alternativeName_et.setText(mList.get(i));

        holder.alternativeName_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = (int) holder.alternativeName_et.getTag();
                mList.remove(i);
                mList.add(i,s.toString());
            }
        });
        if (position == mList.size() - 1) {
            holder.submit_btn.setVisibility(View.VISIBLE);
            //点击提交
            holder.submit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler.sendEmptyMessage(2);
                }
            });
            holder.other_btn.setVisibility(View.VISIBLE);
            //点击修改其他
            holder.other_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, ModifyCompanyOtherInfoActivity.class));
                }
            });
        } else {
            holder.submit_btn.setVisibility(View.GONE);
            holder.other_btn.setVisibility(View.GONE);

        }
        if (mList.size() == 1) {
            holder.delete_btn.setVisibility(View.GONE);
        } else {
            holder.delete_btn.setVisibility(View.VISIBLE);
        }
        //点击删除
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("position", position);
                msg.setData(data);
                msg.what = 1;
                handler.sendMessage(msg);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public ArrayList<String> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EditText alternativeName_et;
        private Button submit_btn, other_btn;
        private ImageButton delete_btn;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            alternativeName_et = (EditText) itemView.findViewById(R.id.alternativeName_et);
            submit_btn = (Button) itemView.findViewById(R.id.submit_btn);
            other_btn = (Button) itemView.findViewById(R.id.other_btn);
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
