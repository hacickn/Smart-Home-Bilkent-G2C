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

    //properties
    private ImageButton electricity,aquarium,gas,greenhouse;
    private Bundle bundle;
    private int themeNumber;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_settings);

        //initialization
        electricity = findViewById(R.id.imageButton15);
        aquarium = findViewById(R.id.imageButton16);
        gas = findViewById(R.id.imageButton19);
        greenhouse = findViewById(R.id.imageButton17);

        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
        }


        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), elecPop.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });

        aquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), aquaPop.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });

        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), gasPop.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });

        greenhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ghPop.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });
    }
}
