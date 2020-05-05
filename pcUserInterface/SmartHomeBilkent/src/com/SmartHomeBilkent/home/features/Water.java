package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

/**
 * a Water class
 *
 * @author İLKE DOĞAN
 * @version 29.04.2020
 */
public class Water extends Communication {

   //properties
   private final String WATER_ON = "water_valve_on#:";
   private final String WATER_OFF = "water_valve_off#:";

   //constructor
   public Water( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( WATER_ON );
      else
         arduino.serialWrite( WATER_OFF );
   }
}
