package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description:要补充的公司信息
 * @author:袁东华 created at 2016/7/29 0029 上午 11:40
 */
public class SupplementaryCompanyInfo implements Parcelable {
    private String name;
    private String type;
    private String label;
    private String value="";
    public SupplementaryCompanyInfo() {
    }

    protected SupplementaryCompanyInfo(Parcel in) {
        name = in.readString();
        type = in.readString();
        label = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(label);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SupplementaryCompanyInfo> CREATOR = new Creator<SupplementaryCompanyInfo>() {
        @Override
        public SupplementaryCompanyInfo createFromParcel(Parcel in) {
            return new SupplementaryCompanyInfo(in);
        }

        @Override
        public SupplementaryCompanyInfo[] newArray(int size) {
            return new SupplementaryCompanyInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
