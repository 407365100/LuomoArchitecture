package com.luomo.commonsdk.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/*******************************************
 * @COMPANY:落寞
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.commonsdk.base
 * @date :2018/10/12 15:32
 * @description:
 *******************************************/
public class BaseActivity extends Activity{
    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }
}
