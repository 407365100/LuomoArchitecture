package com.luomo.commonres.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
  * @author :renpan
  * @version :v1.0
  * @class :{@link GridViewWrap}
  * @date :2018-03-02 15:15
  * @description:
  */
public class GridViewWrap extends android.widget.GridView{
    public GridViewWrap(Context context) {
        super(context);
    }

    public GridViewWrap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewWrap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
