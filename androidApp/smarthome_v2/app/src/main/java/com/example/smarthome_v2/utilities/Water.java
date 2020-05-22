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
import com.example.smarthome_v2.MainScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;
import pl.droidsonroids.gif.GifImageView;
import java.util.HashMap;

/**
 * a Water class
 *
 * @author Tarık Buğra Karali , Nasuh Dincer
 * @version 06.05.2020
 */
public class Water extends AppCompatActivity {

    //properties
    private ImageButton exit;
    private ToggleButton water_controller;
    private ImageView drop_one,drop_two,drop_three,water_wave,water;
    private Bundle bundle;
    private int themeNumber;
    private boolean condition,currentCondition;
    private Intent thm;
    private GifImageView waterGIF;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

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
        waterGIF = findViewById(R.id.waterGIF);
        mAuth = FirebaseAuth.getInstance();

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

            drop_one.setBackgroundResource(R.drawable.ic_bluenight_drop);
            drop_two.setBackgroundResource(R.drawable.ic_bluenight_drop);
            drop_three.setBackgroundResource(R.drawable.ic_bluenight_drop);
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

        if(themeNumber == 3){

            drop_one.setBackgroundResource(R.drawable.ic_wood_drop);
            drop_two.setBackgroundResource(R.drawable.ic_wood_drop);
            drop_three.setBackgroundResource(R.drawable.ic_wood_drop);
            water.setBackgroundResource(R.drawable.ic_wood_tap);
            water_wave.setBackgroundResource(R.drawable.ic_wood_wave);
            waterGIF.setImageResource(R.drawable.backgroundwood);
        }

        //events
        water_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on)
                {
                    // ON
                    drop_one.setVisibility(View.VISIBLE);
                    drop_two.setVisibility(View.VISIBLE);
                    drop_three.setVisibility(View.VISIBLE);
                    water_wave.setVisibility(View.VISIBLE);
                    waterGIF.setImageResource(R.drawable.water);

                    if(themeNumber ==3)
                    {
                        waterGIF.setImageResource(R.drawable.backgroundwood);
                    }
                }
                else
                {
                    // OFF
                    drop_one.setVisibility(View.INVISIBLE);
                    drop_two.setVisibility(View.INVISIBLE);
                    drop_three.setVisibility(View.INVISIBLE);
                    water_wave.setVisibility(View.INVISIBLE);
                    waterGIF.setImageResource(R.drawable.water_first);

                    if(themeNumber ==3)
                    {
                        waterGIF.setImageResource(R.drawable.backgroundwood);
                    }
                }


                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").
                        child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( water_controller.isChecked() )
                {
                    userMap.put("water", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"WATER IS OPENED" ,
                            FancyToast.LENGTH_SHORT,FancyToast.SUCCESS ,
                            R.drawable.ic_alien_water_exchange,false).show();
                }
                else
                {
                    userMap.put("water", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"WATER IS CLOSED" ,
                            FancyToast.LENGTH_SHORT,FancyToast.WARNING ,
                            R.drawable.ic_alien_water_exchange,false).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCondition = water_controller.isChecked();
                thm = new Intent(Water.this, MainScreen.class);
                thm.putExtra("theme",themeNumber);
                thm.putExtra("water",currentCondition);
                startActivity(thm);
            }
        });
    }
}
