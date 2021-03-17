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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import java.util.ArrayList;


public class TouchSimulationFragment extends Fragment {

    View v;
    String TAG="TouchSimulationFragment";

    TextView textViewInfoSim1,textViewInfoSim2,textViewDefaultSim;

    EditText editTextX1,editTextY1,editTextX2,editTextY2,editTextTime,editTextCodeButton;
    Button buttonApply;
    private RadioGroup radioGroup;
    private RadioButton radioButtonTouch;
    private RadioButton radioButtonLongTouch;
    private RadioButton radioButtonSwipe;
    private RadioButton radioButtonclickButton;
    private LinearLayout layoutX1,layoutX2;
    int selectedRadio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_touch_simulation, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");

        selectedRadio=0;

        editTextX1=(EditText) v.findViewById(R.id.editTextX1) ;editTextX1.setTypeface(type);
        editTextY1=(EditText) v.findViewById(R.id.editTextY1) ;editTextY1.setTypeface(type);
        editTextX2=(EditText) v.findViewById(R.id.editTextX2) ;editTextX2.setTypeface(type);
        editTextY2=(EditText) v.findViewById(R.id.editTextY2) ;editTextY2.setTypeface(type);
        editTextTime=(EditText) v.findViewById(R.id.editTextTime) ;editTextTime.setTypeface(type);editTextTime.setVisibility(View.GONE);
        editTextCodeButton=(EditText) v.findViewById(R.id.editTextCodeButton) ;editTextCodeButton.setTypeface(type);editTextCodeButton.setVisibility(View.GONE);
        buttonApply=(Button) v.findViewById(R.id.buttonSendSimulation) ;buttonApply.setTypeface(type);
        radioGroup= (RadioGroup) v.findViewById(R.id.radio_group);
        radioButtonTouch = (RadioButton) v.findViewById(R.id.radioButtonTouch);radioButtonTouch.setTypeface(type);
        radioButtonLongTouch = (RadioButton) v.findViewById(R.id.radioButton_LongTouch);radioButtonLongTouch.setTypeface(type);
        radioButtonSwipe = (RadioButton) v.findViewById(R.id.radioButton_swipe);radioButtonSwipe.setTypeface(type);
        radioButtonclickButton = (RadioButton) v.findViewById(R.id.radioButton_cick);radioButtonclickButton.setTypeface(type);
        layoutX1=(LinearLayout)v.findViewById(R.id.layoutX1);
        layoutX2=(LinearLayout)v.findViewById(R.id.layoutX2);layoutX2.setVisibility(View.GONE);


        buttonApply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent silenterService;
                int x1,y1,x2,y2,time,code;
                switch (selectedRadio){
                    case 0:
                        x1=Integer.parseInt(editTextX1.getText().toString());
                        y1=Integer.parseInt(editTextY1.getText().toString());

                        ArrayList<Param> listParam=new ArrayList<>();
                        listParam.add(new Param("x",x1));
                        listParam.add(new Param("y",y1));
                        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIMULATION_TOUCH.getAction(),listParam);

                         Log.e(TAG,"Touch : "+x1+"-"+y1);
                        break;
                    case 1:
                        x1=Integer.parseInt(editTextX1.getText().toString());
                        y1=Integer.parseInt(editTextY1.getText().toString());
                        listParam=new ArrayList<>();
                        listParam.add(new Param("x",x1));
                        listParam.add(new Param("y",y1));
                        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIMULATION_TOUCH_LONG.getAction(),listParam);

                        Log.e(TAG,"Long Touch : "+x1+"-"+y1);
                        break;
                    case 2:
                        x1=Integer.parseInt(editTextX1.getText().toString());
                        y1=Integer.parseInt(editTextY1.getText().toString());
                        x2=Integer.parseInt(editTextX2.getText().toString());
                        y2=Integer.parseInt(editTextY2.getText().toString());
                        time=Integer.parseInt(editTextTime.getText().toString());

                        listParam=new ArrayList<>();
                        listParam.add(new Param("x",x1));
                        listParam.add(new Param("y",y1));
                        listParam.add(new Param("x1",x2));
                        listParam.add(new Param("y1",y2));
                        listParam.add(new Param("time",time));
                        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIMULATION_SWIPE.getAction(),listParam);
                        Log.e(TAG,"Swipe : "+x1+"-"+y1+"-"+x2+"-"+y2+"-"+time);
                        break;
                    case 3:
                        code=Integer.parseInt(editTextCodeButton.getText().toString());
                        listParam=new ArrayList<>();
                        listParam.add(new Param("keyCode",code));
                        Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SIMULATION_BUTTON_CLICK.getAction(),listParam);
                        Log.e(TAG,"click button : "+code);
                        break;
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId){
                    case R.id.radioButtonTouch:
                        selectedRadio=0;
                        editTextTime.setVisibility(View.GONE);
                        editTextCodeButton.setVisibility(View.GONE);
                        layoutX1.setVisibility(View.VISIBLE);
                        layoutX2.setVisibility(View.GONE);
                        // do operations specific to this selection
                        break;
                    case R.id.radioButton_LongTouch:
                        selectedRadio=1;
                        editTextTime.setVisibility(View.GONE);
                        editTextCodeButton.setVisibility(View.GONE);
                        layoutX1.setVisibility(View.VISIBLE);
                        layoutX2.setVisibility(View.GONE);
                        break;
                    case R.id.radioButton_swipe:
                        selectedRadio=2;
                        editTextTime.setVisibility(View.VISIBLE);
                        editTextCodeButton.setVisibility(View.GONE);
                        layoutX1.setVisibility(View.VISIBLE);
                        layoutX2.setVisibility(View.VISIBLE);
                        // do operations specific to this selection
                        break;

                    case R.id.radioButton_cick:
                        selectedRadio=3;
                        editTextTime.setVisibility(View.GONE);
                        editTextCodeButton.setVisibility(View.VISIBLE);
                        layoutX1.setVisibility(View.GONE);
                        layoutX2.setVisibility(View.GONE);
                        break;
                }

            }
        });
        return v;
    }


}
