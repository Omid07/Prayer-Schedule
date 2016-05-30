package com.example.omid.prayertimes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by omid on 5/27/16.
 */
public class MyArrayAdapter extends ArrayAdapter<PrayerTime> {
    private ArrayList<PrayerTime> list;
    int resource;
    private LayoutInflater inflater;
    public MyArrayAdapter(Context context, int resource, ArrayList<PrayerTime> objects) {
        super(context, resource, objects);
        list = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("position: ",position+"");
        if(convertView == null){
            convertView = inflater.inflate(resource, null);
        }
        TextView temperature = (TextView)convertView.findViewById(R.id.temp_city);
        TextView date = (TextView)convertView.findViewById(R.id.date1);
        TextView fajr = (TextView)convertView.findViewById(R.id.fajr1);
        TextView dhuhr = (TextView)convertView.findViewById(R.id.dhuhr1);
        TextView asr = (TextView)convertView.findViewById(R.id.asr1);
        TextView maghrib = (TextView)convertView.findViewById(R.id.maghrib1);
        TextView isha = (TextView)convertView.findViewById(R.id.isha1);


        temperature.setText(list.get(position).getTemp());
        date.setText(list.get(position).getDateFor());
        fajr.setText(list.get(position).getFajr());
        dhuhr.setText(list.get(position).getDhuhr());
        asr.setText(list.get(position).getAsr());
        maghrib.setText(list.get(position).getMaghrib());
        isha.setText(list.get(position).getIsha());
//        Log.e("isha: ",list.get(position).getIsha());
        return convertView;
    }
}