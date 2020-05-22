package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.smarthome_v2.R;
import com.example.smarthome_v2.utilities.AquariumDaylist;
import com.example.smarthome_v2.utilities.FishList;
import java.util.Calendar;
import pl.droidsonroids.gif.GifImageView;

/**
 * a Aquarium Pop class
 *
 * @author Erengazi Mutlu
 * @version 09.05.2020
 */
public class aquaPop extends Activity {

    //properties
    private Button species;
    private ImageButton timeButton_one;
    private EditText textView_one, textView_two, textView_three, textView_four;
    private ImageButton timeButton_two, day, timeButton_three;
    private ImageView specy, feed, waterExchange, motor, aquarim;
    private Bundle bundle;
    private String type, dayMotor;
    private int themeNumber,textNo;
    private TextView feedText, exchangeDayText, exchangeTimeText, appSettingsText, motorDay;
    private GifImageView aquariumGIF;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aquarium_screen);

        //initializing the pop-up as below
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*1),(int)(height*1));

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
        feedText = findViewById(R.id.feed_text);
        exchangeDayText = findViewById(R.id.exchange_day_text);
        exchangeTimeText = findViewById(R.id.exchange_time_text);
        appSettingsText = findViewById(R.id.text_appsetting);
        motorDay = findViewById(R.id.motor_day);
        aquariumGIF = findViewById(R.id.aquariumGIF);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle != null )
        {
            type = bundle.getString("type");
            themeNumber = bundle.getInt("theme");
            textNo = bundle.getInt("text");
            dayMotor = bundle.getString("day");
            //implementation of datas
            species.setText(type);
            textView_three.setText(dayMotor);
        }

        //choosing theme
        if(themeNumber == 1)
        {
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
            feedText.setTextColor(Color.BLUE);
            exchangeDayText.setTextColor(Color.BLUE);
            exchangeTimeText.setTextColor(Color.BLUE);
            appSettingsText.setTextColor(Color.BLUE);
            motorDay.setTextColor(Color.BLUE);
            species.setBackgroundResource(R.color.blue_night);
            aquariumGIF.setImageResource(R.drawable.bluenight_electricity);
        }

        if(themeNumber == 2)
        {
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
            feedText.setTextColor(Color.BLACK);
            exchangeDayText.setTextColor(Color.BLACK);
            exchangeTimeText.setTextColor(Color.BLACK);
            appSettingsText.setTextColor(Color.BLACK);
            motorDay.setTextColor(Color.BLACK);
            species.setBackgroundResource(R.color.alien);
            aquariumGIF.setImageResource(R.drawable.electricity);
        }

        if(themeNumber == 3)
        {
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
            feedText.setTextColor(Color.WHITE);
            exchangeDayText.setTextColor(Color.WHITE);
            exchangeTimeText.setTextColor(Color.WHITE);
            appSettingsText.setTextColor(Color.WHITE);
            motorDay.setTextColor(Color.WHITE);
            species.setBackgroundResource(R.color.alien);
            aquariumGIF.setImageResource(R.drawable.backgroundwood);
        }

        //choosing text type
        if(textNo == 1)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/acme.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
        }

        if(textNo == 2)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
        }

        if(textNo == 3)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/amarante.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
        }

        if(textNo == 4)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/annie_use_your_telescope.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
        }

        if(textNo == 5)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/atomic_age.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
        }

        if(textNo == 6)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/baumans.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
        }

        if(textNo == 7)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BlackHanSans-Regular.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
        }

        if(textNo == 8)
        {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/cantora.ttf");
            textView_one.setTypeface(typeface);
            textView_two.setTypeface(typeface);
            textView_three.setTypeface(typeface);
            textView_four.setTypeface(typeface);
            feedText.setTypeface(typeface);
            exchangeDayText.setTypeface(typeface);
            exchangeTimeText.setTypeface(typeface);
            appSettingsText.setTypeface(typeface);
            motorDay.setTypeface(typeface);
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
                timePicker = new TimePickerDialog(aquaPop.this, new TimePickerDialog.OnTimeSetListener() {
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
                timePicker = new TimePickerDialog(aquaPop.this, new TimePickerDialog.OnTimeSetListener() {
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
                timePicker = new TimePickerDialog(aquaPop.this, new TimePickerDialog.OnTimeSetListener() {
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

