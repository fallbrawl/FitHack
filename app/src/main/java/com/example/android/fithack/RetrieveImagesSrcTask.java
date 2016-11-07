package com.example.android.fithack;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nexus on 05.11.2016.
 */
public class RetrieveImagesSrcTask extends AsyncTask<String,String, ArrayList<String>> {

    private Document doc;
    private Elements newsImages;
    private ArrayList<String> parsedImg = new ArrayList<>();
    private Exception exception;

    protected ArrayList<String> doInBackground(String... urls) {
        try {
            doc = Jsoup.connect(urls[0]).get();
        } catch (IOException e) {
            this.exception = e;
            e.printStackTrace();
        }
        Elements content = doc.getElementsByTag("body");
        Element contents = content.get(0);
        newsImages = contents.getElementsByClass("entry-thumb");


            for (Element element: newsImages ){
                parsedImg.add(element.absUrl("src"));
            }

        return parsedImg;
    }

    protected void onPostExecute(ArrayList <String> results) {
        parsedImg = new ArrayList<>(results);
    }
}
