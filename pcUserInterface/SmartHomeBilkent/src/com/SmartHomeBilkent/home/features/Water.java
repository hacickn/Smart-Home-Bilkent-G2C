package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;

/**
 * a Water class
 * @author İLKE DOĞAN
 * @version 29.04.2020
 */
public class Water extends Communication {

   //properties
   private final String WATER_ON = "water_valve_on#:";
   private final String WATER_OFF = "water_valve_off#:";

   /**
    * it is a Water constructor
    * @param arduino
    */
   //constructor
   public Water( Arduino arduino ) {
      super( arduino );
   }

   //methods
   /**
    * it is a buzzerOpen method that get the data from the arduino and open the water valve in pc ınterface
    * that is working manually by using arduino.serialWrite
    * @param control is a boolean input parameter
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( WATER_ON );
      else
         arduino.serialWrite( WATER_OFF );
   }
}
