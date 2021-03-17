package com.mobiiot.client.Activity;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.mobiiot.client.R;
import com.mobydata.CSscanner;
import com.mobydata.CSscannerCallback;
import com.mobydata.EVResult;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScannerHeadEngine extends AppCompatActivity {

    private static final String TAG = "Scanner Test App";
    public static CSscanner scannerInterfaceService;

    private TextView textViewResult;
    private Button stopCamera,startCamera;


    private Switch switchBeep,switchVibrating,switchFlash,switchSaveScannedCode;
    private RadioGroup radioGroupScanType;
    private RadioButton radioButtonSingle,radioButtonContinuous,radioButtonTrigger;
    private Button buttonStartStopScanner;
    private Spinner spinnerDelay;
    private String[] delayValueForSpinner = {"500", "1000", "1500", "2000"};
    private TextView textViewDelay;


    private static final String SingleMode = "SINGLE";
    private static final String ContinuousMode = "CONTINOUS";
    private static final String TriggerMode = "TRIGGER";

    int selectedTriggerButton=-1;
    boolean saveScannedCode=false;

    int delayValue=500;

    public enum ScanModeEnum {

        Single(SingleMode),
        Trigger(TriggerMode),
        Continuous(ContinuousMode);

        public final String value;
        ScanModeEnum(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "onCreate --->  "  );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_head_engine);

        bindService(getScannerIntent(), serviceConn, Service.BIND_AUTO_CREATE);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "CaviarDreams.ttf");


        textViewResult = findViewById(R.id.textView);textViewResult.setTypeface(typeface);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());
        switchFlash = (Switch) findViewById(R.id.switchFlash);switchFlash.setTypeface(typeface);
        switchVibrating = (Switch) findViewById(R.id.switchVibrating);switchVibrating.setTypeface(typeface);
        switchBeep = (Switch) findViewById(R.id.switchBeep);switchBeep.setTypeface(typeface);
        switchSaveScannedCode= (Switch) findViewById(R.id.switchSaveScanedCodeAsBitmap);switchSaveScannedCode.setTypeface(typeface);
        radioGroupScanType = (RadioGroup)findViewById(R.id.radioGroupScanType);
        buttonStartStopScanner=(Button) findViewById(R.id.buttonStartStopScan);buttonStartStopScanner.setTypeface(typeface);
        stopCamera = findViewById(R.id.buttonStopCamera);stopCamera.setTypeface(typeface);
        startCamera = findViewById(R.id.buttonStartStopScan);startCamera.setTypeface(typeface);
        spinnerDelay=(Spinner) findViewById(R.id.spinnerDelay);
        radioButtonSingle=(RadioButton) findViewById(R.id.radioButtonSingle);radioButtonSingle.setTypeface(typeface);
        radioButtonContinuous=(RadioButton) findViewById(R.id.radioButtonSingle);radioButtonContinuous.setTypeface(typeface);
        radioButtonTrigger=(RadioButton) findViewById(R.id.radioButtonSingle);radioButtonTrigger.setTypeface(typeface);
        textViewDelay=(TextView)findViewById(R.id.textViewDelay); textViewDelay.setTypeface(typeface);

        ArrayAdapter adapterSpinner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,delayValueForSpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDelay.setAdapter(adapterSpinner);
        spinnerDelay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                delayValue=Integer.parseInt(delayValueForSpinner[i]);
                Log.e(TAG, "DELAY : "+delayValue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        switchFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if(null != scannerInterfaceService){
                        Log.e(TAG, "FLASH : "+isChecked);
                        scannerInterfaceService.setFlash(isChecked);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        switchBeep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if(null != scannerInterfaceService){
                        Log.e(TAG, "BEEP : "+isChecked);
                        scannerInterfaceService.setScannerBeep(isChecked);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        switchVibrating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if(null != scannerInterfaceService){
                        Log.e(TAG, "VIBRATING : "+isChecked);
                        scannerInterfaceService.setScannerVibrating(isChecked);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        switchSaveScannedCode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if(null != scannerInterfaceService){
                        Log.e(TAG, "Save Scanned Code : "+isChecked);

                        saveScannedCode=isChecked;
                        scannerInterfaceService.SaveScannedCode(isChecked,"/sdcard/Pictures/");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        radioGroupScanType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked && (checkedRadioButton.getId()==R.id.radioClickToScanBottomButton)) {
                    Log.e(TAG,"bottom");
                    startCamera.setVisibility(View.GONE);
                    stopCamera.setVisibility(View.GONE);
                    selectedTriggerButton=27;
                }else{
                    Log.e(TAG,"Other");
                    startCamera.setVisibility(View.VISIBLE);
                    stopCamera.setVisibility(View.VISIBLE);
                    selectedTriggerButton=-1;
                }
            }
        });



    }

    public void clickButtonStopScanning(View view) {
        try {
            if(null != scannerInterfaceService){
                Log.d(TAG, "stop_camera");
                scannerInterfaceService.stopScanner();
                switchBeep.setEnabled(true);
                switchFlash.setEnabled(true);
                switchSaveScannedCode.setEnabled(true);
                switchVibrating.setEnabled(true);

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void clickButtonStartStopScanning(View view) {
        int checkedRadioButtonId = radioGroupScanType.getCheckedRadioButtonId();

        switchBeep.setEnabled(false);
        switchFlash.setEnabled(false);
        switchVibrating.setEnabled(false);
        switchSaveScannedCode.setEnabled(false);


        try {
            if((null != scannerInterfaceService) && scannerInterfaceService.scannerHardwareExist()){
                textViewResult.setText("");
                if (checkedRadioButtonId == R.id.radioButtonSingle) {
                    Log.e(TAG, "single scan");

                    scannerInterfaceService.SaveScannedCode(saveScannedCode,"/sdcard/Pictures/");
                    scannerInterfaceService.setScanMode(ScanModeEnum.Single.value());
                }else{
                    Log.e(TAG, "continuous scan : "+delayValue);
                    scannerInterfaceService.stopScanner();
                    scannerInterfaceService.setContinuousModeDelay(delayValue);
                    scannerInterfaceService.setScanMode(ScanModeEnum.Continuous.value());

                }
                scannerInterfaceService.startScanner();
                scannerInterfaceService.getScanResult(mCSscannerCallback);

            }else {
                Log.e(TAG, "Scanner Hardware Don't Exist");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        boolean isConnect= bindService(getScannerIntent(), serviceConn, Service.BIND_AUTO_CREATE);
        Log.d(TAG, "onPause ---> isConnect ----> " + isConnect );
        try {
            if(isConnect && (null != scannerInterfaceService) ){
                Log.d(TAG, "onPause  ---->  scannerEnable "  );
                scannerInterfaceService.scannerEnable();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onResume();
    }


    @Override
    protected void onPause() {
        //
        Log.d(TAG, "onPause");
        try {
            if(null != scannerInterfaceService){
                Log.d(TAG, " onPause ---> scannerDisable");
                scannerInterfaceService.scannerDisable();
                unbindService(serviceConn);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        try {
            scannerInterfaceService.scannerDestroy();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }





    private Intent getScannerIntent() {
        Intent aidlIntent = new Intent();
        aidlIntent.setAction("com.mobydata.ScannerService.action");
        aidlIntent.setPackage("com.mobydata");
        return aidlIntent;
    }




    EVResult mResult = null  ;

    private final CSscannerCallback.Stub mCSscannerCallback = new CSscannerCallback.Stub(){
        @Override
        public void getResults(EVResult results) {
            Log.d(TAG,"CSscannerCallback getResults : " + results );

            mResult = results ;
            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    textViewResult.setText("Code  : "+mResult.getStringValue()+"\n"+
                            "Len  : "+mResult.getStringLength()+"\n"+
                            "Symbology  : "+mResult.getSymbology()+"\n"+
                            "SubType  : "+mResult.getSubtype());
                    int checkedRadioButtonId = radioGroupScanType.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == R.id.radioButtonSingle) {
                        switchBeep.setEnabled(true);
                        switchFlash.setEnabled(true);
                        switchVibrating.setEnabled(true);
                        switchSaveScannedCode.setEnabled(true);
                    }
                }

            });

        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        try {
            Log.e(TAG,"DOWN==="+keyCode+" - "+scannerInterfaceService.isScannerOpened());
            if(keyCode==selectedTriggerButton ){
                switchBeep.setEnabled(false);
                switchFlash.setEnabled(false);
                switchVibrating.setEnabled(false);
                switchSaveScannedCode.setEnabled(false);
                scannerInterfaceService.SaveScannedCode(saveScannedCode,"/sdcard/Pictures/");
                scannerInterfaceService.setScanMode(ScanModeEnum.Single.value());
                scannerInterfaceService.startScanner();
                scannerInterfaceService.getScanResult(mCSscannerCallback);


            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return super.onKeyDown(keyCode, event);
    }

    // catches the onKeyUp button event
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e(TAG,"UP==="+keyCode);
        if(keyCode==selectedTriggerButton){
            try {
                scannerInterfaceService.stopScanner();
                switchBeep.setEnabled(true);
                switchFlash.setEnabled(true);
                switchVibrating.setEnabled(true);
                switchSaveScannedCode.setEnabled(true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        return super.onKeyUp(keyCode, event);
    }


}


