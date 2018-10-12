package com.luomo.commonsdk.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.deviceapp1
 * @date :2018-05-06 16:44
 * @description:
 */
public class DeviceUtil {
    private static String TAG = "DeviceUtil";
    /**
     * 默认值
     */
    private static String EMPTY = "";
    /*
        android.os.Build.BOARD：获取设备基板名称
        android.os.Build.BOOTLOADER:获取设备引导程序版本号
        android.os.Build.BRAND：获取设备品牌
        android.os.Build.CPU_ABI：获取设备指令集名称（CPU的类型）
        android.os.Build.CPU_ABI2：获取第二个指令集名称
        android.os.Build.DEVICE：获取设备驱动名称
        android.os.Build.DISPLAY：获取设备显示的版本包（在系统设置中显示为版本号）和ID一样
        android.os.Build.FINGERPRINT：设备的唯一标识。由设备的多个信息拼接合成。
        android.os.Build.HARDWARE：设备硬件名称,一般和基板名称一样（BOARD）
        android.os.Build.HOST：设备主机地址
        android.os.Build.ID:设备版本号。
        android.os.Build.MODEL ：获取手机的型号 设备名称。
        android.os.Build.MANUFACTURER:获取设备制造商
        android:os.Build.PRODUCT：整个产品的名称
        android:os.Build.RADIO：无线电固件版本号，通常是不可用的 显示unknown
        android.os.Build.TAGS：设备标签。如release-keys 或测试的 test-keys
        android.os.Build.TIME：时间
        android.os.Build.TYPE:设备版本类型  主要为"user" 或"eng".
        android.os.Build.USER:设备用户名 基本上都为android-build
        android.os.Build.VERSION.RELEASE：获取系统版本字符串。如4.1.2 或2.2 或2.3等
        android.os.Build.VERSION.CODENAME：设备当前的系统开发代号，一般使用REL代替
        android.os.Build.VERSION.INCREMENTAL：系统源代码控制值，一个数字或者git hash值
        android.os.Build.VERSION.SDK：系统的API级别 一般使用下面大的SDK_INT 来查看
        android.os.Build.VERSION.SDK_INT：系统的API级别 数字表示
        */

    /**
     * 获取设备唯一id
     * 概念： 是区别移动设备的标志，储存在移动设备中，可用于监控被窃或无效的移动设备。
     * 优点：
     * 1.根据不同的手机设备返回IMEI，MEID或者ESN码，唯一性良好 。
     * <p>
     * 不足：
     * 1.非手机：如平板电脑，像这样设备没有通话的硬件功能，系统中也就没有TELEPHONY_SERVICE，自然也就无法获得DEVICE_ID;
     * 2.权限问题：获取DEVICE_ID需要READ_PHONE_STATE权限；
     * 3.厂商定制系统中的Bug：少数手机设备上，由于该实现有漏洞，会返回垃圾，如:00000000或者****
     *
     * @param context
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return EMPTY;
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            String deviceId = tm.getDeviceId();
            Log.i(TAG, "getDeviceId = " + deviceId);
            return deviceId;
        }
        return "";
    }

    /**
     * 获取设备wifi mac
     * 概念：可以使用手机Wifi或蓝牙的MAC地址作为设备标识
     * 不足：
     * 1.如果设备没有支持WIFI的硬件，就返回null；
     * 2.如果设备没有支持蓝牙的硬件，就返回null。
     *
     * @param context
     */
    public static String getWifiMac(Context context) {// wifi mac地址
        //在Android 6.0系统上，这个方法失效了，返回了”02:00:00:00:00:00”的常量
        /*WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        if (TextUtils.isEmpty(wifiMac)) {
            wifiMac = "";
        }
        Log.i(TAG, "getDeviceId = " + wifiMac);*/
        /*
        问题：
        1.如果重启手机后，Wifi没有打开过，是无法获取其Mac地址的。（可以考虑授予CHANGE_WIFI_STATE权限，开关一次wifi刷一下。）
        2.有一些定制系统的目录并不一样。 例如三星的目录为"cat /sys/class/net/eth0/address"，所以是否对所以机型都有效有待验证。（需要适配）
        3.网上也有反映mac变更问题，是不是刷mac或者wifi故障导致，也不确定。
        4.并不是所有的设备都有Wifi硬件，硬件不存在自然也就得不到这一信息。（这个还好）
        5.需要 ACCESS_WIFI_STATE 权限。（这个还好）
        */
        byte[] wifiMac = new byte[0];
        try {
            NetworkInterface networkInterface = NetworkInterface.getByName("wlan0");
            if (networkInterface != null) {
                wifiMac = networkInterface.getHardwareAddress();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return wifiMac.toString();
    }

    /**
     * 获取设备sim序列号
     * 概念：SIM卡的序列号。
     * 不足：
     * 1.没有装Sim卡时，返回null；
     * 2.对于CDMA设备，返回null。
     * 在少数的一些设备上，会返回垃圾数据。对于没有通话功能的设备，它可能会返回一个固定的值
     *
     * @param context
     * @return
     */
    public static String getSimSerialNumber(Context context) {
        // 序列号（sn）
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return EMPTY;
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            String sn = tm.getSimSerialNumber();
            if (TextUtils.isEmpty(sn)) {
                sn = EMPTY;
            }
            return sn;
        }
        return "";
    }

    /**
     * 获取设备序列号
     * 概念：Android系统2.3版本以上可以获取硬件Serial Number。
     * 优点：非手机设备也可以通过该接口获取ID。
     * 对CDMA设备，返回的是一个空值
     *
     * @param context
     * @return
     */
    public static String getSerialNumber(Context context) {
        return android.os.Build.SERIAL;
    }

    /**
     * 概念：当设备首次启动时，系统会随机生成一个64位的数字，并把这个数字以16进制字符串的形式保存下来。
     * 不足：
     * 1.它在Android <=2.1 or Android >=2.3的版本是可靠、稳定的，但在2.2的版本并不是100%可靠的；
     * 2.在主流厂商生产的设备上，有一个很经常的bug，就是每个设备都会产生相同的ANDROID_ID。
     * 当设备被恢复出厂设置后该值会被重置
     * <p>
     * 厂商定制系统的Bug：不同的设备可能会产生相同的ANDROID_ID：9774d56d682e549c。(摩托罗拉好像出现过这个问题)
     * 厂商定制系统的Bug：有些设备返回的值为null。
     * 设备差异：对于CDMA设备，ANDROID_ID和TelephonyManager.getDeviceId() 返回相同的值。
     * 并且，如果某个Andorid手机被Root过的话，这个ID也可以被改变。
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 根据IP地址获取MAC地址
     *
     * @return
     */
    public static String getLocalMacAddressFromIp() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getLocalInetAddress();
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {

        }
        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return
     */
    public static InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            //列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                NetworkInterface ni = (NetworkInterface) en_netInterface.nextElement();//得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }
                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 通过网络接口取   * @return
     */
    public static String getNewMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
