package com.yingshiyuan.starpark.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.http.AccountHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.utils.MD5Util;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;

import org.xutils.common.util.LogUtil;
import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册-填写手机号界面
 */
@ContentView(R.layout.activity_set_email)
public class SetEmailActivity extends BaseActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "18801123830:123456", "bar@example.com:world"
    };


    // UI references.
    private EditText email_et;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = SetEmailActivity.this;
    private TextView findPassword_tv;
    private ImageView back_iv;
    private TextView title_tv;
    private TextView right_tv;
    private String flag;
    private String email;
    @Override
    protected void receiveIntentData() throws Exception {
        flag = getIntent().getStringExtra(F);
    }

    @Override
    protected void initTopView() throws Exception {

        back_iv = (ImageView) findViewById(R.id.back_iv);
        back_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.write_email);
    }

    @Override
    protected void initView() throws Exception {
        email_et = (EditText) findViewById(R.id.email_et);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void initListener() throws Exception {
        Button done_btn = (Button) findViewById(R.id.done_btn);
        //点击完成
        done_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    @Override
    protected void initData() throws Exception {

    }


    private void attemptLogin() {

        // Reset errors.
        email_et.setError(null);
         email = email_et.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(email)) {
            email_et.setError(getString(R.string.error_field_required));
            focusView = email_et;
            cancel = true;
        } else if (!email.contains("@")) {
            email_et.setError(getString(R.string.email_invalid));
            focusView = email_et;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            if (ContactInfoActivity.MODIFY_EMAIL .equals(flag) ) {
                String userId = SharedPreferencesUtil.getInstance().getId(activity);
                //修改邮箱
                AccountHttp.getInstance().modifyEmail(userId, email, handler,
                        HttpIdentifyingCodeUtil.MODIFY_EMAIL_S, HttpIdentifyingCodeUtil.MODIFY_EMAIL_F);
            } else {
                SharedPreferencesUtil.getInstance().setEmail(activity, email);
                String name = SharedPreferencesUtil.getInstance().getName(activity);
                String pwd = SharedPreferencesUtil.getInstance().getPassword(activity);
                String phone = SharedPreferencesUtil.getInstance().getPhone(activity);

                String md5String = MD5Util.getInstance().getMD5String(pwd);
                //注册账号
                AccountHttp.getInstance().regist(name, md5String, phone, email, handler,
                        HttpIdentifyingCodeUtil.REGIST_S, HttpIdentifyingCodeUtil.REGIST_E);
            }
        }
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
                //注册成功
                case HttpIdentifyingCodeUtil.REGIST_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        String id = data.getString("id");
                        SharedPreferencesUtil.getInstance().setId(activity, id);
                        Toast.makeText(activity, R.string.regist_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                //注册失败
                case HttpIdentifyingCodeUtil.REGIST_E:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
                //修改邮箱成功
                case HttpIdentifyingCodeUtil.MODIFY_EMAIL_S:
                    Bundle data1 = msg.getData();
                    if (data1 != null) {
                        SharedPreferencesUtil.getInstance().setEmail(activity,email);
                        Toast.makeText(activity, R.string.modify_email_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                //修改邮箱失败
                case HttpIdentifyingCodeUtil.MODIFY_EMAIL_F:
                    Bundle data2 = msg.getData();
                    if (data2 != null) {
                        String message = data2.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();



                    }

                    break;

            }
        }
    };
}

