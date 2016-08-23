package com.yingshiyuan.starpark.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 *@description:联系方式等信息界面
 *@author:袁东华
 *created at 2016/6/30 0030 上午 11:50
 */
@ContentView(R.layout.activity_contactinfo)
public class ContactInfoActivity extends BaseActivity {
    public static final String MODIFY_PHONT = "修改手机号";
    public static final String MODIFY_EMAIL = "修改邮箱";
    /**手机号文本框*/
    @ViewInject(R.id.phone_tv)
    private TextView phone_tv;
    /**邮箱文本框*/
    @ViewInject(R.id.email_tv)
    private TextView email_tv;
    @Override
    protected void receiveIntentData()  {

    }

    @Override
    protected void initTopView()  {

        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.contact_way);

    }

    @Override
    protected void initView()  {

    }

    @Override
    protected void initListener()  {
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        //点击返回
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RelativeLayout phone_rl = (RelativeLayout) findViewById(R.id.phone_rl);
        //点击手机号
        phone_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WritePhoneActivity.class);
                intent.putExtra(F, MODIFY_PHONT);
                startActivity(intent);
            }
        });
        RelativeLayout email_rl = (RelativeLayout) findViewById(R.id.email_rl);
        //点击邮箱
        email_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SetEmailActivity.class);
                intent.putExtra(F, MODIFY_EMAIL);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData()  {
        refresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        phone_tv.setText(SharedPreferencesUtil.getInstance().getPhone(activity));
        email_tv.setText(SharedPreferencesUtil.getInstance().getEmail(activity));
    }
}
