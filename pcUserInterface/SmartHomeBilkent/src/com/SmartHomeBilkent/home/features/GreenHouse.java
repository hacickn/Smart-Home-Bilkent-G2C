package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;

/**
 * a GreenHouse class
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
   /**
    * it is a GreenHouse constructor
    * @param arduino
   */
   public GreenHouse( Arduino arduino ) {
      super( arduino );
   }

   //methods
   /**
    * it is a Aquarium constructor
    * @param control
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GREEN_HOUSE_VALVE_ON );
      else
         arduino.serialWrite( GREEN_HOUSE_VALVE_OFF );
   }
   /**
    * it is a getTemperature() method that is used for getting temperature data
    * @return result as a temperature
    */
   public String getTemperature() {
      return temperature;
   }
   /**
    * it is a getHumidity() method that is used for getting humidity data
    * @return result as a humidity
    */
   public String getHumidity() {
      return humidity;
   }
   /**
    * it is a getHumidity() method that is used for getting time regarding incomingWaterTime
    * @return result as a incomingWaterTime
    */
   public String[] getIncomingWaterTime() {
      return incomingWaterTime;
   }
}
