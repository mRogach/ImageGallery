package com.example.user.imagegallery;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 03.02.2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, String> {

    private String message = null;
    private String imageUrl;
    private MyCallBack myCallBack;

    public DownloadImageTask(MyCallBack myCallBack) {
    this.myCallBack = myCallBack;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
    }

    protected String doInBackground(String... urls) {

        String urldisplay = urls[0];
        InputStream iStream = null;
        try {
            URL url = new URL(urldisplay);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(iStream));
            message = in.readLine();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }


    @Override
    protected void onPostExecute(String  result) {
        super.onPostExecute(result);
        imageUrl = result;
        myCallBack.callBack(imageUrl);
    }

}