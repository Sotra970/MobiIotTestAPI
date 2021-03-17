package com.mobiiot.client.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.mobiiot.api.CsDevice;
import com.mobiiot.client.ExecuteFunctionsService;
import com.mobiiot.client.SendAction;
import com.mobiiot.client.model.AppDetail;
import com.mobiiot.client.model.Param;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String tokenDevice;

    public static byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int ch;
        while ((ch = is.read(buffer)) != -1) {
            bytestream.write(buffer, 0, ch);
        }
        byte data[] = bytestream.toByteArray();
        bytestream.close();
        return data;
    }


    public static void sendActionToServiceWithParams(Context context, String action, ArrayList<Param> params){
        Intent silenterService = new Intent(context, ExecuteFunctionsService.class);
        silenterService.setAction(action);
        if(params!=null){
            for(int i=0;i<params.size();i++){
                Object value=params.get(i).getValue();
                String name=params.get(i).getName();
                if(value.getClass()==Integer.class)
                    silenterService.putExtra(name,(Integer) value);
                else if(value.getClass()==Boolean.class)
                    silenterService.putExtra(name,(Boolean) value);
                else if(value.getClass()==String.class){
                    silenterService.putExtra(name,(String)value);
                }


            }
        }
        context.startService(silenterService);
    }

    public static AppDetail isSystemPackage(AppDetail app, Context context){
        ArrayList<AppDetail> allSystemAppList=new ArrayList<>();
        allSystemAppList.add(new AppDetail("","com.android.email",null));//
        allSystemAppList.add(new AppDetail("","com.android.calculator2",null));
        allSystemAppList.add(new AppDetail("","com.android.gallery3d",null));
        allSystemAppList.add(new AppDetail("","com.android.calendar",null));
        allSystemAppList.add(new AppDetail("","com.android.browser",null));//
        allSystemAppList.add(new AppDetail("","com.mediatek.camera",null));
        allSystemAppList.add(new AppDetail("","com.android.contacts",null));
        allSystemAppList.add(new AppDetail("","com.android.deskclock",null));
        allSystemAppList.add(new AppDetail("","com.mediatek.filemanager",null));
        allSystemAppList.add(new AppDetail("","com.android.mms",null));
        allSystemAppList.add(new AppDetail("","com.android.dialer",null));
        allSystemAppList.add(new AppDetail("","com.yanzhenjie.zbar.sample",null));
        allSystemAppList.add(new AppDetail("","com.mobiiot.launcher",null));
        allSystemAppList.add(new AppDetail("","com.android.settings",null));
        allSystemAppList.add(new AppDetail("","com.android.inputmethod.latin",null));
        allSystemAppList.add(new AppDetail("","com.android.quicksearchbox",null));
        allSystemAppList.add(new AppDetail("","com.android.stk",null));

        for (int i=0;i<allSystemAppList.size();i++){
            if(allSystemAppList.get(i).getName().compareTo(app.getName())==0){
                allSystemAppList.get(i).setLabel(app.getLabel());
                return allSystemAppList.get(i);
            }
        }

        return null;
    }

    public static void showLogs(String text1, String text2){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        //String imei=CsDevice.getDeviceInformation().getSim1_imei();
        Log.e(text1,text2);
        /*new SendAction().execute(CsDevice.getDeviceInformation().getSim1_imei()+""
                ,currentDateandTime +"  "+text1+"  -  "+text2);*/
    }

    public static void setToken(String token){
        tokenDevice=token;
    }

    public static String showArray(int[] array){
        String stringArray="{";
        for (int i=0;i<array.length;i++){
            stringArray=stringArray+array[i]+", ";
        }
        stringArray=stringArray+"}";
        return  stringArray;
    }

    public static String getPathFromUri(Uri uri){

        File file = new File(uri.getPath());//create path from uri
        final String[] split = file.getPath().split(":");//split the path.
        return "/sdcard/"+split[1];
    }

    public static  String readFile(File file) {
        if (file.isFile()) {


            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int len = 0;
                while ((len = fis.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                return outputStream.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no file£¡");
        }
        return null;
    }


    public static List alertString(String s){
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(byte\\) (0x[0-9a-fA-F]{2})");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()) {
            String a  = matcher.group(1);
            list.add(a);
        }
        return list;
    }



    public static byte[] getBytes(List<String> list){
        byte []bb = new byte[list.size()];
        Log.d("qinyu222","size = "+list.size());
        for (int i = 0 ; i < list.size() ; i ++){
            int value = Integer.parseInt(list.get(i).substring(2),16);
            Log.d("qinyu222",value +"");
            bb[i] = (byte) value;
        }
        Log.d("qinyu",bb.toString());
        return bb;
    }


}
