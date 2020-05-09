package com.SmartHomeBilkent;

import animatefx.animation.FadeIn;
import com.SmartHomeBilkent.extra.dataBase.DatabaseConnection;
import com.SmartHomeBilkent.extra.dataBase.Users;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * a Main class
 *
 * @author Hacı Çakın
 * @version 28.04.2020
 */
public class Main extends Application {

   @Override
   public void start( Stage primaryStage ) throws Exception {
      Parent root = FXMLLoader.load( getClass().getResource( "view/loginPanel.fxml" ) );
      primaryStage.setTitle( "SMART HOME" );
      primaryStage.setScene( new Scene( root, 400, 400 ) );
      primaryStage.setResizable( false );
      primaryStage.setOnCloseRequest( event -> {
         Platform.exit();
         System.exit( 0 );
      } );
      primaryStage.show();
      new FadeIn( root ).play();
   }

   @Override
   public void init() throws Exception {
      super.init();
      DatabaseConnection.getInstance().connectDatabase();
      Users.getInstance().getAllUsers();
   }

   @Override
   public void stop() throws Exception {
      super.stop();
      DatabaseConnection.getInstance().closeConnectionDatabase();
   }

   public static void main( String[] args ) {
      launch( args );
   }
}
