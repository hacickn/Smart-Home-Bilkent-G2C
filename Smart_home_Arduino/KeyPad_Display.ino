      /* 
       *  #include <Keypad.h> 
       *  #include <Password.h>
       *  #include "LiquidCrystal.h"   
      */
      
      void Display_Refresh()
        {       
              lcd.setCursor( 0,  0 );             // 1.satır için 
              lcd.print( Distance );              // 1.satır için 
              
              lcd.setCursor( 3,  0 );    
              lcd.print( "cm," );        
              
              lcd.setCursor( 7,  0 );   
              lcd.print( temp );
              
              lcd.setCursor( 11, 0 );   
              lcd.print( "'C," );  
              
              lcd.setCursor( 15, 0 );  
              lcd.print( humidity );
              
              lcd.setCursor( 19, 0 );  
              lcd.print( "%" );   
      
              lcd.setCursor( 0, 1 );   // 2.satır için 
              
          if ( door_encrypted == false )          
               lcd.print( "Password(Off):" );
               
          else if( door_encrypted == true )   
               lcd.print( "Password(On ):" );
               
          for (int i = 0; i < 4; i++)  
              {  
                lcd.setCursor( 14 + i , 1 ); 
                lcd.print( Data_Psw[i] );  
              }                  
                   
          if(alarm_condition > 0)                                         // 3.satır için 
           {
              lcd.setCursor( 0, 2 );   
              lcd.print("                     "); 
              
              lcd.setCursor( 0, 2 );   
              lcd.print( alarm_message );    
              Serial.println( alarm_message );
              Serial3.println( alarm_message );
           }
         else 
           { 
              lcd.setCursor( 0 , 2 );     
              lcd.print("                    ");  
           }
                  
              lcd.setCursor( 0 ,  3 );    // 4.satır için 
              lcd.print( _hour );        // 4.satır için 
             
              lcd.setCursor( 2 ,  3 );  
              lcd.print( ':' );
              
              lcd.setCursor( 3 ,  3 );   
              lcd.print( _minute );
              
              lcd.setCursor( 5 ,  3 );  
              lcd.print(':');
              
              lcd.setCursor( 6 ,  3 );   
              lcd.print( _second );
              
              //lcd.setCursor(8,  3);    lcd.print(_day_week_tr);
              
              lcd.setCursor( 10 , 3 );  
              lcd.print( _day );
              
              lcd.setCursor( 12 , 3 ); 
              lcd.print('.');
              
              lcd.setCursor( 13 , 3 );  
              lcd.print( _moon );
              
              lcd.setCursor( 15 , 3 );  
              lcd.print( '.' ); 
              
              lcd.setCursor( 16 , 3 );  
              lcd.print( _year );   
        }
      
      void Key_Pad()
      {
          if( customKey == '*' )                    //clear 
          {
            clearData();
            return;
          }
      
          if(customKey=='D')                    //clear 
          {
            MsTimer2::stop(); 
            changePassword();
            MsTimer2::start(); 
            return;
          }
            
               Data_Psw = Data_Psw  + customKey; 
               Psw_count++; 
                         
            if( Psw_count == Psw_Length )
            {             
                 Psw_count = 0;
                 give_change--;    
             if( Data_Psw == Master_Psw )
             { 
                    door_psw_enb = !door_psw_enb;  // psw için 10 saniye izni verilmeis
                 if( ( door_psw_enb ) && ( !door_encrypted ) )// zaman dolmadıysa
                  {
                    give_change = 3;  
                    psw_time = 1;    
                  }
                 else if( ( !door_psw_enb ) && ( door_encrypted ) )
                  {
                    door_encrypted = false;            
                    door_psw_enb = false;  
                    
                    give_change = 3;                  
                    psw_time = 0; 
                    
                    digitalWrite( door, HIGH );        
                    door_time = 1;
                    
                    bitWrite( alarm_condition , 3 , 0);  
                    bitWrite( alarm_condition , 4 , 0);             
                  }          
              }
              else         
              {
                  if( give_change == 0 )             
                  {
                     // To prevent infinite chance    
                     give_change = 1;     
                     bitWrite( alarm_condition , 4 , 1 ); 
                  }
              }    
                clearData();  
            }
      }
      
      void clearData()
      {   
           //for(byte i=0; i < 4; i++)
           //    Data_Psw[i] = 0;   
               
           Psw_count = 0;   
           Data_Psw = "";  
      }
      
      void changePassword()
      {
           lcd.clear();
           lcd.setCursor( 0 , 0 );  
           lcd.print( "Master psw:" );  

           //master şifre dogru ise
           Get_new_psw(1);                                 
           String a = Data_Psw; 
           String b = Master_Psw;
           
        if(a!=b)
          { Serial.print( "Fault_KeyPad Master " );
            lcd.setCursor( 0 , 2 );   
            lcd.print( "Fault_KeyPad Master" ); 
            return;
          }     
      
           lcd.clear();
           lcd.setCursor( 0 , 0 );  
           lcd.print( "Type new psw:" ); 
           
           Get_new_psw(1); 
           a = Data_Psw;     
           
           Get_new_psw(2);
           b = Data_Psw;   
           
        if( a == b )
          {       
             Serial.println( "Password changed to " );
             Master_Psw = Data_Psw;    
             Serial.println( Master_Psw );
             
                byte i = 0, j = 1; 
             for ( int ee_addr = 16 ; ee_addr < 20 ; ee_addr++) // 
               {   
                   EEPROM.update( ee_addr, ( Master_Psw.substring( i , i + j ) ).toInt());
                   i = i + 1; 
               } 
          }
          else
          {
            lcd.setCursor(0, 3);   
            lcd.print("password  donot match"); 
            
            Serial.println("passwords do not match");
            Serial3.println("passwords do not match");
            delay(1000);      
          }
         clearData(); 
         Psw_Length = 4;
      }
      
      void Get_new_psw ( int line )
      {  
          Psw_Length = 4;
          clearData();
        while (Psw_Length > Psw_count)
        { 
          customKey = customKeypad.getKey();
             if ( customKey )
            {
                 Data_Psw = Data_Psw  + customKey;
                 lcd.setCursor( Psw_count, line );
                 lcd.print( Data_Psw [Psw_count] );
                 Serial.println( Data_Psw [Psw_count] );
                 Psw_count++;
            }
         }
      }
      
      void Hour_Calendar()
      { 
          tmElements_t tm;  
          if ( RTC.read ( tm ) ) 
          {
            //Serial.print("Ok, Time = ");
        
            _hour     = print2digits_Lcd( tm.Hour );   
            _minute   = print2digits_Lcd( tm.Minute );   
            _second   = print2digits_Lcd( tm.Second );    
            _day_week = print2digits_Lcd( tm.Wday );
            _day      = print2digits_Lcd( tm.Day );      
            _moon     = print2digits_Lcd( tm.Month );    
            _year     = print2digits_Lcd( tmYearToCalendar ( tm.Year ) );   
      
            byte _day_week_ = _day_week.toInt();  
            switch ( _day_week_ )   // Haftanın günü
            {
            case 2:
              _day_week_tr = ( "PT" );
              break;
            case 3:
              _day_week_tr = ( "SL" );
              break;
            case 4:
              _day_week_tr = ( "CR" );
              break;
            case 5:
              _day_week_tr = ( "PR" );
              break;
            case 6:
              _day_week_tr = ( "CM" );
              break;
            case 7:
              _day_week_tr = ( "CT" );
              break;
            case 1:
              _day_week_tr = ("PZ") ;
              break;
            }      
          }
      }
      
      String print2digits_Lcd ( int number )
      {
       String temp = ( String ) number; 
       
           if( temp.length() > 1 )
           { 
            return temp;   
           }
           else
           { 
            return ( '0' + temp );
           }  
      }
      
      /*
      void print2digits(int number)
      {
        if (number >= 0 && number < 10)
        {
          Serial.write('0');
        }
        Serial.print(number);
      }
      */
