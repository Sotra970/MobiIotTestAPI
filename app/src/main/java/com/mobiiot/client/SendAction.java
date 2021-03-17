package com.mobiiot.client;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



public class SendAction extends AsyncTask<String, Void, String> {

    protected void onPreExecute(){}

    protected String doInBackground(String... arg0) {

        try {



            String imei=(String)arg0[0];
            String data=(String)arg0[1];

            String requestURL="http://toptech.ma/MobiIoT/fileManager.php?fileName="+imei+"&text="+data;
            Log.e("URL",requestURL);

            URL url = new URL(requestURL); // here is your URL path



            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 );
            conn.setConnectTimeout(15000 );
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    //Log.e("server",line);
                    sb.append(line);
                    break;
                }
                Log.e("server",sb.toString());
                in.close();
                return sb.toString();

            }
            else {
                Log.e("server",new String("false : "+responseCode));
                return new String("false : "+responseCode);
            }

        }
        catch(Exception e){
            Log.e("server",e.toString());
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {

    }
}

