package com.mobiiot.client.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobiiot.api.CsOperation;
import com.mobiiot.client.Activity.DeviceDetailsActivity;
import com.mobiiot.client.Activity.MapViewActivity;
import com.mobiiot.client.Activity.MenuActivity;
import com.mobiiot.client.Activity.PrinterActivity;
import com.mobiiot.client.Activity.SamActivity;
import com.mobiiot.client.Activity.ScannerHeadEngine;
import com.mobiiot.client.Activity.Utils;
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
import com.mobiiot.client.model.Section;

import java.util.ArrayList;

public class SectionAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Section> apps;
    public static Fragment mFragment = null;

    // 1
    public SectionAdapter(Context context, ArrayList<Section> apps) {
        this.mContext = context;
        this.apps = apps;
    }

    // 2
    @Override
    public int getCount() {
        return apps.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Section app= apps.get(position);
        Typeface typeCaviarDream= Typeface.createFromAsset(mContext.getAssets(),"CaviarDreams.ttf");

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.layout_app, null);
        }
        LinearLayout layoutItem = (LinearLayout) convertView.findViewById(R.id.layoutMenuItem);

        TextView textAppLabel = (TextView)convertView.findViewById(R.id.textViewLabelApp);textAppLabel.setTypeface(typeCaviarDream);
        ImageView iconeApp = (ImageView)convertView.findViewById(R.id.imageViewAppIcon);
        textAppLabel.setText(app.getSectionName());
        iconeApp.setImageDrawable(app.getSectionDrawable());
        layoutItem.setBackgroundColor(app.getColor());
        iconeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                switch (app.getSectionName()){
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
                        mContext.startActivity(new Intent(mContext, MapViewActivity.class));
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
                        mContext.startActivity(new Intent(mContext, ScannerHeadEngine.class));
                        break;

                    case "Touch Simulation":
                        showActionFragement(new TouchSimulationFragment());
                        break;

                    case "Device Information":
                        Utils.sendActionToServiceWithParams(mContext, EnumAction.DEVICE_INFORMATION_GET.getAction(),null);
                        mContext.startActivity(new Intent(mContext, DeviceDetailsActivity.class));
                        break;
                    case "SAM":
                        mContext.startActivity(new Intent(mContext, SamActivity.class));
                        break;
                    case "Printer":
                        mContext.startActivity(new Intent(mContext, PrinterActivity.class));
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
                        Log.e("ismail",Build.MODEL);
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
        });
        return convertView;
    }

    public void showActionFragement(Fragment mFragment){
        FragmentManager fragmentManager = ((MenuActivity) mContext).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layoutAction, mFragment).addToBackStack(null).commit();
        MenuActivity.layoutAction.setVisibility(View.VISIBLE);
    }

}