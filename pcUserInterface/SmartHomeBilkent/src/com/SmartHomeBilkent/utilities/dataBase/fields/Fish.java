package com.SmartHomeBilkent.utilities.dataBase.fields;

/**
 * a Fish class
 * @author METEHAN SAÇAKÇI
 * @version 01.05.2020
 */
public class Fish {
   //properties
   private String species;
   private String airMotorWorkTime;

   //constructor

   public Fish( String species, String airMotorWorkTime ) {
      this.species = species;
      this.airMotorWorkTime = airMotorWorkTime;
   }

   //methods

   public String getSpecies() {
      return species;
   }

   public void setSpecies( String species ) {
      this.species = species;
   }

   public String getAirMotorWorkTime() {
      return airMotorWorkTime;
   }

   public void setAirMotorWorkTime( String airMotorWorkTime ) {
      this.airMotorWorkTime = airMotorWorkTime;
   }
}
