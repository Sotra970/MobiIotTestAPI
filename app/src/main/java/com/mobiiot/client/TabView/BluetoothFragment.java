package com.mobiiot.client.TabView;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mobiiot.client.Activity.GetUnPairActivity;
import com.mobiiot.client.Activity.PairDeviceActivity;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mediatek.settings.service.CSAndoridGo;


public class BluetoothFragment extends Fragment {

    private Button enableBluetooth,disableBluetooth,getPairDevice,getUnPairDevice;
    private CSAndoridGo iMyAidlInterface;
    Switch swicthBluetooth;
    public BluetoothFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bluetooth,container,false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        swicthBluetooth=(Switch)rootView.findViewById(R.id.switchWifi) ;swicthBluetooth.setTypeface(type);
        swicthBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("SWITCH bluetooth ",""+b);
                if(b==true){
                    Utils.sendActionToServiceWithParams(getActivity(), EnumAction.BLUETOOTH_ENABLE.getAction(),null );
                }else{
                    Utils.sendActionToServiceWithParams(getActivity(), EnumAction.BLUETOOTH_DISABLE.getAction(),null);
                }

            }
        });

        getPairDevice = rootView.findViewById(R.id.fragment_bluetooth_get_pair_device);getPairDevice.setTypeface(type);
        getPairDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.sendActionToServiceWithParams(getActivity(), EnumAction.BLUETOOTH_GET_LIST_PAIR.getAction(),null);
                Intent intent = new Intent(getContext(), PairDeviceActivity.class);
                getActivity().startActivity(intent);
            }
        });
        getUnPairDevice = rootView.findViewById(R.id.fragment_bluetooth_get_unPair_device);getUnPairDevice.setTypeface(type);
        getUnPairDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.sendActionToServiceWithParams(getActivity(), EnumAction.BLUETOOTH_GET_LIST_UNPAIR.getAction(),null);
                Intent intent = new Intent(getContext(), GetUnPairActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return rootView;
    }

}
