package com.yingshiyuan.starpark.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
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

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil.*;

/**
 * 登陆界面
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

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

    /**
     * 用户名输入框
     */
    @ViewInject(R.id.name_et)
    private EditText name_et;
    /**
     * 密码输入框
     */
    @ViewInject(R.id.password_et)
    private EditText password_et;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = LoginActivity.this;
    private TextView findPassword_tv;
    private ImageView back_iv;
    private TextView title_tv;
    private TextView right_tv;


    @Override
    protected void receiveIntentData() throws Exception {

    }

    @Override
    protected void initTopView() throws Exception {
        title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.action_login);
        right_tv = (TextView) findViewById(R.id.right_tv);
        right_tv.setText(R.string.regist);
        right_tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() throws Exception {


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void initListener() throws Exception {
        back_iv = (ImageView) findViewById(R.id.back_iv);
        back_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击注册
        right_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WritePhoneActivity.class);
                startActivity(intent);
                finish();

            }
        });
        findPassword_tv = (TextView) findViewById(R.id.findPassword_tv);
        findPassword_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "点击了忘记密码", Toast.LENGTH_SHORT).show();
            }
        });
        password_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mPhoneSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mPhoneSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @Override
    protected void initData() throws Exception {

    }


    /**
     * 登录
     */
    private void attemptLogin() {

        // Reset errors.
        name_et.setError(null);
        password_et.setError(null);
        //获取手机号和密码
        String name = name_et.getText().toString();
        String password = password_et.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // 检查帐号是否为空
        if (TextUtils.isEmpty(name)) {
            name_et.setError(getString(R.string.error_field_required));
            focusView = name_et;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            password_et.setError(getString(R.string.error_field_required));
            focusView = password_et;
            cancel = true;
        }
        if (!isPasswordValid(password)) {
            password_et.setError(getString(R.string.error_invalid_password));
            focusView = password_et;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //保存用户名和密码
            SharedPreferencesUtil.getInstance().setName(activity,name);
            SharedPreferencesUtil.getInstance().setPassword(activity,password);

            String md5String = MD5Util.getInstance().getMD5String(password);

            AccountHttp.getInstance().login(name, md5String, handler,
                    LOGIN_S, LOGIN_E);
        }
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.startsWith("1");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 4;
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
                //登陆成功
                case HttpIdentifyingCodeUtil.LOGIN_S:
                    Bundle data = msg.getData();
                    if (data!=null){
                        String email = data.getString("email");
                        String mobile = data.getString("mobile");
                        String id = data.getString("id");
                        SharedPreferencesUtil.getInstance().setEmail(activity,email);
                        SharedPreferencesUtil.getInstance().setPhone(activity,mobile);
                        SharedPreferencesUtil.getInstance().setId(activity,id);
                        Toast.makeText(activity, R.string.login_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                //登陆失败
                case HttpIdentifyingCodeUtil.LOGIN_E:
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

