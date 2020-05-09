      /*
       * As a visual aid, the fields of Intel HEX records are colored throughout this article as follows:
       :0300300002337A1E,
        Start code   Byte count   Address   Record type   Data   Checksum
           :            03         0030         00       02337A     1E
                                                00 veri
                                                01 dosya sonu
            03 + 00 + 30 + 00 + 02 + 33 + 7A = E2(226D) 256-226=30(1EH)
            03 + 00 + 30 + 00 + 02 + 33 + 7A + 1E = 100(256D)
      
      : 10 0100 00 214601360121470136007EFE09D21901 40 
      : 10 0110 00 2146017E17C20001FF5F160021480119 28 
      : 10 0120 00 194E79234623965778239EDA3F01B2CA A7 
      : 10 0130 00 3F0156702B5E712B722B732146013421 C7 
      : 00 0000 01FF
      */
      
      
      void Serial_House_Cnt()
      {   
        Serial.setTimeout( 100 );  //100msn  max kadar süre ver verinin gelmesi için bekleme
                     
        if( _readStringUntil() > 0 ) 
          {       
                  Serial.println( incoming_data );   
                                                  
                if ( first_data == "manual_on" )                            
                    manual_on = true;  
                else if ( first_data == "manual_off" )    
                   {
                    manual_on = false;    
                    last_data = "B0E0G0I0U0A0D0W0X0F0R0S0N0";      
                    Smart_App_Cnt();
                    clearData();              
                    Smart_home_data();         
                   }
                    
                if ( ( first_data == "Smart_App" ) && ( manual_on ) )                           
                    Smart_App_Cnt();
                    
                if ( first_data == "clock" )                            
                    hours_setting();
      
                if ( first_data == "aquarium" )                            
                    aquarium_setting();
      
            if( manual_on )
             {      
                    Serial.println( first_data );
                    Serial3.println( first_data );
                    
                if ( first_data == "buzzer_on" )                        
                    digitalWrite( Buzzer , HIGH );
                if ( first_data == "buzzer_off" )                       
                    digitalWrite( Buzzer , LOW ); 
                 
                if ( first_data == "electricity_on" )                   
                    digitalWrite( electricity , LOW );
                 if ( first_data == "electricity_off" )                 
                    digitalWrite( electricity , HIGH );
                 
                if ( first_data == "gas_on" )                           
                    digitalWrite( gas , LOW );         
                if ( first_data == "gas_off" )                          
                    digitalWrite( gas , HIGH ); 
                
                if ( ( ( first_data == "incoming_water_on" )||( first_data == "incoming_water_off" ) ) && ( !digitalRead( outgoing_water ) ) )  
                 {
                  if ( first_data == "incoming_water_on" )                
                      digitalWrite(incoming_water , HIGH ); 
                  if ( first_data == "incoming_water_off" )                
                      digitalWrite( incoming_water , LOW );
                 }
                 else if ( ( ( first_data == "incoming_water_on" )||( first_data == "incoming_water_off" ) ) && ( digitalRead( outgoing_water ) ) )
                 Serial.println("the incoming and outgoing water cannot be active at the same time");
      
                if ( ( ( first_data == "outgoing_water_on" )||( first_data=="outgoing_water_off" ) ) && ( !digitalRead( incoming_water ) ) )   
                 {           
                  if ( first_data == "outgoing_water_on" )                 
                      digitalWrite( outgoing_water , HIGH );
                  if ( first_data == "outgoing_water_off" )                
                      digitalWrite( outgoing_water , LOW );
                 }
                 else if ( ( ( first_data == "outgoing_water_on" )||( first_data == "outgoing_water_off" ) ) && ( digitalRead( incoming_water ) ) )
                 Serial.println( "the incoming and outgoing water cannot be active at the same time" );
      
                if ( first_data == "air_motor_on" )                      
                    digitalWrite(air_motor , LOW );
                if ( first_data == "air_motor_off" )                     
                    digitalWrite( air_motor , HIGH );
      
                if ( first_data == "door_on" )                           
                   {
                      digitalWrite( door , HIGH ); 
                      door_time = 1;
                   }
                if ( first_data == "door_off" )                          
                   digitalWrite( door , LOW );
      
                if ( first_data == "water_valve_on" )                           
                   digitalWrite( water_valve , LOW );
                if ( first_data == "water_valve_off" )                          
                   digitalWrite( water_valve , HIGH );
      
                if ( first_data == "external_siren_on" )                          
                   digitalWrite(external_siren, HIGH );
                if ( first_data == "external_siren_off" )                         
                   digitalWrite( external_siren, LOW );
      
                if ( first_data == "feeding_on" )                           
                   {
                      myServo.write( 180 ); 
                      feeding_time = 1;
                   } 
                    
                if ( first_data == "garden_lights_on" )                           
                    digitalWrite( garden_lights , HIGH );
                if ( first_data == "garden_lights_off" )                         
                    digitalWrite( garden_lights , LOW );
       
                if ( first_data == "soil_moisture_valf_on" )                           
                    digitalWrite( soil_moisture_valf_Cnt , HIGH);
                if ( first_data == "soil_moisture_valf_off")                         
                    digitalWrite( soil_moisture_valf_Cnt , LOW);
      
                if ( first_data == "rain_cnt_device_on" )                           
                    digitalWrite( Raint_Device_Cnt , HIGH );
                if ( first_data == "rain_cnt_device_off" )                         
                    digitalWrite( Raint_Device_Cnt , LOW );
             }
          }
               first_data = "" ; 
      }
      
      
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
               digitalWrite( air_motor, LOW );   Serial.println( "air_motor on" );   
            }
         }
         else if( ( air_motor_on == true ) && ( _hour.toInt() >= air_motor_time ) )
         { 
               air_motor_on  = false;
               digitalWrite( air_motor , HIGH);
               Serial.println( "air_motor off" );
         }   
      }
      
      void Smart_home_data()
      {       
            byte ee_addr = 0; 
       
            feeding_hour        = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++;
            
            feeding_minute      = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++;
            
            feeding_second      = print2digits( EEPROM.read ( ee_addr ) );     
            ee_addr++;
                    
            water_change_hour   = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++;
            
            water_change_minute = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++;
            
            water_change_second = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++;
            
            water_change_day    = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++;
                    
            air_motor_hour      = print2digits( EEPROM.read ( ee_addr ) );    
            ee_addr++;
            
            air_motor_minute    = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++;
            
            air_motor_second    = print2digits( EEPROM.read ( ee_addr ) );     
            ee_addr++;
            
            air_motor_operating = print2digits( EEPROM.read ( ee_addr ) );   
            ee_addr++; 
      
            //EEPROM.update(16,0xff); EEPROM.update(17,0xff);        //*****deneme durumunda******
            //EEPROM.update(18,0xff); EEPROM.update(19,0xff);  
        
        if ( ( EEPROM.read ( 16 ) > 9 ) || ( EEPROM.read ( 17 ) > 9 ) || ( EEPROM.read ( 18 ) > 9 ) || ( EEPROM.read( 19 ) > 9) )
         {
                int i;
                i = 1;
           for ( ee_addr = 16 ; ee_addr < 20 ; ee_addr++ ) // 
               {   
                   EEPROM.update( ee_addr , i );
                   i++; 
               }     
         }   
      
           for ( ee_addr = 16 ; ee_addr < 20 ; ee_addr++ ) // 
                Master_Psw = Master_Psw + ( print2digits( EEPROM.read( ee_addr ) ) ).toInt();       
            
            /*
            Serial.print(feeding_hour); Serial.print(feeding_minute); Serial.print(feeding_second);
            Serial.print(":");
            Serial.print(water_change_hour);Serial.print(water_change_minute);Serial.print(water_change_second);
            Serial.print(water_change_day); 
            Serial.print(":");
            Serial.print(air_motor_hour);Serial.print(air_motor_minute);Serial.print(air_motor_second);
            Serial.print(air_motor_operating); 
            Serial.print(":");           
            */  
      }
      
      int hex_to_int( unsigned char hex[], int count )
        {
          int sum = 0,temp;
          
          for (int i = 0; i < count; i++)
          {
            temp = (hex[i] & 0xf0U) >> 4;
            sum = sum*16 + temp;
            temp = (hex[i] & 0x0fU);
            sum = sum*16 + temp;
          }
          return sum;
        }
      
      String print2digits( int number )
      {
       String temp = ( String )number; 
       
           if(temp.length() > 1)
           { 
            return temp;   
           }
           else
           {  
            return ('0'+ temp); 
           }  
      }
      
      
      void hours_setting()
      {       
                  byte i=0, j=2; 
                  
               if(last_data.length() > 0)
                 {
                    _hour   =last_data.substring(i,i+j); 
                    i=i+2;
                    
                    _minute =last_data.substring(i,i+j); 
                    i=i+2;
                    
                    _second =last_data.substring(i,i+j);
                    i=i+2;
                    
                    _day_week=last_data.substring(i,i+j);
                    i=i+2;
                    
                    _day     =last_data.substring(i,i+j);
                    i=i+2;
                    
                    _moon    =last_data.substring(i,i+j);
                    i=i+2;
                    
                    _year    =last_data.substring(i,i+4);  
                 }
      
                 tmElements_t tm; 
                 //setTime(hr,min,sec,day,month,yr);
                 setTime(_hour.toInt(),_minute.toInt(),_second.toInt(),_day.toInt(),_moon.toInt(),_year.toInt());
                 RTC.set(now());
      }
      
      int _readStringUntil()// prosedür
      {        
              byte data_count =0, first_index =0, last_index  =0; 

           //seri port için
           if (Serial.available())      
            {  
                 incoming_data = Serial.readStringUntil(':');
                 data_count=incoming_data.length();
                 //Serial.println(data_count);
                 
                 first_index = incoming_data.indexOf('#');
                 last_index  = incoming_data.indexOf(':'); 
                 
                 first_data  = incoming_data.substring(0,first_index);
                 last_data   = incoming_data.substring(first_index+1,last_index);
                 /*
                 Serial.println(incoming_data);
                 Serial.println(first_index);
                 Serial.println(last_index);
                 Serial.println(first_data);
                 Serial.println(last_data); 
                 */          
            }  
            
             // BLUETOOTH için
            if (Serial3.available())    
            {  
                 incoming_data = Serial3.readStringUntil(':');
                 data_count=incoming_data.length();Serial.println(data_count);
                 
                 first_index = incoming_data.indexOf('#');
                 last_index  = incoming_data.indexOf(':'); 
                 
                 first_data  = incoming_data.substring(0,first_index);
                 last_data   = incoming_data.substring(first_index+1,last_index);
                 /*
                 Serial.println(incoming_data);
                 Serial.println(first_index);
                 Serial.println(last_index);
                 Serial.println(first_data);
                 Serial.println(last_data); 
                 */          
            }  
          return data_count;
      }
      
      void Smart_App_Cnt()
        { 
             Serial.println( last_data );  
             String tempa = ""; 
             byte i = 0, j = 2;
      
             tempa=last_data.substring( i,i+j ); 
             i = i + 2; 
             //Serial.print(tempa);
             
          if( tempa == "B1" )       
               digitalWrite( Buzzer, HIGH );
          else if( tempa == "B0" )
               digitalWrite( Buzzer, LOW );       
      
             tempa = last_data.substring( i,i+j );
             i = i+2;
             //Serial.print(tempa);
          if( tempa == "E1" )       
                digitalWrite( electricity, HIGH );
          else if( tempa == "E0" )
                digitalWrite( electricity, LOW );
          
             tempa = last_data.substring( i,i+j );
             i = i + 2;
             //Serial.print(tempa);

          if(tempa == "G1" )       
               digitalWrite( gas, HIGH );
          else if( tempa == "G0" )
               digitalWrite( gas, LOW );     
      
             tempa = last_data.substring ( i , i + j ); 
             i = i + 2;
             //Serial.print(tempa);
             
       if( ( ( tempa == "I1" )||( tempa == "I0" ) ) && ( !digitalRead( outgoing_water ) ) )  
        {
              if( tempa == "I1" )       
                   digitalWrite( incoming_water, HIGH );
              else if( tempa == "I0" )
                   digitalWrite( incoming_water, LOW ); 
        }
       else if( ( ( tempa == "I1" )||( tempa == "I0" ) ) && ( digitalRead( outgoing_water ) ) )
               Serial.println( "the incoming and outgoing water cannot be active at the same time" );
                 
             tempa = last_data.substring( i , i + j );
             i = i + 2;
             //Serial.print(tempa);
       if( ( ( tempa == "U1" )||( tempa == "U0" ) ) && ( !digitalRead( incoming_water) ) )  
        {
          if( tempa == "U1" )       
               digitalWrite( outgoing_water, HIGH );
          else if( tempa == "U0" )
               digitalWrite( outgoing_water, LOW ); 
        }
       else if( ( ( tempa == "U1" )||( tempa == "U0" )) && ( digitalRead( incoming_water) ) )
               Serial.println( "the incoming and outgoing water cannot be active at the same time" );
               
             tempa = last_data.substring( i , i + j );
             i = i + 2;
             //Serial.print(tempa);
             
          if( tempa == "A1" )       
               digitalWrite( air_motor, HIGH );
          else if( tempa == "A0" )
               digitalWrite( air_motor, LOW );
               
             tempa = last_data.substring( i , i + j );
             i = i + 2;
             //Serial.print(tempa);
             
          if( tempa == "D1" )       
               digitalWrite( door, HIGH );
          else if( tempa == "D0" )
               digitalWrite( door, LOW ); 
      
             tempa = last_data.substring( i , i + j );
             i = i + 2;
             //Serial.println(tempa);
             
          if( tempa == "W1" )       
               digitalWrite( water_valve, HIGH );
          else if( tempa == "W0" )
                digitalWrite( water_valve, LOW );
               
             tempa = last_data.substring( i , i + j );
             i = i + 2;
             //Serial.println(tempa);
             
          if( tempa == "X1" )       
               digitalWrite( external_siren, HIGH ); 
          else if( tempa == "X0" )
               digitalWrite( external_siren, LOW );
      
             tempa = last_data.substring( i , i + j );
             i = i + 2;
             //Serial.println(tempa);
             
          if( tempa == "F1" )       
               {
                  myServo.write(180);
                  feeding_time = 1;
                } 
      
             tempa = last_data.substring (i , i + j );
             i = i + 2;
             //Serial.println(tempa);
             
          if( tempa == "R1" )       
               digitalWrite( garden_lights, HIGH );
          else if( tempa == "R0" )
               digitalWrite( garden_lights, LOW );
      
             tempa = last_data.substring ( i , i + j);
             i = i + 2;
             //Serial.println(tempa);
             
          if( tempa == "S1" )       
               digitalWrite( soil_moisture_valf_Cnt, HIGH );
          else if( tempa == "S0" )
               digitalWrite( soil_moisture_valf_Cnt, LOW );
      
             tempa = last_data.substring ( i , i + j);
             //Serial.println(tempa);
             i = i + 2;
             //Serial.println(tempa);
             
          if( tempa == "N1" )       
               digitalWrite( Raint_Device_Cnt, HIGH );
          else if( tempa == "N0" )
               digitalWrite( Raint_Device_Cnt, LOW );
       }
      
      //HC-05 Bluetooth Modülü
      void Bluetooth_Setting()
      {
            String isim = "Bluet_Cnt_Car_rk";
            int sifre = 1111;
           // String uart = "9600,0,0";
           
            //"AT+NAME=Bluet_Cnt_Car_rk"
            Serial3.print( "AT + NAME = " );
            Serial3.println( isim );
            Serial.print( "Isim ayarlandi: " );
            Serial.println( isim );
            delay( 1000 );
            Serial3.print( "AT + PSWD = " );
            Serial3.println( sifre );
            Serial.print( "Sifre ayarlandi: " );
            Serial.println( sifre );
            //delay(1000);
            //Serial3.print("AT+UART=");
            //Serial3.println(uart);
            //Serial.print("Baud rate ayarlandi: ");
            //Serial.println(uart);
            delay( 2000 );
            Serial.println( "Islem tamamlandi." );
      
        /*
            //Handshake 
            Serial.println("Bluetooth Modülü ile iletişim bekleniyor");
                 String  smsMetni;    char txtMsg[20]; 
            blue.println("Mrb Blue");  
            
            while (!blue.available());
                         
            if (blue.available() > 0)     // BLUETOOTH için
                {  
                   readSerial(txtMsg);
                   smsMetni=txtMsg;   Serial.println(smsMetni);
                }  
            
            if(smsMetni=="mrb") 
                   Serial.println("Bluetooth Modülü ile iletişim kuruldu");
             else
                   Serial.println("Bluetooth Modülü ile iletişim kurulamadı");
          */
      }
