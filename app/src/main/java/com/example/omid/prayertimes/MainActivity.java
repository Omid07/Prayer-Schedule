package com.example.omid.prayertimes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity  {

    ListView listView;
    private String url = "http://muslimsalat.com/dhaka.json";
    private HandleJSON obj;
    Button btnComp;
    DatabaseHelper myDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnComp = (Button)findViewById(R.id.btn_comp);
        listView = (ListView) findViewById(R.id.list);
        myDB = new DatabaseHelper(this);
        new JsonParsing().execute();
        btnComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrayerTime p = new PrayerTime();
//                Log.e("onclick",s+"sadasd");
                String qiblaDirection = obj.getquibla();
                Intent intent = new Intent(MainActivity.this, CompasActivity.class);
                intent.putExtra("direction",qiblaDirection);
                startActivity(intent);
            }
        });
    }

    private class JsonParsing extends AsyncTask<Void, Void, ArrayList<PrayerTime>>{
        @Override
        protected ArrayList<PrayerTime> doInBackground(Void... params) {
            obj = new HandleJSON(url);
            obj.fetchJSON();
            ArrayList<PrayerTime> prayerTimes = obj.getPrayerTime();
            Log.e("doInd: ",prayerTimes.size()+"");
            ArrayList<PrayerTime> prayerTimeArrayList = new ArrayList<>() ;
            for(int i=0; i<prayerTimes.size();i++) {
                PrayerTime prayerTime = prayerTimes.get(i);
                prayerTimeArrayList.add(prayerTime);
            }
            return prayerTimeArrayList;
        }

        protected void onPostExecute(ArrayList<PrayerTime> list){

            Log.e("list ", list.size() + " ");
            myDB.insertData(list);
            MyArrayAdapter adapter = new MyArrayAdapter(getApplicationContext(),R.layout.row,list);
            listView.setAdapter(adapter);
        }
    }
}