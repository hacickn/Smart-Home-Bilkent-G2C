package com.SmartHomeBilkent.extra.dataBase.fields;

/**
 * it is a CommonSetting class
 *
 * @author Hacı Çakın
 * @version 05.05.2020
 */
public class CommonSetting {

   //properties
   private String homeID;
   private String sensors;
   private String permission;

   //constructor

   /**
    * it is a Usage constructor
    *
    * @param homeID     is a LocalDate input parameter
    * @param sensors    is a String input parameter
    * @param permission is a String input parameter
    */
   public CommonSetting( String homeID, String sensors, String permission ) {
      this.homeID = homeID;
      this.sensors = sensors;
      this.permission = permission;
   }


   //methods

   /**
    * it is a getHomeID method
    *
    * @return result as a String
    */
   public String getHomeID() {
      return homeID;
   }

   /**
    * it is a setHomeID method
    *
    * @param homeID is a String input parameter
    */
   public void setHomeID( String homeID ) {
      this.homeID = homeID;
   }

   /**
    * it is a getSensors method
    *
    * @return result as a String
    */
   public String getSensors() {
      return sensors;
   }

   /**
    * it is a setSensors method
    *
    * @param sensors is a String input parameter
    */
   public void setSensors( String sensors ) {
      this.sensors = sensors;
   }

   /**
    * it is a getPermission method
    *
    * @return result as a String
    */
   public String getPermission() {
      return permission;
   }

   /**
    * it is a setPermission method
    *
    * @param permission is a String input parameter
    */
   public void setPermission( String permission ) {
      this.permission = permission;
   }
}
