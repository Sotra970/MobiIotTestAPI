package com.mobiiot.mp3p.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.mp3p.api.Utils.ServiceUtil;
import com.mobiiot.mp3p.api.Utils.Utils;

public class CsLocation {

    public static int getCurLocationMode() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {


                int str = iMyAidlInterface.getCurLocationMode();
                Utils.log(MobiiotAPI.TAG, "get current location mode : " + str);
                return str;

            }else if (iMyAidlInterface2 != null) {
                int str = iMyAidlInterface2.getCurLocationMode();
                Utils.log(MobiiotAPI.TAG, "get current location mode : " + str);
                return str;

            }else if (iMyAidlInterfaceGoMPE != null) {
                int str = iMyAidlInterfaceGoMPE.getCurLocationMode();
                Utils.log(MobiiotAPI.TAG, "get current location mode : " + str);
                return str;

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return -1;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static void setLocationMode(int mode) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.setLocationMode(mode);
                Utils.log(MobiiotAPI.TAG, "set mode location : " + mode);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.setLocationMode(mode);
                Utils.log(MobiiotAPI.TAG, "set mode location : " + mode);

            }else if (iMyAidlInterfaceGoMPE != null) {
                iMyAidlInterfaceGoMPE.setLocationMode(mode);
                Utils.log(MobiiotAPI.TAG, "set mode location : " + mode);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    public static  String getLocation(){
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {
                String str = iMyAidlInterface.getLocation();
                Utils.log(MobiiotAPI.TAG, "get location  : "+str);
                return str;

            }else if (iMyAidlInterface2 != null) {
                String str = iMyAidlInterface2.getLocation();
                Utils.log(MobiiotAPI.TAG, "get location  : "+str);
                return str;

            }else if (iMyAidlInterfaceGoMPE != null) {
                String str = iMyAidlInterfaceGoMPE.getLocation();
                Utils.log(MobiiotAPI.TAG, "get location  : "+str);
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


    public static  String getAddress(){

        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {
                String str = iMyAidlInterface.getAddress();
                Utils.log(MobiiotAPI.TAG, "get address  : "+str);
                return str;
            }else if (iMyAidlInterface2 != null) {
                String str = iMyAidlInterface2.getAddress();
                Utils.log(MobiiotAPI.TAG, "get address  : "+str);
                return str;

            }else if (iMyAidlInterfaceGoMPE != null) {
                String str = iMyAidlInterfaceGoMPE.getAddress();
                Utils.log(MobiiotAPI.TAG, "get address  : "+str);
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


    public static void enableLocation() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableLocation();
                Utils.log(MobiiotAPI.TAG, "enable location ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableLocation();
                Utils.log(MobiiotAPI.TAG, "enable location ");

            }else if (iMyAidlInterfaceGoMPE != null) {
                iMyAidlInterfaceGoMPE.enableLocation();
                Utils.log(MobiiotAPI.TAG, "enable location ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public static void disableLocation() {

        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableLocation();
                Utils.log(MobiiotAPI.TAG, "disable location ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableLocation();
                Utils.log(MobiiotAPI.TAG, "disable location ");

            }else if (iMyAidlInterfaceGoMPE != null) {
                iMyAidlInterfaceGoMPE.disableLocation();
                Utils.log(MobiiotAPI.TAG, "disable location ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}
