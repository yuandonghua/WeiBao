package com.yingshiyuan.starpark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 地址
 */
public class Address implements Parcelable {

	//地址的id
	private String id;
	//收件人
	private String name;
	//国家
	private String country;
	//省份
	private String province;
	//城市
	private String city;
	//地区(大兴区,丰台区,海淀区)
	private String district;
	//详细地址(哪个小区哪栋楼)
	private String detailAddress;
	//手机号
	private String phone;
	private String defaultValue="false";
	public Address() {

	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	private Address(Parcel in) {
		id = in.readString();
		name= in.readString();
		country = in.readString();
		province = in.readString();
		city = in.readString();
		district = in.readString();
		detailAddress = in.readString();
		phone = in.readString();
		defaultValue=in.readString();
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(id);
		out.writeString(name);
		out.writeString(country);
		out.writeString(province);
		out.writeString(city);
		out.writeString(district);
		out.writeString(detailAddress);
		out.writeString(phone);
		out.writeString(defaultValue);
	}

	public static final Creator<Address> CREATOR = new Creator<Address>() {
		@Override
		public Address createFromParcel(Parcel in) {
			return new Address(in);
		}

		@Override
		public Address[] newArray(int size) {
			return new Address[size];
		}
	};

	public Address(String name, String country, String province, String city, String district, String detailAddress, String phone) {
		this.name = name;
		this.country = country;
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddress = detailAddress;
		this.phone = phone;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static Creator<Address> getCREATOR() {
		return CREATOR;
	}
}
