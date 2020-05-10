package com.SmartHomeBilkent;

import animatefx.animation.FadeIn;
import com.SmartHomeBilkent.utilities.dataBase.DatabaseConnection;
import com.SmartHomeBilkent.utilities.dataBase.Users;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * a Main class
 * @author Hacı Çakın
 * @version 09.04.2020
 */
public class Main extends Application {

   /**
    * It is a start method that is Overrode
    * @param primaryStage is a Stage input parameter
    * @throws Exception
    */
   @Override
   public void start( Stage primaryStage ) throws Exception {
      Parent root = FXMLLoader.load( getClass().getResource( "view/loginPanel.fxml" ) );
      primaryStage.setTitle( "SMART HOME" );
      primaryStage.setScene( new Scene( root, 400, 400 ) );
      primaryStage.setResizable( false );
      primaryStage.getIcons().add( new Image(Main.class.getResourceAsStream( "styleSheets/images/smartHome.png" )) );
      primaryStage.setOnCloseRequest( event -> {
         Platform.exit();
         System.exit( 0 );
      } );
      primaryStage.show();
      new FadeIn( root ).play();
   }

   /**
    * It is a init method that is Overrode
    * @throws Exception
    */
   @Override
   public void init() throws Exception {
      super.init();
      DatabaseConnection.getInstance().connectDatabase();
      Users.getInstance().getAllUsers();
   }

   /**
    * It is a stop method that is Overrode
    * @throws Exception
    */
   @Override
   public void stop() throws Exception {
      super.stop();
      DatabaseConnection.getInstance().closeConnectionDatabase();
   }

   /**
    * It is a main method
    * @param args
    */
   public static void main( String[] args ) {
      launch( args );
   }
}
