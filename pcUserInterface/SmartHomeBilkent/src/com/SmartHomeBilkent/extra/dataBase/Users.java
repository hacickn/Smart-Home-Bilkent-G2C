package com.SmartHomeBilkent.extra.dataBase;

import com.SmartHomeBilkent.extra.dataBase.fields.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * a Users class
 *
 * @author Hacı Çakın
 * @version 21.03.2020
 */
public class Users {

   //properties
   public static final String TABLE_USERS = "USERS";
   public static final String TABLE_NAME_COLUMN = "NAME";
   public static final String TABLE_SURNAME_COLUMN = "SURNAME";
   public static final String TABLE_BIRTHDAY_COLUMN = "BIRTHDAY";
   public static final String TABLE_GENDER_COLUMN = "GENDER";
   public static final String TABLE_USERNAME_COLUMN = "USERNAME";
   public static final String TABLE_PASSWORD_COLUMN = "PASSWORD";
   public static final String TABLE_USER_TYPE_COLUMN = "USERTYPE";
   public static final String TABLE_PREFERRED_THEME_COLUMN = "PREFERREDTHEME";
   public static final String TABLE_PREFERRED_LANGUAGE_COLUMN = "PREFERREDLANGUAGE";
   public static final String TABLE_ENTER_COLUMN = "ENTER";
   public static final String TABLE_TEXT_COLUMN = "TEXT";
   public static final String TABLE_SOUND_COLUMN = "SOUND";
   public static final String TABLE_LOCATION_COLUMN = "LOCATION";
   private Connection connection;
   private static Users instance = new Users();
   private static ObservableList< User > usersList;

   //constructor

   /**
    * it is a Users constructor
    */
   private Users() {
      connection = DatabaseConnection.getInstance().getConnection();
   }

   //methods

   /**
    * it is a getAllUsers method that get all user from the USERS table
    *
    * @return result as a ObservableList<User>
    */
   public ObservableList< User > getAllUsers() {
      usersList = FXCollections.observableArrayList();
      try {
         Statement statement;
         ResultSet resultSet;

         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_USERS );

         while( resultSet.next() ) {
            usersList.add( new User( resultSet.getString( TABLE_NAME_COLUMN ),
                  resultSet.getString( TABLE_SURNAME_COLUMN ),
                  resultSet.getString( TABLE_BIRTHDAY_COLUMN ),
                  resultSet.getString( TABLE_GENDER_COLUMN ),
                  resultSet.getString( TABLE_USERNAME_COLUMN ),
                  resultSet.getString( TABLE_PASSWORD_COLUMN ),
                  resultSet.getString( TABLE_USER_TYPE_COLUMN ),
                  resultSet.getString( TABLE_PREFERRED_THEME_COLUMN ),
                  resultSet.getString( TABLE_PREFERRED_LANGUAGE_COLUMN ),
                  resultSet.getString( TABLE_ENTER_COLUMN ),
                  resultSet.getString( TABLE_TEXT_COLUMN ),
                  resultSet.getString( TABLE_SOUND_COLUMN ),
                  resultSet.getString( TABLE_LOCATION_COLUMN ) ) );
         }
         return usersList;
      } catch( SQLException e ) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * it is a addUser method that add new user
    *
    * @param user is a User input parameter
    * @return result as a String
    */
   public void addUser( User user ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( "INSERT INTO " +
            TABLE_USERS + " VALUES ('" +
            user.getName() + "', '" +
            user.getSurname() + "', '" +
            user.getBirthday() + "', '" +
            user.getGender() + "', '" +
            user.getUserName() + "', '" +
            user.getPassword() + "', '" +
            user.getUserType() + "', '" +
            user.getPreferredTheme() + "', '" +
            user.getPreferredLanguage() + "', '" +
            user.getEnter() + "', '" +
            user.getText() + "', '" +
            user.getSound() + "', '" +
            user.getLocation() + "')" );
      usersList.add( user );
   }

   /**
    * it is a removeUser method that remove user
    *
    * @param user is a User input parameter
    */
   public void removeUser( User user ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " DELETE FROM " + TABLE_USERS + " WHERE " +
            TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
            TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'" );
      usersList.remove( user );
   }

   /**
    * it is a getUserList method that give user list
    *
    * @return result as a ObservableList<User>
    */
   public ObservableList< User > getUserList() {
      return usersList;
   }

   /**
    * it is a getInstance method
    *
    * @return result as a Users
    */
   public static Users getInstance() {
      return instance;
   }

   /**
    * it is a getParentNumber method give parent number
    *
    * @return result as a int
    */
   public int getParentNumber() {
      int number;
      number = 0;
      Statement statement;
      ResultSet resultSet;

      try {
         statement = connection.createStatement();
         resultSet = statement.executeQuery( " SELECT * FROM " + TABLE_USERS + " WHERE " + TABLE_USER_TYPE_COLUMN + "='" + "PARENT'" );

         while( resultSet.next() ) {
            number++;
         }
         return number;
      } catch( SQLException e ) {
         return 0;
      }
   }

   /**
    * it is a updateUserNormalInfo method update user general information
    *
    * @param user     is a String input parameter
    * @param surname  is a String input parameter
    * @param birthday is a String input parameter
    * @param gender   is a String input parameter
    */
   public void updateUserNormalInfo( User user, String name, String surname, String birthday, String gender ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_USERS +
            " SET " +
            TABLE_NAME_COLUMN + " = '" + name + "' , " +
            TABLE_SURNAME_COLUMN + " = '" + surname + "' , " +
            TABLE_BIRTHDAY_COLUMN + " = '" + birthday + "' , " +
            TABLE_GENDER_COLUMN + " = '" + gender + "' " +
            " WHERE " +
            TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
            TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'" );

      user.setName( name );
      user.setSurname( surname );
      user.setBirthday( birthday );
      user.setGender( gender );
   }

   /**
    * it is a updatePrivateInfo method update user private information
    *
    * @param user     is a String input parameter
    * @param username is a String input parameter
    * @param password is a String input parameter
    */
   public void updatePrivateInfo( User user, String username, String password ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_USERS +
            " SET " +
            TABLE_USERNAME_COLUMN + " = '" + username + "' , " +
            TABLE_PASSWORD_COLUMN + " = '" + password + "' " +
            " WHERE " +
            TABLE_NAME_COLUMN + "='" + user.getName() + "' AND " +
            TABLE_BIRTHDAY_COLUMN + "='" + user.getBirthday() + "' AND " +
            TABLE_SURNAME_COLUMN + "='" + user.getSurname() + "'" );
      user.setUserName( username );
      user.setPassword( password );
   }

   /**
    * it is a updateUsersTheme method update user theme information
    *
    * @param user  is a String input parameter
    * @param theme is a String input parameter
    */
   public void updateUsersTheme( User user, String theme ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_USERS +
            " SET " +
            TABLE_PREFERRED_THEME_COLUMN + " = '" + theme + "' " +
            " WHERE " +
            TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
            TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'" );
      user.setPreferredTheme( theme );
   }

   /**
    * it is a updateUserNormalInfo method update user language information
    *
    * @param user     is a String input parameter
    * @param language is a String input parameter
    */
   public void updateLanguage( User user, String language ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_USERS +
            " SET " +
            TABLE_PREFERRED_LANGUAGE_COLUMN + " = '" + language + "' " +
            " WHERE " +
            TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
            TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'" );
      user.setPreferredLanguage( language );
   }

   /**
    * it is a updateVolume method update user volume-sound information
    *
    * @param user  is a String input parameter
    * @param sound is a String input parameter
    */
   public void updateVolume( User user, String sound ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_USERS +
            " SET " +
            TABLE_SOUND_COLUMN + " = '" + sound + "' " +
            " WHERE " +
            TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
            TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'" );
      user.setSound( sound );
   }

   /**
    * it is a updateText method update user text information
    *
    * @param user is a String input parameter
    * @param text is a String input parameter
    */
   public void updateText( User user, String text ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_USERS +
            " SET " +
            TABLE_TEXT_COLUMN + " = '" + text + "' " +
            " WHERE " +
            TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
            TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'" );
      user.setText( text );
   }

   public void updateLocation( User user, String location ) throws SQLException {
      Statement statement;
      statement = connection.createStatement();
      statement.execute( " UPDATE " + TABLE_USERS +
            " SET " +
            TABLE_LOCATION_COLUMN + " = '" + location + "' " +
            " WHERE " +
            TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
            TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'" );
      user.setLocation( location );
   }
}
