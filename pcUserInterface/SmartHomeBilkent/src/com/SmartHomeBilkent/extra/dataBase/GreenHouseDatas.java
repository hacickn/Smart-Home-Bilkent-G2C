package com.SmartHomeBilkent.extra.dataBase;

import com.SmartHomeBilkent.extra.dataBase.fields.GreenHouseValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * a GreenHouseDatas class
 *
 * @author İLKE DOĞAN
 * @version 01.05.2020
 */
public class GreenHouseDatas {
   //properties
   private static final String TABLE_GREEN_HOUSE = "GREENHOUSE";
   private static final String TABLE_DAY_COLUMN = "DAY";
   private static final String TABLE_TEMPERATURE_COLUMN = "TEMPERATURE";
   private static final String TABLE_HUMIDITY_COLUMN = "HUMIDITY";
   private static GreenHouseDatas instance = new GreenHouseDatas();
   private static ObservableList< Integer > temperaturesPerDay;
   private static ObservableList< Integer > humidityPerDay;
   private static ObservableList< GreenHouseValues > valuesList;
   private Connection connection;
   private DateTimeFormatter dateTimeFormatter;
   private String[] detailUsage;

   //constructor
   private GreenHouseDatas() {
      connection = DatabaseConnection.getInstance().getConnection();
      dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy.MM.dd" );
   }

   //methods
   public static GreenHouseDatas getInstance() {
      return instance;
   }

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

   public ObservableList< Integer > calculateAverageTemperature() {
      temperaturesPerDay = FXCollections.observableArrayList();
      int temperature;

      for( GreenHouseValues values : valuesList ) {
         temperature = 0;
         detailUsage = values.getTemperature().split( "@" );

         if( detailUsage.length > 0 ){
            for( int k = 0; k < detailUsage.length; k++)
               temperature = temperature + Integer.parseInt( detailUsage[ k ] );
            temperaturesPerDay.add( temperature / detailUsage.length );
         }
      }
      return temperaturesPerDay;
   }

   public ObservableList< Integer > calculateAverageHumidity() {
      humidityPerDay = FXCollections.observableArrayList();
      int humidity;

      for( GreenHouseValues values : valuesList ) {
         humidity = 0;
         detailUsage = values.getHumidity().split( "@" );

         if( detailUsage.length > 0 ){
            for( int k = 0; k < detailUsage.length; k++)
               humidity = humidity + Integer.parseInt( detailUsage[ k ] );
            humidityPerDay.add( humidity / detailUsage.length );
         }
      }
      return humidityPerDay;
   }

   public void getTable( LineChart< Number, Number > lineChart ) {
      XYChart.Series dataSeries;
      dataSeries = new XYChart.Series();
      dataSeries.setName( "°C" );
      XYChart.Series dataSeries2;
      dataSeries2 = new XYChart.Series();
      dataSeries2.setName( "%" );
      calculateAverageHumidity();
      calculateAverageTemperature();
      for( int k = 0; k < valuesList.size(); k++ ) {
         dataSeries.getData().add( new XYChart.Data( valuesList.get( k ).getLocalDate().toString(), temperaturesPerDay.get( k ) ) );
         dataSeries2.getData().add( new XYChart.Data( valuesList.get( k ).getLocalDate().toString(), humidityPerDay.get( k ) ) );
      }

      lineChart.getData().addAll( dataSeries, dataSeries2 );

   }
}
