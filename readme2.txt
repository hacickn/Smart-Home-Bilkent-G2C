G2C
SMART HOME
1) İLKE DOĞAN 21702215
2) HACI ÇAKIN 21802641
3) METEHAN SAÇAKÇI 21802788
4) ERENGAZİ MUTLU 21803676
5) TARIK BUĞRA KARALI 21703937
6) NASUH DİNÇER 21702933

1)Description of the project: Smart Home is a project that makes it possible to control home attributes from a
well-designed pc and android interfaces. Therefore, our main target is to provide the program that
gives people freedom to control their houses.
---------------------------------------------------------------------------

2)Project Current Status: Our project has four main parts which are simulation made with proteus, arduino code,pc
program and android application.Our project almost done(according to what we thought at the beginning of the 
semester).
---------------------------------------------------------------------------

3)What works and what doesn't?
-Simulation, arduino code and pc program are completely finished(Of course It cannot be finished exactly but 
we did more than what we think at the beginning of the semester). There will be bugs most probably but we fixed all
bugs that we realized. Simulation,arduino code and pc program are working and their connection are also working 
correctly. All facilities of application are working correctly. Database connection and data flow are also created
and working correctly.Its weather API also works.For more specific details, you can look 
(detailed/PC_USER_INTERFACE_README.md) or (detailed/ARDUINO_README.md).

-For the android application, without connection part we can say it is almost finished(connection to embedded system
does not work). It does not have as many features as the computer program have but it works(not completely but 
almost everythings works). It can connect to firebase and data flow happens.It does not database table for the 
charts in the program which is a deficient.Its weather API also works.
---------------------------------------------------------------------------

4)What remains to be done?
-We need to work on android applicaion to add more functional features and connect it to embedded system.
---------------------------------------------------------------------------

5)Each member's contribution of the project
-We are mainly divided into two groups. One group gave weight to pc program, arduino and simulation. Other group
gave weight to android application.

5.1)For the pc program, ardunio and simulation; Hacı Çakın, İlke Doğan and Metehan Saçakçı were responsible.
We splitted these parts like that(these are who gave weight more, we always helps to each other);
-Hacı Çakın = pc program's normal panel(all of the things about the main part of the program), Relational Databases, 
,connection of program side and weather API
-Metehan Saçakçı = pc program's elder panel(all of the things about the elder panel)
-İlke Doğan = simulation, arduino code, help panel and connection of the embedded system side
(we all gave parts from our own parts to each other)

5.2)For the android application; Tarık Buğra Karali, Nasuh Dinçer and Erengazi Mutlu were responsible.
We splitted these parts like that(these are who gave weight more, we always helps to each other);
-Tarık Buğra Karali = Designed login screen, main screen and features(Gas, Water,
Aquarium, GreenHouse) and pages related to the main screen
-Nasuh Dinçer =  Database (login, register, sign out, features), Register page
-Erengazi Mutlu = Settings(app and home settings using pop up), profile card,main screen
features(Electricity, Garden Light, Door)
---------------------------------------------------------------------------

6)Materials that used

6.1)Programs

6.1.1)For pc program
*IntelliJ IDEA Community Edition v2020.1
*JavaFX Scene Builder v8.5.0
*Java v1.8.0_171-b11
*DB Browser(SQLite) v3.11.2

6.1.2)For android app
*Android Studio v3.6

6.1.3)For simulation and embedded system
*Proteus v7.7
*Proteus v8.5 
*Arduino 1.8.12

6.2)Libraries

6.2.1)For pc program
*controlsfx - Custom controls for JavaFX
*jSerialComm - jSerialComm is a Java library designed to provide a platform-independent way to access standard 
serial ports without requiring external libraries
*JFoenix - JavaFX Material Design Library
*sqlite-jdbc - SQLite JDBC Driver
*animate-fx - Animation library for JavaFX

6.2.2)For android app
*'com.google.firebase:firebase-auth:16.0.4' - for database
*'com.google.firebase:firebase-database:16.0.4' - for database
*'junit:junit:4.13'
*'com.github.PhilJay:MPAndroidChart:v3.0.3' - for graph
*'com.github.Shashank02051997:FancyToast-Android:0.1.6' -for toast messages
*'pl.droidsonroids.gif:android-gif-drawable:1.2.20' - for GIF's
*'com.github.markushi:circlebutton:1.1' -for buttons

6.2.3)For simulation and embedded system
*MsTimer2
*Keypad
*LiquidCrystal
*Servo
*EEPROM
*DS1307RTC
*SHT21
*DHT

6.3)Websites

6.3.1)For pc program
*FLATICON - The vector icons webpage
*AMAZON POLLY - Amazon Polly is a service that turns text into lifelike speech
*WEATHER STACK(API) - Retrieve instant, accurate weather information for any location in the world in lightweight 
JSON format
*MAPBOX(API) - The Mapbox web services APIs allow you to programmatically access Mapbox tools and services

6.3.2)For android app
*FLATICON - The vector icons webpage
*Giphy -GIF's
*WEATHER STACK(API) - Retrieve instant, accurate weather information for any location in the world in lightweight 
JSON format
*VECTOR STOCK - Background images.

6.3.3)For simulation and embedded system
*robitshop
*Arduino
*Current consumer attitudes towards Smart Home
*Direnc.net
*Robotus
*Proteus
*robitshop
*Robotistan
*Mobilhanem.com
*gelecegiyazanlar.turkcell
*Arduino Turkey
*Tripod
*Hasan Eren Keskin
(You can find all of the links below the detailed folder which is in repository folder)
---------------------------------------------------------------------------

7)For detailed intructions;
-Because our project has three main parts, you can follow them seperately to setup and open.
Their intructions are in detailed folder which is in project folder(We already wrote all of them seperately).
Note: Proteus which is a simulation program is not uploaded therefore it does not have intructions.