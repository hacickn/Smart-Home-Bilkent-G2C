package com.SmartHomeBilkent.extra.dataBase;

import com.SmartHomeBilkent.extra.dataBase.fields.Fish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FishSpecies {

   //properties
   private static final String TABLE_FISHES = "FISHES";
   private static final String TABLE_SPECIES_COLUMN = "SPECIES";
   private static final String TABLE_FEEDING_TIME_COLUMN = "FEEDINGTIME";
   private static final String TABLE_WATER_TIME_COLUMN = "WATERTIME";
   private static final String TABLE_AIR_MOTOR_TIME_COLUMN = "AIRMOTORTIME";
   private Connection connection;
   private static FishSpecies instance = new FishSpecies();
   private static ObservableList< Fish > fishes;

   //constructor
   private FishSpecies() {
      fishes = FXCollections.observableArrayList();
      connection = DatabaseConnection.getInstance().getConnection();
   }

   //methods
   public static FishSpecies getInstance() {
      return instance;
   }

   public ObservableList< Fish > getFishes() {
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_FISHES );

         while( resultSet.next() ) {
            fishes.add( new Fish( resultSet.getString( TABLE_SPECIES_COLUMN ),
                  resultSet.getString( TABLE_FEEDING_TIME_COLUMN ),
                  resultSet.getString( TABLE_WATER_TIME_COLUMN ),
                  resultSet.getString( TABLE_AIR_MOTOR_TIME_COLUMN ) ) );
         }
         return fishes;
      } catch( SQLException e ) {
         e.printStackTrace();
         return null;
      }
   }
}
