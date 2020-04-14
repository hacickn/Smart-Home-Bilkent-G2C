package com.SmartHomeBilkent;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User extends RecursiveTreeObject<User>  {
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

    //constructor
    public User(String name, String surname, String birthday, String gender, String userName, String password, String userType, String preferredTheme, String preferredLanguage, String enter, String text, String sound) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.birthday = new SimpleStringProperty(birthday);
        this.gender = new SimpleStringProperty(gender);
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.userType = new SimpleStringProperty(userType);
        this.preferredTheme = new SimpleStringProperty(preferredTheme);
        this.preferredLanguage = new SimpleStringProperty(preferredLanguage);
        this.enter = new SimpleStringProperty(enter);
        this.text = new SimpleStringProperty(text);
        this.sound = new SimpleStringProperty(sound);
    }

    public String getName() {
        return name.get();
    }

    public String getEnter() {
        return enter.get();
    }

    public StringProperty enterProperty() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter.set(enter);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public String getSound() {
        return sound.get();
    }

    public StringProperty soundProperty() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound.set(sound);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUserType() {
        return userType.get();
    }

    public StringProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public String getPreferredTheme() {
        return preferredTheme.get();
    }

    public StringProperty preferredThemeProperty() {
        return preferredTheme;
    }

    public void setPreferredTheme(String preferredTheme) {
        this.preferredTheme.set(preferredTheme);
    }

    public String getPreferredLanguage() {
        return preferredLanguage.get();
    }

    public StringProperty preferredLanguageProperty() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage.set(preferredLanguage);
    }
}
