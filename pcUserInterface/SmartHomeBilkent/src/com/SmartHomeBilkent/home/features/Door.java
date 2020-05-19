package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;

/**
 * a Door class
 * @author METEHAN SAÇAKÇI
 * @version 01.05.2020
 */
public class Door extends Communication {
   //properties
   private final String DOOR_ON = "door_on#:";

   //constructor

   /**
    * This constructor method basically create new door with using ardinuo parameter.
    * @param arduino
    */
   public Door( Arduino arduino ) {
      super( arduino );
   }

   //methods

   /**
    * This method is responsible  to send open or close command to the arduino.
    * @param control is a boolean input parameter
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( DOOR_ON );
   }
}
