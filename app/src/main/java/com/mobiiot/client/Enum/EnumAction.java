package com.mobiiot.client.Enum;

public enum EnumAction {


    //Sim
    SIM_ENABLE_SIM1("com.mobiiot.SIM1Enable"),
    SIM_ENABLE_SIM2("com.mobiiot.SIM2Enable"),
    SIM_DISABLE_SIM1("com.mobiiot.SIM1Disable"),
    SIM_DISABLE_SIM2("com.mobiiot.SIM2Disable"),
    SIM_GET_LIST_NAME("com.mobiiot.SIMGetListName"),
    SIM_GET_CURRENT("com.mobiiot.SIMGetCurrent"),
    SIM_GET_SIGNAL_STRENGTH_SIM1("com.mobiiot.SIM1GetSignalStrength"),
    SIM_GET_SIGNAL_STRENGTH_SIM2("com.mobiiot.SIM2GetSignalStrength"),


    //APN
    APN_UPDATE("com.mobiiot.updateApn"),
    APN_REMOVE("com.mobiiot.removeApn"),
    APN_ADD("com.mobiiot.addApn"),
    APN_RESET_DEFAULT("com.mobiiot.resetApn"),

    APN_GET_SIM1("com.mobiiot.getAPNSim1"),
    APN_GET_SIM2("com.mobiiot.getAPNSim2"),
    APN_GET_LIST_SIM1("com.mobiiot.getAPNListSim1"),
    APN_GET_LIST_SIM2("com.mobiiot.getAPNListSim2"),
    APN_GET_OPERATOR_SIM1("com.mobiiot.getAPNOperatorSim1"),////
    APN_GET_OPERATOR_SIM2("com.mobiiot.getAPNOperatorSim2"),////
    APN_GET_MCC_MNC_SIM1("com.mobiiot.getAPNMccMncSim1"),
    APN_GET_MCC_MNC_SIM2("com.mobiiot.getAPNMccMncSim2"),

    //Reboot
    SOFTWARE_REBOOT("com.mobiiot.reboot"),
    SOFTWARE_SHUTDOWN("com.mobiiot.shutdown"),
    SOFTWARE_RESET("com.mobiiot.reset"),
    SOFTWARE_DESTROY("com.mobiiot.destroy"),

    //Data
    DATA_SET_DEFAULT_SIM1("com.mobiiot.setData1"),
    DATA_SET_DEFAULT_SIM2("com.mobiiot.setData2"),
    DATA_ENABLE("com.mobiiot.dataEnable"),
    DATA_DISABLE("com.mobiiot.dataDisable"),
    DATA_GET_STATUS("com.mobiiot.getDataStatus"),

    //WIFI
    WIFI_ENABLE("com.mobiiot.wifiEnable"),
    WIFI_DISABLE("com.mobiiot.wifiDisable"),
    WIFI_ENABLE_HOTSPOT("com.mobiiot.hotspotEnable"),
    WIFI_DISABLE_HOTSPOT("com.mobiiot.hotspotDisable"),
    WIFI_CONNECT("com.mobiiot.wifiConnect"),
    WIFI_UPDATE_HOTSPOT("com.mobiiot.hotspotUpdate"),
    WIFI_GET_STATUS("com.mobiiot.getWifiStatus"),
    WIFI_GET_LIST("com.mobiiot.getWifiList"),

    //Operation
    OPERATION_CHANGE_BOOT_ANIMATION("com.mobiiot.changeBootAnimation"),
    OPERATION_CHANGE_SHUT_ANIMATION("com.mobiiot.changeShutAnimation"),
    OPERATION_CHANGE_TIMEZONE("com.mobiiot.changeTimeZone"),
    OPERATION_CHANGE_LANGUAGE("com.mobiiot.changeLanguage"),
    OPERATION_CHANGE_WALLPAPER("com.mobiiot.changeWallpaper"),
    OPERATION_SCREENSHOT("com.mobiiot.screenShot"),
    OPERATION_GET_LANGUAGE_LIST("com.mobiiot.getListLanguage"),

    //Location
    LOCATION_ENABLE("com.mobiiot.locationEnable"),
    LOCATION_DISABLE("com.mobiiot.locationDisable"),
    LOCATION_SET_MODE("com.mobiiot.locationSetMode"),
    LOCATION_GET_MODE("com.mobiiot.locationGetMode"),
    LOCATION_GET_LOCATION("com.mobiiot.locationGet"),
    LOCATION_GET_ADDRESS("com.mobiiot.locationGetAddress"),

    //Security
    SECURITY_ADB_ENABLE("com.mobiiot.ADBEnable"),
    SECURITY_ADB_DISABLE("com.mobiiot.ADBDisable"),
    SECURITY_UNKNOWNSOURCE_ENABLE("com.mobiiot.USEnable"),
    SECURITY_UNKNOWNSOURCE_DISABLE("com.mobiiot.USDisable"),
    SECURITY_MTP_ENABLE("com.mobiiot.MTPEnable"),
    SECURITY_MTP_DISABLE("com.mobiiot.MTPDisable"),
    SECURITY_PTP_ENABLE("com.mobiiot.PTPEnable"),
    SECURITY_PTP_DISABLE("com.mobiiot.PTPDisable"),
    SECURITY_STORAGE_ENABLE("com.mobiiot.storageEnable"),
    SECURITY_STORAGE_DISABLE("com.mobiiot.storageDisable"),


    //Package
    PACKAGE_INSTALL("com.mobiiot.packageInstall"),
    PACKAGE_REMOVE("com.mobiiot.packageRemove"),
    PACKAGE_UPDATE("com.mobiiot.packageUpdate"),
    PACKAGE_START("com.mobiiot.packageStart"),
    PACKAGE_STOP("com.mobiiot.packageStop"),
    PACKAGE_SHOW("com.mobiiot.systemPackageShow"),
    PACKAGE_HIDE("com.mobiiot.systemPackageHide"),
    PACKAGE_LIST("com.mobiiot.packageList"),
    PACKAGE_MAIN_MENU_APP_LIST("com.mobiiot.mainMenuAppList"),

    //Keystore
    KEYSTORE_ADD("com.mobiiot.keystoreAdd"),
    KEYSTORE_RESET("com.mobiiot.keystoreReset"),
    KEYSTORE_EXIST("com.mobiiot.keystoreIfExist"),

    //Device Information
    DEVICE_INFORMATION_GET("com.mobiiot.DeviceInformation"),

    //Scanner
    SCANNER_OPEN_ONE("com.mobiiot.openScanOne"),
    SCANNER_OPEN_MORE("com.mobiiot.openScanMore"),
    SCANNER_GET_RESULT("com.mobiiot.getScanResult"),

    //KIOSK
    KIOSK_STATUSBAR_HIDE("com.mobiiot.statusBarHide"),
    KIOSK_STATUSBAR_SHOW("com.mobiiot.statusBarShow"),
    KIOSK_CONTROLPANEL_HIDE("com.mobiiot.panelHide"),
    KIOSK_CONTROLPANEL_SHOW("com.mobiiot.panelShow"),
    KIOSK_SETTINGS_LEVEL1_HIDE("com.mobiiot.SettingsLevel1Hide"),
    KIOSK_SETTINGS_LEVEL2_HIDE("com.mobiiot.SettingsLevel2Hide"),
    KIOSK_SETTINGS_LEVEL1_RESET("com.mobiiot.SettingsLevel1Reset"),
    KIOSK_SETTINGS_LEVEL2_RESET("com.mobiiot.SettingsLevel2Reset"),
    KIOSK_CLEAN_STORAGE_RESET("com.mobiiot.cleanStorage"),


    //Touch/Click Simulation
    SIMULATION_TOUCH("com.mobiiot.touch"),
    SIMULATION_TOUCH_LONG("com.mobiiot.touchLong"),
    SIMULATION_SWIPE("com.mobiiot.swipe"),
    SIMULATION_BUTTON_CLICK("com.mobiiot.buttonClick"),

    //Bluetooth
    BLUETOOTH_GET_LIST_PAIR("com.mobiiot.getBluetoothPairList"),
    BLUETOOTH_GET_LIST_UNPAIR("com.mobiiot.getBluetoothUnPairList"),
    BLUETOOTH_ENABLE("com.mobiiot.bluetoothEnable"),
    BLUETOOTH_DISABLE("com.mobiiot.bluetoothDisable"),
    BLUETOOTH_CONNECT("com.mobiiot.bluetoothConnect"),

    //USB
    USB_ENABLE_PTP("com.mobiiot.PTPEnable"),
    USB_DISABLE_PTP("com.mobiiot.PTPDisable"),
    USB_ENABLE_MIDI("com.mobiiot.MIDIEnable"),
    USB_DISABLE_MIDI("com.mobiiot.MIDIDisable"),
    USB_ENABLE_USB("com.mobiiot.UsbEnable"),
    USB_DISABLE_USB("com.mobiiot.UsbDisable"),
    USB_ENABLE_FILE_TRANSFER("com.mobiiot.fileTransferEnable"),
    USB_DISABLE_FILE_TRANSFER("com.mobiiot.fileTransferDisable");




    private String Action;

    public String getAction() {
        return Action;
    }


    EnumAction(String action) {
        Action = action;
    }

    public static EnumAction fromString(String text) {
        for (EnumAction b : EnumAction.values()) {
            if (b.Action.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
