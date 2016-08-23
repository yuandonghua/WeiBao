package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description:职工的信息
 * @author:袁东华 created at 2016/7/29 0029 下午 4:54
 */
public class Worker implements Parcelable {
    //职工姓名
    private String name="";
    //职工身份证号
    private String idno="";
    //职工的职位
    private String position="";
    //职工的身份证地址
    private String address="";
    //职工的产生方式
    private String orig_way="";

    public Worker() {
    }

    protected Worker(Parcel in) {
        name = in.readString();
        idno = in.readString();
        position = in.readString();
        address = in.readString();
        orig_way = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(idno);
        dest.writeString(position);
        dest.writeString(address);
        dest.writeString(orig_way);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrig_way() {
        return orig_way;
    }

    public void setOrig_way(String orig_way) {
        this.orig_way = orig_way;
    }
}
