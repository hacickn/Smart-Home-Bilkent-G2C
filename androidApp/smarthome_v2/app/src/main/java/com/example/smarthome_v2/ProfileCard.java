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
import java.util.HashMap;


/**
 * a Profile Card class
 *
 * @author Erengazi Mutlu , Nasuh Dinçer
 * @version 11.05.2020
 */
public class ProfileCard extends AppCompatActivity {

    //properties
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
        settingsName = (EditText) findViewById(R.id.profile_name);//initializing the name text editor
        DisplayMetrics dm = new DisplayMetrics();//related to pop-up
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //getting data
        bundle = getIntent().getExtras();
        if(bundle != null )
        {
            themeNumber = bundle.getInt("theme");
            textNo = bundle.getInt("text");
        }
        //pop-up initialize
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8),(int)(height*.8));

        mDisplayDate = findViewById(R.id.birthday);

        //We get the name from user to put it in the firebase
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

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Object yearObj = map.get("year");
                String newYear  =   String.valueOf(yearObj);

                Object dayObj = map.get("day");
                String newDay  =   String.valueOf(dayObj);

                Object monthObj = map.get("month");
                String newMonth  =   String.valueOf(monthObj);

                String date = newDay + "/" + newMonth + "/" + newYear;
                mDisplayDate.setText(date);
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
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/cantora_one.ttf");
            settingsName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        //this part is for the calendar object
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Calendar cal = Calendar.getInstance(); //we use the java Calendar from the java library

            //initializing the year, month and day
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            //we store the Calendar data in the firebase below
            String user_id = mAuth.getCurrentUser().getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("year", year + "");
            userMap.put("day", day + "");
            userMap.put("month", month + "");
            mDatabase.updateChildren(userMap);

            //Creating a date picker dialog
            DatePickerDialog dialog = new DatePickerDialog(ProfileCard.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,year,month,day);

                //while choosing the date, this method makes the background transparent
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        //this is the illustration of the date
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + " / " + month + " / " + year;
                mDisplayDate.setText(date);
            }
        };
    }
}
