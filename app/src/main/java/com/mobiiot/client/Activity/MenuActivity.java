package com.mobiiot.client.Activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiiot.api.CsDevice;
import com.mobiiot.api.MobiIotScannerApi;
import com.mobiiot.api.Utils.PrinterServiceUtil;
import com.mobiiot.api.Utils.ServiceUtilIOPrint;
import com.mobiiot.api.Utils.Utils;
import com.mobiiot.client.Adapter.SectionAdapter;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.GPSTracker;
import com.mobiiot.client.R;
import com.mobiiot.client.RegisterForPushNotificationsAsync;
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
import com.mobiiot.client.model.Section;
import com.mobydata.CSscanner;

import java.io.Serializable;
import java.util.ArrayList;

import me.pushy.sdk.Pushy;

public class MenuActivity extends AppCompatActivity {
    private GridView gridViewSystemApp;
    private LinearLayout listMenu;
    ArrayList<Section> listSystemApp;
    public static CSscanner scannerInterfaceService;

    public static FrameLayout layoutAction;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        startService(new Intent(this, ExecuteFunctionsService.class));
        startService(new Intent(this, GPSTracker.class));

        Pushy.listen(this);
        new RegisterForPushNotificationsAsync().execute(this,"");


        gridViewSystemApp = (GridView)findViewById(R.id.gridviewMenu);
        listMenu=(LinearLayout) findViewById(R.id.listViewMenu);

        listSystemApp=new ArrayList<>();

        if(!Utils.isGMS(this)){
            listSystemApp.add(new Section("SIM",getDrawable(R.drawable.ic_sim),getColor(R.color.SIM)));
            listSystemApp.add(new Section("APN",this.getDrawable(R.drawable.ic_apn),getColor(R.color.APN)));
            listSystemApp.add(new Section("DATA",this.getDrawable(R.drawable.ic_sim),getColor(R.color.DATA)));

            listSystemApp.add(new Section("WIFI",this.getDrawable(R.drawable.ic_wifi),getColor(R.color.WIFI)));
            listSystemApp.add(new Section("Reboot",this.getDrawable(R.drawable.ic_reboot),getColor(R.color.Reboot)));
            listSystemApp.add(new Section("Operation",this.getDrawable(R.drawable.ic_animation),getColor(R.color.Operation)));
            listSystemApp.add(new Section("Location",this.getDrawable(R.drawable.ic_location),getColor(R.color.Location)));
            listSystemApp.add(new Section("Security",this.getDrawable(R.drawable.ic_security),getColor(R.color.Security)));

            listSystemApp.add(new Section("Package",this.getDrawable(R.drawable.ic_app),getColor(R.color.Package)));
            listSystemApp.add(new Section("Keystore",this.getDrawable(R.drawable.ic_key),getColor(R.color.Keystore)));
            listSystemApp.add(new Section("Kiosk",this.getDrawable(R.drawable.ic_kiosk),getColor(R.color.Kiosk)));
            listSystemApp.add(new Section("Touch Simulation",this.getDrawable(R.drawable.ic_touch),getColor(R.color.Touch)));

            listSystemApp.add(new Section("Bluetooth",this.getDrawable(R.drawable.ic_bluetooth),getColor(R.color.Bluetooth)));
            listSystemApp.add(new Section("Device Information",this.getDrawable(R.drawable.ic_information),getColor(R.color.Device)));
            listSystemApp.add(new Section("Scanner",this.getDrawable(R.drawable.ic_scanner),getColor(R.color.Scanner)));
            //listSystemApp.add(new Section("SAM",this.getDrawable(R.drawable.ic_sam),getColor(R.color.SAM)));
            if (Build.MODEL.contains("MP3")  || Build.MODEL.contains("MP4") || Build.MODEL.contains("MobiPrint") || Build.MODEL.contains("MPE")){
                listSystemApp.add(new Section("Printer",this.getDrawable(R.drawable.ic_printer),getColor(R.color.colorPackage)));
            }
            listSystemApp.add(new Section("Update from server",this.getDrawable(R.drawable.ic_update_server),getColor(R.color.updateServer)));

            //listSystemApp.add(new Section("NFC",this.getDrawable(R.drawable.ic_nfc),getColor(R.color.NFC)));

        }else{

        }
        if (Build.MODEL.contains("MobiGo2") ){
            listSystemApp.add(new Section("FingerPrint Sensor",this.getDrawable(R.drawable.ic_cbm),getColor(R.color.CBM)));
            //listSystemApp.add(new Section("Device Information",this.getDrawable(R.drawable.ic_information),getColor(R.color.Device)));
            boolean isScannerExist =bindService(getScannerIntent(), serviceConn, Service.BIND_AUTO_CREATE);
            Log.e("ismail", isScannerExist +"");
            if (isScannerExist == true){
                listSystemApp.add(new Section("Scanner Head Engine",this.getDrawable(R.drawable.ic_scanner),getColor(R.color.Scanner2)));
            }
        }

        listSystemApp.add(new Section("SAM",this.getDrawable(R.drawable.ic_sam),getColor(R.color.SAM)));

        if(Build.MODEL.contains("MPE")){
            setList();
        }else{
            setGridView();
        }





        layoutAction=(FrameLayout)findViewById(R.id.layoutAction);
    }

    public void setGridView(){
        listMenu.setVisibility(View.GONE);
        gridViewSystemApp.setVisibility(View.VISIBLE);
        SectionAdapter appSystemAdapter = new SectionAdapter(this, listSystemApp);
        gridViewSystemApp.setAdapter(appSystemAdapter);
    }
    public void showActionFragement(Fragment mFragment){
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layoutAction, mFragment).addToBackStack(null).commit();
        MenuActivity.layoutAction.setVisibility(View.VISIBLE);
        MenuActivity.layoutAction.setFocusable(true);*/
        Intent intent = new Intent(MenuActivity.this,FragementActivity.class);
        intent.putExtra("fragement", (Parcelable) mFragment);
        startActivity(intent);
    }

    public void setList(){
        gridViewSystemApp.setVisibility(View.GONE);
        listMenu.setVisibility(View.VISIBLE);
        Typeface type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
        for(int i=0;i<listSystemApp.size();i++){

            final Button button=new Button(this);
            button.setTypeface(type);
            button.setText(listSystemApp.get(i).getSectionName());
            button.setTextColor(getResources().getColor(R.color.white));
            button.setTextSize(16);
            button.setBackgroundColor(listSystemApp.get(i).getColor());

            Drawable img = listSystemApp.get(i).getSectionDrawable();
            button.setCompoundDrawables(img, null, null, null);
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(MenuActivity.this,FragementActivity.class);
                    intent.putExtra("nameF",button.getText() );
                    startActivity(intent);



                }
            });
            listMenu.addView(button);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        layoutAction.setVisibility(View.GONE);
        gridViewSystemApp.setVisibility(View.VISIBLE);
    }

    private ServiceConnection serviceConn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("TAG", "aidl connect fail");
            scannerInterfaceService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("TAG", "aidl connect success");
            scannerInterfaceService = CSscanner.Stub.asInterface(service);

        }
    };

    private Intent getScannerIntent() {
        Intent aidlIntent = new Intent();
        aidlIntent.setAction("com.mobydata.ScannerService.action");
        aidlIntent.setPackage("com.mobydata");
        return aidlIntent;
    }
}
