package com.luomo.commonsdk.widget.pick.regionpicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.ezhantu.library_base.R;
import com.ezhantu.library_base.widget.pick.regionpicker.domain.RegionDomain;

import java.util.List;

/**
 * 省市区选择器
 * Created by ycuwq on 2018/1/1.
 */
@SuppressWarnings("unused")
public class RegionPicker extends LinearLayout{

	private ProvincePicker provincePicker;
	private CityPicker cityPicker;


	private OnRegionSelectedListener onRegionSelectedListener;

	/**
	 * Instantiates a new Date picker.
	 *
	 * @param context the context
	 */
	public RegionPicker(Context context) {
		this(context, null);
	}

	/**
	 * Instantiates a new Date picker.
	 *
	 * @param context the context
	 * @param attrs   the attrs
	 */
	public RegionPicker(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * Instantiates a new Date picker.
	 *
	 * @param context      the context
	 * @param attrs        the attrs
	 * @param defStyleAttr the def style attr
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public RegionPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		LayoutInflater.from(context).inflate(R.layout.layout_region, this);
		initChild();
		initAttrs(context, attrs);
	}

	private void initAttrs(Context context, @Nullable AttributeSet attrs) {
		if (attrs == null) {
			return;
		}
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePicker);
		int textSize = a.getDimensionPixelSize(R.styleable.DatePicker_itemTextSize,getResources().getDimensionPixelSize(R.dimen.WheelItemTextSize));
		int textColor = a.getColor(R.styleable.DatePicker_itemTextColor,Color.BLACK);
		boolean isTextGradual = a.getBoolean(R.styleable.DatePicker_textGradual, true);
		boolean isCyclic = a.getBoolean(R.styleable.DatePicker_wheelCyclic, false);
		int halfVisibleItemCount = a.getInteger(R.styleable.DatePicker_halfVisibleItemCount, 2);
		int selectedItemTextColor = a.getColor(R.styleable.DatePicker_selectedTextColor,getResources().getColor(R.color.lib_base_datepicker_selectedTextColor));
		int selectedItemTextSize = a.getDimensionPixelSize(R.styleable.DatePicker_selectedTextSize,getResources().getDimensionPixelSize(R.dimen.WheelSelectedItemTextSize));
		int itemWidthSpace = a.getDimensionPixelSize(R.styleable.DatePicker_itemWidthSpace,getResources().getDimensionPixelOffset(R.dimen.WheelItemWidthSpace));
		int itemHeightSpace = a.getDimensionPixelSize(R.styleable.DatePicker_itemHeightSpace,getResources().getDimensionPixelOffset(R.dimen.WheelItemHeightSpace));
		boolean isZoomInSelectedItem = a.getBoolean(R.styleable.DatePicker_zoomInSelectedItem, true);
		boolean isShowCurtain = a.getBoolean(R.styleable.DatePicker_wheelCurtain, true);
		int curtainColor = a.getColor(R.styleable.DatePicker_wheelCurtainColor, Color.WHITE);
		boolean isShowCurtainBorder = a.getBoolean(R.styleable.DatePicker_wheelCurtainBorder, false);
		int curtainBorderColor = a.getColor(R.styleable.DatePicker_wheelCurtainBorderColor,getResources().getColor(R.color.lib_base_datepicker_divider));
		a.recycle();

		setTextSize(textSize);
		setTextColor(textColor);
		setTextGradual(isTextGradual);
		setCyclic(isCyclic);
		setHalfVisibleItemCount(halfVisibleItemCount);
		setSelectedItemTextColor(selectedItemTextColor);
		setSelectedItemTextSize(selectedItemTextSize);
		setItemWidthSpace(itemWidthSpace);
		setItemHeightSpace(itemHeightSpace);
		setZoomInSelectedItem(isZoomInSelectedItem);
		setShowCurtain(isShowCurtain);
		setCurtainColor(curtainColor);
		setShowCurtainBorder(isShowCurtainBorder);
		setCurtainBorderColor(curtainBorderColor);
	}
	private void initChild() {
		provincePicker = (ProvincePicker) findViewById(R.id.pp_province);
		cityPicker = (CityPicker) findViewById(R.id.cp_city);
		provincePicker.setOnProvinceSelectedListener(new ProvincePicker.OnProvinceSelectedListener() {
			@Override
			public void onProvinceSelected(RegionDomain region) {
				if (onRegionSelectedListener != null) {
					onRegionSelectedListener.onProvinceSelected(region);
				}
			}
		});
		cityPicker.setOnCitySelectedListener(new CityPicker.OnCitySelectedListener() {
			@Override
			public void onCitySelected(RegionDomain region) {
				if (onRegionSelectedListener != null) {
					onRegionSelectedListener.onCitySelected(region);
				}
			}
		});
	}

	/**
	 * 设置省列表信息
	 * @param list
	 */
	public void setProvinceList(List<RegionDomain> list){
		provincePicker.setDataSource(list);
	}

	/**
	 * 设置市列表信息
	 * @param list
	 */
	public void setCityList(List<RegionDomain> list){
		cityPicker.setDataSource(list);
	}

	/**
	 * 设置市列表信息
	 * @param region
	 */
	public void setSelectedCity(RegionDomain region){
		cityPicker.setSelectedCity(region);
	}

	/**
	 * Gets province.
	 *
	 * @return the province
	 */
	public RegionDomain getProvince() {
		return provincePicker.getSelectedProvince();
	}

	/**
	 * Gets city
	 *
	 * @return the city
	 */
	public RegionDomain getCity() {
		return cityPicker.getSelectedCity();
	}

	/**
	 * Gets year picker.
	 *
	 * @return the year picker
	 */
	public ProvincePicker getProvincePicker() {
		return provincePicker;
	}

	/**
	 * Gets month picker.
	 *
	 * @return the month picker
	 */
	public CityPicker getCityPicker() {
		return cityPicker;
	}

	/**
	 * 一般列表的文本颜色
	 *
	 * @param textColor 文本颜色
	 */
	public void setTextColor(@ColorInt int textColor) {
		cityPicker.setTextColor(textColor);
		provincePicker.setTextColor(textColor);
	}

	/**
	 * 一般列表的文本大小
	 *
	 * @param textSize 文字大小
	 */
	public void setTextSize(int textSize) {
		cityPicker.setTextSize(textSize);
		provincePicker.setTextSize(textSize);
	}

	/**
	 * 设置被选中时候的文本颜色
	 *
	 * @param selectedItemTextColor 文本颜色
	 */
	public void setSelectedItemTextColor(@ColorInt int selectedItemTextColor) {
		cityPicker.setSelectedItemTextColor(selectedItemTextColor);
		provincePicker.setSelectedItemTextColor(selectedItemTextColor);
	}

	/**
	 * 设置被选中时候的文本大小
	 *
	 * @param selectedItemTextSize 文字大小
	 */
	public void setSelectedItemTextSize(int selectedItemTextSize) {
		cityPicker.setSelectedItemTextSize(selectedItemTextSize);
		provincePicker.setSelectedItemTextSize(selectedItemTextSize);
	}


	/**
	 * 设置显示数据量的个数的一半。
	 * 为保证总显示个数为奇数,这里将总数拆分，itemCount = mHalfVisibleItemCount * 2 + 1
	 *
	 * @param halfVisibleItemCount 总数量的一半
	 */
	public void setHalfVisibleItemCount(int halfVisibleItemCount) {
		cityPicker.setHalfVisibleItemCount(halfVisibleItemCount);
		provincePicker.setHalfVisibleItemCount(halfVisibleItemCount);
	}

	/**
	 * Sets item width space.
	 *
	 * @param itemWidthSpace the item width space
	 */
	public void setItemWidthSpace(int itemWidthSpace) {
		cityPicker.setItemWidthSpace(itemWidthSpace);
		provincePicker.setItemWidthSpace(itemWidthSpace);
	}

	/**
	 * 设置两个Item之间的间隔
	 *
	 * @param itemHeightSpace 间隔值
	 */
	public void setItemHeightSpace(int itemHeightSpace) {
		cityPicker.setItemHeightSpace(itemHeightSpace);
		provincePicker.setItemHeightSpace(itemHeightSpace);
	}


	/**
	 * Set zoom in center item.
	 *
	 * @param zoomInSelectedItem the zoom in center item
	 */
	public void setZoomInSelectedItem(boolean zoomInSelectedItem) {
		cityPicker.setZoomInSelectedItem(zoomInSelectedItem);
		provincePicker.setZoomInSelectedItem(zoomInSelectedItem);
	}

	/**
	 * 设置是否循环滚动。
	 * set wheel cyclic
	 * @param cyclic 上下边界是否相邻
	 */
	public void setCyclic(boolean cyclic) {
		cityPicker.setCyclic(cyclic);
		provincePicker.setCyclic(cyclic);
	}

	/**
	 * 设置文字渐变，离中心越远越淡。
	 * Set the text color gradient
	 * @param textGradual 是否渐变
	 */
	public void setTextGradual(boolean textGradual) {
		cityPicker.setTextGradual(textGradual);
		provincePicker.setTextGradual(textGradual);
	}


	/**
	 * 设置中心Item是否有幕布遮盖
	 * set the center item curtain cover
	 * @param showCurtain 是否有幕布
	 */
	public void setShowCurtain(boolean showCurtain) {
		cityPicker.setShowCurtain(showCurtain);
		provincePicker.setShowCurtain(showCurtain);
	}

	/**
	 * 设置幕布颜色
	 * set curtain color
	 * @param curtainColor 幕布颜色
	 */
	public void setCurtainColor(@ColorInt int curtainColor) {
		cityPicker.setCurtainColor(curtainColor);
		provincePicker.setCurtainColor(curtainColor);
	}

	/**
	 * 设置幕布是否显示边框
	 * set curtain border
	 * @param showCurtainBorder 是否有幕布边框
	 */
	public void setShowCurtainBorder(boolean showCurtainBorder) {
		cityPicker.setShowCurtainBorder(showCurtainBorder);
		provincePicker.setShowCurtainBorder(showCurtainBorder);
	}

	/**
	 * 幕布边框的颜色
	 * curtain border color
	 * @param curtainBorderColor 幕布边框颜色
	 */
	public void setCurtainBorderColor(@ColorInt int curtainBorderColor) {
		cityPicker.setCurtainBorderColor(curtainBorderColor);
		provincePicker.setCurtainBorderColor(curtainBorderColor);
	}

	/**
	 * 设置选择器的指示器文本
	 * set indicator text
	 * @param yearText  年指示器文本
	 * @param monthText 月指示器文本
	 * @param dayText   日指示器文本
	 */
	public void setIndicatorText(String yearText, String monthText, String dayText) {
		provincePicker.setIndicatorText(yearText);
		cityPicker.setIndicatorText(monthText);
	}

	/**
	 * 设置指示器文字的颜色
	 * set indicator text color
	 * @param textColor 文本颜色
	 */
	public void setIndicatorTextColor(@ColorInt int textColor) {
		provincePicker.setIndicatorTextColor(textColor);
		cityPicker.setIndicatorTextColor(textColor);
	}

	/**
	 * 设置指示器文字的大小
	 *  indicator text size
	 * @param textSize 文本大小
	 */
	public void setIndicatorTextSize(int textSize) {
		provincePicker.setTextSize(textSize);
		cityPicker.setTextSize(textSize);
	}

	/**
	 * Sets on date selected listener.
	 *
	 * @param onRegionSelectedListener
	 */
	public void setOnRegionSelectedListener(OnRegionSelectedListener onRegionSelectedListener) {
		this.onRegionSelectedListener = onRegionSelectedListener;
	}

	/**
	 * The interface On date selected listener.
	 */
	public interface OnRegionSelectedListener {
		/**
		 * On date selected.
		 *
		 * @param province
		 */
		void onProvinceSelected(RegionDomain province);

		/**
		 * @param city
		 */
		void onCitySelected(RegionDomain city);
	}
}
