package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;
import com.SmartHomeBilkent.utilities.dataBase.ElectricityUsage;

/**
 * a Electricity class
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class Electricity extends Communication {

   //properties
   private final String ELECTRICITY_ON = "electricity_on#:";
   private final String ELECTRICITY_OFF = "electricity_off#:";

   //constructors
   /**
    * This constructor method basically create new electricity with using ardinuo parameter.
    * @param arduino
    */
   public Electricity( Arduino arduino ) {
      super( arduino );
   }

   //methods
   /**
    * This method is responsible  to send open or close command to the arduino.
    * @param control is a boolean input parameter
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( ELECTRICITY_ON );
      else
         arduino.serialWrite( ELECTRICITY_OFF );
      ElectricityUsage.getInstance().updateElectricity( control );
   }
}

