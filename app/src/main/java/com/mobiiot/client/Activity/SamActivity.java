package com.mobiiot.client.Activity;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobiiot.client.R;
import com.mobiwire.csapi.CsNewSam;

public class SamActivity extends AppCompatActivity {

    private Context mContext = SamActivity.this;
    private Button samOpen;
    private Button samClose;
    private Button samAtr;
    private Button samApdu;

    private CsNewSam sam;
    private final int CARD_SAM = 0;
    private final int CARD_SMART = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sam);

        sam = new CsNewSam(mContext);
        Typeface type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");

        samOpen = (Button) findViewById(R.id.samOpen);samOpen.setTypeface(type);
        samClose = (Button) findViewById(R.id.samClose);samClose.setTypeface(type);
        samAtr = (Button) findViewById(R.id.samAtr);samAtr.setTypeface(type);
        samApdu = (Button) findViewById(R.id.samApdu);samApdu.setTypeface(type);

        samOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sam.open(CARD_SMART);
            }
        });
        samClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sam.close(CARD_SMART);
            }
        });
        samAtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] returnValue;
                returnValue = sam.IccPowerOn(CARD_SMART);
                Log.d("MobiIot API", "ATR return: " + byteArrayToHexStr(returnValue));
                Toast.makeText(SamActivity.this, byteArrayToHexStr(returnValue),Toast.LENGTH_LONG).show();
            }
        });

        samApdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] returnValue;
                byte[] apdu = new byte[] {0x00, (byte)0x84, 0x00, 0x00, 0x08 };
                returnValue = sam.sendApdu(CARD_SMART,apdu);
                Log.d("MobiIot API", "APDU return: " + byteArrayToHexStr(returnValue));
                Toast.makeText(SamActivity.this, byteArrayToHexStr(returnValue),Toast.LENGTH_LONG).show();
            }
        });
    }

    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}