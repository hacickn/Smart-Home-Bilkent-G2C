package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

/**
 * a Water class
 * @author Hacı Çakın
 * @version 29.03.2020
 */
public class Water extends Communication {
   //properties
   private final String WATER_ON = "water_valve_on#:";
   private final String WATER_OFF = "water_valve_off#:";
   private boolean check;

   //constructor
   public  Water ( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive(){
      return check;
   }

   public void open() {
      arduino.serialWrite( WATER_ON );
      check = true;
   }

   public void close() {
      arduino.serialWrite( WATER_OFF );
      check = false;
   }
}
