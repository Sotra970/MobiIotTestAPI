package com.mobiiot.mp3p.api;

import android.os.Build;
import android.os.RemoteException;
import android.util.Log;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.mp3p.api.Utils.ServiceUtil;
import com.mobiiot.mp3p.api.Utils.Utils;
public class CsOperation {

    public static  String[] getLngList(){
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                String[] str = iMyAidlInterface.getList();
                Utils.log(MobiiotAPI.TAG, "get lng list");
                return str;
            }else if (iMyAidlInterface2 != null) {

                String[] str = iMyAidlInterface2.getList();
                Utils.log(MobiiotAPI.TAG, "get lng list");
                return str;
            }else if (iMyAidlInterfaceGoMPE != null) {

                String[] str = iMyAidlInterfaceGoMPE.getList();
                Utils.log(MobiiotAPI.TAG, "get lng list");
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

    public static void setLanguage(int i) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.getLanguages(i);
                Utils.log(MobiiotAPI.TAG, "set language : " + i);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.getLanguages(i);
                Utils.log(MobiiotAPI.TAG, "set language : " + i);

            }else if (iMyAidlInterfaceGoMPE != null) {
                iMyAidlInterfaceGoMPE.getLanguages(i);
                Utils.log(MobiiotAPI.TAG, "set language : " + i);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public static void setSysZone(String zone) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.setSysZone(zone);
                Utils.log(MobiiotAPI.TAG, "set system zone : " + zone);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.setSysZone(zone);
                Utils.log(MobiiotAPI.TAG, "set system zone : " + zone);

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.setSysZone(zone);
                Utils.log(MobiiotAPI.TAG, "set system zone : " + zone);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public static Boolean changeWallpager(String path) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                boolean str = iMyAidlInterface.changeWallpager(path);
                Utils.log(MobiiotAPI.TAG, "change wallpaper : " + str);
                return str;

            }else if (iMyAidlInterface2 != null) {

                boolean str = iMyAidlInterface2.changeWallpager(path);
                Utils.log(MobiiotAPI.TAG, "change wallpaper : " + str);
                return str;

            }else if (iMyAidlInterfaceGoMPE != null) {
                boolean str = iMyAidlInterfaceGoMPE.changeWallpager(path);
                Utils.log(MobiiotAPI.TAG, "change wallpaper : " + str);
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


    public static boolean changeBootAnimation(String path, String name) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                boolean str = iMyAidlInterface.changeBootAnimation(path,name);
                Utils.log(MobiiotAPI.TAG, "change boot animation : " + str);
                return str;

            }else if (iMyAidlInterface2 != null) {

                boolean str = iMyAidlInterface2.changeBootAnimation(path,name);
                Utils.log(MobiiotAPI.TAG, "change boot animation : " + str);
                return str;

            }else if (iMyAidlInterfaceGoMPE != null) {
                boolean str = iMyAidlInterfaceGoMPE.changeBootAnimation(path,name);
                Utils.log(MobiiotAPI.TAG, "change boot animation : " + str);
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

    public static boolean changeShutAnimation(String path, String name) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                boolean str = iMyAidlInterface.changeShutAnimation(path,name);
                Utils.log(MobiiotAPI.TAG, "change shut animation : " + str);
                return str;

            }else if (iMyAidlInterface2 != null) {

                boolean str = iMyAidlInterface2.changeShutAnimation(path,name);
                Utils.log(MobiiotAPI.TAG, "change shut animation : " + str);
                return str;

            }else if (iMyAidlInterfaceGoMPE != null) {

                boolean str = iMyAidlInterfaceGoMPE.changeShutAnimation(path,name);
                Utils.log(MobiiotAPI.TAG, "change shut animation : " + str);
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

    public static void showStatusbar() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (Build.VERSION.SDK_INT==29) {

                iMyAidlInterface.showStatusbar();
                Utils.log(MobiiotAPI.TAG, "show Status bar");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.showStatusbar();
                Utils.log(MobiiotAPI.TAG, "show Status bar");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.showStatusbar();
                Utils.log(MobiiotAPI.TAG, "show Status bar");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void hideStatusbar() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (Build.VERSION.SDK_INT==29) {

                iMyAidlInterface.hideStatusbar();
                Utils.log(MobiiotAPI.TAG, "hide Status bar");

            }else if (Build.VERSION.SDK_INT==27) {

                iMyAidlInterface2.hideStatusbar();
                Utils.log(MobiiotAPI.TAG, "hide Status bar");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.hideStatusbar();
                Utils.log(MobiiotAPI.TAG, "hide Status bar");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void blockPanel(boolean status) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (Build.VERSION.SDK_INT==29) {

                iMyAidlInterface.blockPanel(status);
                Utils.log(MobiiotAPI.TAG, "block Panel" + status);

            }else if (Build.VERSION.SDK_INT==27) {

                iMyAidlInterface2.blockPanel(status);
                Utils.log(MobiiotAPI.TAG, "block Panel" + status);

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.blockPanel(status);
                Utils.log(MobiiotAPI.TAG, "block Panel" + status);
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    //support Android GO
    public static void hideSettingsAndroidGo(int[] lines) {

        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.hideSettings(lines);
                Utils.log(MobiiotAPI.TAG, "hide Settings GO" + lines.length);
            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    //support Android Q
    public static void hideSettingsAndroidQ(String lines) {

        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");

            } else {
                iMyAidlInterface.hideSettings(lines);
                Utils.log(MobiiotAPI.TAG, "hide Settings Q" + lines);
            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void resetSettings() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.resetSettings();
                Utils.log(MobiiotAPI.TAG, "reset Settings: ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.resetSettings();
                Utils.log(MobiiotAPI.TAG, "reset Settings: ");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.resetSettings();
                Utils.log(MobiiotAPI.TAG, "reset Settings: ");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void hideSettingsLevel2(String lines) {
        try {
            CsApiAndroidQ aidlInterfaceAndroidQ = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo aidlInterfaceAndroidGO = ServiceUtil.getAidlInterfaceAndroidGo();
            Log.e("ismail","and"+Build.VERSION.SDK_INT);
            if (Build.VERSION.SDK_INT==29) {

                aidlInterfaceAndroidQ.hideSettingsLevel2(lines);
                Utils.log(MobiiotAPI.TAG, "hide Settings 2 Q" + lines);

            }else if (Build.VERSION.SDK_INT==27) {
                aidlInterfaceAndroidGO.hideSettingsLevel2(lines);
                Utils.log(MobiiotAPI.TAG, "hide Settings 2 GO" + lines);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void resetSettingsLevel2() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.resetLevel2();
                Utils.log(MobiiotAPI.TAG, "reset Settings level 2: ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.resetLevel2();
                Utils.log(MobiiotAPI.TAG, "reset Settings level 2: ");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.resetLevel2();
                Utils.log(MobiiotAPI.TAG, "reset Settings level 2: ");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public static void cleanSdCardStorage() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.cleanSdCardStorage();
                Utils.log(MobiiotAPI.TAG, "cleanSdCardStorage");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.cleanSdCardStorage();
                Utils.log(MobiiotAPI.TAG, "cleanSdCardStorage");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.cleanSdCardStorage();
                Utils.log(MobiiotAPI.TAG, "cleanSdCardStorage");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public static void screenShot() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.screenShot();
                Utils.log(MobiiotAPI.TAG, "screenShot: ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.screenShot();
                Utils.log(MobiiotAPI.TAG, "screenShot: ");

            }else if (iMyAidlInterfaceGoMPE != null) {

                iMyAidlInterfaceGoMPE.screenShot();
                Utils.log(MobiiotAPI.TAG, "screenShot: ");
            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
