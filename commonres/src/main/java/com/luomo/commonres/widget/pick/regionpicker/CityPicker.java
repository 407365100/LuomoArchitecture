package com.luomo.commonres.widget.pick.regionpicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.luomo.commonres.widget.pick.datepicker.WheelPicker;
import com.luomo.commonres.widget.pick.regionpicker.domain.RegionDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * 市选择器
 * Created by ycuwq on 17-12-28.
 */
public class CityPicker extends WheelPicker<RegionDomain> {
    RegionDomain selectedCity;
    private OnCitySelectedListener onCitySelectedListener;
    private List<RegionDomain> list= new ArrayList<RegionDomain>();

    public CityPicker(Context context) {
        this(context, null,0);
    }

    public CityPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CityPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TYPE_DATA = 2;
        setOnWheelChangeListener(new OnWheelChangeListener<RegionDomain>() {
            @Override
            public void onWheelSelected(RegionDomain item, int position) {
                selectedCity = item;
                if (onCitySelectedListener != null) {
                    onCitySelectedListener.onCitySelected(item);
                }
            }
        });
    }

    public void setSelectedCity(RegionDomain selectedCity) {
        setSelectedCity(selectedCity, true);
    }

    public void setSelectedCity(RegionDomain selectedCity, boolean smoothScroll) {
        setCurrentPosition(list.indexOf(selectedCity), smoothScroll);
    }

    public void setDataSource(List<RegionDomain> list) {
        if(list == null){
            list = new ArrayList<RegionDomain>();
        }
        this.list.clear();
        this.list.addAll(list);
        setDataList(this.list);
        if(list.size() > 0) {
            selectedCity = list.get(0);
        }
    }

    public OnCitySelectedListener getOnCitySelectedListener() {
        return onCitySelectedListener;
    }

    public void setOnCitySelectedListener(OnCitySelectedListener onCitySelectedListener) {
        this.onCitySelectedListener = onCitySelectedListener;
    }

    public RegionDomain getSelectedCity() {
        return selectedCity;
    }

    public interface OnCitySelectedListener {
        void onCitySelected(RegionDomain region);
    }
}
