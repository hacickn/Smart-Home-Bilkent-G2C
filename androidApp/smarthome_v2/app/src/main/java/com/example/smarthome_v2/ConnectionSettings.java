package com.example.smarthome_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * an Connection Settings class
 *
 * @author Erengazi Mutlu
 * @version 11.05.2020
 */
public class ConnectionSettings extends AppCompatActivity {
    public ImageButton exit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_settings);

        exit=findViewById(R.id.exit_connection);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(ConnectionSettings.this, MainScreen.class);
                startActivity(main);
            }
        });
    }
}
