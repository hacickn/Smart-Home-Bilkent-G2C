package com.example.smarthome_v2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class gas extends AppCompatActivity {
    public ImageButton exit;
    public ToggleButton gas_controller;
    private ImageView smokes;
    private ImageView wave_one;
    private ImageView wave_two;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_screen);



        exit=findViewById(R.id.exit_gas);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(gas.this,main_screen.class);
                startActivity(main);
            }
        });

        smokes = findViewById(R.id.smokes);
        gas_controller = (ToggleButton) findViewById(R.id.gas_control);
        boolean condition = Objects.requireNonNull(getIntent().getExtras()).getBoolean("gasCondition");
        gas_controller.setChecked(condition);

        wave_one = findViewById(R.id.wave_1);
        wave_two = findViewById(R.id.wave_two);
        if(!condition) {
            smokes.setVisibility(View.INVISIBLE);
            wave_one.setVisibility(View.INVISIBLE);
            wave_two.setVisibility(View.INVISIBLE);
        }

        gas_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON durumunda yapılacaklar
                    smokes.setVisibility(View.VISIBLE);
                    wave_one.setVisibility(View.VISIBLE);
                    wave_two.setVisibility(View.VISIBLE);
                } else {
                    // OFF durumunda yapılacaklar
                    smokes.setVisibility(View.INVISIBLE);
                    wave_one.setVisibility(View.INVISIBLE);
                    wave_two.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

}
