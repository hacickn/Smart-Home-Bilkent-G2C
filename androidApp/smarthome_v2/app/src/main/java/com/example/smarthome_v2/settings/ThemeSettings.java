package com.example.smarthome_v2.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;

public class ThemeSettings extends Activity {
    int themeNo;
    ImageButton blueNight,mainTheme,alien,wood,space;
    Intent thm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_settings_screen);

        //initialization
        thm = new Intent(getApplicationContext(), main_screen.class);


        mainTheme = findViewById(R.id.main_theme);
        blueNight = findViewById(R.id.blue_night);
        alien =findViewById(R.id.alien);
        wood = findViewById(R.id.wood);
        space = findViewById(R.id.space);

        //events
        mainTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 0;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

            }
        });

        blueNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 1;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

            }
        });

        alien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 2;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

            }
        });

        wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 3;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

            }
        });

        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 4;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

            }
        });
    }





}
