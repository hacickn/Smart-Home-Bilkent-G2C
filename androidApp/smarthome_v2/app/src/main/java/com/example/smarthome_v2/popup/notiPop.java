package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.smarthome_v2.R;

/**
 * a Notification Pop class
 *
 * @author Erengazi Mutlu
 * @version 11.05.2020
 */
public class notiPop extends Activity {
    private Bundle bundle;
    private int themeNumber;
    private ConstraintLayout notifications;
    private TextView top, fire, gas, smoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_pop);

        //initializing the pop-up as below
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 1), (int) (height * 1));

        //initialization
        notifications = findViewById(R.id.notifications);
        top = findViewById(R.id.text_notif);
        fire = findViewById(R.id.fire_sensor);
        gas = findViewById(R.id.gas_sensor);
        smoke = findViewById(R.id.smoke_sensor);

        //getting data
        bundle = getIntent().getExtras();

        if (bundle != null) {
            themeNumber = bundle.getInt("theme");
        }

        //theme choosing
        if (themeNumber == 1)
        {
            notifications.setBackgroundResource(R.drawable.gradient_bluenight);
            top.setTextColor(Color.BLUE);
            fire.setTextColor(Color.BLUE);
            gas.setTextColor(Color.BLUE);
            smoke.setTextColor(Color.BLUE);
        }

        if (themeNumber == 2)
        {
            notifications.setBackgroundResource(R.drawable.gradient_alien);
            top.setTextColor(Color.GREEN);
            fire.setTextColor(Color.GREEN);
            gas.setTextColor(Color.GREEN);
            smoke.setTextColor(Color.GREEN);
        }

        if (themeNumber == 3)
        {
            notifications.setBackgroundResource(R.drawable.backgroundwood);
            top.setTextColor(Color.WHITE);
            fire.setTextColor(Color.WHITE);
            gas.setTextColor(Color.WHITE);
            smoke.setTextColor(Color.WHITE);
        }

    }
}

