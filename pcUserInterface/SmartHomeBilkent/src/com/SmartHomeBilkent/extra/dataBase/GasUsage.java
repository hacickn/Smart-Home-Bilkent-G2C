package com.SmartHomeBilkent.extra.dataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GasUsage {

   //properties
   private static final String TABLE_GAS = "ELECTRICITY";
   private static final String TABLE_USAGE_COLUMN = "USAGE";
   private Connection connection;
   private static GasUsage instance = new GasUsage();
   private static ObservableList<String> usageList;

   //constructor
   private GasUsage() {
      usageList = FXCollections.observableArrayList();
      connection = DatabaseConnection.getInstance().getConnection();
   }

   //methods
   public static GasUsage getInstance() {
      return instance;
   }

   public ObservableList<String> getGasUsage() {
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_GAS );

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
