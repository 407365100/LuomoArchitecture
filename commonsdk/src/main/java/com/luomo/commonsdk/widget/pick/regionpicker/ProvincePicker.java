package com.luomo.commonsdk.widget.pick.regionpicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ezhantu.library_base.widget.pick.datepicker.WheelPicker;
import com.ezhantu.library_base.widget.pick.regionpicker.domain.RegionDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * 省份选择器
 * Created by ycuwq on 17-12-28.
 */
public class ProvincePicker extends WheelPicker<RegionDomain> {
    /**
     * 选中的省
     */
    RegionDomain selectedProvince;
    /**
     * 选中省份监听
     */
    private OnProvinceSelectedListener onProvinceSelectedListener;
    private List<RegionDomain> list = new ArrayList<RegionDomain>();

    public ProvincePicker(Context context) {
        this(context, null,0);
    }

    public ProvincePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProvincePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TYPE_DATA = 2;
        setOnWheelChangeListener(new OnWheelChangeListener<RegionDomain>() {
            @Override
            public void onWheelSelected(RegionDomain item, int position) {
                selectedProvince = item;
                if (onProvinceSelectedListener != null) {
                    onProvinceSelectedListener.onProvinceSelected(item);
                }
            }
        });
    }

    public void setDataSource(List<RegionDomain> list) {
        if(list == null){
            list = new ArrayList<RegionDomain>();
        }
        this.list.clear();
        this.list.addAll(list);
        setDataList(this.list);
        if(list.size() > 0) {
            selectedProvince = list.get(0);
        }
    }

    public OnProvinceSelectedListener getOnProvinceSelectedListener() {
        return onProvinceSelectedListener;
    }

    public void setOnProvinceSelectedListener(OnProvinceSelectedListener onProvinceSelectedListener) {
        this.onProvinceSelectedListener = onProvinceSelectedListener;
    }


    public void setSelectedProvince(RegionDomain selectedProvince) {
        setSelectedProvince(selectedProvince, true);
    }

    public void setSelectedProvince(RegionDomain selectedProvince, boolean smoothScroll) {
        setCurrentPosition(list.indexOf(selectedProvince), smoothScroll);
    }

    public RegionDomain getSelectedProvince() {
        return selectedProvince;
    }

    public interface OnProvinceSelectedListener {
        void onProvinceSelected(RegionDomain region);
    }
}
