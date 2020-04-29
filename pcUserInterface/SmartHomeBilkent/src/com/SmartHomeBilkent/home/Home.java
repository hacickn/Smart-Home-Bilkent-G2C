package com.SmartHomeBilkent.home;

import arduino.Arduino;
import com.SmartHomeBilkent.home.features.*;

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

}
