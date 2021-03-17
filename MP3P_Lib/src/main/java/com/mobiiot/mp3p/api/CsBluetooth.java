package com.mobiiot.mp3p.api;

import android.bluetooth.BluetoothDevice;
import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.mp3p.api.Utils.ServiceUtil;
import com.mobiiot.mp3p.api.Utils.Utils;

import java.util.List;

public class CsBluetooth {

    public static void enableBluetooth() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.enableBluetooth();
                Utils.log(MobiiotAPI.TAG, "enable Bluetooth ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.enableBluetooth();
                Utils.log(MobiiotAPI.TAG, "enable Bluetooth ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void disableBluetooth() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.disableBluetooth();
                Utils.log(MobiiotAPI.TAG, "disable Bluetooth ");

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.disableBluetooth();
                Utils.log(MobiiotAPI.TAG, "disable Bluetooth ");

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void connectToBluetooth(int i) {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceGoMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (iMyAidlInterface != null) {

                iMyAidlInterface.connectToBluetooth(i);
                Utils.log(MobiiotAPI.TAG, "connect To Bluetooth "+i);

            }else if (iMyAidlInterface2 != null) {

                iMyAidlInterface2.connectToBluetooth(i);
                Utils.log(MobiiotAPI.TAG, "connect To Bluetooth "+i);

            }else {
                Utils.log(MobiiotAPI.TAG, "service is KO");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //support Android GO
    public static List<BluetoothDevice> getDevicePairListAndroidGo() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;

            } else {
                List<BluetoothDevice> listDevice=iMyAidlInterface.getDevicePairList();
                Utils.log(MobiiotAPI.TAG, "get device pair list : "+listDevice.size());
                return listDevice;


            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;

        }
    }

    //support Android GO
    public static List<BluetoothDevice> getDeviceUnPairListAndroidGo() {
        try {
            CSAndoridGo iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidGo();
            if (iMyAidlInterface == null) {
                Utils.log(MobiiotAPI.TAG, "service is KO");
                return null;

            } else {
                List<BluetoothDevice> listDevice=iMyAidlInterface.getDeviceUnPairList();
                Utils.log(MobiiotAPI.TAG, "get device Unpair list : "+listDevice.size());
                return listDevice;


            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


}
