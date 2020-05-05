package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

/**
 * a GardenLight class
 *
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class GardenLight extends Communication {

   //properties
   private final String GARDEN_LIGHTS_ON = "garden_lights_on#:";
   private final String GARDEN_LIGHTS_OFF = "garden_lights_off#:";

   //constructor
   public GardenLight( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GARDEN_LIGHTS_ON );
      else
         arduino.serialWrite( GARDEN_LIGHTS_OFF );
   }
}
