# Smart Home Bilkent G2C
## Pc User Interface 

> Note: After the Java 8, module system came to Java. Because we have written 
this code in JDK v1.8.0 we don't use this module system.Moreover, after the Java 9 javafx library is removed from default. Therefore when 
you are running program in intelliJ, Please use JDK 1.8(Any java 8' JDK )(In intelliJ v2020.1,
you can download all JDK versions). However if you use Java JDK which is publish after the Java 8, you need to follow this steps(Second step is valid for only after the java 10);

1) Instead of dealing with all this, you can download java JDK 1.8 version and choose it from the project sdk. Or you can follow the steps 2-6.

2)[Go Gluon website](https://gluonhq.com/products/javafx/) and find suitable version for your JDK version and download it and add these libraries to project in project structere.
3) You have to add following codes to your module-info.java 
```
module ProjectName{
   requires javafx.fxml;
   requires javafx.controls;
   
   opens sample;  //package name of the main class
}
```
5) Enter the run configuration settings and add these following lines to VM option
```
--add-exports javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix
--add-exports javafx.controls/com.sun.javafx.scene.control=com.jfoenix
--add-exports javafx.base/com.sun.javafx.binding=com.jfoenix
--add-exports javafx.graphics/com.sun.javafx.stage=com.jfoenix
--add-exports javafx.base/com.sun.javafx.event=com.jfoenix
```
6) Add others libraries as a maven library. Maven links of each libraries can be found in the their github pages. You can reach these pages links which are below.Hopefully it should work.
---

>To experience the program there are for users in the program( Each user type has different features ).
```
 UserName       Password      User Type
- david     |    cs102    |    Parent
- nasuh     |    nasuh    |    Child
- mete      |    mete     |    Child
- ilke      |    ilke     |    Elder
```
---
- [Materials Used](#MaterialsUsed)
- [Features](#Features)
- [Useful Codes Explanations](#UsefulCodesExplanations)

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
* [WEATHER STACK(API)](https://weatherstack.com/) - Retrieve instant, accurate weather information for
any location in the world in lightweight JSON format
* [MAPBOX(API)](https://docs.mapbox.com/api/) - The Mapbox web services APIs allow you to programmatically access Mapbox tools and services
---
3) Libraries
* [controlsfx](https://github.com/controlsfx/controlsfx) - Custom controls for JavaFX
* [jSerialComm](https://fazecast.github.io/jSerialComm/) - jSerialComm is a Java library designed to provide a platform-independent way to access standard serial ports without requiring external libraries
* [JFoenix](https://github.com/jfoenixadmin/JFoenix) - JavaFX Material Design Library
* [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) - SQLite JDBC Driver
* [animate-fx](https://github.com/onexip/animate-fx) - Animation library for JavaFX

#### Features
##### Program Features
```
1) There are three option for user type ( CHILD/PARENT/ELDER ).

2) There are many features which can be personalized for each person.These are: 
2.1) Theme of user interface can be changed(There are 12 option).
2.2) Text mode can be closed or opened.
2.3) Sound mode can be closed or opended.
2.4) Each person can adjust sound mode volume level.
2.5) There are 5 Language for both sound and text modes.
2.6) Each person can get weather information, no matter where they want to learn 
about which part of the world.(Internet connection is necessity)
2.7) Each person has personal information card which can be updated by the users.
2.8) Each person can see other users' preferences.
2.9) Elder users have more simple and understandable user interface(bigger
buttons, bigger texts).

3) Parents can restrict access of child users to some home features(Such as
opening electricity, notification settings).

4) Notification settings can adjust by only parents.

5.1) Parents can remove profile of childeren with his password. 
5.2) Parents also can remove profiles of other parents and elders with their
password.
5.3) Childeren can remove only their profiles.
5.4) If program has only one parent, No one can delete this profile.

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
8.1) See their electricity run time each day on the graph(hours/day).
8.2) See their gas run time each day on the graph(hours/day).
8.3) See their green house average of each day on the graph.
8.4) Use weather service(internet connection is necessity).
8.5) Change personal information

9) Interface gives chance to choose which port we use when connecting to 
embedded system.  

10)Each user names are unique. Therefore if you try to get user name that is 
used by somenone, program doesn't permit to do that.

11)Program has help button which contains all information about the program.

(*)= In this demo, people can experience without connection. Program will
not five any error because we prevent it. However, In logic way, users 
should not able to use this features(7.1-7.14). In order to experience all
user interface, we use this way. However, if people want to experince all
things working, they have to use simulation program( proteus ) and arduino
IDE. If they setup them in their computers, program also can connected and 
work correctly. As a result, user can use this interface without error when
both connecting or not connecting. 
```
##### Technical Details
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

### Useful Codes Explanations
##### In here, I put some extra code with their explanation to be useful my friends or anyone who interested in this project.

>1. In javaFX, if you will use Threads, then you have to use Platform.runLater method.

```
new Thread( new Runnable() {
    @Override
    public void run() {
        //the codes are not related to GUI visual changes shoul be written here!!!
        
        Platform.runLater( new Runnable() {
        
            @Override
            public void run() {
                //the codes are related the GUI changes should be written here!!!!        
            }
        } );
    }
} ).start();
```
* EXAMPLE(In the code, It is not exactly same because In program there is lots of Exception probablity.
The only thing I want to show that is showing how Thread should use in javafx)
```
/*
The codes that create request from the server and wait response. When It is waiting, In order to prevent occuping default thread,
I used new thread
*/
new Thread( () -> {
    
    //this part make a new request and get the response. In other word, there is nothing about GUI
    if( weatherForecast == null )                                                                           
        weatherForecast = new WeatherForecast( loginUser.getLocation() );
               
    Platform.runLater( () -> {
    
        //After finished request and response process, for regulating GUI according to Information
        
        weatherUpdateSpinner.setVisible( false );
        settingWeatherForecastLabelValue.setText( weatherForecast.getWeather() );
        settingWeatherTemperatureLabelValue.setText( weatherForecast.getTemperature() + "°C" );
        settingWeatherHumidityLabelValue.setText( weatherForecast.getHumidity() );
        settingWeatherWindLabelValue.setText( weatherForecast.getWind() );
        informationTime.setText( weatherForecast.getLocalTime() );
        backgroundSetup( weatherForecast.getWeather() );
           
        }
    } );
} ).start();
```

---
>2. This one is about getting status of capslock when Program run at the beginning
```
/*
It returns boolean 
*/
capsLock = ( Toolkit.getDefaultToolkit().getLockingKeyState( java.awt.event.KeyEvent.VK_CAPS_LOCK ) );
```
---

>3. To embed css or tff files and using them.
3.1)for tff files
3.2)for css files
3.3)using this css file

* 3.1) for tff files
```
Font.loadFont( String urlStr, double size);

//IN other world, you can use like this;

Font.loadFont( yourSelectedClassName.class.getResource( "file path way according to selected class as a string" ).toExternalForm(), size );
```
* Example( After you do this, You can use this font in your css files )
```
Font.loadFont( LoginPanel.class.getResource( "styleSheets/font/Oswald-VariableFont_wght.ttf" ).toExternalForm(), 10 );
```

* 3.2) for css files
```
this.currenctClass.getResource( "file path way according to selected class as a string" ).toExternalForm();
```
* Example( After you do this, This gives a string to you )
```
this.getClass().getResource( "styleSheets/main_menu_light_theme.css" ).toExternalForm();
```

* 3.3) using this css file
```
yourLayoutMaterial.getStylesheets().removeAll( yourLayoutMaterial.getStylesheets() );
yourLayoutMaterial.getStylesheets().add( css );
```
* Example
```
commonBorderPane.getStylesheets().removeAll( commonBorderPane.getStylesheets() );
commonBorderPane.getStylesheets().add( css );
```
---

>4. Using sound files

```
AudioClip audioClip;
audioClip = new AudioClip( this.getClass().getResource( "sound file path way according to selected class as a string ).toString() );
audioClip.setVolume( between 0-1 (double) );
audioClip.setRate( double );
audioClip.play
```
* Example
```
AudioClip audioClip;
audioClip = new AudioClip( this.getClass().getResource( "music/suprise.mp3" ).toString() );
audioClip.setVolume( 0.8 );
audioClip.setRate( 1.1 );
audioClip.play
```
---

>5. Get available port list
```
SerialPort[] portNames;
portChooser.getItems().removeAll( portChooser.getItems() );
portNames = SerialPort.getCommPorts();

for( SerialPort portName : portNames )
   portChooser.getItems().add( portName.getSystemPortName() );

```
* Information( if you wonder getCommPorts(), you shout look at SerialPort )
---


>6. Read and write data using SerialPort class
6.1) Read data
6.2) Write data

* 6.1) Read data
```
home.getArduino().getSerialPort().setComPortTimeouts( SerialPort.TIMEOUT_NONBLOCKING, 0, 0 );
/*
The reason of this is that without this method is work correctly until It recieves longer string
.To prevent it, I put Thread.sleep()
*/
try {
   Thread.sleep( 200 );
} catch( InterruptedException e ) {
   e.printStackTrace();
}

StringBuilder out = new StringBuilder();
Scanner in = new Scanner( home.getArduino().getSerialPort().getInputStream() );

try {
   while( in.hasNextLine() )
      out.append( in.nextLine() );
} catch( Exception e ) {
   e.printStackTrace();
}
```

* 6.2) Write data
```
public void serialWrite( String string ) {
   PrintWriter printWriter;
   comPort.setComPortTimeouts( SerialPort.TIMEOUT_SCANNER, 0, 0 );
   
   try {
      Thread.sleep( 5 );
   } catch( Exception e ) {
      e.printStackTrace();
   }
   
   printWriter = new PrintWriter( comPort.getOutputStream() );
   printWriter.print( string );
   printWriter.flush();
}
```

