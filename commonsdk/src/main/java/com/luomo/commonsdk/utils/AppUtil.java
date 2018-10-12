package com.luomo.commonsdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.library_base.utils
 * @date :2018/6/1 11:12
 * @description:
 */
public class AppUtil {

    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
