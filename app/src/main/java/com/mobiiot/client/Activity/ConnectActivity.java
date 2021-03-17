package com.mobiiot.client.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobiiot.api.CsWifi;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mobiiot.client.model.Param;

import java.util.ArrayList;
import java.util.List;

public class ConnectActivity extends AppCompatActivity {
    private static final String TAG = "ConnectActivity";

    private List<ScanResult> listWifi = new ArrayList<>();
    private RecyclerView recyclerView;

    private WifiAdapter adapter;
    private WifiManager wifiManager;
    Typeface type;
    private Button buttonGetList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);




        type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
        buttonGetList=(Button)findViewById(R.id.getlist);buttonGetList.setTypeface(type);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WifiAdapter();
        recyclerView.setAdapter(adapter);
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ScanResult scResult = listWifi.get(position);
                showExitDialog(getSecurityType(scResult),scResult.SSID);
            }
        });
        //bindRemoteService();
    }
    public int getSecurityType(ScanResult scResult) {
        if (!TextUtils.isEmpty(scResult.SSID)) {
            String capabilities = scResult.capabilities;
            Log.e(TAG, "securityType=" + capabilities);

            if (!TextUtils.isEmpty(capabilities)) {
                if (capabilities.contains("WPA") || capabilities.contains("wpa")) {
                    return WifiConfiguration.KeyMgmt.WPA_PSK;
                }else {
                    return WifiConfiguration.KeyMgmt.NONE;
                }
            }
        }
        return WifiConfiguration.KeyMgmt.NONE;
    }

    //
    private void showExitDialog(final int securityType, final String ssid) {
        final EditText edt = new EditText(this);
        edt.setMinLines(3);
        new AlertDialog.Builder(this)
                .setTitle("input password")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(edt)
                .setPositiveButton("sure", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Log.e(TAG ,"securityType:" + securityType + ":ssid:" + ssid);

                        ArrayList<Param> listParam=new ArrayList<>();
                        listParam.add(new Param("securityType",securityType));
                        listParam.add(new Param("ssid",ssid));
                        listParam.add(new Param("key",edt.getText().toString()));
                        Utils.sendActionToServiceWithParams(ConnectActivity.this,EnumAction.WIFI_CONNECT.getAction(),listParam);


                    }
                })
                .setNegativeButton("cancel", null)
                .show();
    }

    public void getList(View view) {
        //sure wifi is open

        Utils.sendActionToServiceWithParams(ConnectActivity.this,EnumAction.WIFI_GET_LIST.getAction(),null);
        wifiManager.setWifiEnabled(true);
        //get wifi list
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {

                    listWifi = CsWifi.getListWifi();

                if (listWifi != null) {
                    adapter.notifyDataSetChanged();
                } else {
                    listWifi = new ArrayList<>();
                }
            }
        }, 2000);
    }

    public class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.ViewHolder> {
        private OnItemClickListener onItemClickListener;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ConnectActivity.this).inflate(R.layout.item_wifi, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            holder.textView.setTypeface(type);
            holder.textView.setText(listWifi.get(position).SSID);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.textView, pos);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return listWifi.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.item_text);
                textView.setTypeface(type);
            }
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.onItemClickListener = listener;
        }

    }
    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
