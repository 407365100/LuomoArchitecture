package com.luomo.commonsdk.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.library_base.utils
 * @date :2018/6/6 11:54
 * @description:验证工具类
 */
public class VerificationUtil {

    /**
     * 描述：手机号格式验证.
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格式:是为true，否则false
     */
    public static Boolean isMobileNumber(String str) {
        Boolean isMobileNo = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(str);
            isMobileNo = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMobileNo;
    }

    /**
     * 验证身份证号码
     *
     * @param str
     * @return
     */
    public static Boolean isIDCardNum(String str) {
        if (str.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 第一：普通汽车
     * 车牌号格式：汉字 + A-Z + 5位A-Z或0-9(  车牌号不存在字母I和O防止和1、0混淆)
     * （只包括了普通车牌号，教练车，警等车牌号 。部分部队车，新能源不包括在内）
     * 京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼军空海北沈兰济南广成使领
     * 普通汽车规则："[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1}"
     * <p>
     * 第二：新能源车
     * 组成：省份简称（1位汉字）+发牌机关代号（1位字母）+序号（6位），总计8个字符，序号不能出现字母I和字母O
     * 通用规则：不区分大小写，第一位：省份简称（1位汉字），第二位：发牌机关代号（1位字母）
     * 序号位：
     * 小型车，第一位：只能用字母D或字母F，第二位：字母或者数字，后四位：必须使用数字
     * ---([DF][A-HJ-NP-Z0-9][0-9]{4})
     * 大型车，前五位：必须使用数字，第六位：只能用字母D或字母F。
     * ----([0-9]{5}[DF])
     * 新能源车规则："[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF][A-HJ-NP-Z0-9][0-9]{4}))"
     * <p>
     * 总规则："([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})"
     */
    public static boolean isCarNumberNO(String carNumber) {
        String carNumberRegex = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})";
        if (TextUtils.isEmpty(carNumber)) {
            return false;
        } else {
            return carNumber.matches(carNumberRegex);
        }
    }
}
