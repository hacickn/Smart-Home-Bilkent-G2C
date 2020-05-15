package com.example.smarthome_v2.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smarthome_v2.ProfileCard;
import com.example.smarthome_v2.R;

public class Settings extends AppCompatActivity {

    private ImageButton homeSet, appSet,profile;
    Bundle bundle;
    int themeNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //initialization
        homeSet = findViewById(R.id.homeSettings);
        appSet = findViewById(R.id.appSettings);
        profile = findViewById(R.id.profilebutton);

        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
        }

        //events
        homeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeSettings.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });
        appSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AppSettings.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProfileCard.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });

    }
}
