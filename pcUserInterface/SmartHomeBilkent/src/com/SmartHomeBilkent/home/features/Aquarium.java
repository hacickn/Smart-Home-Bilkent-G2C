package com.SmartHomeBilkent.home.features;

import arduino.Arduino;
import com.SmartHomeBilkent.extra.Fish;
import javafx.collections.ObservableList;

public class Aquarium extends Communication{
   //properties
   private Fish fish;
   private String incomingWaterTime;
   private String outgoingWaterTime;
   private boolean check;

   //constructor
   public Aquarium (Arduino arduino) {
      super( arduino );
   }

   //methods
   public boolean isActive() {
      return check;
   }

   public void open() {

   }

   public void close() {

   }

   public void setAquariumSettings() {

   }

   public void openIncomingWater() {

   }

   public void closeIncomingWater() {

   }

   public void openOutgoingWater() {

   }

   public void closeOutgoingWater() {

   }
}
