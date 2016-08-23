package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;
/**
 *@description:财务服务
 *@author:袁东华
 *created at 2016/7/15 0015 下午 1:54
 */
public class FinancialService implements Parcelable {
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

    public FinancialService(String title) {
        this.title = title;
    }

    public FinancialService(String title, int image) {
        this.title = title;
        this.image = image;
    }

    protected FinancialService(Parcel in) {
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

    public static final Creator<FinancialService> CREATOR = new Creator<FinancialService>() {
        @Override
        public FinancialService createFromParcel(Parcel in) {
            return new FinancialService(in);
        }

        @Override
        public FinancialService[] newArray(int size) {
            return new FinancialService[size];
        }
    };
}
