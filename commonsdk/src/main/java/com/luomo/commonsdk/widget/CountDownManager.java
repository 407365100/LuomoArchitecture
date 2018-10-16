package com.luomo.commonsdk.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.luomo.commonsdk.base.BaseClass;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.commonsdk.utils
 * @date :2018/6/6 14:47
 * @description:倒计时工具
 */
public class CountDownManager extends BaseClass{
    private static CountDownManager countDownManager = null;
    private static Context mContext;

    OnChangeListener mOnChangeListener;
    private Timer mTimer = new Timer(true);
    private TimerTask mTimerTask;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 倒计时长,单位s
     */
    private int TOTAL_SECONDS = 60;
    private int currentSecond = TOTAL_SECONDS;

    private CountDownManager() {
    }

    public static CountDownManager getInstance(Context context) {
        if (countDownManager == null || mContext != context) {
            mContext = context;
            countDownManager = new CountDownManager();
        }
        return countDownManager;
    }

    /**
     * 开始倒计时
     *
     * @param onChangeListener
     */
    public CountDownManager startCountDown(OnChangeListener onChangeListener) {
        mOnChangeListener = onChangeListener;
        currentSecond = TOTAL_SECONDS;
        startTimer();
        return this;
    }

    /**
     * 开始倒计时
     */
    private void startTimer() {
        if (mTimer != null) {
            if (mTimerTask != null) {
                mTimerTask.cancel(); // 将原任务从队列中移除
            }
            mTimer = new Timer(true);
            mTimerTask = new TimerTask() {
                public void run() {
                    if (mOnChangeListener != null) {
                        //主线程执行
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (currentSecond == TOTAL_SECONDS) {
                                    mOnChangeListener.onStart();
                                    currentSecond--;
                                } else if (currentSecond == 0) {
                                    mOnChangeListener.onFinish();
                                } else {
                                    mOnChangeListener.onChange(TOTAL_SECONDS, currentSecond);
                                    currentSecond--;
                                }
                            }
                        });
                    }
                }
            };
            mTimer.schedule(mTimerTask, 0, 1000); // 延时1000ms后执行，1000ms执行一次
        }
    }

    /**
     * 清除timer
     */
    public void clear() {
        if (mTimer != null) {
            if (mTimerTask != null) {
                mTimerTask.cancel(); // 将原任务从队列中移除
                mTimer.cancel();
            }
        }
    }

    public CountDownManager setCurrentSecond(int currentSecond) {
        this.currentSecond = currentSecond;
        return this;
    }

    public int getTotalSeconds() {
        return TOTAL_SECONDS;
    }

    public CountDownManager setTotalSeconds(int countTime) {
        this.TOTAL_SECONDS = countTime;
        return this;
    }

    public interface OnChangeListener {
        void onStart();

        /**
         * @param totalSeconds  总秒数，如60
         * @param currentSecond 剩余秒数，如49，
         */
        void onChange(int totalSeconds, int currentSecond);

        void onFinish();
    }
}
