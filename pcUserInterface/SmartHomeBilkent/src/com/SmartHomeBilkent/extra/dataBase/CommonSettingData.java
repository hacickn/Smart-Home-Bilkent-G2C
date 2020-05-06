package com.SmartHomeBilkent.extra.dataBase;

import com.SmartHomeBilkent.extra.dataBase.fields.CommonSetting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * it is a CommonSettingData class
 * this class provide settings from databases
 *
 * @author Hacı Çakın
 * @version 05.05.2020
 */
public class CommonSettingData {

   //properties
   private static final String TABLE_HOME_SETTINGS = "HOME";
   private static final String TABLE_HOME_ID = "ID";
   private static final String TABLE_SENSOR_SETTINGS = "SENSORS";
   private static final String TABLE_PERMISSION_SETTING = "PERMISSIONS";
   private static final String TABLE_AQUARIUM_SETTING= "AQUARIUM";
   private static final String TABLE_FISH_SPECIES= "FISHES";
   private static CommonSettingData instance = new CommonSettingData();
   private static ObservableList< CommonSetting > homeList;
   private Connection connection;
   private Statement statement;

   //constructor

   /**
    * it is a CommonSettingData constructor
    */
   private CommonSettingData() {
      connection = DatabaseConnection.getInstance().getConnection();
   }

   //methods

   /**
    * it is a getInstance method that provide the access to CommonSettingData
    *
    * @return result as a CommonSettingData
    */
   public static CommonSettingData getInstance() {
      return instance;
   }

   /**
    * it is a getAllHome method that get all data from database
    * and put it in ObservableList
    *
    * @return result as a ObservableList< CommonSetting >
    */
   public ObservableList< CommonSetting > getAllHome() {
      homeList = FXCollections.observableArrayList();
      try {
         ResultSet resultSet;
         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_HOME_SETTINGS );

         while( resultSet.next() ) {
            homeList.add( new CommonSetting(
                  resultSet.getString( TABLE_HOME_ID ),
                  resultSet.getString( TABLE_SENSOR_SETTINGS ),
                  resultSet.getString( TABLE_PERMISSION_SETTING ),
                  resultSet.getString( TABLE_AQUARIUM_SETTING ),
                  resultSet.getString( TABLE_FISH_SPECIES )) );
         }
         return homeList;
      } catch( SQLException e ) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * it is a getSensors method
    *
    * @param commonSetting is a CommonSetting input parameter
    * @return result as a String[]
    */
   public String[] getSensors( CommonSetting commonSetting ) {
      return commonSetting.getSensors().split( "@" );
   }

   /**
    * it is a getPermission method
    *
    * @param commonSetting is a CommonSetting input parameter
    * @return result as a String[]
    */
   public String[] getPermission( CommonSetting commonSetting ) {
      return commonSetting.getPermission().split( "@" );
   }

   /**
    * it is a updateSensors method
    *
    * @param commonSetting is a CommonSetting input parameter
    * @param sensor        is a String[] input parameter
    */
   public void updateSensors( CommonSetting commonSetting, String[] sensor ) throws SQLException {
      String message;
      message = sensor[ 0 ];

      for( int k = 1; k < sensor.length; k++ )
         message = message + "@" + sensor[ k ];

      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_HOME_SETTINGS +
            " SET " +
            TABLE_SENSOR_SETTINGS + " = '" + message + "' " +
            " WHERE " +
            TABLE_HOME_ID + "='" + commonSetting.getHomeID() + "'" );
      homeList.get( homeList.indexOf( commonSetting ) ).setSensors( message );
   }

   /**
    * it is a updatePermission method
    *
    * @param commonSetting is a CommonSetting input parameter
    * @param permission    is a String[] input parameter
    */
   public void updatePermission( CommonSetting commonSetting, String[] permission ) throws SQLException {
      String message;
      message = permission[ 0 ];

      for( int k = 1; k < permission.length; k++ )
         message = message + "@" + permission[ k ];

      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_HOME_SETTINGS +
            " SET " +
            TABLE_PERMISSION_SETTING + " = '" + message + "' " +
            " WHERE " +
            TABLE_HOME_ID + "='" + commonSetting.getHomeID() + "'" );
      homeList.get( homeList.indexOf( commonSetting ) ).setPermission( message );
   }

   /**
    * it is a getHomeList method
    *
    * @return result as a bservableList< CommonSetting >
    */
   public ObservableList< CommonSetting > getHomeList() {
      return homeList;
   }

   public String[] getSelectedFishes( CommonSetting commonSetting ) {
      return commonSetting.getFishSpecies().split( "@" );
   }


   public String[] getAquariumSettings( CommonSetting commonSetting ){
      return commonSetting.getAquariumSettings().split( "@" );
   }

   public void updateSelectedFishes( CommonSetting commonSetting, String[] fishList ) throws SQLException {
      String message;
      statement = connection.createStatement();
      message = "";

      if( fishList.length > 0 )
         message = fishList[0];

      for( int k = 1; k < fishList.length; k++ )
         message = "@" + fishList[k];

      statement.execute( " UPDATE " + TABLE_HOME_SETTINGS +
            " SET " +
            TABLE_FISH_SPECIES + " = '" + message + "' " +
            " WHERE " +
            TABLE_HOME_ID + "='" + commonSetting.getHomeID() + "'" );
   }

   public void updateAquariumSettings(  CommonSetting commonSetting, String aquariumSetting ) throws SQLException {
      statement = connection.createStatement();

      statement.execute( " UPDATE " + TABLE_HOME_SETTINGS +
            " SET " +
            TABLE_AQUARIUM_SETTING + " = '" + aquariumSetting + "' " +
            " WHERE " +
            TABLE_HOME_ID + "='" + commonSetting.getHomeID() + "'" );
   }
}
