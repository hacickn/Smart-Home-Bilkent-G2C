package com.example.smarthome_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class ProfileCard extends AppCompatActivity {
    private static final String TAG = "ProfileCard";
    private EditText settingsName;
    private TextView mDisplayDate;
    private int themeNumber,textNo;
    private  Bundle bundle;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_card);
        settingsName = (EditText) findViewById(R.id.profile_name);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //getting data
        bundle = getIntent().getExtras();
        if(bundle != null ){

            themeNumber = bundle.getInt("theme");
            textNo = bundle.getInt("text");

        }

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int)(height*.8));


        mDisplayDate = findViewById(R.id.birthday);

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_name = dataSnapshot.child("name").getValue().toString();

                settingsName.setText(user_name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //choosing text type
        if(textNo == 1)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 2)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 3)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/amarante.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 4)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/annie_use_your_telescope.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 5)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/atomic_age.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }
        if(textNo == 6)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/baumans.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 7)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BlackHanSans-Regular.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }


        if(textNo == 8)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/cantora.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ProfileCard.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }
}
