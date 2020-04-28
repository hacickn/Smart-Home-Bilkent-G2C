package com.SmartHomeBilkent.home.features;

import arduino.Arduino;
import javafx.collections.ObservableList;

public class Electricity extends Communication {

   //properties
   private final String ELECTRICITY_ON = "electricity_on#:";
   private final String ELECTRICITY_OFF = "electricity_off#:";
   private ObservableList<String> hoursOfDayList;
   private boolean check;

   //constructors
   public Electricity ( Arduino arduino ) {
      super(arduino);
   }

   //methods
   public boolean isActive (){
      return check;
   }

   public void close() {
      arduino.serialWrite(ELECTRICITY_OFF);
      check = false;
   }

   public void open() {
      arduino.serialWrite(ELECTRICITY_ON);
      check = true;
   }
}

