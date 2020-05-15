package com.example.smarthome_v2.popup;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.smarthome_v2.R;
import com.example.smarthome_v2.utilities.WeatherForecast;
import java.io.IOException;
import java.time.LocalTime;
import pl.droidsonroids.gif.GifImageView;

public class WeatherPop extends Activity {
    private TextView weatherTopLabel, weatherTemperature,
            weatherHumidity, weatherForecastValue,
            weatherWind, weatherLastUpdate;
    private EditText locationTextField;
    private ImageButton weatherUpdateButton;
    private WeatherForecast weatherForecast;
    private GifImageView weatherGIF;




    @RequiresApi(api = Build.VERSION_CODES.O)
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
        locationTextField = findViewById(R.id.locationTextField);
        weatherGIF = findViewById(R.id.weatherGIF);

        if( LocalTime.now().getHour() >= 6 && LocalTime.now().getHour() < 20 ){
            weatherGIF.setImageResource(R.drawable.morning);
            System.out.println(LocalTime.now().getHour());
        }else if( LocalTime.now().getHour() >= 20  ){
            weatherGIF.setImageResource(R.drawable.morning);
        }else if( LocalTime.now().getHour() < 6 ){
            weatherGIF.setImageResource(R.drawable.morning);
        }

        weatherUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!String.valueOf(locationTextField.getText()).isEmpty()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                weatherForecast = new WeatherForecast(locationTextField.getText().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    weatherForecastValue.setText( weatherForecast.getWeather());
                                    weatherTemperature.setText( weatherForecast.getTemperature() + "°" );
                                    weatherHumidity.setText("HUMIDITY: %" + weatherForecast.getHumidity());
                                    weatherWind.setText("WIND: " + weatherForecast.getWind());
                                    weatherLastUpdate.setText("LAST UPDATE: " + weatherForecast.getLocalTime());
                                }
                            });
                        }
                    }).start();

                } else {
                    weatherLastUpdate.setText("PLEASE ENTER YOUR LOCATION");
                }
            }
        });
    }

}
