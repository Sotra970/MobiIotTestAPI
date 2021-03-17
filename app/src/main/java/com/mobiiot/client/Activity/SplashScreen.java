package com.mobiiot.client.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.mobiiot.client.R;
import com.mobiiot.api.MobiiotAPI;

public class SplashScreen extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        //getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        new MobiiotAPI(this);
        //ServiceUtil.bindRemoteService(this);

        Typeface type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
        ((TextView)findViewById(R.id.textSplashScreen)).setTypeface(type);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MenuActivity.class);
                //startActivity(new Intent(SplashScreen.this, PrinterActivity.class));
                startActivity(i);
                finish();
            }
        }, 2000);

    }


}
