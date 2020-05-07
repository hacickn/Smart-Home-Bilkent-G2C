package com.example.smarthome_v2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Water extends AppCompatActivity {
    public ImageButton exit;
    public ToggleButton water_controller;
    private ImageView drop_one;
    private ImageView drop_two;
    private ImageView drop_three;
    private ImageView water_wave;





    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_screen);



        exit=findViewById(R.id.exit_water);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(Water.this, main_screen.class);
                startActivity(main);
            }
        });


        water_controller = (ToggleButton) findViewById(R.id.water_control);
        boolean condition = Objects.requireNonNull(getIntent().getExtras()).getBoolean("waterCondition");
        water_controller.setChecked(condition);

        drop_one = findViewById(R.id.drop_1);
        drop_two = findViewById(R.id.drop_2);
        drop_three = findViewById(R.id.drop_3);
        water_wave = findViewById(R.id.water_wave);
        if(!condition) {
            drop_one.setVisibility(View.INVISIBLE);
            drop_two.setVisibility(View.INVISIBLE);
            drop_three.setVisibility(View.INVISIBLE);
            water_wave.setVisibility(View.INVISIBLE);
        }

        water_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON durumunda yapılacaklar
                    drop_one.setVisibility(View.VISIBLE);
                    drop_two.setVisibility(View.VISIBLE);
                    drop_three.setVisibility(View.VISIBLE);
                    water_wave.setVisibility(View.VISIBLE);
                } else {
                    // OFF durumunda yapılacaklar
                    drop_one.setVisibility(View.INVISIBLE);
                    drop_two.setVisibility(View.INVISIBLE);
                    drop_three.setVisibility(View.INVISIBLE);
                    water_wave.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
