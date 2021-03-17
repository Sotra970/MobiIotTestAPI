package com.mobiiot.client.TabView;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobiiot.api.CsData;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;



public class DataFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "DataFragment";
    Typeface type;
    private Button btManager,btDisabled,btGetDataStatus,btSimOne,btSimTwo;
    private TextView tvStatus;
    public DataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_data, container, false);

        type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        tvStatus = rootView.findViewById(R.id.fragment_data_tvStatus);tvStatus.setTypeface(type);
        btManager = rootView.findViewById(R.id.fragment_data_manageBt);btManager.setTypeface(type);
        btDisabled = rootView.findViewById(R.id.fragment_data_disableBt);btDisabled.setTypeface(type);
        btGetDataStatus = rootView.findViewById(R.id.fragment_data_getDataStatusBt);btGetDataStatus.setTypeface(type);
        btSimOne = rootView.findViewById(R.id.fragment_data_setSimOne);btSimOne.setTypeface(type);
        btSimTwo = rootView.findViewById(R.id.fragment_data_setSimTwo);btSimTwo.setTypeface(type);

        btManager.setOnClickListener(this);
        btDisabled.setOnClickListener(this);
        btGetDataStatus.setOnClickListener(this);
        btSimOne.setOnClickListener(this);
        btSimTwo.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {


        if (view == btManager) {
            Utils.sendActionToServiceWithParams(getActivity(), EnumAction.DATA_ENABLE.getAction(),null);
        } else if (view == btDisabled) {
            Utils.sendActionToServiceWithParams(getActivity(), EnumAction.DATA_DISABLE.getAction(),null);
        } else if (view == btGetDataStatus) {
            Utils.sendActionToServiceWithParams(getActivity(), EnumAction.DATA_GET_STATUS.getAction(),null);
            tvStatus.setText(CsData.getDataStatus());
        } else if (view == btSimOne){
            Utils.sendActionToServiceWithParams(getActivity(), EnumAction.DATA_SET_DEFAULT_SIM1.getAction(),null);

        } else if (view == btSimTwo) {
            Utils.sendActionToServiceWithParams(getActivity(), EnumAction.DATA_SET_DEFAULT_SIM2.getAction(),null);
        }
    }
}
