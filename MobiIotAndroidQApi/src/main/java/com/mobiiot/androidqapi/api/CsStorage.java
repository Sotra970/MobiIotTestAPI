package com.mobiiot.androidqapi.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.androidqapi.api.Utils.ServiceUtil;
import com.mobiiot.androidqapi.api.Utils.Utils;

public class CsStorage {



    public static void enableUnknownSource() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "enable Unknown source ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "enable Unknown source ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void disableUnknownSource() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "disable Unknown source ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "disable Unknown source ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void enableAdb() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableAdb();
                Utils.log(MobiiotAPI.TAG, "enable adb ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableAdb();
                Utils.log(MobiiotAPI.TAG, "enable adb ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void disableAdb() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableAdb();
                Utils.log(MobiiotAPI.TAG, "disable adb ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableAdb();
                Utils.log(MobiiotAPI.TAG, "disable adb ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //support Android Q
    public static void enableUseUsbFor(String useUsbFor){
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.enableUseUsbFor(useUsbFor);
                Utils.log(MobiiotAPI.TAG, "enable : "+useUsbFor);

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    //support Android Q
    public static void disableUseUsbFor(String useUsbFor){
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.disableUseUsbFor(useUsbFor);
                Utils.log(MobiiotAPI.TAG, "disable : "+useUsbFor);

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

}
