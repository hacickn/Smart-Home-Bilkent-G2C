package com.example.smarthome_v2.utilities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarthome_v2.R;

import java.util.Calendar;
/**
 * an Aquarium class
 *
 * @author Tarık Buğra Karali
 * @version 06.05.2020
 */
public class Aquarium extends AppCompatActivity {
    private Button species;
    ImageButton timeButton_one;
    EditText textView_one;
    ImageButton timeButton_two;
    EditText textView_two;
    ImageButton day;
    EditText textView_three;
    ImageButton timeButton_three;
    ImageView specy,feed,waterExchange,motor,aquarim;
    EditText textView_four;
    Bundle bundle;
    String type,dayMotor;
    int themeNumber;
    TextView a,b,c,d,e;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aquarium_screen);

        //initialization
        species = findViewById(R.id.species);
        timeButton_one = findViewById(R.id.feed_button);
        textView_one = findViewById(R.id.time_feed);
        timeButton_two = findViewById(R.id.exchange_button);
        textView_two = findViewById(R.id.time_exhange);
        day = findViewById(R.id.exchange_day_button);
        textView_three = findViewById(R.id.day_exhange);
        timeButton_three = findViewById(R.id.air_motor_button);
        textView_four = findViewById(R.id.air_motor_start);
        specy = findViewById(R.id.im_species);
        waterExchange = findViewById(R.id.im_water);
        feed = findViewById(R.id.im_feed);
        motor = findViewById(R.id.im_motor);
        aquarim = findViewById(R.id.im_aquarium);
        a = findViewById(R.id.feed_text);
        b = findViewById(R.id.exchange_day_text);
        c = findViewById(R.id.exchange_time_text);
        d = findViewById(R.id.motor_hour);
        e = findViewById(R.id.motor_day);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle != null ){
            type = bundle.getString("type");
            themeNumber = bundle.getInt("theme");
            dayMotor = bundle.getString("day");
           //implementation of datas
            species.setText(type);
            species.setBackgroundResource(R.color.on);
            textView_three.setText(dayMotor);
        }

        //choosing theme
        if(themeNumber == 1){

            aquarim.setBackgroundResource(R.drawable.ic_bluenight_aquarium);
            specy.setBackgroundResource(R.drawable.ic_bluenight_species);
            waterExchange.setBackgroundResource(R.drawable.ic_bluenight_water_exhange);
            feed.setBackgroundResource(R.drawable.ic_nightblue_fish_food);
            motor.setBackgroundResource(R.drawable.ic_bluenight_air_engine);
            Drawable drawable = textView_one.getBackground(); // get current EditText drawable
            drawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_one.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableTwo = textView_two.getBackground(); // get current EditText drawable
            drawableTwo.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_two.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableThree = textView_three.getBackground(); // get current EditText drawable
            drawableThree.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_three.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableFour = textView_four.getBackground(); // get current EditText drawable
            drawableFour.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_four.setBackgroundDrawable(drawable); // set the new drawable to EditText
            timeButton_one.setBackgroundResource(R.drawable.ic_bluenight_clock);
            timeButton_two.setBackgroundResource(R.drawable.ic_bluenight_clock);
            timeButton_three.setBackgroundResource(R.drawable.ic_bluenight_clock);
            day.setBackgroundResource(R.drawable.ic_bluenight_clock);
            a.setTextColor(Color.BLUE);
            b.setTextColor(Color.BLUE);
            c.setTextColor(Color.BLUE);
            d.setTextColor(Color.BLUE);
            e.setTextColor(Color.BLUE);
            species.setBackgroundResource(R.color.blue_night);

        }

        if(themeNumber == 2){

            aquarim.setBackgroundResource(R.drawable.ic_alien_aquarium);
            specy.setBackgroundResource(R.drawable.ic_alien_species);
            waterExchange.setBackgroundResource(R.drawable.ic_alien_water_exchange);
            feed.setBackgroundResource(R.drawable.ic_alien_fish_feed);
            motor.setBackgroundResource(R.drawable.ic_alien_air_engine);
            Drawable drawable = textView_one.getBackground(); // get current EditText drawable
            drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_one.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableTwo = textView_two.getBackground(); // get current EditText drawable
            drawableTwo.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_two.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableThree = textView_three.getBackground(); // get current EditText drawable
            drawableThree.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_three.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableFour = textView_four.getBackground(); // get current EditText drawable
            drawableFour.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_four.setBackgroundDrawable(drawable); // set the new drawable to EditText
            timeButton_one.setBackgroundResource(R.drawable.ic_bluenight_clock);
            timeButton_two.setBackgroundResource(R.drawable.ic_bluenight_clock);
            timeButton_three.setBackgroundResource(R.drawable.ic_bluenight_clock);
            day.setBackgroundResource(R.drawable.ic_bluenight_clock);
            a.setTextColor(Color.BLACK);
            b.setTextColor(Color.BLACK);
            c.setTextColor(Color.BLACK);
            d.setTextColor(Color.BLACK);
            e.setTextColor(Color.BLACK);
            species.setBackgroundResource(R.color.alien);

        }

        if(themeNumber == 3){

            aquarim.setBackgroundResource(R.drawable.ic_wood_aquarium);
            specy.setBackgroundResource(R.drawable.ic_wood_species);
            waterExchange.setBackgroundResource(R.drawable.ic_wood_water_exhange);
            feed.setBackgroundResource(R.drawable.ic_wood_fish_food);
            motor.setBackgroundResource(R.drawable.ic_wood_air_engine);
            Drawable drawable = textView_one.getBackground(); // get current EditText drawable
            drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_one.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableTwo = textView_two.getBackground(); // get current EditText drawable
            drawableTwo.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_two.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableThree = textView_three.getBackground(); // get current EditText drawable
            drawableThree.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_three.setBackgroundDrawable(drawable); // set the new drawable to EditText
            Drawable drawableFour = textView_four.getBackground(); // get current EditText drawable
            drawableFour.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); // change the drawable color
            textView_four.setBackgroundDrawable(drawable); // set the new drawable to EditText
            timeButton_one.setBackgroundResource(R.drawable.ic_wood_clock);
            timeButton_two.setBackgroundResource(R.drawable.ic_wood_clock);
            timeButton_three.setBackgroundResource(R.drawable.ic_wood_clock);
            day.setBackgroundResource(R.drawable.ic_wood_clock);
            a.setTextColor(Color.WHITE);
            b.setTextColor(Color.WHITE);
            c.setTextColor(Color.WHITE);
            d.setTextColor(Color.WHITE);
            e.setTextColor(Color.WHITE);
            species.setBackgroundResource(R.color.alien);

        }
        //events
        species.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FishList.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });

        timeButton_one.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//actual hour is taken
                int minute = mcurrentTime.get(Calendar.MINUTE);//actual minute is taken
                TimePickerDialog timePicker; //Time Picker reference is created.

               //TimePicker creates object and add  click listener
                timePicker = new TimePickerDialog(Aquarium.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView_one.setText( selectedHour + ":" + selectedMinute);//print on the text view
                    }
                }, hour, minute, true);//true for 24 hour system
                timePicker.setTitle("Choose Time");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Save", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });


        timeButton_two.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//actual hour is taken
                int minute = mcurrentTime.get(Calendar.MINUTE);//actual minute is taken
                TimePickerDialog timePicker; //Time Picker reference is created.

                //TimePicker creates object and add  click listener
                timePicker = new TimePickerDialog(Aquarium.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView_two.setText( selectedHour + ":" + selectedMinute);//print on the text view
                    }
                }, hour, minute, true);//true for 24 hour system
                timePicker.setTitle("Choose Time");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Save", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });


        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AquariumDaylist.class);
                i.putExtra("theme", themeNumber);
                startActivity(i);
            }
        });


        timeButton_three.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//actual hour is taken
                int minute = mcurrentTime.get(Calendar.MINUTE);//actual minute is taken
                TimePickerDialog timePicker; //Time Picker reference is created.

                //TimePicker creates object and add  click listener
                timePicker = new TimePickerDialog(Aquarium.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView_four.setText( selectedHour + ":" + selectedMinute);//print on the text view
                    }
                }, hour, minute, true);//true for 24 hour system
                timePicker.setTitle("Choose Time");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Save", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });

    }
}
