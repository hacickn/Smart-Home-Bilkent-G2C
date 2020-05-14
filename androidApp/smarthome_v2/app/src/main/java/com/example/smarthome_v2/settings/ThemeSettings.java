package com.example.smarthome_v2.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;
import com.example.smarthome_v2.utilities.Gas;

public class ThemeSettings extends Activity {
    int themeNo;
    ImageButton blueNight,mainTheme,alien;
    Intent thm,thmGas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_settings_screen);

        //initialization
        thm = new Intent(getApplicationContext(), main_screen.class);


        mainTheme = findViewById(R.id.main_theme);
        blueNight = findViewById(R.id.blue_night);
        alien =findViewById(R.id.alien);

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
    }





}
