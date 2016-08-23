package com.yingshiyuan.starpark.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.ucrop.UCropActivity;
import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.data.CompanyInfo;
import com.yingshiyuan.starpark.data.JuridicalPerson;
import com.yingshiyuan.starpark.http.CompanyHttp;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.http.SDPath;
import com.yingshiyuan.starpark.utils.ManageUserDataUtil;
import com.yingshiyuan.starpark.utils.XUtils;

import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * @description:公司成立界面
 * @author:袁东华 created at 2016/7/19 0019 上午 9:24
 */
@ContentView(R.layout.activity_create_company)
public class CreateCompanyActivity extends BaseActivity {

    //经营范围下拉框
    @ViewInject(R.id.scopeOfBusiness_sp1)
    private Spinner scopeOfBusiness_sp1;
    //经营范围下拉框
    @ViewInject(R.id.scopeOfBusiness_sp2)
    private Spinner scopeOfBusiness_sp2;
    //身份证正面
    @ViewInject(R.id.idFront_iv)
    private ImageView idFront_iv;
    //身份证反面
    @ViewInject(R.id.idBack_iv)
    private ImageView idBack_iv;
    //法人姓名输入框
    @ViewInject(R.id.juridicalPersonName_et)
    private AutoCompleteTextView juridicalPersonName_et;
    //法人身份证号
    @ViewInject(R.id.juridicalPersonId_et)
    private EditText juridicalPersonId_et;
    //首选字号
    @ViewInject(R.id.fristName_et)
    private EditText fristName_et;
    //备选字号
    @ViewInject(R.id.alternativeName_et)
    private EditText alternativeName_et;

    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = CreateCompanyActivity.this;
    private TextView findPassword_tv;
    private ImageView back_iv;
    //标题
    @ViewInject(R.id.title_tv)
    private TextView title_tv;
    //流程
    @ViewInject(R.id.right_tv)
    private TextView right_tv;
    private Address address;
    private String[] type1, type2;
    private String idFrontFlag = "身份证正面";
    private String idBackFlag = "身份证反面";
    private String idFrontFileName = "slp_idpic_front.jpg";
    private String idBackFileName = "slp_idpic_back.jpg";
    private String scope1 = "商贸", scope2 = "建材批发";
    private JuridicalPerson juridicalPerson;
    private CompanyInfo company;

    @Override
    protected void receiveIntentData()  {
    }

    @Override
    protected void initTopView()  {
        title_tv.setText(R.string.set_company);
        right_tv.setText("流程");
        right_tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView()  {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(activity,
                R.array.scope_of_business_1, R.layout.support_simple_spinner_dropdown_item);
        scopeOfBusiness_sp1.setAdapter(arrayAdapter);
        type1 = getResources().getStringArray(R.array.scope_of_business_1);
        ArrayAdapter arrayAdapter2 = ArrayAdapter.createFromResource(activity,
                R.array.sm, R.layout.support_simple_spinner_dropdown_item);
        scopeOfBusiness_sp2.setAdapter(arrayAdapter2);
    }

    @Override
    protected void initListener()  {
        right_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,CreateCompanyProcessActivity.class);
                startActivity(intent);
            }
        });
        Button submit_btn = (Button) findViewById(R.id.submit_btn);
        //点击提交
        submit_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( company!=null&&!"".equals(company.getStatus())) {
                    Toast.makeText(activity,company.getStatus(), Toast.LENGTH_SHORT).show();
                } else {
                    attemptSubmit();
                }
            }
        });
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        //点击返回
        back_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击正面
        idFront_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera(idFrontFlag);
            }
        });
        //点击反面
        idBack_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera(idBackFlag);
            }
        });


        scopeOfBusiness_sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scope1 = type1[position];
                if ("商贸".equals(scope1)) {
                    ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(activity,
                            R.array.sm, R.layout.support_simple_spinner_dropdown_item);
                    scopeOfBusiness_sp2.setAdapter(arrayAdapter);
                    type2 = getResources().getStringArray(R.array.sm);
                    scope2 = type2[0];
                } else if ("科技".equals(scope1)) {
                    ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(activity,
                            R.array.kj, R.layout.support_simple_spinner_dropdown_item);
                    scopeOfBusiness_sp2.setAdapter(arrayAdapter);
                    type2 = getResources().getStringArray(R.array.kj);
                    scope2 = type2[0];
                } else if ("信息咨询".equals(scope1)) {
                    ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(activity,
                            R.array.xxzx, R.layout.support_simple_spinner_dropdown_item);
                    scopeOfBusiness_sp2.setAdapter(arrayAdapter);
                    type2 = getResources().getStringArray(R.array.xxzx);
                    scope2 = type2[0];
                } else if ("百货".equals(scope1)) {
                    ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(activity,
                            R.array.bh, R.layout.support_simple_spinner_dropdown_item);
                    scopeOfBusiness_sp2.setAdapter(arrayAdapter);
                    type2 = getResources().getStringArray(R.array.bh);
                    scope2 = type2[0];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        scopeOfBusiness_sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scope2 = type2[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initData()  {
        //获取公司信息
        CompanyHttp.getInstance().getCompanyInfo(ManageUserDataUtil.getInstance().getUserId(activity),
                handler, HttpIdentifyingCodeUtil.GET_COMPANY_INFO_S, HttpIdentifyingCodeUtil.GET_COMPANY_INFO_F);


    }


    /**
     * @description:提交数据
     * @author:袁东华 created at 2016/7/20 0020 下午 5:47
     */
    private void attemptSubmit() {

        juridicalPersonName_et.setError(null);
        juridicalPersonId_et.setError(null);
        fristName_et.setError(null);
        alternativeName_et.setError(null);
        //法人姓名
        String juridicalPersonName = juridicalPersonName_et.getText().toString();
        //法人身份证号
        String juridicalPersonId = juridicalPersonId_et.getText().toString();
        //首选字号
        String fristName = fristName_et.getText().toString();
        //备选字号
        String alternativeName = alternativeName_et.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(juridicalPersonName)) {
            juridicalPersonName_et.setError(getString(R.string.error_field_required));
            focusView = juridicalPersonName_et;
            cancel = true;
        } else if (TextUtils.isEmpty(juridicalPersonId)) {
            juridicalPersonId_et.setError(getString(R.string.error_field_required));
            focusView = juridicalPersonId_et;
            cancel = true;
        } else if (TextUtils.isEmpty(fristName)) {
            fristName_et.setError(getString(R.string.error_field_required));
            focusView = fristName_et;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            File idFrontFile = new File(SDPath.IMAGE_CACHE, idFrontFileName);
            File idBackFile = new File(SDPath.IMAGE_CACHE, idBackFileName);
            if (!idFrontFile.exists()) {
                Toast.makeText(activity, "请拍摄身份证正面", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!idBackFile.exists()) {
                Toast.makeText(activity, "请拍摄身份证反面", Toast.LENGTH_SHORT).show();
                return;
            }
            if ("".equals(scope1) || "".equals(scope2)) {
                Toast.makeText(activity, "请选择行业特点", Toast.LENGTH_SHORT).show();
                return;
            }

            CompanyHttp.getInstance().createCompany(handler,
                    ManageUserDataUtil.getInstance().getUserId(activity),"",
                    juridicalPersonName, juridicalPersonId, idFrontFile, idBackFile, scope1, scope2,
                    fristName, alternativeName,
                    HttpIdentifyingCodeUtil.CREATE_COMPANY_S, HttpIdentifyingCodeUtil.CREATE_COMPANY_F);
        }
    }

    protected void openCamera(String idFlag) {
        // TODO Auto-generated method stub
        try {
            File folder = new File(SDPath.IMAGE_CACHE);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            if (idFrontFlag.equals(idFlag)) {
                startActivityForResult(getTakePickIntent(new File(
                        SDPath.IMAGE_CACHE, "/id_front_0.0")), HttpIdentifyingCodeUtil.OPEN_CAMERA_ID_FRONT);
            } else if (idBackFlag.equals(idFlag)) {
                startActivityForResult(getTakePickIntent(new File(
                        SDPath.IMAGE_CACHE, "/id_back_0.0")), HttpIdentifyingCodeUtil.OPEN_CAMERA_ID_BACK);
            }

        } catch (Exception e) {
            Toast.makeText(activity, "没有找到相机", Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HttpIdentifyingCodeUtil.OPEN_CAMERA_ID_FRONT) {
            try {
                File t = new File(SDPath.IMAGE_CACHE, "/id_front_0.0");
                if (t.exists()) {
                    Uri input_uri = Uri.fromFile(t);
                    Uri output_uri = Uri.fromFile(new File(SDPath.IMAGE_CACHE, idFrontFileName));
                    Intent intent = new Intent(activity, UCropActivity.class);
                    intent.putExtra("input_uri", input_uri);
                    intent.putExtra("output_uri", output_uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(activity, "图片获取失败,请重新拍照", Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else if (requestCode == HttpIdentifyingCodeUtil.OPEN_CAMERA_ID_BACK) {
            try {
                File t = new File(SDPath.IMAGE_CACHE, "/id_back_0.0");
                if (t.exists()) {
                    Uri input_uri = Uri.fromFile(t);
                    Uri output_uri = Uri.fromFile(new File(SDPath.IMAGE_CACHE, idBackFileName));
                    Intent intent = new Intent(activity, UCropActivity.class);
                    intent.putExtra("input_uri", input_uri);
                    intent.putExtra("output_uri", output_uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(activity, "图片获取失败,请重新拍照", Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //裁剪完毕,加载身份证正反面图片
        File idFrontFile = new File(SDPath.IMAGE_CACHE, idFrontFileName);
        if (idFrontFile.exists()) {
            x.image().bind(idFront_iv, idFrontFile.getAbsolutePath(),
                    XUtils.getInstance().getImageOptions(activity,false));
        }
        File idBackFile = new File(SDPath.IMAGE_CACHE, idBackFileName);
        if (idBackFile.exists()) {
            x.image().bind(idBack_iv, idBackFile.getAbsolutePath(),
                    XUtils.getInstance().getImageOptions(activity,false));
        }

    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //获取公司信息成功
                case HttpIdentifyingCodeUtil.GET_COMPANY_INFO_S:
                    Bundle successdata1 = msg.getData();
                    if (successdata1 != null) {
                        company = successdata1.getParcelable("company");
                        juridicalPerson = successdata1.getParcelable("juridicalPerson");
                        if (company != null && juridicalPerson != null) {
                            juridicalPersonName_et.setText(juridicalPerson.getName());
                            juridicalPersonName_et.setFocusable(false);
                            juridicalPersonId_et.setText(juridicalPerson.getIdno());
                            juridicalPersonId_et.setFocusable(false);
                            fristName_et.setText(company.getName());
                            fristName_et.setFocusable(false);
                            alternativeName_et.setText(company.getNameOpt());
                            alternativeName_et.setFocusable(false);

                            x.image().bind(idFront_iv, juridicalPerson.getIdPicFront(), new CommonCallback<Drawable>() {
                                @Override
                                public void onSuccess(Drawable result) {

                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    idFront_iv.setImageResource(R.drawable.ic_id_front_black);
                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                            x.image().bind(idBack_iv, juridicalPerson.getIdPicBack(),
                                    new CommonCallback<Drawable>() {
                                        @Override
                                        public void onSuccess(Drawable result) {
                                        }

                                        @Override
                                        public void onError(Throwable ex, boolean isOnCallback) {
                                            idBack_iv.setImageResource(R.drawable.ic_id_back_black);
                                        }

                                        @Override
                                        public void onCancelled(CancelledException cex) {
                                        }

                                        @Override
                                        public void onFinished() {
                                        }
                                    });

                            idFront_iv.setEnabled(false);
                            idBack_iv.setEnabled(false);

                            int position;
                            for (int i = 0; i < type1.length; i++) {
                                if (type1[i].equals(company.getCls())) {
                                    position = i;
                                    scopeOfBusiness_sp1.setSelection(position);
                                }
                            }
                            scopeOfBusiness_sp1.setEnabled(false);
                            scopeOfBusiness_sp2.setEnabled(false);


                        } else {
                            LogUtil.e("没有获取到公司和法人信息");
                        }
                    }

                    break;
                //获取公司信息失败
                case HttpIdentifyingCodeUtil.GET_COMPANY_INFO_F:
                    Bundle errorData1 = msg.getData();
                    if (errorData1 != null) {
                        String message = errorData1.getString("message");
                        if (!"success".equals(message)){

                            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                //公司设立成功
                case HttpIdentifyingCodeUtil.CREATE_COMPANY_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        Toast.makeText(activity, R.string.operate_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                //公司设立失败
                case HttpIdentifyingCodeUtil.CREATE_COMPANY_F:
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

