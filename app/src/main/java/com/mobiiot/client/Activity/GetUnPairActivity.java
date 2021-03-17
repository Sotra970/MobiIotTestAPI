package com.mobiiot.client.Activity;


import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.client.Adapter.BluetoothItemAdapter;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.R;
import com.mediatek.settings.service.CSAndoridGo;

import java.util.ArrayList;
import java.util.List;


public class GetUnPairActivity extends Activity {
    String TAG="GetUnPairActivity";
    private ListView listView;
    private List listDevice = new ArrayList<BluetoothDevice>();
    private List listName = new ArrayList<String>();
    private List listAddress = new ArrayList<String>();
    private CSAndoridGo iMyAidlInterface;
    private BluetoothItemAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_un_pair);

        Utils.sendActionToServiceWithParams(this, EnumAction.BLUETOOTH_GET_LIST_PAIR.getAction(),null);

        //iMyAidlInterface = ServiceUtil.getiMyAidlInterface();
        try {
            listDevice.addAll(iMyAidlInterface.getDeviceUnPairList());
            Log.d(TAG, "onCreate: "+listDevice.size());
            if (null != listDevice){
                for(int i = 0 ; i < listDevice.size() ; i++){
                    BluetoothDevice device = (BluetoothDevice) listDevice.get(i);
                    listName.add(device.getName());
                    Log.d(TAG, "onCreate: "+listName.get(i));
                    listAddress.add(device.getAddress());
                    Log.d(TAG, "onCreate: "+listAddress.size());
                }

            }else{
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        listView = findViewById(R.id.activity_bluetooth_unpair_listview);
        adapter = new BluetoothItemAdapter(this,listName,listAddress);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent silenterService = new Intent(GetUnPairActivity.this, ExecuteFunctionsService.class);
                    silenterService.setAction(EnumAction.BLUETOOTH_CONNECT.getAction());
                    silenterService.putExtra("i",position);
                    startService(silenterService);

                    iMyAidlInterface.connectToBluetooth(position);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

