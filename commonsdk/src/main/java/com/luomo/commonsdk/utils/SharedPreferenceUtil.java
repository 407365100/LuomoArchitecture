package com.luomo.commonsdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SharedPreferenceUtil {
    /**
     * 存储一些基础数据
     */
    private static String SP_BASE = "sp_base";

    public static void clear(Context context){
        //清空其他配置信息
        SharedPreferences sp = context.getSharedPreferences(SP_BASE, Activity.MODE_PRIVATE);
        sp.edit().clear().commit();
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