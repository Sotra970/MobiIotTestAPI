package com.mobiiot.client.TabView;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobiiot.client.Activity.LanguageActivity;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Activity.ZoneTime;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import java.util.ArrayList;
import java.util.TimeZone;

public class OperationFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "OperationFragment lyl123";

    private Button language,time,wallpager,changeBootAnimation,changeShutAnimation;

    public OperationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_operation, container, false);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        language = rootView.findViewById(R.id.fragment_opera_language);language.setTypeface(type);
        time = rootView.findViewById(R.id.fragment_opera_time);time.setTypeface(type);time.setText(getDefaultTimeZone());
        wallpager = rootView.findViewById(R.id.fragment_opera_wallpager);wallpager.setTypeface(type);
        changeBootAnimation = rootView.findViewById(R.id.fragment_opera_bootanimation);changeBootAnimation.setTypeface(type);
        changeShutAnimation = rootView.findViewById(R.id.fragment_opera_shutanimation);changeShutAnimation.setTypeface(type);
        changeBootAnimation.setOnClickListener(this);
        changeShutAnimation.setOnClickListener(this);
        language.setOnClickListener(this);
        time.setOnClickListener(this);
        wallpager.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view == language){
            Intent intent = new Intent(getContext(), LanguageActivity.class);
            startActivity(intent);
        } else if (view == time){
            Intent intent = new Intent(getContext(), ZoneTime.class);
            startActivity(intent);
        } else if (view == wallpager){

            Intent intent = new Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 124);

        }else if (view == changeBootAnimation){
            Intent intent = new Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);

        }else if (view == changeShutAnimation){

            Intent intent = new Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 122);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent silenterService = new Intent(getActivity(), ExecuteFunctionsService.class);
        if(requestCode == 123 && resultCode == Activity.RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            Log.e("selected File boot ", Utils.getPathFromUri(selectedfile));

            ArrayList<Param> listParam=new ArrayList<>();
            listParam.add(new Param("path",Utils.getPathFromUri(selectedfile)));
            listParam.add(new Param("name","bootanimation.zip"));
            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.OPERATION_CHANGE_BOOT_ANIMATION.getAction(),listParam);

        }else if(requestCode == 122 && resultCode == Activity.RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            Log.e("selected File shut",Utils.getPathFromUri(selectedfile));

            ArrayList<Param> listParam=new ArrayList<>();
            listParam.add(new Param("path",Utils.getPathFromUri(selectedfile)));
            listParam.add(new Param("name","shutanimation.zip"));
            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.OPERATION_CHANGE_SHUT_ANIMATION.getAction(),listParam);

        }else if(requestCode == 124 && resultCode == Activity.RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            ArrayList<Param> listParam=new ArrayList<>();
            listParam.add(new Param("path",Utils.getPathFromUri(selectedfile)));
            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.OPERATION_CHANGE_WALLPAPER.getAction(),listParam);

            Log.e("selected File wallp",Utils.getPathFromUri(selectedfile));
        }

    }

    @Override
    public void onResume() {
        time.setText(getDefaultTimeZone());
        super.onResume();
    }



    public String getDefaultTimeZone(){
        String s = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT) + "\n" + TimeZone.getDefault().getDisplayName();
        return s;
    }

}
