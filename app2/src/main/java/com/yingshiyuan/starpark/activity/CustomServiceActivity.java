package com.yingshiyuan.starpark.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.adapter.CustomServiceAdapter;
import com.yingshiyuan.starpark.data.CustomServiceContent;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.recyclerview.HorizontalDividerItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;

/**
 * @description:客服界面
 * @author:袁东华 created at 2016/7/8 0008 下午 12:04
 */
@ContentView(R.layout.activity_customservice)
public class CustomServiceActivity extends BaseActivity {
    private static final int REQUEST_PERMISSION = 0;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private CustomServiceAdapter customServiceAdapter;
    private ArrayList<CustomServiceContent> list = new ArrayList<>();
    @ViewInject(R.id.right_tv)
    private TextView right_tv;

    @Override
    protected void receiveIntentData()  {

    }

    @Override
    protected void initTopView()  {

        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(R.string.action_custom_service);
        right_tv.setText(R.string.call_phone);
        right_tv.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initView()  {


        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(activity)
                .color(getResources().getColor(R.color.black_14_2))
                .size(activity.getResources().getDimensionPixelSize(
                        R.dimen.divider_2dp))
                .build());

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
        customServiceAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        //点击拨打
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission(CALL_PHONE,REQUEST_PERMISSION)) {
                    callPhone();
                } else {
                }
            }
        });
    }

    private void callPhone() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(activity, R.string.permission_rationale, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + R.string.phone_number);
        intent.setData(data);
        startActivity(intent);
    }



    @Override
    protected void initData()  {
        list.add(new CustomServiceContent("[工商]一个人可以注册多个公司吗?"));
        list.add(new CustomServiceContent("[工商]注册公司有什么要求吗?"));
        list.add(new CustomServiceContent("[业务]提供公司注册地址要收费吗?"));
        list.add(new CustomServiceContent("[业务]注册公司有那些好处?"));
        list.add(new CustomServiceContent("[财税]报销票据都有那些要求?"));
        list.add(new CustomServiceContent("[财税]普通发票与专票有什么不同?"));
        list.add(new CustomServiceContent("[工商]一个人可以注册多个公司吗?"));
        list.add(new CustomServiceContent("[工商]注册公司有什么要求吗?"));
        list.add(new CustomServiceContent("[业务]提供公司注册地址要收费吗?"));
        list.add(new CustomServiceContent("[业务]注册公司有那些好处?"));
        list.add(new CustomServiceContent("[财税]报销票据都有那些要求?"));
        list.add(new CustomServiceContent("[财税]普通发票与专票有什么不同?"));
        list.add(new CustomServiceContent("[工商]一个人可以注册多个公司吗?"));
        list.add(new CustomServiceContent("[工商]注册公司有什么要求吗?"));
        list.add(new CustomServiceContent("[业务]提供公司注册地址要收费吗?"));
        list.add(new CustomServiceContent("[业务]注册公司有那些好处?"));
        list.add(new CustomServiceContent("[财税]报销票据都有那些要求?"));
        list.add(new CustomServiceContent("[财税]普通发票与专票有什么不同?"));
        list.add(new CustomServiceContent("[工商]一个人可以注册多个公司吗?"));
        list.add(new CustomServiceContent("[工商]注册公司有什么要求吗?"));
        list.add(new CustomServiceContent("[业务]提供公司注册地址要收费吗?"));
        list.add(new CustomServiceContent("[业务]注册公司有那些好处?"));
        list.add(new CustomServiceContent("[财税]报销票据都有那些要求?"));
        list.add(new CustomServiceContent("[财税]普通发票与专票有什么不同?"));
        customServiceAdapter = new CustomServiceAdapter(activity);
        customServiceAdapter.setList(list);
        recyclerView.setAdapter(customServiceAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            }
        }
    }
}
