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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobiiot.api.Cs_KeyStore;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KeystoreFragement extends Fragment {

    public String TAG="PackageFragment";


    Button buttonInstallKeyFromStorage,buttonResetKeyList,buttonifKeyExist;
    TextView textViewKeystore;
    View v;
    LinearLayout layoutPackage;
    Typeface type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        v =inflater.inflate(R.layout.fragment_keystore, container, false);
        type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");

        textViewKeystore=(TextView) v.findViewById(R.id.textViewKeystoreInfo);textViewKeystore.setTypeface(type);
        textViewKeystore.setText("If your application is not signed with keystore added in  System with  addKeyToList function, you cannot execute  functions of MobiIot API.\n"+
                "After adding the Keystore you must remove the current version, and install a signed version.");



        buttonInstallKeyFromStorage=(Button)v.findViewById(R.id.buttonAddKeyFromStorage);buttonInstallKeyFromStorage.setTypeface(type);
        buttonResetKeyList=(Button)v.findViewById(R.id.buttonResetListKey);buttonResetKeyList.setTypeface(type);
        buttonifKeyExist=(Button)v.findViewById(R.id.buttonCheckKeystore);buttonifKeyExist.setTypeface(type);




        buttonInstallKeyFromStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"buttonInstallKeyFromStorage");
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 110);
            }
        });

        buttonResetKeyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"buttonResetKeyList");
                Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KEYSTORE_RESET.getAction(),null);
            }
        });

        buttonifKeyExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"buttonifKeyExist : ");
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 111);


            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<Param> listParam=new ArrayList<>();

        if(requestCode == 110 && resultCode == Activity.RESULT_OK) {
            Uri selectedfile = data.getData();
            Log.e("add key ",Utils.getPathFromUri(selectedfile));
            listParam.add(new Param("keystore",Utils.getPathFromUri(selectedfile)));
            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KEYSTORE_ADD.getAction(),listParam);
        }else if(requestCode == 111 && resultCode == Activity.RESULT_OK) {
            Uri selectedfile = data.getData();
            Log.e("if key exist",Utils.getPathFromUri(selectedfile));
            String fileContent = Utils.readFile(new File(Utils.getPathFromUri(selectedfile)));
            List<String> fileContentList= Utils.alertString(fileContent);
            byte[] fileByte= Utils.getBytes(fileContentList);
            listParam.add(new Param("keystore",Utils.getPathFromUri(selectedfile)));
            Utils.sendActionToServiceWithParams(getActivity(),EnumAction.KEYSTORE_EXIST.getAction(),listParam);
            Cs_KeyStore.doesKeyExist(fileByte);
        }
    }

}
