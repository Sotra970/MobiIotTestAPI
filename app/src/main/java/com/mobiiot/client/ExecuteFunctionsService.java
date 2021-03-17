package com.mobiiot.client;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mobiiot.api.CsApn;
import com.mobiiot.api.CsBluetooth;
import com.mobiiot.api.CsData;
import com.mobiiot.api.CsDevice;
import com.mobiiot.api.CsLocation;
import com.mobiiot.api.CsOperation;
import com.mobiiot.api.CsPackage;
import com.mobiiot.api.CsReboot;
import com.mobiiot.api.CsScanner;
import com.mobiiot.api.CsSimSlot;
import com.mobiiot.api.CsStorage;
import com.mobiiot.api.CsTouchButtonSimulation;
import com.mobiiot.api.CsWifi;
import com.mobiiot.api.Cs_KeyStore;
import com.mobiiot.client.Activity.APNAddActivity;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mediatek.settings.service.APN;

import java.io.File;
import java.util.List;

//am startservice -e ACTION com.mobiiot.DeviceInformation com.mobiiot.client/.ExecuteFunctionsService
public class ExecuteFunctionsService extends Service {

    public static String TAG="MobiIot API SERVICE";

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        if (intent != null && intent.getAction() != null) {

            String value=intent.getAction();
            if(intent.getStringExtra("ACTION")!=null)
                value=intent.getStringExtra("ACTION");

            EnumAction whichView = EnumAction.fromString(value);
            switch (whichView) {
                case SIM_ENABLE_SIM1:
                    String result = CsSimSlot.simRadioSwitch(0,true);
                    Utils.showLogs(TAG,"SIM_ENABLE_SIM1 - "+result);
                    break;
                case SIM_ENABLE_SIM2:

                    result = CsSimSlot.simRadioSwitch(1,true);
                    Utils.showLogs(TAG,"SIM_ENABLE_SIM2 - "+result);
                    break;
                case SIM_DISABLE_SIM1:
                    result = CsSimSlot.simRadioSwitch(0,false);
                    Utils.showLogs(TAG,"SIM_DISABLE_SIM1 - "+result);
                    break;
                case SIM_DISABLE_SIM2:
                    result = CsSimSlot.simRadioSwitch(1,false);
                    Utils.showLogs(TAG,"SIM_DISABLE_SIM2 - "+result);
                    break;
                case SIM_GET_LIST_NAME:
                    List<String> listSim = CsSimSlot.getSimNameList();
                    result = TextUtils.join(", ", listSim);
                    Utils.showLogs(TAG,"SIM_GET_LIST_NAME - "+result);
                    break;
                case SIM_GET_SIGNAL_STRENGTH_SIM1:
                    result = CsSimSlot.getSignalStrength(0);
                    Utils.showLogs(TAG,"SIM_GET_SIGNAL_STRENGTH_SIM1 - "+result);
                    break;
                case SIM_GET_SIGNAL_STRENGTH_SIM2:
                    result = CsSimSlot.getSignalStrength(1);
                    Utils.showLogs(TAG,"SIM_GET_SIGNAL_STRENGTH_SIM2 - "+result);
                    break;
                case SIM_GET_CURRENT:
                    result = CsSimSlot.getCurrentSimName();
                    Utils.showLogs(TAG,"SIM_GET_CURRENT - "+result);
                    break;

                case APN_RESET_DEFAULT:
                    if (CsApn.restoreDefault()){
                        Utils.showLogs(TAG,"APN_RESET_DEFAULT ==>"+true);
                    } else {
                        Utils.showLogs(TAG,"APN_RESET_DEFAULT ==>"+false);
                    }
                    break;
                case APN_ADD:
                    Bundle bundle = intent.getExtras();
                    APN newApn = (APN)bundle.getParcelable(APNAddActivity.NEW_APN);
                    if (CsApn.addApn(newApn, false)){
                        Utils.showLogs(TAG,"APN_ADD ==>"+true+"\n+"+newApn.toString());
                    } else {
                        Utils.showLogs(TAG,"APN_ADD ==>"+false+"\n+"+newApn.toString());
                    }
                    break;
                case APN_REMOVE:
                    String apnKey=intent.getStringExtra("apnKey");
                    if (CsApn.removeAPN(apnKey)){
                        Utils.showLogs(TAG,"APN_REMOVE ==>"+true+"\nAPNKey="+apnKey);
                    } else {
                        Utils.showLogs(TAG,"APN_REMOVE ==>"+false+"\nAPNKey="+apnKey);
                    }
                    break;
                case APN_UPDATE://///////////////
                    bundle = intent.getExtras();
                    newApn = (APN)bundle.getParcelable(APNAddActivity.NEW_APN);
                    if (CsApn.updateApn(newApn,false)){
                        Utils.showLogs(TAG,"APN_UPDATE ==>"+true+"\n+"+newApn.toString());
                    } else {
                        Utils.showLogs(TAG,"APN_UPDATE ==>"+false+"\n+"+newApn.toString());
                    }
                    break;
                case APN_GET_LIST_SIM1:
                    result = TextUtils.join(", ", CsApn.getApnList(0));
                    Utils.showLogs(TAG,"APN_GET_LIST_SIM1\n"+result);
                    break;
                case APN_GET_LIST_SIM2:
                    result = TextUtils.join(", ", CsApn.getApnList(1));
                    Utils.showLogs(TAG,"APN_GET_LIST_SIM2\n"+result);
                    break;
                case APN_GET_MCC_MNC_SIM1:
                    result = CsApn.getMccMnc(0);
                    Utils.showLogs(TAG,"APN_GET_MCC_MNC_SIM1\n"+result);
                    break;
                case APN_GET_MCC_MNC_SIM2:
                    result = CsApn.getMccMnc(1);
                    Utils.showLogs(TAG,"APN_GET_MCC_MNC_SIM2\n"+result);
                    break;
                case APN_GET_SIM1:
                    newApn = CsApn.getAPN(0);
                    Utils.showLogs(TAG,"APN_GET_SIM1\n"+newApn.toString());
                    break;
                case APN_GET_SIM2:
                    newApn = CsApn.getAPN(1);
                    Utils.showLogs(TAG,"APN_GET_SIM2\n"+newApn.toString());
                    break;

                case KEYSTORE_ADD://////////////////////////////:

                    String filePath=intent.getStringExtra("keystore");
                    String fileContent = Utils.readFile(new File(filePath));
                    List<String> fileContentList= Utils.alertString(fileContent);
                    byte[] fileByte= Utils.getBytes(fileContentList);
                    if(filePath.contains("http")){

                    }else{
                        Cs_KeyStore.addKeystoreToList(fileByte);
                    }

                    Utils.showLogs(TAG,"KEYSTORE_ADD  => "+filePath);
                    break;
                case DEVICE_INFORMATION_GET:
                    Utils.showLogs(TAG,"DEVICE_INFORMATION_GET\n"+ CsDevice.getDeviceInformation().toString());
                    break;

                case SCANNER_GET_RESULT:
                    String scanresult=intent.getStringExtra("scanResult");

                    Utils.showLogs(TAG,"SCANNER_GET_RESULT\n"+ scanresult.toString());
                    break;
                case KEYSTORE_RESET:
                    Utils.showLogs(TAG,"KEYSTORE_RESET");
                    Cs_KeyStore.resetListKey();
                    break;
                case KEYSTORE_EXIST:
                    filePath=intent.getStringExtra("keystore");
                    fileContent = Utils.readFile(new File(filePath));
                    fileContentList= Utils.alertString(fileContent);
                    fileByte= Utils.getBytes(fileContentList);
                    boolean ifKeystoreExist=Cs_KeyStore.doesKeyExist(fileByte);
                    Utils.showLogs(TAG,"KEYSTORE_EXIST ==>"+ifKeystoreExist);
                    break;
                case PACKAGE_SHOW:
                    Utils.showLogs(TAG,"PACKAGE_SHOW"+intent.getStringExtra("packageName"));
                    CsPackage.showApp(intent.getStringExtra("packageName"));
                    //new DownloadInstallApk().execute(params,false);
                    break;
                case PACKAGE_HIDE:
                    Utils.showLogs(TAG,"PACKAGE_HIDE"+intent.getStringExtra("packageName"));
                    CsPackage.hideApp(intent.getStringExtra("packageName"));
                    break;
                case PACKAGE_START:
                    Toast.makeText(getApplicationContext(),"START PACKAGE",Toast.LENGTH_SHORT).show();
                    Intent i = getPackageManager().getLaunchIntentForPackage(intent.getStringExtra("packageName"));
                    startActivity(i);
                    break;
                case PACKAGE_STOP:///////////////////////////////
                    Toast.makeText(getApplicationContext(),"STOP PACKAGE--",Toast.LENGTH_SHORT).show();
                    /*i = context.getPackageManager().getLaunchIntentForPackage("com.mesutpiskin");
                    context.startActivity(i);*/
                    break;
                case PACKAGE_INSTALL:

                    String path=intent.getStringExtra("path");
                    String packageName=intent.getStringExtra("packageName");
                    if(path.contains("http")){
                        Utils.showLogs(TAG,"PACKAGE_INSTALL URL ==>"+packageName+" - "+path);
                        new DownloadInstallApk().execute(path,packageName,true,false);
                    }else{
                        Utils.showLogs(TAG,"PACKAGE_INSTALL ==>"+packageName+" - "+path);
                        CsPackage.installApp(path,packageName);
                    }


                    break;
                case PACKAGE_UPDATE:

                    path=intent.getStringExtra("path");
                    packageName=intent.getStringExtra("packageName");
                    if(path.contains("http")){
                        Utils.showLogs(TAG,"PACKAGE_UPDATE URL ==>"+packageName+" - "+path);
                        new DownloadInstallApk().execute(path,packageName,true,true);
                    }else{
                        Utils.showLogs(TAG,"PACKAGE_UPDATE ==>["+packageName+" - "+path+"]");
                        CsPackage.updateApp(path,packageName);
                    }



                    break;
                case PACKAGE_REMOVE:
                    packageName=intent.getStringExtra("packageName");
                    CsPackage.removeApp(packageName);
                    Utils.showLogs(TAG,"PACKAGE_REMOVE ==>"+packageName);
                    break;
                case PACKAGE_LIST:
                    result = TextUtils.join(", ", CsPackage.getPackageList());
                    Utils.showLogs(TAG,"PACKAGE_LIST ==>"+result);
                    break;
                case PACKAGE_MAIN_MENU_APP_LIST:
                    result = TextUtils.join(", ", CsPackage.getMainMenuAPPList());
                    Utils.showLogs(TAG,"PACKAGE_MAIN_MENU_APP_LIST ==>"+result);
                    break;
                case SCANNER_OPEN_MORE:
                    CsScanner.openNewScan(true);
                    Utils.showLogs(TAG,"SCANNER_OPEN_MORE ");
                    break;
                case SCANNER_OPEN_ONE:
                    CsScanner.openNewScan(false);
                    Utils.showLogs(TAG,"SCANNER_OPEN_ONE");
                    break;
                case KIOSK_SETTINGS_LEVEL2_HIDE:
                    String lines=intent.getStringExtra("lines");
                    CsOperation.hideSettingsLevel2(lines);
                    Utils.showLogs(TAG,"KIOSK_SETTINGS_LEVEL2_HIDE =>"+lines);
                    break;
                case KIOSK_SETTINGS_LEVEL2_RESET:
                    CsOperation.resetSettingsLevel2();
                    Utils.showLogs(TAG,"KIOSK_SETTINGS_LEVEL2_RESET");
                    break;
                case KIOSK_CLEAN_STORAGE_RESET:
                    CsOperation.cleanSdCardStorage();
                    Utils.showLogs(TAG,"KIOSK_CLEAN_STORAGE_RESET");
                    break;

                case KIOSK_SETTINGS_LEVEL1_HIDE://////////////////////

                    String [] settingsLine=new String[]{
                            "top_level_network",
                            "top_level_connected_devices",
                            "top_level_apps_and_notifs",
                            "top_level_battery",
                            "top_level_display",
                            "top_level_sound",
                            "top_level_storage",
                            "top_level_privacy",
                            "top_level_location",
                            "top_level_security",
                            "top_level_accounts",
                            "top_level_accessibility",
                            "dashboard_tile_pref_com.mediatek.duraspeed.DuraSpeedMainActivity",
                            "top_level_system",
                            "soft_update",
                            "top_level_about_device"
                    };





                    String listSettingToHode=intent.getStringExtra("lines");
                    String [] listToHide=(listSettingToHode.replace("[","").replace("]","").split(","));
                    int [] list=new int[listToHide.length];
                    for (int j=0;j<list.length;j++){
                        list[j]=Integer.parseInt(listToHide[j]);
                    }
                    if (Build.VERSION.SDK_INT==27){
                        CsOperation.hideSettingsAndroidGo(list);
                    }else if (Build.VERSION.SDK_INT==29){
                        for (int j=0;j<list.length;j++){
                            String line=settingsLine[list[j]];
                            CsOperation.hideSettingsAndroidQ(line);
                        }

                    }

                    Utils.showLogs(TAG,"KIOSK_SETTINGS_LEVEL1_HIDE ==>"+Utils.showArray(list));
                    list=null;
                    break;
                case KIOSK_SETTINGS_LEVEL1_RESET:
                    Utils.showLogs(TAG,"KIOSK_SETTINGS_LEVEL1_RESET");
                    CsOperation.resetSettings();
                    break;
                case KIOSK_STATUSBAR_HIDE:
                    Utils.showLogs(TAG,"KIOSK_STATUSBAR_HIDE");
                    CsOperation.hideStatusbar();

                    break;
                case KIOSK_STATUSBAR_SHOW:
                    Utils.showLogs(TAG,"KIOSK_STATUSBAR_SHOW");
                    CsOperation.showStatusbar();
                    break;
                case KIOSK_CONTROLPANEL_HIDE:
                    Utils.showLogs(TAG,"KIOSK_CONTROLPANEL_HIDE");
                    CsOperation.blockPanel(true);
                    break;
                case KIOSK_CONTROLPANEL_SHOW:
                    Utils.showLogs(TAG,"KIOSK_CONTROLPANEL_SHOW");
                    CsOperation.blockPanel(false);
                    break;

                case SOFTWARE_SHUTDOWN:
                    Utils.showLogs(TAG,"SOFTWARE_SHUTDOWN");
                    CsReboot.shutDown();
                    break;
                case SOFTWARE_REBOOT:
                    Utils.showLogs(TAG,"SOFTWARE_REBOOT");
                    CsReboot.sofewareReboot();
                    break;
                case SOFTWARE_RESET:
                    Utils.showLogs(TAG,"SOFTWARE_RESET");
                    CsReboot.factoryReset();
                    break;
                case SOFTWARE_DESTROY:
                    Utils.showLogs(TAG,"SOFTWARE_DESTROY");
                    CsReboot.destory();
                    break;
                case WIFI_CONNECT:
                    int securityType=intent.getIntExtra("securityType",1);
                    String ssid=intent.getStringExtra("ssid");
                    String key=intent.getStringExtra("key");
                    boolean resultConnectWifi= CsWifi.connectToWiFi(securityType,ssid,key);
                    Utils.showLogs(TAG,"WIFI_CONNECT ==>" +securityType+ " - "+ssid+" - "+key+"\n"+resultConnectWifi);
                    break;
                case WIFI_DISABLE:
                    CsWifi.disableWifi();
                    Utils.showLogs(TAG,"WIFI_DISABLE");
                    break;

                case WIFI_ENABLE:
                    CsWifi.enableWifi();
                    Utils.showLogs(TAG,"WIFI_ENABLE");
                    break;
                case WIFI_DISABLE_HOTSPOT:
                    CsWifi.disableHotspot();
                    Utils.showLogs(TAG,"WIFI_DISABLE_HOTSPOT");
                    break;
                case WIFI_ENABLE_HOTSPOT:
                    CsWifi.enableHotspot();
                    Utils.showLogs(TAG,"WIFI_ENABLE_HOTSPOT");
                    break;
                case WIFI_UPDATE_HOTSPOT:
                    String name=intent.getStringExtra("name");
                    String password=intent.getStringExtra("password");
                    CsWifi.editHotspot(name,password);
                    Utils.showLogs(TAG,"WIFI_UPDATE_HOTSPOT ==>" +name+" - "+password);
                    break;

                case WIFI_GET_STATUS:
                    Utils.showLogs(TAG,"WIFI_GET_STATUS ==>" +CsWifi.getWifiStatus());
                    break;
                case WIFI_GET_LIST:
                    List<ScanResult> listScanWifi=CsWifi.getListWifi();
                    result="{\n";
                    if (listScanWifi !=null){
                        for (int ind=0;ind<listScanWifi.size();ind++){
                            result=result+listScanWifi.get(ind).toString()+",\n";
                        }
                        result=result+"\n}";
                    }
                    Utils.showLogs(TAG,"WIFI_GET_LIST\n" +result);
                    break;

                case DATA_GET_STATUS:
                    Utils.showLogs(TAG,"DATA_GET_STATUS  ==>"+ CsData.getDataStatus());
                    break;
                case DATA_DISABLE:
                    Utils.showLogs(TAG,"DATA_DISABLE");
                    CsData.disableData();
                    break;
                case DATA_ENABLE:
                    Utils.showLogs(TAG,"DATA_ENABLE");
                    CsData.enableData();
                    break;
                case DATA_SET_DEFAULT_SIM2:
                    Utils.showLogs(TAG,"DATA_SET_DEFAULT_SIM2 ==>"+CsData.setDefaultSimTwo());

                    break;
                case DATA_SET_DEFAULT_SIM1:
                    Utils.showLogs(TAG,"DATA_SET_DEFAULT_SIM1 ==>"+CsData.setDefaultSimOne());
                    break;


                case OPERATION_CHANGE_BOOT_ANIMATION:
                    path=intent.getStringExtra("path");
                    name=intent.getStringExtra("name");
                    boolean valueChangeAnimation = CsOperation.changeBootAnimation(path,name);
                    Utils.showLogs(TAG,"OPERATION_CHANGE_BOOT_ANIMATION ==>"+path+" - "+name+"\n"+valueChangeAnimation);
                    break;
                case OPERATION_CHANGE_SHUT_ANIMATION:
                    path=intent.getStringExtra("path");
                    name=intent.getStringExtra("name");
                    valueChangeAnimation = CsOperation.changeShutAnimation(path,name);
                    Utils.showLogs(TAG,"OPERATION_CHANGE_SHUT_ANIMATION ==>"+path+" - "+name+"\n"+valueChangeAnimation);
                    break;
                case OPERATION_GET_LANGUAGE_LIST:
                    result = TextUtils.join(", ", CsOperation.getLngList());
                    Utils.showLogs(TAG,"OPERATION_GET_LANGUAGE_LIST \n"+result);
                    break;
                case OPERATION_CHANGE_LANGUAGE:
                    int valuel =intent.getIntExtra("i",1);
                    CsOperation.setLanguage(valuel);
                    Utils.showLogs(TAG,"OPERATION_CHANGE_LANGUAGE ==>"+valuel);
                    break;
                case OPERATION_CHANGE_TIMEZONE:
                    String zone=intent.getStringExtra("zone");
                    CsOperation.setSysZone(zone);
                    Utils.showLogs(TAG,"OPERATION_CHANGE_TIMEZONE ==>"+zone);
                    break;

                case OPERATION_CHANGE_WALLPAPER:
                    path=intent.getStringExtra("path");
                    CsOperation.changeWallpager(path);
                    Utils.showLogs(TAG,"OPERATION_CHANGE_WALLPAPER ==>"+path);
                    break;
                case LOCATION_ENABLE:
                    CsLocation.enableLocation();
                    Utils.showLogs(TAG,"LOCATION_ENABLE");
                    break;
                case LOCATION_DISABLE:
                    CsLocation.disableLocation();
                    Utils.showLogs(TAG,"LOCATION_DISABLE");
                    break;
                case LOCATION_SET_MODE:
                    int mode=intent.getIntExtra("mode",1);
                    CsLocation.setLocationMode(mode);
                    Utils.showLogs(TAG,"LOCATION_SET_MODE ==>"+mode);
                    break;
                case LOCATION_GET_MODE:
                     mode=CsLocation.getCurLocationMode();
                    Utils.showLogs(TAG,"LOCATION_GET_MODE ==>"+mode);
                    break;
                case LOCATION_GET_ADDRESS:
                    Utils.showLogs(TAG,"LOCATION_GET_ADDRESS ==>"+GPSTracker.address+" - "+GPSTracker.country);
                    break;
                case LOCATION_GET_LOCATION:
                    Utils.showLogs(TAG,"LOCATION_GET_LOCATION ==>"+GPSTracker.latitude+" - "+GPSTracker.longitude);
                    break;

                case SECURITY_ADB_DISABLE:
                    CsStorage.disableAdb();
                    Utils.showLogs(TAG,"SECURITY_ADB_DISABLE");
                    break;
                case SECURITY_ADB_ENABLE:
                    CsStorage.enableAdb();
                    Utils.showLogs(TAG,"SECURITY_ADB_ENABLE");
                    break;
                case SECURITY_STORAGE_DISABLE:
                    CsStorage.disableStorage();
                    Utils.showLogs(TAG,"SECURITY_STORAGE_DISABLE");
                    break;
                case SECURITY_STORAGE_ENABLE:
                    CsStorage.enableStorage();
                    Utils.showLogs(TAG,"SECURITY_STORAGE_ENABLE");
                    break;
                case SECURITY_PTP_DISABLE:

                    CsStorage.disablePtp();
                    Utils.showLogs(TAG,"SECURITY_PTP_DISABLE");
                    break;
                case SECURITY_PTP_ENABLE:
                    CsStorage.enablePtp();
                    Utils.showLogs(TAG,"SECURITY_PTP_ENABLE");
                    break;
                case SECURITY_MTP_DISABLE:
                    CsStorage.disableMtp();
                    Utils.showLogs(TAG,"SECURITY_MTP_DISABLE");
                    break;
                case SECURITY_MTP_ENABLE:
                    CsStorage.enableMtp();
                    Utils.showLogs(TAG,"SECURITY_MTP_ENABLE");
                    break;
                case SECURITY_UNKNOWNSOURCE_DISABLE:
                    CsStorage.disableUnknownSource();
                    Utils.showLogs(TAG,"SECURITY_UNKNOWNSOURCE_DISABLE");
                    break;
                case SECURITY_UNKNOWNSOURCE_ENABLE:
                    CsStorage.enableUnknownSource();
                    Utils.showLogs(TAG,"SECURITY_UNKNOWNSOURCE_ENABLE");
                    break;
                case BLUETOOTH_CONNECT:
                    int indexList=intent.getIntExtra("i",1);
                    CsBluetooth.connectToBluetooth(indexList);
                    Utils.showLogs(TAG,"BLUETOOTH_CONNECT : "+indexList);
                    break;
                case BLUETOOTH_DISABLE:
                    Utils.showLogs(TAG,"BLUETOOTH_CONNECT");
                    CsBluetooth.disableBluetooth();
                    break;
                case BLUETOOTH_GET_LIST_PAIR:
                    /*result = TextUtils.join(", ", CsBluetooth.getDevicePairList());
                    Utils.showLogs(TAG,"BLUETOOTH_GET_LIST_PAIR\n"+result);*/
                    break;
                case BLUETOOTH_GET_LIST_UNPAIR:
                    /*result = TextUtils.join(", ", CsBluetooth.getDeviceUnPairList());
                    Utils.showLogs(TAG,"BLUETOOTH_GET_LIST_UNPAIR\n"+result);*/
                    break;
                case BLUETOOTH_ENABLE:
                    Utils.showLogs(TAG,"BLUETOOTH_ENABLE");
                    CsBluetooth.enableBluetooth();
                    break;
                case SIMULATION_TOUCH_LONG:
                    float x=intent.getIntExtra("x",1);
                    float y=intent.getIntExtra("y",1);
                    CsTouchButtonSimulation.touchLong(x,y);
                    Utils.showLogs(TAG,"SIMULATION_TOUCH_LONG ==>"+x+" - "+y);
                    break;
                case SIMULATION_TOUCH:
                    x=intent.getIntExtra("x",1);
                    y=intent.getIntExtra("y",1);
                    CsTouchButtonSimulation.touch(x,y);
                    Utils.showLogs(TAG,"SIMULATION_TOUCH ==>"+x+" - "+y);
                    break;
                case SIMULATION_SWIPE:
                     x=intent.getIntExtra("x",1);
                     y=intent.getIntExtra("y",1);
                    float x1=intent.getIntExtra("x1",1);
                    float y1=intent.getIntExtra("y1",1);
                    int time=intent.getIntExtra("time",1);
                    CsTouchButtonSimulation.swipe(x,y,x1,y1,time);
                    Utils.showLogs(TAG,"SIMULATION_BUTTON_CLICK ==>"+x+" - "+y+" - "+x1+" - "+y1+" - "+time+" - ");
                    break;

                case SIMULATION_BUTTON_CLICK:
                    int keyCode=intent.getIntExtra("keyCode",1);
                    CsTouchButtonSimulation.clickButton(keyCode);
                    Utils.showLogs(TAG,"SIMULATION_BUTTON_CLICK ==>"+keyCode);
                    break;

                case OPERATION_SCREENSHOT:
                    Utils.showLogs(TAG,"OPERATION_SCREENSHOT");
                    CsOperation.screenShot();
                    break;

                case USB_ENABLE_FILE_TRANSFER:
                    Utils.showLogs(TAG,"USB_ENABLE_FILE_TRANSFER");
                    CsStorage.enableUseUsbFor("File Transfer");
                    break;
                case USB_DISABLE_FILE_TRANSFER:
                    Utils.showLogs(TAG,"USB_DISABLE_FILE_TRANSFER");
                    CsStorage.disableUseUsbFor("File Transfer");
                    break;
                case USB_ENABLE_MIDI:
                    Utils.showLogs(TAG,"USB_ENABLE_MIDI");
                    CsStorage.enableUseUsbFor("MIDI");
                    break;
                case USB_DISABLE_MIDI:
                    Utils.showLogs(TAG,"USB_DISABLE_MIDI");
                    CsStorage.disableUseUsbFor("MIDI");
                    break;
                case USB_ENABLE_USB:
                    Utils.showLogs(TAG,"USB_ENABLE_USB");
                    CsStorage.enableUseUsbFor("USB tethering");
                    break;
                case USB_DISABLE_USB:
                    Utils.showLogs(TAG,"USB_DISABLE_USB");
                    CsStorage.disableUseUsbFor("USB tethering");
                    break;
                case USB_ENABLE_PTP:
                    Utils.showLogs(TAG,"USB_ENABLE_PTP");
                    CsStorage.enableUseUsbFor("PTP");
                    break;
                case USB_DISABLE_PTP:
                    Utils.showLogs(TAG,"USB_DISABLE_PTP");
                    CsStorage.disableUseUsbFor("PTP");
                    break;
            }
        }
        return START_STICKY;
    }


    public ExecuteFunctionsService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
