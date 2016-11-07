package com.example.android.fithack;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nexus on 03.11.2016.
 */
public class RetrieveHeadersTask extends AsyncTask<String,String, ArrayList<String>>{
    private Document doc;
    private Elements newsHeadlines;
    private Elements newsImages;
    private ArrayList<String> parsedText = new ArrayList<>();
    private ArrayList<String> parsedImg = new ArrayList<>();
    private Exception exception;
    private ArrayList<Articles> articles;

        protected ArrayList<String> doInBackground(String... urls) {
            try {
                doc = Jsoup.connect(urls[0]).get();
            } catch (IOException e) {
                this.exception = e;
                e.printStackTrace();
            }
            Elements content = doc.getElementsByTag("body");
            Element contents = content.get(0);
            newsHeadlines = contents.getElementsByClass("entry-title");
            newsImages = contents.getElementsByClass("entry-thumb");


            for (Element element : newsHeadlines) {
                parsedText.add(element.text());
            }

            for (int i = 0; i < newsHeadlines.size(); i++){
                //articles.add(new Articles(newsHeadlines.get(i).text(),newsImages.get(i).text()));
            }

            return parsedText;
        }

        protected void onPostExecute(ArrayList <String> results) {
            parsedText = new ArrayList<>(results);
        }


    }

