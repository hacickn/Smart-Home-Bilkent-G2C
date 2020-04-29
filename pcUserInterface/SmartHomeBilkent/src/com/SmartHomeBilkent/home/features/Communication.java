package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

/**
 * Aquarium class
 * @author Hacı Çakın
 * @version 29.03.2020
 */
public abstract class Communication {
   //properties
   Arduino arduino;

   //constructor
   public Communication ( Arduino arduino) {
      this.arduino = arduino;
   }

   //methods
   abstract void open();

   abstract void close();

   abstract boolean isActive();
}
