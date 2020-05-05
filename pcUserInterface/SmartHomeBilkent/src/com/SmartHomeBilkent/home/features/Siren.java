package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.extra.connection.Arduino;

/**
 * a Siren class
 *
 * @author İLKE DOĞAN
 * @version 29.04.2020
 */
public class Siren extends Communication {

   //properties
   private final String SIREN_ON = "external_siren_on#:";
   private final String SIREN_OFF = "external_siren_off#:";
   private final String BUZZER_ON = "buzzer_on#:";
   private final String BUZZER_OFF = "buzzer_off#:";

   //constructor
   public Siren( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( SIREN_ON );
      else
         arduino.serialWrite( SIREN_OFF );
   }

   public void buzzerOpen( boolean control ) {
      if( control )
         arduino.serialWrite( BUZZER_ON );
      else
         arduino.serialWrite( BUZZER_OFF );
   }

}
