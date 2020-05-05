package com.example.smarthome_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {

    private ImageButton homeSet, appSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        homeSet = findViewById(R.id.homeSettings);
        appSet = findViewById(R.id.appSettings);

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

    }
}
