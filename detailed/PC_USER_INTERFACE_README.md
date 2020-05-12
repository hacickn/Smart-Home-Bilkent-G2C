# Smart Home Bilkent G2C
## Pc User Interface 
> (Until Today)
To experience the program there are three users in the program

> Note: After the Java 11, module system came to Java. Because we write 
this code in jdk v1.8.0 we don't use this module system. Therefore when 
you are running program in intelliJ, Please use JDK 1.8(In intelliJ v2020.1,
you can download all JDK versions in the intelliJ) .
```
 UserName       Password      User Type
- david     |    cs102    |    Parent
- nasuh     |    nasuh    |    Child
- mete      |    mete     |    Child
- ilke      |    ilke     |    Elder
```
- [Materials Used](#MaterialsUsed)
- [Features](#Features)



#### Materials Used

1) Programs
* [IntelliJ IDEA Community Edition v2020.1](https://www.jetbrains.com/idea/)
* [JavaFX Scene Builder v8.5.0](https://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html)
* [Java v1.8.0_171-b11](https://www.java.com/en/)
* [DB Browser(SQLite) v3.11.2](https://sqlitebrowser.org/)
---
2) Web Sites
* [FLATICON](https://www.flaticon.com/) - The vector icons webpage
* [AMAZON POLLY](https://aws.amazon.com/polly/?nc1=h_ls) - Amazon Polly is a service that turns text into lifelike speech
* [WEATHER STACK](https://weatherstack.com/) - Retrieve instant, accurate weather information for
any location in the world in lightweight JSON format
---
3) Libraries
* [controlsfx](https://github.com/controlsfx/controlsfx) - Custom controls for JavaFX
* [jSerialComm](https://fazecast.github.io/jSerialComm/) - jSerialComm is a Java library designed to provide a platform-independent way to access standard serial ports without requiring external libraries
* [JFoenix](https://github.com/jfoenixadmin/JFoenix) - JavaFX Material Design Library
* [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) - SQLite JDBC Driver

#### Features
##### PROGRAM FEATURES
```
1) There are three option for user type ( CHILD/PARENT/ELDER ).

2) There are many features which can be personalized for each person.These are: 
2.1) Theme of user interface can be changed(There are 6 option).
2.2) Text mode can be closed or opened.
2.3) Sound mode can be closed or opended.
2.4) Each person can adjust sound mode volume level.
2.5) There are 3 Language for both sound and text modes.
2.6) Each person can get weather information, no matter where they want to learn 
about which part of the world.(Internet connection is necessity)
2.7) Each person has personal information card which can be updated by the users.
2.8) Each person can see other users' preferences.
2.9) Elder users have more simple and understandable user interface(bigger
buttons, bigger texts).

3) Parents can restrict access of child users to some home features(Such as
opening electricity, notification settings).

4) Notification settings can adjust by only parents.

5) Parents can remove profile of childeren with his password. 
5.1) Parents also can remove profiles of other parents and elders with their
password.
5.2) Childeren can remove only their profiles.
5.3) If program has only one parent, No one can delete this profile.

6) In emergency situation, users can regulate visual and auditory warnings.

7) In user interface, If program is connected status, users can;
7.1) Open/close electriciy.
7.2) Open/close gas.
7.3) Open/close water.
7.4) Open/close air motor.
7.5) Feed.
7.6) Open/close garden Light.
7.7) Open/close incoming water to aquairum.
7.8) Open/close outcoming water from the aquairum to water tank.
7.9) Open door.
7.10) Open/close internal siren.
7.11) Open/close external siren.
7.12) They can set all of these features(7.1-7.12) in one go, or seperately.
7.13) Set time of embedded system.
7.14) Make all aquiarum settings to automatic(feeding time, water exchange
day and time, air motor work amount and start time).

8) In user interface, If program is not connected status(*), users can;
8.1) See their electricity opening time each day on the graph(hours/day).
8.2) See their gas opening time each day on the graph(hours/day).
8.3) See their green house average of each day on the graph.
8.4) Use weather service.
8.5) Change personal information

9) Interface gives chance to choose which port we use when connecting to 
embedded system.  

10)Each user names are unique. Therefore if you try to get user name that is 
used by somenone, program doesn't permit to do that.

(*)= In this demo, people can experience without connection. Program will
not five any error because we prevent it. However, In logic way, users 
should not able to use this features(7.1-7.14). In order to experience all
user interface, we use this way. However, if people want to experince all
things working, they have to use simulation program( proteus ) and arduino
IDE. If they setup them in their computers, program also can connected and 
work correctly. As a result, user can use this interface without error when
both connecting or not connecting. 
```
##### TECHNICAL DETAILS
```
1) To communicate with database, Singleton patters is used.
2) Model-View-Controller pattern is used.
3) Getting data from the web api as a JSON format(for weather forecast).
4) All movements are recorded in databases(Electricity usage,gas usage, greenHouseValues etc.).
5) SerialPort is used to communicate with embedded system(Normally we want to add others 
options such as bluetooth or ethernet shild(LAN).However due to COVID'19 we can only
simulate serial port connection in the simulation program virtually)
6) To be contiuned...
```
