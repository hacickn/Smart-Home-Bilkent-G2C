package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.smarthome_v2.R;

import org.w3c.dom.Text;

public class alarmPop extends Activity {
    private Bundle bundle;
    private int themeNumber;
    private ConstraintLayout alarm;
    private TextView top, siren, sirenTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 1), (int) (height * 1));

        //initialization
        alarm = findViewById(R.id.alarm);
        top = findViewById(R.id.text_emergancy);
        siren = findViewById(R.id.text_siren1);
        sirenTwo = findViewById(R.id.external_siren);

        //getting data
        bundle = getIntent().getExtras();

        if (bundle != null) {
            themeNumber = bundle.getInt("theme");
        }

        //theme choosing
        if (themeNumber == 1) {
            alarm.setBackgroundResource(R.drawable.gradient_bluenight);
            siren.setTextColor(Color.BLUE);
            sirenTwo.setTextColor(Color.BLUE);
            top.setTextColor(Color.BLUE);
        }

        if (themeNumber == 2) {
            alarm.setBackgroundResource(R.drawable.gradient_alien);
            siren.setTextColor(Color.GREEN);
            sirenTwo.setTextColor(Color.GREEN);
            top.setTextColor(Color.GREEN);
        }

        if (themeNumber == 3) {
            alarm.setBackgroundResource(R.drawable.backgroundwood);
            siren.setTextColor(Color.WHITE);
            sirenTwo.setTextColor(Color.WHITE);
            top.setTextColor(Color.WHITE);

        }
    }
}
