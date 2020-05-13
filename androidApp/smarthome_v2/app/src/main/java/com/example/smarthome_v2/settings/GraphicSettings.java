package com.example.smarthome_v2.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.main_screen;

public class GraphicSettings extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic_settings_screen);

        ImageButton theme = findViewById(R.id.theme_type);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(GraphicSettings.this, ThemeSettings.class);
                startActivity(main);
            }
        });
    }
}
