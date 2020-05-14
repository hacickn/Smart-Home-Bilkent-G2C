package com.example.smarthome_v2.utilities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;

import java.util.Objects;
/**
 * a Water class
 *
 * @author Tarık Buğra Karali
 * @version 06.05.2020
 */
public class Water extends AppCompatActivity {
    public ImageButton exit;
    public ToggleButton water_controller;
    private ImageView drop_one,drop_two,drop_three,water_wave,water;
    Bundle bundle;
    int themeNumber;
    boolean condition,currentCondition;
    Intent thm;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_screen);

        //initialization
        exit=findViewById(R.id.exit_water);
        water_controller = (ToggleButton) findViewById(R.id.water_control);
        drop_one = findViewById(R.id.drop_1);
        drop_two = findViewById(R.id.drop_2);
        drop_three = findViewById(R.id.drop_3);
        water_wave = findViewById(R.id.water_wave);
        water = findViewById(R.id.tap);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
            condition = bundle.getBoolean("waterCondition");
        }

        //impementation of datas
        water_controller.setChecked(condition);

        if(!condition) {
            drop_one.setVisibility(View.INVISIBLE);
            drop_two.setVisibility(View.INVISIBLE);
            drop_three.setVisibility(View.INVISIBLE);
            water_wave.setVisibility(View.INVISIBLE);
        }

        //choosing theme
        if(themeNumber == 1){

            drop_one.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            drop_two.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            drop_three.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            water.setBackgroundResource(R.drawable.ic_bluenight_tap);
            water_wave.setBackgroundResource(R.drawable.ic_bluenight_wave);
        }

        if(themeNumber == 2){

            drop_one.setBackgroundResource(R.drawable.ic_alien_drop);
            drop_two.setBackgroundResource(R.drawable.ic_alien_drop);
            drop_three.setBackgroundResource(R.drawable.ic_alien_drop);
            water.setBackgroundResource(R.drawable.ic_alien_tap);
            water_wave.setBackgroundResource(R.drawable.ic_alien_wave);
        }

        //events
        water_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON
                    drop_one.setVisibility(View.VISIBLE);
                    drop_two.setVisibility(View.VISIBLE);
                    drop_three.setVisibility(View.VISIBLE);
                    water_wave.setVisibility(View.VISIBLE);
                } else {
                    // OFF
                    drop_one.setVisibility(View.INVISIBLE);
                    drop_two.setVisibility(View.INVISIBLE);
                    drop_three.setVisibility(View.INVISIBLE);
                    water_wave.setVisibility(View.INVISIBLE);
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCondition = water_controller.isChecked();
                thm = new Intent(Water.this, main_screen.class);
                thm.putExtra("theme",themeNumber);
                thm.putExtra("water",currentCondition);
                startActivity(thm);
            }
        });

    }
}
