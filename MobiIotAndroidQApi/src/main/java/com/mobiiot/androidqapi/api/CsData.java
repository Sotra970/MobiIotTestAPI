package com.mobiiot.androidqapi.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.androidqapi.api.Utils.ServiceUtil;
import com.mobiiot.androidqapi.api.Utils.Utils;

public class CsData {

    public static boolean setDefaultSimOne() {

        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                boolean str = iMyAidlInterface.setDefaultSimOne();
                iMyAidlInterface.enableData();
                Utils.log(MobiiotAPI.TAG, "set default Sim 1 : " + str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                boolean str = iMyAidlInterface2.setDefaultSimOne();
                iMyAidlInterface2.enableData();
                Utils.log(MobiiotAPI.TAG, "set default Sim 1 : " + str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                boolean str = iMyAidlInterfaceGoMPE.setDefaultSimOne();
                iMyAidlInterface2.enableData();
                Utils.log(MobiiotAPI.TAG, "set default Sim 1 : " + str);
                return str;
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return false;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean setDefaultSimTwo() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                boolean str = iMyAidlInterface.setDefaultSimTwo();
                iMyAidlInterface.enableData();
                Utils.log(MobiiotAPI.TAG, "set default Sim 2 : " + str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                boolean str = iMyAidlInterface2.setDefaultSimTwo();
                iMyAidlInterface2.enableData();
                Utils.log(MobiiotAPI.TAG, "set default Sim 2 : " + str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                boolean str = iMyAidlInterfaceGoMPE.setDefaultSimTwo();
                iMyAidlInterface2.enableData();
                Utils.log(MobiiotAPI.TAG, "set default Sim 2 : " + str);
                return str;
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return false;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void disableData() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableData();
                Utils.log(MobiiotAPI.TAG, "disable data ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableData();
                Utils.log(MobiiotAPI.TAG, "disable data ");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.disableData();
                Utils.log(MobiiotAPI.TAG, "disable data ");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void enableData() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableData();
                Utils.log(MobiiotAPI.TAG, "enable data ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableData();
                Utils.log(MobiiotAPI.TAG, "enable data ");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.enableData();
                Utils.log(MobiiotAPI.TAG, "enable data ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static  String getDataStatus(){
        //successful/failure
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {


                Utils.log(MobiiotAPI.TAG, "get data status");
                return iMyAidlInterface.getDataStatus();

            }else if (iMyAidlInterface2 != null) {
                Utils.log(MobiiotAPI.TAG, "get data status");
                return iMyAidlInterface2.getDataStatus();

            }else if (iMyAidlInterfaceGoMPE != null) {

                Utils.log(MobiiotAPI.TAG, "get data status");
                return iMyAidlInterfaceGoMPE.getDataStatus();
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
