package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.extra.connection.Arduino;

/**
 * a GreenHouse class
 *
 * @author İLKE DOĞAN
 * @version 29.04.2020
 */
public class GreenHouse extends Communication {

   //properties
   private final String GREEN_HOUSE_VALVE_ON = "soil_moisture_valf_on#:";
   private final String GREEN_HOUSE_VALVE_OFF = "soil_moisture_valf_off#:";
   private String[] incomingWaterTime;
   private String humidity;
   private String temperature;

   //constructor
   public GreenHouse( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GREEN_HOUSE_VALVE_ON );
      else
         arduino.serialWrite( GREEN_HOUSE_VALVE_OFF );
   }

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
