package com.example.android.fithack;

/**
 * Created by nexus on 28.10.2016.
 */
public class Articles {

    int imageId = -1;
    String text = "";

    public Articles(int imageId, String text){
        this.imageId = imageId;
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public int getImageId(){
        return imageId;
    }

}
