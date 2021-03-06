package com.example.smarthome_v2.popup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.smarthome_v2.R;
import com.example.smarthome_v2.MainScreen;

/**
 * a Green House Pop class
 *
 * @author Erengazi Mutlu
 * @version 11.05.2020
 */
public class ghPop extends Activity {

    //properties
    private ImageButton exit;
    private ImageView greenHouse;
    private TextView temperature,water,humudity;
    private EditText hum,comingWater,temp;
    private int themeNumber, textNo;
    private Bundle bundle;
    private Intent thm;

    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_house_screen);

        //initializing the pop-up as below
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*1),(int)(height*1));
        //initialization of the objects
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
        if(bundle!=null)
        {
            themeNumber = bundle.getInt("theme");
        }

        //choosing theme
        if(themeNumber == 1)
        {
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

        if(themeNumber == 2)
        {
            greenHouse.setBackgroundResource(R.drawable.ic_alien_green_house);
            temperature.setTextColor(Color.BLACK);
            water.setTextColor(Color.BLACK);
            humudity.setTextColor(Color.BLACK);
            Drawable drawable = hum.getBackground(); // get current EditText drawable
            drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            hum.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableTwo = comingWater.getBackground(); // get current EditText drawable
            drawableTwo.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            comingWater.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableThree = temp.getBackground(); // get current EditText drawable
            drawableThree.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            temp.setBackgroundDrawable(drawable); // set the new drawable to EditText
            //greenHouseGIF.setImageResource(R.drawable.electricity);
        }

        if(themeNumber == 3)
        {
            greenHouse.setBackgroundResource(R.drawable.ic_wood_green_house);
            temperature.setTextColor(Color.WHITE);
            water.setTextColor(Color.WHITE);
            humudity.setTextColor(Color.WHITE);
            Drawable drawable = hum.getBackground(); // get current EditText drawable
            drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            hum.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableTwo = comingWater.getBackground(); // get current EditText drawable
            drawableTwo.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            comingWater.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableThree = temp.getBackground(); // get current EditText drawable
            drawableThree.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            temp.setBackgroundDrawable(drawable); // set the new drawable to EditText
            //greenHouseGIF.setImageResource(R.drawable.backgroundwood);
        }

        //choosing text type
        if(textNo == 1)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        if(textNo == 2)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        if(textNo == 3)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/amarante.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        if(textNo == 4)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/annie_use_your_telescope.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        if(textNo == 5)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/atomic_age.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        if(textNo == 6)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/baumans.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        if(textNo == 7)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BlackHanSans-Regular.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        if(textNo == 8)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/cantora.ttf");
            temperature.setTypeface(typeface);
            water.setTypeface(typeface);
            humudity.setTypeface(typeface);
            hum.setTypeface(typeface);
            comingWater.setTypeface(typeface);
            temp.setTypeface(typeface);
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thm = new Intent(ghPop.this, MainScreen.class);
                thm.putExtra("theme",themeNumber);
                startActivity(thm);
            }
        });
    }
}
