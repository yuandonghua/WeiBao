package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 工单
 */

public class WorkerOrder implements Parcelable {
    //标题
    private String title;
    //图片
    private int image;
    //消息内容
    private String message;
    //时间
    private String time;
    //工单类型
    private String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public WorkerOrder(String title, int image, String message, String time, String type) {
        this.title = title;
        this.image = image;
        this.message = message;
        this.time = time;
        this.type = type;
    }

    protected WorkerOrder(Parcel in) {
        title = in.readString();
        image = in.readInt();
        message = in.readString();
        time = in.readString();
        type = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(image);
        dest.writeString(message);
        dest.writeString(time);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkerOrder> CREATOR = new Creator<WorkerOrder>() {
        @Override
        public WorkerOrder createFromParcel(Parcel in) {
            return new WorkerOrder(in);
        }

        @Override
        public WorkerOrder[] newArray(int size) {
            return new WorkerOrder[size];
        }
    };
}
