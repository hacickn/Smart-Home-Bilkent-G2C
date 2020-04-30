package com.SmartHomeBilkent.extra.dataBase;

import com.SmartHomeBilkent.extra.Fish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElectricityUsage {

   //properties
   private static final String TABLE_ELECTRICITY = "ELECTRICITY";
   private static final String TABLE_USAGE_COLUMN = "USAGE";
   private Connection connection;
   private static ElectricityUsage instance = new ElectricityUsage();
   private static ObservableList<String> usageList;

   //constructor
   private ElectricityUsage() {
      usageList = FXCollections.observableArrayList();
      connection = DatabaseConnection.getInstance().getConnection();
   }

   //methods
   public static ElectricityUsage getInstance() {
      return instance;
   }

   public ObservableList<String> getElectricityUsage() {
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_ELECTRICITY );

         while (resultSet.next()) {
            usageList.add(resultSet.getString( TABLE_USAGE_COLUMN ));
         }
         return usageList;
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }

}
