package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;
/**
 *@description:股东信息
 *@author:袁东华
 *created at 2016/7/29 0029 下午 3:28
 */
public class ShareHolder implements Parcelable {
    //股东姓名
    private String name="";
    //股东身份证号
    private String idno="";
    //股东入资额
    private String capital="";


    public ShareHolder() {
    }

    protected ShareHolder(Parcel in) {
        name = in.readString();
        idno = in.readString();
        capital = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(idno);
        dest.writeString(capital);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShareHolder> CREATOR = new Creator<ShareHolder>() {
        @Override
        public ShareHolder createFromParcel(Parcel in) {
            return new ShareHolder(in);
        }

        @Override
        public ShareHolder[] newArray(int size) {
            return new ShareHolder[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
