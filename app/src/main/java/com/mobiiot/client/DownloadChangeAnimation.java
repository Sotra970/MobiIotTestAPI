package com.mobiiot.client;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.mobiiot.api.CsOperation;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by fachati on 05/04/18.
 */

public class DownloadChangeAnimation extends AsyncTask<Object, String, String> {



    private Context context;
    String filePath;
    boolean isBoot;

    protected void onCancelled(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... f_url) {
        int count;

        try {

            String urlString=(String)f_url[0];
            isBoot=(boolean)f_url[1];

                Log.e("file to download", urlString);

                URL url = new URL(urlString);
                URLConnection conection = url.openConnection();
                conection.connect();

                int lenghtOfFile = conection.getContentLength();

                 filePath="/sdcard/bootanimation.zip";
                if(!isBoot)
                    filePath="/sdcard/shutanimation.zip";

                Log.e("TAG", "=========== Zip File Size "+lenghtOfFile);

                    InputStream input = new BufferedInputStream(url.openStream(),
                            8192);

                    OutputStream output = new FileOutputStream(filePath);

                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        if (isCancelled()) break;
                        total += count;
                        publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                        output.write(data, 0, count);
                    }

                    output.flush();
                    output.close();
                    input.close();


            } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }


    protected void onProgressUpdate(String... progress) {}

    @Override
    protected void onPostExecute(String file_url) {

        //if(!ifPackageExistInDevice(appInDevice,appToDownload.get(i)))
        if(isBoot)
            CsOperation.changeShutAnimation("/sdcard/bootanimation.zip","bootanimation.zip");
        else{
            CsOperation.changeShutAnimation("/sdcard/shutanimation.zip","shutanimation.zip");
        }

    }

}
