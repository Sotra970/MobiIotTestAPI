package com.mobiiot.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.api.Utils.Utils;

public class CsReboot {

    public static void shutDown() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.shutDown();
                Utils.log(MobiiotAPI.TAG, "shut down");
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.shutDown();
                Utils.log(MobiiotAPI.TAG, "shut down");
            }else if (iMyAidlInterfaceGoMPE != null) {
                iMyAidlInterfaceGoMPE.shutDown();
                Utils.log(MobiiotAPI.TAG, "shut down");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }

    }

    public static void sofewareReboot() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.sofewareReboot();
                Utils.log(MobiiotAPI.TAG, "reboot");
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.sofewareReboot();
                Utils.log(MobiiotAPI.TAG, "reboot");
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.sofewareReboot();
                Utils.log(MobiiotAPI.TAG, "reboot");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void factoryReset() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.factoryReset();
                Utils.log(MobiiotAPI.TAG, "factory reset");
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.factoryReset();
                Utils.log(MobiiotAPI.TAG, "factory reset");
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.factoryReset();
                Utils.log(MobiiotAPI.TAG, "factory reset");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }

    }

    public static void destory() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.destory();
                Utils.log(MobiiotAPI.TAG, "destory");
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.destory();
                Utils.log(MobiiotAPI.TAG, "destory");
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.destory();
                Utils.log(MobiiotAPI.TAG, "destory");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

}
