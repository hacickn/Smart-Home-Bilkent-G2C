package com.SmartHomeBilkent.extra.dataBase.fields;

import java.time.LocalDate;

public class GreenHouseValues {

   //properties
   private LocalDate localDate;
   private String temperature;
   private String humidity;

   //constructor
   public GreenHouseValues( LocalDate localDate, String temperature, String humidity ) {
      this.localDate = localDate;
      this.temperature = temperature;
      this.humidity = humidity;
   }

   //methods

   public LocalDate getLocalDate() {
      return localDate;
   }

   public void setLocalDate( LocalDate localDate ) {
      this.localDate = localDate;
   }

   public String getTemperature() {
      return temperature;
   }

   public void setTemperature( String temperature ) {
      this.temperature = temperature;
   }

   public String getHumidity() {
      return humidity;
   }

   public void setHumidity( String humidity ) {
      this.humidity = humidity;
   }
}
