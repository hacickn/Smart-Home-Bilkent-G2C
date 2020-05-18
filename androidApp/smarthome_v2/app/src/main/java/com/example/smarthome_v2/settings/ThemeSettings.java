package com.example.smarthome_v2.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ThemeSettings extends Activity {
    int themeNo;
    ImageButton blueNight,mainTheme,alien,wood,space;
    Button main,blueNighttxt,alientxt,woodtxt,spacetxt;
    Intent thm;
    private Bundle bundle;
    int thmNo;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_settings_screen);

        //initialization
        thm = new Intent(getApplicationContext(), main_screen.class);
        mAuth = FirebaseAuth.getInstance();
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


        mainTheme = findViewById(R.id.main_theme);
        blueNight = findViewById(R.id.blue_night);
        alien =findViewById(R.id.alien);
        wood = findViewById(R.id.wood);
        space = findViewById(R.id.space);
        main =findViewById(R.id.maintxt);
        blueNighttxt =findViewById(R.id.bluenighttxt);
        alientxt =findViewById(R.id.alientxt);
        woodtxt =findViewById(R.id.woodtxt);
        spacetxt =findViewById(R.id.spacetxt);

        bundle = getIntent().getExtras();
        if(bundle!=null) {
            thmNo = bundle.getInt("theme");
        }

        if(thmNo == 1)
        {
            blueNighttxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        else if(thmNo == 2)
        {
            alientxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }
        else if(thmNo == 3)
        {
            woodtxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }
        else if(thmNo == 4)
        {
            spacetxt.setBackgroundResource(R.drawable.buttonshapetwo);
        }
        else
        {
            main.setBackgroundResource(R.drawable.buttonshapetwo);
        }


        //events

        mainTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 0;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("theme", "0");
                mDatabase.updateChildren(userMap);

            }
        });

        blueNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 1;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("theme", "1");
                mDatabase.updateChildren(userMap);

            }
        });

        alien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 2;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("theme", "2");
                mDatabase.updateChildren(userMap);

            }
        });

        wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 3;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("theme", "3");
                mDatabase.updateChildren(userMap);

            }
        });

        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeNo = 4;
                thm.putExtra("theme", themeNo);
                startActivity(thm);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("theme", "4");
                mDatabase.updateChildren(userMap);

            }
        });
    }





}
