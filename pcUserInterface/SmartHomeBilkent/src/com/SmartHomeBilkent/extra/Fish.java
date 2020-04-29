package com.SmartHomeBilkent.extra;

public class Fish {
   //properties
   private String species;
   private String feedingTime;
   private String waterExchangeTime;
   private String airMotorWorkTime;

   //constructor

   public Fish(String species, String feedingTime, String waterExchangeTime, String airMotorWorkTime) {
      this.species = species;
      this.feedingTime = feedingTime;
      this.waterExchangeTime = waterExchangeTime;
      this.airMotorWorkTime = airMotorWorkTime;
   }

   //methods

   public String getSpecies() {
      return species;
   }

   public void setSpecies( String species ) {
      this.species = species;
   }

   public String getFeedingTime() {
      return feedingTime;
   }

   public void setFeedingTime( String feedingTime ) {
      this.feedingTime = feedingTime;
   }

   public String getWaterExchangeTime() {
      return waterExchangeTime;
   }

   public void setWaterExchangeTime( String waterExchangeTime ) {
      this.waterExchangeTime = waterExchangeTime;
   }

   public String getAirMotorWorkTime() {
      return airMotorWorkTime;
   }

   public void setAirMotorWorkTime( String airMotorWorkTime ) {
      this.airMotorWorkTime = airMotorWorkTime;
   }
}
