package com.SmartHomeBilkent.home.features;

import com.SmartHomeBilkent.utilities.connection.Arduino;
import com.SmartHomeBilkent.utilities.dataBase.GasUsage;

/**
 * a Gas class
 * @author METEHAN SAÇAKÇI
 * @version 29.04.2020
 */
public class Gas extends Communication {

   //properties
   private final String GAS_ON = "gas_on#:";
   private final String GAS_OFF = "gas_off#:";

   //constructor
   /**
    * This constructor method basically create new garden light with using ardinuo parameter.
    * @param arduino
    */
   public Gas( Arduino arduino ) {
      super( arduino );
   }

   //methods
   /**
    * This method is responsible  to send open or close command to the arduino.
    * @param control is a boolean input parameter
    */
   public void open( boolean control ) {
      if( control )
         arduino.serialWrite( GAS_ON );
      else
         arduino.serialWrite( GAS_OFF );

      GasUsage.getInstance().updateGas( control );
   }
}
