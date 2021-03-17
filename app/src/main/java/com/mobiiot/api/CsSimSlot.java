package com.mobiiot.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.api.Utils.Utils;

import java.util.List;

public class CsSimSlot {

    public static String simRadioSwitch(int slotIndex, boolean isOn){
        //successful/failure
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                String str = iMyAidlInterface.simRadioSwitch(slotIndex,isOn);
                return str;
            }else if (iMyAidlInterface2 != null) {

                String str = iMyAidlInterface2.simRadioSwitch(slotIndex,isOn);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                String str = iMyAidlInterfaceGoMPE.simRadioSwitch(slotIndex,isOn);
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



    public static List<String> getSimNameList(){
        //successful/failure
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                List<String> str = iMyAidlInterface.getSimNameList();
                return str;
            }else if (iMyAidlInterface2 != null) {

                List<String> str = iMyAidlInterface2.getSimNameList();
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                List<String> str = iMyAidlInterfaceGoMPE.getSimNameList();
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


    public static String getCurrentSimName(){
        //successful/failure
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                String str = iMyAidlInterface.getCurrentSimName();
                Utils.log(MobiiotAPI.TAG, "sim name : "+str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                String str = iMyAidlInterface2.getCurrentSimName();
                Utils.log(MobiiotAPI.TAG, "sim name : "+str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                String str = iMyAidlInterfaceGoMPE.getCurrentSimName();
                Utils.log(MobiiotAPI.TAG, "sim name : "+str);
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

    public static  String getSignalStrength(int slotId){


        try{

            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                String str = iMyAidlInterface.getSignalStrength(slotId);
                Utils.log(MobiiotAPI.TAG, "signal strengh : "+slotId +" = "+str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                String str = iMyAidlInterface2.getSignalStrength(slotId);
                Utils.log(MobiiotAPI.TAG, "signal strengh : "+slotId +" = "+str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                String str = iMyAidlInterfaceGoMPE.getSignalStrength(slotId);
                Utils.log(MobiiotAPI.TAG, "signal strengh : "+slotId +" = "+str);
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
