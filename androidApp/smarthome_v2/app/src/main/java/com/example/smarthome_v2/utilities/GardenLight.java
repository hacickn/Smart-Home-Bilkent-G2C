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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

public class GardenLight extends AppCompatActivity {
    ToggleButton gardenLightController;
    Bundle bundle;
    int themeNumber;
    ImageView gardenLight;
    boolean gardenLightOnOff,cuurentCondition;
    ImageButton exit;
    Intent thm;
    private GifImageView gardenLightGIF;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_light);


        //initialization
        gardenLightController = findViewById(R.id.garden_light_control);
        gardenLight = findViewById(R.id.image_garden_light);
        exit = findViewById(R.id.exit_garden);
        gardenLightGIF = findViewById(R.id.gardenlightGIF);
        mAuth = FirebaseAuth.getInstance();




        //getting datas
        bundle = getIntent().getExtras();
        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
            gardenLightOnOff = bundle.getBoolean("gardenCondition");
        }

        //implementation of datas
        gardenLightController.setChecked(gardenLightOnOff);

        //choosing theme
        if(themeNumber == 1){

            gardenLight.setBackgroundResource(R.drawable.ic_bluenight_gardening);
            gardenLightGIF.setImageResource(R.drawable.bluenight_electricity_first);

        }

        if(themeNumber == 2){

            gardenLight.setBackgroundResource(R.drawable.ic_alien_gardening);

        }

        if(themeNumber == 3){

            gardenLight.setBackgroundResource(R.drawable.ic_wood_gardening);
            gardenLightGIF.setImageResource(R.drawable.backgroundwood);

        }

        //events
        gardenLightController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON
                    gardenLightGIF.setImageResource(R.drawable.gardenlight);
                    if(themeNumber == 2) {
                        gardenLightGIF.setImageResource(R.drawable.gardenlight);
                    }

                    if(themeNumber == 1) {
                        gardenLightGIF.setImageResource(R.drawable.bluenight_electricity);
                    }

                } else {
                    // OFF
                    gardenLightGIF.setImageResource(R.drawable.gardenlight_first);

                    if(themeNumber == 2) {
                        gardenLightGIF.setImageResource(R.drawable.gardenlight_first);
                    }

                    if(themeNumber == 1) {
                       gardenLightGIF.setImageResource(R.drawable.bluenight_electricity_first);
                    }
                }
                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( gardenLightController.isChecked() )
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
