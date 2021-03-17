package com.mobiiot.client.Activity;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiiot.api.Utils.ServiceUtil;
import com.mobiiot.client.Adapter.BluetoothItemAdapter;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.R;
import com.mediatek.settings.service.CSAndoridGo;

import java.util.ArrayList;
import java.util.List;

public class PairDeviceActivity extends Activity {

    private ListView listView;
    private List listDevice = new ArrayList<BluetoothDevice>();
    private List listName = new ArrayList<String>();
    private List listAddress = new ArrayList<String>();
    private CSAndoridGo iMyAidlInterface;
    private BluetoothItemAdapter adapter ;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_device);

        Utils.sendActionToServiceWithParams(this, EnumAction.BLUETOOTH_GET_LIST_PAIR.getAction(),null);

        //iMyAidlInterface = ServiceUtil.getiMyAidlInterface();
        try {
            listDevice = iMyAidlInterface.getDevicePairList();
            if (null != listDevice){
                for(int i = 0 ; i < listDevice.size() ; i++){
                    BluetoothDevice device = (BluetoothDevice) listDevice.get(i);
                    listName.add(device.getName());
                    listAddress.add(device.getAddress());
                    textView.setText("pair device");
                }

            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        listView = findViewById(R.id.activity_PairDevice_listview);
        adapter = new BluetoothItemAdapter(this,listName,listAddress);
        listView.setAdapter(adapter);

    }
}
