package com.luomo.commonsdk.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.tools
 * @date :2018/5/8 11:15
 * @description:
 */
public class NumberUtil {

    /**
     * 格式化金钱，有小数则显示小数后两位，没有小数则显示整数
     * @param money
     * @return
     */
    public static String formatMoney(double money){
        if(money == (int)money) {
            return String.format("%.0f",money);
        } else {
            return String.format("%.2f",money);
        }
    }
    public static String formatMoneyWithPrefix(double money){
        return "￥" + formatMoney(money);
    }

    public static float parseFloat(String number){
        float result = 0;
        try {
            result = Float.parseFloat(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static double parseDouble(String number){
        double result = 0;
        try {
            if(TextUtils.isEmpty(number)){
                return result;
            }
            result = Double.parseDouble(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int parseInteger(String number){
        int result = 0;
        try {
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static float double2Float(double number){
        float result = 0;
        try {
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
            result = bigDecimal.floatValue();
            //result = Float.valueOf(String.valueOf(number));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }
}
