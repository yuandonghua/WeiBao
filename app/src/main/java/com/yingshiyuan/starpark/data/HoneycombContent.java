package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 蜂巢界面中的功能模块
 */

public class HoneycombContent implements Parcelable {
    //模块标题
    private String title;
    //模块图片
    private int image;

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

    public HoneycombContent(String title, int image) {
        this.title = title;
        this.image = image;
    }

    protected HoneycombContent(Parcel in) {
        title= in.readString();
        image= in.readInt();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HoneycombContent> CREATOR = new Creator<HoneycombContent>() {
        @Override
        public HoneycombContent createFromParcel(Parcel in) {
            return new HoneycombContent(in);
        }

        @Override
        public HoneycombContent[] newArray(int size) {
            return new HoneycombContent[size];
        }
    };
}
