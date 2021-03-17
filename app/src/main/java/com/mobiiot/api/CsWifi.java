package com.mobiiot.api;

import android.net.wifi.ScanResult;
import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.api.Utils.Utils;

import java.util.List;

public class CsWifi {

    public static void enableWifi() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableWifi();
                Utils.log(MobiiotAPI.TAG, "enable wifi");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableWifi();
                Utils.log(MobiiotAPI.TAG, "enable wifi");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.enableWifi();
                Utils.log(MobiiotAPI.TAG, "enable wifi");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void disableWifi() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableWifi();
                Utils.log(MobiiotAPI.TAG, "disable wifi");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableWifi();
                Utils.log(MobiiotAPI.TAG, "disable wifi");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.disableWifi();
                Utils.log(MobiiotAPI.TAG, "disable wifi");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static  String getWifiStatus(){
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {


                String str = iMyAidlInterface.getWifiStatus();
                Utils.log(MobiiotAPI.TAG, "wifi status : "+str);
                return str;

            }else if (iMyAidlInterface2 != null) {
                String str = iMyAidlInterface2.getWifiStatus();
                Utils.log(MobiiotAPI.TAG, "wifi status : "+str);
                return str;

            }else if (iMyAidlInterfaceGoMPE != null) {

                String str = iMyAidlInterfaceGoMPE.getWifiStatus();
                Utils.log(MobiiotAPI.TAG, "wifi status : "+str);
                return str;

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void disableHotspot() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableHotspot();
                Utils.log(MobiiotAPI.TAG, "disable hotspot wifi");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableHotspot();
                Utils.log(MobiiotAPI.TAG, "disable hotspot wifi");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.disableHotspot();
                Utils.log(MobiiotAPI.TAG, "disable hotspot wifi");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void enableHotspot() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableHotspot();
                Utils.log(MobiiotAPI.TAG, "enable hotspot wifi");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableHotspot();
                Utils.log(MobiiotAPI.TAG, "enable hotspot wifi");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.enableHotspot();
                Utils.log(MobiiotAPI.TAG, "enable hotspot wifi");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void editHotspot(String name, String password) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.editHotspot(name,password);
                Utils.log(MobiiotAPI.TAG, "edit hotspot wifi : "+name);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.editHotspot(name,password);
                Utils.log(MobiiotAPI.TAG, "edit hotspot wifi : "+name);

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.editHotspot(name,password);
                Utils.log(MobiiotAPI.TAG, "edit hotspot wifi : "+name);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static Boolean connectToWiFi(int securityType, String ssid, String key) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                boolean str = iMyAidlInterface.connectToWiFi(securityType,  ssid,  key);
                Utils.log(MobiiotAPI.TAG, "connect to wifi : " + str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                boolean str = iMyAidlInterface2.connectToWiFi(securityType,  ssid,  key);
                Utils.log(MobiiotAPI.TAG, "connect to wifi : " + str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                boolean str = iMyAidlInterfaceGoMPE.connectToWiFi(securityType,  ssid,  key);
                Utils.log(MobiiotAPI.TAG, "connect to wifi : " + str);
                return str;

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<ScanResult> getListWifi() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                List<ScanResult> str = iMyAidlInterface.getListWifi();
                Utils.log(MobiiotAPI.TAG, "get list wifi ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "list wifi : " + str.get(i) + " = " + str.get(i));
                }
                return str;
            }else if (iMyAidlInterface2 != null) {

                List<ScanResult> str = iMyAidlInterface2.getListWifi();
                Utils.log(MobiiotAPI.TAG, "get list wifi ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "list wifi : " + str.get(i) + " = " + str.get(i));
                }
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                List<ScanResult> str = iMyAidlInterfaceGoMPE.getListWifi();
                Utils.log(MobiiotAPI.TAG, "get list wifi ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "list wifi : " + str.get(i) + " = " + str.get(i));
                }
                return str;

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }


    }



}
