package com.example.smarthome_v2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Aquarium extends AppCompatActivity {
    private Button species;
    ImageButton timeButton_one;
    EditText textView_one;
    ImageButton timeButton_two;
    EditText textView_two;
    ImageButton day;
    EditText textView_three;
    ImageButton timeButton_three;
    EditText textView_four;


    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aquarium_screen);

        species = findViewById(R.id.species);
        species.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(Aquarium.this, FishList.class);
                startActivity(main);

            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
            String type = bundle.getString("type");
            species.setText(type);
            species.setBackgroundResource(R.color.on);
        }

        timeButton_one = findViewById(R.id.feed_button);
        textView_one=findViewById(R.id.time_feed);
        timeButton_one.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(Aquarium.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView_one.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Save", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });




        timeButton_two = findViewById(R.id.exchange_button);
        textView_two=findViewById(R.id.time_exhange);
        timeButton_two.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(Aquarium.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView_two.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Save", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });


        day = findViewById(R.id.exchange_day_button);
        textView_three=findViewById(R.id.day_exhange);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(Aquarium.this, AquariumDaylist.class);
                startActivity(main);

            }
        });
        Bundle bundle_two = getIntent().getExtras();
        if(bundle_two != null){
            String type = bundle_two.getString("day");
            textView_three.setText(type);

        }


        timeButton_three = findViewById(R.id.air_motor_button);
        textView_four=findViewById(R.id.air_motor_start);
        timeButton_three.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(Aquarium.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView_four.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Save", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });

    }
}
