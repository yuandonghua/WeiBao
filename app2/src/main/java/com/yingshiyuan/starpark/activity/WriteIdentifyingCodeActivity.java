package com.yingshiyuan.starpark.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.http.AccountHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;

/**
 * 注册-验证码界面
 */
public class WriteIdentifyingCodeActivity extends BaseActivity {


    // UI references.
    private EditText identifyingCode_tv;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = WriteIdentifyingCodeActivity.this;
    private TextView findPassword_tv;
    private TextView title_tv;
    private TextView right_tv;
    private String identifyingCode = "8888";
    private String flag;
    private String phone;

    @Override
    protected void receiveIntentData()  {
        flag = getIntent().getStringExtra(F);
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void initTopView()  {
        setContentView(R.layout.activity_identifyingcode);

        title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.write_identifyingcode);
    }

    @Override
    protected void initView()  {
        identifyingCode_tv = (EditText) findViewById(R.id.identifyingCode_tv);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
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
        Button next_btn = (Button) findViewById(R.id.next_btn);
        if (ContactInfoActivity.MODIFY_PHONT.equals(flag)) {
            next_btn.setText(R.string.action_done);
        }

        //点击下一步
        next_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @Override
    protected void initData()  {

    }

    /**
     * @description:验证验证码,把数据传递到下一层
     * @author:袁东华 created at 2016/7/21 0021 下午 4:18
     */
    private void attemptLogin() {
        // Reset errors.
        identifyingCode_tv.setError(null);

        String code = identifyingCode_tv.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(code)) {
            identifyingCode_tv.setError(getString(R.string.error_field_required));
            focusView = identifyingCode_tv;
            cancel = true;
        } else if (!identifyingCode.equals(code)) {
            identifyingCode_tv.setError(getString(R.string.error_invalid));
            focusView = identifyingCode_tv;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //跳转
            if ("修改密码".equals(flag)) {
                startActivity(new Intent(activity, SetPasswordActivity.class));
                finish();
            } else if (ContactInfoActivity.MODIFY_PHONT.equals(flag)) {
                //修改手机号流程
                String userId = SharedPreferencesUtil.getInstance().getId(activity);
                AccountHttp.getInstance().modifyPhone(userId, phone, handler,
                        HttpIdentifyingCodeUtil.MODIFY_PHONE_S, HttpIdentifyingCodeUtil.MODIFY_PHONE_F);

            } else {
                //注册流程
                Intent intent=new Intent(activity, SetNameWithPasswordActivity.class);
                intent.putExtra("phone",phone);
                intent.putExtra(F,flag);
                startActivity(intent);
                finish();
            }
        }
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.startsWith(identifyingCode);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private Handler handler = new Handler() {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //手机号修改成功
                case HttpIdentifyingCodeUtil.MODIFY_PHONE_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        SharedPreferencesUtil.getInstance().setPhone(activity, phone);
                        Toast.makeText(activity, R.string.modify_phone_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                //手机号修改失败
                case HttpIdentifyingCodeUtil.MODIFY_PHONE_F:
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

