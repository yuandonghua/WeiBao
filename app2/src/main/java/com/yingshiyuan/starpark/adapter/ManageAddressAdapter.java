package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.activity.AddAddressActivity;
import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.http.AddressHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.listener.OnItemLongClickListener;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;


/**
 * @description:管理收货地址适配器
 * @author:袁东华
 * @time:2015-9-1上午10:28:22
 */
public class ManageAddressAdapter extends
        Adapter<ManageAddressAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private Activity activity;
    private Handler mHandler;
    /**
     * 需求列表集合
     */
    private ArrayList<Address> mAddressList;

    public ManageAddressAdapter(Activity activity, Handler mHandler) {
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.mHandler = mHandler;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return mAddressList != null ? mAddressList.size() : 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // TODO Auto-generated method stub
        setContent(viewHolder, position);
    }

    /**
     * @description:给条目设置内容
     * @parameters
     */
    private void setContent(final ViewHolder viewHolder, final int position) {
        // TODO Auto-generated method stub
        LogUtil.e("mAddressList:" + mAddressList.size());
        if (mAddressList != null && mAddressList.size() > position) {
            final Address address = mAddressList.get(position);
            if (address != null) {
                if ("true".equals(address.getDefaultValue())) {
                    viewHolder.rb.setChecked(true);
                } else {
                    viewHolder.rb.setChecked(false);
                }
                viewHolder.name_tv.setText(address.getName());
                viewHolder.phone_tv.setText(address.getPhone());
                viewHolder.address_tv.setText(address.getProvince()
                        + address.getCity() + address.getDistrict()
                        + address.getDetailAddress());

                // 点击编辑
                viewHolder.edit_iv.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(activity, AddAddressActivity.class);
                        intent.putExtra("address", address);
                        activity.startActivity(intent);
                    }
                });
                // 点击删除
                viewHolder.delete_iv.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        AddressHttp.getInstance().deleteAddress(address.getId(), mHandler,
                                HttpIdentifyingCodeUtil.DELETE_ADDRESS_S, HttpIdentifyingCodeUtil.DELETE_ADDRESS_F);
                    }
                });
                //点击设为默认地址
                viewHolder.rb.setOnClickListener(new OnClickListener() {
                    /**
                     * Called when a view has been clicked.
                     *
                     * @param v The view that was clicked.
                     */
                    @Override
                    public void onClick(View v) {
                        if (viewHolder.rb.isChecked()) {

                            Address address1 = mAddressList.get(position);
                            address1.setDefaultValue("true");
                            String userId = SharedPreferencesUtil.getInstance().getId(activity);
                            AddressHttp.getInstance().editAddress(userId, address1.getId(),
                                    address1.getName(), address1.getPhone(), address1.getProvince(),
                                    address1.getCity(), address1.getDistrict(), address1.getDetailAddress(),
                                    address1.getDefaultValue(), mHandler,
                                    HttpIdentifyingCodeUtil.ADD_ADDRESS_S,
                                    HttpIdentifyingCodeUtil.ADD_ADDRESS_F);
                        } else {
                            Address address1 = mAddressList.get(position);
                            address1.setDefaultValue("false");
                            String userId = SharedPreferencesUtil.getInstance().getId(activity);
                            AddressHttp.getInstance().editAddress(userId, address1.getId(),
                                    address1.getName(), address1.getPhone(), address1.getProvince(),
                                    address1.getCity(), address1.getDistrict(), address1.getDetailAddress(),
                                    address1.getDefaultValue(), mHandler,
                                    HttpIdentifyingCodeUtil.ADD_ADDRESS_S,
                                    HttpIdentifyingCodeUtil.ADD_ADDRESS_F);
                        }

                    }
                });
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int poition) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.manageaddress_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, mOnItemClickListener,
                mOnItemLongClickListener);
        return vh;
    }

    public void setData(ArrayList<Address> AddressList) {
        this.mAddressList = AddressList;
        LogUtil.e("mAddressList:" + mAddressList.size());
    }

    /**
     * @Description:设置条目点击监听,供外部调用
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }

    /**
     * @Description:设置长按点击监听,供外部调用
     */
    public void setOnItemLongClickListener(
            OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            OnClickListener, OnLongClickListener {
        private OnItemClickListener mOnItemClickListener;
        private OnItemLongClickListener mOnItemLongClickListener;
        private CheckBox rb;
        private TextView name_tv, phone_tv, address_tv;
        private ImageView edit_iv, delete_iv;

        public ViewHolder(View v, OnItemClickListener itemClick,
                          OnItemLongClickListener itemLongClick) {
            super(v);
            // TODO Auto-generated constructor stub
            mOnItemClickListener = itemClick;
            mOnItemLongClickListener = itemLongClick;

            rb = (CheckBox) v.findViewById(R.id.rb);
            name_tv = (TextView) v.findViewById(R.id.name_tv);
            phone_tv = (TextView) v.findViewById(R.id.phone_tv);
            address_tv = (TextView) v.findViewById(R.id.address_tv);

            edit_iv = (ImageView) v.findViewById(R.id.edit_iv);
            delete_iv = (ImageView) v.findViewById(R.id.delete_iv);

            v.setOnClickListener(this);
            v.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View v) {
            // TODO Auto-generated method stub
            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener.onItemLongClick(v, getPosition());

            }
            return true;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getPosition());

            }
        }

    }
}
