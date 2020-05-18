package com.example.smarthome_v2.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;

public class GraphicSettings extends Activity {

    //properties
    private ImageButton theme,textType;
    private int themeNo,text;
    private Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic_settings_screen);


        theme = findViewById(R.id.theme_type);
        textType = findViewById(R.id.text_type);

        bundle = getIntent().getExtras();

        if(bundle!=null) {
            themeNo = bundle.getInt("theme");
            text = bundle.getInt("text");
        }


        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(GraphicSettings.this, ThemeSettings.class);
                main.putExtra("theme",themeNo);
                startActivity(main);
            }
        });

        textType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(GraphicSettings.this, TextSettings.class);
                main.putExtra("text",text);
                startActivity(main);
            }
        });
    }
}
