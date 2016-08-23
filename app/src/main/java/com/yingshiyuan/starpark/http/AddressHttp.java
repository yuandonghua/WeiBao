package com.yingshiyuan.starpark.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.json.AddressJson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:与用户地址有关
 * @author:袁东华 created at 2016/6/30 0030 下午 1:24
 */
public class AddressHttp {
    /**
     * 删除用户地址
     *
     * @param addr_id 地址id
     * @param handler 接收结果
     * @param success 结果成功
     * @param error   结果错误
     */
    public void deleteAddress(String addr_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.DELETE_ADDRESS);
        params.addBodyParameter("addr_id", addr_id);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
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
     * 获取用户地址列表
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 结果成功
     * @param error   结果错误
     */
    public void getAddressList(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.GET_ADDRESS_LIST);
        params.addBodyParameter("user_id", user_id);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        ArrayList<Address> addresses = AddressJson.getInstance().analyzeAddressList(result);
                        data.putParcelableArrayList("list", addresses);
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
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
     * 新增地址
     *
     * @param user_id  用户id
     * @param name     收件人
     * @param tel      收件人电话
     * @param province 省份
     * @param city     城市
     * @param district 地区
     * @param address  详细地址
     * @param first    是否默认
     * @param handler  接收结果
     * @param success  结果成功
     * @param error    结果错误
     */
    public void addAddress(String user_id, String name, String tel, String province, String city,
                           String district, String address, String first,
                           final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.ADD_ADDRESS);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("name", name);
        params.addBodyParameter("tel", tel);
        params.addBodyParameter("province", province);
        params.addBodyParameter("city", city);
        params.addBodyParameter("district", district);
        params.addBodyParameter("address", address);
        params.addBodyParameter("first", first);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
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
     * 编辑地址
     *
     * @param user_id  用户id
     * @param addr_id  地址id
     * @param name     收件人
     * @param tel      收件人电话
     * @param province 省份
     * @param city     城市
     * @param district 地区
     * @param address  详细地址
     * @param first    是否默认
     * @param handler  接收结果
     * @param success  结果成功
     * @param error    结果错误
     */
    public void editAddress(String user_id, String addr_id, String name, String tel, String province, String city,
                            String district, String address, String first,
                            final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.ADD_ADDRESS);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("addr_id", addr_id);
        params.addBodyParameter("name", name);
        params.addBodyParameter("tel", tel);
        params.addBodyParameter("province", province);
        params.addBodyParameter("city", city);
        params.addBodyParameter("district", district);
        params.addBodyParameter("address", address);
        params.addBodyParameter("first", first);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
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

    private static AddressHttp instance;

    private AddressHttp() {
    }

    public static AddressHttp getInstance() {
        if (instance == null) {
            synchronized (AddressHttp.class) {
                if (instance == null) {
                    instance = new AddressHttp();
                }
            }
        }
        return instance;
    }
}
