package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;

/**
 * a Aquarium class
 *
 * @author HACI ÇAKIN
 * @version 29.04.2020
 */
public class Aquarium extends Communication {

   //properties
   private final String AIR_MOTOR_ON = "air_motor_on#:";
   private final String AIR_MOTOR_OFF = "air_motor_off#:";
   private final String FEEDING_ON = "feeding_on#:";
   private final String FEEDING_OFF = "feeding_off#:";
   private final String INCOMING_WATER_ON = "incoming_water_on#:";
   private final String INCOMING_WATER_OFF = "incoming_water_off#:";
   private final String OUTGOING_WATER_ON = "outgoing_water_on#:";
   private final String OUTGOING_WATER_OFF = "outgoing_water_off#:";
   //constructor

   /**
    * it is a Aquarium constructor
    *
    * @param arduino is an Arduino input parameter
    */
   public Aquarium( Arduino arduino ) {
      super( arduino );
   }

   //methods

   /**
    * it is a open method that open air motor according to input parameter
    *
    * @param control is a boolean input parameter
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( AIR_MOTOR_ON );
      else
         arduino.serialWrite( AIR_MOTOR_OFF );
   }

   /**
    * it is a feedingOpen that open feed motor according to input parameter
    *
    * @param control is a boolean input parameter
    */
   public void feedingOpen( boolean control ) {
      if( control )
         arduino.serialWrite( FEEDING_ON );
      else
         arduino.serialWrite( FEEDING_OFF );
   }

   /**
    * it is a setAquariumSettings method
    *
    * @param message is a String input parameter
    */
   public void setAquariumSettings( String message ) {
      arduino.serialWrite( message );
   }

   /**
    * it is a openIncomingWater that open incoming water according to input parameter
    *
    * @param control is a boolean input parameter
    */
   public void openIncomingWater( boolean control ) {
      if( control )
         arduino.serialWrite( INCOMING_WATER_ON );
      else
         arduino.serialWrite( INCOMING_WATER_OFF );
   }

   /**
    * it is a openOutgoingWater that open outgoing water according to input parameter
    *
    * @param control is a boolean input parameter
    */
   public void openOutgoingWater( boolean control ) {
      if( control )
         arduino.serialWrite( OUTGOING_WATER_ON );
      else
         arduino.serialWrite( OUTGOING_WATER_OFF );
   }

}
