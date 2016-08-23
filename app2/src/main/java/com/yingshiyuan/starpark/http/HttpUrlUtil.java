package com.yingshiyuan.starpark.http;
/**
 *@description:url数据
 *@author:袁东华
 *created at 2016/7/20 0020 上午 11:09
 */
public class HttpUrlUtil {
    //本地测试
//    public static final String SERVER = "http://192.168.1.104:8085";
    //域名
    public static final String SERVER = "http://see.dressbook.cn";
    //图片地址
    public static final String SERVER_IMAGE = "http://sdt.dressbook.cn";
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
    //公司设立
    public static final String CREATE_COMPANY = SERVER + "/compUpdWt.json";
    //获取公司信息
    public static final String GET_COMPANY_INFO = SERVER + "/compInfoGet.json";
    //获取管家消息列表
    public static final String GET_MESSAGE_LIST = SERVER + "/busMsgList.json";
    //补充公司股东信息
    public static final String SUPPLEMENTARY_SHAREHOLDER = SERVER + "/compHolderAndCompManSaveWt.json";
    //完成任务
    public static final String FINISH_TASK = SERVER + "/actTaskDeal.json";
}
