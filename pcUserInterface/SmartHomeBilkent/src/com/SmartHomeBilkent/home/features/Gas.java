package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.extra.connection.Arduino;
import com.SmartHomeBilkent.extra.dataBase.GasUsage;

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

   //constructor
   public Gas( Arduino arduino ) {
      super( arduino );
   }

   //methods
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GAS_ON );
      else
         arduino.serialWrite( GAS_OFF );

      GasUsage.getInstance().updateGas( control );
   }
}
