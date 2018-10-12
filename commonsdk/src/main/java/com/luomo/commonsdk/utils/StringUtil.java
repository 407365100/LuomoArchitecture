package com.luomo.commonsdk.utils;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.tools
 * @date :2018/5/4 17:11
 * @description:
 */
public class StringUtil {
    public static String nul = "";

    /**
     * 获取最大的字符串
     *
     * @param sts
     * @return
     */
    public static String maxString(String[] sts) {
        String maxSt = null;
        for (int i = 0; i < sts.length - 1; i++) {
            if (TextUtils.isEmpty(sts[i])) {
                maxSt = sts[i + 1];
            } else if (sts[i].compareTo(sts[i + 1]) > 0) {
                maxSt = sts[i];
            } else {
                maxSt = sts[i + 1];
            }
        }
        return maxSt;
    }

    public static void main(String[] args) {
        String[] sts = {"1", "2", "3", "4", "5", "6", "7"};
        List<String> strings = Arrays.asList(sts);

        System.out.println(strings.subList(2, 4));
    }

    public static String unicode(String source) {
        StringBuffer sb = new StringBuffer();
        char[] source_char = source.toCharArray();
        String unicode = null;
        for (int i = 0; i < source_char.length; i++) {
            unicode = Integer.toHexString(source_char[i]);
            if (unicode.length() <= 2) {
                unicode = "00" + unicode;
            }
            sb.append("\\u" + unicode);
        }
        System.out.println(sb);
        return sb.toString();
    }

    public static String decodeUnicode(String unicode) {
        StringBuffer sb = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            sb.append((char) data);
        }
        return sb.toString();
    }
}