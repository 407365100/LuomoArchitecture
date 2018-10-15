package com.luomo.commonsdk.utils;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.transportcompany.utils
 * @date :2018/6/6 8:51
 * @description:
 */
public class CommonUtil {
    static String TAG = CommonUtil.class.getSimpleName();

    /**
     * 打电话
     *
     * @param context
     */
    public static void callPhone(final Context context, final String call) {
        if (TextUtils.isEmpty(call)) {
            //ToastUtil.showToast(context, "号码不能为空");
            return;
        }
        // 如果你不想申请权限组，仅仅想申请某几个权限：
        AndPermission.with(context)
                .requestCode(100)
                .permission(
                        Manifest.permission.CALL_PHONE
                )
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        LogUtil.i(TAG, "showRequestPermissionRationale");
                        // 此对话框可以自定义，调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(context, rationale).show();
                    }
                })
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        LogUtil.i(TAG, "requestPermission onSucceed," + grantPermissions.toString());
                        //创建打电话的意图
                        Intent intent = new Intent();
                        // 设置拨打电话的动作
                        intent.setAction(Intent.ACTION_CALL);
                        // 设置拨打电话的号码
                        intent.setData(Uri.parse("tel:" + call));
                        // 开启打电话的意图
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        LogUtil.i(TAG, "requestPermission onFailed," + deniedPermissions.toString());
                    }
                })
                .start();
    }

    /**
     * 调用第三方浏览器打开
     *
     * @param context
     * @param url     要浏览的资源地址
     */
    public static void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            // 打印Log   ComponentName到底是什么
            LogUtil.i("componentName = " + componentName.getClassName());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            ToastUtil.showToast(context.getApplicationContext(), "请下载浏览器");
        }
    }

    /**
     * 发送邮件
     * @param c
     * @param mailAdress
     */
    public static void sendEmail(Context c, String mailAdress) {
        Uri uri = Uri.parse("mailto:" + mailAdress);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
        // intent.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
        // intent.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
        c.startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
    }

}
