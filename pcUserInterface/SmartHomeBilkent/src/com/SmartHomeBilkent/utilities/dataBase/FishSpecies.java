package com.SmartHomeBilkent.utilities.dataBase;

import com.SmartHomeBilkent.utilities.dataBase.fields.CommonSetting;
import com.SmartHomeBilkent.utilities.dataBase.fields.Fish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.control.CheckComboBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * a FishSpecies class
 * @author METEHAN SAÇAKÇI
 * @version 01.05.2020
 */
public class FishSpecies {

   //properties
   private static final String TABLE_FISHES = "FISHES";
   private static final String TABLE_SPECIES_COLUMN = "SPECIES";
   private static final String TABLE_AIR_MOTOR_TIME_COLUMN = "AIRMOTORTIME";
   private Connection connection;
   private static FishSpecies instance = new FishSpecies();
   private static ObservableList< Fish > fishes;

   //constructor
   private FishSpecies() {
      connection = DatabaseConnection.getInstance().getConnection();
   }

   //methods
   public static FishSpecies getInstance() {
      return instance;
   }

   public ObservableList< Fish > getFishes() {
      fishes = FXCollections.observableArrayList();
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_FISHES );

         while( resultSet.next() ) {
            fishes.add( new Fish( resultSet.getString( TABLE_SPECIES_COLUMN ),
                  resultSet.getString( TABLE_AIR_MOTOR_TIME_COLUMN ) ) );
         }
         return fishes;
      } catch( SQLException e ) {
         e.printStackTrace();
         return null;
      }
   }

   public void addFishToComboBox( CheckComboBox< String > comboBox ) {
      for( int k = 0; k < fishes.size(); k++ )
         comboBox.getItems().add( fishes.get( k ).getSpecies() );
   }
   public int getFishesAirMotorRunTime (CommonSetting commonSetting) {
      String[] flowList;
      flowList = CommonSettingData.getInstance().getSelectedFishes(commonSetting);
      return 0;
   }
}
