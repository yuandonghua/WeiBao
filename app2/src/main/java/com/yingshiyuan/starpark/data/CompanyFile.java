package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *@description:公司档案
 *@author:袁东华
 *created at 2016/7/15 0015 上午 11:48
 */
public class CompanyFile implements Parcelable {
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

    public CompanyFile(String title) {
        this.title = title;
    }

    public CompanyFile(String title, int image) {
        this.title = title;
        this.image = image;
    }

    protected CompanyFile(Parcel in) {
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

    public static final Creator<CompanyFile> CREATOR = new Creator<CompanyFile>() {
        @Override
        public CompanyFile createFromParcel(Parcel in) {
            return new CompanyFile(in);
        }

        @Override
        public CompanyFile[] newArray(int size) {
            return new CompanyFile[size];
        }
    };
}
