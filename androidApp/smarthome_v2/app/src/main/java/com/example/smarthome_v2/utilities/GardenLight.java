package com.example.smarthome_v2.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ToggleButton;

import com.example.smarthome_v2.R;

public class GardenLight extends AppCompatActivity {
    ToggleButton gardenLightController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_light);
        setContentView(R.layout.garden_light);

        gardenLightController = findViewById(R.id.garden_light_control);
    }
}
