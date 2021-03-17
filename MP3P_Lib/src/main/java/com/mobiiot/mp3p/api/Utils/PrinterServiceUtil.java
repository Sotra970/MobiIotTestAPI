package com.mobiiot.mp3p.api.Utils;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.sagereal.printer.PrinterInterface;

public class PrinterServiceUtil {
    private static final String TAG = "PrinterServiceUtil";
    public static PrinterInterface atService;


    public static void bindService(Context mContext){
        ServiceConnection serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG,  "aidl connect fail");
                atService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG,  "aidl connect success");
                atService = PrinterInterface.Stub.asInterface(service);
            }
        };

        mContext.bindService(getPrintIntent(), serviceConnection, Service.BIND_AUTO_CREATE);
    }



    public static PrinterInterface getPrinterService(){
        return atService;
    }

    public static Intent getPrintIntent() {
        Intent aidlIntent = new Intent();
        aidlIntent.setAction("sagereal.intent.action.START_PRINTER_SERVICE_AIDL");
        aidlIntent.setPackage("com.sagereal.printer");
        return aidlIntent;
    }
}
