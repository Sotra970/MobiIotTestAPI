
// CSApiMPE.aidl
package com.mediatek.settings.service;

import com.mediatek.settings.service.APN;
import java.util.List;
import com.mediatek.settings.service.DeviceInfo;
import android.bluetooth.BluetoothDevice;

interface CSApiMPE {
	/* simSlot */
	/* 1. enable or disable sim card */
	String simRadioSwitch(int slotIndex, boolean isOn);
	/* 2. get all operator's name */
	List<String> getSimNameList();
	/* 3. get current sim's operator name */
	String getCurrentSimName();
	/* 4. get signal strength */
	String getSignalStrength(int slotIndex);

	/* 2. apn */
	/* 2.1 get current apn  */
	APN getAPN(int slotId);
	/* 2.2 get list of apn */
	List<APN> getApnList(int slotId);
	/* 2.3 update apn */
	boolean updateApn(in APN newApn, boolean force);
	/* 2.4 remove apn */
	boolean removeAPN(String apnKey);
	/* 2.5 restore to defualt */
	boolean restoreDefault();
	/* 2.6 get operator */
	void getOperator(int slotId);
	/* 2.7 add apn */
	boolean addApn(in APN newApn, boolean force);
	/* 2.8 get mcc and mnc*/
	String getMccMnc(int slotId);

	void shutDown();//shut down
	void sofewareReboot();//reboot
	void factoryReset();//reset factory
    void destory();


	/* Data */
	/* 10. disable data */
	boolean setDefaultSimOne();
	boolean setDefaultSimTwo();
	void disableData();
    void enableData();
	String getDataStatus();

	void enableWifi();
	void disableWifi();
	String getWifiStatus();
	void disableHotspot();
	void enableHotspot();
	void editHotspot(String name, String password);
	boolean connectToWiFi(int securityType, String ssid, String key);
	List getListWifi();

	/*Operation*/
	/*language*/
	String[] getList();
	void getLanguages(int i);

	/*time*/
    void setSysDate(int year, int month, int day);
    void setSysTime(int hour, int minute);
    void setSysZone(String zone);
    void setTimeFormat(int format);

	/*wallpager*/
	boolean changeWallpager(String path);
	/*change bootAnimation*/
	boolean changeBootAnimation(String path, String name);
	/*change bootAnimation*/
	boolean changeShutAnimation(String path, String name);

	/* 5.1 change location mode */
	int getCurLocationMode();
	void setLocationMode(int mode);
	/* 5.2 get location */
	String getLocation();
	/* 5.3 get address */
	String getAddress();
 	void enableLocation();
	void disableLocation();

	/*secourity*/
	void enableUnknownSource();
    void disableUnknownSource();
    void enableAdb();
    void disableAdb();
    void enableMtp();
    void disableMtp();
    void enablePtp();
    void disablePtp();
    void enableStorage();
    void disableStorage();

	/*install/uninstall apk*/
	void removeApp(String packageName);
	void installApp(String path , String packageName);
	void updateApp(String path,String packageName);
	List<String> getPackageList();
	void hideApp(String packageName);
	void showApp(String packageName);
	List<String> getMainMenuAPPList();

	/*check for signature*/
	boolean addKeystoreToList(in byte[] keystore);
    void resetListKey();

    /*getDeviceInformation*/
    DeviceInfo getDeviceInformation();

        /*firmware update*/
        boolean isAutoUpdate(int value);
        int updateFirmware(String fwPath);
        String getFirmwareVersion();

        /*fontLib update*/
        int updateFontLib(String fontLibPath);
        String getFontLibVersion();
    /*showStatusbar*/
    void showStatusbar();
    /*hideStatusbar*/
    void hideStatusbar();
    //disable/enable slide of shortcuts panel
    void blockPanel(boolean status);
    //hide the item of settings
    boolean hideSettings(in int[] lines);
    //resetSettings
    void resetSettings();
    //void showNotification(String notificationTitle, String notificationText, int notificationIcon, int textColor);
    void showNotification(String notificationTitle, String notificationText, int notificationIcon, int textColor);
    boolean doesKeyExist(in byte[] keystore);

    /*scan*/
	void openScan();
	String getScanInfo();

	/*screenShot*/
	void screenShot();

	/*AndroidBeam*/
	boolean enableAndroidBeam() ;
    boolean disableAndroidBeam() ;

	/*unknow source*/
	boolean enableUnknownSourceNew();
	boolean disableUnknownSourceNew();

	/*cleansdcardstorage*/

	void cleanSdCardStorage();

	/*bluebooth*/
	void enableBluetooth();
	void disableBluetooth();
	List<BluetoothDevice> getDevicePairList();
	List<BluetoothDevice> getDeviceUnPairList();
	void connectToBluetooth(int i);

	/*Key/touch simulation*/
	void touch(float x,float y);
	void touchLong(float x,float y);
	void swipe(float x,float y,float x1,float y1,int time);
	void clickButton(int keyCode);

	void hideMessageFromScreen();
	void showMessageInScreen(String message);

	void hideSettingsLevel2(String key);
	void resetLevel2();


	//scan once or more
	void openNewScan(boolean isCountinousScan);
	String [] getNewScanInfo();
    boolean isUSSDCode(String ussdcode);

}
