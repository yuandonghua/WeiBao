package com.yingshiyuan.starpark.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
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

/**
 * 设置密码
 */
@ContentView(R.layout.activity_set_password)
public class SetPasswordActivity extends BaseActivity {

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
     * 密码输入框
     */
    private EditText password_et;
    /**
     * 确认密码输入框
     */
    private EditText confirmPassword_et;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = SetPasswordActivity.this;
    private TextView findPassword_tv;
    private ImageView back_iv;
    private TextView title_tv;
    private TextView right_tv;

private String password;
    @Override
    protected void receiveIntentData() throws Exception {

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
        title_tv.setText(R.string.set_password);
    }

    @Override
    protected void initView() throws Exception {
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
    }

    @Override
    protected void initData() throws Exception {

    }


    /**
     * 登录
     */
    private void attemptLogin() {

        password_et.setError(null);
        confirmPassword_et.setError(null);

        //获取用户名,密码,确认密码
        password = password_et.getText().toString();
        String confirmPassword = confirmPassword_et.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //检验密码是否为空及是否有效
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            password_et.setError(getString(R.string.error_invalid_password));
            focusView = password_et;
            cancel = true;
        }

       if (TextUtils.isEmpty(password)) {
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
            String userId=SharedPreferencesUtil.getInstance().getId(activity);
            String pwd=SharedPreferencesUtil.getInstance().getPassword(activity);
            String oldPwd = MD5Util.getInstance().getMD5String(pwd);
            String newPwd = MD5Util.getInstance().getMD5String(password);
            AccountHttp.getInstance().modifyPassword(userId,oldPwd,newPwd,handler,
                    HttpIdentifyingCodeUtil.MODIFY_PASSWORD_S,HttpIdentifyingCodeUtil.MODIFY_PASSWORD_F);
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

   private Handler handler=new Handler(){
       /**
        * Subclasses must implement this to receive messages.
        *
        * @param msg
        */
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what) {
               //密码修改成功
               case HttpIdentifyingCodeUtil.MODIFY_PASSWORD_S:
                   Bundle data = msg.getData();
                   if (data!=null){
                       SharedPreferencesUtil.getInstance().setPassword(activity,password);
                       Toast.makeText(activity, R.string.modify_password_success, Toast.LENGTH_SHORT).show();
                       finish();
                   }

                   break;
               //登陆失败
               case HttpIdentifyingCodeUtil.MODIFY_PASSWORD_F:
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

