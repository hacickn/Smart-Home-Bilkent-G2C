package com.example.smarthome_v2.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.popup.aquaPop;
import com.example.smarthome_v2.popup.elecPop;
import com.example.smarthome_v2.popup.gasPop;
import com.example.smarthome_v2.popup.ghPop;

public class HomeSettings extends AppCompatActivity {

    private ImageButton electricity,aquarium,gas,greenhouse;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_settings);
        //declaring image buttons
        electricity = findViewById(R.id.imageButton15);
        aquarium = findViewById(R.id.imageButton16);
        gas = findViewById(R.id.imageButton19);
        greenhouse = findViewById(R.id.imageButton17);

        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSettings.this, elecPop.class));
            }
        });

        aquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSettings.this, aquaPop.class));
            }
        });

        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSettings.this, gasPop.class));
            }
        });

        greenhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSettings.this, ghPop.class));
            }
        });
    }
}
