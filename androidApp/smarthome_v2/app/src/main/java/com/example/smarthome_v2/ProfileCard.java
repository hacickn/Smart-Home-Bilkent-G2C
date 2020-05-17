package com.example.smarthome_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class ProfileCard extends AppCompatActivity {
    private static final String TAG = "ProfileCard";
    private TextView pName;
    private TextView mDisplayDate;
    private int themeNumber,textNo;
    private  Bundle bundle;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_card);

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

        pName = findViewById(R.id.profile_name);
        mDisplayDate = findViewById(R.id.birthday);

        //choosing text type
        if(textNo == 1)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
            pName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 2)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
            pName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 3)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/amarante.ttf");
            pName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 4)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/annie_use_your_telescope.ttf");
            pName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 5)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/atomic_age.ttf");
            pName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }
        if(textNo == 6)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/baumans.ttf");
            pName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }

        if(textNo == 7)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BlackHanSans-Regular.ttf");
            pName.setTypeface(typeface);
            mDisplayDate.setTypeface(typeface);
        }


        if(textNo == 8)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/cantora.ttf");
            pName.setTypeface(typeface);
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
