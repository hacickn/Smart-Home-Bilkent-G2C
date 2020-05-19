package com.example.smarthome_v2.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smarthome_v2.AppSettingsHelper;
import com.example.smarthome_v2.R;
import com.example.smarthome_v2.popup.alarmPop;
import com.example.smarthome_v2.popup.blutPop;
import com.example.smarthome_v2.popup.notiPop;

public class AppSettings extends AppCompatActivity {

    //properties
    private ImageButton alarm,blut,noti,appSettingsHelperButton;
    private Bundle bundle;
    private int themeNumber;
    private ConstraintLayout appSetting;
    private TextView alarmText , connection , notification ,top;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        //initialization
        alarm = findViewById(R.id.Emergency);
        blut = findViewById(R.id.Blt);
        noti = findViewById(R.id.Noti);
        appSettingsHelperButton = findViewById(R.id.app_settings_helperbutton);
        appSetting = findViewById(R.id.app_settings);
        alarmText = findViewById(R.id.text_alarm);
        connection = findViewById(R.id.text_connection);
        notification = findViewById(R.id.text_noti);
        top =  findViewById(R.id.text_appsetting);

        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
        }
        if(themeNumber == 1)
        {
            appSetting.setBackgroundResource(R.drawable.gradient_bluenight);
            alarmText.setTextColor(Color.BLUE);
            connection.setTextColor(Color.BLUE);
            notification.setTextColor(Color.BLUE);
            top.setTextColor(Color.BLUE);
        }

        if(themeNumber == 2)
        {
            appSetting.setBackgroundResource(R.drawable.gradient_alien);
            alarmText.setTextColor(Color.GREEN);
            connection.setTextColor(Color.GREEN);
            notification.setTextColor(Color.GREEN);
            top.setTextColor(Color.GREEN);
        }

        if(themeNumber == 3)
        {
            appSetting.setBackgroundResource(R.drawable.backgroundwood);
            alarmText.setTextColor(Color.WHITE);
            connection.setTextColor(Color.WHITE);
            notification.setTextColor(Color.WHITE);
            top.setTextColor(Color.WHITE);
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
        appSettingsHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AppSettingsHelper.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });
    }
}
