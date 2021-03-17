package com.mobiiot.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.api.Utils.Utils;

public class Cs_KeyStore {


    public static void addKeystoreToList( byte[] keystore) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.addKeystoreToList(keystore);
                Utils.log(MobiiotAPI.TAG, "add keystore ");
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.addKeystoreToList(keystore);
                Utils.log(MobiiotAPI.TAG, "add keystore ");
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.addKeystoreToList(keystore);
                Utils.log(MobiiotAPI.TAG, "add keystore ");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void resetListKey( ) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.resetListKey();
                Utils.log(MobiiotAPI.TAG, "Reset keystore ");
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.resetListKey();
                Utils.log(MobiiotAPI.TAG, "Reset keystore ");
            }else if (iMyAidlInterfaceGoMPE != null) {
                iMyAidlInterfaceGoMPE.resetListKey();
                Utils.log(MobiiotAPI.TAG, "Reset keystore ");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }



    public static Boolean doesKeyExist(byte[] keystore) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {
                Utils.log(MobiiotAPI.TAG, "does keystore Exist");
                return iMyAidlInterface.doesKeyExist(keystore);
            }else if (iMyAidlInterface2 != null) {

                Utils.log(MobiiotAPI.TAG, "add keystore");
                return iMyAidlInterface2.doesKeyExist(keystore);
            }else if (iMyAidlInterfaceGoMPE != null) {

                Utils.log(MobiiotAPI.TAG, "add keystore");
                return iMyAidlInterfaceGoMPE.doesKeyExist(keystore);
            }else {
                Utils.log(MobiiotAPI.TAG, "does keystore Exist");
                return null;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;

        }
    }

}
