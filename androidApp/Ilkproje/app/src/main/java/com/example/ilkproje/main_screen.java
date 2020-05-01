package com.example.ilkproje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class main_screen extends Activity {
    public ImageButton connection;
    public ImageButton gas;
    public ImageButton degree;
    public ImageButton sera;
    public ImageButton aquarium;
    public ImageButton electricity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        connection = findViewById(R.id.connection_settings);
        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,connection.class);
                startActivity(main);
            }
        });

        gas = findViewById(R.id.gas);
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,gas.class);
                startActivity(main);
            }
        });

        degree = findViewById(R.id.degree);
        degree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,degree.class);
                startActivity(main);
            }
        });

        sera = findViewById(R.id.sera);
        sera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,sera.class);
                startActivity(main);
            }
        });

        aquarium = findViewById(R.id.aquarium);
        aquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,aquarium.class);
                startActivity(main);
            }
        });

        electricity = findViewById(R.id.electricity);
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,electricity.class);
                startActivity(main);
            }
        });
    }
}
