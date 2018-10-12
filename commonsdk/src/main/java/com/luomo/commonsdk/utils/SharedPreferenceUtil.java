package com.luomo.commonsdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.luomo.library_base.common.SharedPreferenceParam;

public class SharedPreferenceUtil {
    /**
     * 存储一些基础数据
     */
    private static String SP_BASE = "sp_base";

    public static void clear(Context context){
        //1.保留全局的配置
        boolean guideStatus = getBaseBoolean(context, SharedPreferenceParam.PARAM_GUIDE_STATUS);//用户引导页
        //2.清空其他配置信息
        SharedPreferences sp = context.getSharedPreferences(SP_BASE, Activity.MODE_PRIVATE);
        sp.edit().clear().commit();
        //3.还原系统配置
        putBaseBoolean(context, SharedPreferenceParam.PARAM_GUIDE_STATUS, guideStatus);
    }

    public static String getBaseString(Context context, String name) {
        return getBaseString(context, name, StringUtil.nul);
    }

    public static String getBaseString(Context context, String name, String defaultValue) {
        return getString(context, SP_BASE, name, StringUtil.nul);
    }

    public static int getBaseInt(Context context, String name) {
        return getBaseInt(context, name, 0);
    }

    public static int getBaseInt(Context context, String name, int defaultValue) {
        return getInt(context, SP_BASE, name, defaultValue);
    }

    public static float getBaseFloat(Context context, String name) {
        return getBaseFloat(context, name, 0);
    }

    public static float getBaseFloat(Context context, String name, int defaultValue) {
        return getFloat(context, SP_BASE, name, defaultValue);
    }

    public static long getBaseLong(Context context, String name) {
        return getBaseLong(context, name, 0);
    }

    public static long getBaseLong(Context context, String name, int defaultValue) {
        return getLong(context, SP_BASE, name, defaultValue);
    }

    public static boolean getBaseBoolean(Context context, String name) {
        return getBaseBoolean(context, name, false);
    }

    public static boolean getBaseBoolean(Context context, String name, boolean defaultValue) {
        return getBoolean(context, SP_BASE, name, defaultValue);
    }

    public static void putBaseString(Context context, String name, String value) {
        putString(context, SP_BASE, name, value);
    }

    public static void putBasesInt(Context context, String name, int value) {
        putInt(context, SP_BASE, name, value);
    }

    public static void putBasesFloat(Context context, String name, float value) {
        putFloat(context, SP_BASE, name, value);
    }

    public static void putBaseBoolean(Context context, String name, boolean value) {
        putBoolean(context, SP_BASE, name, value);
    }

    public static void putBaseLong(Context context, String name, long value) {
        putLong(context, SP_BASE, name, value);
    }

    /**********基本操作*********/
    private static String getString(Context context, String fileName, String name, String defaultValue) {
        if (context == null) {
            return StringUtil.nul;
        }
        try {
            SharedPreferences sp = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
            return sp.getString(name, defaultValue);
        } catch (Exception e) {
            return StringUtil.nul;
        }
    }

    private static int getInt(Context context, String fileName, String name, int defaultValue) {
        if (context == null) {
            return 0;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        return share.getInt(name, defaultValue);
    }

    private static float getFloat(Context context, String fileName, String name, int defaultValue) {
        if (context == null) {
            return 0;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        return share.getFloat(name, defaultValue);
    }

    private static long getLong(Context context, String fileName, String name, int defaultValue) {
        if (context == null) {
            return 0;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        return share.getLong(name, defaultValue);
    }

    private static boolean getBoolean(Context context, String fileName, String name, boolean defaultValue) {
        if (context == null) {
            return false;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        return share.getBoolean(name, defaultValue);
    }

    private static void putString(Context context, String fileName, String name, String value) {
        if (context == null) {
            return;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putString(name, value);
        editor.commit();
    }

    private static void putInt(Context context, String fileName, String name, int value) {
        if (context == null) {
            return;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    private static void putFloat(Context context, String fileName, String name, float value) {
        if (context == null) {
            return;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putFloat(name, value);
        editor.commit();
    }

    private static void putLong(Context context, String fileName, String name, long value) {
        if (context == null) {
            return;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putLong(name, value);
        editor.commit();
    }

    private static void putBoolean(Context context, String fileName, String name, boolean value) {
        if (context == null) {
            return;
        }
        SharedPreferences share = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }
}