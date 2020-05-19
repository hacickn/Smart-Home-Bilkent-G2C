package com.SmartHomeBilkent.utilities.dataBase;

import com.SmartHomeBilkent.utilities.dataBase.fields.GreenHouseValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * a GreenHouseData class
 * @author İLKE DOĞAN
 * @version 01.05.2020
 */
public class GreenHouseData {

   //properties
   private static final String TABLE_GREEN_HOUSE = "GREENHOUSE";
   private static final String TABLE_DAY_COLUMN = "DAY";
   private static final String TABLE_TEMPERATURE_COLUMN = "TEMPERATURE";
   private static final String TABLE_HUMIDITY_COLUMN = "HUMIDITY";
   private static GreenHouseData instance = new GreenHouseData();
   private static ObservableList< Integer > temperaturesPerDay;
   private static ObservableList< Integer > humidityPerDay;
   private static ObservableList< GreenHouseValues > valuesList;
   private static Connection connection;
   private DateTimeFormatter dateTimeFormatter;
   private String[] detailUsage;

   //constructor
   /**
    * it is a GreenHouseData constructor
    */
   private GreenHouseData() {
      connection = DatabaseConnection.getInstance().getConnection();
      dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy.MM.dd" );
   }

   //method

    /**
     * it is a getInstance method that provide the access to GreenHouseData
     * @return result as a instance
     */
   public static GreenHouseData getInstance() {
      return instance;
   }

    /**
     * it is a getGreenHouseValues method that get all user from the USERS table
     *
     * @return result as a ObservableList<User>
     */
   public ObservableList< GreenHouseValues > getGreenHouseValues() {
      valuesList = FXCollections.observableArrayList();
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_GREEN_HOUSE );

         while( resultSet.next() ) {
            valuesList.add( new GreenHouseValues( LocalDate.parse( resultSet.getString( TABLE_DAY_COLUMN ), dateTimeFormatter ),
                  resultSet.getString( TABLE_TEMPERATURE_COLUMN ),
                  resultSet.getString( TABLE_HUMIDITY_COLUMN ) ) );
         }
         return valuesList;
      } catch( SQLException e ) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * It is a calculateAverageTemperature that controls the given
    * calculated temperature values
    * @return result as a temperaturesPerDay
    */
   public ObservableList< Integer > calculateAverageTemperature() {
      temperaturesPerDay = FXCollections.observableArrayList();
      int temperature;

      for( GreenHouseValues values : valuesList ) {
         temperature = 0;
         detailUsage = values.getTemperature().split( "@" );

         if( detailUsage.length > 0 ) {
            for( int k = 0; k < detailUsage.length; k++ )
               temperature = temperature + Integer.parseInt( detailUsage[ k ] );
            temperaturesPerDay.add( temperature / detailUsage.length );
         }
      }
      return temperaturesPerDay;
   }

    /**
     * It is a calculateAverageHumidity that controls the given
     * calculated humidity values
     * @return result as a humidityPerDay
     */
   public ObservableList< Integer > calculateAverageHumidity() {
      humidityPerDay = FXCollections.observableArrayList();
      int humidity;

      for( GreenHouseValues values : valuesList ) {
         humidity = 0;
         detailUsage = values.getHumidity().split( "@" );

         if( detailUsage.length > 0 ) {
            for( int k = 0; k < detailUsage.length; k++ )
               humidity = humidity + Integer.parseInt( detailUsage[ k ] );
            humidityPerDay.add( humidity / detailUsage.length );
         }
      }
      return humidityPerDay;
   }

   /**
    * it is a updateSelectedFishes method
    * @param lineChart   LineChart< Number, Number > input parameter
    * @param bundle      as an ResourceBundle input parameter
    */
   public void getTable( LineChart< Number, Number > lineChart, ResourceBundle bundle ) {
      XYChart.Series dataSeries;
      dataSeries = new XYChart.Series();
      dataSeries.setName( "°C " + bundle.getString( "tempLang" ) );
      XYChart.Series dataSeries2;
      dataSeries2 = new XYChart.Series();
      dataSeries2.setName( "% " + bundle.getString( "humidity" ) );
      calculateAverageHumidity();
      calculateAverageTemperature();
      for( int k = 0; k < valuesList.size(); k++ ) {
         dataSeries.getData().add( new XYChart.Data( valuesList.get( k ).getLocalDate().toString(), temperaturesPerDay.get( k ) ) );
         dataSeries2.getData().add( new XYChart.Data( valuesList.get( k ).getLocalDate().toString(), humidityPerDay.get( k ) ) );
      }

      lineChart.getData().addAll( dataSeries, dataSeries2 );

   }
}
