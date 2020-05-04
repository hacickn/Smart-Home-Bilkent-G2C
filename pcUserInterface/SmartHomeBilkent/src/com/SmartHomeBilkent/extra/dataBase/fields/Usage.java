package com.SmartHomeBilkent.extra.dataBase.fields;

import java.time.LocalDate;

/**
 * it is a Usage class
 *
 * @author Hacı Çakın
 * @version 01.05.2020
 */
public class Usage {

   //properties
   private LocalDate day;
   private String activity;

   //constructor

   /**
    * it is a Usage constructor
    *
    * @param day      is a LocalDate input parameter
    * @param activity is a String input parameter
    */
   public Usage( LocalDate day, String activity ) {
      this.day = day;
      this.activity = activity;
   }

   //methods

   /**
    * it is a getDay method that give the day of Usage object
    *
    * @return day as a LocalDate
    */
   public LocalDate getDay() {
      return day;
   }

   /**
    * it is a setDay method that set the day
    *
    * @param day is a LocalDate input parameter
    */
   public void setDay( LocalDate day ) {
      this.day = day;
   }

   /**
    * it is a getActivity method that give the activity
    *
    * @return activity as a String
    */
   public String getActivity() {
      return activity;
   }

   /**
    * it is a setActivity method that set the activity
    *
    * @param activity is a String input parameter
    */
   public void setActivity( String activity ) {
      this.activity = activity;
   }
}
