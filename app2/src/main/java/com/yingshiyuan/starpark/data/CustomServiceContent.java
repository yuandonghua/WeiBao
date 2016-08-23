package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description:客服内容
 * @author:袁东华 created at 2016/7/8 0008 下午 1:37
 */
public class CustomServiceContent implements Parcelable {
    //模块标题
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public CustomServiceContent(String title) {
        this.title = title;
    }

    protected CustomServiceContent(Parcel in) {
        title = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CustomServiceContent> CREATOR = new Creator<CustomServiceContent>() {
        @Override
        public CustomServiceContent createFromParcel(Parcel in) {
            return new CustomServiceContent(in);
        }

        @Override
        public CustomServiceContent[] newArray(int size) {
            return new CustomServiceContent[size];
        }
    };
}
