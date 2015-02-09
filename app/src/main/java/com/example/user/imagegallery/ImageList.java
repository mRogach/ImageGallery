package com.example.user.imagegallery;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by User on 09.02.2015.
 */
public class ImageList {

    private static ImageList list;
    private Context mContext;
    private ArrayList<Image> mImages;

    public ImageList(Context mContext) {
        this.mContext = mContext;
        mImages = new ArrayList<Image>();

    }

    public static ImageList get (Context context){
        if (list == null) {
            list = new ImageList(context.getApplicationContext());
        }
        return list;
    }
    public ArrayList<Image> getImages() {
        return mImages;
    }
    public void setImages(ArrayList<Image> images) {
        mImages = images;
    }
    public Image getImage(String url) {
        for (Image c : mImages) {
            if (c.getmUrl().equals(url))
                return c;
        }
        return null;
    }

}
