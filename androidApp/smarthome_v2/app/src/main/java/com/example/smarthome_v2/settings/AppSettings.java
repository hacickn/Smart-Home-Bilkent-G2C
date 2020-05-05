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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        alarm = findViewById(R.id.Emergency);
        blut = findViewById(R.id.Blt);
        noti = findViewById(R.id.Noti);

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppSettings.this, alarmPop.class));

            }
        });

        blut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppSettings.this, blutPop.class));
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppSettings.this, notiPop.class));
            }
        });
    }
}
