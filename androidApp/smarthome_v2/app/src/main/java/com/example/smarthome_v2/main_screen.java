package com.example.smarthome_v2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.smarthome_v2.popup.WeatherPop;
import com.example.smarthome_v2.settings.GraphicSettings;
import com.example.smarthome_v2.settings.Settings;
import com.example.smarthome_v2.utilities.Aquarium;
import com.example.smarthome_v2.utilities.Electricity;
import com.example.smarthome_v2.utilities.GardenLight;
import com.example.smarthome_v2.utilities.Gas;
import com.example.smarthome_v2.utilities.GreenHouse;
import com.example.smarthome_v2.utilities.Water;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class main_screen extends AppCompatActivity {

    public ToggleButton electricity_control, water_control,gas_control,gardenLight_control,aquarium_control,greenHouse_control;
    public boolean gasOnOff, elecOnOff, waterOnOff;
    private ImageButton weather, settings, elec, water,gardenLight,gas,aquarium,greenHouse,graphics;
    private FirebaseAuth mAuth;
    int themeNumber;
    ConstraintLayout main;
    ImageView menu;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        //database
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null)
        {
            Intent loginIntent;
            loginIntent = new Intent(main_screen.this, MainActivity.class);
            startActivity(loginIntent);
            Toast.makeText(getApplicationContext(), "Please Log in", Toast.LENGTH_SHORT).show();
        }

        main = (ConstraintLayout) findViewById(R.id.mainmenu);
        //ImageButton initialize
        gardenLight = findViewById(R.id.garden_light);
        gas = findViewById(R.id.gas);
        weather = findViewById(R.id.weather);
        settings = findViewById(R.id.settings);
        elec = findViewById(R.id.electricity);
        water = findViewById(R.id.water_tap);
        aquarium = findViewById(R.id.aquarium);
        greenHouse = findViewById(R.id.green_house);
        graphics = findViewById(R.id.graphic_options);
        menu = findViewById(R.id.menu);

        gas_control = findViewById(R.id.gas_control);
        electricity_control = findViewById(R.id.electricity_control);
        water_control = findViewById(R.id.water_controller);
        aquarium_control = findViewById(R.id.aquarium_control);
        gardenLight_control= findViewById(R.id.gardenlight_control);
        greenHouse_control=  findViewById(R.id.greenhouse_control);

        //theme choosing
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
        }
        if(themeNumber == 1){
            gas.setBackgroundResource(R.drawable.ic_bluenight_gas);
            main.setBackgroundResource(R.drawable.backgroundbluenight);
            gardenLight.setBackgroundResource(R.drawable.ic_bluenight_gardening);

            weather.setBackgroundResource(R.drawable.ic_bluenight_weather);
            settings.setBackgroundResource(R.drawable.ic_bluenight_option);
            elec.setBackgroundResource(R.drawable.ic_bluenight_electricity);
            water.setBackgroundResource(R.drawable.ic_bluenight_tap);
            aquarium.setBackgroundResource(R.drawable.ic_bluenight_aquarium);
            greenHouse.setBackgroundResource(R.drawable.ic_bluenight_green_house);
            graphics.setBackgroundResource(R.drawable.ic__bluenight_graphic_option);
            menu.setBackgroundResource(R.drawable.ic_bluenight_menu);

        }



        gardenLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, GardenLight.class);
                startActivity(main);
            }
        });


        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, Gas.class);
                startActivity(main);

                gasOnOff = gas_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Gas.class);

                i.putExtra("gasCondition", gasOnOff);
                startActivity(i);
            }
        });




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


        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, Electricity.class);
                startActivity(main);

                elecOnOff = electricity_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Electricity.class);

                i.putExtra("elecCondition", elecOnOff);
                startActivity(i);
            }
        });


        water.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, Water.class);
                startActivity(main);

                waterOnOff = water_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Water.class);

                i.putExtra("waterCondition", waterOnOff);
                startActivity(i);
            }
        });


        aquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, Aquarium.class);
                startActivity(main);

            }
        });


        greenHouse_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, GreenHouse.class);
                startActivity(main);
            }
        });


        graphics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this, GraphicSettings.class);
                startActivity(main);
            }
        });
    }


}

