package com.SmartHomeBilkent.home.features;

import arduino.Arduino;
import com.SmartHomeBilkent.extra.dataBase.GasUsage;
import javafx.collections.ObservableList;

/**
 * a Gas class
 *
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class Gas extends Communication {

   //properties
   private final String GAS_ON = "gas_on#:";
   private final String GAS_OFF = "gas_off#:";
   private boolean check;

   //constructor
   public Gas( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public boolean isActive() {
      return check;
   }

   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GAS_ON );
      else
         arduino.serialWrite( GAS_OFF );

      GasUsage.getInstance().updateGas( control );
      check = control;
   }
}
