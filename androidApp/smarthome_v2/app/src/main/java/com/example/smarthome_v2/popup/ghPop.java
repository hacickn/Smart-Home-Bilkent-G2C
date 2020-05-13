package com.example.smarthome_v2.popup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;
import com.example.smarthome_v2.utilities.GreenHouse;


public class ghPop extends Activity {
    public ImageButton exit;
    private ImageView greenHouse;
    private TextView temperature,water,humudity;
    private EditText hum,comingWater,temp;
    int themeNumber;
    Bundle bundle;
    Intent thm;
    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_house_screen);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int)(height*.8));
        //initialization
        greenHouse = findViewById(R.id.green_house_image);
        temperature = findViewById(R.id.Temperature);
        water = findViewById(R.id.Water);
        humudity = findViewById(R.id.Humudity);
        hum = findViewById(R.id.hum);
        comingWater = findViewById(R.id.coming_water);
        temp = findViewById(R.id.temp);
        exit = findViewById(R.id.exit_greenhouse);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
        }

        //choosing theme
        if(themeNumber == 1){

            greenHouse.setBackgroundResource(R.drawable.ic_bluenight_green_house);
            temperature.setTextColor(Color.BLUE);
            water.setTextColor(Color.BLUE);
            humudity.setTextColor(Color.BLUE);
            Drawable drawable = hum.getBackground(); // get current EditText drawable
            drawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            hum.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableTwo = comingWater.getBackground(); // get current EditText drawable
            drawableTwo.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            comingWater.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableThree = temp.getBackground(); // get current EditText drawable
            drawableThree.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            temp.setBackgroundDrawable(drawable); // set the new drawable to EditText

        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thm = new Intent(ghPop.this, main_screen.class);
                thm.putExtra("theme",themeNumber);
                startActivity(thm);
            }
        });

    }
}
