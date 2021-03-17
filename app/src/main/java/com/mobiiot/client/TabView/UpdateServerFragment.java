package com.mobiiot.client.TabView;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiiot.client.R;
import com.mediatek.settings.service.APN;
import com.mobiiot.DownloadConfigurationFolder;
import com.mobiiot.api.CsApn;
import com.mobiiot.client.Activity.APNAddActivity;
import com.mobiiot.client.Activity.ApnListActivity;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateServerFragment extends Fragment{
    private static final String TAG = "ApnFragment lyl123";


    Button buttonCheckServer;
    EditText editTextServerFolder;
    TextView textViewDesc1,textViewDesc2,textViewDesc3;
    Typeface type;
    View v;
    Context context;

    public UpdateServerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v =inflater.inflate(R.layout.fragment_update_server, container, false);
        type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");

        context=container.getContext();
        textViewDesc1=(TextView) v.findViewById(R.id.textViewDescUpdateServer1);textViewDesc1.setTypeface(type);
        textViewDesc2=(TextView) v.findViewById(R.id.textViewDescUpdateServer2);textViewDesc2.setTypeface(type);
        textViewDesc3=(TextView) v.findViewById(R.id.textViewDescUpdateServer3);textViewDesc3.setTypeface(type);
        editTextServerFolder=(EditText)v.findViewById(R.id.editTextServerLink);editTextServerFolder.setTypeface(type);
        editTextServerFolder.setText("http://toptech.ma/devices/");


        buttonCheckServer=(Button)v.findViewById(R.id.buttonStartCheckServer);buttonCheckServer.setTypeface(type);
        buttonCheckServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadConfigurationFolder().execute(context,editTextServerFolder.getText().toString());
            }
        });


        return v;
    }




}
