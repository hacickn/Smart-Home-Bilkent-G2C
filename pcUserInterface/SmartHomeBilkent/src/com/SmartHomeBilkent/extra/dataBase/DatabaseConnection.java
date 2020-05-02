package com.SmartHomeBilkent.extra.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

   //properties
   public static final String DB_NAME = "smarthome.db";
   public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
   private static DatabaseConnection instance = new DatabaseConnection();
   private Connection connection;


   //constructor
   private DatabaseConnection () {

   }

   //methods
   /**
    * it is a connectDatabase method that get connection between database and program
    * @return result as a boolean
    */
   public boolean connectDatabase() {
      try {
         connection = DriverManager.getConnection(CONNECTION_STRING);
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   /**
    * it is a closeConnectionDatabase method that close the connection
    */
   public void closeConnectionDatabase() {
      try {
         if (connection != null) {
            connection.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public Connection getConnection() {
      return connection;
   }

   public static DatabaseConnection getInstance() {
      return instance;
   }
}
