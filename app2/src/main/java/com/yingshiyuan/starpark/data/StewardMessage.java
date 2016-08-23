package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description:管家提醒消息
 * @author:袁东华 created at 2016/7/27 0027 上午 10:46
 */
public class StewardMessage implements Parcelable {

    //地址的id
    private String id;
    //标题
    private String title;
    //消息内容
    private String detail;
    //消息类型
    private String type;
    //行为
    private String action;
    private String linkId;
    private String procId;
    private String sender;
    //时间
    private String time;
    private String feedbackType;
    private String ext;
    public StewardMessage() {

    }


    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    private StewardMessage(Parcel in) {
        id = in.readString();
        title = in.readString();
        detail = in.readString();
        type = in.readString();
        action = in.readString();
        linkId = in.readString();
        procId = in.readString();
        sender = in.readString();
        time = in.readString();
        feedbackType = in.readString();
        ext = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(title);
        out.writeString(detail);
        out.writeString(type);
        out.writeString(action);
        out.writeString(linkId);
        out.writeString(procId);
        out.writeString(sender);
        out.writeString(time);
        out.writeString(feedbackType);
        out.writeString(ext);
    }

    public static final Creator<StewardMessage> CREATOR = new Creator<StewardMessage>() {
        @Override
        public StewardMessage createFromParcel(Parcel in) {
            return new StewardMessage(in);
        }

        @Override
        public StewardMessage[] newArray(int size) {
            return new StewardMessage[size];
        }
    };


    public static Creator<StewardMessage> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
