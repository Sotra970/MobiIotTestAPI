package com.mobiiot.client.TabView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CsApiAndroidQ;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.R;
import com.mobiiot.client.model.AppDetail;
import com.mobiiot.client.model.Param;

import java.util.ArrayList;
import java.util.List;

public class PackageFragment extends Fragment {

    public String TAG="PackageFragment";

    EditText editTextUrlApk,editTextPackageName;
    Button buttonInstallApk,buttonInstallFromStore,buttonUpdateApk,buttonUpdateFromStore;
    View v;
    LinearLayout layoutPackage;
    Typeface type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        v =inflater.inflate(R.layout.fragment_package, container, false);
        type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        editTextUrlApk=(EditText)v.findViewById(R.id.editTextUrlApk);editTextUrlApk.setTypeface(type);
        editTextPackageName=(EditText)v.findViewById(R.id.editTextPackageApk);editTextPackageName.setTypeface(type);

        editTextUrlApk.setText("http://toptech.ma/devices/com.mobiwire.testopencv-1.apk");
        editTextPackageName.setText("com.mobiwire.testopencv");
        buttonInstallApk=(Button)v.findViewById(R.id.buttonInstallApk);buttonInstallApk.setTypeface(type);
        buttonInstallFromStore=(Button)v.findViewById(R.id.buttonInstallApkStore);buttonInstallFromStore.setTypeface(type);
        buttonUpdateApk=(Button)v.findViewById(R.id.buttonUpdateApk);buttonUpdateApk.setTypeface(type);
        buttonUpdateFromStore=(Button)v.findViewById(R.id.buttonUpdateApkStore);buttonUpdateFromStore.setTypeface(type);

        setUpListSystemPackage();

        buttonInstallApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"Install APK");
                String editTextValue=editTextUrlApk.getText().toString();
                String editTextpackgeValue=editTextPackageName.getText().toString();
                //sendActionToService(getActivity(),EnumAction.PACKAGE_INSTALL.getAction(),editTextpackgeValue,editTextValue);

                ArrayList<Param> listParam=new ArrayList<>();
                listParam.add(new Param("packageName",editTextpackgeValue));
                listParam.add(new Param("path",editTextValue));
                Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_INSTALL.getAction(),listParam);

                //new SendAction().execute(EnumAction.INSTALL.getAction(),editTextUrlApk.getText().toString());
            }
        });

        buttonInstallFromStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"Install APK store");
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 100);
            }
        });

        buttonUpdateApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"update APK");
                String editTextValue=editTextUrlApk.getText().toString();
                String editTextpackgeValue=editTextPackageName.getText().toString();
                ArrayList<Param> listParam=new ArrayList<>();
                listParam.add(new Param("packageName",editTextpackgeValue));
                listParam.add(new Param("path",editTextValue));
                Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_UPDATE.getAction(),listParam);
                //new SendAction().execute(EnumAction.INSTALL.getAction(),editTextUrlApk.getText().toString());
            }
        });

        buttonUpdateFromStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"update APK Store");
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 101);

            }
        });

        return v;
    }

    public void setUpListSystemPackage(){
        layoutPackage=(LinearLayout)v.findViewById(R.id.layoutPackage);
        final List<String> listPackage=new ArrayList<>();

        if (Build.VERSION.SDK_INT==29) {
            listPackage.add("com.android.storagemanager");
        } else if (Build.VERSION.SDK_INT==27) {
            listPackage.add("com.mediatek.filemanager");
        }
        //listPackage.add("com.mediatek.filemanager");

        listPackage.add("com.android.providers.contacts");
        listPackage.add("com.android.email");
        listPackage.add("com.android.calculator2");
        listPackage.add("com.android.gallery3d");
        listPackage.add("com.android.calendar");
        listPackage.add("com.android.browser");
        listPackage.add("com.mediatek.camera");
        listPackage.add("com.android.contacts");
        //listPackage.add("com.android.quicksearchbox");
        listPackage.add("com.android.deskclock");
        listPackage.add("com.android.mms");
        listPackage.add("com.android.dialer");

        List<String> listCustomerPackage=setUpListCustomerPackage();

        for(int i=0;i<listCustomerPackage.size();i++){
            LinearLayout layout=new LinearLayout(getActivity());
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout layoutButton=new LinearLayout(getActivity());
            layoutButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutButton.setOrientation(LinearLayout.HORIZONTAL);

            final TextView tv1 = new TextView(getActivity());
            tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,5));
            tv1.setText(listCustomerPackage.get(i));

            final String packageName=listCustomerPackage.get(i);
            tv1.setTypeface(type);
            tv1.setPadding(15,5,0,5);
            tv1.setTextSize(18);
            tv1.setTextColor(Color.WHITE);

            LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(80, ViewGroup.LayoutParams.WRAP_CONTENT,1);
            lp1.setMargins(8,8,8,8);




            Button buttonRemove=new Button(getActivity());
            buttonRemove.setLayoutParams(lp1);
            buttonRemove.setBackgroundResource(R.color.white);
            buttonRemove.setText("Remove");
            buttonRemove.setTypeface(type);
            buttonRemove.setTextColor(getResources().getColor(R.color.colorPackage));
            buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("Remove",tv1.getText().toString());
                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("packageName",packageName));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_REMOVE.getAction(),listParam);

                }
            });

            Button buttonStart=new Button(getActivity());
            buttonStart.setLayoutParams(lp1);
            buttonStart.setBackgroundResource(R.color.white);
            buttonStart.setText("START");
            buttonStart.setTypeface(type);
            buttonStart.setTextColor(getResources().getColor(R.color.colorPackage));
            buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("START",tv1.getText().toString());
                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("packageName",packageName));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_START.getAction(),listParam);

                }
            });

            Button buttonStop=new Button(getActivity());
            buttonStop.setLayoutParams(lp1);
            buttonStop.setBackgroundResource(R.color.white);
            buttonStop.setText("STOP");
            buttonStop.setTypeface(type);
            buttonStop.setTextColor(getResources().getColor(R.color.colorPackage));
            buttonStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("STOP",tv1.getText().toString());
                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("packageName",packageName));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_STOP.getAction(),listParam);

                }
            });



            View v=new View(getActivity());
            v.setBackgroundColor(Color.WHITE);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));

            layout.addView(tv1);
            layoutButton.addView(buttonRemove);
            layoutButton.addView(buttonStart);
            layoutButton.addView(buttonStop);

            layoutPackage.addView(layout);
            layoutPackage.addView(layoutButton);
            layoutPackage.addView(v);
        }

        for(int i=0;i<listPackage.size();i++){
            LinearLayout layout=new LinearLayout(getActivity());
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout layoutButton=new LinearLayout(getActivity());
            layoutButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutButton.setOrientation(LinearLayout.HORIZONTAL);

            final TextView tv1 = new TextView(getActivity());
            tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,5));
            tv1.setText(listPackage.get(i));

            final String packageName=listPackage.get(i);
            tv1.setTypeface(type);
            tv1.setPadding(5,10,0,5);
            tv1.setTextSize(18);
            tv1.setTextColor(Color.WHITE);

            LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(80, ViewGroup.LayoutParams.WRAP_CONTENT,1);
            lp1.setMargins(8,8,8,8);




            Button buttonShow=new Button(getActivity());
            buttonShow.setLayoutParams(lp1);
            buttonShow.setBackgroundResource(R.color.white);
            buttonShow.setText("SHOW");
            buttonShow.setTypeface(type);
            buttonShow.setTextColor(getResources().getColor(R.color.colorPackage));
            buttonShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("SHOW",tv1.getText().toString());

                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("packageName",packageName));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_SHOW.getAction(),listParam);
                }
            });

            Button buttonHide=new Button(getActivity());
            buttonHide.setLayoutParams(lp1);
            buttonHide.setBackgroundResource(R.color.white);
            buttonHide.setText("HIDE");
            buttonHide.setTypeface(type);
            buttonHide.setTextColor(getResources().getColor(R.color.colorPackage));
            buttonHide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("HIDE",tv1.getText().toString());
                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("packageName",packageName));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_HIDE.getAction(),listParam);
                    //sendActionToService(getActivity(),EnumAction.PACKAGE_HIDE.getAction(),packageName,null);
                }
            });

            Button buttonStart=new Button(getActivity());
            buttonStart.setLayoutParams(lp1);
            buttonStart.setBackgroundResource(R.color.white);
            buttonStart.setText("START");
            buttonStart.setTypeface(type);
            buttonStart.setTextColor(getResources().getColor(R.color.colorPackage));
            buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("START",tv1.getText().toString());
                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("packageName",packageName));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_START.getAction(),listParam);


                }
            });

            Button buttonStop=new Button(getActivity());
            buttonStop.setLayoutParams(lp1);
            buttonStop.setBackgroundResource(R.color.white);
            buttonStop.setText("STOP");
            buttonStop.setTypeface(type);
            buttonStop.setTextColor(getResources().getColor(R.color.colorPackage));
            buttonStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("STOP",tv1.getText().toString());
                    ArrayList<Param> listParam=new ArrayList<>();
                    listParam.add(new Param("packageName",packageName));
                    Utils.sendActionToServiceWithParams(getActivity(),EnumAction.PACKAGE_STOP.getAction(),listParam);

                }
            });

            View v=new View(getActivity());
            v.setBackgroundColor(Color.WHITE);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));

            layout.addView(tv1);
            layoutButton.addView(buttonHide);
            layoutButton.addView(buttonShow);
            layoutButton.addView(buttonStart);
            layoutButton.addView(buttonStop);

            layoutPackage.addView(layout);
            layoutPackage.addView(layoutButton);
            layoutPackage.addView(v);
        }




    }

    public List<String> setUpListCustomerPackage(){
        PackageManager manager = getActivity().getPackageManager();
        final List<String> listPackage=new ArrayList<>();

        Intent nt = new Intent(Intent.ACTION_MAIN, null);
        nt.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(nt, 0);
        for(ResolveInfo ri:availableActivities){

            String packageName=ri.activityInfo.packageName;
            String label=ri.loadLabel(manager)+"";
            AppDetail app=new AppDetail(label,packageName,ri.loadIcon(manager));

            if(Utils.isSystemPackage(app,getActivity())==null) {
                Log.e(TAG,app.getName());
                listPackage.add(app.getName());
            }

        }


       return listPackage;





    }


    public static void sendActionToService(Context context, String action,String packageName,String path){
        Intent silenterService = new Intent(context, ExecuteFunctionsService.class);
        silenterService.setAction(action);
        silenterService.putExtra("packageName",packageName);
        if(path!=null){
            silenterService.putExtra("path",path);
        }
        context.startService(silenterService);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent silenterService = new Intent(getActivity(), ExecuteFunctionsService.class);
        if(requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            Log.e("install ",Utils.getPathFromUri(selectedfile));
            String editTextpackgeValue=editTextPackageName.getText().toString();
            sendActionToService(getActivity(),EnumAction.PACKAGE_INSTALL.getAction(),editTextpackgeValue,Utils.getPathFromUri(selectedfile));
        }else if(requestCode == 101 && resultCode == Activity.RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            Log.e("update",Utils.getPathFromUri(selectedfile));
            String editTextpackgeValue=editTextPackageName.getText().toString();
            sendActionToService(getActivity(),EnumAction.PACKAGE_UPDATE.getAction(),editTextpackgeValue,Utils.getPathFromUri(selectedfile));

        }
        getActivity().startService(silenterService);
    }
}
