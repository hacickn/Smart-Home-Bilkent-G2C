package com.example.smarthome_v2.utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.utilities.Aquarium;
/**
 * an AquariumDayList class
 *
 * @author Tarık Buğra Karali
 * @version 06.05.2020
 */
public class AquariumDaylist extends AppCompatActivity {
    private ListView list;
    private static final String[] days = new String[]{"1. Day of the Week","2. Day of the Week","3. Day of the Week","4. Day of the Week","5. Day of the Week","6. Day of the Week","7. Day of the Week"};
    Context context = this;
    Bundle bundle;
    int themeNumber;
    ConstraintLayout main;
    ArrayAdapter<String> adaptor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aquarium_daylist);

        //initialization
        list = findViewById(R.id.day_list);
        main = findViewById(R.id.list_of_days);
        adaptor = new ArrayAdapter<String>(context,R.layout.text_special,days);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle != null ){
            themeNumber = bundle.getInt("theme");
        }

        //choosing theme
        if(themeNumber == 1){
            main.setBackgroundResource(R.color.blue_night);
            adaptor = new ArrayAdapter<String>(context,R.layout.bluenight_text_special,days);
        }


        //implementation
        list.setAdapter(adaptor);

        //events
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String theDay = days[position]+"";
                Intent j = new Intent(getApplicationContext(), Aquarium.class);
                j.putExtra("day",theDay);
                j.putExtra("theme",themeNumber);
                startActivity(j);
                setContentView(R.layout.aquarium_screen);
            }
        });
    }
}
