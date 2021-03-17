package com.mobiiot.api;

import android.os.RemoteException;

import com.mediatek.settings.service.APN;
import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.api.Utils.Utils;

import java.util.List;

public class CsApn {


    public static Boolean updateApn(APN newApn, boolean force) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {


                Utils.log(MobiiotAPI.TAG, "update Apn : " + newApn.toString());
                return  iMyAidlInterface.updateApn(newApn, force);
            }else if (iMyAidlInterface2 != null) {


                Utils.log(MobiiotAPI.TAG, "update Apn : " + newApn.toString());
                return  iMyAidlInterface2.updateApn(newApn, force);
            }else if (iMyAidlInterfaceGoMPE != null) {


                Utils.log(MobiiotAPI.TAG, "update Apn : " + newApn.toString());
                return  iMyAidlInterfaceGoMPE.updateApn(newApn, force);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean removeAPN(String apnKey) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {


                Utils.log(MobiiotAPI.TAG, "remove Apn : " + apnKey);
                return  iMyAidlInterface.removeAPN(apnKey);
            }else if (iMyAidlInterface2 != null) {


                Utils.log(MobiiotAPI.TAG, "remove Apn : " + apnKey);
                return  iMyAidlInterface2.removeAPN(apnKey);
            }else if (iMyAidlInterfaceGoMPE != null) {
                Utils.log(MobiiotAPI.TAG, "remove Apn : " + apnKey);
                return  iMyAidlInterfaceGoMPE.removeAPN(apnKey);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void getOperator(int slotId) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.getOperator(slotId);
                Utils.log(MobiiotAPI.TAG, "get Operator : " + slotId );

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.getOperator(slotId);
                Utils.log(MobiiotAPI.TAG, "get Operator : " + slotId );
            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.getOperator(slotId);
                Utils.log(MobiiotAPI.TAG, "get Operator : " + slotId );
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static Boolean addApn(APN newApn, boolean force) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {


                Utils.log(MobiiotAPI.TAG, "Add Apn : " + newApn.toString());
                return  iMyAidlInterface.addApn(newApn, force);
            }else if (iMyAidlInterface2 != null) {


                Utils.log(MobiiotAPI.TAG, "Add Apn : " + newApn.toString());
                return  iMyAidlInterface2.addApn(newApn, force);
            }else if (iMyAidlInterfaceGoMPE != null) {


                Utils.log(MobiiotAPI.TAG, "Add Apn : " + newApn.toString());
                return  iMyAidlInterfaceGoMPE.addApn(newApn, force);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static APN getAPN(int slotId) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                APN str = iMyAidlInterface.getAPN(slotId);
                Utils.log(MobiiotAPI.TAG, "get Apn : " + slotId + " = " + str.toString());
                return str;
            }else if (iMyAidlInterface2 != null) {

                APN str = iMyAidlInterface2.getAPN(slotId);
                Utils.log(MobiiotAPI.TAG, "get Apn : " + slotId + " = " + str.toString());
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {


                APN str = iMyAidlInterfaceGoMPE.getAPN(slotId);
                Utils.log(MobiiotAPI.TAG, "get Apn : " + slotId + " = " + str.toString());
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


    public static List<APN> getApnList(int slotId) {

        try{

            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                List<APN> str = iMyAidlInterface.getApnList(slotId);
                Utils.log(MobiiotAPI.TAG, "list Apn : " + slotId);
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "list Apn : " + str.get(i) + " = " + str.get(i).toString());
                }

                return str;
            }else if (iMyAidlInterface2 != null) {

                List<APN> str = iMyAidlInterface2.getApnList(slotId);
                Utils.log(MobiiotAPI.TAG, "list Apn : " + slotId);
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "list Apn : " + str.get(i) + " = " + str.get(i).toString());
                }
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {
                List<APN> str = iMyAidlInterfaceGoMPE.getApnList(slotId);
                Utils.log(MobiiotAPI.TAG, "list Apn : " + slotId);
                for (int i = 0; i < str.size(); i++) {
                    Utils.log(MobiiotAPI.TAG, "list Apn : " + str.get(i) + " = " + str.get(i).toString());
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





    public static boolean restoreDefault() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                boolean str = iMyAidlInterface.restoreDefault();
                Utils.log(MobiiotAPI.TAG, "restore Default : " + str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                boolean str = iMyAidlInterface2.restoreDefault();
                Utils.log(MobiiotAPI.TAG, "restore Default : " + str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {
                boolean str = iMyAidlInterfaceGoMPE.restoreDefault();
                Utils.log(MobiiotAPI.TAG, "restore Default : " + str);
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




    public static  String getMccMnc(int slotId){

        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                String str = iMyAidlInterface.getMccMnc(slotId);
                Utils.log(MobiiotAPI.TAG, "mcc & mnc : " + str);
                return str;
            }else if (iMyAidlInterface2 != null) {

                String str = iMyAidlInterface2.getMccMnc(slotId);
                Utils.log(MobiiotAPI.TAG, "mcc & mnc : " + str);
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {
                String str = iMyAidlInterfaceGoMPE.getMccMnc(slotId);
                Utils.log(MobiiotAPI.TAG, "mcc & mnc : " + str);
                return str;
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return "-";
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return "-";
        }
    }
}
