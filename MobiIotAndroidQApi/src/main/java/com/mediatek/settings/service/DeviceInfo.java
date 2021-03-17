package com.mediatek.settings.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class DeviceInfo implements Parcelable {

    private String Custom_build_version;
    private String Build_number;
    private String Kernel_version;
    private String BaseBand_version;
    private String Android_version;
    private String Model;
    private String Battery_level;
    private String WiFi_MAC_address;
    private String Bluetooth_address;
    private String Serial_number;
    private String Up_time;
    private String Ip_address;
    private String API_version;
    private String Sim1_imei;
    private String Sim2_imei;
    private  int Sim_count;
    private String ScannerHardwareExist;
    private String Sim1_imsi;
    private String Sim2_imsi;
    public DeviceInfo(){
        this.Custom_build_version = null;
        this.Build_number = null;
        this.Kernel_version = null;
        this.BaseBand_version = null;
        this.Android_version = null;
        this.Model = null;
        this.Battery_level = null;
        this.WiFi_MAC_address = null;
        this.Bluetooth_address = null;
        this.Serial_number = null;
        this.Up_time = null;
        this.Ip_address = null;
        this.API_version = null;
        this.Sim1_imei=null;
        this.Sim2_imei=null;
        this.Sim_count=0;
        this.ScannerHardwareExist=null;
        this.Sim1_imsi=null;
        this.Sim2_imsi=null;
    }
    public DeviceInfo(DeviceInfo deviceInfo){
        this.Custom_build_version = deviceInfo.Custom_build_version;
        this.Build_number = deviceInfo.Build_number;
        this.Kernel_version = deviceInfo.Kernel_version;
        this.BaseBand_version = deviceInfo.BaseBand_version;
        this.Android_version = deviceInfo.Android_version;
        this.Model = deviceInfo.Model;
        this.Battery_level = deviceInfo.Battery_level;
        this.WiFi_MAC_address = deviceInfo.WiFi_MAC_address;
        this.Bluetooth_address = deviceInfo.Bluetooth_address;
        this.Serial_number = deviceInfo.Serial_number;
        this.Up_time = deviceInfo.Up_time;
        this.Ip_address = deviceInfo.Ip_address;
        this.API_version = deviceInfo.API_version;
        this.Sim1_imei=deviceInfo.Sim1_imei;
        this.Sim2_imei=deviceInfo.Sim2_imei;
        this.Sim_count=deviceInfo.Sim_count;
        this.ScannerHardwareExist=deviceInfo.ScannerHardwareExist;
        this.Sim1_imsi=deviceInfo.Sim1_imsi;
        this.Sim2_imsi=deviceInfo.Sim2_imsi;
    }


    public DeviceInfo(String Custom_build_version, String Build_number, String Kernel_version, String BaseBand_version, String Android_version, String Model, String Battery_level, String WiFi_MAC_address, String Bluetooth_address, String Serial_number, String Up_time, String Ip_address, String API_version, String Sim1_imei, String Sim2_imei, int Sim_count, String ScannerHardwareExist, String Sim1_imsi, String Sim2_imsi){
        this.Custom_build_version = Custom_build_version;
        this.Build_number = Build_number;
        this.Kernel_version = Kernel_version;
        this.BaseBand_version = BaseBand_version;
        this.Android_version = Android_version;
        this.Model = Model;
        this.Battery_level = Battery_level;
        this.WiFi_MAC_address = WiFi_MAC_address;
        this.Bluetooth_address = Bluetooth_address;
        this.Serial_number = Serial_number;
        this.Up_time = Up_time;
        this.Ip_address = Ip_address;
        this.API_version = API_version;
        this.Sim1_imei=Sim1_imei;
        this.Sim2_imei=Sim2_imei;
        this.Sim_count=Sim_count;
        this.ScannerHardwareExist=ScannerHardwareExist;
        this.Sim1_imsi=Sim1_imsi;
        this.Sim2_imsi=Sim2_imsi;
    }
    //public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() {}
    public static final Creator<DeviceInfo> CREATOR = new Creator<DeviceInfo>() {
        @Override
           public DeviceInfo createFromParcel(Parcel in) {
                         Log.e("lyl123", "service :  - createFromParcel");
                         return new DeviceInfo(
                                         in.readString(), in.readString(), in.readString(),
                                         in.readString(), in.readString(), in.readString(),
                                         in.readString(), in.readString(), in.readString(),
                                         in.readString(), in.readString(), in.readString(),
                                         in.readString(), in.readString(), in.readString(),
                                         in.readInt(), in.readString(), in.readString(), in.readString());
                     };

        @Override
        public DeviceInfo[] newArray(int size) {
            return new DeviceInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.e("lyl123", "service :  - writeToParcel API_version :" + Ip_address);
        dest.writeString(Custom_build_version);
        dest.writeString(Build_number);
        dest.writeString(Kernel_version);
        dest.writeString(BaseBand_version);
        dest.writeString(Android_version);
        dest.writeString(Model);
        dest.writeString(Battery_level);
        dest.writeString(WiFi_MAC_address);
        dest.writeString(Bluetooth_address);
        dest.writeString(Serial_number);
        dest.writeString(Up_time);
        dest.writeString(Ip_address);
        dest.writeString(API_version);
        dest.writeString(Sim1_imei);
        dest.writeString(Sim2_imei);
        dest.writeInt(Sim_count);
        dest.writeString(ScannerHardwareExist);
        dest.writeString(Sim1_imsi);
        dest.writeString(Sim2_imsi);
    }



    public String getCustom_build_version() {
        return Custom_build_version;
    }

    public  void setCustom_build_version(String custom_build_version) {
        Custom_build_version = custom_build_version;
    }

    public String getAPI_version(){
        return API_version;
    }

    public void setAPI_version(String Api_version){
        API_version = Api_version;
    }

    public String getBuild_number() {
        return Build_number;
    }

    public  void setBuild_number(String build_number) {
        Build_number = build_number;
    }

    public String getKernel_version() {
        return Kernel_version;
    }

    public  void setKernel_version(String kernel_version) {
        Kernel_version = kernel_version;
    }

    public String getBaseBand_version() {
        return BaseBand_version;
    }

    public  void setBaseBand_version(String baseBand_version) {
        BaseBand_version = baseBand_version;
    }

    public String getAndroid_version() {
        return Android_version;
    }

    public void setAndroid_version(String android_version) {
        Android_version = android_version;
    }

    public String getModel() {
        return Model;
    }

    public  void setModel(String model) {
        Model = model;
    }

    public String getBattery_level() {
        return Battery_level;
    }

    public  void setBattery_level(String battery_level) {
        Battery_level = battery_level;
    }

    public String getWiFi_MAC_address() {
        return WiFi_MAC_address;
    }

    public  void setWiFi_MAC_address(String wiFi_MAC_address) {
        WiFi_MAC_address = wiFi_MAC_address;
    }

    public String getBluetooth_address() {
        return Bluetooth_address;
    }

    public  void setBluetooth_address(String bluetooth_address) {
        Bluetooth_address = bluetooth_address;
    }

    public String getSerial_number() {
        return Serial_number;
    }

    public  void setSerial_number(String serial_number) {
        Serial_number = serial_number;
    }

    public String getUp_time() {
        return Up_time;
    }

    public  void setUp_time(String up_time) {
        Up_time = up_time;
    }

    public String getIp_address() {
        return Ip_address;
    }

    public  void setIp_address(String ip_address) {
        Ip_address = ip_address;
    }

    public String getSim1_imei() {
        return Sim1_imei;
    }

    public void setSim1_imei(String sim1_imei) {
        Sim1_imei = sim1_imei;
    }

    public String getSim2_imei() {
        return Sim2_imei;
    }

    public void setSim2_imei(String sim2_imei) {
        Sim2_imei = sim2_imei;
    }

    public int getSim_count() {
        return Sim_count;
    }

    public void setSim_count(int sim_count) {
        Sim_count = sim_count;
    }

    public String getscanner() {
        return ScannerHardwareExist;
    }

    public  void setscanner(String scannerHardwareExist) {
        ScannerHardwareExist = scannerHardwareExist;
    }
    public String getSim1_imsi() {
        return Sim1_imsi;
    }
    public void setSim1_imsi(String sim1_imsi) {
        Sim1_imsi = sim1_imsi;
    }
    public String getSim2_imsi() {
        return Sim2_imsi;
    }
    public void setSim2_imsi(String sim2_imsi) {
        Sim2_imsi = sim2_imsi;
    }


    @Override
    public String toString() {
        Log.d("qinyu333", "deviceInfo : aaaa");
        String str="DeviceInformation{" +
                "Custom_build_version='" + Custom_build_version + '\'' +
                ", Build_number='" + Build_number + '\'' +
                ", Kernel_version='" + Kernel_version + '\'' +
                ", BaseBand_version='" + BaseBand_version + '\'' +
                ", Android_version='" + Android_version + '\'' +
                ", Model='" + Model + '\'' +
                ", Battery_level='" + Battery_level + '\'' +
                ", WiFi_MAC_address='" + WiFi_MAC_address + '\'' +
                ", Bluetooth_address='" + Bluetooth_address + '\'' +
                ", Serial_number='" + Serial_number + '\'' +
                ", Up_time='" + Up_time + '\'' +
                ", Ip_address='" + Ip_address + '\'' +
                ", API_version='" + API_version + '\''+
                ",SIM1_IMEI='" + Sim1_imei +'\'';
        if(Sim_count==2) {
            str += ",SIM2_IMEI='" + Sim2_imei + '\'';
        }
        str+=",ScannerHardwareExist='" + ScannerHardwareExist +'\''+
                ", Sim1_imsi='" + Sim1_imsi + '\''+
                ", Sim2_imsi='" + Sim2_imsi + '\'';
        return str+"}";
    }
    //get-set ways end
}
