package com.mobiiot.androidqapi.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.androidqapi.api.Utils.ServiceUtil;
import com.mobiiot.androidqapi.api.Utils.Utils;


public class CsScanner {



    public static void openNewScan(boolean isCountinousScan){
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.openNewScan(isCountinousScan);
                Utils.log(MobiiotAPI.TAG, "Start scan");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.openNewScan(isCountinousScan);
                Utils.log(MobiiotAPI.TAG, "Start scan");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.openNewScan(isCountinousScan);
                Utils.log(MobiiotAPI.TAG, "Start scan");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static String[] getNewScanInfo() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                String[] str = iMyAidlInterface.getNewScanInfo();
                Utils.log(MobiiotAPI.TAG, "get result scan : " + str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                String[] str = iMyAidlInterface2.getNewScanInfo();
                Utils.log(MobiiotAPI.TAG, "get result scan : " + str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                String[] str = iMyAidlInterfaceGoMPE.getNewScanInfo();
                Utils.log(MobiiotAPI.TAG, "get result scan : " + str);
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

    public static String getScanInfo() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                String str = iMyAidlInterface.getScanInfo();
                Utils.log(MobiiotAPI.TAG, "get result scan : " + str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                String str = iMyAidlInterface2.getScanInfo();
                Utils.log(MobiiotAPI.TAG, "get result scan : " + str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                String str = iMyAidlInterfaceGoMPE.getScanInfo();
                Utils.log(MobiiotAPI.TAG, "get result scan : " + str);
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
