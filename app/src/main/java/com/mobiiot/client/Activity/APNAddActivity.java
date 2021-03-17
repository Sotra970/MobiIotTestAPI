package com.mobiiot.client.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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

public class APNAddActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "APNAddActivity";
    public static final int RES_CODE = 2;
    public static final String NEW_APN = "new_apn";
    private APN newApn;
    Typeface type;

    private String[] nameList = {
            "Name", "APN", "Proxy", "Port", "Username",
            "Password", "Server", "MMSC", "MMS proxy", "MMS port",
            "MCC", "MNC", "Authentication type", "APN type", "APN protocol",
            "APN roaming protocol", "APN enable/disable", "Bearer", "MVNO type", "MVNO value"};
    private String[] valueList = {
            "", "", "", "", "",
            "", "", "", "", "",
            "460", "02", "", "", "IPv4",
            "IPv4", "APN enabled", "Unspecified", "None", ""};

    private String[] itemsAuthTypeVal = {"None", "PAP", "CHAP", "PAP or CHAP"};
    private String[] itemsAPNProtocol = {"IPv4", "IPv6", "IPv4/IPv6"};
    private String[] itemsAPNRoamingProtocol = {
            "Unspecified", "LTE", "HSPA", "HSPAP", "HSUPA",
            "HSDPA", "UMTS", "GEGE", "GPRS", "eHRPD",
            "EVDO_B", "EVDO_A", "EVDO_0", "1XRTT", "IS95B",
            "IS95A", "GSM", "TD_SCDMA", "IWLAN"};
    private boolean[] checkItems = new boolean[itemsAPNRoamingProtocol.length];
    private String[] itemsMVNOType = {"None", "SPN", "IMSI", "GID", "PNN"};

    private ListView lv_add_apn;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apnadd);

        type = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
        Intent intent = getIntent();
        String mccmnc = intent.getStringExtra("mccmnc");
        if (!TextUtils.isEmpty(mccmnc)) {
            valueList[10] = mccmnc.substring(0, 3);
            valueList[11] = mccmnc.substring(3, 5);
            Log.e(TAG, "mcc = " + valueList[10] + ", mnc = " + valueList[11]);
        } else {
            Log.e(TAG, "mccmnc = " + mccmnc);
        }
        lv_add_apn = (ListView) findViewById(R.id.lv_add_apn);
        myAdapter = new MyAdapter();
        lv_add_apn.setAdapter(myAdapter);
        lv_add_apn.setDividerHeight(0);//去除分割线
        lv_add_apn.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save){
            Log.e(TAG, "select save button");
            //getView();
            int authTypeVal = 0;
            for (int i = 0; i < itemsAuthTypeVal.length; i++){
                if (valueList[12].equals(itemsAuthTypeVal[i])){
                    authTypeVal = i;
                    break;
                }
            }

            /*
                String key, String name, String apn, String proxy, String port,
                String user, String password, String server, String mmsc, String mmsproxy,
                String mmsport, String mcc, String mnc, int authTypeVal, String apnType,
                String apnProtocol, String roamingProtocol, int carrierEnabled, String bearerVal, String MVNOType,
                String MVNOValue, int userVisible
            */
            newApn = new APN("", valueList[0], valueList[1], valueList[2], valueList[3],
                    valueList[4], valueList[5], valueList[6], valueList[7], valueList[8],
                    valueList[9], valueList[10], valueList[11], authTypeVal, valueList[13],
                    valueList[14], valueList[15], 1, "0", valueList[18],
                    valueList[19], 1);
            Log.e(TAG, "newApn = " + newApn);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(NEW_APN, newApn);
            intent.putExtras(bundle);
            setResult(RES_CODE, intent);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        if (12 == i){
            showRadioAlertDialog(itemsAuthTypeVal, i);
        } else if (14 == i || 15 == i){//IPv4
            showRadioAlertDialog(itemsAPNProtocol, i);
        } else if (17 == i){//Unspecified
            showMultiAlerDialog(itemsAPNRoamingProtocol, i);
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

        new AlertDialog.Builder(APNAddActivity.this)
                .setTitle(nameList[position])
                .setSingleChoiceItems(items, checkItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int j) {
                        valueList[position] = items[j];
                        myAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showMultiAlerDialog(final String[] items, final int position){
        for (int t = 0; t < items.length; t++){
            int lyl = valueList[position].indexOf(items[t]);
            Log.e("lyl123", "onItemClick: lyl = " + lyl);

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

        new AlertDialog.Builder(APNAddActivity.this)
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
        final EditText et = new EditText(APNAddActivity.this);
        et.setText(valueList[position]);
        if (null != valueList[position]) {
            et.setSelection(valueList[position].length());
        }
        new AlertDialog.Builder(APNAddActivity.this)
                .setView(et)
                .setTitle(nameList[position])
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        if (null != et.getText()) {
                            valueList[position] = et.getText().toString();
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    class MyAdapter extends BaseAdapter{

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
                view = View.inflate(APNAddActivity.this, R.layout.apn_item, null);
                holder = new ViewHolder();
                holder.tv_item_name = view.findViewById(R.id.tv_item_name);holder.tv_item_name.setTypeface(type);
                holder.tv_item_value = view.findViewById(R.id.tv_item_value);holder.tv_item_value.setTypeface(type);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            /*if (16 == i || 17 == i ||  19 == i){
                holder.tv_item_name.setTextColor(getColor(R.color.APN));//Color.GRAY
            } else {
                holder.tv_item_name.setTextColor(getColor(R.color.APN2));
            }*/
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
        Log.e(TAG, "select save button");
        //getView();
        int authTypeVal = 0;
        for (int i = 0; i < itemsAuthTypeVal.length; i++){
            if (valueList[12].equals(itemsAuthTypeVal[i])){
                authTypeVal = i;
                break;
            }
        }

            /*
                String key, String name, String apn, String proxy, String port,
                String user, String password, String server, String mmsc, String mmsproxy,
                String mmsport, String mcc, String mnc, int authTypeVal, String apnType,
                String apnProtocol, String roamingProtocol, int carrierEnabled, String bearerVal, String MVNOType,
                String MVNOValue, int userVisible
            */
        newApn = new APN("", valueList[0], valueList[1], valueList[2], valueList[3],
                valueList[4], valueList[5], valueList[6], valueList[7], valueList[8],
                valueList[9], valueList[10], valueList[11], authTypeVal, valueList[13],
                valueList[14], valueList[15], 1, "0", valueList[18],
                valueList[19], 1);
        Log.e(TAG, "newApn = " + newApn);


        Intent silenterService = new Intent(this, ExecuteFunctionsService.class);
        silenterService.setAction(EnumAction.APN_ADD.getAction());
        Bundle bundle = new Bundle();
        bundle.putParcelable(NEW_APN, newApn);
        silenterService.putExtras(bundle);
        silenterService.putExtra("force",false);
        startService(silenterService);
        super.onBackPressed();
    }
}
