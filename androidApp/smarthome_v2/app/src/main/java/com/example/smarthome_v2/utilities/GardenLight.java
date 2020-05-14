package com.example.smarthome_v2.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;

public class GardenLight extends AppCompatActivity {
    ToggleButton gardenLightController;
    Bundle bundle;
    int themeNumber;
    ImageView gardenLight;
    boolean gardenLightOnOff,cuurentCondition;
    ImageButton exit;
    Intent thm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_light);


        //initialization
        gardenLightController = findViewById(R.id.garden_light_control);
        gardenLight = findViewById(R.id.image_garden_light);
        exit = findViewById(R.id.exit_garden);

        //choosing theme
        bundle = getIntent().getExtras();

        //getting datas
        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
            gardenLightOnOff = bundle.getBoolean("gardenCondition");
        }

        //implementation of datas
        gardenLightController.setChecked(gardenLightOnOff);

        //choosing theme
        if(themeNumber == 1){

            gardenLight.setBackgroundResource(R.drawable.ic_bluenight_gardening);

        }

        //events
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thm = new Intent(GardenLight.this, main_screen.class);
                thm.putExtra("theme",themeNumber);
                thm.putExtra("garden",cuurentCondition);

                startActivity(thm);
            }
        });

    }
}
