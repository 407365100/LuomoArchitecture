package com.luomo.commonsdk;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.ezhantu.transportcompany.common
 * @date :2018/5/29 14:13
 * @description:
 */
public class SharedPreferenceParam {
    /**
     * 是否已经展示了用户指导页
     */
    public static final String PARAM_GUIDE_STATUS = "param_guide_status";
    /**
     * 是否已经登录
     */
    public static final String PARAM_IS_LOGIN = "param_is_login";
    /**
     * 之前登录的时间点
     */
    public static final String PARAM_PRE_LOGIN_TIME = "param_pre_login_time";
    /**
     * 用户类型
     */
    public static final String PARAM_USER_TYPE = "param_user_type";
    /**
     * 可用余额
     */
    public static final String PARAM_DEPOSIT = "param_deposit";
    /**
     * 消费总额
     */
    public static final String PARAM_MONTH_CONSUMPTION_MONEY = "param_month_consumption_money";
    /**
     * 转账总额
     */
    public static final String PARAM_MONTH_TRANSFER_MONEY = "param_month_transfer_money";
    /**
     * 支付密码
     */
    public static final String PARAM_PAY_PASSWORD = "param_pay_password";
    /**
     * 运输公司id
     */
    public static final String PARAM_TRANSPORTATION_ID = "param_transportation_id";
    /**
     * 运输公司名
     */
    public static final String PARAM_TRANSPORTATION_NAME = "param_transportation_name";
    /**
     * 运输公司状态
     */
    public static final String PARAM_TRANSPORTATION_STATUS = "param_transportation_status";
    /**
     * 转账时，最近联系人最多显示几个
     */
    public static final String PARAM_RECENT_TRANSFEROR_NUMBER = "param_recent_transferor_number";
    /**
     * 默认共享额度
     */
    public static final String PARAM_SHARED_MONEY_DEFAULT = "param_shared_money_default";
    /**
     * 默认共享类型
     */
    public static final String PARAM_SHARED_TYPE = "param_shared_type";
    /**
     * 用户id
     */
    public static final String PARAM_USER_ID = "param_user_id";
    public static final String PARAM_PHONE = "param_phone";
    /**
     * 登录状态有效期，默认为7天
     * 7*24*60*60*1000
     */
    public static final long LOGIN_VALID_DURING_TIME = 7*24*60*60*1000;
}
