      /* 
       *  SMART HOME ARDUINO PROGRAM   
      */

      #include "MsTimer2.h"            // Her 20 msn de bir timer kesmesi için
      #include <Keypad.h>              // Keypad 'e basıldıgında , Keypad'ten deger almak için 4*4 lük
      #include "LiquidCrystal.h"       // Lcd ye değer basmak için 4*20 lik
      #include <Servo.h>               // Servo motor TIMER1 
      #include <EEPROM.h>              // Kullanıcı rom'una veri saklamak ve saklananı okumak için
      //#include <Wire.h>                // I2C protoküyle veri almak için
      #include <DS1307RTC.h>           // Saat ve takvim verilerini almak için
      #include <SHT21.h>
      
      #include <DHT.h>     // dht11 (%20 ve %80 arasındaki nem ölçümünde %5’lik bir doğruluk sağlar)
                           // 0 ile 50 °C arasındaki sıcaklık ölçümünde 2 °C’lik bir doğruluk sağlar.
                           // dht22 ( %0 ile %100 arasındaki nem ölçümünde %2 ile %5 ’lik bir doğruluk sağlar)
                           // -40 ile 80 °C arasındaki sıcaklık ölçümünde 0.5 °C’lik bir doğruluk sağlar.
      SHT21      sht; 
      
      float      temp;   // variable to store temperature
      float      humidity; // variable to store hemidity
      
      //dht        DHT11_green_house;
      #define    DHTPIN  A12
      #define    DHTTYPE DHT22   // DHT 22  (AM2302), AM2321
      DHT        dht( DHTPIN , DHTTYPE );
      
      float      temp_green_house;
      float      humid_green_house;
      
      /*Servo motor variables
       * 
       */
      const int  servoPin = 12; 
      Servo      myServo; 
      
      const int  servoPin_Ldr_curtain_Cnt = 8; 
      Servo      myServo_Ldr_curtain_Cnt;
      
      const int  servoPin_Rain_Device_Cnt = 7; 
      Servo      myServo_Rain_Device_Cnt;
      
      //LiquidCrystal(RS, RW, E, D4, D5, D6, D7)
      //byte = uint8_t 
      uint8_t rs = 45 , rw = 46 , en = 44 , d4 = 47 , d5 = 48 , d6 = 49 , d7 = 50;
      LiquidCrystal lcd(rs, rw, en, d4, d5, d6, d7);
      
      byte       Psw_Length = 4;
      String     Data_Psw = ""; 
      String     Master_Psw = "";//eğer ff se progrm ilkkez çalıştırılr ff i  = "1234"; 
      int        Psw_count = 0, master_count = 0;
      char       customKey;
      int        give_change = 3;
      
      const byte ROWS = 4;
      const byte COLS = 4;
      
      char hexaKeys[ROWS][COLS] = 
      {
        {'1', '2', '3', 'A'},
        {'4', '5', '6', 'B'},
        {'7', '8', '9', 'C'},
        {'*', '0', '#', 'D'}
      };
      
      byte rowPins[ROWS] = {43, 42, 41, 40};
      byte colPins[COLS] = {39, 38, 37, 36};
      
      Keypad customKeypad = Keypad( makeKeymap ( hexaKeys ) , rowPins , colPins , ROWS , COLS );
      
      // Hardware interrupt 
      volatile unsigned long LastPulseTimeA;
      #define trigPinA       13
      #define echoPinA        2    //Int.0
      static unsigned int     Triger = 0;
      static unsigned int     Distance = 0;
      uint8_t                 Distance_Max = 14;
      uint8_t                 Distance_Min = 6;
      
      uint8_t        Gas_Sensor    = A0;
      uint8_t        Smoke_Sensor  = A1;
      uint8_t        Fire_Sensor   = A2;
      uint8_t        Motion_Sensor = A3;
      uint8_t        Ldr_Sensor    = A13;
      uint8_t        soil_moisture_sens = A14;
      uint8_t        Rain_sens     = A15;
      
      float          light_sens_value;
      
      uint8_t        Buzzer         = 22;
      uint8_t        electricity    = 23;
      uint8_t        gas            = 24;
      uint8_t        incoming_water = 25;
      uint8_t        outgoing_water = 26;
      uint8_t        air_motor      = 27;
      uint8_t        door           = 28;
      uint8_t        water_valve    = 29;
      uint8_t        external_siren = 30;
      uint8_t        garden_lights  = 31;
      uint8_t        soil_moisture_valf_Cnt = 32;
      uint8_t        Raint_Device_Cnt = 33;
      uint8_t        Green_House_Heater_Cnt = 34;
      //const uint8_t port_Pins[ ] = {Buzzer,electricity,gas};
      
      char           House_movement = ' ';
      uint8_t        Speed = 60;
      
      String        _hour , _minute , _second;
      String        _day_week;
      String        _day , _moon , _year;
      String        _day_week_tr;
        
      unsigned long previousMillisTask_1 = 0;
      unsigned long previousMillisTask_2 = 0;
      unsigned long previousMillisTask_3 = 0;
      
      // different intervals for each  
      int           intervalTask_1 = 200; //200;
      int           intervalTask_2 = 1000; //1000;
      int           intervalTask_3 = 2000; //2000;2sn
      
      String        incoming_data;
      String        first_data;
      String        last_data; 
      String        feeding_hour , feeding_minute , feeding_second;
      String        water_change_hour , water_change_minute , water_change_second , water_change_day;
      String        air_motor_hour , air_motor_minute ,air_motor_second , air_motor_operating;     
      
      uint8_t       water_change = 0;
      bool          air_motor_on = false;
      int           air_motor_time = 0;
      
      static unsigned int _time = 0;                       // use volatile for shared variabless

      static unsigned int curtain_time = 0;
      static unsigned int feeding_time = 0;
      static unsigned int door_time = 0;
      bool                window_open=false;
      
      bool          door_encrypted = false;
      bool          door_psw_enb = false;
      unsigned int  psw_time = 0;  
      byte          alarm_condition = 0;
      String        alarm_message = "";
      bool          manual_on = false;


      /* setup()Method
       *  
       */ 
      void setup()
      {
        attachInterrupt( 0 , EchoPinA_ISR , CHANGE );  
      
        /* attach lib is embedded library in Arduino
        *  it uses Timer1 digital circuit
        */ 
        myServo.attach( servoPin );
        myServo.write( 0 );
      
        myServo_Ldr_curtain_Cnt.attach( servoPin_Ldr_curtain_Cnt );
        myServo_Ldr_curtain_Cnt.write( 0 );
      
        myServo_Rain_Device_Cnt.attach( servoPin_Rain_Device_Cnt );
        myServo_Rain_Device_Cnt.write( 0 );
        
        pinMode( soil_moisture_sens , INPUT_PULLUP );
        pinMode( Rain_sens ,      INPUT_PULLUP );
        pinMode( Ldr_Sensor ,     INPUT );
            
        pinMode( trigPinA ,     OUTPUT );
        pinMode( echoPinA ,     INPUT );
      
        pinMode( Gas_Sensor,     INPUT );
        pinMode( Smoke_Sensor,   INPUT );
        pinMode( Fire_Sensor,    INPUT );
        pinMode( Motion_Sensor,  INPUT );

         // for (int i=22; i <= 24; i++) //portlar pasif  
         // pinMode(port_Pins[i], OUTPUT);  
      
        pinMode( Buzzer ,         OUTPUT );  // Buzzer Declared as Output 
        pinMode( electricity ,    OUTPUT );
        pinMode( gas ,            OUTPUT );
        pinMode( incoming_water , OUTPUT );
        pinMode( outgoing_water , OUTPUT );
        pinMode( air_motor ,      OUTPUT );
        pinMode( door ,           OUTPUT );
        pinMode( water_valve ,    OUTPUT );
        pinMode( external_siren , OUTPUT );
        pinMode( garden_lights ,  OUTPUT );
        pinMode( soil_moisture_valf_Cnt , OUTPUT );
        pinMode( Raint_Device_Cnt , OUTPUT );  
        pinMode( Green_House_Heater_Cnt , OUTPUT );
        
        last_data = "B0E0G0I0U0A0D0W0X0F0R0S0N0";      
        Smart_App_Cnt();
        
       // Serial Port
        Serial.begin( 9600 );     
        Serial.println( "start" );
        
        // Bluetooth
        Serial3.begin( 9600 );    
        Serial3.write( "start" );
        //Bluetooth_Setting();
        
        // set up the LCD's number of columns and rows:
        lcd.begin( 20, 4 );//20,4
        lcd.display();
        lcd.clear();       
        clearData();              
        Smart_home_data(); 
      
        MsTimer2::set( 20, timing );//20ms period
        MsTimer2::start();
      }

      /* EchoPinA_ISR() Method
       *  
       */ 
      
      void EchoPinA_ISR() 
      {
        /**  speed of sound v=340m/s  => v=0.34 cm/micro sn
         *  time =distance / speed
         *  t    =   s     /   v     => 10cm/ 0..34=294micro sn
         *   distance:               => s=t*0.034/2q
         */
         
          static unsigned long startTimeA;
         
          if ( digitalRead( echoPinA ) )      // Gone HIGH
              startTimeA = micros();
          else                            // Gone LOW
          {
              LastPulseTimeA = micros() - startTimeA;
      
              //Serial.print("Mesafe :");  
              //Serial.print(LastPulseTimeA); Serial.print('\t');
              Distance = ( LastPulseTimeA / 2 ) / 29.1,1;
              Distance = Distance * 0.034 / 2 ;
              //Serial.print(Distance); 
              //Serial.println(" cm");   
              Triger = 0;         
          }
      }

      
      /**  timing() 
       * MsTimer2 Library
       */
      void timing()  // MsTimer2 => 20ms period
      {        
           _time++;       
        if( _time == 50 ) // 50 * MsTimer2 = 1000msn =1sn
           {     
             _time = 0;  
             if( ( alarm_condition != 0) && ( !manual_on ) )
                {
                     digitalWrite( external_siren , HIGH );  
                     digitalWrite( Buzzer , HIGH );
                }
                else if( ( alarm_condition == 0 ) && ( !manual_on ) )
                {
                     digitalWrite( external_siren , LOW );  
                     digitalWrite( Buzzer , LOW );
                }
                
             if( ( psw_time > 0 ) && ( !manual_on ) )
                digitalWrite( Buzzer , ( !digitalRead( Buzzer ) ) );
             else if( ( psw_time == 0 )&& ( !manual_on ) )
                digitalWrite( Buzzer , LOW );
           }         
      
             
        if( feeding_time > 0 )
          { 
                if( feeding_time++ >= 100 )
                {
                   feeding_time = 0;
                   myServo.write( 0 );  
                   Serial.println( "feeding off" );
                }
          }
      
       if( curtain_time > 0 )
          { 
                if( curtain_time++ >= 100 )
                {
                   // open curtain
                   curtain_time = 0;
                   myServo_Ldr_curtain_Cnt.write( 0 );  
                   Serial.println( "curtain off" );
                }
          }
          
        if( door_time > 0 ) 
          { 
                if( door_time++ >= 50 )
                {
                   door_time = 0 ;
                   digitalWrite( door, LOW );   
                   Serial.println( "door open" );
                }
          }
      
         // evden çıkma uyarısı verme buzzer ı (iç siren)
        if( psw_time > 0 )
          {                
                if( psw_time++ >= 500 )   // 10sn
                {
                   psw_time = 0;                 
                                       
                       if ( ( door_psw_enb ) && ( door_encrypted == false ) )
                         { 
                          door_encrypted = true;               
                          Serial.println( "door_encrypted _on" );
                         }
                        else if( digitalRead( Motion_Sensor ) && ( door_encrypted ) )    
                         { 
                           bitWrite( alarm_condition , 3 , 1 );               
                         }
                }
          }        
      
            if( Triger == 0 )
            {
                digitalWrite( trigPinA , LOW ); 
                // Time to stay at 0
                delayMicroseconds( 2 ); 
                digitalWrite( trigPinA , HIGH ); 
                // Time to stay at 1
                delayMicroseconds( 10 );      
                digitalWrite( trigPinA , LOW );
                Triger = 1;
            }   
      }
      
        /**  Main Method
         *  loop()
         */
      void loop()
      {
              unsigned long currentMillis = millis();
       
         // time to toggle Task1=200
         if ( ( unsigned long )( currentMillis - previousMillisTask_1) >= intervalTask_1 ) 
         {
                customKey = customKeypad.getKey();
            if ( customKey )
                Key_Pad();       
            previousMillisTask_1 = currentMillis;  // save current time to Task1 
         }
      
            // time to toggle Task2=1000
        if ( ( unsigned long )( currentMillis - previousMillisTask_2 ) >= intervalTask_2 ) 
        {   
                 House_Temp_Humidity();        
                 Hour_Calendar();  
                 Sensor_Cnt(); 
                 Display_Refresh();    
                 light_sens_state();
                 
              if ( !manual_on )     
                  aquarium_control();   
                     
            previousMillisTask_2 = currentMillis;  // save current time to Task2 
        }
           
            // time to toggle Task2=2000
        if ( ( unsigned long )( currentMillis - previousMillisTask_3 ) >= intervalTask_3 ) 
        {   
                    humid_green_house = dht.readHumidity();
                    temp_green_house  = dht.readTemperature();
                      
             if( temp_green_house <= 20 )    
                digitalWrite( Green_House_Heater_Cnt , HIGH );
             else if( temp_green_house > 20 ) 
               digitalWrite( Green_House_Heater_Cnt , LOW );
      
            previousMillisTask_3 = currentMillis;  // save current time to Task2 
        }
      
        if ( Serial.available() > 0 || Serial3.available() > 0 )   
        {        
                 MsTimer2::stop();
                 Serial_House_Cnt();
                 MsTimer2::start();
        }
     }

     //HC-05 Bluetooth Modülü                           
      void Bluetooth_Setting()
      {
            String _name = "Bluet_Cnt_Car_rk";
            int _psw = 1111;
           // String uart = "9600,0,0";
           
            //"AT+NAME=Bluet_Cnt_Car_rk"
            Serial3.print( "AT + NAME = " );
            Serial3.println( _name );
            Serial.print( "Name is set " );
            Serial.println( _name );
            delay( 1000 );
            Serial3.print( "AT + PSWD = " );
            Serial3.println( _psw );
            Serial.print( "Password set " );
            Serial.println( _psw );
            //delay(1000);
            //Serial3.print("AT+UART=");
            //Serial3.println(uart);
            //Serial.print("Baud rate set: ");
            //Serial.println(uart);
            delay( 2000 );
            Serial.println( "Process completed" );
      
        /*
            //Handshake 
            Serial.println("Awaiting communication with the Bluetooth Module");
                 String  smstxt;    char txtMsg[20]; 
            blue.println("Mrb Blue");  
            
            while (!blue.available());
                         
            if (blue.available() > 0)     // BLUETOOTH için
                {  
                   readSerial(txtMsg);
                   smstxt=txtMsg;   Serial.println(smstxt);
                }  
            
            if(smstxt=="mrb") 
                   Serial.println("Communication with the Bluetooth Module has been established");
             else
                   Serial.println("Communication with the Bluetooth Module failed");
          */
      }                                                               
