package com.SmartHomeBilkent.utilities.dataBase.fields;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * a User class that extended by RecursiveTreeObject<User>
 * @author Hacı Çakın
 * @version 28.04.2020
 */
public class User extends RecursiveTreeObject< User > {

   //properties
   private StringProperty name;
   private StringProperty surname;
   private StringProperty birthday;
   private StringProperty gender;
   private StringProperty userName;
   private StringProperty password;
   private StringProperty userType;
   private StringProperty preferredTheme;
   private StringProperty preferredLanguage;
   private StringProperty enter;
   private StringProperty text;
   private StringProperty sound;
   private StringProperty location;

   //constructor

   /**
    * it is a User constructor
    * @param name              is a String input parameter
    * @param surname           is a String input parameter
    * @param birthday          is a String input parameter
    * @param gender            is a String input parameter
    * @param userName          is a String input parameter
    * @param password          is a String input parameter
    * @param userType          is a String input parameter
    * @param preferredTheme    is a String input parameter
    * @param preferredLanguage is a String input parameter
    * @param enter             is a String input parameter
    * @param text              is a String input parameter
    * @param sound             is a String input parameter
    * @param location          is a String input parameter
    */
   public User( String name, String surname, String birthday, String gender, String userName, String password, String userType, String preferredTheme, String preferredLanguage, String enter, String text, String sound, String location ) {
      this.name = new SimpleStringProperty( name );
      this.surname = new SimpleStringProperty( surname );
      this.birthday = new SimpleStringProperty( birthday );
      this.gender = new SimpleStringProperty( gender );
      this.userName = new SimpleStringProperty( userName );
      this.password = new SimpleStringProperty( password );
      this.userType = new SimpleStringProperty( userType );
      this.preferredTheme = new SimpleStringProperty( preferredTheme );
      this.preferredLanguage = new SimpleStringProperty( preferredLanguage );
      this.enter = new SimpleStringProperty( enter );
      this.text = new SimpleStringProperty( text );
      this.sound = new SimpleStringProperty( sound );
      this.location = new SimpleStringProperty( location );
   }

   /**
    * it is a getName method
    * @return result as a String
    */
   public String getName() {
      return name.get();
   }

   /**
    * it is a getEnter method
    * @return result as a String
    */
   public String getEnter() {
      return enter.get();
   }

   /**
    * it is a setEnter method
    * @param enter is a String input parameter
    */
   public void setEnter( String enter ) {
      this.enter.set( enter );
   }

   /**
    * it is a nameProperty method
    * @return result as a StringProperty
    */
   public StringProperty nameProperty() {
      return name;
   }

   /**
    * it is a setName method
    * @param name is a String input parameter
    */
   public void setName( String name ) {
      this.name.set( name );
   }

   /**
    * it is a getSurname method
    * @return result as a String
    */
   public String getSurname() {
      return surname.get();
   }

   /**
    * it is a surnameProperty method
    * @return result as a StringProperty
    */
   public StringProperty surnameProperty() {
      return surname;
   }

   /**
    * it is a setSurname method
    * @param surname is a String input parameter
    */
   public void setSurname( String surname ) {
      this.surname.set( surname );
   }

   /**
    * it is a getBirthday method
    * @return result as a String
    */
   public String getBirthday() {
      return birthday.get();
   }

   /**
    * it is a birthdayProperty method
    * @return result as a StringProperty
    */
   public StringProperty birthdayProperty() {
      return birthday;
   }

   /**
    * it is a setBirthday method
    * @param birthday is a String input parameter
    */
   public void setBirthday( String birthday ) {
      this.birthday.set( birthday );
   }

   /**
    * it is a getGender method
    * @return result as a String
    */
   public String getGender() {
      return gender.get();
   }

   /**
    * it is a genderProperty method
    * @return result as a StringProperty
    */
   public StringProperty genderProperty() {
      return gender;
   }

   /**
    * it is a setGender method
    * @param gender is a String input parameter
    */
   public void setGender( String gender ) {
      this.gender.set( gender );
   }

   /**
    * it is a getText method
    * @return result as a String
    */
   public String getText() {
      return text.get();
   }

   /**
    * it is a setText method
    * @param text is a String input parameter
    */
   public void setText( String text ) {
      this.text.set( text );
   }

   /**
    * it is a getSound method
    * @return result as a String
    */
   public String getSound() {
      return sound.get();
   }

   /**
    * it is a setSound method
    * @param sound is a String input parameter
    */
   public void setSound( String sound ) {
      this.sound.set( sound );
   }

   /**
    * it is a getUserName method
    * @return result as a String
    */
   public String getUserName() {
      return userName.get();
   }

   /**
    * it is a userNameProperty method
    * @return result as a StringProperty
    */
   public StringProperty userNameProperty() {
      return userName;
   }

   /**
    * it is a setUserName method
    * @param userName is a String input parameter
    */
   public void setUserName( String userName ) {
      this.userName.set( userName );
   }

   /**
    * it is a getPassword method
    * @return result as a String
    */
   public String getPassword() {
      return password.get();
   }

   /**
    * it is a setPassword method
    * @param password is a String input parameter
    */
   public void setPassword( String password ) {
      this.password.set( password );
   }

   /**
    * it is a getUserType method
    * @return result as a String
    */
   public String getUserType() {
      return userType.get();
   }

   /**
    * it is a getPreferredTheme method
    * @return result as a String
    */
   public String getPreferredTheme() {
      return preferredTheme.get();
   }

   /**
    * it is a preferredThemeProperty method
    * @return result as a StringProperty
    */
   public StringProperty preferredThemeProperty() {
      return preferredTheme;
   }

   /**
    * it is a setPreferredTheme method
    * @param preferredTheme is a String input parameter
    */
   public void setPreferredTheme( String preferredTheme ) {
      this.preferredTheme.set( preferredTheme );
   }

   /**
    * it is a getPreferredLanguage method
    * @return result as a String
    */
   public String getPreferredLanguage() {
      return preferredLanguage.get();
   }

   /**
    * it is a preferredLanguageProperty method
    * @return result as a StringProperty
    */
   public StringProperty preferredLanguageProperty() {
      return preferredLanguage;
   }

   /**
    * it is a setPreferredLanguage method
    * @param preferredLanguage is a String input parameter
    */
   public void setPreferredLanguage( String preferredLanguage ) {
      this.preferredLanguage.set( preferredLanguage );
   }

   /**
    * it is a getLocation method
    * @return result as a String
    */
   public String getLocation() {
      return location.get();
   }

   /**
    * it is a setLocation method
    * @param location is a String input parameter
    */
   public void setLocation( String location ) {
      this.location.set( location );
   }
}
