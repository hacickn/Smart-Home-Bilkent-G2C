package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.smarthome_v2.R;


public class blutPop extends Activity {

    private Button search;
    private Bundle bundle;
    private int themeNumber;
    private ConstraintLayout connection;
    private TextView top;
    private ImageView blut;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*1),(int)(height*1));

        //initialization
        connection = findViewById(R.id.connection);
        top = findViewById(R.id.text_conn);
        search = findViewById(R.id.search);
        blut = findViewById(R.id.blut_connection);

        //getting data
        bundle = getIntent().getExtras();

        if (bundle != null) {
            themeNumber = bundle.getInt("theme");
        }

        //theme choosing
        if (themeNumber == 1) {
            connection.setBackgroundResource(R.drawable.gradient_bluenight);
            top.setTextColor(Color.BLUE);
        }

        if (themeNumber == 2) {
            connection.setBackgroundResource(R.drawable.gradient_alien);
            top.setTextColor(Color.GREEN);
        }

        if (themeNumber == 3) {
            connection.setBackgroundResource(R.drawable.backgroundwood);
            top.setTextColor(Color.WHITE);
        }





            search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
