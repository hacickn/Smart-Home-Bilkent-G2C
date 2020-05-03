package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

/**
 * a Door class
 *
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class Door extends Communication {
   //properties
   private final String DOOR_ON = "door_on#:";
   private boolean check;

   //constructor
   public Door( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive() {
      return check;
   }

   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( DOOR_ON );
      check = control;
   }
}
