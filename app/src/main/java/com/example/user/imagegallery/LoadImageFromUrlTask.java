package com.example.user.imagegallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by User on 09.02.2015.
 */
public class LoadImageFromUrlTask extends AsyncTask<String, Void, Bitmap> {

    private Context mContext;
    private ImageView imageView;
    private Bitmap bitmap = null;

    public LoadImageFromUrlTask(Context context, ImageView imageView) {
        this.imageView = imageView;
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        String image = strings[0];
        String newString = image.substring(image.lastIndexOf("/") + 1);
        try {
            URL wallpaperURL = new URL(image);
            URLConnection connection = wallpaperURL.openConnection();
            InputStream inputStream = new BufferedInputStream(wallpaperURL.openStream(), 10240);
            File cacheDir = getCacheFolder(mContext);
            File cacheFile = new File(cacheDir, newString);
            FileOutputStream outputStream = new FileOutputStream(cacheFile);

            byte buffer[] = new byte[1024];
            int dataSize;
            int loadedSize = 0;
            while ((dataSize = inputStream.read(buffer)) != -1) {
                loadedSize += dataSize;
                // publishProgress(loadedSize);
                outputStream.write(buffer, 0, dataSize);
            }

            outputStream.flush();
            outputStream.close();

            File cacheDirToLoad = getCacheFolder(mContext);
            File cacheFileToLoad = new File(cacheDirToLoad, newString);

            InputStream fileInputStream = new FileInputStream(cacheFileToLoad);
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = 3;
            bitmapOptions.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(fileInputStream, null, bitmapOptions);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        bitmap = result;
        imageView.setImageBitmap(bitmap);
    }


    public File getCacheFolder(Context context) {
        File cacheDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(Environment.getExternalStorageDirectory(), "cachefolder");
            if (!cacheDir.isDirectory()) {
                cacheDir.mkdirs();
            }
        }

        if (!cacheDir.isDirectory()) {
            cacheDir = context.getCacheDir(); //get system cache folder
        }

        return cacheDir;
    }
}
