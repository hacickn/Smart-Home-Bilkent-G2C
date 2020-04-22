package com.SmartHomeBilkent.extra.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast {

    //properties
    public String locationName;
    public String location;
    public String weather;
    public String temperature;
    public String wind;
    public String humidity;
    public String localTime;

    //constructor
    public WeatherForecast(String location) throws IOException {
        locationName = location;
        this.location = findLocationXY(location);
        System.out.println(this.location);
        weather = "";
        wind = "";
        temperature = "";
        humidity = "";
        localTime = "";
        getWeatherCase();
    }



    //methods
    public String findLocationXY(String location) throws IOException {
        String XY = "";
        URL url;
        url = new URL("https://api.mapbox.com/geocoding/v5/mapbox.places/%20" + location + ".json?access_token=pk.eyJ1IjoiaGNja24iLCJhIjoiY2s4dTd0b3VsMDIxNzNna2Rsdjd2enJieiJ9.6OwVbhFTRPwDxPX5BLedTw&limit=1");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            for(int k = 0; k < response.length()-15 ; k++){
                if(response.substring(k,k+11).equals("coordinates")){
                    while(!(response.charAt(k+14)==']')){
                        XY = XY + response.charAt(k+14);
                        k++;
                    }
                }
            }
            int number = XY.indexOf(',');
            XY = XY.substring(number + 1) + "," + XY.substring(0,number);
            this.location = XY;
            return XY;
        } else {
            System.out.println("GET request not worked");
            return null;
        }
    }

    public String[] getWeatherCase() throws IOException {
        String[] weatherInfo;
        weatherInfo = new String[5];

        URL url;
        url = new URL("http://api.weatherstack.com/current?access_key=c3606cc969e14ee977079a6e2ab290bf&query=" + getLocation());
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            for(int k = 0; k < response.length()-25 ; k++){
                if(response.substring(k,k+20).equals("weather_descriptions")){
                    while(!(response.charAt(k+24)=='"')){
                        weatherInfo[0] = weatherInfo[0] + response.charAt(k+24);
                        k++;
                    }
                    weather = weatherInfo[0].substring(4);
                }
                if(response.substring(k,k+11).equals("temperature")){
                    while(!(response.charAt(k+13)==',')){
                        weatherInfo[1] = weatherInfo[1] + response.charAt(k+13);
                        k++;
                    }
                    temperature = weatherInfo[1].substring(4);
                }
                if(response.substring(k,k+10).equals("wind_speed")){
                    while(!(response.charAt(k+12)==',')){
                        weatherInfo[2] = weatherInfo[2] + response.charAt(k+12);
                        k++;
                    }
                    wind = weatherInfo[2].substring(4);
                }
                if(response.substring(k,k+8).equals("humidity")){
                    while(!(response.charAt(k+10)==',')){
                        weatherInfo[3] = weatherInfo[3] + response.charAt(k+10);
                        k++;
                    }
                    humidity = weatherInfo[3].substring(4);
                }
                if(response.substring(k,k+10).equals("localtime\"")){
                    while(!(response.charAt(k+12)=='"')){
                        weatherInfo[4] = weatherInfo[4] + response.charAt(k+12);
                        k++;
                    }
                    localTime = weatherInfo[4].substring(4);
                }
            }
            return weatherInfo;
        } else {
            System.out.println("GET request not worked");
            return null;
        }
    }
    public String getLocation(){
        return location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }
}
