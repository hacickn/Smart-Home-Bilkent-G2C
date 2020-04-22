package com.SmartHomeBilkent.extra.dataBase;

import com.SmartHomeBilkent.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Users {

    //properties
    public static final String DB_NAME = "smarthome.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
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

    private Connection connection;
    private static Users instance = new Users();
    private static ObservableList<User> usersList;

    //constructor
    private Users(){
        usersList = FXCollections.observableArrayList();
    }

    //methods
    public boolean connectDatabase(){
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnectionDatabase(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList<User> getAllUsers(){
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_USERS)){
            while (resultSet.next()){
            usersList.add(new User(resultSet.getString(TABLE_NAME_COLUMN),
                    resultSet.getString(TABLE_SURNAME_COLUMN),
                    resultSet.getString(TABLE_BIRTHDAY_COLUMN),
                    resultSet.getString(TABLE_GENDER_COLUMN),
                    resultSet.getString(TABLE_USERNAME_COLUMN),
                    resultSet.getString(TABLE_PASSWORD_COLUMN),
                    resultSet.getString(TABLE_USER_TYPE_COLUMN),
                    resultSet.getString(TABLE_PREFERRED_THEME_COLUMN),
                    resultSet.getString(TABLE_PREFERRED_LANGUAGE_COLUMN),
                    resultSet.getString(TABLE_ENTER_COLUMN),
                    resultSet.getString(TABLE_TEXT_COLUMN),
                    resultSet.getString(TABLE_SOUND_COLUMN)));
            }
            return usersList;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO " +
                TABLE_USERS + " VALUES ('"  +
                user.getName()              + "', '" +
                user.getSurname()           +  "', '" +
                user.getBirthday()          +  "', '" +
                user.getGender()            +  "', '" +
                user.getUserName()          +  "', '" +
                user.getPassword()          +  "', '" +
                user.getUserType()          +  "', '" +
                user.getPreferredTheme()    +  "', '" +
                user.getPreferredLanguage() +  "', '" +
                user.getEnter()             +  "', '" +
                user.getText()              +  "', '" +
                user.getSound()             + "')" );
        usersList.add(user);
    }

    public void removeUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(" DELETE FROM " + TABLE_USERS + " WHERE " +
                TABLE_USERNAME_COLUMN + "='" + user.getUserName() + "' AND " +
                TABLE_PASSWORD_COLUMN + "='" + user.getPassword() + "'");
        usersList.remove(user);
    }


    public void updateUser(User previous, User updated) throws SQLException {
        removeUser(previous);
        addUser(updated);
    }

    public ObservableList<User> getUserList(){
        return usersList;
    }

    public static Users getInstance(){
        return instance;
    }

    public int getParentNumber(){
        int number;
        number = 0;
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_USERS + " WHERE " +
                    TABLE_USER_TYPE_COLUMN + "='" + "PARENT'" )){
            while (resultSet.next()){
                number++;
            }
            return number;
        }catch (SQLException e){
            return 0;
        }
    }
}
