package com.mobiiot.androidqapi.api;

import android.os.Build;
import android.os.RemoteException;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mediatek.settings.service.DeviceInfo;
import com.mobiiot.androidqapi.api.Utils.ServiceUtil;
import com.mobiiot.androidqapi.api.Utils.Utils;

public class CsDevice {



    public static DeviceInfo getDeviceInformation() {
        try {
            CsApiAndroidQ iMyAidlInterface = ServiceUtil.getAidlInterfaceAndroidQ();
            CSAndoridGo iMyAidlInterface2 = ServiceUtil.getAidlInterfaceAndroidGo();
            CSApiMPE iMyAidlInterfaceMPE = ServiceUtil.getAidlInterfaceAndroidGoMPE();
            if (Build.VERSION.SDK_INT==29) {

                DeviceInfo str = iMyAidlInterface.getDeviceInformation();
                Utils.log(MobiiotAPI.TAG, "MP4P device information : " + str.toString());
                return str;
            }else if (Build.VERSION.SDK_INT==27) {
                if(Build.MODEL.contains("MPE")){
                    DeviceInfo str = iMyAidlInterfaceMPE.getDeviceInformation();
                    Utils.log(MobiiotAPI.TAG, "MP4 device information : " + str.toString());
                    return str;
                }else{
                    DeviceInfo str = iMyAidlInterface2.getDeviceInformation();
                    Utils.log(MobiiotAPI.TAG, "MP4 device information : " + str.toString());
                    return str;
                }

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
