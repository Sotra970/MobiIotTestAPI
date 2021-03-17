package com.mobiiot.client.TabView.Printer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobiiot.client.R;
import com.mobiiot.api.CsPrinter;


public class PrintEscPosFragment extends Fragment {

    View v;

    Button buttonEnableEscPos,buttonPrintEscPos;
    TextView textViewEscPosComment;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_print_escpos, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");

        buttonEnableEscPos=(Button)v.findViewById(R.id.buttonOpenEscPos);buttonEnableEscPos.setTypeface(type);
        buttonPrintEscPos=(Button)v.findViewById(R.id.buttonPrintEscPos);buttonPrintEscPos.setTypeface(type);
        textViewEscPosComment=(TextView) v.findViewById(R.id.textViewEscPosComment);textViewEscPosComment.setTypeface(type);
        textViewEscPosComment.setText("Hello World \n[1b 40 48 65 6c 6c 6f 20 77 6f 72 6c 64 0a]");

        buttonEnableEscPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CsPrinter.powerOn(true);
            }
        });

        buttonPrintEscPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] data = new byte[] {
                        //size: 0
                        0x1d, 0x21, 0x00,
                        //left to right
                        0x1b,0x1c,0x26,0x20,0x56,0x31,0x20,0x73,0x65,0x74,0x76,0x61,0x6c,0x20,0x22,0x70,0x72,
                        0x6e,0x5f,0x64,0x69,0x72,0x22,0x20,0x22,0x6c,0x65,0x66,0x74,0x22,0x0d,0x0a,
                        //font: 3
                        0x1b, 0x4d, 0x03,
                        //align: left
                        0x1b, 0x61, 0x00,
                        //density: 3
                        0x1d, 0x28, 0x4b, 0x02, 0x00, 0x31, 0x03,
                        //bold or not
                        0x1b, 0x45, 0x01,
                        //underline or not
                        0x1b, 0x2d, 0x00,
                        //inverse or not
                        0x1d, 0x42, 0x00,
                        //set unicode
                        0x1b,0x1c,0x26,0x20,0x56,0x31,0x20,0x73,0x65,0x74,0x76,0x61,0x6c,0x20,0x22,0x75,0x6e,0x69,0x63,
                        0x6f,0x64,0x65,0x5f,0x69,0x6e,0x22,0x20,0x22,0x65,0x6e,0x61,0x62,0x6c,0x65,0x22,0x0d,0x0a,
                        //Hello World
                        0x00, 0x48, 0x00, 0x65, 0x00, 0x6c, 0x00, 0x6c, 0x00, 0x6f, 0x00, 0x20, 0x00, 0x57, 0x00, 0x6f, 0x00, 0x72, 0x00, 0x6c, 0x00, 0x64, 0x00, 0x2e,
                        //set non unicode
                        (byte)0xff, (byte)0xf0,
                        //end
                        0x0a
                };
                CsPrinter.transmit(data,data.length);
            }
        });


        return v;
    }
}
