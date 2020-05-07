package com.example.smarthome_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarthome_v2.R;

public class AquariumDaylist extends AppCompatActivity {
    private ListView list;
    private static final String[] days = new String[]{"1. Day of the Week","2. Day of the Week","3. Day of the Week","4. Day of the Week","5. Day of the Week","6. Day of the Week","7. Day of the Week"};
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aquarium_daylist);
        list = findViewById(R.id.day_list);

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(context,R.layout.text_special,days);
        list.setAdapter(adaptor);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String theDay = days[position]+"";
                Intent j = new Intent(getApplicationContext(),Aquarium.class);

                j.putExtra("day",theDay);
                startActivity(j);
                setContentView(R.layout.aquarium_screen);

            }
        });

    }
}
