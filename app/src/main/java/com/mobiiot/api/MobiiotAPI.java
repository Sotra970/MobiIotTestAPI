package com.mobiiot.api;

import android.content.Context;
import android.os.Build;
import android.util.Log;


import com.mobiiot.api.Utils.PrinterServiceUtil;
import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.api.Utils.ServiceUtilIOPrint;
import com.mobiiot.api.Utils.Utils;

import org.slf4j.helpers.Util;

public class MobiiotAPI {

    public static CsSimSlot simSlot;
    public static String TAG="AndroidGoCSApi";

    public MobiiotAPI(Context mContext) {
        Log.e("ismail",""+Build.VERSION.SDK_INT);
        if(!Utils.isGMS(mContext))
            ServiceUtil.bindRemoteService(mContext);


        new MobiIotScannerApi(mContext);


        if (Build.MODEL.contains("MP3")  || Build.MODEL.contains("MobiPrint 4+") || Build.MODEL.contains("MPE") ){
            PrinterServiceUtil.bindService(mContext);

        }

        if (Build.MODEL.contains("MP3")   || Build.MODEL.contains("MobiPrint 4+") ){
            ServiceUtilIOPrint.bindRemoteService(mContext);

        }

        /*if (Build.MODEL.contains("MP3")){
            ServiceUtilIOPrint.bindRemoteService(mContext);
        }*/

    }
}
