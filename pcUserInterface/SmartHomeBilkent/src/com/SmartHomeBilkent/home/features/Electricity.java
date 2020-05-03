package com.SmartHomeBilkent.home.features;

import arduino.Arduino;
import com.SmartHomeBilkent.extra.dataBase.ElectricityUsage;
import javafx.collections.ObservableList;

/**
 * a Electricity class
 *
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class Electricity extends Communication {

   //properties
   private final String ELECTRICITY_ON = "electricity_on#:";
   private final String ELECTRICITY_OFF = "electricity_off#:";
   private boolean check;

   //constructors
   public Electricity( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive() {
      return check;
   }

   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( ELECTRICITY_ON );
      else
         arduino.serialWrite( ELECTRICITY_OFF );
      ElectricityUsage.getInstance().updateElectricity( control );
      check = control;
   }
}

