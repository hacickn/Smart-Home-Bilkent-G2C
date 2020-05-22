package com.example.smarthome_v2.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.popup.aquaPop;
import com.example.smarthome_v2.popup.elecPop;
import com.example.smarthome_v2.popup.gasPop;
import com.example.smarthome_v2.popup.ghPop;

/**
 * a Home Settings class
 *
 * @author Erengazi Mutlu
 * @version 10.05.2020
 */
public class HomeSettings extends AppCompatActivity {

    //properties
    private ImageButton electricity,aquarium,gas,greenhouse;
    private TextView elec,aqua,gass,ghouse,top;
    private Bundle bundle;
    private int themeNumber;
    private ConstraintLayout home;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_settings);

        //initialization
        electricity = findViewById(R.id.imageButton15);
        aquarium = findViewById(R.id.imageButton16);
        gas = findViewById(R.id.imageButton19);
        greenhouse = findViewById(R.id.imageButton17);
        home = findViewById(R.id.home_settings);
        top = findViewById(R.id.top);
        elec = findViewById(R.id.home_text_electricity);
        aqua = findViewById(R.id.home_text_aquarium);
        gass = findViewById(R.id.home_text_gas);
        ghouse = findViewById(R.id.home_text_greenhouse);

        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null)
        {
            themeNumber = bundle.getInt("theme");
        }

        if(themeNumber == 1)
        {
            home.setBackgroundResource(R.drawable.gradient_bluenight);
            electricity.setBackgroundResource(R.drawable.ic_bluenight_electricity);
            gas.setBackgroundResource(R.drawable.ic_bluenight_gas);
            greenhouse.setBackgroundResource(R.drawable.ic_bluenight_green_house);
            aquarium.setBackgroundResource(R.drawable.ic_bluenight_aquarium);
            top.setTextColor(Color.BLUE);
            elec.setTextColor(Color.BLUE);
            aqua.setTextColor(Color.BLUE);
            gass.setTextColor(Color.BLUE);
            ghouse.setTextColor(Color.BLUE);
        }

        if(themeNumber == 2)
        {
            home.setBackgroundResource(R.drawable.gradient_alien);
            electricity.setBackgroundResource(R.drawable.ic_alien_electricity);
            gas.setBackgroundResource(R.drawable.ic_alien_gas);
            greenhouse.setBackgroundResource(R.drawable.ic_alien_green_house);
            aquarium.setBackgroundResource(R.drawable.ic_alien_aquarium);
            top.setTextColor(Color.GREEN);
            elec.setTextColor(Color.GREEN);
            aqua.setTextColor(Color.GREEN);
            gass.setTextColor(Color.GREEN);
            ghouse.setTextColor(Color.GREEN);
        }

        if(themeNumber == 3)
        {
            home.setBackgroundResource(R.drawable.backgroundwood);
            electricity.setBackgroundResource(R.drawable.ic_wood_electricity);
            gas.setBackgroundResource(R.drawable.ic_wood_gas);
            greenhouse.setBackgroundResource(R.drawable.ic_wood_green_house);
            aquarium.setBackgroundResource(R.drawable.ic_wood_aquarium);
            top.setTextColor(Color.WHITE);
            elec.setTextColor(Color.WHITE);
            aqua.setTextColor(Color.WHITE);
            gass.setTextColor(Color.WHITE);
            ghouse.setTextColor(Color.WHITE);
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
