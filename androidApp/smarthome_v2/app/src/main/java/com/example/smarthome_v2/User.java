package com.example.smarthome_v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class User {

    String name;
    String email;
    String password;
    String aquarium;
    static HashMap<String, String> information;

    public User(String name, String email, String password, String aquarium ) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.aquarium = aquarium;

        setInformation( name, email, password, aquarium );

    }

    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.aquarium = "Off";


        information = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void addToInformation( String key, String value ) {
        information.put( key, value );
    }

    public static void setInformation(String name, String email, String password, String aquarium ) {
        information = new HashMap<>();
        information.put( "name", name );
        information.put( "email", email );
        information.put( "password", password );
        information.put( "aquarium", aquarium );
    }

    public static List<HashMap<String, String>> getInformation() {

        return Arrays.asList( information );

    }

}
