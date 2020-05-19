package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;

/**
 * a GardenLight class
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class GardenLight extends Communication {

   //properties
   private final String GARDEN_LIGHTS_ON = "garden_lights_on#:";
   private final String GARDEN_LIGHTS_OFF = "garden_lights_off#:";

   //constructor

   /**
    * This constructor method basically create new garden light with using ardinuo parameter.
    * @param arduino
    */
   public GardenLight( Arduino arduino ) {
      super( arduino );
   }

   //methods
   /**
    * This method is responsible  to send open or close command to the arduino.
    * @param control is a boolean input parameter
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GARDEN_LIGHTS_ON );
      else
         arduino.serialWrite( GARDEN_LIGHTS_OFF );
   }
}
