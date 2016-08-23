package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description:公司法人信息
 * @author:袁东华 created at 2016/7/21 0021 下午 12:39
 */
public class JuridicalPerson implements Parcelable {
    //法人姓名
    private String name;
    //法人身份证号
    private String idno;
    //身份证正面照
    private String idPicFront;
    //身份证反面照
    private String idPicBack;

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

    public String getIdPicFront() {
        return idPicFront;
    }

    public void setIdPicFront(String idPicFront) {
        this.idPicFront = idPicFront;
    }

    public String getIdPicBack() {
        return idPicBack;
    }

    public void setIdPicBack(String idPicBack) {
        this.idPicBack = idPicBack;
    }

    public JuridicalPerson() {
    }

    protected JuridicalPerson(Parcel in) {
        name = in.readString();
        idno = in.readString();
        idPicFront = in.readString();
        idPicBack = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(idno);
        dest.writeString(idPicFront);
        dest.writeString(idPicBack);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JuridicalPerson> CREATOR = new Creator<JuridicalPerson>() {
        @Override
        public JuridicalPerson createFromParcel(Parcel in) {
            return new JuridicalPerson(in);
        }

        @Override
        public JuridicalPerson[] newArray(int size) {
            return new JuridicalPerson[size];
        }
    };
}
