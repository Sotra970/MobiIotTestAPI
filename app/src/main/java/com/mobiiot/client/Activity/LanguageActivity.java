package com.mobiiot.client.Activity;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiiot.api.CsOperation;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import java.util.ArrayList;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {
    private static final String TAG = "LanguageActivity";



    private ListView lv;
    private  String[] languages ;
    private List<String> list = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        getLanguage();
    }

    public void getLanguage(){

                languages = CsOperation.getLngList();
                Log.d(TAG, languages[0]);
                if (languages != null) {
                    list = new ArrayList<>();
                    for(int i = 0; i < languages.length;i++) {
                        list.add(languages[i]);
                    }
                }
                lv = (ListView)findViewById(R.id.lv_changeLanguage);
                lv.setAdapter(new MyAdapter());


                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ArrayList<Param> listParam=new ArrayList<>();
                        listParam.add(new Param("i",i));
                        Utils.sendActionToServiceWithParams(LanguageActivity.this,EnumAction.OPERATION_CHANGE_LANGUAGE.getAction(),listParam);
                        finish();
                    }
                });

    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(LanguageActivity.this, R.layout.change_language_item, null) ;
            Typeface type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
            TextView tv = (TextView) view.findViewById(R.id.tv_changelanguage);
            tv.setTypeface(type);
            String s = list.get(i);
            tv.setText(s);
            return view;
        }
    }
}
