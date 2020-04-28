package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

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
