package com.yingshiyuan.starpark.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yingshiyuan.starpark.data.StewardMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

/**
 * @description:与管家消息有关
 * @author:袁东华 created at 2016/7/27 0027 上午 10:51
 */
public class StewardMessageHttp {
    /**
     * @description:获取消息列表
     * @author:袁东华 created at 2016/7/27 0027 上午 10:51
     */
    public void getMessageList(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.GET_MESSAGE_LIST);
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
                    JSONArray info = result.getJSONArray("info");
                    ArrayList<StewardMessage> list = new ArrayList<StewardMessage>();
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject jsonObject = info.getJSONObject(i);
                        StewardMessage sm = new StewardMessage();
                        sm.setId(jsonObject.optString("id"));
                        sm.setTitle(jsonObject.optString("title"));
                        sm.setAction(jsonObject.optString("action"));
                        sm.setDetail(jsonObject.optString("detail"));

                        String linkId = jsonObject.optString("linkId");
                        if (!"".equals(linkId)&&linkId.contains("@")) {
                            linkId = linkId.substring(0, linkId.indexOf("@"));
                        }
                        sm.setLinkId(linkId);

                        String procId = jsonObject.optString("procId");
                        if (!"".equals(procId)&&procId.contains("@")) {
                            procId = procId.substring(0, procId.indexOf("@"));
                        }
                        sm.setProcId(procId);

                        sm.setSender(jsonObject.optString("sender"));
                        sm.setType(jsonObject.optString("type"));
                        sm.setTime(jsonObject.optString("sendTimeShow"));
                        sm.setFeedbackType(jsonObject.optString("feedbackType"));
                        sm.setExt(jsonObject.optString("ext"));
                        list.add(sm);

                    }
                    data.putParcelableArrayList("list", list);
                    msg.what = success;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    LogUtil.e(e.toString());
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
     * @description:任务完成
     * @author:袁东华 created at 2016/7/29 0029 下午 6:27
     */
    public void finishTask(final Handler handler,
                           final String user_name, final String task_id,
                           final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.FINISH_TASK);
        params.addBodyParameter("what", "complete");
        params.addBodyParameter("user_logname", "sac_" + user_name);
        params.addBodyParameter("task_id", task_id);

        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                LogUtil.e("返回结果:" + result);
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


    private static StewardMessageHttp instance;

    private StewardMessageHttp() {
    }

    public static StewardMessageHttp getInstance() {
        if (instance == null) {
            synchronized (StewardMessageHttp.class) {
                if (instance == null) {
                    instance = new StewardMessageHttp();
                }
            }
        }
        return instance;
    }
}
