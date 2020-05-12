# CS102 ~ Personal Log page ~
****
## İLKE DOĞAN ~ 21702215
****

On this page I will keep a weekly record of what I have done for the CS102 group project. This page will be submitted together with the rest of the repository, in partial fulfillment of the CS102 course requirements.

---

### ~ 02.03.2020 ~
In the first week, I started to report on the systems that can be used in the smart home. I suggested these systems and shared them with my group friends at the group meeting. I started to research about the decisions taken at the meeting. We attended microcontroller and Arduino trainings with my friends. Also during this week, we have shared information about the Arduino and C ++ language that we intend to use in the system at the group meeting. At the same time, [Arduino](https://www.arduino.cc/en/Tutorial/HomePage), I searched and watched tutorils related to Arduino. (Almost 10 hours in total)

---

### ~ 09.03.2020 ~
This week, with the start of the exams, I continued to research the systems we can use in the smart home. I searched for features that people are looking for in a smart home from the Internet [Current consumer attitudes towards Smart Home](https://www.citizensadvice.org.uk/Global/CitizensAdvice/Energy/Smart%20homes%20final%20report%20(new%20Traverse%20logo).pdf). We also continued to participate in Arduino and digital trainings with my friends.(Almost 4 hours in total)

---

### ~ 16.03.2020 ~
With the onset of the pandemic period, I started to work more intensively on the project. I shared the idea of ​​adding the Aquarium and Greenhouse systems to the smart home with my group friends, and we thought that it would actually be a useful idea for the pandemic period. I made researches and made reports about how plant production and aquarium cycle can be achieved. I continued my first week based on the research of the digital systems of the sensors that we will generally use like from [Direnc.net](https://www.direnc.net/) and [Robotus](https://www.robotus.net/). I also thought that a simulation would help us implement and test the project, with the idea that project presentations could be made at home.  I have tried a few researches and applications on digital design in [Proteus](https://www.labcenter.com/) simulation. (almost 30 hours)

---

### ~ 23.03.2020 ~
This week, I continued my sensor research that can be used  [robitshop](https://www.robitshop.com/) and [Robotistan](https://www.robotistan.com/). Although I initially thought of the sensor that uses infrared beam to measure the water level in the aquarium, I then thought about the breaks that might have been in the water and decided to use an ultrasonic sensor. In the encryption system we will use, I decided to choose motion, gas, smoke sensors and fire button, lcd and lcd types. With the researches of the code we will write in Arduino, [Mobilhanem.com](https://www.mobilhanem.com/arduino-dersleri-serial-port-ve-fonctionlari/) and [gelecegiyazanlar.turkcell](https: //gelecegiyazanlar.turkcell. com.tr/konu/arduino/egitim/arduino-201/seri-port-uzinden-haberlesme) I started writing the codes. Examine the connection diagrams of these systems [Arduino Turkey](http://arduinoturkiye.com/arduino-ile-seri-iletisim-2/) I started to build our C ++ code. I also thought that being a 4x4 Keypad is suitable for features such as deletion, changing the password and I started to add these systems to the simulation in Proteus. I also found libraries not in Proteus' library on the internet and added them to the library of the simulation program. I examined the individual connection protocols of these sensors and added them to the code in C ++ and to Proteus.(nearly 30- 40 hours)

---

### ~ 30.03.2020 ~
This week, I continued to review serial communication protocols [Tripod](http://yildizertan.tripod.com/whatsnew.htm) and [Hasan Eren Keskin](https://herenkeskin.com/seri-haberlesme-protokolleri-uart-spi-i2c/). I have examined the protocols I can find about how to get and use the data from the serial port, ie the PC interface, and produced a protocol like 
manual_on#: that based on the characthers '#' and ':' . It had to work according to the protocol to run manually and sensor-based, and I did not add the library since the serial communication library came embedded in Arduino. We searched for serial communication and Bluetooth connections and tried to ensure that Arduino and pc interface work together with Hacı. Also, I did the Timer library research to arrange the operation of the units used in Ardunio, and I chose the "MsTimer2.h" library because it worked better than other versions of the latest MsTimer.(nearly 20-30 hours)

---

### ~ 06.04.2020 ~
This week, I decided to use a servo motor for the feeding system to be used in the Aquarium and I used the "Servo.h" library. I also added the "DS1307RTC.h" library to get instant Clock and calendar data. However, I encountered a bug, after giving the user 3 cogs in the encryption system, when the luck was reset, it entered the endless loop and
```
else
         {
             if (give_chang == 0)
             {
                give_chang = 1; // infinite chance
                bitwrit A (alarm_conditio's, 4.1);
             }
         }
           clearData ();
       }
```
I fixed it with the code.

---

### ~ 13.04.2020 ~
  This week I started working on the pc interface and started adding on greenhouse. In Arduino, we measured the moisture of the soil and sent it to the smart home as data, and we had to make the pc interface read it. We added the soil moisture sensor and used DHT222 to measure the temperature and humidity of the environment in the greenhouse and added the library "DHT.h". I added the classes regarding GreenhHouse that are
GreenHouseData, GreenHouseValues, and GreenHouse. From the Ardunio data from greenhouse is measured and can be sent to pc interface. In this classes I also design the view of greenhouse button and labels.(nearly estimation 10-20 hours)

---

### ~ 20.04.2020 ~
This week I started working on weather class on the pc interface with Hacı. We designed background of the weather class in Main panel. We arranged the idea that it should suit based on the weather and we added some of pictures to pictures file and arrange the colors which is suitable for background color.  I wrote Ultrasonic sensor methods that are echo and trigger and I did search about the way to use these connection and I found the formula from the internet t  = s /  v  => 10cm/ 0..34 = 294 micro sec * distance: => s = t*0.034/2q. (nearly 10-15 hours)

---

### ~ 27.04.2020 ~
This week I almost packed the program in Arduino. I moved on to the simulations in Proteus. I started shortening the codes. I had my friends tested the interface and tried to find the bugs of the interface and took notes of them and corrected them with the Hacı.

---

### ~ 04.05.2020 ~
This week we started to develop programs in Arduino and Pc interface. We've duplicated the user's background theme option and I added 3 backgrounds. We researched these pictures from the internet and I added them to the images file. Then I made the color adjustments for these 3 background pics in the interface.

****

### ~ 11.05.2020 ~