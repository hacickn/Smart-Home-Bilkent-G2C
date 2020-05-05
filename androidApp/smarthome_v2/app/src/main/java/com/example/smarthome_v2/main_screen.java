package com.example.smarthome_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class main_screen extends AppCompatActivity {
    public ToggleButton gas_control;
    public ToggleButton electricity_control;
    public boolean gasOnOff;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        ImageButton connection_settings = findViewById(R.id.connection);
        connection_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,connection_settings.class);
                startActivity(main);
            }
        });

        ImageButton gas = findViewById(R.id.gas);
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(main_screen.this,gas.class);
                startActivity(main);
                gas_control = findViewById(R.id.gas_control);
                gasOnOff = gas_control.isChecked();
                Intent i = new Intent(getApplicationContext(), gas.class);

                i.putExtra("gasCondition",gasOnOff);
                startActivity(i);
            }
        });









    }
}