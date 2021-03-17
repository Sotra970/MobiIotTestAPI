package com.mobiiot.client.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.R;
import com.mediatek.settings.service.APN;

public class APNActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "APNActivity";

    private APN oldApn;
    private final static int MSG_UPDATE_UI = 1;
    private APN newApn;

    private String[] nameList = {
            "Name", "APN", "Proxy", "Port", "Username",
            "Password", "Server", "MMSC", "MMS proxy", "MMS port",
            "MCC", "MNC", "Authentication type", "APN type", "APN protocol",
            "APN roaming protocol", "APN enable/disable", "Bearer", "MVNO type", "MVNO value"};
    private String[] valueList = new String[20]/*{
            "", "", "", "", "",
            "", "", "", "", "",
            "460", "02", "", "", "IPv4",
            "IPv4", "APN enabled", "Unspecified", "None", ""}*/;

    private String[] itemsAuthTypeVal = {"None", "PAP", "CHAP", "PAP or CHAP"};
    private String[] itemsAPNProtocol = {"IPv4", "IPv6", "IPv4/IPv6"};
    private String[] itemsBearer = {
            "Unspecified", "LTE", "HSPA", "HSPAP", "HSUPA",
            "HSDPA", "UMTS", "GEGE", "GPRS", "eHRPD",
            "EVDO_B", "EVDO_A", "EVDO_0", "1XRTT", "IS95B",
            "IS95A", "GSM", "TD_SCDMA", "IWLAN"};
    private boolean[] checkItems = new boolean[itemsBearer.length];
    private String[] itemsMVNOType = {"None", "SPN", "IMSI", "GID", "PNN"};

    private ListView lv_edit_apn;
    private MyAdapter myAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);//这里是调用menu文件夹中的main.xml，在登陆界面label右上角的三角里显示其他功能
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save){
            setValue();
            Log.e(TAG, "will save, new apn = " + newApn);

            Bundle bundle = new Bundle();
            bundle.putParcelable("NEW_APN", newApn);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            Log.e(TAG, "newApn = " + newApn);
            Log.e(TAG, "bundle = " + bundle + ", intent = " + intent);
            setResult(2,intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apn);

        Intent intent1 = getIntent();
        Bundle extras = intent1.getExtras();
        oldApn = (APN)extras.get("APN");
        newApn = new APN(oldApn);
        Log.e(TAG, "new apn = " +newApn);
        getValue();

        lv_edit_apn = (ListView) findViewById(R.id.lv_edit_apn);
        myAdapter = new MyAdapter();
        lv_edit_apn.setAdapter(myAdapter);
        lv_edit_apn.setDividerHeight(0);//去除分割线
        lv_edit_apn.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (12 == i){
            showRadioAlertDialog(itemsAuthTypeVal, i);
        } else if (14 == i || 15 == i){//IPv4
            showRadioAlertDialog(itemsAPNProtocol, i);
        } else if (17 == i){//Unspecified
            //showMultiAlerDialog(itemsBearer, i);//do nothing
        } else if (18 == i) {//None
            showRadioAlertDialog(itemsMVNOType, i);
        } else {
            showEditAlertDialog(i);
        }
    }

    private void showRadioAlertDialog(final String[] items, final int position){
        int checkItem = 0;

        if (!TextUtils.isEmpty(valueList[position])){
            for (int t = 0; t < items.length; t++) {
                if (items[t].equals(valueList[position])) {
                    checkItem = t;
                    break;
                }
            }
        } else {
            checkItem = 4;
        }

        new AlertDialog.Builder(APNActivity.this)
                .setTitle(nameList[position])
                .setSingleChoiceItems(items, checkItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int j) {
                        valueList[position] = items[j];
                        Log.e(TAG, "valueList[" + position + "] = " + valueList[position]);

                        if (12 == position){//Auth TypeVal
                            Log.e(TAG, "Auth TypeVal: index = 12, j = " + j);
                            newApn.setAuthTypeVal(j);
                        } /*else if (14 == position){//APN Protocol
                            Log.e(TAG, "APN Protocol: index = 14, value = " + valueList[position]);
                            newApn.setApnProtocol(valueList[position]);
                        } else if (15 == position){//APN Roaming Protocol
                            Log.e(TAG, "APN Roaming Protocol: index = 15, value = " + valueList[position]);
                            newApn.setRoamingProtocol(valueList[position]);
                        } else if (18 == position){//MVNO type
                            Log.e(TAG, "MVNO type: index = 18, value = " + valueList[position]);
                            newApn.setMVNOType(valueList[position]);
                        }*/
                        myAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showMultiAlerDialog(final String[] items, final int position){
        for (int t = 0; t < items.length; t++){
            int lyl = valueList[position].indexOf(items[t]);
            Log.e(TAG, "onItemClick: lyl = " + lyl);

            if (valueList[position].indexOf(items[t]) != -1) {
                checkItems[t] = true;
            } else {
                checkItems[t] = false;
            }
        }

        //对HSPA　HSPAP特殊处理
        if ((valueList[position].indexOf("HSPAP") != -1) && (valueList[position].indexOf("HSPA,") == -1)){
            checkItems[2] = false;
            checkItems[3] = true;
        }

        new AlertDialog.Builder(APNActivity.this)
                .setTitle(nameList[position])
                .setMultiChoiceItems(items, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int j, boolean b) {
                        checkItems[j] = b;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        valueList[position] = "";
                        for (int k = 0; k < items.length; k++){
                            if (checkItems[k]){
                                if (valueList[position].indexOf(items[k]) == -1) {//无此字符串，则包含之
                                    if (!TextUtils.isEmpty(valueList[position])) {//要加的字符串之前有字符串，则要加,
                                        valueList[position] += ",";
                                    }
                                    valueList[position] += items[k];
                                }
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private void showEditAlertDialog(final int position){
        final EditText et = new EditText(APNActivity.this);
        et.setText(valueList[position]);
        if (null != valueList[position]) {
            et.setSelection(valueList[position].length());
        }
        new AlertDialog.Builder(APNActivity.this)
                .setView(et)
                .setTitle(nameList[position])
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        if (null != et.getText()) {
                            valueList[position] = et.getText().toString();
                            Log.e(TAG, "position = " + position);

                            myAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private void getValue() {
//        String[] nameList = {
//            "Name", "APN", "Proxy", "Port", "Username",
//            "Password", "Server", "MMSC", "MMS proxy", "MMS port",
//            "MCC", "MNC", "Authentication type", "APN type", "APN protocol",
//            "APN roaming protocol", "APN enable/disable", "Bearer", "MVNO type", "MVNO value"};
//        String[] valueList = new String[20]{
//            "", "", "", "", "",
//            "", "", "", "", "",
//            "460", "02", "", "", "IPv4",
//            "IPv4", "APN enabled", "Unspecified", "None", ""};

        valueList[0] = newApn.getName();
        valueList[1] = newApn.getApn();
        valueList[2] = newApn.getProxy();
        valueList[3] = newApn.getPort();
        valueList[4] = newApn.getUser();

        valueList[5] = newApn.getPassword();
        valueList[6] = newApn.getServer();
        valueList[7] = newApn.getMmsc();
        valueList[8] = newApn.getMmsproxy();
        valueList[9] = newApn.getMmsport();

        valueList[10] = newApn.getMcc();
        valueList[11] = newApn.getMnc();
        int value = newApn.getAuthTypeVal();
        if ((0 <= value) && (value < itemsAuthTypeVal.length)){
            valueList[12] = itemsAuthTypeVal[value];
        } else {
            valueList[12] = "";
        }
        valueList[13] = newApn.getApnType();
        valueList[14] = newApn.getApnProtocol();

        valueList[15] = newApn.getRoamingProtocol();
        valueList[16] = "APN enabled";
        String bearerVal = newApn.getBearerVal();
        if (!TextUtils.isEmpty(bearerVal)){
            int val = Integer.parseInt(bearerVal);
            valueList[17] = itemsBearer[val];
        } else {
            valueList[17] = "Unspecified";
        }
        String str = newApn.getMVNOType();
        if (TextUtils.isEmpty(str)){
            valueList[18] = itemsMVNOType[0];
        } else {
            valueList[18] = str;
        }

        valueList[19] = "";
    }

    private void setValue() {
//        String[] nameList = {
//            "Name", "APN", "Proxy", "Port", "Username",
//            "Password", "Server", "MMSC", "MMS proxy", "MMS port",
//            "MCC", "MNC", "Authentication type", "APN type", "APN protocol",
//            "APN roaming protocol", "APN enable/disable", "Bearer", "MVNO type", "MVNO value"};
//        String[] valueList = new String[20]{
//            "", "", "", "", "",
//            "", "", "", "", "",
//            "460", "02", "", "", "IPv4",
//            "IPv4", "APN enabled", "Unspecified", "None", ""};

        newApn.setName(valueList[0]);
        newApn.setApn(valueList[1]);
        newApn.setProxy(valueList[2]);
        newApn.setPort(valueList[3]);
        newApn.setUser(valueList[4]);

        newApn.setPassword(valueList[5]);
        newApn.setServer(valueList[6]);
        newApn.setMmsc(valueList[7]);
        newApn.setMmsproxy(valueList[8]);
        newApn.setMmsport(valueList[9]);

        newApn.setMcc(valueList[10]);
        newApn.setMnc(valueList[11]);
        //12
        newApn.setApnType(valueList[13]);
        newApn.setApnProtocol(valueList[14]);

        newApn.setRoamingProtocol(valueList[15]);
        //16
        //17
        newApn.setMVNOType(valueList[18]);
        //19
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nameList.length;
        }

        @Override
        public Object getItem(int i) {
            return nameList[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (null == view){
                view = View.inflate(APNActivity.this, R.layout.apn_item, null);
                holder = new ViewHolder();
                holder.tv_item_name = view.findViewById(R.id.tv_item_name);
                holder.tv_item_value = view.findViewById(R.id.tv_item_value);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }


            holder.tv_item_name.setText(nameList[i]);
            holder.tv_item_value.setText(valueList[i]);
            return view;
        }

        @Override
        public boolean isEnabled(int position) {
            if (16 == position || 17 == position || 19 == position){
                return false;
            } else {
                return true;
            }
        }
    }

    class ViewHolder {
        TextView tv_item_name;
        TextView tv_item_value;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setValue();
        Log.e(TAG, "will save, new apn = " + newApn);
        Utils.showLogs(ExecuteFunctionsService.TAG, "newApn = " + newApn);

        Intent silenterService = new Intent(this, ExecuteFunctionsService.class);
        silenterService.setAction(EnumAction.APN_UPDATE.getAction());
        Bundle bundle = new Bundle();
        bundle.putParcelable("new_apn", newApn);
        silenterService.putExtras(bundle);
        silenterService.putExtra("force",false);
        startService(silenterService);

        Log.e(TAG, "newApn = " + newApn);


    }
}
