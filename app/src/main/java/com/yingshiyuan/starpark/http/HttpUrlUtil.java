package com.yingshiyuan.starpark.http;

/**
 * 存放接口数据
 */

public class HttpUrlUtil {

    /**
     * 域名
     */
    public static final String SERVER = "http://see.dressbook.cn";
    /**
     * 登陆
     */
    public static final String LOGIN = SERVER + "/userLoginWt.json";
    /**
     * 注册
     */
    public static final String REGIST = SERVER + "/userRegWt.json";
    /**
     * 修改密码
     */
    public static final String MIDIFY_PASSWORD = SERVER + "/pwdResetWt.json";
    /**
     * 修改手机号
     */
    public static final String MIDIFY_PHONE = SERVER + "/contactWayUpdWt.json";
    /**
     * 修改邮箱
     */
    public static final String MIDIFY_EMAIL = SERVER + "/contactWayUpdWt.json";
    /**
     * 新增地址
     */
    public static final String ADD_ADDRESS = SERVER + "/addressUpdWt.json";
    /**
     * 获取用户地址列表
     */
    public static final String GET_ADDRESS_LIST = SERVER + "/addressList.json";
    /**
     * 删除用户地址
     */
    public static final String DELETE_ADDRESS = SERVER + "/addressDelWt.json";

}
