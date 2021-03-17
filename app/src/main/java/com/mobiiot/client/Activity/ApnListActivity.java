package com.mobiiot.client.Activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.R;
import com.mediatek.settings.service.APN;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class ApnListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private static final String TAG = "ismail";



    private ListView lv_apn_list;
    private ArrayList<APN> apnlist;
    private MyAdapter myAdapter;
    Typeface type;

    private int pos;
    private final static int MSG_UPDATE_UI = 1;
    private APN newApn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apn_list);
        type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
        lv_apn_list = (ListView) findViewById(R.id.lv_apn_list);

        Intent intent = getIntent();
        apnlist = intent.getParcelableArrayListExtra("apnlist");
        // Log.e(TAG, "APNListActivity onCreate() - apnlist = " + apnlist.toString());
        myAdapter = new MyAdapter();
        lv_apn_list.setAdapter(myAdapter);
        lv_apn_list.setOnItemClickListener(this);
        lv_apn_list.setOnItemLongClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent = new Intent(ApnListActivity.this, APNActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("APN", apnlist.get(position));
        intent.putExtras(bundle);
        Log.e(TAG, "position = " + position + ", intent = " + intent);
        pos = position;
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult()");
        if ((1 == requestCode) && (2 == resultCode)){

            handler.sendEmptyMessageDelayed(MSG_UPDATE_UI, 3000);
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_UPDATE_UI:

                            Log.e(TAG, "will update apn");
                            Intent silenterService = new Intent(ApnListActivity.this, ExecuteFunctionsService.class);
                            silenterService.setAction(EnumAction.APN_ADD.getAction());
                            silenterService.putExtra("newApn",newApn);
                            startService(silenterService);
                            apnlist.set(pos, newApn);
                            Log.e(TAG, "apnlist = " + apnlist);
                            myAdapter.notifyDataSetChanged();


                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
        try {
            new AlertDialog.Builder(ApnListActivity.this)
                    .setTitle("NOTE")
                    .setMessage("will remove this apn?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                                    String apnKey = apnlist.get(position).getKey();
                            Intent silenterService = new Intent(ApnListActivity.this, ExecuteFunctionsService.class);
                            silenterService.setAction(EnumAction.APN_REMOVE.getAction());
                            silenterService.putExtra("apnKey",apnKey);
                            startService(silenterService);
                            Log.e(TAG, "apnKey = " + apnKey);
                                    /*if (isSuccess) {
                                        apnlist.remove(position);
                                        myAdapter.notifyDataSetChanged();
                                        Toast.makeText(ApnListActivity.this, "remove apn success!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ApnListActivity.this, "remove apn failure, pls try again!", Toast.LENGTH_SHORT).show();
                                    }*/

                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return apnlist.size();
        }
        @Override
        public Object getItem(int position) {
            return apnlist.get(position);
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (null == convertView){
                //Log.e(TAG, "null == convertView");
                convertView = View.inflate(ApnListActivity.this, R.layout.list_apn, null);
                holder = new ViewHolder();
                holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
                holder.text_name.setTypeface(type);
                holder.text_apn = (TextView) convertView.findViewById(R.id.text_apn);
                holder.text_apn.setTypeface(type);
                convertView.setTag(holder);
            } else {
                //Log.e(TAG, "null != convertView");
                holder = (ViewHolder)convertView.getTag();
            }
            //Log.e(TAG, "holder = " + holder);

            holder.text_name.setText(apnlist.get(position).getName());
            holder.text_apn.setText(apnlist.get(position).getApn());
            return convertView;
        }
    }

    class ViewHolder{
        public TextView text_name;
        public TextView text_apn;
    }


}
