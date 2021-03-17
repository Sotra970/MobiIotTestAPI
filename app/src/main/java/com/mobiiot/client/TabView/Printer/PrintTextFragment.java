package com.mobiiot.client.TabView.Printer;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.mobiiot.client.R;
import com.mobiiot.api.CsPrinter;


public class PrintTextFragment extends Fragment  {

    View v;
    Switch swicthBold,switchUnderline;
    Spinner spinnerSize,spinnerFont, spinnerDirection, spinnerAlignment,spinnerDarkness;
    TextView textViewSize,textViewFont, textViewDirection, textViewAlignment,textViewDarkness;
    Button buttonPrintText;
    EditText editTextPrint;

    boolean bold, underline;
    int sizeValue, fontValue, directionValue,alignmentValue;

    String[] size = { "0","1", "2","3","4","5"};
    String[] font = { "1", "2","3","4","5"};
    String[] direction = { "3", "2","1"};
    String[] alignment = { "1", "2","3"};
    String[] darkness = { "1", "2","3","4"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_print_text, container, false);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        bold =false;underline=false;sizeValue=0; fontValue=1; directionValue=1;alignmentValue=1;
        editTextPrint=(EditText)v.findViewById(R.id.editTextPrint);
        swicthBold=(Switch)v.findViewById(R.id.SwitchBold) ;swicthBold.setTypeface(type);
        switchUnderline=(Switch)v.findViewById(R.id.switchUnderline) ;switchUnderline.setTypeface(type);

        textViewSize = (TextView) v.findViewById(R.id.textViewSize);textViewSize.setTypeface(type);
        spinnerSize = (Spinner) v.findViewById(R.id.spinnerSize);
        ArrayAdapter arrayAdapterSize = new ArrayAdapter(v.getContext(), android.R.layout.simple_spinner_item, size);
        arrayAdapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(arrayAdapterSize);
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                sizeValue=Integer.parseInt(size[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        textViewFont = (TextView) v.findViewById(R.id.textViewFont);textViewFont.setTypeface(type);
        spinnerFont = (Spinner) v.findViewById(R.id.spinnerFont);
        ArrayAdapter arrayAdapterFont = new ArrayAdapter(v.getContext(), android.R.layout.simple_spinner_item, font);
        arrayAdapterFont.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFont.setAdapter(arrayAdapterFont);
        spinnerFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                fontValue=Integer.parseInt(font[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        textViewDirection = (TextView) v.findViewById(R.id.textViewDirection);textViewDirection.setTypeface(type);
        spinnerDirection = (Spinner) v.findViewById(R.id.spinnerDirection);
        ArrayAdapter arrayAdapterDirection = new ArrayAdapter(v.getContext(), android.R.layout.simple_spinner_item, direction);
        arrayAdapterDirection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDirection.setAdapter(arrayAdapterDirection);
        spinnerDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                directionValue=Integer.parseInt(direction[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        textViewAlignment = (TextView) v.findViewById(R.id.textViewAlignment);textViewAlignment.setTypeface(type);
        spinnerAlignment = (Spinner) v.findViewById(R.id.spinnerAlignment);
        ArrayAdapter arrayAdapterAlignment = new ArrayAdapter(v.getContext(), android.R.layout.simple_spinner_item, alignment);
        arrayAdapterAlignment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlignment.setAdapter(arrayAdapterAlignment);
        spinnerAlignment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                alignmentValue=Integer.parseInt(alignment[position]);
                //CsPrinter.printSetDarkness(Integer.parseInt(alignment[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });

        textViewDarkness = (TextView) v.findViewById(R.id.textViewDarkness);textViewDarkness.setTypeface(type);
        spinnerDarkness = (Spinner) v.findViewById(R.id.spinnerDarkness);
        ArrayAdapter arrayAdapterDarkness = new ArrayAdapter(v.getContext(), android.R.layout.simple_spinner_item, darkness);
        arrayAdapterDarkness.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDarkness.setAdapter(arrayAdapterAlignment);
        spinnerDarkness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                CsPrinter.printSetDarkness(Integer.parseInt(darkness[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });






        swicthBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("swicthUnknownSource",""+b);
                bold = b;
            }
        });

        switchUnderline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("switchAdb",""+b);
                underline = b;

            }
        });

        buttonPrintText=(Button) v.findViewById(R.id.buttonPrintText) ;buttonPrintText.setTypeface(type);


        buttonPrintText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!editTextPrint.getText().equals("")){

                    Log.e("Print text",editTextPrint.getText().toString()+"- size : "+
                            sizeValue+"- direction : "+directionValue+"- font : "+fontValue+"- alignement : "+alignmentValue+"- bold : "+bold+"- underline : "+underline);
                    if(!Build.MODEL.contains("MPE")){
                        boolean result = CsPrinter.printText_FullParam2_r(editTextPrint.getText().toString(),
                                sizeValue,directionValue,fontValue,alignmentValue,bold,underline,false);
                        Log.e("print result text",result+"");
                    }else{
                        CsPrinter.printText_FullParam(editTextPrint.getText().toString(),
                                sizeValue,directionValue,fontValue,alignmentValue,bold,underline);
                    }

                }

            }
        });




        return v;
    }


}
