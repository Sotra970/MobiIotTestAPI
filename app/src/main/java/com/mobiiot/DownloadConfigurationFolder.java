package com.mobiiot;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.mobiiot.api.CsPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class DownloadConfigurationFolder extends AsyncTask<Object, String, String> {


    private String TAG="GetServer";
    public static String urlServer="http://196.207.246.32/nomad/";

    private Context context;
    ArrayList<String> listApkToDownload;
    ArrayList<String> listApkToRemove;



    protected void onCancelled(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(Object... f_url) {
        try {
            listApkToDownload=new ArrayList<>();
            listApkToRemove=new ArrayList<>();
            context=(Context)f_url[0];
            urlServer=(String)f_url[1];
            Log.e(TAG, "=========== Start Download Secure file ... ");
            URL url2 = new URL(urlServer+"version.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url2.openStream()));
            String str;

            while ((str = in.readLine()) != null) {
                boolean booleanValue=false;
                if(str.contains("/")){
                    String packageName=str.split("/")[0];
                    //double versionName=Double.parseDouble(str.split("/")[1]);
                    int versionCode= Integer.parseInt(str.split("/")[1]);

                    Log.e(TAG, "packageName = "+packageName+" - versionCode : "+versionCode);
                    boolean addPackageForDownload = checkPackageInDevice(packageName,versionCode);
                    Log.e(TAG, "packageName2 = "+packageName+" - versionCode2 : "+versionCode);
                    boolean ifPackageExistInDevice = ifPackageExistInDevice(packageName);

                    Log.e(TAG,"addPackageForDownload ==>" +addPackageForDownload);
                    Log.e(TAG, "packageName3 = "+packageName+" - versionCode3 : "+versionCode);
                    Log.e(TAG,"ifPackageExistInDevice ==>" +ifPackageExistInDevice);

                    if(versionCode==-1){
                        listApkToRemove.add(packageName);
                    }else if(versionCode==0){
                        listApkToRemove.add(packageName);
                        listApkToDownload.add(packageName);
                    }else if(versionCode>0){
                        if(ifPackageExistInDevice==false || addPackageForDownload==true)
                            listApkToDownload.add(packageName);
                    }
                }
            }
            int count;

            Log.e(TAG, "=========== Start Download APKs ... ");
            for(int i=0;i<listApkToDownload.size();i++){
                Log.e(TAG, "Download : "+urlServer+listApkToDownload.get(i)+".apk");
                URL url = new URL((String)urlServer+listApkToDownload.get(i)+".apk");
                trustAllCertificates();
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                int lenghtOfFile = httpURLConnection.getContentLength();
                File file = new File( "/sdcard/" + listApkToDownload.get(i)+".apk");

                if(file.exists())
                    file.delete();

                if(!file.exists() || (file.exists() && file.length()!=lenghtOfFile)){
                    InputStream input = httpURLConnection.getInputStream();
                    OutputStream output = new FileOutputStream(file);
                    byte data[] = new byte[1024];
                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        if (isCancelled()) break;
                        total += count;
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
            }
        } catch (Exception e) {
            for(int i =0;i<listApkToRemove.size();i++){
                Log.e(TAG,listApkToRemove.get(i));
                CsPackage.removeApp(listApkToRemove.get(i));

            }
            Log.e(TAG, "=========== ERROR DOWNLOAD :"+e.getMessage());
        }
        return null;
    }


    protected void onProgressUpdate(String... progress) {}

    @Override
    protected void onPostExecute(String file_url) {
        Log.e(TAG, "========= start Install/update");

        for(int i =0;i<listApkToRemove.size();i++){
            Log.e(TAG,listApkToRemove.get(i));
            CsPackage.removeApp(listApkToRemove.get(i));

        }
        for(int i =0;i<listApkToDownload.size();i++){

            if(ifPackageExistInDevice(listApkToDownload.get(i))){
                Log.e(TAG, "update : "+listApkToDownload.get(i));
                CsPackage.updateApp("/sdcard/"+listApkToDownload.get(i)+".apk",listApkToDownload.get(i));
            } else{
                Log.e(TAG, "install : "+listApkToDownload.get(i));
                CsPackage.installApp("/sdcard/"+listApkToDownload.get(i)+".apk",listApkToDownload.get(i));
            }

        }


    }

    public boolean checkPackageInDevice(String appPackageName, int appCodeVersion){
        Log.e(TAG, "checkPackageInDevice package in server : "+appPackageName+" - "+appCodeVersion);
        List<PackageInfo> packList = context.getPackageManager().getInstalledPackages(0);
        for (int i=0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            if (  (packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                int codeVersion=packInfo.versionCode;

                String devicePackageName = packInfo.packageName;

                Log.e(TAG, "checkPackageInDevice package in device : "+devicePackageName+" - "+codeVersion);

                if(devicePackageName.equals(appPackageName)){
                    if(appCodeVersion>codeVersion)
                            return true;
                }
            }
        }
        return false;
    }

    public boolean ifPackageExistInDevice(String appPackageName){
        List<PackageInfo> packList = context.getPackageManager().getInstalledPackages(0);
        for (int i=0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            if (  (packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                int codeVersion=packInfo.versionCode;

                String devicePackageName = packInfo.packageName;
                Log.e(TAG, "ifPackageExistInDevice : package in device : "+devicePackageName+" - "+codeVersion);
                if(devicePackageName.equals(appPackageName)){
                   return true;
                }
            }
        }
        return false;
    }



    public void trustAllCertificates() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
            Log.e("ERROR",e.toString());
        }
    }



}
