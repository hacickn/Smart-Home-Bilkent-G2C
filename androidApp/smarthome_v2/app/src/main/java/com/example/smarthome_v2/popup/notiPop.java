package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.smarthome_v2.R;

public class notiPop extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*1),(int)(height*1));
    }
}
