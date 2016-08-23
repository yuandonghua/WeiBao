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
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置用户昵称和密码
 */
public class SetNameWithPasswordActivity extends BaseActivity {

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
    private EditText name_et;
    /**
     * 密码输入框
     */
    private EditText password_et;
    /**
     * 确认密码输入框
     */
    private EditText confirmPassword_et;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = SetNameWithPasswordActivity.this;
    private TextView findPassword_tv;
    private ImageView back_iv;
    private TextView title_tv;
    private TextView right_tv;



    @Override
    protected void receiveIntentData() throws Exception {

    }

    @Override
    protected void initTopView() throws Exception {
        setContentView(R.layout.activity_set_name_with_password);

        title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.set_name_password);

    }

    @Override
    protected void initView() throws Exception {

        name_et = (EditText) findViewById(R.id.name_et);
        password_et = (EditText) findViewById(R.id.password_et);
        confirmPassword_et = (EditText) findViewById(R.id.confirmPassword_et);




        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void initListener() throws Exception {
        Button next_btn = (Button) findViewById(R.id.next_btn);
        //点击下一步
        next_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        back_iv = (ImageView) findViewById(R.id.back_iv);
        back_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        confirmPassword_et.setError(null);

        //获取用户名,密码,确认密码
        String name = name_et.getText().toString();
        String password = password_et.getText().toString();
        String confirmPassword = confirmPassword_et.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //检验密码是否为空及是否有效
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            password_et.setError(getString(R.string.error_invalid_password));
            focusView = password_et;
            cancel = true;
        }

        // 检查用户名是否为空
        if (TextUtils.isEmpty(name)) {
            name_et.setError(getString(R.string.error_field_required));
            focusView = name_et;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            // 检查密码是否为空
            password_et.setError(getString(R.string.error_field_required));
            focusView = password_et;
            cancel = true;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            // 检查确认密码是否为空
            confirmPassword_et.setError(getString(R.string.error_field_required));
            focusView = confirmPassword_et;
            cancel = true;
        } else if (!password.equals(confirmPassword)) {
            confirmPassword_et.setError(getString(R.string.error_confirmpassword));
            focusView = confirmPassword_et;
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

            //跳转
            startActivity(new Intent(activity, SetEmailActivity.class));
            finish();
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


}

