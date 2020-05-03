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
   private boolean check;

   //constructor
   public GardenLight( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive() {
      return check;
   }

   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GARDEN_LIGHTS_ON );
      else
         arduino.serialWrite( GARDEN_LIGHTS_OFF );
      check = control;
   }
}
