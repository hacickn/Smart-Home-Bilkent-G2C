package com.SmartHomeBilkent.utilities.connection;

import com.fazecast.jSerialComm.SerialPort;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * it is a Arduino class
 * this class provide communication
 * @author Hacı Çakın
 * @version 06.05.2020
 */
public class Arduino {

   //properties
   private SerialPort comPort;
   private String portDescription;
   private int baudRate;

   //constructor

   /**
    * it is a CommonSettingData constructor
    * @param portDescription is a String input parameter
    * @param baudRate       is an int input parameter
    */
   public Arduino( String portDescription, int baudRate ) {
      this.portDescription = portDescription;
      this.baudRate = baudRate;
      comPort = SerialPort.getCommPort( this.portDescription );
      comPort.setBaudRate( this.baudRate );
   }

   //methods

   /**
    * it is a openConnection method
    * @return result as a boolean
    */
   public boolean openConnection() {
      if( comPort.openPort() ) {
         try {
            Thread.sleep( 100 );
         } catch( Exception e ) {
            e.printStackTrace();
         }
         return true;
      } else {
         //AlertBox alert = new AlertBox(new Dimension(400,100),"Error Connecting", "Try Another port");
         //alert.display();
         return false;
      }
   }

   /**
    * it is a closeConnection method
    */
   public void closeConnection() {
      comPort.closePort();
   }

   /**
    * it is a getSerialPort method
    * @return result as a SerialPort
    */
   public SerialPort getSerialPort() {
      return comPort;
   }

   /**
    * it is a serialRead method
    * @return result as a String
    */
   public String serialRead() {
      comPort.setComPortTimeouts( SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0 );
      String out = "";
      Scanner in = new Scanner( comPort.getInputStream() );

      try {
         while( in.hasNext() )
            out += ( in.next() + "\n" );
         in.close();
      } catch( Exception e ) {
         e.printStackTrace();
      }
      return out;
   }

   /**
    * it is a serialWrite method
    * @param string is a String input parameter
    */
   public void serialWrite( String string ) {
      PrintWriter printWriter;
      comPort.setComPortTimeouts( SerialPort.TIMEOUT_SCANNER, 0, 0 );

      try {
         Thread.sleep( 5 );
      } catch( Exception e ) {
         e.printStackTrace();
      }
      printWriter = new PrintWriter( comPort.getOutputStream() );
      printWriter.print( string );
      printWriter.flush();
   }
}

