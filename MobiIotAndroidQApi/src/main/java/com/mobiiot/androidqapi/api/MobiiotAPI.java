package com.mobiiot.androidqapi.api;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.mobiiot.androidqapi.api.Utils.PrinterServiceUtil;
import com.mobiiot.androidqapi.api.Utils.ServiceUtil;
import com.mobiiot.androidqapi.api.Utils.ServiceUtilIOPrint;
import com.mobiiot.androidqapi.api.Utils.Utils;

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
