package com.luomo.commonsdk.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.ezhantu.library_base.R;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.ezhantu.library_base.widget.dialog
 * @date :2018/6/5 10:59
 * @description:
 */
public class CustomProgressDialog extends ProgressDialog {
    public static final int TYPE_LOADING = 1;
    public static final int TYPE_REQUESTING = 2;
    int type = TYPE_LOADING;
    public CustomProgressDialog(Context context) {
        super(context, R.style.lib_base_CustomProgressDialog);
    }

    public CustomProgressDialog(Context context, int type) {
        this(context);
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.dialog_progress);
        TextView tvProgress = findViewById(R.id.tv_load_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        if (type == TYPE_LOADING) {
            tvProgress.setText(context.getString(R.string.lib_base_loading));
        } else if(type == TYPE_REQUESTING){
            tvProgress.setText(context.getString(R.string.lib_base_requesting));
        }
    }
}
