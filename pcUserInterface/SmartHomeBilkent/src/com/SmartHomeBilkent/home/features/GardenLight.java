package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

public class GardenLight extends Communication {
   //properties
   private final String GARDEN_LIGHTS_ON = "garden_lights_on#:";
   private final String GARDEN_LIGHTS_OFF = "garden_lights_off#:";
   private boolean check;

   //constructor
   public  GardenLight ( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive(){
      return check;
   }

   public void open() {
      arduino.serialWrite( GARDEN_LIGHTS_ON );
      check = true;
   }

   public void close() {
      arduino.serialWrite( GARDEN_LIGHTS_OFF );
      check = false;
   }
}
