package com.example.smarthome_v2.utilities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
 * a Gas class
 *
 * @author Tarık Buğra Karali
 * @version 06.05.2020
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Gas extends AppCompatActivity {
    public ImageButton exit;
    public ToggleButton gas_controller;
    private ImageView smokes,wave_one,wave_two,gas;
    private BarChart gasChart;
    private ArrayList<BarEntry> dataValues;
    private BarDataSet lineDataSet;
    private  ArrayList<IBarDataSet> dataSets;
    private BarData data;
    private Description description;
    boolean condition,currentCondition;
    int themeNumber;
    Bundle bundle;
    Intent thm;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_screen);

        //chart
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
        exit=findViewById(R.id.exit_gas);
        smokes = findViewById(R.id.smokes);
        wave_one = findViewById(R.id.wave_1);
        wave_two = findViewById(R.id.wave_two);
        gas_controller = (ToggleButton) findViewById(R.id.gas_control);
        gas=findViewById(R.id.gas_gas);

        //getting datas
        bundle = getIntent().getExtras();
        if(bundle!=null) {
            themeNumber = bundle.getInt("theme");
            condition = bundle.getBoolean("gasCondition");
        }
        //impementation of datas

        gas_controller.setChecked(condition);

        if(!condition) {
            smokes.setVisibility(View.INVISIBLE);
            wave_one.setVisibility(View.INVISIBLE);
            wave_two.setVisibility(View.INVISIBLE);
        }

        //choosing theme
        if(themeNumber == 1){

            smokes.setBackgroundResource(R.drawable.ic_bluenight_smoke);
            wave_one.setBackgroundResource(R.drawable.ic_bluenight_wave);
            wave_two.setBackgroundResource(R.drawable.ic_bluenight_wave);
            gas.setBackgroundResource(R.drawable.ic_bluenight_gas);
        }

        //events
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCondition =gas_controller.isChecked();
                 thm = new Intent(Gas.this, main_screen.class);
                 thm.putExtra("theme",themeNumber);
                thm.putExtra("gas",currentCondition);
                startActivity(thm);
            }
        });

        gas_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    // ON

                    smokes.setVisibility(View.VISIBLE);
                    wave_one.setVisibility(View.VISIBLE);
                    wave_two.setVisibility(View.VISIBLE);
                    gasChart.setBackgroundColor(Color.GREEN);
                } else {
                    // OFF
                    smokes.setVisibility(View.INVISIBLE);
                    wave_one.setVisibility(View.INVISIBLE);
                    wave_two.setVisibility(View.INVISIBLE);
                    gasChart.setBackgroundColor(Color.RED);
                }
            }
        });


    }

}
