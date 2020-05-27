# Smart Home Bilkent G2C
## Android Application
> (Until Today)
To experience the app, follow these steps;
1)open the smarthome_v2 folder which is under androidApp folder with Android Studio.
2)If you don't have suitable sdk, download it in android studio
3)Build program and synchronize the gradle file if it did not
4)Wait all progress to be done,then run the app
5)You can register and start to use program


#### Materials Used

1) Programs
* Android Studio v3.6

---
2) Web Sites
* [FLATICON](https://www.flaticon.com/) - The vector icons webpage
* [Giphy](https://giphy.com/) -GIF's
* [WEATHER STACK(API)](https://weatherstack.com/) - Retrieve instant, accurate weather information for
any location in the world in lightweight JSON format
* [VECTOR STOCK](https://www.vectorstock.com/) - Background images.
---
3) Libraries
*  'com.google.firebase:firebase-auth:16.0.4' - for database
*  'com.google.firebase:firebase-database:16.0.4' - for database
*  'junit:junit:4.13'    
*  'com.github.PhilJay:MPAndroidChart:v3.0.3' - for graph
*  'com.github.Shashank02051997:FancyToast-Android:0.1.6' -for toast messages
*  'pl.droidsonroids.gif:android-gif-drawable:1.2.20' - for GIF's
*  'com.github.markushi:circlebutton:1.1' -for buttons

#### Features
##### PROGRAM FEATURES
```
1)Easy access to smart home with bluetooth connection. Basic options can be managed far from the pc. 

2)Main screen can control gas , electricity, garden light,aquarium ,water and green house power. 
2.1) Open/close electriciy.
2.2) Open/close gas.
2.3) Open/close water.
2.4) Open/close air motor.
2.5) Feed.
2.6) Open/close garden Light.
2.7) Open/close incoming water to aquairum.
2.8) Open/close outcoming water from the aquairum to water tank.
2.9) Open door.
2.10) Open/close internal siren.
2.11) Open/close external siren.
2.12) They can set all of these features(2.1-2.14) in one go, or seperately.
2.13) Set time of embedded system.
2.14) Make all aquiarum settings to automatic(feeding time, water exchange
day and time, air motor work amount and start time).

3) Gas , electricity and water usage can be visualize by graphics.
 
4)Each user names are unique. Therefore if you try to get user name that is 
used by somenone, program doesn't permit to do that.

5)Popups offers relaxable movement among the settings and simplicity of each screen reduce the complexity of usage.
6) There are 5 different themes.
7)There are 8 different font types.
```
##### TECHNICAL DETAILS
```
1) To communicate with database, Firebase is used.

```