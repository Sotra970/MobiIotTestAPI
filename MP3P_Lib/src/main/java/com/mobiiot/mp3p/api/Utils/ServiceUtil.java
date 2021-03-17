package com.mobiiot.mp3p.api.Utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.mediatek.settings.service.CSAndoridGo;
import com.mediatek.settings.service.CSApiMPE;
import com.mediatek.settings.service.CsApiAndroidQ;

import java.util.List;

/**
 * Created by lyl on 18-6-13.
 */
public class ServiceUtil {

    private static final String TAG = "ServiceUtil";
    private static ServiceConnection connection;
    private static CsApiAndroidQ aidlInterfaceAndroidQ;
    private static CSAndoridGo aidlInterfaceAndroidGO;
    private static CSApiMPE aidlInterfaceAndroidGOMPE;

    public static void bindRemoteService(Context context){
        final Intent intent = new Intent();
        intent.setAction("com.mediatek.settings.MyService.action"); //若修改了清单文件，一定要重启手机！
        final Intent service = new Intent(createExplicitFromImplicitIntent(context,intent));
        if (null == connection) {
            Log.e(TAG, "创建conn并连接");
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e(TAG, "Connected!!! connection = " + connection); //iBinder里有Student对象


                    if (Build.VERSION.SDK_INT==29) {
                        Log.e(TAG, Build.MODEL);
                        aidlInterfaceAndroidQ = CsApiAndroidQ.Stub.asInterface(service);
                    } else if (Build.VERSION.SDK_INT==27) {
                        if(Build.MODEL.contains("MPE")){
                            Log.e(TAG, "MPE");
                            aidlInterfaceAndroidGOMPE = CSApiMPE.Stub.asInterface(service);
                        }else{
                            Log.e(TAG, "MP4 || MP3P");
                            aidlInterfaceAndroidGO = CSAndoridGo.Stub.asInterface(service);
                        }

                    }

                }
                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                }
            };
            context.bindService(service, connection, Context.BIND_AUTO_CREATE);
        } else {
            Log.e(TAG, "已经连接上了");
        }
    }

    public static void unbindRemoteService(Context context) {
        if (null != connection) {
            Log.e(TAG, "断开连接 connection = null");
            context.unbindService(connection);
            connection = null;
            aidlInterfaceAndroidQ = null;
        } else {
            Log.e(TAG, "已经断开了连接");
        }
    }

    public static CsApiAndroidQ getAidlInterfaceAndroidQ() {
        return aidlInterfaceAndroidQ;
    }

    public static CSAndoridGo getAidlInterfaceAndroidGo() {
        return aidlInterfaceAndroidGO;
    }

    public static CSApiMPE getAidlInterfaceAndroidGoMPE() {
        return aidlInterfaceAndroidGOMPE;
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