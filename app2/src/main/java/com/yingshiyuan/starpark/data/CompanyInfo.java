package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;
/**
 *@description:公司信息
 *@author:袁东华
 *created at 2016/7/21 0021 下午 3:45
 */
public class CompanyInfo implements Parcelable {
    //首选字号
    private String name;
    //备选字号
    private String nameOpt;
    //经营范围大类
    private String cls;
    //经营范围细类
    private String industry;
    //公司的状态
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOpt() {
        return nameOpt;
    }

    public void setNameOpt(String nameOpt) {
        this.nameOpt = nameOpt;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CompanyInfo() {
    }

    protected CompanyInfo(Parcel in) {
        name = in.readString();
        nameOpt = in.readString();
        cls = in.readString();
        industry = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nameOpt);
        dest.writeString(cls);
        dest.writeString(industry);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CompanyInfo> CREATOR = new Creator<CompanyInfo>() {
        @Override
        public CompanyInfo createFromParcel(Parcel in) {
            return new CompanyInfo(in);
        }

        @Override
        public CompanyInfo[] newArray(int size) {
            return new CompanyInfo[size];
        }
    };
}
