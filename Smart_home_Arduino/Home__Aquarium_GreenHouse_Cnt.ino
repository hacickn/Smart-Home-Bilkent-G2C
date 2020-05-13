/*
 * void aquarium_setting()
 * void aquarium_control()
 * 
 */

      void aquarium_setting()
      {       
           byte i = 0, j = 2; 
                   
       if( last_data.length() > 0 )
        {
          feeding_hour        = last_data.substring( i , i + j ); 
          i = i + 2;
          
          feeding_minute      = last_data.substring( i , i + j ); 
          i = i + 2;
          
          feeding_second      = last_data.substring( i , i + j ); 
          i = i + 2;
                  
          water_change_hour   = last_data.substring( i , i + j ); 
          i = i + 2;
          
          water_change_minute = last_data.substring( i , i + j ); 
          i = i + 2;
          
          water_change_second = last_data.substring( i , i + j ); 
          i = i + 2;
          
          water_change_day    = last_data.substring( i , i + j ); 
          i = i + 2;
                  
          air_motor_hour      = last_data.substring( i , i + j ); 
          i = i + 2;
          
          air_motor_minute    = last_data.substring( i , i + j ); 
          i = i + 2;
          
          air_motor_second    = last_data.substring( i , i + j ); 
          i = i + 2;
          
          air_motor_operating = last_data.substring( i , i + j ); 
          i = i + 2;        
      /*
      Serial.println(feeding_hour); Serial.println(feeding_minute); Serial.println(feeding_second);
      Serial.println(water_change_hour); Serial.println(water_change_minute); 
      Serial.println(water_change_second); Serial.println(water_change_day); 
      Serial.println(air_motor_hour); Serial.println(air_motor_minute); 
      Serial.println(air_motor_second); Serial.println(air_motor_operating); 
      */
           //for (int ee_addr=0; ee_addr < 16; ee_addr++) // 
           //        EEPROM.update(ee_addr, 0xff);
       
                i=0;
           for ( int ee_addr = 0 ; ee_addr < 12; ee_addr++ ) // 
               {   
                   EEPROM.update( ee_addr , ( last_data.substring( i ,i + j ) ).toInt() );
                   i = i + 2 ; 
               }  
        }
      }
      
      void aquarium_control()
      {              
         if( ( feeding_hour == _hour ) && ( feeding_minute == _minute ) && ( feeding_second == _second ) )
         {
            feeding_time = 1;
            myServo.write( 180 );       
            Serial.println( "feeding on" );
         }
      
      
         if( ( water_change_hour == _hour ) && ( water_change_minute == _minute ) && ( water_change_day == _day_week )&& ( water_change == 0 ) )
         { 
            water_change = 1;
            digitalWrite( air_motor , HIGH ); 
            digitalWrite( incoming_water , LOW );      
            digitalWrite( outgoing_water , HIGH );   
            Serial.println( "outgoing_water on" );
         }
         else if( ( water_change == 1 ) && ( Distance > Distance_Max ) )
         { 
            water_change = 2;
            digitalWrite( outgoing_water, LOW );    
            Serial.println( "outgoing_water off" );
            digitalWrite( incoming_water, HIGH );    
            Serial.println( "incoming_water on" );
         }
         else if ( ( water_change == 2 ) && ( Distance < Distance_Min ) )
         { 
            water_change = 0;
            digitalWrite( incoming_water , LOW );    
            digitalWrite( outgoing_water , LOW );
            Serial.println( "water change end" );
            if( air_motor_on == true )
               digitalWrite( air_motor , LOW );
         }
      
      
         if( ( air_motor_hour == _hour ) && ( air_motor_minute == _minute ) && ( air_motor_on == false ) )
         {
               air_motor_on = true;
               air_motor_time = air_motor_operating.toInt() + _hour.toInt();
            if(air_motor_time > 24)
               air_motor_time = air_motor_time - 24;  
            if( water_change == 0 )
            {
               digitalWrite( air_motor, LOW );  
               Serial.println( "air_motor on" );   
            }
         }
         else if( ( air_motor_on == true ) && ( _hour.toInt() >= air_motor_time ) )
         { 
               air_motor_on  = false;
               digitalWrite( air_motor , HIGH);
               Serial.println( "air_motor off" );
         }   
      }
