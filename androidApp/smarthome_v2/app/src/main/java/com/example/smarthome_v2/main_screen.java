package com.example.smarthome_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarthome_v2.popup.WeatherPop;
import com.example.smarthome_v2.settings.Settings;
import com.google.firebase.auth.FirebaseAuth;

public class main_screen extends AppCompatActivity {
    public ToggleButton gas_control;
    public ToggleButton electricity_control, water_control;
    public boolean gasOnOff, elecOnOff, waterOnOff;
    private ImageButton weather, settings, elec, water,gardenLight;
    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null)
        {
            Intent loginIntent;
            loginIntent = new Intent(main_screen.this, MainActivity.class);
            startActivity(loginIntent);
            Toast.makeText(getApplicationContext(), "Please Log in", Toast.LENGTH_SHORT).show();
        }

        gardenLight = findViewById(R.id.garden_light);
        gardenLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, GardenLight.class);
                startActivity(main);
            }
        });

        ImageButton gas = findViewById(R.id.gas);
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, gas.class);
                startActivity(main);
                gas_control = findViewById(R.id.gas_control);
                gasOnOff = gas_control.isChecked();
                Intent i = new Intent(getApplicationContext(), gas.class);

                i.putExtra("gasCondition", gasOnOff);
                startActivity(i);
            }
        });
        //ImageButton initialize
        weather = findViewById(R.id.weather);
        settings = findViewById(R.id.settings);

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toWeather = new Intent(main_screen.this, WeatherPop.class);
                startActivity(toWeather);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettings = new Intent(main_screen.this, Settings.class);
                startActivity(toSettings);
            }
        });

        elec = findViewById(R.id.electricity);
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, Electricity.class);
                startActivity(main);
                electricity_control = findViewById(R.id.electricity_control);
                elecOnOff = electricity_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Electricity.class);

                i.putExtra("elecCondition", elecOnOff);
                startActivity(i);
            }
        });

        water = findViewById(R.id.water_tap);
        water.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, Water.class);
                startActivity(main);
                water_control = findViewById(R.id.water_controller);
                waterOnOff = water_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Water.class);

                i.putExtra("waterCondition", waterOnOff);
                startActivity(i);
            }
        });

        ImageButton aquarium = findViewById(R.id.aquarium);
        aquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, Aquarium.class);
                startActivity(main);

            }
        });

        ImageButton greenHouse_control = findViewById(R.id.green_house);
        greenHouse_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, GreenHouse.class);
                startActivity(main);


            }
        });
    }
}

