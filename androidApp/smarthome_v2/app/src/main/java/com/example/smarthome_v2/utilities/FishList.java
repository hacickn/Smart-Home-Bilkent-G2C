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

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.utilities.Aquarium;
/**
 * an FishList class
 *
 * @author Tarık Buğra Karali
 * @version 06.05.2020
 */
public class FishList extends AppCompatActivity {
    private ListView list;
    private static final String[] fishTypes = new String[]{"Lepistes","Japon","Vatoz","Brachydanio","clownfish","Pencilfish","Juvenile"};
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.species_list);
        list = findViewById(R.id.fish_type);

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(context,R.layout.text_special,fishTypes);
        list.setAdapter(adaptor);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String show = fishTypes[position]+"";
                Intent i = new Intent(getApplicationContext(), Aquarium.class);

                i.putExtra("type",show);
                startActivity(i);
                setContentView(R.layout.aquarium_screen);

            }
        });

    }
}
