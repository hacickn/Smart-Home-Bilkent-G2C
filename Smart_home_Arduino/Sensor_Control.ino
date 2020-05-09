      void Sensor_Cnt()
      {     //alarm_condition=0;
      
             if((digitalRead(Rain_sens)) && (!digitalRead(Raint_Device_Cnt))) 
              {
                 Serial.println("Rain_Sens_on");  
                 digitalWrite(Raint_Device_Cnt, HIGH);
              }
             else if((!digitalRead(Rain_sens)) && (digitalRead(Raint_Device_Cnt)))
              {
                 Serial.println("Rain_Sens_of");   
                 digitalWrite(Raint_Device_Cnt, LOW);
              }    
      
              if((digitalRead(soil_moisture_sens)) && (!digitalRead(soil_moisture_valf_Cnt)))   
              {
                 Serial.println("soil_moisture_sens_on");   
                 digitalWrite(soil_moisture_valf_Cnt, HIGH);
              }
              else if((!digitalRead(soil_moisture_sens)) && (digitalRead(soil_moisture_valf_Cnt)))
              {
                 Serial.println("soil_moisture_sens_of");   
                 digitalWrite(soil_moisture_valf_Cnt, LOW);
              } 
              
             if(digitalRead(Gas_Sensor))
             {
                bitWrite(alarm_condition,0,1); 
                Gas_Smoke_alarm(); 
             }
             else
                bitWrite(alarm_condition,0,0);     
             
             if(digitalRead(Smoke_Sensor))
             {
                bitWrite(alarm_condition,1,1);        
                Gas_Smoke_alarm();  
             }
             else
                bitWrite(alarm_condition,1,0);     
                
             if(digitalRead(Fire_Sensor))
             {
                bitWrite(alarm_condition,2,1);       
                Gas_Smoke_alarm(); 
             }
             else
                bitWrite(alarm_condition,2,0);     
             
             if(( door_encrypted ) && digitalRead( Motion_Sensor ) && ( psw_time == 0))
                 psw_time = 1;              
            
         if((!digitalRead( Gas_Sensor ))&&(!digitalRead( Smoke_Sensor )) && ( !digitalRead( Fire_Sensor )))
           {
             if (manual_on == false)
             {
              digitalWrite(electricity, LOW);      
              digitalWrite(gas, LOW);
             }
           }        
      
             if((!digitalRead( air_motor )) && ( water_change == 0) && ( Distance > Distance_Max ))
             {  // air_motor NK!!!!!
                digitalWrite(outgoing_water, LOW);    
                digitalWrite(incoming_water, LOW);
                 
                digitalWrite(air_motor, HIGH);    
                Serial.println("air_motor off");
                
                Serial.println("low water level");  
                Serial3.println("low water level");
             }      
                 
                    alarm_message="";  
            switch (alarm_condition)    
            {
                 case 1: alarm_message="Gas Alarm           "; break;     
                 case 2: alarm_message="Smoke Alarm         "; break;
                 case 3: alarm_message="Gas+Smoke alarm     "; break;  
                 case 4: alarm_message="Fire Buton          " ;break; 
                 case 5: alarm_message="Gas+Fire            " ;break;
                 case 6: alarm_message="Gas+Smoke           " ;break;
                 case 7: alarm_message="Gas+Smoke+Fire      " ;break;
                 
                 case 8: alarm_message="motion Alarm        "; break;
                 case 9: alarm_message="Motion+Gas Alarm    "; break;
                 case 10:alarm_message="Motion+Smoke  Alarm "; break;
                 case 11:alarm_message="Motion+Smoke+Gas    "; break;
                 case 12:alarm_message="Motion+Fire Buton   "; break;
                 case 13:alarm_message="Motion+Fire+Gas     "; break;
                 case 14:alarm_message="Motion+Fire+Smoke   "; break;
                 case 15:alarm_message="Motion+Fire+Smok+Gas"; break; 
                 
                 case 16:alarm_message="Psw Alarm           "; break;
                 case 17:alarm_message="Psw+Gas Alarm       "; break;
                 case 18:alarm_message="Psw+Smoke Alarm     "; break;
                 case 19:alarm_message="Psw+Smoke+Gas       "; break;
                 case 20:alarm_message="Psw+Fire Buton      "; break;
                 case 21:alarm_message="Psw+Fire+Gas        "; break;
                 case 22:alarm_message="Psw+Fire+Smoke      "; break;
                 case 23:alarm_message="Psw+Fire+Smoke+Gas  "; break; 
                 case 24:alarm_message="Psw+Motion          "; break;
                 case 25:alarm_message="Psw+Motion+Gas      "; break;
                 case 26:alarm_message="Psw+Motion+Smoke    "; break;
                 case 27:alarm_message="Psw+Motion+Smoke+Gas"; break;
                 case 28:alarm_message="Psw+Motion+Fire     "; break;
                 case 29:alarm_message="Psw+Motion+Fire+Gas "; break;
                 case 30:alarm_message="Psw+Motion+Fire+Smok"; break;
                 case 31:alarm_message="Psw+Mot+Fir+Smok+Gas"; break;
                 
                 case 32:alarm_message="Water Decreased     " ;break;
                 //default:alarm_message="Ozel durum";           break;        
            }      
          //Serial.println(alarm_condition);
      }
      
      void Gas_Smoke_alarm()
        { 
           digitalWrite(air_motor, LOW);        
           digitalWrite(water_valve, LOW);
           
           digitalWrite(electricity, HIGH);     
           digitalWrite(gas, HIGH); 
           
           digitalWrite(incoming_water, LOW);    
           digitalWrite(outgoing_water, LOW);
        }
