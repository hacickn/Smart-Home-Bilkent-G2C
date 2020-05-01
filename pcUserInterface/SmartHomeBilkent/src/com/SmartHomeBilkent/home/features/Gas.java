package com.SmartHomeBilkent.home.features;

import arduino.Arduino;
import javafx.collections.ObservableList;

/**
 * a Gas class
 * @author METEHAN SAÇAKÇI
 * @version 29.03.2020
 */
public class Gas extends Communication {

   //properties
   private final String GAS_ON = "gas_on#:";
   private final String GAS_OFF = "gas_off#:";
   private ObservableList<String> hoursOfDayList;
   private boolean check;

   //constructor
   public Gas ( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive() {
      return check;
   }

   public void open ( boolean control ) {
      if( control )
         arduino.serialWrite( GAS_ON );
      else
         arduino.serialWrite( GAS_OFF );
      check = control;
   }
}
