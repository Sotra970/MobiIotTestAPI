package com.mobiiot.client.Activity;


import android.app.AlarmManager;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class ZoneTime extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "ZoneTime ";

    private ListView lv;
    private ArrayList<String> list = new ArrayList<String>();
    private AlarmManager mAlarmManager;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_time);
        Log.e(TAG, "onCreate()");

        getdata();
        mAlarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        lv = findViewById(R.id.lv_zonetime);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

    }


    public void getdata() {

        XmlResourceParser xmlParser = getResources().getXml(R.xml.timezones);

        try {
            int event = xmlParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        //Log.e(TAG, "当前标签是："+xmlParser.getName());
                        if (xmlParser.getName().equals("timezone")){
//                            Log.e(TAG, "第一个属性：" + xmlParser.getAttributeName(0)
//                                    + "   第一个属性的值：" + xmlParser.getAttributeValue(0));
                            String cityName =  xmlParser.getAttributeValue(0);
                            list.add(cityName);
                        }
                        break;
                    case XmlPullParser.TEXT:

                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                event = xmlParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Log.e(TAG, "list.get(i) = " + list.get(i) + ", i = " + i);

        ArrayList<Param> listParam=new ArrayList<>();
        listParam.add(new Param("zone",list.get(i)));
        Utils.sendActionToServiceWithParams(ZoneTime.this,EnumAction.OPERATION_CHANGE_TIMEZONE.getAction(),listParam);

        finish();

    }




}