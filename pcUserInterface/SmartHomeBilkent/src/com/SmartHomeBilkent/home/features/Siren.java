package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;

/**
 * a Siren class
 * @author İLKE DOĞAN
 * @version 29.04.2020
 */
public class Siren extends Communication {

   //properties
   private final String SIREN_ON = "external_siren_on#:";
   private final String SIREN_OFF = "external_siren_off#:";
   private final String BUZZER_ON = "buzzer_on#:";
   private final String BUZZER_OFF = "buzzer_off#:";
   /**
    * it is a GreenHouse constructor
    * @param arduino
    */
   //constructor
   public Siren( Arduino arduino ) {
      super( arduino );
   }

   //methods
   /**
    * it is a buzzerOpen method that get the data from the arduino and open the siren Mode in pc ınterface
    * that is working if there is security compromise manually by using arduino.serialWrite
    * @param control is a boolean input parameter
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( SIREN_ON );
      else
         arduino.serialWrite( SIREN_OFF );
   }
   /**
    * it is a buzzerOpen method that get the data from the arduino and open the buzzer Mode in pc ınterface
    * that is working if there is security compromise manually by using arduino.serialWrite
    * @param control is a boolean input parameter
    */
   public void buzzerOpen( boolean control ) {
      if( control )
         arduino.serialWrite( BUZZER_ON );
      else
         arduino.serialWrite( BUZZER_OFF );
   }

}
