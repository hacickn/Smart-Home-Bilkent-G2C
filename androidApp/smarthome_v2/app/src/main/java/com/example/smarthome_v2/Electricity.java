package com.example.smarthome_v2;

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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Objects;

public class Electricity extends AppCompatActivity {
    public ImageButton exit;
    public ToggleButton elec_controller;
    private ImageView light_one;
    private ImageView light_two;
    private ImageView light_three;
    private BarChart gasChart;
    private ArrayList<BarEntry> dataValues;
    private BarDataSet lineDataSet;
    private  ArrayList<IBarDataSet> dataSets;
    private BarData data;
    private Description description;




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electricity_screen);
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


        exit=findViewById(R.id.exit_elec);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(Electricity.this,main_screen.class);
                startActivity(main);
            }
        });

        light_one = findViewById(R.id.light1);
        elec_controller = (ToggleButton) findViewById(R.id.elec_control);
        boolean condition = Objects.requireNonNull(getIntent().getExtras()).getBoolean("elecCondition");
        elec_controller.setChecked(condition);

        light_two = findViewById(R.id.light2);
        light_three = findViewById(R.id.light3);
        if(!condition) {
            light_one.setVisibility(View.INVISIBLE);
            light_two.setVisibility(View.INVISIBLE);
            light_three.setVisibility(View.INVISIBLE);
        }

        elec_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON durumunda yapılacaklar
                    light_one.setVisibility(View.VISIBLE);
                    light_two.setVisibility(View.VISIBLE);
                    light_three.setVisibility(View.VISIBLE);
                    gasChart.setBackgroundColor(Color.GREEN);
                } else {
                    // OFF durumunda yapılacaklar
                    light_one.setVisibility(View.INVISIBLE);
                    light_two.setVisibility(View.INVISIBLE);
                    light_three.setVisibility(View.INVISIBLE);
                    gasChart.setBackgroundColor(Color.RED);
                }
            }
        });

    }
}
