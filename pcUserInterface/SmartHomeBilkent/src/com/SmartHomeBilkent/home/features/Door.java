package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

public class Door extends Communication {
   //properties
   private final String DOOR_ON = "door_on#:";
   private final String DOOR_OFF = "door_off#:";
   private boolean check;

   //constructor
   public  Door ( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive(){
      return check;
   }

   public void open() {
      arduino.serialWrite( DOOR_ON );
      check = true;
   }

   public void close() {
      arduino.serialWrite( DOOR_OFF );
      check = false;
   }
}
