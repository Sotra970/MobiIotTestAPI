package com.mediatek.settings.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class APN implements Parcelable {
    private String key;//_id
    private String name;
    private String apn;
    private String proxy;
    private String port;
    private String user;	//5
    private String password;
    private String server;
    private String mmsc;
    private String mmsproxy;
    private String mmsport;//10
    private String mcc;
    private String mnc;
    private int authTypeVal;
    private String apnType;//strType;
    private String apnProtocol;//protocol;//15
    private String roamingProtocol;
    private int carrierEnabled;//APN enable/disable
    private String bearerVal;
    private String MVNOType;
    private String MVNOValue;//20
    private int userVisible;
//    //protected int sourceType;
//    protected boolean selectable;
//    protected boolean isChecked;
//    protected boolean isEditable;


    public APN(String key, String name, String apn, String proxy, String port, String user, String password, String server, String mmsc, String mmsproxy, String mmsport, String mcc, String mnc, int authTypeVal, String apnType, String apnProtocol, String roamingProtocol, int carrierEnabled, String bearerVal, String MVNOType, String MVNOValue, int userVisible) {
        this.key = key;
        this.name = name;
        this.apn = apn;
        this.proxy = proxy;
        this.port = port;
        this.user = user;
        this.password = password;
        this.server = server;
        this.mmsc = mmsc;
        this.mmsproxy = mmsproxy;
        this.mmsport = mmsport;
        this.mcc = mcc;
        this.mnc = mnc;
        this.authTypeVal = authTypeVal;
        this.apnType = apnType;
        this.apnProtocol = apnProtocol;
        this.roamingProtocol = roamingProtocol;
        this.carrierEnabled = carrierEnabled;
        this.bearerVal = bearerVal;
        this.MVNOType = MVNOType;
        this.MVNOValue = MVNOValue;
        this.userVisible = userVisible;
    }

    public APN(){
        this.key = null;
        this.name = null;
        this.apn = null;
        this.proxy = null;
        this.port = null;
        this.user = null;
        this.password = null;
        this.server = null;
        this.mmsc = null;
        this.mmsproxy = null;
        this.mmsport = null;
        this.mcc = null;
        this.mnc = null;
        this.authTypeVal = 0;
        this.apnType = null;
        this.apnProtocol = null;
        this.roamingProtocol = null;
        this.carrierEnabled = 0;
        this.bearerVal = null;
        this.MVNOType = null;
        this.MVNOValue = null;
        this.userVisible = 0;
    }

    public APN(APN apn) {
        this.key = apn.key;
        this.name = apn.name;
        this.apn = apn.apn;
        this.proxy = apn.proxy;
        this.port = apn.port;
        this.user = apn.user;
        this.password = apn.password;
        this.server = apn.server;
        this.mmsc = apn.mmsc;
        this.mmsproxy = apn.mmsproxy;
        this.mmsport = apn.mmsport;
        this.mcc = apn.mcc;
        this.mnc = apn.mnc;
        this.authTypeVal = apn.authTypeVal;
        this.apnType = apn.apnType;
        this.apnProtocol = apn.apnProtocol;
        this.roamingProtocol = apn.roamingProtocol;
        this.carrierEnabled = apn.carrierEnabled;
        this.bearerVal = apn.bearerVal;
        this.MVNOType = apn.MVNOType;
        this.MVNOValue = apn.MVNOValue;
        this.userVisible = apn.userVisible;
    }

    //public static final Parcelable.Creator<APN> CREATOR = new Parcelable.Creator<APN>() {}
    public static final Creator<APN> CREATOR = new Creator<APN>() {
        @Override
        public APN createFromParcel(Parcel in) {
            Log.e("lyl123", "service : 解包 - createFromParcel");
            return new APN(in.readString(), in.readString(), in.readString(),
                    in.readString(), in.readString(), in.readString(),
                    in.readString(), in.readString(), in.readString(),
                    in.readString(), in.readString(), in.readString(),
                    in.readString(), in.readInt(), in.readString(),
                    in.readString(), in.readString(), in.readInt(),
                    in.readString(), in.readString(), in.readString(),
                    in.readInt());
        }

        @Override
        public APN[] newArray(int size) {
            return new APN[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.e("lyl123", "service : 打包 - writeToParcel");
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(apn);
        dest.writeString(proxy);
        dest.writeString(port);
        dest.writeString(user);
        dest.writeString(password);
        dest.writeString(server);
        dest.writeString(mmsc);
        dest.writeString(mmsproxy);
        dest.writeString(mmsport);
        dest.writeString(mcc);
        dest.writeString(mnc);
        dest.writeInt(authTypeVal);
        dest.writeString(apnType);
        dest.writeString(apnProtocol);
        dest.writeString(roamingProtocol);
        dest.writeInt(carrierEnabled);
        dest.writeString(bearerVal);
        dest.writeString(MVNOType);
        dest.writeString(MVNOValue);
        dest.writeInt(carrierEnabled);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getMmsc() {
        return mmsc;
    }

    public void setMmsc(String mmsc) {
        this.mmsc = mmsc;
    }

    public String getMmsproxy() {
        return mmsproxy;
    }

    public void setMmsproxy(String mmsproxy) {
        this.mmsproxy = mmsproxy;
    }

    public String getMmsport() {
        return mmsport;
    }

    public void setMmsport(String mmsport) {
        this.mmsport = mmsport;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public int getAuthTypeVal() {
        return authTypeVal;
    }

    public void setAuthTypeVal(int authTypeVal) {
        this.authTypeVal = authTypeVal;
    }

    public String getApnType() {
        return apnType;
    }

    public void setApnType(String apnType) {
        this.apnType = apnType;
    }

    public String getApnProtocol() {
        return apnProtocol;
    }

    public void setApnProtocol(String apnProtocol) {
        this.apnProtocol = apnProtocol;
    }

    public String getRoamingProtocol() {
        return roamingProtocol;
    }

    public void setRoamingProtocol(String roamingProtocol) {
        this.roamingProtocol = roamingProtocol;
    }

    public int getCarrierEnabled() {
        return carrierEnabled;
    }

    public void setCarrierEnabled(int carrierEnabled) {
        this.carrierEnabled = carrierEnabled;
    }

    public String getBearerVal() {
        return bearerVal;
    }

    public void setBearerVal(String bearerVal) {
        this.bearerVal = bearerVal;
    }

    public String getMVNOType() {
        return MVNOType;
    }

    public void setMVNOType(String MVNOType) {
        this.MVNOType = MVNOType;
    }

    public String getMVNOValue() {
        return MVNOValue;
    }

    public void setMVNOValue(String MVNOValue) {
        this.MVNOValue = MVNOValue;
    }

    public static Creator<APN> getCREATOR() {
        return CREATOR;
    }

    public int getUserVisible() {
        return userVisible;
    }

    public void setUserVisible(int userVisible) {
        this.userVisible = userVisible;
    }

    @Override
    public String toString() {
        return "APN{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", apn='" + apn + '\'' +
                ", proxy='" + proxy + '\'' +
                ", port='" + port + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", server='" + server + '\'' +
                ", mmsc='" + mmsc + '\'' +
                ", mmsproxy='" + mmsproxy + '\'' +
                ", mmsport='" + mmsport + '\'' +
                ", mcc='" + mcc + '\'' +
                ", mnc='" + mnc + '\'' +
                ", authTypeVal=" + authTypeVal +
                ", apnType='" + apnType + '\'' +
                ", apnProtocol='" + apnProtocol + '\'' +
                ", roamingProtocol='" + roamingProtocol + '\'' +
                ", carrierEnabled=" + carrierEnabled +
                ", bearerVal='" + bearerVal + '\'' +
                ", MVNOType='" + MVNOType + '\'' +
                ", MVNOValue='" + MVNOValue + '\'' +
                ", userVisible='" + userVisible + '\'' +
                '}';
    }
}
