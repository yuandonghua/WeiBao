package com.yingshiyuan.starpark.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yingshiyuan.starpark.data.CompanyInfo;
import com.yingshiyuan.starpark.data.JuridicalPerson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * @description:与公司相关
 * @author:袁东华 created at 2016/7/20 0020 下午 5:13
 */
public class CompanyHttp {
    /**
     * @description:补充职工信息
     * @author:袁东华 created at 2016/7/29 0029 下午 5:06
     */
    public void supplementaryWorker(final Handler handler,
                                    final String user_id, String comp_id,
                                    final String name, final String idno, final String position,
                                    final String address, final String orig_way,
                                    final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.SUPPLEMENTARY_SHAREHOLDER);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("comp_id", comp_id);
        params.addBodyParameter("type", "holder");
        params.addBodyParameter("sac_comp_man_name", name);
        params.addBodyParameter("sac_comp_man_idno", idno);
        params.addBodyParameter("sac_comp_man_position", position);
        params.addBodyParameter("sac_comp_man_address", address);
        params.addBodyParameter("sac_comp_man_orig_way", orig_way);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                LogUtil.e("supplementaryWorker"+result.toString());
                try {
                    if (result != null) {
                        String message = result.optString("message");
                        Bundle data = new Bundle();
                        Message msg = new Message();
                        data.putString("message", message);
                        msg.setData(data);
                        if ("success".equals(message)) {
                            handler.sendEmptyMessage(success);
                        } else {
                            handler.sendEmptyMessage(error);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (handler != null) {

                    handler.sendEmptyMessage(error);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (handler != null) {

                    handler.sendEmptyMessage(error);
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * @description:补充公司股东信息
     * @author:袁东华 created at 2016/7/29 0029 下午 3:57
     */
    public void supplementaryShareHolder(final Handler handler,
                                         final String user_id, String comp_id,
                                         final String name, final String idno, final String capital,
                                         final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.SUPPLEMENTARY_SHAREHOLDER);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("comp_id", comp_id);
        params.addBodyParameter("type", "holder");
        params.addBodyParameter("sac_comp_holder_name", name);
        params.addBodyParameter("sac_comp_holder_idno", idno);
        params.addBodyParameter("sac_comp_holder_capital", capital);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                LogUtil.e("supplementaryShareHolder:"+result.toString());
                try {
                    if (result != null) {
                        String message = result.optString("message");
                        Bundle data = new Bundle();
                        Message msg = new Message();
                        data.putString("message", message);
                        msg.setData(data);
                        if ("success".equals(message)) {
                            handler.sendEmptyMessage(success);
                        } else {
                            handler.sendEmptyMessage(error);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (handler != null) {

                    handler.sendEmptyMessage(error);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (handler != null) {

                    handler.sendEmptyMessage(error);
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * @description:补充公司法人信息
     * @author:袁东华 created at 2016/7/29 0029 下午 2:47
     */
    public void supplementaryJuridicalPerson(final Handler handler,
                                             final String content,
                                             final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.CREATE_COMPANY+content);

        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                LogUtil.e("supplementaryJuridicalPerson"+result.toString());
                try {
                    if (result != null) {
                        String message = result.optString("message");
                        Bundle data = new Bundle();
                        Message msg = new Message();
                        data.putString("message", message);
                        msg.setData(data);
                        if ("success".equals(message)) {
                            handler.sendEmptyMessage(success);
                        } else {
                            handler.sendEmptyMessage(error);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (handler != null) {

                    handler.sendEmptyMessage(error);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (handler != null) {

                    handler.sendEmptyMessage(error);
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * @description:获取用户的公司信息
     * @author:袁东华 created at 2016/7/21 0021 上午 11:19
     */
    public void getCompanyInfo(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.GET_COMPANY_INFO);
        params.addBodyParameter("user_id", user_id);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    data.putString("message", message);
                    msg.what = error;
                    if (!"success".equals(message)) {
                        handler.sendMessage(msg);
                        return;
                    }
                    if (result.isNull("info")) {
                        handler.sendMessage(msg);
                        return;
                    }
                    JSONObject info = result.getJSONObject("info");
                    if (info.isNull("sacLegalPerson")) {
                        handler.sendMessage(msg);
                        return;
                    }
                    if (info.isNull("sacComp")) {
                        handler.sendMessage(msg);
                        return;
                    }
                    //解析法人信息
                    JSONObject sacLegalPerson = info.getJSONObject("sacLegalPerson");
                    JuridicalPerson juridicalPerson = new JuridicalPerson();
                    juridicalPerson.setName(sacLegalPerson.optString("name"));
                    juridicalPerson.setIdno(sacLegalPerson.optString("idno"));
                    juridicalPerson.setIdPicBack(HttpUrlUtil.SERVER_IMAGE + sacLegalPerson.optString("idpicBack"));
                    juridicalPerson.setIdPicFront(HttpUrlUtil.SERVER_IMAGE + sacLegalPerson.optString("idpicFront"));
                    data.putParcelable("juridicalPerson", juridicalPerson);
                    //解析公司信息
                    JSONObject sacComp = info.getJSONObject("sacComp");
                    CompanyInfo company = new CompanyInfo();
                    company.setName(sacComp.optString("name"));
                    company.setNameOpt(sacComp.optString("nameOpt"));
                    company.setCls(sacComp.optString("cls"));
                    company.setIndustry(sacComp.optString("industry"));
                    //解析核名状态
                    company.setStatus(sacComp.optString("status"));
                    data.putParcelable("company", company);
                    msg.what = success;
                    handler.sendMessage(msg);
                } catch (Exception e) {

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * @description:公司设立界面提交数据的接口
     * @author:袁东华 created at 2016/7/20 0020 下午 5:26
     */
    public void createCompany(final Handler handler,
                              final String user_id, final String task_id, final String juridicalPersonName,
                              final String juridicalPersonIDNumber, File idFront,
                              File idBack, final String scope1, final String scope2,
                              final String firstName, final String alternativeName,
                              final int flag1, final int flag2) {
        RequestParams params = new RequestParams(HttpUrlUtil.CREATE_COMPANY);
        params.setMultipart(true);
        //用户ID
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("task_id", task_id);
        //法人姓名
        params.addBodyParameter("slp_name", juridicalPersonName);
        //法人身份证号
        params.addBodyParameter("slp_idno", juridicalPersonIDNumber);
        //经营范围种类
//        params.addBodyParameter("cls", scope1);
        if ("".equals(scope1) || "".equals(scope2)) {
            //经营范围细类
            params.addBodyParameter("industry", "");
        } else {
            //经营范围细类
            params.addBodyParameter("industry", scope2 + "@" + scope1);
        }
        //首选字号
        params.addBodyParameter("name", firstName);
        //备选字号
        params.addBodyParameter("nameOpt", alternativeName);
        //身份证正面照
        params.addBodyParameter("uploadFile", idFront);
        //身份证反面照
        params.addBodyParameter("uploadFile", idBack);
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    if (result != null) {
                        String message = result.optString("message");
                        Bundle data = new Bundle();
                        Message msg = new Message();
                        data.putString("message", message);
                        msg.setData(data);
                        if ("success".equals(message)) {
                            handler.sendEmptyMessage(flag1);
                        } else {
                            handler.sendEmptyMessage(flag2);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (handler != null) {

                    handler.sendEmptyMessage(flag2);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (handler != null) {

                    handler.sendEmptyMessage(flag2);
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }


    private static CompanyHttp instance;

    private CompanyHttp() {
    }

    public static CompanyHttp getInstance() {
        if (instance == null) {
            synchronized (CompanyHttp.class) {
                if (instance == null) {
                    instance = new CompanyHttp();
                }
            }
        }
        return instance;
    }
}
