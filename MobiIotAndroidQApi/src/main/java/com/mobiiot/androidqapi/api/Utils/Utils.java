package com.mobiiot.androidqapi.api.Utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Utils {

    public static boolean isGMS(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo("com.android.vending", 0).enabled;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void log(String title1,String title2){
        Log.e(title1,title2);
        writeFile("/sdcard/Download/",title1+" | "+title2);
    }


    public static void writeFile(String path,String data){
        File file = new File(path+"Log.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.append(data+"\n");
            writer.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
