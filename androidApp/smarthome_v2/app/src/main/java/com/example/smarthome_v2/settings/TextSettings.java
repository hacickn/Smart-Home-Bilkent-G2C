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

public class TextSettings extends Activity {

    //properties
    private int textNo;
    private Button acme,aladin,amarante,annie,atomic,baumans,blackHans,cantora;
    private Intent txt;
    private Bundle bundle;
    private int text;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_settings_screen);

        //initialization
        txt = new Intent(getApplicationContext(), main_screen.class);
        acme = findViewById(R.id.text_acme);
        aladin = findViewById(R.id.text_aladin);
        amarante = findViewById(R.id.text_amarante);
        annie = findViewById(R.id.text_annie);
        atomic = findViewById(R.id.text_atomicage);
        baumans = findViewById(R.id.text_baumans);
        blackHans = findViewById(R.id.text_blackhans);
        cantora = findViewById(R.id.text_cantora);

        mAuth = FirebaseAuth.getInstance();
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


        bundle = getIntent().getExtras();
        if(bundle!=null) {
            text = bundle.getInt("text");
        }
        //choosing text type
        if(text == 1)
        {
            acme.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        if(text == 2)
        {
            aladin.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        if(text == 3)
        {
            amarante.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        if(text == 4)
        {
            annie.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        if(text == 5)
        {
            atomic.setBackgroundResource(R.drawable.buttonshapetwo);
        }
        if(text == 6)
        {
            baumans.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        if(text == 7)
        {
            blackHans.setBackgroundResource(R.drawable.buttonshapetwo);
        }


        if(text == 8)
        {
            cantora.setBackgroundResource(R.drawable.buttonshapetwo);
        }

        acme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 1;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "1");
                mDatabase.updateChildren(userMap);

            }
        });

        aladin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 2;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "2");
                mDatabase.updateChildren(userMap);

            }
        });

        amarante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 3;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "3");
                mDatabase.updateChildren(userMap);

            }
        });

        annie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 4;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "4");
                mDatabase.updateChildren(userMap);

            }
        });

        atomic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 5;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "5");
                mDatabase.updateChildren(userMap);

            }
        });

        baumans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 6;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "6");
                mDatabase.updateChildren(userMap);

            }
        });

        blackHans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 7;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "7");
                mDatabase.updateChildren(userMap);

            }
        });

        cantora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNo = 8;
                txt.putExtra("text", textNo);
                startActivity(txt);

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("txt", "8");
                mDatabase.updateChildren(userMap);

            }
        });


    }
}
