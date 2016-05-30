package com.example.omid.prayertimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class CompasActivity extends AppCompatActivity {

    ImageView qiblaImage;
    String imageUrl = "http://muslimsalat.com/qibla_compass/200/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compas);
        qiblaImage = (ImageView)findViewById(R.id.compass_image);
        String qiblaDirection = getIntent().getExtras().getString("direction");
        Toast.makeText(getApplicationContext(), qiblaDirection, Toast.LENGTH_LONG).show();
        Picasso.with(this).load(imageUrl+qiblaDirection+".png").into(qiblaImage);

    }
}
