package com.mobiiot.mp3p.api;

import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.mp3p.api.Utils.ServiceUtil;
import com.mobiiot.mp3p.api.Utils.Utils;

public class CsStorage {


    public static void enableStorage() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.enableStorage();
                Utils.log(MobiiotAPI.TAG, "enable Storage ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    //support Android GO
    public static void disableStorage() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.disableStorage();
                Utils.log(MobiiotAPI.TAG, "disable Storage ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void enableUnknownSource() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "enable Unknown source ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "enable Unknown source ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void disableUnknownSource() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "disable Unknown source ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableUnknownSource();
                Utils.log(MobiiotAPI.TAG, "disable Unknown source ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void enableAdb() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableAdb();
                Utils.log(MobiiotAPI.TAG, "enable adb ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableAdb();
                Utils.log(MobiiotAPI.TAG, "enable adb ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void disableAdb() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableAdb();
                Utils.log(MobiiotAPI.TAG, "disable adb ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableAdb();
                Utils.log(MobiiotAPI.TAG, "disable adb ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    //support Android GO
    public static void enableMtp() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.enableMtp();
                Utils.log(MobiiotAPI.TAG, "enable Mtp ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    //support Android GO
    public static void disableMtp() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.disableMtp();
                Utils.log(MobiiotAPI.TAG, "disable Mtp ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    //support Android GO
    public static void enablePtp() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.enablePtp();
                Utils.log(MobiiotAPI.TAG, "enable Ptp ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    //support Android GO
    public static void disablePtp() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.disablePtp();
                Utils.log(MobiiotAPI.TAG, "disable Ptp ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }


}
