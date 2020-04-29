package com.SmartHomeBilkent.home.features;

public class GreenHouse {
   //properties
   private String[] incomingWaterTime;
   private String humidity;
   private String temperature;

   //constructor
   public GreenHouse() {

   }

   //methods
   public String getTemperature() {
      return temperature;
   }

   public String getHumidity() {
      return humidity;
   }

   public String[] getIncomingWaterTime() {
      return incomingWaterTime;
   }
}
