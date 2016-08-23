package com.yingshiyuan.starpark.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.data.CompanyInfo;
import com.yingshiyuan.starpark.data.JuridicalPerson;
import com.yingshiyuan.starpark.http.HttpIdentifyingCodeUtil;
import com.yingshiyuan.starpark.http.SDPath;
import com.yingshiyuan.starpark.utils.SharedPreferencesUtil;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;

/**
 * @description:法人信息界面
 * @author:袁东华 created at 2016/8/1 0001 上午 9:56
 */
@ContentView(R.layout.activity_juridical_info)
public class JuridicalInfoActivity extends BaseActivity {
    //信用代码
    @ViewInject(R.id.creditCode_et)
    private EditText creditCode_et;
    //公司名称
    @ViewInject(R.id.companyName_et)
    private EditText companyName_et;
    //公司类型
    @ViewInject(R.id.companyType_et)
    private EditText companyType_et;
    //公司地址
    @ViewInject(R.id.companyAddress_et)
    private EditText companyAddress_et;
    //法人
    @ViewInject(R.id.juridicalPerson_et)
    private EditText juridicalPerson_et;
    //注册资金
    @ViewInject(R.id.registeredCapital_et)
    private EditText registeredCapital_et;
    //成立日期
    @ViewInject(R.id.dateEstablishment_et)
    private EditText dateEstablishment_et;
    //营业期限
    @ViewInject(R.id.operatingPeriod_et)
    private EditText operatingPeriod_et;
    //经营范围
    @ViewInject(R.id.scopeBusiness_et)
    private EditText scopeBusiness_et;
    //等级机关
    @ViewInject(R.id.department_et)
    private EditText department_et;

    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = JuridicalInfoActivity.this;
    private TextView findPassword_tv;
    private ImageView back_iv;
    //标题
    @ViewInject(R.id.title_tv)
    private TextView title_tv;
    private Address address;
    private String[] type1, type2;
    private String idFrontFlag = "身份证正面";
    private String idBackFlag = "身份证反面";
    private String idFrontFileName = "slp_idpic_front.jpg";
    private String idBackFileName = "slp_idpic_back.jpg";
    private String scope1 = "商贸", scope2 = "建材批发";
    private JuridicalPerson juridicalPerson;
    private CompanyInfo company;
    private String content = "";

    @Override
    protected void receiveIntentData() {
    }

    @Override
    protected void initTopView() {
        title_tv.setText(R.string.juridical_info);

    }

    @Override
    protected void initView() {
    }


    @Override
    protected void initData() {
        //信用代码
        String creditCodeHint = getResources().getString(R.string.prompt_credit_code);
        String creditCodeText = creditCode_et.getText().toString();
        //公司名称
        String companyNameHint = getResources().getString(R.string.prompt_company_name);
        String companyNameText = companyName_et.getText().toString();
        //公司类型
        String companyTypeHint = getResources().getString(R.string.prompt_company_type);
        String companyTypeText = companyType_et.getText().toString();
        //公司地址
        String companyAddressHint = getResources().getString(R.string.prompt_company_address);
        String companyAddressText = companyAddress_et.getText().toString();
        //法人
        String juridicalPersonHint = getResources().getString(R.string.prompt_juridical_person);
        String juridicalPersonText = juridicalPerson_et.getText().toString();
        //注册资金
        String registeredCapitalHint = getResources().getString(R.string.prompt_registered_capital);
        String registeredCapitalText = registeredCapital_et.getText().toString();
        //成立日期
        String dateEstablishmentHint = getResources().getString(R.string.prompt_date_establishment);
        String dateEstablishmentText = dateEstablishment_et.getText().toString();
        //营业期限
        String operatingPeriodHint = getResources().getString(R.string.prompt_operating_period);

        //邮件内容
        content = creditCodeHint + ":" + creditCodeText + "\n" +
                companyNameHint + ":" + companyNameText + "\n" +
                companyTypeHint + ":" + companyTypeText + "\n" +
                companyAddressHint + ":" + companyAddressText + "\n" +
                juridicalPersonHint + ":" + juridicalPersonText + "\n" +
                registeredCapitalHint + ":" + registeredCapitalText + "\n" +
                dateEstablishmentHint + ":" + dateEstablishmentText ;

    }

    @Override
    protected void initListener() {
        ImageView back_iv = (ImageView) findViewById(R.id.back_iv);
        //点击返回
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button sendToMailbox_btn = (Button) findViewById(R.id.sendToMailbox_btn);
        //点击发送至邮箱
        sendToMailbox_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = SharedPreferencesUtil.getInstance().getEmail(activity);
                Intent email = new Intent(Intent.ACTION_SEND_MULTIPLE);
                //邮件发送类型：无附件，纯文本
                email.setType("plain/text");
                //邮件接收者（数组，可以是多位接收者）
                String[] emailReciver = new String[]{account};

                String emailTitle = "营业执照";
                //设置接收者
                email.putExtra(Intent.EXTRA_EMAIL, emailReciver);
                //发送者
//                email.putExtra(Intent.EXTRA_CC, ccs);
                //设置邮件标题
                email.putExtra(Intent.EXTRA_SUBJECT, emailTitle);
                //设置发送的内容
                email.putExtra(Intent.EXTRA_TEXT, content);
                //附件
                ArrayList<Uri> list = new ArrayList<Uri>();
                list.add(Uri.fromFile(new File(SDPath.IMAGE_CACHE, "slp_idpic_front.jpg")));
                list.add(Uri.fromFile(new File(SDPath.IMAGE_CACHE, "slp_idpic_back.jpg")));
                email.putParcelableArrayListExtra(Intent.EXTRA_STREAM, list);
                //调用系统的邮件系统
                startActivity(Intent.createChooser(email, "请选择邮件发送软件"));
                Toast.makeText(activity, "正在发送...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @description:提交数据
     * @author:袁东华 created at 2016/7/20 0020 下午 5:47
     */
    private void attemptSubmit() {
//
//        juridicalPersonName_et.setError(null);
//        juridicalPersonId_et.setError(null);
//        fristName_et.setError(null);
//        alternativeName_et.setError(null);
//        //法人姓名
//        String juridicalPersonName = juridicalPersonName_et.getText().toString();
//        //法人身份证号
//        String juridicalPersonId = juridicalPersonId_et.getText().toString();
//        //首选字号
//        String fristName = fristName_et.getText().toString();
//        //备选字号
//        String alternativeName = alternativeName_et.getText().toString();
//        boolean cancel = false;
//        View focusView = null;
//        if (TextUtils.isEmpty(juridicalPersonName)) {
//            juridicalPersonName_et.setError(getString(R.string.error_field_required));
//            focusView = juridicalPersonName_et;
//            cancel = true;
//        } else if (TextUtils.isEmpty(juridicalPersonId)) {
//            juridicalPersonId_et.setError(getString(R.string.error_field_required));
//            focusView = juridicalPersonId_et;
//            cancel = true;
//        } else if (TextUtils.isEmpty(fristName)) {
//            fristName_et.setError(getString(R.string.error_field_required));
//            focusView = fristName_et;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//            File idFrontFile = new File(SDPath.IMAGE_CACHE, idFrontFileName);
//            File idBackFile = new File(SDPath.IMAGE_CACHE, idBackFileName);
//            if (!idFrontFile.exists()) {
//                Toast.makeText(activity, "请拍摄身份证正面", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (!idBackFile.exists()) {
//                Toast.makeText(activity, "请拍摄身份证反面", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if ("".equals(scope1) || "".equals(scope2)) {
//                Toast.makeText(activity, "请选择行业特点", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            CompanyHttp.getInstance().createCompany(handler,
//                    ManageUserDataUtil.getInstance().getUserId(activity),
//                    juridicalPersonName, juridicalPersonId, idFrontFile, idBackFile, scope1, scope2,
//                    fristName, alternativeName,
//                    HttpIdentifyingCodeUtil.CREATE_COMPANY_S, HttpIdentifyingCodeUtil.CREATE_COMPANY_F);
//        }
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
//                            juridicalPersonName_et.setText(juridicalPerson.getName());
//                            juridicalPersonName_et.setFocusable(false);
//                            juridicalPersonId_et.setText(juridicalPerson.getIdno());
//                            juridicalPersonId_et.setFocusable(false);
//                            fristName_et.setText(company.getName());
//                            fristName_et.setFocusable(false);
//                            alternativeName_et.setText(company.getNameOpt());
//                            alternativeName_et.setFocusable(false);

//                            x.image().bind(idFront_iv, juridicalPerson.getIdPicFront(), new CommonCallback<Drawable>() {
//                                @Override
//                                public void onSuccess(Drawable result) {
//
//                                }
//
//                                @Override
//                                public void onError(Throwable ex, boolean isOnCallback) {
//                                    idFront_iv.setImageResource(R.drawable.ic_id_front_black);
//                                }
//
//                                @Override
//                                public void onCancelled(CancelledException cex) {
//
//                                }
//
//                                @Override
//                                public void onFinished() {
//
//                                }
//                            });
//                            x.image().bind(idBack_iv, juridicalPerson.getIdPicBack(),
//                                    new CommonCallback<Drawable>() {
//                                        @Override
//                                        public void onSuccess(Drawable result) {
//                                        }
//
//                                        @Override
//                                        public void onError(Throwable ex, boolean isOnCallback) {
//                                            idBack_iv.setImageResource(R.drawable.ic_id_back_black);
//                                        }
//
//                                        @Override
//                                        public void onCancelled(CancelledException cex) {
//                                        }
//
//                                        @Override
//                                        public void onFinished() {
//                                        }
//                                    });
//
//                            idFront_iv.setEnabled(false);
//                            idBack_iv.setEnabled(false);

//                            int position;
//                            for (int i = 0; i < type1.length; i++) {
//                                if (type1[i].equals(company.getCls())) {
//                                    position = i;
//                                    scopeOfBusiness_sp1.setSelection(position);
//                                }
//                            }
//                            scopeOfBusiness_sp1.setEnabled(false);
//                            scopeOfBusiness_sp2.setEnabled(false);


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
                        if (!"success".equals(message)) {

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

