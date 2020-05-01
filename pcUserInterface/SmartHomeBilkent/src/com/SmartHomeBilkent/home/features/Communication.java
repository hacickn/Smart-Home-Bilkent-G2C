package com.SmartHomeBilkent.home.features;

import arduino.Arduino;
import com.SmartHomeBilkent.MainPanel;

/**
 * Aquarium class
 * @author HACI ÇAKIN
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
   abstract void open( boolean control );

   abstract boolean isActive();
}