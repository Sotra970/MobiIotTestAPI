package com.mobiiot.client.TabView;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.idemia.peripherals.PeripheralsPowerInterface;
import com.mobiiot.client.R;
import com.mobiiot.api.CsWifi;
import com.mobiiot.client.Activity.ConnectActivity;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.model.Param;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CbmSwitchFragment extends Fragment {
    private static final String TAG = "CBMFragment";


    Switch switchCBM;
    private TextView tvStatus;
    private PeripheralsPowerInterface asInterface;
    private Intent aidlIntent;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cbm,container,false);


        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        switchCBM=(Switch)rootView.findViewById(R.id.switchCBM) ;switchCBM.setTypeface(type);

        tvStatus = rootView.findViewById(R.id.textViewCBMInfo);tvStatus.setTypeface(type);

        switchCBM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("SWITCH CBM ",""+b);
                try {
                    asInterface.setFingerPrintSwitch(b);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
        initAidlService();



        return rootView;
    }

    public void initAidlService(){
        aidlIntent = new Intent();
        aidlIntent.setAction("idemia.intent.action.CONN_PERIPHERALS_SERVICE_AIDL");
        aidlIntent.setPackage("com.android.settings");

        getContext().bindService(aidlIntent, serviceConnSam, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnSam = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            asInterface = PeripheralsPowerInterface.Stub.asInterface(service);
            Log.d(TAG,"connected service"+asInterface);
            try {
                switchCBM.setChecked(asInterface.getFingerPrintSwitch());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            asInterface = null;
            Log.d(TAG,"disconnected"+asInterface);
        }
    };


}
