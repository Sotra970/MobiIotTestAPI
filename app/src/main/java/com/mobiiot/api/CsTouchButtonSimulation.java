package com.mobiiot.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.api.Utils.Utils;

public class CsTouchButtonSimulation {



    public static void touch(float x,float y) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.touch(x,y);
                Utils.log(MobiiotAPI.TAG, "touch : " + x+" - "+y);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.touch(x,y);
                Utils.log(MobiiotAPI.TAG, "touch : " + x+" - "+y);

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.touch(x,y);
                Utils.log(MobiiotAPI.TAG, "touch : " + x+" - "+y);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void touchLong(float x,float y) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.touchLong(x,y);
                Utils.log(MobiiotAPI.TAG, "touchLong : " + x+" - "+y);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.touchLong(x,y);
                Utils.log(MobiiotAPI.TAG, "touchLong : " + x+" - "+y);

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.touchLong(x,y);
                Utils.log(MobiiotAPI.TAG, "touchLong : " + x+" - "+y);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void swipe(float x,float y,float x1,float y1,int time) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.swipe(x,y,x1,y1,time);
                Utils.log(MobiiotAPI.TAG, "swipe : " + x+" - "+y+" - "+ x1+" - "+y1+" - "+time);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.swipe(x,y,x1,y1,time);
                Utils.log(MobiiotAPI.TAG, "swipe : " + x+" - "+y+" - "+ x1+" - "+y1+" - "+time);

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.swipe(x,y,x1,y1,time);
                Utils.log(MobiiotAPI.TAG, "swipe : " + x+" - "+y+" - "+ x1+" - "+y1+" - "+time);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void clickButton(int keyCode) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.clickButton(keyCode);
                Utils.log(MobiiotAPI.TAG, "clickButton: " + keyCode);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.clickButton(keyCode);
                Utils.log(MobiiotAPI.TAG, "clickButton: " + keyCode);

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.clickButton(keyCode);
                Utils.log(MobiiotAPI.TAG, "clickButton: " + keyCode);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
