package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import com.example.smarthome_v2.R;
import com.example.smarthome_v2.MainScreen;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.ArrayList;
import java.util.HashMap;
import pl.droidsonroids.gif.GifImageView;

/**
 * a Electricity Pop class
 *
 * @author Erengazi Mutlu , Nasuh Dincer
 * @version 10.05.2020
 */
public class elecPop extends Activity {

    //properties
    private ImageButton exit;
    private ToggleButton elec_controller;
    private ImageView light_one, light_two, light_three, elec;
    private BarChart gasChart;
    private ArrayList<BarEntry> dataValues;
    private ArrayList<IBarDataSet> dataSets;
    private BarDataSet lineDataSet;
    private BarData data;
    private Description description;
    private Bundle bundle;
    private int themeNumber;
    private boolean condition,currentCondition;
    private Intent thm;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private GifImageView electricityGIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electricity_screen);

        //initializing the pop-up as below
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*1),(int)(height*1));
        ImageButton exit = findViewById(R.id.exit_elec);
        exit.setVisibility(View.GONE);

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
        light_one = findViewById(R.id.light1);
        elec_controller = (ToggleButton) findViewById(R.id.elec_control);
        light_two = findViewById(R.id.light2);
        light_three = findViewById(R.id.light3);
        elec = findViewById(R.id.mainelectiricity);
        mAuth = FirebaseAuth.getInstance();
        electricityGIF = findViewById(R.id.electricityGIF);

        //getting datas

        bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            themeNumber = bundle.getInt("theme");
            condition = bundle.getBoolean("elecCondition");
        }

        //impementation of datas
        elec_controller.setChecked(condition);

        if(!condition)
        {
            light_one.setVisibility(View.INVISIBLE);
            light_two.setVisibility(View.INVISIBLE);
            light_three.setVisibility(View.INVISIBLE);
        }

        //choosing theme
        if(themeNumber == 1)
        {
            light_one.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            light_two.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            light_three.setBackgroundResource(R.drawable.ic_bluenight_eleclight);
            elec.setBackgroundResource(R.drawable.ic_bluenight_electricity);
            electricityGIF.setImageResource(R.drawable.bluenight_electricity_first);
        }

        if(themeNumber == 2)
        {
            light_one.setBackgroundResource(R.drawable.ic_alien_eleclight);
            light_two.setBackgroundResource(R.drawable.ic_alien_eleclight);
            light_three.setBackgroundResource(R.drawable.ic_alien_eleclight);
            elec.setBackgroundResource(R.drawable.ic_alien_electricity);
            electricityGIF.setImageResource(R.drawable.alien_electricity_first);
        }

        if(themeNumber == 3)
        {
            light_one.setBackgroundResource(R.drawable.ic_wood_eleclight);
            light_two.setBackgroundResource(R.drawable.ic_wood_eleclight);
            light_three.setBackgroundResource(R.drawable.ic_wood_eleclight);
            elec.setBackgroundResource(R.drawable.ic_wood_electricity);
            electricityGIF.setImageResource(R.drawable.backgroundwood);
        }

        //events
        elec_controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on)
                {
                    // ON
                    light_one.setVisibility(View.VISIBLE);
                    light_two.setVisibility(View.VISIBLE);
                    light_three.setVisibility(View.VISIBLE);
                    gasChart.setBackgroundColor(Color.GREEN);
                } else
                {
                    // OFF
                    light_one.setVisibility(View.INVISIBLE);
                    light_two.setVisibility(View.INVISIBLE);
                    light_three.setVisibility(View.INVISIBLE);
                    gasChart.setBackgroundColor(Color.RED);
                }

                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String, Object> userMap = new HashMap<>();

                if( elec_controller.isChecked() )
                {
                    userMap.put("electricity", "on");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"ELECTRICITY IS OPENED" , FancyToast.LENGTH_SHORT,FancyToast.SUCCESS ,R.drawable.ic_alien_electricity,false).show();
                }
                else
                {
                    userMap.put("electricity", "off");
                    mDatabase.updateChildren(userMap);
                    FancyToast.makeText(getApplicationContext(),"ELECTRICITY IS CLOSED" , FancyToast.LENGTH_SHORT,FancyToast.WARNING ,R.drawable.ic_alien_electricity,false).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCondition =elec_controller.isChecked();
                thm = new Intent(elecPop.this, MainScreen.class);
                thm.putExtra("theme",themeNumber);
                thm.putExtra("electricity",currentCondition);
                startActivity(thm);
            }
        });
    }
}
