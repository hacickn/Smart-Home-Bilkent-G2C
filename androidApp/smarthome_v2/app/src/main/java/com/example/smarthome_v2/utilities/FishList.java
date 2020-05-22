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
 * an FishList class
 *
 * @author Tarık Buğra Karali
 * @version 06.05.2020
 */
public class FishList extends AppCompatActivity {

    //properties
    private ListView list;
    private static final String[] fishTypes = new String[]
            {"Lepistes","Japon","Vatoz","Brachydanio","clownfish","Pencilfish","Juvenile"};
    private Context context = this;
    private Bundle bundle;
    private int themeNumber;
    private ConstraintLayout main;
    private ArrayAdapter<String> adaptor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.species_list);

        //initialization
        list = findViewById(R.id.fish_type);
        main = findViewById(R.id.list_of_fish);
        adaptor = new ArrayAdapter<String>(context,R.layout.text_special,fishTypes);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle != null )
        {
            themeNumber = bundle.getInt("theme");
        }

        //choosing theme
        if(themeNumber == 1)
        {
            main.setBackgroundResource(R.color.blue_night);
            adaptor = new ArrayAdapter<String>(context,R.layout.bluenight_text_special,fishTypes);
        }

        if(themeNumber == 2)
        {
            main.setBackgroundResource(R.color.alien);
            adaptor = new ArrayAdapter<String>(context,R.layout.bluenight_text_special,fishTypes);
        }

        //implementation
        list.setAdapter(adaptor);

        //events
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String show = fishTypes[position]+"";
                Intent i = new Intent(getApplicationContext(), Aquarium.class);
                i.putExtra("theme",themeNumber);
                i.putExtra("type",show);
                startActivity(i);
                setContentView(R.layout.aquarium_screen);
            }
        });
    }
}
