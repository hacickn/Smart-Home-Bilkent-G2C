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
import java.time.format.DateTimeFormatter;

public class GasUsage {

   //properties
   private static final String TABLE_GAS = "GAS";
   private static final String TABLE_DAY_COLUMN = "DAY";
   private static final String TABLE_USAGE_COLUMN = "USAGE";

   private static GasUsage instance = new GasUsage();
   private static ObservableList< Integer > daysOfUsage;
   private static ObservableList< Usage > usageList;

   private Connection connection;
   private DateTimeFormatter dateTimeFormatter;
   private String[] detailUsage;

   //constructor
   private GasUsage() {
      usageList = FXCollections.observableArrayList();
      daysOfUsage = FXCollections.observableArrayList();
      connection = DatabaseConnection.getInstance().getConnection();
      dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy.MM.dd" );
   }

   //methods
   public static GasUsage getInstance() {
      return instance;
   }

   public ObservableList< Usage > getGasUsage() {
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_GAS );

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
      hours = 0;
      for( Usage usage : usageList ) {
         hours = 0;
         detailUsage = usage.getActivity().split( "@" );
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

            } else if( detailUsage[ k ].charAt( 0 ) == 'C' && k < detailUsage.length - 1 ) {
               hours = hours + ( ( ( Integer.parseInt( detailUsage[ k ].substring( 1, 3 ) ) * 60 ) +
                     ( Integer.parseInt( detailUsage[ k ].substring( 4 ) ) ) ) / 60 );
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
}
