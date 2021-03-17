package com.mobiiot.api;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.mobiiot.api.Utils.Utils;
import com.mobydata.CSscanner;
import com.mobydata.CSscannerCallback;

public class MobiIotScannerApi {

    public static String TAG="MobiIoT_API";
    public static CSscanner scannerInterfaceService;



    public MobiIotScannerApi(Context mContext) {
        mContext.bindService(getScannerIntent(), serviceConn, Service.BIND_AUTO_CREATE);
    }

    private Intent getScannerIntent() {
        Intent aidlIntent = new Intent();
        aidlIntent.setAction("com.mobydata.ScannerService.action");
        aidlIntent.setPackage("com.mobydata");
        return aidlIntent;
    }

    private ServiceConnection serviceConn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "aidl connect fail");
            scannerInterfaceService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "aidl connect success");
            scannerInterfaceService = CSscanner.Stub.asInterface(service);

        }
    };


    public static void setScannerVibrating(boolean isOpen){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.setScannerVibrating(isOpen);
                Utils.log(TAG, "setScannerVibrating : "+isOpen);

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void setScannerBeep(boolean isOpen){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.setScannerBeep(isOpen);
                Utils.log(TAG, "setScannerBeep : "+isOpen);

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static Boolean scannerHardwareExist(){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");
                return null;
            } else {
                Utils.log(TAG, "scannerHardwareExist ");
                return scannerInterfaceService.scannerHardwareExist();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void scannerEnable(){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.scannerEnable();
                Utils.log(TAG, "scannerEnable  ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void scannerDisable(){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.scannerDisable();
                Utils.log(TAG, "scannerDisable  ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void scannerDestroy(){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.scannerDestroy();
                Utils.log(TAG, "scannerDestroy  ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void startScanner(){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.scannerDestroy();
                Utils.log(TAG, "startScanner  ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void stopScanner(){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.stopScanner();
                Utils.log(TAG, "stopScanner  ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void setFlash(boolean isOpen){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.setFlash(isOpen);
                Utils.log(TAG, "setFlash  :"+ isOpen);

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void setScanMode(String mode){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.setScanMode(mode);
                Utils.log(TAG, "setScanMode  :"+ mode);

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static void getScanResult( CSscannerCallback cb){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.getScanResult(cb);
                Utils.log(TAG, "getScanResult  ");

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static Boolean isScannerOpened(){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");
                return null;
            } else {
                Utils.log(TAG, "isScannerOpened ");
                return scannerInterfaceService.isScannerOpened();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean setContinuousModeDelay(int delay){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");
                return null;
            } else {
                Utils.log(TAG, "setContinuousModeDelay "+delay);
                return scannerInterfaceService.isScannerOpened();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void SaveScannedCode(boolean isSave, String folderPath){
        try {

            if (scannerInterfaceService == null) {
                Utils.log(TAG, "service is KO");

            } else {
                scannerInterfaceService.SaveScannedCode(isSave,folderPath);
                Utils.log(TAG, "SaveScannedCode  "+isSave+" - "+folderPath);

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }


}
