package com.SmartHomeBilkent.home;

import com.SmartHomeBilkent.utilities.connection.Arduino;
import com.SmartHomeBilkent.home.features.*;

/**
 * a Home class
 * In this class Actually, inheritance is used. Each object can be reached through the serial port.
 * The objects of the classes in the home are used in this class. The communication class is being extended.
 * @author İlke Doğan
 * @version 29.04.2020
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

   /**
    * it is a Home constructor
    * @param arduino
    */
   //constructors
   public Home( Arduino arduino ) {
      this.arduino = arduino;
      electricity = new Electricity( arduino );
      gas = new Gas( arduino );
      gardenLight = new GardenLight( arduino );
      siren = new Siren( arduino );
      water = new Water( arduino );
      door = new Door( arduino );
      greenHouse = new GreenHouse( arduino );
      aquarium = new Aquarium( arduino );
   }
   //methods

   /**
    * it is a adjustCollective method that is used for getting message from Arduino class
    *  @param message is a String input parameter
    */
   public void adjustCollective( String message ) {
      arduino.serialWrite( message );
   }

   /**
    * it is a getElectricity method that is used for getting on/ off information regarding electrity  from Electricity
    * class and its object via Arduino class
    * @return result as a electricity
    */
   public Electricity getElectricity() {
      return electricity;
   }

    /**
     * it is a getGas method that is used for getting on/ off information regarding gas from Gas class and its
     * object via Arduino class
     * @return result as a gas
     */
   public Gas getGas() {
      return gas;
   }

    /**
     * it is a getGardenLight method that is used for getting on/ off information regarding gas from GardenLight class and
     * its object via Arduino class
     * @return result as a gardenLight
     */
   public GardenLight getGardenLight() {
      return gardenLight;
   }

    /**
     * it is a getSiren method that is used for getting on/ off information regarding gas from Siren class and
     * its object via Arduino class
     * @return result as a siren
     */
   public Siren getSiren() {
      return siren;
   }

    /**
     * it is a getWater method that is used for getting on/ off information regarding Water
     * object from Arduino class
     * @return result as a water
     */
   public Water getWater() {
      return water;
   }

    /**
     * it is a getGreenHouse method that is used for getting on/ off information regarding GreenHouse
     * object from Arduino class
     * @return result as a greenHouse
     */
   public GreenHouse getGreenHouse() {
      return greenHouse;
   }

    /**
     * it is a getDoor method that is used for getting on/ off information regarding Door
     * object from Arduino class
     * @return result as a door
     */
   public Door getDoor() {
      return door;
   }

   /**
    * it is a getArduino method that is used for getting arduino object from Arduino class
    * @return result as a arduino
    */
   public Arduino getArduino() {
      return arduino;
   }

   /**
    * it is a getAquarium method that is used for getting aquarium object from Aquarium class
    * @return result as a aquarium
    */
   public Aquarium getAquarium() {
      return aquarium;
   }

}
