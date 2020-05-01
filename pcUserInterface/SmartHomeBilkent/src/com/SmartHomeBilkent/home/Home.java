package com.SmartHomeBilkent.home;

import arduino.Arduino;
import com.SmartHomeBilkent.home.features.*;

/**
 * a Home class
 * @author İlke Doğan
 * @version 29.03.2020
 */
public class Home {

   //properties
   private Electricity electricity;
   private Gas gas;
   private GardenLight gardenLight;
   private Siren siren;
   private Water water;
   private GreenHouse greenHouse;
   private Door door;
   private Arduino arduino;
   private Aquarium aquarium;

   //constructors
   public Home(Arduino arduino) {
      this.arduino = arduino;
      electricity = new Electricity( arduino );
      gas = new Gas( arduino );
      gardenLight = new GardenLight( arduino );
      siren = new Siren( arduino );
      water = new Water( arduino );
      door = new Door( arduino );
      greenHouse = new GreenHouse();
      aquarium = new Aquarium( arduino );
   }


   //methods
   public void adjustCollective( String message ) {
      arduino.serialWrite( message );
   }

   public Electricity getElectricity() {
      return electricity;
   }

   public Gas getGas() {
      return gas;
   }

   public GardenLight getGardenLight() {
      return gardenLight;
   }

   public Siren getSiren() {
      return siren;
   }

   public Water getWater() {
      return water;
   }

   public GreenHouse getGreenHouse() {
      return greenHouse;
   }

   public Door getDoor() {
      return door;
   }

   public Arduino getArduino() {
      return arduino;
   }

   public Aquarium getAquarium() {
      return aquarium;
   }

}
