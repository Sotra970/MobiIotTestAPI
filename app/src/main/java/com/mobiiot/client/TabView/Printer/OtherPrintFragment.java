package com.mobiiot.client.TabView.Printer;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.mobiiot.api.CsPrinter;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class OtherPrintFragment extends Fragment  {

    View v;
    Switch swicthSyncMode;
    Button buttonPrintText,buttonPrintBitmap,buttonPrintTextAndBitmap;
    TextView textSyncModeInfo;

    String listStringToPrint[]=new String[]{"Hello World","MobiIOT Test Printer","wwww.MobiIot.com","test Sync Mode","MobiIot SDK","France"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_print_other, container, false);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        textSyncModeInfo=(TextView) v.findViewById(R.id.textViewSyncMode);
        textSyncModeInfo.setTypeface(type);
        textSyncModeInfo.setText("Sync mode allows you to return the result of each print command");


        swicthSyncMode=(Switch)v.findViewById(R.id.SwitchSyncMode);
        swicthSyncMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("swicthSyncMode",""+b);
                CsPrinter.printSetSyncMode(b);

            }
        });





        buttonPrintTextAndBitmap=(Button) v.findViewById(R.id.buttonLongTicket) ;buttonPrintTextAndBitmap.setTypeface(type);
        buttonPrintText=(Button) v.findViewById(R.id.buttonLongTicketText) ;buttonPrintText.setTypeface(type);
        buttonPrintBitmap=(Button) v.findViewById(R.id.buttonLongTicketBitmap) ;buttonPrintBitmap.setTypeface(type);
        if(Build.MODEL.contains("MPE")) {
            swicthSyncMode.setVisibility(View.GONE);
            textSyncModeInfo.setVisibility(View.GONE);
            buttonPrintTextAndBitmap.setVisibility(View.GONE);
        }



        buttonPrintBitmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i=0;i<100;i++){
                        InputStream is_ticket;
                        byte[] inputStreamToByte = null;
                        boolean isPrintOk2;
                        is_ticket = getResources().openRawResource(R.raw.mobiwire_logo);
                        try {
                            inputStreamToByte = Utils.InputStreamToByte(is_ticket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(Build.MODEL.contains("MPE")) {
                            CsPrinter.printBitmapMPE(inputStreamToByte, 0);

                        }else {
                            isPrintOk2=CsPrinter.printBitmap(inputStreamToByte, 0);
                            Log.e("Line :"+i,isPrintOk2+"");
                        }

                    }
                }
            });

            buttonPrintText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i=0;i<200;i++){
                        if(Build.MODEL.contains("MPE")) {
                            CsPrinter.printText_FullParam("Hello World" +i,
                                    1,3,1,1,false,false);

                        }else {
                            boolean isPrintOk=CsPrinter.printText_FullParam2_r("Hello World" +i,
                                    1,3,1,1,false,false,false);
                            Log.e("Line :"+i,isPrintOk+"");
                        }


                    }
                }
            });
            buttonPrintTextAndBitmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for(int i=0;i<200;i++){
                        switch (new Random().nextInt(2) + 1){
                            case 1:

                                String text=listStringToPrint[new Random().nextInt(listStringToPrint.length-1) + 0];
                                int randomSize=new Random().nextInt(2) + 0;
                                int randomFont=new Random().nextInt(5) + 0;
                                int randomAlign=new Random().nextInt(3) + 1;
                                boolean randomBold=new Random().nextBoolean();
                                boolean randomUnderline=new Random().nextBoolean();

                                boolean isPrintOk=CsPrinter.printText_FullParam2_r(text,
                                        randomSize,3,randomFont,randomAlign,randomBold,randomUnderline,false);
                                Log.e("Line : "+i+"  result = "+isPrintOk+" Print text",text+"- size : "+
                                        randomSize+"- font : "+randomFont+"- alignement : "+randomAlign+"- bold : "+randomBold+"- underline : "+randomUnderline);

                                break;
                            case 2:

                                int randomBitmap=new Random().nextInt(3) + 1;

                                InputStream is_ticket;
                                byte[] inputStreamToByte = null;
                                boolean isPrintOk2 = false;
                                switch (randomBitmap){
                                    case 1:
                                        is_ticket = getResources().openRawResource(R.raw.mobiwire_logo);
                                        try {
                                            inputStreamToByte = Utils.InputStreamToByte(is_ticket);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        isPrintOk2=CsPrinter.printBitmap(inputStreamToByte, 0);
                                        Log.e("Line :"+i,""+randomBitmap+"result"+isPrintOk2+"Print bitmap");
                                        break;
                                    case 2:
                                        is_ticket = getResources().openRawResource(R.raw.mobiwire_logo);
                                        try {
                                            inputStreamToByte = Utils.InputStreamToByte(is_ticket);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        isPrintOk2=CsPrinter.printBitmap(inputStreamToByte, 0);
                                        Log.e("Line :"+i,""+randomBitmap+"result"+isPrintOk2+"Print bitmap");
                                        break;
                                    case 3:
                                        try {
                                            isPrintOk2=CsPrinter.printBitmap(CsPrinter.createBarQrCode(listStringToPrint[new Random().nextInt(listStringToPrint.length-1) + 0], BarcodeFormat.QR_CODE,240,220));
                                        } catch (WriterException e) {
                                            e.printStackTrace();
                                        }
                                        Log.e("Line :"+i,""+randomBitmap+"result"+isPrintOk2+"Print bitmap");

                                        break;


                                }
                                break;
                        }
                    }

                }
            });




            return v;
        }






}