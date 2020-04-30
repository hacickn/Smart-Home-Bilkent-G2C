package com.SmartHomeBilkent.home.features;

import arduino.Arduino;
import com.SmartHomeBilkent.extra.Fish;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * a Aquarium class
 * @author Hacı Çakın
 * @version 29.03.2020
 */
public class Aquarium extends Communication{

   //properties
   private final String AIR_MOTOR_ON = "air_motor_on#:";
   private final String AIR_MOTOR_OFF = "air_motor_off#:";
   private final String FEEDING_ON = "feeding_on#:";
   private final String FEEDING_OFF = "feeding_off#:";
   private final String INCOMING_WATER_ON = "incoming_water_on#:";
   private final String INCOMING_WATER_OFF = "incoming_water_off#:";
   private final String OUTGOING_WATER_ON = "outgoing_water_on#:";
   private final String OUTGOING_WATER_OFF = "outgoing_water_off#:";
   private Fish fish;
   private String detailedMessage;
   private String lastIncomingWaterTime;
   private String lastOutgoingWaterTime;
   private boolean check;


   //constructor
   public Aquarium( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive() {
      return check;
   }

   public void open ( boolean control ) {
      if( control )
         arduino.serialWrite( AIR_MOTOR_ON );
      else
         arduino.serialWrite( AIR_MOTOR_OFF );
      check = control;
   }

   public void feedingOpen( boolean control ) {
      if( control )
         arduino.serialWrite( FEEDING_ON );
      else
         arduino.serialWrite( FEEDING_OFF );
   }


   public void setAquariumSettings() {
      detailedMessage = "aquarium#" + fish.getFeedingTime() +
            fish.getWaterExchangeTime() + fish.getAirMotorWorkTime() + ":";
      arduino.serialWrite( detailedMessage );
   }

   public void setAquariumSettings( String feeding, String waterExchange, String airMotor) {
      detailedMessage = "aquarium#" + feeding +
            waterExchange + airMotor + ":";
      arduino.serialWrite( detailedMessage );
   }

   public void openIncomingWater( boolean control ) {
      if( control )
         arduino.serialWrite( INCOMING_WATER_ON );
      else
         arduino.serialWrite( INCOMING_WATER_OFF );
   }

   public void openOutgoingWater( boolean control ) {
      if( control )
         arduino.serialWrite( OUTGOING_WATER_ON );
      else
         arduino.serialWrite( OUTGOING_WATER_OFF );
   }

   public void openOutgoingWater() {

   }

   public void closeOutgoingWater() {

   }

   public void setFish( Fish fish ) {
      this.fish = fish;
   }
}
