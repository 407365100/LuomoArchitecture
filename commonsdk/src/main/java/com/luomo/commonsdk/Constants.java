package com.luomo.commonsdk;

import android.os.Environment;

import java.io.File;

/*******************************************
 * @COMPANY:落寞
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.commonsdk
 * @date :2018/10/12 15:10
 * @description:
 *******************************************/
public interface Constants {
    /**
     * 请求码
     */
    public static final int REQUEST_CODE_0x01 = 0x01,
            REQUEST_CODE_0x02 = 0x02,
            REQUEST_CODE_0x03 = 0x03;
    /**
     * default int value
     */
    public static final int DEFAULT_INT = 0x00;
    /**
     * default string value
     */
    public static final String DEFAULT_STRING_NULL = "";


    /**
     * 应用的缓存信息存储路径
     */
    public final static String PATH_DIRECTORY = Environment.getExternalStorageDirectory().getPath() + File.separator +"ezhantu";
    /**
     * 日志输出路径
     */
    public final static String PATH_DIRECTORY_LOG = PATH_DIRECTORY + File.separator + "log";
    /**
     * apk下载路径
     */
    public final static String PATH_DIRECTORY_APK = PATH_DIRECTORY + File.separator + "apk";
    /**
     * 图片缓存输出路径
     */
    public final static String PATH_DIRECTORY_IMAGE = PATH_DIRECTORY + File.separator + "imageLoader"+ File.separator+"Cache";
}
