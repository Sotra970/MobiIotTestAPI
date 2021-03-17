package com.mobiiot.mp3p.api.Utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;

import com.mediatek.engineermode.atservice.IATService;

import java.util.List;

/**
 * Created by dengjifu on 18-7-20.
 */

public class ATServiceUtils {
    private static final String TAG = "ATServiceUtils";
    public static IATService atService;
    private static ServiceConnection conn;

    public static void bindService(Context context){
        Intent intent = new Intent("com.sagereal.atcmd.service.ACTION");
        Intent service = new Intent(createExplicitFromImplicitIntent(context, intent));
        if (null == conn){
            Log.e(TAG, "创建conn并连接");
            conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e(TAG, "Connected!!! conn = " + conn); //iBinder里有Student对象
                    atService = IATService.Stub.asInterface(service);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
            context.bindService(service, conn, Context.BIND_AUTO_CREATE);
        } else {
            Log.e(TAG, "已经连接上了");
        }
    }

    public static void unBindService(Context context){
        if (null != conn){
            Log.e(TAG, "断开连接 connection = null");
            context.unbindService(conn);
            conn = null;
            atService = null;
        } else {
            Log.e(TAG, "已经断开了连接");
        }
    }

    public static IATService getATService(){
        return atService;
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
