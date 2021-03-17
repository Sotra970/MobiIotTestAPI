package com.mobiiot.client.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobiiot.client.R;

public class ChoiceActivity extends AppCompatActivity {

    Button buttonSimpleTest, ButtonSendAction, buttonRecieveAction, buttonSelfTesting;
    TextView textViewSimpleTest, textViewReceiveAction, textViewSendAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Typeface type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");

        buttonSimpleTest=(Button)findViewById(R.id.buttonSimpleTest);buttonSimpleTest.setTypeface(type);
        ButtonSendAction=(Button)findViewById(R.id.buttonSendAction);ButtonSendAction.setTypeface(type);
        buttonRecieveAction=(Button)findViewById(R.id.buttonReceiveAction);buttonRecieveAction.setTypeface(type);
        buttonSelfTesting=(Button)findViewById(R.id.buttonSelfTesting);buttonSelfTesting.setTypeface(type);
        textViewSimpleTest=(TextView) findViewById(R.id.textViewSimpleTest);textViewSimpleTest.setTypeface(type);
        textViewReceiveAction=(TextView) findViewById(R.id.textViewReceiveAction);textViewReceiveAction.setTypeface(type);

        buttonSimpleTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChoiceActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
