package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.extra.connection.Arduino;

/**
 * a Door class
 *
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class Door extends Communication {
   //properties
   private final String DOOR_ON = "door_on#:";

   //constructor
   public Door( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( DOOR_ON );
   }
}
