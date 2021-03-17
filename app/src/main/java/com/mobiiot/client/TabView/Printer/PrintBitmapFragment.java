package com.mobiiot.client.TabView.Printer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.mobiiot.api.CsOperation;
import com.mobiiot.api.CsPrinter;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class PrintBitmapFragment extends Fragment {

    View v;

    EditText editTextPath, editTextQrCode;
    Button buttonPrintFromPath, buttonPrintFromRes, buttonPrintQrCode, buttonPrintScreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_print_bitmap, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");

        editTextPath=(EditText)v.findViewById(R.id.editTextPathBitmap);editTextPath.setTypeface(type);editTextPath.setText("/sdcard/bitmap_ticket.bmp");
        editTextQrCode=(EditText)v.findViewById(R.id.editTextQrCode);editTextQrCode.setTypeface(type);editTextQrCode.setText("123456789");

        buttonPrintFromPath=(Button)v.findViewById(R.id.buttonPrintBitmapFromPath);buttonPrintFromPath.setTypeface(type);
        buttonPrintFromRes=(Button)v.findViewById(R.id.buttonPrintBitmapRes);buttonPrintFromRes.setTypeface(type);
        buttonPrintQrCode=(Button)v.findViewById(R.id.buttonPrintBitmapQrCode);buttonPrintQrCode.setTypeface(type);
        buttonPrintScreen=(Button)v.findViewById(R.id.buttonPrintBitmapScreen);buttonPrintScreen.setTypeface(type);


        if(Build.MODEL.contains("MPE")){
            editTextPath.setVisibility(View.GONE);
            buttonPrintFromPath.setVisibility(View.GONE);
            buttonPrintScreen.setVisibility(View.GONE);
            editTextQrCode.setVisibility(View.GONE);
            buttonPrintQrCode.setVisibility(View.GONE);
        }

        buttonPrintFromPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click ","buttonPrintFromPath");
                Bitmap bitmap = BitmapFactory.decodeFile(editTextPath.getText().toString());
                CsPrinter.printBitmap(bitmap,0);
            }
        });

        buttonPrintFromRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream is_ticket = getResources().openRawResource(R.raw.mobiwire_logo);
                byte[] inputStreamToByte = null;
                try {
                    inputStreamToByte = Utils.InputStreamToByte(is_ticket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!Build.MODEL.contains("MPE")){
                    boolean result = CsPrinter.printBitmap(inputStreamToByte, 0);
                    Log.e("print result bitmap",result+"");
                }else{
                    CsPrinter.printBitmapMPE(inputStreamToByte, 0);
                }

            }
        });

        buttonPrintQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CsPrinter.printBitmap(CsPrinter.createBarQrCode(editTextQrCode.getText().toString(), BarcodeFormat.QR_CODE,240,220));
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });

        buttonPrintScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CsOperation.screenShot();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String path = "/sdcard/Pictures/Screenshots";
                File directory = new File(path);
                File[] files = directory.listFiles();
                Log.d("Files", "Size: "+ files.length);
                for (int i = 0; i < files.length; i++)
                {
                    Log.d("Files", "FileName:" + files[i].getName());
                    Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/Pictures/Screenshots/"+files[i].getName());
                    CsPrinter.printBitmap(bitmap,0);

                }
            }
        });

        return v;
    }


}
