package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

/**
 * a Siren class
 * @author İLKE DOĞAN
 * @version 29.03.2020
 */
public class Siren extends Communication {

   //properties
   private final String SIREN_ON = "external_siren_on#:";
   private final String SIREN_OFF = "external_siren_off#:";
   private boolean check;

   //constructor
   public  Siren ( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive(){
      return check;
   }

   public void open ( boolean control ) {
      if( control )
         arduino.serialWrite( SIREN_ON );
      else
         arduino.serialWrite( SIREN_OFF );
      check = control;
   }
}
