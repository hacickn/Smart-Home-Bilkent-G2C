package com.SmartHomeBilkent.extra.dataBase;

import com.SmartHomeBilkent.extra.dataBase.fields.Usage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ElectricityUsage {

   //properties
   private static final String TABLE_ELECTRICITY = "ELECTRICITY";
   private static final String TABLE_DAY_COLUMN = "DAY";
   private static final String TABLE_USAGE_COLUMN = "USAGE";
   private static ElectricityUsage instance = new ElectricityUsage();
   private static ObservableList< Integer > daysOfUsage;
   private static ObservableList< Usage > usageList;
   private Connection connection;
   private DateTimeFormatter dateTimeFormatter;
   private String[] detailUsage;
   private String localDate;
   private String localTime;


   //constructor
   private ElectricityUsage() {
      usageList = FXCollections.observableArrayList();
      daysOfUsage = FXCollections.observableArrayList();
      connection = DatabaseConnection.getInstance().getConnection();
      dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy.MM.dd" );
   }

   //methods
   public static ElectricityUsage getInstance() {
      return instance;
   }

   public ObservableList< Usage > getElectricityUsage() {
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_ELECTRICITY );

         while( resultSet.next() ) {
            usageList.add( new Usage( LocalDate.parse( resultSet.getString( TABLE_DAY_COLUMN ), dateTimeFormatter ),
                  resultSet.getString( TABLE_USAGE_COLUMN ) ) );
         }
         return usageList;
      } catch( SQLException e ) {
         e.printStackTrace();
         return null;
      }
   }

   public ObservableList< Integer > calculateUsage() {
      int hours;

      for( int m = 0; m < usageList.size(); m++ ) {
         hours = 0;

         if( !usageList.get( m ).getActivity().isEmpty() ) {
            detailUsage = usageList.get( m ).getActivity().split( "@" );

            for( int k = 0; k < detailUsage.length; k++ ) {

               if( detailUsage[ k ].charAt( 0 ) == 'O' && k < detailUsage.length - 1 ) {
                  hours = hours + ( ( ( ( Integer.parseInt( detailUsage[ k + 1 ].substring( 1, 3 ) ) -
                        Integer.parseInt( detailUsage[ k ].substring( 1, 3 ) ) ) * 60 ) +
                        ( Integer.parseInt( detailUsage[ k + 1 ].substring( 4 ) ) -
                              Integer.parseInt( detailUsage[ k ].substring( 4 ) ) ) ) / 60 );
                  k++;

               } else if( detailUsage[ k ].charAt( 0 ) == 'O' && k == detailUsage.length - 1 ) {
                  hours = hours + ( ( ( ( 24 - Integer.parseInt( detailUsage[ k ].substring( 1, 3 ) ) ) * 60 ) +
                        ( 0 - Integer.parseInt( detailUsage[ k ].substring( 4 ) ) ) ) / 60 );

               } else if( detailUsage[ k ].charAt( 0 ) == 'C' ) {
                  hours = hours + ( ( ( Integer.parseInt( detailUsage[ k ].substring( 1, 3 ) ) * 60 ) +
                        ( Integer.parseInt( detailUsage[ k ].substring( 4 ) ) ) ) / 60 );
               }
            }
         } else {
            if( usageList.get( m - 1 ).getActivity().charAt( usageList.get( m - 1 ).getActivity().length() - 6 ) == 'O' )
               hours = 24;
            else {
               hours = 0;
            }
         }
         daysOfUsage.add( hours );
      }
      return daysOfUsage;
   }

   public void getTable( BarChart< Number, Number > barChart ) {
      XYChart.Series dataSeries;
      dataSeries = new XYChart.Series();
      calculateUsage();
      for( int k = 0; k < daysOfUsage.size(); k++ ) {
         dataSeries.getData().add( new XYChart.Data( usageList.get( k ).getDay().toString(), daysOfUsage.get( k ) ) );
      }
      barChart.getData().add( dataSeries );

   }

   public void updateElectricity( boolean control ) {
      localDate = LocalDate.now().format( dateTimeFormatter );
      if( LocalTime.now().getHour() < 0 )
         localTime = "0" + LocalTime.now().getHour() + ":";
      else
         localTime = "" + LocalTime.now().getHour() + ":";

      if( LocalTime.now().getMinute() < 0 )
         localTime = localTime + "0" + LocalTime.now().getMinute();
      else
         localTime = localTime + "" + LocalTime.now().getMinute();

      if( control )
         localTime = "O" + localTime;
      else
         localTime = "C" + localTime;

      try{
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_ELECTRICITY +
               " WHERE " + TABLE_DAY_COLUMN + "='" + localDate + "'");

         if( !resultSet.next() ) {
            statement.execute( "INSERT INTO " +
                  TABLE_ELECTRICITY + " VALUES ('" +
                  localDate + "', '" +
                  localTime + "')" );
         }else{
            statement.execute( " UPDATE " + TABLE_ELECTRICITY +
                  " SET " +
                  TABLE_USAGE_COLUMN + " = '" + resultSet.getString( TABLE_USAGE_COLUMN ) + "@" + localTime + "' " +
                  " WHERE " +
                  TABLE_DAY_COLUMN + "='" + localDate + "'" );
         }
      }catch( SQLException exception ) {
         exception.printStackTrace();
      }
   }

}
