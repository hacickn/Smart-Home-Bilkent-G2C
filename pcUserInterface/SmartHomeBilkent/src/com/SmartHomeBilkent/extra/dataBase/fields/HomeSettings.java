package com.SmartHomeBilkent.extra.dataBase.fields;

public class HomeSettings {

   //properties
   private String homeID;
   private String sensors;
   private String permission;

   //constructor
   public HomeSettings( String homeID, String sensors, String permission ) {
      this.homeID = homeID;
      this.sensors = sensors;
      this.permission = permission;
   }


   //methods
   public String getHomeID() {
      return homeID;
   }

   public void setHomeID( String homeID ) {
      this.homeID = homeID;
   }

   public String getSensors() {
      return sensors;
   }

   public void setSensors( String sensors ) {
      this.sensors = sensors;
   }

   public String getPermission() {
      return permission;
   }

   public void setPermission( String permission ) {
      this.permission = permission;
   }
}
