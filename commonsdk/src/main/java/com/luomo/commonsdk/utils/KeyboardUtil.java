package com.luomo.commonsdk.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.tools
 * @date :2018/5/7 19:17
 * @description:
 */
public class KeyboardUtil {
    /**
     * 动态打开软键盘
     *
     * @param mEditText
     * @param context
     */
    public static void openKeyboard(Context context, EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
    }

    /**
     * 动态关闭软键盘
     *
     * @param editText
     * @param context
     */
    public static void closeKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * activity默认不显示软键盘，可通过点击输入框弹出
     *
     * @param window
     */
    public static void activityHiddenKeyboard(Window window) {
        if(window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }

    /**
     * activity默认显示软键盘
     *
     * @param window
     */
    public static void activityShowKeyboard(Window window) {
        if(window != null) {
            //android:windowSoftInputMode="stateVisible|adjustResize
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    /**
     * 判断当前软键盘是否打开
     *
     * @param activity
     * @return true 打开状态
     */
    public static boolean isSoftInputShow(Activity activity) {
        // 虚拟键盘隐藏 判断view是否为空
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            // 隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//       inputmanger.hideSoftInputFromWindow(view.getWindowToken(),0);
            return inputmanger.isActive() && activity.getWindow().getCurrentFocus() != null;
        }
        return false;
    }



    /**
     * 动态隐藏软键盘
     *
     * @param view 视图
     */
    public static void hideSoftInput(Context context, final View view) {
        InputMethodManager imm =(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
