package com.mobiiot.androidqapi.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.androidqapi.api.Utils.ServiceUtil;
import com.mobiiot.androidqapi.api.Utils.Utils;

import java.util.List;

public class CsPackage {


    public static void removeApp(String packageName) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.removeApp(packageName);
                Utils.log(MobiiotAPI.TAG, "remove Package :"+packageName);
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.removeApp(packageName);
                Utils.log(MobiiotAPI.TAG, "remove Package :"+packageName);
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.removeApp(packageName);
                Utils.log(MobiiotAPI.TAG, "remove Package :"+packageName);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }


        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }


    public static void installApp(String path , String packageName){
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.installApp(path,packageName);
                Utils.log(MobiiotAPI.TAG, "install Package :"+path+" / "+packageName);
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.installApp(path,packageName);
                Utils.log(MobiiotAPI.TAG, "install Package :"+path+" / "+packageName);
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.installApp(path,packageName);
                Utils.log(MobiiotAPI.TAG, "install Package :"+path+" / "+packageName);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }





        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }


    public static void updateApp(String path , String packageName){
        try {

            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.updateApp(path,packageName);
                Utils.log(MobiiotAPI.TAG, "update Package :"+path+" / "+packageName);
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.updateApp(path,packageName);
                Utils.log(MobiiotAPI.TAG, "update Package :"+path+" / "+packageName);
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.updateApp(path,packageName);
                Utils.log(MobiiotAPI.TAG, "update Package :"+path+" / "+packageName);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static List<String> getPackageList() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                List<String> str = iMyAidlInterface.getPackageList();
                Utils.log(MobiiotAPI.TAG, "get list package ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "get list package : " + str.get(i) + " = " + str.get(i));
                }
                return str;
            }else if (iMyAidlInterface2 != null) {

                List<String> str = iMyAidlInterface2.getPackageList();
                Utils.log(MobiiotAPI.TAG, "get list package ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "get list package : " + str.get(i) + " = " + str.get(i));
                }
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                List<String> str = iMyAidlInterfaceGoMPE.getPackageList();
                Utils.log(MobiiotAPI.TAG, "get list package ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "get list package : " + str.get(i) + " = " + str.get(i));
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

    public static void hideApp(String packageName){
        try {

            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.hideApp(packageName);
                Utils.log(MobiiotAPI.TAG, "hide Package :"+packageName);
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.hideApp(packageName);
                Utils.log(MobiiotAPI.TAG, "hide Package :"+packageName);
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.hideApp(packageName);
                Utils.log(MobiiotAPI.TAG, "hide Package :"+packageName);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }

    }

    public static void showApp(String packageName){
        try {

            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.showApp(packageName);
                Utils.log(MobiiotAPI.TAG, "show Package :"+packageName);
            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.showApp(packageName);
                Utils.log(MobiiotAPI.TAG, "show Package :"+packageName);
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.showApp(packageName);
                Utils.log(MobiiotAPI.TAG, "show Package :"+packageName);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            }
        } catch (RemoteException e) {
            e.printStackTrace();

        }

    }

    public static List<String> getMainMenuAPPList() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                List<String> str = iMyAidlInterface.getMainMenuAPPList();
                Utils.log(MobiiotAPI.TAG, "get list Main Menu package ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "get list Main Menu package : " + str.get(i) + " = " + str.get(i));
                }
                return str;
            }else if (iMyAidlInterface2 != null) {

                List<String> str = iMyAidlInterface2.getMainMenuAPPList();
                Utils.log(MobiiotAPI.TAG, "get list Main Menu package ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "get list Main Menu package : " + str.get(i) + " = " + str.get(i));
                }
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                List<String> str = iMyAidlInterfaceGoMPE.getMainMenuAPPList();
                Utils.log(MobiiotAPI.TAG, "get list Main Menu package ");
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "get list Main Menu package : " + str.get(i) + " = " + str.get(i));
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
