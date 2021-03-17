package com.mobiiot.client.TabView;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
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

import com.mobiiot.api.CsWifi;
import com.mobiiot.client.Activity.ConnectActivity;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WifiFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "WifiFragment";

    private Button btEditHotspot,getStatus,btConnect;
    Switch swicthWifi,switchHotspot;
    private TextView tvStatus;
    private EditText etName,etPassword;
    private WifiManager wifiManager;
    public WifiFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wifi,container,false);
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        swicthWifi=(Switch)rootView.findViewById(R.id.switchWifi) ;swicthWifi.setTypeface(type);
        switchHotspot=(Switch)rootView.findViewById(R.id.switchHotspotWifi) ;switchHotspot.setTypeface(type);

        etName = rootView.findViewById(R.id.fragment_wifi_etName);etName.setTypeface(type);
        etPassword = rootView.findViewById(R.id.fragment_wifi_etPassword);etPassword.setTypeface(type);

        tvStatus = rootView.findViewById(R.id.fragment_wifi_tvStatus);tvStatus.setTypeface(type);
        btEditHotspot = rootView.findViewById(R.id.fragment_wifi_btEditHotspot);btEditHotspot.setTypeface(type);
        btConnect = rootView.findViewById(R.id.fragment_wifi_btConnect);btConnect.setTypeface(type);
        getStatus = rootView.findViewById(R.id.fragment_wifi_getStatus);getStatus.setTypeface(type);

        btEditHotspot.setOnClickListener(this);
        getStatus.setOnClickListener(this);
        btConnect.setOnClickListener(this);

        swicthWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("SWITCH Wifi ",""+b);
                if (b)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.WIFI_ENABLE.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.WIFI_DISABLE.getAction(),null);
            }
        });

        switchHotspot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("SWITCH Hotspot",""+b);
                if (b)
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.WIFI_ENABLE_HOTSPOT.getAction(),null);
                else
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.WIFI_DISABLE_HOTSPOT.getAction(),null);
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view == btEditHotspot){
            String name = etName.getText().toString();
            String password = etPassword.getText().toString();
            Log.e(TAG,name + "  " + password);

                if((name != null && password != null) && (!name.equals("") && !password.equals(""))){
                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("name",name));
                    listParam.add(new Param("password",password));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.WIFI_UPDATE_HOTSPOT.getAction(),listParam);
                    android.widget.Toast.makeText(getActivity(), "modify successfully", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etPassword.setText("");
                }else {

                    android.widget.Toast.makeText(getActivity(), "name and password can not be empty!", Toast.LENGTH_SHORT).show();
                }

        } else if (view == getStatus){
            Log.e(TAG, "will get wifi's status");
            String wifiStatus = CsWifi.getWifiStatus();
            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.WIFI_GET_STATUS.getAction(),null);

            if (!TextUtils.isEmpty(wifiStatus)){
                tvStatus.setText(wifiStatus);
            } else {
                tvStatus.setText("can not get wifi's status");
            }
        } else if (view == btConnect){
            Intent intent = new Intent(getContext(), ConnectActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
