İlke Doğan	21702215
Hacı Çakın	21802641
 
### It works with the program Proteus. When the user open Proteus, Arduino, and PC Interface together, the user should open all the programs at the same time and use these commands through Proteus.
### Is it manuel ?

```
manual_on#:
manual_off#:
```

### If we want to control elements on the system one by one the protokols that we created
```
* buzzer_on#:
* buzzer_off#:
* electricity_on#:
* electricity_off#:
* gas_on#:
* gas_off#:
* incoming_water_on#:
* incoming_water_off#:
* outgoing_water_on#:
* outgoing_water_off#:
* air_motor_on#:
* air_motor_off#:
* door_on#:   		 // door closing automatically
* water_valve_on#: 
* water_valve_off#:
* external_siren_on#:
* external_siren_off#:
* feeding_on#:		 //feeding closing automatically
* garden_lights_on#:
* garden_lights_off#:
* soil_moisture_valf_on#:
* soil_moisture_valf_off#:
* rain_cnt_device_on#:
* rain_cnt_device_off#:
```
### If we want to control elements on the system in one pocket the protokols that we created

```
* Smart_App#B1E1G1I1U0A1D1W1X1F1R1S1N1:

* Smart_App#B0E1G1I0U0A1D0W1X0F0R0S0N0:

* Smart_App#B0E0G0I0U0A0D0W0X0F0R0S0N0:

- B1 ==> buzzer_on#:
- B0 ==> buzzer_off#:
- E1 ==> electricity_on#:
- E0 ==> electricity_off#:
- G1 ==> gas_on#:
- G0 ==> gas_off#:
- I1 ==> incoming_water_on#:
- I0 ==> incoming_water_off#:
- U1 ==> outgoing_water_on#:
- U0 ==> outgoing_water_off#:
- A1 ==> air_motor_on#:
- A0 ==> air_motor_off#:
- D1 ==> door_on#:
- D0 ==> door_off#:
- W1 ==> water_valve_on#:
- W0 ==> water_valve_off#:
- X1 ==> external_siren_on#:
- X0 ==> external_siren_off#:
- F1 ==> feeding_on#:
- F0 ==> feeding_off#:
- R1 ==> garden_lights_on#:
- R0 ==> garden_lights_off#:
- S1 ==> soil_moisture_valf_on#:
- S0 ==> soil_moisture_valf_off#: 
- N1 ==> rain_cnt_device_on#:
- N0 ==> rain_cnt_device_off#: 
```

### From jAVA interface, Setting Clock Information
#### hour-minutes-week-day-mounth-year 
```
clock#0246570228042020:   23 characters
      02  57  28  2020:
        44  02  04 
      hr  sn  dy  year
        mn  we  mo
```
### From jAVA interface, Setting Clock Information
#### hour-mounth-day-year 
```
aquarium#0245000246020302440202: 32 characters
         024500 
               02460203
                       02440202
        feeding+ water+ air_motor
         hrmnsn+hrmnsndy+hrmnsnHR
```
### Connection
```
String readSerial()
{   
    String temp_data;   

if (Serial.available() > 0 || Serial3.available() > 0)   
  {
     while (Serial.available())       // for serial port
      {  
      delay(10); 
           char data= (Serial.read());
       if (data == '\n') 
          {//Serial.println(temp_data);
           break;} 
      temp_data+= data; 
      }  
      
      while (Serial3.available())     // for BLUETOOTH 
      {  
      delay(10); 
           char data= (Serial.read());
       if (data == '\n') 
          {//Serial.println(temp_data);
           break;} 
      temp_data+= data; 
      }  
    return temp_data;
  }
}
```
---
```
  char * ac="ledac";
  char * kapat="ledkapat";
  
  byte buffer[34];
  char * answer;
  byte incoming_data_lenght;
  Serial.setTimeout(300L);
  
  incoming_data_lenght=Serial.readBytesUntil('#',(char *) buffer,30);
  Serial.println(incoming_data_lenght);
  
  for(byte i=incoming_data_lenght; i < 30; i++)
      buffer[i]=' ';

      answer=strstr(buffer,ac);
      if(answer)
      {
        Serial.println(answer);
      }
      
      answer=strstr(buffer,kapat);
      if(answer)
      {
         Serial.println(answer);
      }
```
---
```
  * byte decToBcd(byte val) {
  return ((val/10*16) + (val%10));
}
byte bcdToDec(byte val) {
  return ((val/16*10) + (val%16));
}
```
---
```
  * char CardNumber[9] = "ABCDEF99";
byte j;
auto getNum = [](char c)
{
  return c > '9' ? c - 'a' + 10 : c - '0';
};

char arr[10];
char i;
byte *ptr = out;

for (i = 0; i < 8; i++)
{
  arr[i] = CardNumber[i];
}

for (char *index = arr ; *index ; ++index, ++ptr )
{
  *ptr = (getNum( *index++ ) << 4) + getNum(*index);
}

//Check converted byte values.
Serial.print("Card Number in Bytes :");
for (j = 0; j < 4; j++)
{
  Serial.print(out[j], HEX );
}
Serial.println();
```
---
```
 char hex[17]="0123456789ABCDEF";

void ShowHex(byte convertByte){
  Serial.print( hex[(convertByte >>4) & 0x0F]);
  Serial.println( hex[convertByte & 0x0F]);
}
```
---
```
   int hex_to_int(unsigned char hex[], int count)
{
int sum = 0;
int i;
int temp;

for (i = 0; i < count; i++)
{
temp = (hex[i] & 0xf0U) >> 4;
sum = sum*16 + temp;
temp = (hex[i] & 0xofU);
sum = sum*16 + temp;
}
return sum;
}
```






