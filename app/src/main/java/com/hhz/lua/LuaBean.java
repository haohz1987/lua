package com.hhz.lua;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haohz on 2018/2/12.
 */

public class LuaBean implements Parcelable{

    /**
     * appDesc : qq下载
     * appIcon :
     * appId : 261
     * appName  : QQ
     * appPackage : com.tencent.mobileqq.apk
     * appSize :
     * appStatus : 00
     * appType : 01
     * appUrl : http://121.29.10.1/f3.market.xiaomi.com/download/AppStore/0ff0604fd770f481927d1edfad35675a3568ba656/com.tencent.mobileqq.apk
     * appVersion : 3.0.2
     * createTime : Tue Jan 16 17:38:25 CST 2018
     * createTimeForamt : 2018-01-16 17:38:25
     * factoryCode : XDL001
     * installType : 0
     * uptTime : Tue Jan 16 17:38:46 CST 2018
     * uptTimeForamt : 2018-01-16 17:38:46
     */

    private String appDesc;
    private String appIcon;
    private double appId;
    private String appName;
    private String appPackage;
    private String appSize;
    private String appStatus;
    private String appType;
    private String appUrl;
    private String appVersion;
    private String createTime;
    private String createTimeForamt;
    private String factoryCode;
    private double installType;
    private String uptTime;
    private String uptTimeForamt;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"uptTimeForamt\":\"")
                .append(uptTimeForamt).append('\"');
        sb.append(",\"uptTime\":\"")
                .append(uptTime).append('\"');
        sb.append(",\"installType\":")
                .append(installType);
        sb.append(",\"factoryCode\":\"")
                .append(factoryCode).append('\"');
        sb.append(",\"describeContents\":")
                .append(describeContents());
        sb.append(",\"createTimeForamt\":\"")
                .append(createTimeForamt).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"appVersion\":\"")
                .append(appVersion).append('\"');
        sb.append(",\"appUrl\":\"")
                .append(appUrl).append('\"');
        sb.append(",\"appType\":\"")
                .append(appType).append('\"');
        sb.append(",\"appStatus\":\"")
                .append(appStatus).append('\"');
        sb.append(",\"appSize\":\"")
                .append(appSize).append('\"');
        sb.append(",\"appPackage\":\"")
                .append(appPackage).append('\"');
        sb.append(",\"appName\":\"")
                .append(appName).append('\"');
        sb.append(",\"appId\":")
                .append(appId);
        sb.append(",\"appIcon\":\"")
                .append(appIcon).append('\"');
        sb.append(",\"appDesc\":\"")
                .append(appDesc).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public Double getAppId() {
        return appId;
    }

    public void setAppId(double appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeForamt() {
        return createTimeForamt;
    }

    public void setCreateTimeForamt(String createTimeForamt) {
        this.createTimeForamt = createTimeForamt;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public double getInstallType() {
        return installType;
    }

    public void setInstallType(double installType) {
        this.installType = installType;
    }

    public String getUptTime() {
        return uptTime;
    }

    public void setUptTime(String uptTime) {
        this.uptTime = uptTime;
    }

    public String getUptTimeForamt() {
        return uptTimeForamt;
    }

    public void setUptTimeForamt(String uptTimeForamt) {
        this.uptTimeForamt = uptTimeForamt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appDesc);
        dest.writeString(this.appIcon);
        dest.writeDouble(this.appId);
        dest.writeString(this.appName);
        dest.writeString(this.appPackage);
        dest.writeString(this.appSize);
        dest.writeString(this.appStatus);
        dest.writeString(this.appType);
        dest.writeString(this.appUrl);
        dest.writeString(this.appVersion);
        dest.writeString(this.createTime);
        dest.writeString(this.createTimeForamt);
        dest.writeString(this.factoryCode);
        dest.writeDouble(this.installType);
        dest.writeString(this.uptTime);
        dest.writeString(this.uptTimeForamt);
    }

    public LuaBean() {
    }

    protected LuaBean(Parcel in) {
        this.appDesc = in.readString();
        this.appIcon = in.readString();
        this.appId = in.readDouble();
        this.appName = in.readString();
        this.appPackage = in.readString();
        this.appSize = in.readString();
        this.appStatus = in.readString();
        this.appType = in.readString();
        this.appUrl = in.readString();
        this.appVersion = in.readString();
        this.createTime = in.readString();
        this.createTimeForamt = in.readString();
        this.factoryCode = in.readString();
        this.installType = in.readInt();
        this.uptTime = in.readString();
        this.uptTimeForamt = in.readString();
    }

    public static final Creator<LuaBean> CREATOR = new Creator<LuaBean>() {
        @Override
        public LuaBean createFromParcel(Parcel source) {
            return new LuaBean(source);
        }

        @Override
        public LuaBean[] newArray(int size) {
            return new LuaBean[size];
        }
    };
}
