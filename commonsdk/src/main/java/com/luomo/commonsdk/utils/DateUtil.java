package com.luomo.commonsdk.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.utils
 * @date :2018-03-20 9:23
 * @description:
 */
public class DateUtil {
    public static final String PATTERN_MONTH_M_YYYY = "M/yyyy";
    public static final String PATTERN_MONTH_YYYY_MM = "yyyyMM";
    public static final String PATTERN_DAY_M_D = "M月d日";
    public static final String PATTERN_DAY_YYYYMMDD = "yyyyMMdd";
    public static final String PATTERN_YYYYMMDD_HHMMSS_S = "yyyy-MM-dd HH:mm:ss.S";
    public static final String PATTERN_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_HHMM = "HH:mm";
    public static final String PATTERN_DAY_YYYYMD = "yyyyMd";
    public static final String PATTERN_DAY_D = "d";
    private static SimpleDateFormat simpleDateFormatWithSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    private static SimpleDateFormat simpleDateFormatWithMonth = new SimpleDateFormat();
    private static SimpleDateFormat simpleDateFormatWithDay = new SimpleDateFormat();
    private static SimpleDateFormat simpleDateFormatWithSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取精确到月的时间
     *
     * @param time
     * @return
     */
    public static String getMonth(long time, String pattern) {
        if(time == 0){
            return "";
        }
        simpleDateFormatWithMonth.applyPattern(pattern);
        return simpleDateFormatWithMonth.format(time);
    }

    /**
     * 转换月的格式
     *
     * @param time
     * @return
     */
    public static String formatMonth(String time, String originalPattern, String destinationPattern) {
        if(TextUtils.isEmpty(time)){
            return "";
        }
        try {
            simpleDateFormatWithMonth.applyPattern(originalPattern);
            Date date = simpleDateFormatWithMonth.parse(time);
            simpleDateFormatWithMonth.applyPattern(destinationPattern);
            return simpleDateFormatWithMonth.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换月的格式
     *
     * @param time
     * @return
     */
    public static String monthAdd(String time, String pattern) {
        if(TextUtils.isEmpty(time)){
            return "";
        }
        try {
            simpleDateFormatWithMonth.applyPattern(pattern);
            Date date = simpleDateFormatWithMonth.parse(time);
            date.setMonth(date.getMonth() + 1);
            return simpleDateFormatWithMonth.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换月的格式
     *
     * @param time
     * @return
     */
    public static String monthMinus(String time, String pattern) {
        if(TextUtils.isEmpty(time)){
            return "";
        }
        try {
            simpleDateFormatWithMonth.applyPattern(pattern);
            Date date = simpleDateFormatWithMonth.parse(time);
            date.setMonth(date.getMonth() - 1);
            return simpleDateFormatWithMonth.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取精确到日的时间
     *
     * @param time
     * @return
     */
    public static String getDay(String time, String pattern) {
        if(TextUtils.isEmpty(time)){
            return "";
        }
        simpleDateFormatWithDay.applyPattern(pattern);
        return simpleDateFormatWithDay.format(time);
    }

    public static String getDay(String time) {
        if(TextUtils.isEmpty(time)){
            return "";
        }
        simpleDateFormatWithDay.applyPattern(PATTERN_DAY_D);
        return simpleDateFormatWithDay.format(time);
    }

    /**
     * 获取精确到日的时间
     *
     * @param time
     * @return
     */
    public static String getDay(long time, String pattern) {
        if(time == 0){
            return "";
        }
        simpleDateFormatWithDay.applyPattern(pattern);
        return simpleDateFormatWithDay.format(time);
    }

    /**
     * 转换月的格式
     *
     * @param time
     * @return
     */
    public static String formatDay(String time, String originalPattern, String destinationPattern) {
        if(TextUtils.isEmpty(time)){
            return "";
        }
        try {
            simpleDateFormatWithMonth.applyPattern(originalPattern);
            Date date = simpleDateFormatWithMonth.parse(time);
            simpleDateFormatWithMonth.applyPattern(destinationPattern);
            return simpleDateFormatWithMonth.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换月的格式
     * @param time
     * @return
     */
    public static String dayAdd(String time, String pattern){
        if(TextUtils.isEmpty(time)){
            return "";
        }
        try {
            simpleDateFormatWithMonth.applyPattern(pattern);
            Date date = simpleDateFormatWithMonth.parse(time);
            date.setDate(date.getDate() + 1);
            return simpleDateFormatWithMonth.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换月的格式
     * @param time
     * @return
     */
    public static String dayMinus(String time, String pattern){
        if(TextUtils.isEmpty(time)){
            return "";
        }
        try {
            simpleDateFormatWithMonth.applyPattern(pattern);
            Date date = simpleDateFormatWithMonth.parse(time);
            date.setDate(date.getDate() - 1);
            return simpleDateFormatWithMonth.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取日期
     *
     * @param time
     * @return
     */
    public static Date getDate(String time, String pattern) throws ParseException {
        if(TextUtils.isEmpty(time)){
            return new Date();
        }
        simpleDateFormatWithMonth.applyPattern(pattern);
        return simpleDateFormatWithMonth.parse(time);
    }

    /**
     * 获取精确到秒的时间
     *
     * @param time
     * @return
     */
    public static String getDateWithSS(String time) {
        try {
            return getDateWithSS(Long.parseLong(time));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取精确到秒的时间
     *
     * @param time
     * @return
     */
    public static String getDateWithSS(long time) {
        if(time == 0){
            return "";
        }
        try {
            return simpleDateFormatWithSS.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 比较两个月份
     * @param t1
     * @param t2
     * @param pattern
     * @return 0 相等，1 t1>t2, -1 t1<t2
     */
    public static int comparableMonth(String t1,String t2,String pattern) throws ParseException {
        simpleDateFormatWithMonth.applyPattern(pattern);
        Date date1 = simpleDateFormatWithMonth.parse(t1);
        Date date2 = simpleDateFormatWithMonth.parse(t2);
        if(date1.getTime() > date2.getTime()){
            return 1;
        } else if(date1.getTime() == date2.getTime()){
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 比较两个日期
     * @param t1
     * @param t2
     * @param pattern
     * @return 0 相等，1 t1>t2, -1 t1<t2
     */
    public static int comparableDay(String t1,String t2,String pattern) throws ParseException {
        simpleDateFormatWithMonth.applyPattern(pattern);
        Date date1 = simpleDateFormatWithDay.parse(t1);
        Date date2 = simpleDateFormatWithDay.parse(t2);
        if(date1.getTime() > date2.getTime()){
            return 1;
        } else if(date1.getTime() == date2.getTime()){
            return 0;
        } else {
            return -1;
        }
    }
}
