package com.mobiiot.client.model;


public class DeviceInformation {

    String imei;
    String ip;
    double latitude;
    double longitude;
    int dataStatus;
    int wifiStatus;
    int bluetoothStatus;
    String address;
    String firstUpdate;
    String lastUpdate;
    String installedApplication;
    String launchedApplication;
    String Server;
    int nombreUpdatePerDay;
    int updateImdtly;
    String notificationToken;
    String country;
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

    public DeviceInformation(){
        this.Server=null;
    }

    public DeviceInformation(String imei, String ip, double latitude, double longitude,
                             int dataStatus, int wifiStatus, int bluetoothStatus, String address,
                             String firstUpdate, String lastUpdate, String installedApplication,
                             String launchedApplication, String server, int nombreUpdatePerDay, int updateImdtly,
                             String notificationToken, String country, String custom_build_version, String build_number, String kernel_version, String baseBand_version, String android_version, String model, String battery_level, String wiFi_MAC_address, String bluetooth_address, String serial_number, String up_time, String ip_address) {
        this.imei = imei;
        this.ip = ip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataStatus = dataStatus;
        this.wifiStatus = wifiStatus;
        this.bluetoothStatus = bluetoothStatus;
        this.address = address;
        this.firstUpdate = firstUpdate;
        this.lastUpdate = lastUpdate;
        this.installedApplication = installedApplication;
        this.launchedApplication = launchedApplication;
        Server = server;
        this.nombreUpdatePerDay = nombreUpdatePerDay;
        this.updateImdtly = updateImdtly;
        this.notificationToken = notificationToken;
        this.country = country;
        Custom_build_version = custom_build_version;
        Build_number = build_number;
        Kernel_version = kernel_version;
        BaseBand_version = baseBand_version;
        Android_version = android_version;
        Model = model;
        Battery_level = battery_level;
        WiFi_MAC_address = wiFi_MAC_address;
        Bluetooth_address = bluetooth_address;
        Serial_number = serial_number;
        Up_time = up_time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public int getWifiStatus() {
        return wifiStatus;
    }

    public void setWifiStatus(int wifiStatus) {
        this.wifiStatus = wifiStatus;
    }

    public int getBluetoothStatus() {
        return bluetoothStatus;
    }

    public void setBluetoothStatus(int bluetoothStatus) {
        this.bluetoothStatus = bluetoothStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(String firstUpdate) {
        this.firstUpdate = firstUpdate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getInstalledApplication() {
        return installedApplication;
    }

    public void setInstalledApplication(String installedApplication) {
        this.installedApplication = installedApplication;
    }

    public String getLaunchedApplication() {
        return launchedApplication;
    }

    public void setLaunchedApplication(String launchedApplication) {
        this.launchedApplication = launchedApplication;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }

    public int getNombreUpdatePerDay() {
        return nombreUpdatePerDay;
    }

    public void setNombreUpdatePerDay(int nombreUpdatePerDay) {
        this.nombreUpdatePerDay = nombreUpdatePerDay;
    }

    public int getUpdateImdtly() {
        return updateImdtly;
    }

    public void setUpdateImdtly(int updateImdtly) {
        this.updateImdtly = updateImdtly;
    }

    public String getCustom_build_version() {
        return Custom_build_version;
    }

    public void setCustom_build_version(String custom_build_version) {
        Custom_build_version = custom_build_version;
    }

    public String getBuild_number() {
        return Build_number;
    }

    public void setBuild_number(String build_number) {
        Build_number = build_number;
    }

    public String getKernel_version() {
        return Kernel_version;
    }

    public void setKernel_version(String kernel_version) {
        Kernel_version = kernel_version;
    }

    public String getBaseBand_version() {
        return BaseBand_version;
    }

    public void setBaseBand_version(String baseBand_version) {
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

    public void setModel(String model) {
        Model = model;
    }

    public String getBattery_level() {
        return Battery_level;
    }

    public void setBattery_level(String battery_level) {
        Battery_level = battery_level;
    }

    public String getWiFi_MAC_address() {
        return WiFi_MAC_address;
    }

    public void setWiFi_MAC_address(String wiFi_MAC_address) {
        WiFi_MAC_address = wiFi_MAC_address;
    }

    public String getBluetooth_address() {
        return Bluetooth_address;
    }

    public void setBluetooth_address(String bluetooth_address) {
        Bluetooth_address = bluetooth_address;
    }

    public String getSerial_number() {
        return Serial_number;
    }

    public void setSerial_number(String serial_number) {
        Serial_number = serial_number;
    }

    public String getUp_time() {
        return Up_time;
    }

    public void setUp_time(String up_time) {
        Up_time = up_time;
    }


    @Override
    public String toString() {
        return "DeviceInformation{" +
                "imei='" + imei + '\'' +
                ", ip='" + ip + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", dataStatus=" + dataStatus +
                ", wifiStatus=" + wifiStatus +
                ", bluetoothStatus=" + bluetoothStatus +
                ", address='" + address + '\'' +
                ", firstUpdate='" + firstUpdate + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", installedApplication='" + installedApplication + '\'' +
                ", launchedApplication='" + launchedApplication + '\'' +
                ", Server='" + Server + '\'' +
                ", nombreUpdatePerDay=" + nombreUpdatePerDay +
                ", updateImdtly=" + updateImdtly +
                ", notificationToken='" + notificationToken + '\'' +
                ", country='" + country + '\'' +
                ", Custom_build_version='" + Custom_build_version + '\'' +
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
                '}';
    }
}
