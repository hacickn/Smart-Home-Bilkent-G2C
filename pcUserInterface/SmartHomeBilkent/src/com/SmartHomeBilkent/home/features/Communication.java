package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

/**
 * Aquarium class
 *
 * @author HACI ÇAKIN
 * @version 29.04.2020
 */
public abstract class Communication {

   //properties
   Arduino arduino;

   //constructor
   public Communication( Arduino arduino ) {
      this.arduino = arduino;
   }

   //methods
   abstract void open( boolean control );

   abstract boolean isActive();
}
