package com.mobiiot.client.Activity;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mediatek.settings.service.DeviceInfo;
import com.mobiiot.client.R;
import com.mediatek.settings.service.CSAndoridGo;
import com.mobiiot.api.CsDevice;

public class DeviceDetailsActivity extends AppCompatActivity  {



    TextView deviceImei,deviceIp;
    private CSAndoridGo iMyAidlInterface;

    TextView textViewBuildVersionValue,textViewBuildVersionName,
            textViewUpTimeValue,textViewUpTimeName
            ,textViewBuildNumberValue,textViewBuildNumberName
            , textViewKernelVersionValue, textViewKernelVersionName
            , textViewBaseBandVersionValue, textViewBaseBandVersionValueName
            , textViewAndroidVersionValue, textViewAndroidVersionName
            , textViewModelValue, textViewModelName
            , textViewBatteryLevelValue, textViewBatteryLevelName
            , textViewWiFiAddressValue, textViewWiFiAddressName,
            textViewBluetoothAddressValue,textViewBluetoothAddressName
            , textViewSerialNumberValue, textViewSerialNumberNAme
            , textViewApiVersionValue, textViewApiVersionName,
            textViewIMEI1Value,textViewIMEI1Name
            , textViewIMEI2Value, textViewIMEI2NAme,
            textViewIMSI1Value,textViewIMSI1Name
            , textViewIMSI2Value, textViewIMSI2NAme;
    ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_device_details);


        device= CsDevice.getDeviceInformation();
        Log.e("device",device.toString());

        setupView();
        updateInformation(device);



    }

    public void setupView(){
        Typeface type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");





        deviceIp = (TextView)findViewById(R.id.textIp);deviceIp.setTypeface(type);




        textViewBuildVersionValue= (TextView)findViewById(R.id.textViewBuildVersionValue);textViewBuildVersionValue.setTypeface(type);
        textViewBuildVersionName= (TextView)findViewById(R.id.textViewBuildVersionName);textViewBuildVersionName.setTypeface(type);

        textViewUpTimeValue= (TextView)findViewById(R.id.textViewUpTimeValue);textViewUpTimeValue.setTypeface(type);
        textViewUpTimeName= (TextView)findViewById(R.id.textViewUpTimeName);textViewUpTimeName.setTypeface(type);

        textViewBuildNumberValue= (TextView)findViewById(R.id.textViewBuildNumberValue);textViewBuildNumberValue.setTypeface(type);
        textViewBuildNumberName= (TextView)findViewById(R.id.textViewBuildNumberName);textViewBuildNumberName.setTypeface(type);

        textViewKernelVersionValue= (TextView)findViewById(R.id.textViewKernelVersionValue);textViewKernelVersionValue.setTypeface(type);
        textViewKernelVersionName= (TextView)findViewById(R.id.textViewKernelVersionName);textViewKernelVersionName.setTypeface(type);

        textViewBaseBandVersionValue= (TextView)findViewById(R.id.textViewBaseBandVersionValue);textViewBaseBandVersionValue.setTypeface(type);
        textViewBaseBandVersionValueName= (TextView)findViewById(R.id.textViewBaseBandVersionName);textViewBaseBandVersionValueName.setTypeface(type);

        textViewAndroidVersionValue= (TextView)findViewById(R.id.textViewAndroidVersionValue);textViewAndroidVersionValue.setTypeface(type);
        textViewAndroidVersionName= (TextView)findViewById(R.id.textViewAndroidVersionName);textViewAndroidVersionName.setTypeface(type);

        textViewModelValue= (TextView)findViewById(R.id.textViewModelValue);textViewModelValue.setTypeface(type);
        textViewModelName= (TextView)findViewById(R.id.textViewModelName);textViewModelName.setTypeface(type);

        textViewBatteryLevelValue= (TextView)findViewById(R.id.textViewBatteryValue);textViewBatteryLevelValue.setTypeface(type);
        textViewBatteryLevelName= (TextView)findViewById(R.id.textViewBatteryName);textViewBatteryLevelName.setTypeface(type);

        textViewWiFiAddressValue= (TextView)findViewById(R.id.textViewWifiAddressValue);textViewWiFiAddressValue.setTypeface(type);
        textViewWiFiAddressName= (TextView)findViewById(R.id.textViewWifiAddressName);textViewWiFiAddressName.setTypeface(type);

        textViewBluetoothAddressValue= (TextView)findViewById(R.id.textViewBluetoothAddressValue);textViewBluetoothAddressValue.setTypeface(type);
        textViewBluetoothAddressName= (TextView)findViewById(R.id.textViewBluetoothAddressName);textViewBluetoothAddressName.setTypeface(type);

        textViewSerialNumberValue= (TextView)findViewById(R.id.textViewSerialNumberValue);textViewSerialNumberValue.setTypeface(type);
        textViewSerialNumberNAme= (TextView)findViewById(R.id.textViewSerialNumberName);textViewSerialNumberNAme.setTypeface(type);


        textViewApiVersionValue= (TextView)findViewById(R.id.textViewApiVersionValue);textViewApiVersionValue.setTypeface(type);
        textViewApiVersionName= (TextView)findViewById(R.id.textViewApiVersion);textViewApiVersionName.setTypeface(type);

        textViewIMEI1Value= (TextView)findViewById(R.id.textViewIMEI1Value);textViewIMEI1Value.setTypeface(type);
        textViewIMEI1Name= (TextView)findViewById(R.id.textViewIMEI1);textViewIMEI1Name.setTypeface(type);

        textViewIMEI2Value= (TextView)findViewById(R.id.textViewIMEI2Value);textViewIMEI2Value.setTypeface(type);
        textViewIMEI2NAme= (TextView)findViewById(R.id.textViewIMEI2);textViewIMEI2NAme.setTypeface(type);



        textViewIMSI1Value= (TextView)findViewById(R.id.textViewIMSI1Value);textViewIMSI1Value.setTypeface(type);
        textViewIMSI1Name= (TextView)findViewById(R.id.textViewIMSI1);textViewIMSI1Name.setTypeface(type);

        textViewIMSI2Value= (TextView)findViewById(R.id.textViewIMSI2Value);textViewIMSI2Value.setTypeface(type);
        textViewIMSI2NAme= (TextView)findViewById(R.id.textViewIMSI2);textViewIMSI2NAme.setTypeface(type);


    }

    public static DeviceInfo device;

    public void updateInformation(DeviceInfo device){
        deviceIp.setText(device.getIp_address());
        textViewBuildVersionValue.setText(device.getCustom_build_version());
        textViewUpTimeValue.setText(device.getUp_time());
        textViewBuildNumberValue.setText(device.getBuild_number());
        textViewKernelVersionValue.setText(device.getKernel_version());
        textViewBaseBandVersionValue.setText(device.getBaseBand_version());
        textViewAndroidVersionValue.setText(device.getAndroid_version());
        textViewModelValue.setText(device.getModel());
        textViewBatteryLevelValue.setText(device.getBattery_level()+"%");
        textViewWiFiAddressValue.setText(device.getWiFi_MAC_address());
        textViewBluetoothAddressValue.setText(device.getBluetooth_address());
        textViewSerialNumberValue.setText(device.getSerial_number());
        textViewApiVersionValue.setText(device.getAPI_version());
        textViewIMEI1Value.setText(device.getSim1_imei());
        textViewIMEI2Value.setText(device.getSim2_imei());
        if (Build.MODEL.contains("MobiGo2") || Build.MODEL.contains("MobiPrint 4+") ){
            textViewIMSI1Value.setText(device.getSim1_imsi());
            textViewIMSI2Value.setText(device.getSim2_imsi());
        }else{
            textViewIMSI1Value.setVisibility(View.GONE);
            textViewIMSI1Name.setVisibility(View.GONE);
            textViewIMSI2Value.setVisibility(View.GONE);
            textViewIMSI2NAme.setVisibility(View.GONE);
        }


    }
}
