package com.example.smarthome_v2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import java.util.Objects;

public class Electricity extends AppCompatActivity {
    public ImageButton exit;
    public ToggleButton elec_controller;
    private ImageView light_one;
    private ImageView light_two;
    private ImageView light_three;





    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electricity_screen);



        exit=findViewById(R.id.exit_elec);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(Electricity.this,main_screen.class);
                startActivity(main);
            }
        });

        light_one = findViewById(R.id.light1);
        elec_controller = (ToggleButton) findViewById(R.id.elec_control);
        boolean condition = Objects.requireNonNull(getIntent().getExtras()).getBoolean("elecCondition");
        elec_controller.setChecked(condition);

        light_two = findViewById(R.id.light2);
        light_three = findViewById(R.id.light3);
        if(!condition) {
            light_one.setVisibility(View.INVISIBLE);
            light_two.setVisibility(View.INVISIBLE);
            light_three.setVisibility(View.INVISIBLE);
        }

        elec_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON durumunda yapılacaklar
                    light_one.setVisibility(View.VISIBLE);
                    light_two.setVisibility(View.VISIBLE);
                    light_three.setVisibility(View.VISIBLE);
                } else {
                    // OFF durumunda yapılacaklar
                    light_one.setVisibility(View.INVISIBLE);
                    light_two.setVisibility(View.INVISIBLE);
                    light_three.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
