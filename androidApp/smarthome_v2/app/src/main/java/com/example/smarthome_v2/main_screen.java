package com.example.smarthome_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;

public class main_screen extends AppCompatActivity {

    private ToggleButton electricity_control, water_control,gas_control,gardenLight_control,
            aquarium_control,greenhouse_control;
    private boolean gasOnOff, elecOnOff, waterOnOff,gardenLightOnOff;
    private ImageButton weather, settings, elec, water,gardenLight,gas,aquarium,greenHouse,
            graphics, smartHomeDoor,helpbutton;
    private FirebaseAuth mAuth;
    private int themeNumber,textNo;
    private ConstraintLayout main;
    private ImageView menu;
    private Bundle bundle;
    private boolean gasCheck,gardenCheck,electricityCheck,waterCheck;
    private DatabaseReference mDatabase;


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
        greenhouse_control=  findViewById(R.id.greenhouse_control);
        smartHomeDoor = findViewById(R.id.smarthome_door);
        helpbutton = findViewById(R.id.helper_button);

        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null) {
            // themeNumber = bundle.getInt("theme");
            textNo = bundle.getInt("text");
            gasCheck = bundle.getBoolean("gas");
            gardenCheck = bundle.getBoolean("garden");
            electricityCheck = bundle.getBoolean("electricity");
            waterCheck = bundle.getBoolean("water");
        }

        //setting toggles


        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object isOnObj = map.get("aquarium");
                String isOn =  String.valueOf(isOnObj);
                if(isOn.equals("on"))
                    aquarium_control.setChecked(true);
                else
                    aquarium_control.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object isOnObj = map.get("gas");
                String isOn =  String.valueOf(isOnObj);
                if(isOn.equals("on"))
                    gas_control.setChecked(true);
                else
                    gas_control.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object isOnObj = map.get("water");
                String isOn =  String.valueOf(isOnObj);
                if(isOn.equals("on"))
                    water_control.setChecked(true);
                else
                    water_control.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object isOnObj = map.get("gardenLight");
                String isOn =  String.valueOf(isOnObj);
                if(isOn.equals("on"))
                    gardenLight_control.setChecked(true);
                else
                    gardenLight_control.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object isOnObj = map.get("electricity");
                String isOn =  String.valueOf(isOnObj);
                if(isOn.equals("on"))
                    electricity_control.setChecked(true);
                else
                    electricity_control.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object isOnObj = map.get("greenHouse");
                String isOn =  String.valueOf(isOnObj);
                if(isOn.equals("on"))
                    greenhouse_control.setChecked(true);
                else
                    greenhouse_control.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object thmObj = map.get("theme");
                String thmNum =  String.valueOf(thmObj);
                if(thmNum.equals("0"))
                {

                    themeNumber = 0;
                }

                else if (thmNum.equals("1"))
                {

                    themeNumber = 1;

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

                else if (thmNum.equals("2"))
                {

                    themeNumber = 2;

                    gas.setBackgroundResource(R.drawable.ic_alien_gas);
                    main.setBackgroundResource(R.drawable.backgroundalien);
                    gardenLight.setBackgroundResource(R.drawable.ic_alien_gardening);
                    weather.setBackgroundResource(R.drawable.ic_alien_weather);
                    settings.setBackgroundResource(R.drawable.ic_alien_optons);
                    elec.setBackgroundResource(R.drawable.ic_alien_electricity);
                    water.setBackgroundResource(R.drawable.ic_alien_tap);
                    aquarium.setBackgroundResource(R.drawable.ic_alien_aquarium);
                    greenHouse.setBackgroundResource(R.drawable.ic_alien_green_house);
                    graphics.setBackgroundResource(R.drawable.ic__alien_graphic_option);
                    menu.setBackgroundResource(R.drawable.ic_alien_menu);
                }

                else if (thmNum.equals("3"))
                {

                    themeNumber = 3;

                    gas.setBackgroundResource(R.drawable.ic_wood_gas);
                    main.setBackgroundResource(R.drawable.backgroundwood);
                    gardenLight.setBackgroundResource(R.drawable.ic_wood_gardening);
                    weather.setBackgroundResource(R.drawable.ic_wood_weather);
                    settings.setBackgroundResource(R.drawable.ic_wood_option);
                    elec.setBackgroundResource(R.drawable.ic_wood_electricity);
                    water.setBackgroundResource(R.drawable.ic_wood_tap);
                    aquarium.setBackgroundResource(R.drawable.ic_wood_aquarium);
                    greenHouse.setBackgroundResource(R.drawable.ic_wood_green_house);
                    graphics.setBackgroundResource(R.drawable.ic_wood_graphic_option);
                    menu.setBackgroundResource(R.drawable.ic_wood_menu);
                }

                else if (thmNum.equals("4"))
                {

                    themeNumber = 4;

                    gas.setBackgroundResource(R.drawable.ic_alien_gas);
                    main.setBackgroundResource(R.drawable.backgroundspace);
                    gardenLight.setBackgroundResource(R.drawable.ic_alien_gardening);
                    weather.setBackgroundResource(R.drawable.ic_alien_weather);
                    settings.setBackgroundResource(R.drawable.ic_alien_optons);
                    elec.setBackgroundResource(R.drawable.ic_alien_electricity);
                    water.setBackgroundResource(R.drawable.ic_alien_tap);
                    aquarium.setBackgroundResource(R.drawable.ic_alien_aquarium);
                    greenHouse.setBackgroundResource(R.drawable.ic_alien_green_house);
                    graphics.setBackgroundResource(R.drawable.ic__alien_graphic_option);
                    menu.setBackgroundResource(R.drawable.ic_alien_menu);
                }

                else
                {
                    themeNumber = 0;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object txtObj = map.get("txt");
                String txtNum =  String.valueOf(txtObj);
                if(txtNum.equals("0"))
                {
                    textNo = 0;
                }

                else if (txtNum.equals("1"))
                {
                    textNo = 1;
                }

                else if (txtNum.equals("2"))
                {
                    textNo = 2;
                }

                else if (txtNum.equals("3"))
                {
                    textNo = 3;
                }

                else if (txtNum.equals("4"))
                {
                    textNo = 4;
                }

                else if (txtNum.equals("5"))
                {
                    textNo = 5;
                }

                else if (txtNum.equals("6"))
                {
                    textNo = 6;
                }

                else if (txtNum.equals("7"))
                {
                    textNo = 7;
                }

                else if (txtNum.equals("8"))
                {
                    textNo = 8;
                }

                else
                {
                    textNo = 0;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        gas_control.setChecked(gasCheck);
        gardenLight_control.setChecked(gardenCheck);
        electricity_control.setChecked(electricityCheck);
        water_control.setChecked(waterCheck);

        //theme choosing
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
        if(themeNumber == 2){
            gas.setBackgroundResource(R.drawable.ic_alien_gas);
            main.setBackgroundResource(R.drawable.backgroundalien);
            gardenLight.setBackgroundResource(R.drawable.ic_alien_gardening);
            weather.setBackgroundResource(R.drawable.ic_alien_weather);
            settings.setBackgroundResource(R.drawable.ic_alien_optons);
            elec.setBackgroundResource(R.drawable.ic_alien_electricity);
            water.setBackgroundResource(R.drawable.ic_alien_tap);
            aquarium.setBackgroundResource(R.drawable.ic_alien_aquarium);
            greenHouse.setBackgroundResource(R.drawable.ic_alien_green_house);
            graphics.setBackgroundResource(R.drawable.ic__alien_graphic_option);
            menu.setBackgroundResource(R.drawable.ic_alien_menu);

        }

        if(themeNumber == 3){
            gas.setBackgroundResource(R.drawable.ic_wood_gas);
            main.setBackgroundResource(R.drawable.backgroundwood);
            gardenLight.setBackgroundResource(R.drawable.ic_wood_gardening);
            weather.setBackgroundResource(R.drawable.ic_wood_weather);
            settings.setBackgroundResource(R.drawable.ic_wood_option);
            elec.setBackgroundResource(R.drawable.ic_wood_electricity);
            water.setBackgroundResource(R.drawable.ic_wood_tap);
            aquarium.setBackgroundResource(R.drawable.ic_wood_aquarium);
            greenHouse.setBackgroundResource(R.drawable.ic_wood_green_house);
            graphics.setBackgroundResource(R.drawable.ic_wood_graphic_option);
            menu.setBackgroundResource(R.drawable.ic_wood_menu);

        }
        if(themeNumber == 4){
            gas.setBackgroundResource(R.drawable.ic_alien_gas);
            main.setBackgroundResource(R.drawable.backgroundspace);
            gardenLight.setBackgroundResource(R.drawable.ic_alien_gardening);

            weather.setBackgroundResource(R.drawable.ic_alien_weather);
            settings.setBackgroundResource(R.drawable.ic_alien_optons);
            elec.setBackgroundResource(R.drawable.ic_alien_electricity);
            water.setBackgroundResource(R.drawable.ic_alien_tap);
            aquarium.setBackgroundResource(R.drawable.ic_alien_aquarium);
            greenHouse.setBackgroundResource(R.drawable.ic_alien_green_house);
            graphics.setBackgroundResource(R.drawable.ic__alien_graphic_option);
            menu.setBackgroundResource(R.drawable.ic_alien_menu);

        }



        gardenLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gardenLightOnOff= gardenLight_control.isChecked();
                Intent i = new Intent(getApplicationContext(), GardenLight.class);
                i.putExtra("gardenCondition", gardenLightOnOff);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
                finish();
            }
        });


        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gasOnOff = gas_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Gas.class);

                i.putExtra("gasCondition", gasOnOff);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
                finish();
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
                Intent i = new Intent(getApplicationContext(), Settings.class);

                i.putExtra("elecCondition", elecOnOff);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });


        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                elecOnOff = electricity_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Electricity.class);

                i.putExtra("elecCondition", elecOnOff);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });


        water.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                waterOnOff = water_control.isChecked();
                Intent i = new Intent(getApplicationContext(), Water.class);

                i.putExtra("waterCondition", waterOnOff);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });


        aquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Aquarium.class);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });


        greenHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), GreenHouse.class);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });


        graphics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_screen.this, GraphicSettings.class);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });

        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHelp = new Intent(main_screen.this, HelpPop.class);
                startActivity(toHelp);
            }
        });

        electricity_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( electricity_control.isChecked() )
                {
                    userMap.put("electricity", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"ELECTRICITY IS OPENED" , FancyToast.LENGTH_SHORT,FancyToast.SUCCESS ,R.drawable.ic_alien_electricity,false).show();
                }
                else
                {
                    userMap.put("electricity", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"ELECTRICITY IS CLOSED" , FancyToast.LENGTH_SHORT,FancyToast.WARNING ,R.drawable.ic_alien_electricity,false).show();
                }
            }
        });

        gas_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( gas_control.isChecked() )
                {
                    userMap.put("gas", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"GAS IS OPENED" , FancyToast.LENGTH_SHORT,FancyToast.SUCCESS ,R.drawable.ic_alien_gas,false).show();
                }
                else
                {
                    userMap.put("gas", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"GAS IS CLOSED" , FancyToast.LENGTH_SHORT,FancyToast.WARNING ,R.drawable.ic_alien_gas,false).show();
                }
            }
        });

        greenhouse_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( greenhouse_control.isChecked() )
                {
                    userMap.put("greenHouse", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(), "GREENHOUSE IS OPENED", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.ic_alien_green_house, false).show();
                }
                else
                {
                    userMap.put("greenHouse", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"GREENHOUSE IS CLOSED" , FancyToast.LENGTH_SHORT,FancyToast.WARNING ,R.drawable.ic_alien_green_house,false).show();
                }
            }
        });

        gardenLight_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( gardenLight_control.isChecked() )
                {
                    userMap.put("gardenLight", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"GARDEN LIGHT IS OPENED" , FancyToast.LENGTH_SHORT,FancyToast.SUCCESS ,R.drawable.ic_alien_gardening,false).show();
                }
                else
                {
                    userMap.put("gardenLight", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"GARDEN LIGHT IS CLOSED" , FancyToast.LENGTH_SHORT,FancyToast.WARNING ,R.drawable.ic_alien_gardening,false).show();
                }
            }
        });

        water_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( water_control.isChecked() )
                {
                    userMap.put("water", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"WATER IS OPENED" , FancyToast.LENGTH_SHORT,FancyToast.SUCCESS ,R.drawable.ic_alien_water_exchange,false).show();
                }
                else
                {
                    userMap.put("water", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"WATER IS CLOSED" , FancyToast.LENGTH_SHORT,FancyToast.WARNING ,R.drawable.ic_alien_water_exchange,false).show();
                }
            }
        });

        aquarium_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( aquarium_control.isChecked() )
                {
                    userMap.put("aquarium", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(), "AQUARIUM IS OPENED", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.ic_alien_aquarium, false).show();
                }
                else
                {
                    userMap.put("aquarium", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"AQUARIUM IS CLOSED" , FancyToast.LENGTH_SHORT,FancyToast.WARNING ,R.drawable.ic_alien_aquarium,false).show();
                }
            }
        });

    }
}

