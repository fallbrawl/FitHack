package com.example.android.fithack;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nexus on 28.10.2016.
 */
public class ArticlesAdapter extends ArrayAdapter<Articles> {

    private int colorId = 0;

    public ArticlesAdapter(Activity context, ArrayList<Articles> Article, int color) {
        super(context, 0, Article);
        this.colorId = colorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;

        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_item, parent, false);
        }

        // Get the {@link Article} object located at this position in the list
        Articles currentArticle = getItem(position);

        // Find the TextView in the article_item.xml layout with the ID version_name
        TextView articleTextView = (TextView) gridItemView.findViewById(R.id.article_item_text_row);

        // Get the miwok translation from the current Word object and
        // set this text on the name TextView
        articleTextView.setText(currentArticle.getText());


        // Setting background color
        // TODO:uncomment when fragments ready

//        LinearLayout texts = (LinearLayout) gridItemView.findViewById(R.id.gridList);
//        texts.setBackgroundColor(colorId);

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) gridItemView.findViewById(R.id.article_item_pic);

        // Get the image resource ID from the current Article object and
        // set the image to iconView

        iconView.setImageBitmap(currentArticle.getBitmap());
        iconView.setVisibility(View.VISIBLE);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return gridItemView;
    }
}
