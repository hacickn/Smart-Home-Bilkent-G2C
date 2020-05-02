package com.SmartHomeBilkent.extra.dataBase.fields;

import java.time.LocalDate;

public class Usage {

   //properties
   private LocalDate day;
   private String activity;

   //constructor
   public Usage ( LocalDate day, String activity ) {
      this.day = day;
      this.activity = activity;
   }

   //methods
   public LocalDate getDay() {
      return day;
   }

   public void setDay(LocalDate day) {
      this.day = day;
   }

   public String getActivity() {
      return activity;
   }

   public void setActivity(String activity) {
      this.activity = activity;
   }
}
