package com.example.smarthome_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * an Help Pop class
 *
 * @author Erengazi Mutlu , Tarık Buğra Karali
 * @version 11.05.2020
 */
public class HelpPop extends AppCompatActivity {
    Bundle bundle;
    int textNo,themeNumber;
    TextView graphic, garden,water,aqua,elec,gas,ghouse,opt,door,weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*1),(int)(height*1));
        //init
        graphic = findViewById(R.id.help_text_graphsettings);
        garden = findViewById(R.id.help_text_gardenlight);
        water = findViewById(R.id.help_text_water);
        aqua = findViewById(R.id.help_text_aquarium);
        elec = findViewById(R.id.help_text_electricity);
        gas = findViewById(R.id.help_text_gas);
        ghouse = findViewById(R.id.help_text_greenhouse);
        opt = findViewById(R.id.help_text_settings);
        door = findViewById(R.id.help_text_door);
        weather = findViewById(R.id.help_text_weather);

        //getting data
        bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
            textNo = bundle.getInt("text");
        }

        //text types
        if(textNo == 1)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);

        }

        if(textNo == 2)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);

        }

        if(textNo == 3)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/amarante.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);
        }

        if(textNo == 4) {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/annie_use_your_telescope.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);
        }

        if(textNo == 5)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/atomic_age.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);
        }

        if(textNo == 6)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/baumans.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);
        }

        if(textNo == 7)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BlackHanSans-Regular.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);
        }

        if(textNo == 8)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/cantora_one.ttf");
            graphic.setTypeface(typeface);
            garden.setTypeface(typeface);
            water.setTypeface(typeface);
            aqua.setTypeface(typeface);
            elec.setTypeface(typeface);
            gas.setTypeface(typeface);
            ghouse.setTypeface(typeface);
            opt.setTypeface(typeface);
            door.setTypeface(typeface);
            weather.setTypeface(typeface);
        }
    }
}
