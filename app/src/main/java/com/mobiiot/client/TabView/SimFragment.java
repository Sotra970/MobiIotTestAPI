package com.mobiiot.client.TabView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.mobiiot.api.CsSimSlot;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;

import java.util.List;


public class SimFragment extends Fragment {

    View v;


    Switch swicthSim1,switchSim2;
    TextView textViewInfoSim1,textViewInfoSim2,textViewDefaultSim;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                if (getView()!=null){
                    getView().setFocusableInTouchMode(true);
                    getView().requestFocus();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_sim, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        swicthSim1=(Switch)v.findViewById(R.id.switchSim1) ;swicthSim1.setTypeface(type);
        switchSim2=(Switch)v.findViewById(R.id.switchSim2) ;switchSim2.setTypeface(type);

        textViewInfoSim1=(TextView) v.findViewById(R.id.sim1Info) ;textViewInfoSim1.setTypeface(type);
        textViewInfoSim2=(TextView) v.findViewById(R.id.sim2Info) ;textViewInfoSim2.setTypeface(type);
        textViewDefaultSim=(TextView) v.findViewById(R.id.textViewDefaultSim) ;textViewDefaultSim.setTypeface(type);


        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_GET_CURRENT.getAction(),null);
        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_GET_SIGNAL_STRENGTH_SIM1.getAction(),null);
        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_GET_SIGNAL_STRENGTH_SIM2.getAction(),null);
        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_GET_LIST_NAME.getAction(),null);
        String CurrentSimName= CsSimSlot.getCurrentSimName();Log.e("CurrentSimName",CurrentSimName+"");
        String signalSim1=CsSimSlot.getSignalStrength(0);Log.e("signalSim1",signalSim1+"");
        String signalSim2=CsSimSlot.getSignalStrength(1);Log.e("signalSim2",signalSim2+"");
        List<String> listsim1=CsSimSlot.getSimNameList();

        Log.e("sim list 1",listsim1.size()+"");


        textViewDefaultSim.setText("Default Sim : "+CurrentSimName);
        for(int i=0;i<listsim1.size();i++){
            if (listsim1.get(i).equals(" ")){
                listsim1.remove(i);
            }
        }

        for(int i=0;i<listsim1.size();i++){

            Log.e("sim list 1",listsim1.get(i));
        }

        switch (listsim1.size()){
            case 0:
                swicthSim1.setClickable(false);
                switchSim2.setClickable(false);
                textViewInfoSim2.setText("sim name : "+ "null"+"\n"+"signal strength : "+"null");
                textViewInfoSim1.setText("sim name : "+ "null"+"\n"+"signal strength : "+"null");
                break;
            case 1:
                if(signalSim1==null){
                    textViewInfoSim1.setText("sim name : "+ "null"+"\n"+"signal strength : "+"null");
                    textViewInfoSim2.setText("sim name : "+ listsim1.get(0)+"\n"+"signal strength : "+signalSim2);
                    swicthSim1.setClickable(false);
                }else{
                    textViewInfoSim2.setText("sim name : "+ "null"+"\n"+"signal strength : "+"null");
                    textViewInfoSim1.setText("sim name : "+ listsim1.get(0)+"\n"+"signal strength : "+signalSim1);
                    switchSim2.setClickable(false);
                }


                break;
            case 2 :
                textViewInfoSim1.setText("sim name : "+ listsim1.get(0)+"\n"+"signal strength : "+signalSim1);
                textViewInfoSim2.setText("sim name : "+ listsim1.get(1)+"\n"+"signal strength : "+signalSim2);
                break;
        }





        swicthSim1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("SWITCH SIM 1",""+b);

                if(b==true){
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_ENABLE_SIM1.getAction(),null);
                }else{
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_DISABLE_SIM1.getAction(),null);
                }
            }
        });

        switchSim2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("SWITCH SIM 2",""+b);
                if(b==true){
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_ENABLE_SIM2.getAction(),null);
                }else{
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIM_DISABLE_SIM2.getAction(),null);
                }
            }
        });


        return v;
    }





}
