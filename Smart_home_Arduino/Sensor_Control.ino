   
      /* 
       *  void Sensor_Cnt() 
       *  void Gas_Smoke_alarm()
       *  void House_Temp_Humidity()
       *  void light_sens_state()
      */   
      void Sensor_Cnt()
      {     //alarm_condition=0;
      
              if( ( digitalRead( Rain_sens ) )  && ( window_open ) )  
              {
                /*
                 * servo motor will turn off at 0 degrees
                 */
                 window_open = false;
                 Serial.println( "Window closed" );    
                 myServo_Rain_Device_Cnt.write( 0 );                
              }  
      
              if( ( digitalRead( soil_moisture_sens ) ) && ( !digitalRead( soil_moisture_valf_Cnt ) ) )   
              {
                 Serial.println( "soil_moisture_sens_on" );   
                 digitalWrite( soil_moisture_valf_Cnt , HIGH );
              }
              else if( ( !digitalRead( soil_moisture_sens ) ) && ( digitalRead( soil_moisture_valf_Cnt ) ) )
              {
                 Serial.println( "soil_moisture_sens_of" );   
                 digitalWrite ( soil_moisture_valf_Cnt , LOW );
              } 
              
             if ( digitalRead( Gas_Sensor ) )
             {
                bitWrite( alarm_condition , 0 , 1 ); 
                Gas_Smoke_alarm(); 
             }
             else
                bitWrite( alarm_condition , 0 , 0 );     
             
             if ( digitalRead( Smoke_Sensor ) )
             {
                bitWrite( alarm_condition , 1 , 1 );        
                Gas_Smoke_alarm();  
             }
             else
                bitWrite( alarm_condition , 1 , 0 );     
                
             if ( digitalRead( Fire_Sensor ) )
             {
                bitWrite( alarm_condition , 2 ,  1 );       
                Gas_Smoke_alarm(); 
             }
             else
                bitWrite( alarm_condition , 2 , 0 );     
             
             if( ( door_encrypted ) && digitalRead( Motion_Sensor ) && ( psw_time == 0))
                 psw_time = 1;              
            
         if( ( !digitalRead( Gas_Sensor ) )&&(!digitalRead( Smoke_Sensor )) && ( !digitalRead( Fire_Sensor ) ) )
           {
             if (manual_on == false)
             {
              digitalWrite(electricity , LOW);      
              digitalWrite(gas , LOW);
             }
           }        
      
             if( ( !digitalRead( air_motor ) ) && ( water_change == 0) && ( Distance > Distance_Max ) )
             {  // air_motor NK!!!!!
                digitalWrite( outgoing_water , LOW );    
                digitalWrite( incoming_water , LOW );
                 
                digitalWrite( air_motor , HIGH );    
                Serial.println( "air_motor off" );
                
                Serial.println( "low water level" );  
                Serial3.println( "low water level" );
             }      
                 
             alarm_message = "";  
         if ( alarm_condition > 0 )    
            {                    
              if( alarm_condition == 1 )
                 alarm_message = "Gas Alarm           ";             
              else if( alarm_condition == 2 )
                 alarm_message = "Smoke Alarm         ";
              else if( alarm_condition == 3 )
                 alarm_message = "Gas+Smoke alarm     ";  
              else if( alarm_condition == 4 )
                 alarm_message = "Fire Buton          ";
              else if( alarm_condition == 5 )
                 alarm_message = "Gas+Fire            ";  
              else if( alarm_condition == 6 )
                 alarm_message = "Gas+Smoke           ";             
              else if( alarm_condition == 7 )
                 alarm_message = "Gas+Smoke+Fire      ";
                 
              else if( alarm_condition == 8 )
                 alarm_message = "motion Alarm        ";
              else if( alarm_condition == 9 )
                 alarm_message = "Motion+Gas Alarm    ";
              else if( alarm_condition == 10 )
                 alarm_message = "Motion+Smoke  Alarm "; 
              else if( alarm_condition == 11 )
                 alarm_message = "Motion+Smoke+Gas    ";
              else if( alarm_condition == 12 )
                 alarm_message = "Motion+Fire Buton   ";
              else if( alarm_condition == 13 )
                 alarm_message="Motion+Fire+Gas     "; 
              else if( alarm_condition == 14 )
                 alarm_message="Motion+Fire+Smoke   ";
              else if( alarm_condition == 15 )
                 alarm_message = "Motion+Fire+Smok+Gas";
                 
              else if( alarm_condition == 16 )
                 alarm_message = "Psw Alarm           ";
              else if( alarm_condition == 17 )
                 alarm_message="Psw+Gas Alarm       ";
              else if( alarm_condition == 18 )
                 alarm_message = "Psw+Smoke Alarm     "; 
              else if( alarm_condition == 19 )
                 alarm_message = "Psw+Smoke+Gas       ";
              else if( alarm_condition == 20 )
                 alarm_message = "Psw+Fire Buton      ";
              else if( alarm_condition == 21 )
                 alarm_message = "Psw+Fire+Gas        "; 
              else if( alarm_condition == 22 )
                 alarm_message = "Psw+Fire+Smoke      ";
              else if( alarm_condition == 23 )
                 alarm_message = "Psw+Fire+Smoke+Gas  ";
              else if( alarm_condition == 24 )
                 alarm_message = "Psw+Motion          ";
              else if( alarm_condition == 25 )
                 alarm_message = "Psw+Motion+Gas      ";
              else if( alarm_condition == 26 )
                 alarm_message="Psw+Motion+Smoke    "; 
              else if( alarm_condition == 27 )
                 alarm_message = "Psw+Motion+Smoke+Gas";
              else if( alarm_condition == 28 )
                 alarm_message = "Psw+Motion+Fire     ";
              else if( alarm_condition == 29 )
                 alarm_message = "Psw+Motion+Fire+Gas "; 
              else if( alarm_condition == 30 )
                 alarm_message = "Psw+Motion+Fire+Smok";
              else if( alarm_condition == 31 )
                 alarm_message = "Psw+Mot+Fir+Smok+Gas";

              else if( alarm_condition == 32 )
                 alarm_message = "Water Decreased     ";
            }
          //Serial.println(alarm_condition);
      }
      
      void Gas_Smoke_alarm()
        { 
           digitalWrite( air_motor, LOW );        
           digitalWrite( water_valve, LOW );
           
           digitalWrite( electricity , HIGH );     
           digitalWrite( gas , HIGH ); 
           
           digitalWrite( incoming_water , LOW );    
           digitalWrite( outgoing_water , LOW );
        }
        /*
       * House_Temp_Humidity()
       * GreenHouse Humidty data
       */
          
      void House_Temp_Humidity()
      {       //SHT21     
              sht.reset();
              temp = sht.getTemperature();  // get temp from SHT 
              sht.reset();
              humidity = sht.getHumidity(); // get temp from SHT 
           
              Serial.print( "Temp_HS: " );      
              Serial.print( temp );
              Serial.print( "\t Humidity_HS: " );
              Serial.println( humidity );
      
              Serial.print( "Temp_GH: " );
              Serial.print( temp_green_house );
              Serial.print( "\t Humidity_GH: " );
              Serial.println( humid_green_house );
      }       
         /*
          * light_sens_state()
          * curtain control based on ligth amount
          */
      void light_sens_state()
      {      
              /** duyarlılık = Vref / 2^n 
              * miliV
              * multipliyng w 1000 to convert it Volt to milivolt
              */
              //ADC- A13
              light_sens_value =  analogRead( Ldr_Sensor );
              light_sens_value = map( light_sens_value , 0,1023 , 0,100 );
              Serial.println( light_sens_value );         
           if ( ( light_sens_value > 70 ) && ( !digitalRead( garden_lights ) ) )  
             {
               /*
                * If we use Stor curtain we will turn the 
                * curtain 360° in 3 times 
                */
               curtain_time=1;  
               myServo_Ldr_curtain_Cnt.write( 180 );
               Serial.println( " myServo_Ldr_curtain_Cnt ON  " );
               digitalWrite( garden_lights , HIGH );
               Serial.println( " Garden_lights ON  " );
             }
             else if( ( light_sens_value < 70 ) && ( digitalRead( garden_lights ) ) )
             {
               digitalWrite( garden_lights , LOW );
               Serial.println( " Garden_lights Off  " );
             }
      }
