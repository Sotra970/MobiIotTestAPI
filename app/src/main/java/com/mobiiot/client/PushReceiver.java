package com.mobiiot.client;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

import com.mobiiot.client.Activity.Utils;

public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action;
        if (intent.getStringExtra("ACTION") != null) {
            action = intent.getStringExtra("ACTION");
            Utils.showLogs(ExecuteFunctionsService.TAG,"Notification - "+action);
            Intent silenterService = intent;
            intent.setClass(context,ExecuteFunctionsService.class);
            silenterService.setAction(action);
            context.startService(silenterService);
        }
    }
}