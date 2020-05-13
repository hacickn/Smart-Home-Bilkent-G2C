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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        homeSet = findViewById(R.id.homeSettings);
        appSet = findViewById(R.id.appSettings);
        profile = findViewById(R.id.profilebutton);

        homeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(Settings.this,HomeSettings.class);
                startActivity(toHome);
            }
        });
        appSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toApp = new Intent(Settings.this,AppSettings.class);
                startActivity(toApp);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile = new Intent(Settings.this,ProfileCard.class);
                startActivity(toProfile);
            }
        });

    }
}
