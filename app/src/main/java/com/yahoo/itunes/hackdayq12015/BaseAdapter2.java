package com.yahoo.itunes.hackdayq12015;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseAdapter2 extends BaseAdapter {

    private Activity activity;
    // private ArrayList&lt;HashMap&lt;String, String&gt;&gt; data;
    private static ArrayList id;
    private static ArrayList score;
    private static LayoutInflater inflater = null;

    public BaseAdapter2(Activity a, ArrayList b, ArrayList c) {
        activity = a;
        id = b;
        score = c;


        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return id.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_view, null);

        TextView title2 = (TextView) vi.findViewById(R.id.txt_ttlsm_row); // title
        String song = id.get(position).toString();
        title2.setText(song);


        TextView title3 = (TextView) vi.findViewById(R.id.score); // title
        if(song.equals("No results")){
            title3.setText("");
        }else {
            Float scoreFloat = Float.parseFloat(score.get(position).toString()) * 100;

            String scoreA = String.format("%.2f", scoreFloat) + "%";
            title3.setText(scoreA);
        }
        return vi;

    }
}