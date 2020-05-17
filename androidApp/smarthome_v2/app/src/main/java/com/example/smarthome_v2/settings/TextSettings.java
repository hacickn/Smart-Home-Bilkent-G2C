package com.example.smarthome_v2.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;

public class TextSettings extends Activity {
    int textNo;
    Button acme,aladin,amarante,annie,atomic,baumans,blackHans,cantora;
    Intent txt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_settings_screen);

        //initialization
        txt = new Intent(getApplicationContext(), main_screen.class);
        acme = findViewById(R.id.text_acme);
        aladin = findViewById(R.id.text_aladin);
        amarante = findViewById(R.id.text_amarante);
        annie = findViewById(R.id.text_annie);
        atomic = findViewById(R.id.text_atomicage);
        baumans = findViewById(R.id.text_baumans);
        blackHans = findViewById(R.id.text_blackhans);
        cantora = findViewById(R.id.text_cantora);

        acme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 1;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });

        aladin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 2;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });

        amarante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 3;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });

        annie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 4;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });

        atomic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 5;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });

        baumans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 6;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });

        blackHans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 7;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });

        cantora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 8;
                txt.putExtra("text", textNo);
                startActivity(txt);

            }
        });


    }
}
