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

   /**
    * it is a GasUsage constructor
    *
    * @param arduino is an Arduino input parameter
    */
   public Communication( Arduino arduino ) {
      this.arduino = arduino;
   }

   //methods

   /**
    * it is a open abstract method
    *
    * @param control is a boolean input parameter
    */
   abstract void open( boolean control );
}
