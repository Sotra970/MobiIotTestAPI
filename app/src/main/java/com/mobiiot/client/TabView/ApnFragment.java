package com.mobiiot.client.TabView;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiiot.api.CsApn;
import com.mobiiot.client.Activity.APNAddActivity;
import com.mobiiot.client.Activity.ApnListActivity;
import com.mobiiot.client.Activity.Utils;
import com.mobiiot.client.Enum.EnumAction;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.R;
import com.mediatek.settings.service.APN;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApnFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ApnFragment lyl123";

    private static final int REQ_CODE = 1;
    private Button btGetApnList,btReset,
            btGetOperator,btn_add_apn,btGetApnList2,btn_add_apn2,
            btGetOperator2;
    private TextView tv_opertor, tv_opertor2;
    //private ProgressBar pb_get_operator;//replace by progressDialog
    private ProgressDialog progressDialog;
    private MyReceiver receiver;
    Typeface type;

    public ApnFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_apn,container,false);

        type = Typeface.createFromAsset(getActivity().getAssets(),"CaviarDreams.ttf");
        tv_opertor = (TextView) rootView.findViewById(R.id.tv_opertor);tv_opertor.setTypeface(type);
        tv_opertor2 = (TextView) rootView.findViewById(R.id.tv_opertor2);tv_opertor2.setTypeface(type);
        //pb_get_operator = (ProgressBar) rootView.findViewById(R.id.pb_get_operator);


        btGetApnList = rootView.findViewById(R.id.fragment_apn_btApnList);btGetApnList.setTypeface(type);
        btReset = rootView.findViewById(R.id.fragment_apn_btReset);btReset.setTypeface(type);
        btGetOperator = rootView.findViewById(R.id.fragment_apn_btOperator);btGetOperator.setTypeface(type);
        btGetOperator2 = rootView.findViewById(R.id.fragment_apn_btOperator2);btGetOperator2.setTypeface(type);
        btn_add_apn = (Button) rootView.findViewById(R.id.btn_add_apn);btn_add_apn.setTypeface(type);
        btn_add_apn2 = (Button) rootView.findViewById(R.id.btn_add_apn2);btn_add_apn2.setTypeface(type);
        btGetApnList2 = (Button) rootView.findViewById(R.id.fragment_apn_btApnList2);btGetApnList2.setTypeface(type);


        btGetApnList.setOnClickListener(this);
        btGetApnList2.setOnClickListener(this);
        btReset.setOnClickListener(this);
        btGetOperator.setOnClickListener(this);
        btGetOperator2.setOnClickListener(this);
        btn_add_apn.setOnClickListener(this);
        btn_add_apn2.setOnClickListener(this);

        if (null == receiver) {
            receiver = new MyReceiver();
            IntentFilter intentFilter = new IntentFilter("com.lyl.broadcast.ACTION");
            getContext().registerReceiver(receiver, intentFilter);
            Log.e(TAG,"onCreateView()--register Receiver");
        }

        return rootView;
    }

    private void getApnList(int slotId){
        Log.e(TAG, "will get apn list , slotId = " + slotId);
        ArrayList<APN> apnList = null;

            apnList = (ArrayList<APN>) CsApn.getApnList(slotId);
            if(null != apnList) {
                Log.e(TAG,apnList.toString() + " , apnList.size() = " + apnList.size());

                if (apnList.size() == 1 && "Emergency".equals(apnList.get(0).getName())){
                    Toast.makeText(getContext(), "Sim 1 is not exist or not operational!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), ApnListActivity.class);
                    intent.putParcelableArrayListExtra("apnlist", apnList);
                    startActivity(intent);
                }
            } else {
                Log.e(TAG,"apn list is null");
            }

    }

    private void addApn(int slotId){
        Log.e(TAG, "will add apn , slotId = " + slotId);

            String mccMnc = CsApn.getMccMnc(slotId);
            if (!TextUtils.isEmpty(mccMnc)){
                Intent intent = new Intent(getContext(), APNAddActivity.class);
                intent.putExtra("mccmnc", mccMnc);
                startActivityForResult(intent, REQ_CODE);
            } else {
                Toast.makeText(getContext(), "can not add apn!", Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    public void onClick(View view) {




        if (view == btGetApnList) {
            getApnList(0);
        } else if (view == btGetApnList2){
            getApnList(1);
        } else if (view == btReset) {
            Log.e(TAG, "will reset default");
            Utils.sendActionToServiceWithParams(getActivity(), EnumAction.APN_RESET_DEFAULT.getAction(),null);

        } else if (view == btGetOperator ) {
            Log.e(TAG, "will get operator");
                CsApn.getOperator(0);
                showProgressDialog();

        } else if (view == btGetOperator2){
              Log.e(TAG, "will get operator2");

                CsApn.getOperator(1);
                showProgressDialog();

        } else if (view == btn_add_apn){
            addApn(0);
        } else if (view == btn_add_apn2){
            addApn(1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == APNAddActivity.RES_CODE){

            Bundle bundle = data.getExtras();
            APN newApn = (APN)bundle.getParcelable(APNAddActivity.NEW_APN);
            Utils.showLogs(ExecuteFunctionsService.TAG, "newApn = " + newApn);
            /*Intent silenterService = new Intent(getActivity(), ExecuteFunctionsService.class);
            silenterService.setAction(EnumAction.APN_ADD.getAction());
            silenterService.putExtra("apn",newApn);
            silenterService.putExtra("force",false);*/
            //getActivity().startService(silenterService);

        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "MyReceiver onRceive()");
            if ("com.lyl.broadcast.ACTION" == intent.getAction()){

                ArrayList<String> operator_list = intent.getStringArrayListExtra("OPERATOR_LIST");
                Log.e(TAG, "operator_list = " + operator_list);
                //pb_get_operator.setVisibility(View.GONE);
                dismissProgressDialog();
                String optStr = "";
                for (String str : operator_list){
                    optStr += str;
                    optStr += '\n';
                }

                int slotid = intent.getIntExtra("SLOTID", -1);
                if (0 == slotid) {
                    tv_opertor.setText(optStr);
                } else if (1 == slotid){
                    tv_opertor2.setText(optStr);
                }

            }
        }
    }

    private void showProgressDialog(){
        Log.e(TAG, "showProgressDialog()");
        if (null == progressDialog){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("please wait...");
            progressDialog.setCanceledOnTouchOutside(false);//
        }
        progressDialog.show();
    }

    private void dismissProgressDialog(){
        if (null != progressDialog){
            progressDialog.dismiss();
        }
    }
    @Override
    public void onDestroy() {
        if (null != receiver) {
            Log.e(TAG,"onDestroy()--unregister Receiver");
            getContext().unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}
