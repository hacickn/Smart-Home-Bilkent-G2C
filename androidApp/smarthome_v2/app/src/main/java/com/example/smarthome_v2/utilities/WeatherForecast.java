package com.example.smarthome_v2.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * a WeatherForecast class
 *
 * @author Hacı Çakın
 * @version 06.05.2020
 */
public class WeatherForecast {

    //properties
    private String locationName;
    private String location;
    private String weather;
    private String temperature;
    private String wind;
    private String humidity;
    private String localTime;

    //constructor

    /**
     * it is a WeatherForecast constructor
     *
     * @param location is a String input parameter
     */
    public WeatherForecast( String location ) throws IOException {
        locationName = location;
        this.location = findLocationXY( location );
        weather = "";
        wind = "";
        temperature = "";
        humidity = "";
        localTime = "";
        getWeatherCase();
    }

    //methods

    /**
     * it is a findLocationXY method that finds coordinates of given location
     *
     * @param location is a String input parameter
     * @return result as a String
     */
    public String findLocationXY( String location ) throws IOException {
        String XY;
        String inputLine;
        URL url;
        HttpURLConnection con;
        int responseCode;
        int number;

        XY = "";
        url = new URL( "https://api.mapbox.com/geocoding/v5/mapbox.places/%20" +
                makeItSuitableForUTF( location ) +
                ".json?access_token=pk.eyJ1IjoiaGNja24iLCJhIjoiY2s4dTd0b3VsMDIxNzNna2Rsdjd2enJieiJ9.6OwVbhFTRPwDxPX5BLedTw&limit=1" );
        con = ( HttpURLConnection ) url.openConnection();
        con.setRequestMethod( "GET" );
        con.setRequestProperty( "User-Agent", "Mozilla/5.0" );
        responseCode = con.getResponseCode();

        if( responseCode == HttpURLConnection.HTTP_OK ) {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
            StringBuilder response;
            response = new StringBuilder();

            while( ( inputLine = bufferedReader.readLine() ) != null ) {
                response.append( inputLine );
            }
            bufferedReader.close();

            // print result
            for( int k = 0; k < response.length() - 15; k++ ) {
                if( response.substring( k, k + 11 ).equals( "coordinates" ) ) {
                    while( !( response.charAt( k + 14 ) == ']' ) ) {
                        XY = XY + response.charAt( k + 14 );
                        k++;
                    }
                }
            }
            number = XY.indexOf( ',' );
            XY = XY.substring( number + 1 ) + "," + XY.substring( 0, number );
            this.location = XY;
            return XY;
        } else {
            System.out.println( "GET request not worked" );
            return null;
        }
    }

    /**
     * it is a getWeatherCase method that finds weather forecast of found coordinates
     *
     * @return result as a String[]
     */
    public String[] getWeatherCase() throws IOException {
        String[] weatherInfo;
        String inputLine;
        StringBuilder response;
        weatherInfo = new String[ 5 ];
        URL url;
        HttpURLConnection con;
        int responseCode;

        url = new URL( "http://api.weatherstack.com/current?access_key=c3606cc969e14ee977079a6e2ab290bf&query=" +
                getLocation() );
        con = ( HttpURLConnection ) url.openConnection();
        con.setRequestMethod( "GET" );
        con.setRequestProperty( "User-Agent", "Mozilla/5.0" );
        responseCode = con.getResponseCode();

        if( responseCode == HttpURLConnection.HTTP_OK ) {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
            response = new StringBuilder();

            while( ( inputLine = bufferedReader.readLine() ) != null ) {
                response.append( inputLine );
            }
            bufferedReader.close();

            System.out.println(response);
            for( int k = 0; k < response.length() - 25; k++ ) {
                if( response.substring( k, k + 20 ).equals( "weather_descriptions" ) ) {
                    while( !( response.charAt( k + 24 ) == '"' ) ) {
                        weatherInfo[ 0 ] = weatherInfo[ 0 ] + response.charAt( k + 24 );
                        k++;
                    }
                    weather = weatherInfo[ 0 ].substring( 4 );
                }
                if( response.substring( k, k + 11 ).equals( "temperature" ) ) {
                    while( !( response.charAt( k + 13 ) == ',' ) ) {
                        weatherInfo[ 1 ] = weatherInfo[ 1 ] + response.charAt( k + 13 );
                        k++;
                    }
                    temperature = weatherInfo[ 1 ].substring( 4 );
                }
                if( response.substring( k, k + 10 ).equals( "wind_speed" ) ) {
                    while( !( response.charAt( k + 12 ) == ',' ) ) {
                        weatherInfo[ 2 ] = weatherInfo[ 2 ] + response.charAt( k + 12 );
                        k++;
                    }
                    wind = weatherInfo[ 2 ].substring( 4 );
                }
                if( response.substring( k, k + 8 ).equals( "humidity" ) ) {
                    while( !( response.charAt( k + 10 ) == ',' ) ) {
                        weatherInfo[ 3 ] = weatherInfo[ 3 ] + response.charAt( k + 10 );
                        k++;
                    }
                    humidity = weatherInfo[ 3 ].substring( 4 );
                }
                if( response.substring( k, k + 10 ).equals( "localtime\"" ) ) {
                    while( !( response.charAt( k + 12 ) == '"' ) ) {
                        weatherInfo[ 4 ] = weatherInfo[ 4 ] + response.charAt( k + 12 );
                        k++;
                    }
                    localTime = weatherInfo[ 4 ].substring( 4 );
                }
            }
            return weatherInfo;
        } else {
            System.out.println( "GET request not worked" );
            return null;
        }
    }

    /**
     * it is a getLocation method
     *
     * @return result as a String
     */
    public String getLocation() {
        return location;
    }

    /**
     * it is a getLocationName method
     *
     * @return result as a String
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * it is a setLocationName method
     *
     * @param locationName is a String input parameter
     */
    public void setLocationName( String locationName ) {
        this.locationName = locationName;
    }

    /**
     * it is a setLocation method
     *
     * @param location is a String input parameter
     */
    public void setLocation( String location ) {
        this.location = location;
    }

    /**
     * it is a getWeather method
     *
     * @return weather as a String
     */
    public String getWeather() {
        return weather;
    }

    /**
     * it is a getTemperature method
     *
     * @return temperature as a String
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * it is a getWind method
     *
     * @return wind as a String
     */
    public String getWind() {
        return wind;
    }

    /**
     * it is a getHumidity method
     *
     * @return humidity as a String
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * it is a getLocalTime method
     *
     * @return localTime as a String
     */
    public String getLocalTime() {
        return localTime;
    }

    /**
     * it is a makeItSuitableForUTF method that make given string suitable to request
     *
     * @param s a String input parameter
     * @return localTime as a String
     */
    public String makeItSuitableForUTF( String s ){
        String finalString;
        String[] list;
        finalString = "";
        list = new String[ s.length() ];
        for( int k = 0; k < s.length(); k++)
            list[ k ] = s.charAt( k ) + "";

        for( String string:list ){
            if( string.equals( "Ç" )
                    || string.equals( "ç" ) )
                string = "c";

            else if( string.equals( "Ğ" )
                    || string.equals( "ğ" ))
                string = "g";

            else if( string.equals( "İ" )
                    || string.equals( "ı" )
                    || string.equals( "I" ))
                string = "i";

            else if( string.equals( "Ö" )
                    || string.equals( "ö" ))
                string = "o";

            else if( string.equals( "Ş" )
                    || string.equals( "ş" ))
                string = "s";

            else if( string.equals( "Ü" )
                    || string.equals( "ü" ))
                string = "u";

            finalString = finalString + string;
        }
        return finalString.toLowerCase();
    }
}
