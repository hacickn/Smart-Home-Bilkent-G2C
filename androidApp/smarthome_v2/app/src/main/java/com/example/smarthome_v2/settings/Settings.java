package com.example.smarthome_v2.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smarthome_v2.ProfileCard;
import com.example.smarthome_v2.R;
import com.example.smarthome_v2.SettingsHelper;

public class Settings extends AppCompatActivity {

    //properties
    private ImageButton homeSet, appSet,profile,helpButton;
    private Bundle bundle;
    private int themeNumber,textNo;
    private TextView app,home,top;
    private ConstraintLayout setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //initialization
        homeSet = findViewById(R.id.homeSettings);
        appSet = findViewById(R.id.appSettings);
        profile = findViewById(R.id.profilebutton);
        helpButton = findViewById(R.id.settings_help_button);
        setting = findViewById(R.id.settings);
        app = findViewById(R.id.text_appset);
        home = findViewById(R.id.text_hset);
        top = findViewById(R.id.text_set);



        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
            textNo = bundle.getInt("text");
        }

        //theme choosing
        if(themeNumber == 1)
        {
            setting.setBackgroundResource(R.drawable.gradient_bluenight);
            top.setTextColor(Color.BLUE);
            app.setTextColor(Color.BLUE);
            home.setTextColor(Color.BLUE);
        }

        if(themeNumber == 2)
        {
            setting.setBackgroundResource(R.drawable.gradient_alien);
            top.setTextColor(Color.GREEN);
            app.setTextColor(Color.GREEN);
            home.setTextColor(Color.GREEN);
        }

        if(themeNumber == 3)
        {
            setting.setBackgroundResource(R.drawable.backgroundwood);
            top.setTextColor(Color.WHITE);
            app.setTextColor(Color.WHITE);
            home.setTextColor(Color.WHITE);
        }

        //events
        homeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeSettings.class);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });
        appSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AppSettings.class);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProfileCard.class);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SettingsHelper.class);
                i.putExtra("theme", themeNumber);
                i.putExtra("text", textNo);
                startActivity(i);
            }
        });

    }
}
