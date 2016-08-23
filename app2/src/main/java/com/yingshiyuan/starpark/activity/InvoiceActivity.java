package com.yingshiyuan.starpark.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.InvoicePagerAdapter;
import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.view.tab.TabLayout;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * @description:开票界面
 * @author:袁东华 created at 2016/7/13 0013 下午 1:26
 */
@ContentView(R.layout.activity_receipt)
public class InvoiceActivity extends BaseActivity {

    @ViewInject(R.id.container)
    private ViewPager container;
    @ViewInject(R.id.tabs)
    private TabLayout tabs;


    //tab的图标
    private int[] tabImage = new int[]{
            R.drawable.invoice_selector,
            R.drawable.special_invoice_selector};

    private InvoicePagerAdapter mInvoicePagerAdapter;
    /**
     * 省份输入框
     */
    @ViewInject(R.id.province_et)
    private EditText province_et;
    /**
     * 城市输入框
     */
    @ViewInject(R.id.city_et)
    private EditText city_et;
    /**
     * 县(区)输入框
     */
    @ViewInject(R.id.district_et)
    private EditText district_et;
    /**
     * 详细地址输入框
     */
    @ViewInject(R.id.detailAddress_et)
    private EditText detailAddress_et;
    /**
     * 默认地址开关
     */
    @ViewInject(R.id.default_switch)
    private Switch default_switch;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = InvoiceActivity.this;
    private TextView findPassword_tv;
    private ImageView back_iv;
    /**
     * 标题
     */
    @ViewInject(R.id.title_tv)
    private TextView title_tv;
    private TextView right_tv;
    private Address address;

    @Override
    protected void receiveIntentData()  {
    }

    @Override
    protected void initTopView()  {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.receipt);

    }

    @Override
    protected void initView()  {
        mInvoicePagerAdapter = new InvoicePagerAdapter(getSupportFragmentManager(), this);
        container.setAdapter(mInvoicePagerAdapter);
//        tabs.setImages(tabImage);
        //是否约束TabLayout最低高度
        tabs.restrainHeight(true);
        //设置TabView水平展示内容
        tabs.setOrientation(LinearLayout.HORIZONTAL);
        tabs.setupWithViewPager(container);
        try {
            //给tab设置自定义View
            for (int i = 0; i < tabs.getTabCount(); i++) {
                TabLayout.Tab tab = tabs.getTabAt(i);
                tab.setIcon(tabImage[i]);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void initListener()  {
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
    protected void initData()  {

//        if (address == null) {
//            title_tv.setText(R.string.add_address);
//        } else {
//            title_tv.setText(R.string.edit_address);
//            province_et.setText(address.getProvince());
//            city_et.setText(address.getCity());
//            district_et.setText(address.getDistrict());
//            detailAddress_et.setText(address.getDetailAddress());
//            if ("false".equals(address.getDefaultValue())) {
//                default_switch.setChecked(false);
//            } else {
//                default_switch.setChecked(true);
//            }
//        }
    }

    /**
     * @description:提交收货地址
     * @author:袁东华 created at 2016/6/30 0030下午 2:21
     */
    private void attemptSubmit() {

        // Reset errors.
        province_et.setError(null);
        city_et.setError(null);
        district_et.setError(null);
        detailAddress_et.setError(null);

        String province = province_et.getText().toString();
        String city = city_et.getText().toString();
        String district = district_et.getText().toString();
        String detailAddress = detailAddress_et.getText().toString();
        boolean checked = default_switch.isChecked();
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(province)) {
            province_et.setError(getString(R.string.error_field_required));
            focusView = province_et;
            cancel = true;
        } else if (TextUtils.isEmpty(city)) {
            city_et.setError(getString(R.string.error_field_required));
            focusView = city_et;
            cancel = true;
        } else if (TextUtils.isEmpty(district)) {
            district_et.setError(getString(R.string.error_field_required));
            focusView = district_et;
            cancel = true;
        } else if (TextUtils.isEmpty(detailAddress)) {
            detailAddress_et.setError(getString(R.string.error_field_required));
            focusView = detailAddress_et;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
        }
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
                //新增地址成功
                case HttpIdentifyingCodeUtil.ADD_ADDRESS_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        Toast.makeText(activity, R.string.operate_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                //新增地址失败
                case HttpIdentifyingCodeUtil.ADD_ADDRESS_F:
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

