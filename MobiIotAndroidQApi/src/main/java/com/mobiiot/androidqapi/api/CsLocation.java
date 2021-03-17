package com.mobiiot.androidqapi.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.androidqapi.api.Utils.ServiceUtil;
import com.mobiiot.androidqapi.api.Utils.Utils;

public class CsLocation {


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
