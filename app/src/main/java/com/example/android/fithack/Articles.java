package com.example.android.fithack;

import android.graphics.Bitmap;

/**
 * Created by nexus on 28.10.2016.
 */
public class Articles {

    Bitmap bitmap;
    String text = "";

    public Articles(Bitmap imageId, String text){
        this.bitmap = imageId;
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

}
