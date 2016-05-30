package com.example.omid.prayertimes;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by omid on 5/27/16.
 */
public class HandleJSON {

    private String urlString = null;

    ArrayList<PrayerTime> prayerTimes = new ArrayList<>();

    private String direction;

    public HandleJSON(String url){
        this.urlString = url;
    }

    public ArrayList<PrayerTime> fetchJSON(){
        try{
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream stream = conn.getInputStream();
            String data = convertStreamToString(stream);
            Log.e("data: ",data);
            stream.close();
            return readAndParseJSON(data);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String convertStreamToString(java.io.InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public  ArrayList<PrayerTime> readAndParseJSON(String in) {
        Log.e("reader",in+"");
        try {
            Log.e("try","asche");
//            JSONArray jsonArray = new JSONArray(in);
            JSONObject reader = new JSONObject(in);
            Log.e("size", " "+reader.length());
//            for (int i = 0; i < reader.length(); i++) {
                PrayerTime p = new PrayerTime();
                JSONObject waether = reader.getJSONObject("today_weather");
                p.setTemp(waether.getString("temperature"));
            this.direction = reader.getString("qibla_direction");
            p.setDirection(reader.getString("qibla_direction"));
//            Log.e("direction", name+" kachumachu");
//            JSONObject parentObject = new JSONObject("items");
                JSONArray jsonArray = reader.getJSONArray("items");
                JSONObject item = jsonArray.getJSONObject(0);
//            for (int i =0; i<jsonArray.length(); i++){
//                JSONObject item = jsonArray.getJSONObject(i);
                p.setDate(item.getString("date_for"));
                p.setFajr(item.getString("fajr"));
                p.setDhuhr(item.getString("dhuhr"));
                p.setAsr(item.getString("asr"));
                p.setMaghrib(item.getString("maghrib"));
                p.setIsha(item.getString("isha"));
                prayerTimes.add(p);
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.e("again", prayerTimes.size() + " ");
        return prayerTimes;
    }

    public ArrayList getPrayerTime() {
//        Log.e("getPrayerTime ",prayerTimes.size()+" ");
        return prayerTimes;
    }
    public String getquibla(){
        return direction;
    }
}
