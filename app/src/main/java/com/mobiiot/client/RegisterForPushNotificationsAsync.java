package com.mobiiot.client;

import android.content.Context;
import android.os.AsyncTask;

import com.mobiiot.client.Activity.Utils;

import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;


public class RegisterForPushNotificationsAsync extends AsyncTask<Object, Void, Exception> {
    protected Exception doInBackground(Object... params) {
        try {
            Context context=(Context)params[0];
            if (!Pushy.isRegistered(context)) {
                String deviceToken = Pushy.register(context);
                Utils.showLogs(ExecuteFunctionsService.TAG, "Pushy device token: " + deviceToken);
                Utils.setToken(deviceToken);
            }else{
                try {
                    Pushy.subscribe("France", context);
                    Utils.showLogs(ExecuteFunctionsService.TAG, "Pushy sub in topic : France");
                } catch (PushyException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception exc) {
            Utils.showLogs(ExecuteFunctionsService.TAG, exc.toString());
            return exc;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Exception exc) {
        // Failed?
        if (exc != null) {
            // Show error as toast message
            return;
        }

        // Succeeded, optionally do something to alert the user
    }
}