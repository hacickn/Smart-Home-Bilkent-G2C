package com.SmartHomeBilkent.home.features;

import arduino.Arduino;

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
