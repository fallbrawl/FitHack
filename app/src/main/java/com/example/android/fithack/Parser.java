package com.example.android.fithack;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nexus on 30.10.2016.
 */
public class Parser {

    private Document doc;
    private Elements newsHeadlines;
    private ArrayList<String> parsedText = new ArrayList<>();

    public Parser() {
        parseText();
        parseImage();
    }

    private void parseText() {
        try {
            doc = Jsoup.connect("http://fithacker.ru/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements content = doc.getElementsByTag("body");
        Element contents = content.get(0);
        newsHeadlines = contents.getElementsByClass("entry-title");

        for (Element wow : newsHeadlines) {
            parsedText.add(wow.text());
        }
    }

    private void parseImage() {

    }

    public ArrayList<String> getParsedText() {
        return parsedText;
    }


}
