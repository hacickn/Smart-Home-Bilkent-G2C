package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthome_v2.R;
import com.example.smarthome_v2.utilities.WeatherForecast;

import java.io.IOException;

public class WeatherPop extends Activity {
    private TextView weatherTopLabel, weatherTemperature,
            weatherHumidity, weatherForecastValue,
            weatherWind, weatherLastUpdate;
    private EditText locationTextField;
    private ImageButton weatherUpdateButton;
    private ImageView weatherImage;
    private WeatherForecast weatherForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_pop);

        weatherTopLabel = findViewById(R.id.weatherTopLabel);
        weatherTemperature = findViewById(R.id.weatherTemperature);
        weatherHumidity = findViewById(R.id.weatherHumidity);
        weatherForecastValue = findViewById(R.id.weatherForecast);
        weatherWind = findViewById(R.id.weatherWind);
        weatherLastUpdate = findViewById(R.id.weatherLastUpdate);
        weatherUpdateButton = findViewById(R.id.weatherUpdateButton);
        weatherImage = findViewById(R.id.weatherImage);
        locationTextField = findViewById(R.id.locationTextField);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        weatherUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!String.valueOf(locationTextField.getText()).isEmpty()) {
                    try {
                        weatherForecast = new WeatherForecast(locationTextField.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    weatherForecastValue.setText("WEATHER: " + weatherForecast.getWeather());
                    weatherTemperature.setText("TEMPERATURE: " + weatherForecast.getTemperature() + "°C" );
                    weatherHumidity.setText("HUMIDITY: %" + weatherForecast.getHumidity());
                    weatherWind.setText("WIND: " + weatherForecast.getWind());
                    weatherLastUpdate.setText("LAST UPDATE: " + weatherForecast.getLocalTime());
                } else {
                    weatherLastUpdate.setText("PLEASE ENTER YOUR LOCATION");
                }
            }
        });
    }

}
