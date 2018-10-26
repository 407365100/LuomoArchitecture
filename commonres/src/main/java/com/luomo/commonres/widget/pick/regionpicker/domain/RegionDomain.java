package com.luomo.commonres.widget.pick.regionpicker.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.ezhantu.module.gasstation.model.domain
 * @date :2018/5/14 19:59
 * @description:
 */
public class RegionDomain implements Parcelable{

    /**
     * region_id : 2
     * parent_id : 1
     * region_name : 北京
     * region_type : 1
     * agency_id : 0
     * pin_yin : null
     * is_top : 0
     * is_show : 0
     */

    @SerializedName("region_id")
    private int regionId;
    @SerializedName("parent_id")
    private int parentId;
    @SerializedName("region_name")
    private String regionName;
    @SerializedName("region_type")
    private int regionType;
    @SerializedName("agency_id")
    private int agencyId;
    @SerializedName("pin_yin")
    private Object pinYin;
    @SerializedName("is_top")
    private int isTop;
    @SerializedName("is_show")
    private int isShow;

    public RegionDomain() {
    }

    public RegionDomain(int regionId, int parentId, String regionName, int regionType) {
        this.regionId = regionId;
        this.parentId = parentId;
        this.regionName = regionName;
        this.regionType = regionType;
    }

    protected RegionDomain(Parcel in) {
        regionId = in.readInt();
        parentId = in.readInt();
        regionName = in.readString();
        regionType = in.readInt();
        agencyId = in.readInt();
        isTop = in.readInt();
        isShow = in.readInt();
    }

    public static final Creator<RegionDomain> CREATOR = new Creator<RegionDomain>() {
        @Override
        public RegionDomain createFromParcel(Parcel in) {
            return new RegionDomain(in);
        }

        @Override
        public RegionDomain[] newArray(int size) {
            return new RegionDomain[size];
        }
    };

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegionType() {
        return regionType;
    }

    public void setRegionType(int regionType) {
        this.regionType = regionType;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public Object getPinYin() {
        return pinYin;
    }

    public void setPinYin(Object pinYin) {
        this.pinYin = pinYin;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(regionId);
        dest.writeInt(parentId);
        dest.writeString(regionName);
        dest.writeInt(regionType);
        dest.writeInt(agencyId);
        dest.writeInt(isTop);
        dest.writeInt(isShow);
    }
}
