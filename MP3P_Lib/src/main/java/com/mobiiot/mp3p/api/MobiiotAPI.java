package com.mobiiot.mp3p.api;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.mobiiot.mp3p.api.Utils.PrinterServiceUtil;
import com.mobiiot.mp3p.api.Utils.ServiceUtil;
import com.mobiiot.mp3p.api.Utils.ServiceUtilIOPrint;
import com.mobiiot.mp3p.api.Utils.Utils;

public class MobiiotAPI {

    public static CsSimSlot simSlot;
    public static String TAG="AndroidGoCSApi";

    public MobiiotAPI(Context mContext) {
        Log.e("ismail",""+Build.VERSION.SDK_INT);
        if(!Utils.isGMS(mContext))
            ServiceUtil.bindRemoteService(mContext);

        PrinterServiceUtil.bindService(mContext);
        ServiceUtilIOPrint.bindRemoteService(mContext);


        /*if (Build.MODEL.contains("MP3")){
            ServiceUtilIOPrint.bindRemoteService(mContext);
        }*/

    }
}
