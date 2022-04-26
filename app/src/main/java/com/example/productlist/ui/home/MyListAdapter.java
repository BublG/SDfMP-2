package com.example.productlist.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.productlist.R;
import com.example.productlist.db.Fish;
import com.squareup.picasso.Picasso;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final Fish[] fish;

    public MyListAdapter(Activity context, Fish[] fish, String[] mainTitles) {
        super(context, R.layout.fish_list, mainTitles);
        this.context = context;
        this.fish = fish;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fish_list, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        TextView rightText = (TextView) rowView.findViewById(R.id.right_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        titleText.setText(fish[position].getSpeciesName());
        subtitleText.setText(fish[position].getScientificName());
        rightText.setText(fish[position].getCalories());
        Picasso.get().load(fish[position].getImageSrc()).into(imageView);
        return rowView;
    }
}
