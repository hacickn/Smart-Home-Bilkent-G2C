package com.example.smarthome_v2.utilities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Objects;
/**
 * an Electricity class
 *
 * @author Erengazi Mutlu
 * @version 06.05.2020
 */
public class Electricity extends AppCompatActivity {
    public ImageButton exit;
    public ToggleButton elec_controller;
    private ImageView light_one;
    private ImageView light_two;
    private ImageView light_three,elec;
    private BarChart gasChart;
    private ArrayList<BarEntry> dataValues;
    private BarDataSet lineDataSet;
    private  ArrayList<IBarDataSet> dataSets;
    private BarData data;
    private Description description;
    Bundle bundle;
    int themeNumber;
    boolean condition,currentCondition;
    Intent thm;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electricity_screen);

        //graph
        gasChart = findViewById(R.id.gasChart);
        dataValues = new ArrayList<>();
        dataValues.add(new BarEntry(0,20));
        dataValues.add(new BarEntry(1,15));
        dataValues.add(new BarEntry(2,16));
        dataValues.add(new BarEntry(3,5));
        dataValues.add(new BarEntry(4,7));
        dataValues.add(new BarEntry(5,22));
        dataValues.add(new BarEntry(6,17));
        dataValues.add(new BarEntry(7,14));
        dataValues.add(new BarEntry(8,13));
        dataValues.add(new BarEntry(9,15));
        dataValues.add(new BarEntry(10,14));
        dataValues.add(new BarEntry(11,11));
        lineDataSet = new BarDataSet(dataValues,"GAS USAGE");
        dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        data = new BarData( dataSets);
        gasChart.setData(data);
        gasChart.invalidate();
        gasChart.setDrawGridBackground( false );
        gasChart.setBorderColor( R.color.orange_two);
        description = new Description();
        description.setText("HOURS/DAY");
        gasChart.setDescription(description);


        //initialization
        exit=findViewById(R.id.exit_elec);
        light_one = findViewById(R.id.light1);
        elec_controller = (ToggleButton) findViewById(R.id.elec_control);
        light_two = findViewById(R.id.light2);
        light_three = findViewById(R.id.light3);
        elec = findViewById(R.id.mainelectiricity);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
            condition = bundle.getBoolean("elecCondition");
        }

        //impementation of datas
        elec_controller.setChecked(condition);

        if(!condition) {
            light_one.setVisibility(View.INVISIBLE);
            light_two.setVisibility(View.INVISIBLE);
            light_three.setVisibility(View.INVISIBLE);
        }

        //choosing theme
        if(themeNumber == 1){

            light_one.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            light_two.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            light_three.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            elec.setBackgroundResource(R.drawable.ic_bluenight_electricity);
        }

        if(themeNumber == 2){

            light_one.setBackgroundResource(R.drawable.ic_alien_eleclight);
            light_two.setBackgroundResource(R.drawable.ic_alien_eleclight);
            light_three.setBackgroundResource(R.drawable.ic_alien_eleclight);
            elec.setBackgroundResource(R.drawable.ic_alien_electricity);
        }

        if(themeNumber == 3){

            light_one.setBackgroundResource(R.drawable.ic_wood_eleclight);
            light_two.setBackgroundResource(R.drawable.ic_wood_eleclight);
            light_three.setBackgroundResource(R.drawable.ic_wood_eleclight);
            elec.setBackgroundResource(R.drawable.ic_wood_electricity);
        }

        //events
        elec_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON
                    light_one.setVisibility(View.VISIBLE);
                    light_two.setVisibility(View.VISIBLE);
                    light_three.setVisibility(View.VISIBLE);
                    gasChart.setBackgroundColor(Color.GREEN);
                } else {
                    // OFF
                    light_one.setVisibility(View.INVISIBLE);
                    light_two.setVisibility(View.INVISIBLE);
                    light_three.setVisibility(View.INVISIBLE);
                    gasChart.setBackgroundColor(Color.RED);
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCondition =elec_controller.isChecked();
                thm = new Intent(Electricity.this, main_screen.class);
                thm.putExtra("theme",themeNumber);
                thm.putExtra("electricity",currentCondition);
                startActivity(thm);
            }
        });

    }
}
