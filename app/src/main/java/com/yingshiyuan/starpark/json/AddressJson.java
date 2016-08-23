package com.yingshiyuan.starpark.json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yingshiyuan.starpark.data.Address;
import com.yingshiyuan.starpark.http.HttpUrlUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:与用户地址有关
 * @author:袁东华 created at 2016/6/30 0030 下午 1:24
 */
public class AddressJson {
    /**
     * 解析用户的地址列表
     * @param jsonObject
     * @return 地址列表集合
     * @throws Exception
     */
    public ArrayList<Address> analyzeAddressList(JSONObject jsonObject) throws Exception {
        JSONObject info = jsonObject.getJSONObject("info");
        JSONArray addressList = info.getJSONArray("addressList");
        ArrayList<Address> list = new ArrayList<>();
        for (int i = 0; i < addressList.length(); i++) {
            JSONObject jsonObject1 = addressList.getJSONObject(i);
            String id = jsonObject1.optString("id");
            String name = jsonObject1.optString("name");
            String tel = jsonObject1.optString("tel");
            String province = jsonObject1.optString("province");
            String city = jsonObject1.optString("city");
            String district = jsonObject1.optString("district");
            String address = jsonObject1.optString("address");
            String first = jsonObject1.optString("first");
            Address address1 = new Address();
            address1.setId(id);
            address1.setName(name);
            address1.setPhone(tel);
            address1.setProvince(province);
            address1.setCity(city);
            address1.setDistrict(district);
            address1.setDetailAddress(address);
            address1.setDefaultValue(first);
            list.add(address1);
        }
        return list;
    }


    private static AddressJson instance;

    private AddressJson() {
    }

    public static AddressJson getInstance() {
        if (instance == null) {
            synchronized (AddressJson.class) {
                if (instance == null) {
                    instance = new AddressJson();
                }
            }
        }
        return instance;
    }
}
