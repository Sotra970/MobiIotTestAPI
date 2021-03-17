package com.mobiiot.client.TabView;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;


public class SecurityFragment extends Fragment {

    View v;


    Switch swicthUnknownSource,switchAdb,swicthMTP,switchPTP,switchStorage, switchPTP2,switchMIDI,switchUsb,switchFileTransfer;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_security, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        swicthUnknownSource=(Switch)v.findViewById(R.id.SwitchUnknownSource) ;swicthUnknownSource.setTypeface(type);
        switchAdb=(Switch)v.findViewById(R.id.switchAdb) ;switchAdb.setTypeface(type);
        swicthMTP=(Switch)v.findViewById(R.id.switchMTP) ;swicthMTP.setTypeface(type);
        switchPTP=(Switch)v.findViewById(R.id.switchPTP) ;switchPTP.setTypeface(type);
        switchStorage=(Switch)v.findViewById(R.id.switchStorage) ;switchStorage.setTypeface(type);

        switchPTP2=(Switch)v.findViewById(R.id.switchPtp2) ;switchPTP2.setTypeface(type);
        switchMIDI=(Switch)v.findViewById(R.id.switchMidi) ;switchMIDI.setTypeface(type);
        switchFileTransfer=(Switch)v.findViewById(R.id.switchFileTransfer) ;switchFileTransfer.setTypeface(type);
        switchUsb=(Switch)v.findViewById(R.id.switchUsb) ;switchUsb.setTypeface(type);

        if (Build.VERSION.SDK_INT==29) {
            switchPTP2.setVisibility(View.VISIBLE);
            switchMIDI.setVisibility(View.VISIBLE);
            switchFileTransfer.setVisibility(View.VISIBLE);
            switchUsb.setVisibility(View.VISIBLE);

            switchPTP.setVisibility(View.GONE);
            switchStorage.setVisibility(View.GONE);
            swicthMTP.setVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT==27) {
            switchPTP2.setVisibility(View.GONE);
            switchMIDI.setVisibility(View.GONE);
            switchFileTransfer.setVisibility(View.GONE);
            switchUsb.setVisibility(View.GONE);

            switchPTP.setVisibility(View.VISIBLE);
            switchStorage.setVisibility(View.VISIBLE);
            swicthMTP.setVisibility(View.VISIBLE);
        }




        swicthUnknownSource.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("swicthUnknownSource",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_UNKNOWNSOURCE_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_UNKNOWNSOURCE_DISABLE.getAction(),null);

            }
        });


        switchAdb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchAdb",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_ADB_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_ADB_DISABLE.getAction(),null);

            }
        });

        swicthMTP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("swicthMTP",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_MTP_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_MTP_DISABLE.getAction(),null);

            }
        });

        switchPTP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchPTP",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_PTP_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_PTP_DISABLE.getAction(),null);

            }
        });

        switchStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchStorage",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_STORAGE_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_STORAGE_DISABLE.getAction(),null);


            }
        });

        switchPTP2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchPTP2",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_PTP_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SECURITY_PTP_DISABLE.getAction(),null);


            }
        });

        switchMIDI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchMIDI",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.USB_ENABLE_MIDI.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.USB_DISABLE_MIDI.getAction(),null);
            }
        });

        switchFileTransfer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchFileTransfer",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.USB_ENABLE_FILE_TRANSFER.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.USB_DISABLE_FILE_TRANSFER.getAction(),null);
            }
        });

        switchUsb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchUsb",""+b);
                if(b==true)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.USB_ENABLE_USB.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.USB_DISABLE_USB.getAction(),null);
            }
        });


        return v;
    }
}
