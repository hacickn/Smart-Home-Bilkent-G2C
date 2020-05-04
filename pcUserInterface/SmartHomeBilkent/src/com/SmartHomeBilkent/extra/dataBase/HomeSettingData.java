package com.SmartHomeBilkent.extra.dataBase;


import com.SmartHomeBilkent.extra.dataBase.fields.HomeSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HomeSettingData {

   //properties
   private static final String TABLE_HOME_SETTINGS = "HOME";
   private static final String TABLE_HOME_ID = "ID";
   private static final String TABLE_SENSOR_SETTINGS = "SENSORS";
   private static final String TABLE_PERMISSION_SETTING = "PERMISSIONS";
   private static HomeSettingData instance = new HomeSettingData();
   private static ObservableList< HomeSettings > homeList;
   private Connection connection;

   //constructor
   private HomeSettingData() {
      connection = DatabaseConnection.getInstance().getConnection();
   }

   //methods
   public static HomeSettingData getInstance() {
      return instance;
   }

   public ObservableList< HomeSettings > getAllHome() {
      homeList = FXCollections.observableArrayList();
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_HOME_SETTINGS );

         while( resultSet.next() ) {
            homeList.add( new HomeSettings(
                  resultSet.getString( TABLE_HOME_ID ),
                  resultSet.getString( TABLE_SENSOR_SETTINGS ),
                  resultSet.getString( TABLE_PERMISSION_SETTING ) ) );
         }
         return homeList;
      } catch( SQLException e ) {
         e.printStackTrace();
         return null;
      }
   }

   public String[] getSensors( HomeSettings homeSettings ) {
      return homeSettings.getSensors().split( "@" );
   }

   public String[] getPermission( HomeSettings homeSettings ) {
      return homeSettings.getPermission().split( "@" );
   }

   public void updateSensors( HomeSettings homeSettings, String[] sensor ) throws SQLException {
      String message;
      Statement statement;
      message = sensor[ 0 ];

      for( int k = 1; k < sensor.length; k++ )
         message = message + "@" + sensor[ k ];

      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_HOME_SETTINGS +
            " SET " +
            TABLE_SENSOR_SETTINGS + " = '" + message + "' " +
            " WHERE " +
            TABLE_HOME_ID + "='" + homeSettings.getHomeID() + "'" );
      homeList.get( homeList.indexOf( homeSettings ) ).setSensors( message );
   }

   public void updatePermission( HomeSettings homeSettings, String[] permission ) throws SQLException {
      String message;
      Statement statement;
      message = permission[ 0 ];

      for( int k = 1; k < permission.length; k++ )
         message = message + "@" + permission[ k ];

      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_HOME_SETTINGS +
            " SET " +
            TABLE_PERMISSION_SETTING + " = '" + message + "' " +
            " WHERE " +
            TABLE_HOME_ID + "='" + homeSettings.getHomeID() + "'" );
      homeList.get( homeList.indexOf( homeSettings ) ).setPermission( message );
   }

   public ObservableList< HomeSettings > getHomeList() {
      return homeList;
   }
}
