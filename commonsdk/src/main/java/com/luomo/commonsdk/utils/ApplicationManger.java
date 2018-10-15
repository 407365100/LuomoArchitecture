package com.luomo.commonsdk.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;


import com.luomo.commonsdk.base.BaseActivity;
import com.luomo.commonsdk.widget.interfacewrap.ActivityLifecycleCallbacksWrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.library_base.utils
 * @date :2018/5/29 10:32
 * @description:
 */
public class ApplicationManger {
    public final Context applicationContext;
    private Application app;
    private HashMap<String, ArrayList<BaseActivity>> mBufferActivity;

    public ApplicationManger(Application app) {
        this.app = app;
        applicationContext = this.app.getApplicationContext();
    }

    /**
     * 管理activity
     */
    public void loadManagerActivity() {
        mBufferActivity = new HashMap<String, ArrayList<BaseActivity>>();
        app.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksWrap(){
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);
                ArrayList<BaseActivity> activities = null;
                String simpleName = activity.getClass().getSimpleName();
                //是否已经存储过
                if(mBufferActivity.containsKey(simpleName)){//已经存储过
                    activities = mBufferActivity.get(simpleName);
                } else {//未存储过
                    activities = new ArrayList<BaseActivity>();
                }
                //管理这个activity
                if(activity instanceof BaseActivity) {
                    activities.add((BaseActivity)activity);
                    mBufferActivity.put(simpleName, activities);
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                super.onActivityDestroyed(activity);
                String simpleName = activity.getClass().getSimpleName();
                ArrayList<BaseActivity> activities = mBufferActivity.get(simpleName);
                //如果当前activity存在，则移除对当前activity的管理
                if (activities != null) {
                    activities.remove(activity);
                    if (activities.size() < 1) {
                        mBufferActivity.remove(simpleName);
                    }
                }
            }
        });
    }


    /**
     * 退出app
     */
    public void exitApp() {
        finishAllActivity();
        System.exit(0);
    }


    /**
     * 退出app
     */
    private void finishAllActivity() {
        Iterator<Map.Entry<String, ArrayList<BaseActivity>>> iterator = mBufferActivity.entrySet().iterator();
        while (iterator.hasNext()) {
            ArrayList<BaseActivity> activities = iterator.next().getValue();
            if (activities != null) {
                for (BaseActivity basicActivity : activities) {
                    if (basicActivity != null) {
                        basicActivity.finish();
                    }
                }
            }
        }
        mBufferActivity.clear();
    }
}
