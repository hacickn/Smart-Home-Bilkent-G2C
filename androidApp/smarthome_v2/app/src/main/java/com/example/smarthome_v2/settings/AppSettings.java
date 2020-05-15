package com.example.smarthome_v2.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.popup.alarmPop;
import com.example.smarthome_v2.popup.blutPop;
import com.example.smarthome_v2.popup.notiPop;

public class AppSettings extends AppCompatActivity {

    private ImageButton alarm,blut,noti;
    Bundle bundle;
    int themeNumber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        //initialization
        alarm = findViewById(R.id.Emergency);
        blut = findViewById(R.id.Blt);
        noti = findViewById(R.id.Noti);

        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
        }


        //events
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), alarmPop.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);

            }
        });

        blut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), blutPop.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), notiPop.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });
    }
}
