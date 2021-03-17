package com.mobiiot.client.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.TabView.ApnFragment;
import com.mobiiot.client.TabView.BluetoothFragment;
import com.mobiiot.client.TabView.CbmSwitchFragment;
import com.mobiiot.client.TabView.DataFragment;
import com.mobiiot.client.TabView.KeystoreFragement;
import com.mobiiot.client.TabView.OperationFragment;
import com.mobiiot.client.TabView.PackageFragment;
import com.mobiiot.client.TabView.RebootFragment;
import com.mobiiot.client.TabView.ScanFragment;
import com.mobiiot.client.TabView.SecurityFragment;
import com.mobiiot.client.TabView.SecuritySettingsFragment;
import com.mobiiot.client.TabView.SecuritySettingsFragmentAndroidQ;
import com.mobiiot.client.TabView.SimFragment;
import com.mobiiot.client.TabView.TouchSimulationFragment;
import com.mobiiot.client.TabView.UpdateServerFragment;
import com.mobiiot.client.TabView.WifiFragment;

public class FragementActivity extends AppCompatActivity {

    private static final int CONTENT_VIEW_ID = 10101010;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragement);


        String nameF=getIntent().getStringExtra("nameF");

        switch (nameF){
            case "SIM":
                showActionFragement(new SimFragment());
                break;
            case "APN":
                showActionFragement(new ApnFragment());
                break;
            case "Reboot":
                showActionFragement(new RebootFragment());
                break;
            case "DATA":
                showActionFragement(new DataFragment());
                break;
            case "WIFI":
                showActionFragement(new WifiFragment());
                break;

            case "Operation":
                showActionFragement(new OperationFragment());
                break;
            case "Location":
                startActivity(new Intent(FragementActivity.this, MapViewActivity.class));
                break;
            case "Security":
                showActionFragement(new SecurityFragment());
                break;
            case "Package":
                showActionFragement(new PackageFragment());
                break;

            case "Keystore":
                showActionFragement(new KeystoreFragement());
                break;

            case "Scanner":
                showActionFragement(new ScanFragment());
                break;

            case "Scanner Head Engine":
                startActivity(new Intent(FragementActivity.this, ScannerHeadEngine.class));
                break;

            case "Touch Simulation":
                showActionFragement(new TouchSimulationFragment());
                break;

            case "Device Information":
                com.mobiiot.client.Activity.Utils.sendActionToServiceWithParams(FragementActivity.this, EnumAction.DEVICE_INFORMATION_GET.getAction(),null);
                startActivity(new Intent(FragementActivity.this, DeviceDetailsActivity.class));
                break;

            case "Printer":
                startActivity(new Intent(FragementActivity.this, PrinterActivity.class));
                break;

            case "Bluetooth":
                showActionFragement(new BluetoothFragment());
                break;
            case "FingerPrint Sensor":
                showActionFragement(new CbmSwitchFragment());
                break;

            case "Update from server":
                showActionFragement(new UpdateServerFragment());
                break;

            case "Kiosk":
                Log.e("ismail", Build.MODEL);
                if (Build.MODEL.equals("MP3_Plus")){
                    Log.e("ismail",Build.MODEL+" - ");
                    showActionFragement(new SecuritySettingsFragment());
                }else if (Build.MODEL.equals("MobiPrint 4+")|| Build.MODEL.equals( "MobiGo2")){
                    Log.e("ismail",Build.MODEL+" - ");
                    showActionFragement(new SecuritySettingsFragmentAndroidQ());

                }

                break;


        }

    }

    public void showActionFragement(Fragment mFragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, mFragment);
        ft.commit();
    }
}