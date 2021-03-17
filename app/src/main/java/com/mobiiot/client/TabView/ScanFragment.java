package com.mobiiot.client.TabView;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mobiiot.api.CsScanner;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ScanFragment";

    private Button btOpenNewOnce,btOpenNewMore;
    private ListView listView;

    private int isScanMore;

    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);
        isScanMore = 0;

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        btOpenNewOnce = rootView.findViewById(R.id.fragment_scan_once);btOpenNewOnce.setTypeface(type);
        btOpenNewMore = rootView.findViewById(R.id.fragment_scan_more);btOpenNewMore.setTypeface(type);
        listView = rootView.findViewById(R.id.new_scan_list);
        btOpenNewOnce.setOnClickListener(this);
        btOpenNewMore.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        listView.setAdapter(null);
    }

    @Override
    public void onResume() {
        super.onResume();


        String[] newScanInfo = null;
        if(isScanMore == 1) {

                isScanMore = 0;
                newScanInfo = new String[]{CsScanner.getScanInfo()};

            try {
                show(newScanInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }else if(isScanMore == 2){

                isScanMore = 0;
                newScanInfo = CsScanner.getNewScanInfo();
            try {
                show(newScanInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    ArrayList<Param> listParam;
    public void show(String[] newScanInfo) throws JSONException {
        if(newScanInfo!= null && newScanInfo.length>0){

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,newScanInfo);
            listView.setAdapter(arrayAdapter);
            JSONArray jsonArray1 = new JSONArray(newScanInfo);
            ArrayList<Param> listParam=new ArrayList<>();
            listParam.add(new Param("scanResult",jsonArray1.toString()));
            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.SCANNER_GET_RESULT.getAction(),listParam);

            //tvShow.setText("");

        }else {
            listView.setAdapter(null);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btOpenNewOnce){
                isScanMore =1;
                Utils.sendActionToServiceWithParams(getActivity(), EnumAction.SCANNER_OPEN_ONE.getAction(),null);
        }else if(v == btOpenNewMore){
                isScanMore = 2;
                Utils.sendActionToServiceWithParams(getActivity(), EnumAction.SCANNER_OPEN_MORE.getAction(),null);

        }
    }
}

