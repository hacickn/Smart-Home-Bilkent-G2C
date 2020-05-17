package com.example.smarthome_v2.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;

public class ThemeSettings extends Activity {
    int themeNo;
    ImageButton blueNight,mainTheme,alien,wood,space;
    Button main,blueNighttxt,alientxt,woodtxt,spacetxt;
    Intent thm;
    private Bundle bundle;
    int thmNo;

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
        main =findViewById(R.id.maintxt);
        blueNighttxt =findViewById(R.id.bluenighttxt);
        alientxt =findViewById(R.id.alientxt);
        woodtxt =findViewById(R.id.woodtxt);
        spacetxt =findViewById(R.id.spacetxt);

        bundle = getIntent().getExtras();
        if(bundle!=null) {
            thmNo = bundle.getInt("theme");
        }

        if(thmNo == 1)
        {
            blueNighttxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        else if(thmNo == 2)
        {
            alientxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }
        else if(thmNo == 3)
        {
            woodtxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }
        else if(thmNo == 4)
        {
            spacetxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }
        else
        {
            main.setBackgroundResource(R.drawable.buttonshapetwo);
        }


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
