package com.SmartHomeBilkent;

import com.SmartHomeBilkent.extra.dataBase.Users;
import com.SmartHomeBilkent.extra.dataBase.fields.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * a LoginPanel class implemented by Initializable
 *
 * @author Hacı Çakın
 * @version 28.04.2020
 */
public class LoginPanel extends Application implements Initializable {

   //properties
   @FXML
   private JFXButton loginButton;
   @FXML
   private JFXSpinner loginSpinner;
   @FXML
   private JFXTextField userNameField;
   @FXML
   private JFXPasswordField passwordField;
   @FXML
   private Label waningLabel,
         capslockOpen;
   private Boolean capsLock;


   //methods
   @FXML
   void toLogin() {
      if( userNameField.getText().length() > 0 && passwordField.getText().length() > 0 ) {
         for( User s : Users.getInstance().getUserList() ) {
            if( userNameField.getText().equals( s.getUserName() ) && passwordField.getText().equals( s.getPassword() ) ) {
               loginButton.setVisible( false );
               loginSpinner.setVisible( true );
               new Thread( new Runnable() {
                  @Override
                  public void run() {
                     try {
                        String fxmlAddress;
                        FXMLLoader load;
                        Parent root;
                        final Stage[] stage = new Stage[ 1 ];

                        if( s.getUserType().equals( "ELDER" ) || s.getUserType().equals( "ÄLTERE" ) || s.getUserType().equals( "YASLI" ) || s.getUserType().equals( "YASLİ" ) )
                           fxmlAddress = "view/elderMainPanel.fxml";
                        else
                           fxmlAddress = "view/mainPanel.fxml";
                        s.setEnter( "true" );
                        load = new FXMLLoader( getClass().getResource( fxmlAddress ) );
                        root = load.load();
                        Platform.runLater( new Runnable() {
                           @Override
                           public void run() {
                              stage[ 0 ] = new Stage();
                              stage[ 0 ].setTitle( "SMART HOME" );

                              if( fxmlAddress.equals( "view/elderMainPanel.fxml" ) )
                                 stage[ 0 ].setScene( new Scene( root, 800, 600 ) );
                              else if( fxmlAddress.equals( "view/mainPanel.fxml" ) )
                                 stage[ 0 ].setScene( new Scene( root, 800, 800 ) );
                              stage[ 0 ].setResizable( true );
                              stage[ 0 ].show();
                              userNameField.getScene().getWindow().hide();
                           }
                        } );

                     } catch( Exception e ) {
                        e.printStackTrace();
                     }
                  }
               } ).start();
               waningLabel.setVisible( false );
            }
         }
         waningLabel.setText( "USERNAME/PASSWORD IS WRONG" );
      } else {
         waningLabel.setVisible( true );
         waningLabel.setText( "PLEASE ENTER BOTH PASSWORD AND USERNAME" );
      }
   }

   @FXML
   void controlPressedCapslock( KeyEvent event ) {
      if( capsLock && event.getCode() == KeyCode.CAPS ) {
         capslockOpen.setVisible( true );
         capsLock = false;
      } else if( !capsLock && event.getCode() == KeyCode.CAPS ) {
         capslockOpen.setVisible( false );
         capsLock = true;
      }
   }

   @Override
   public void initialize( URL location, ResourceBundle resources ) {
      capsLock = ( Toolkit.getDefaultToolkit().getLockingKeyState( java.awt.event.KeyEvent.VK_CAPS_LOCK ) );
      waningLabel.setVisible( true );
      if( capsLock ) {
         capslockOpen.setVisible( true );
         capsLock = false;
      } else {
         capsLock = true;
      }

      Font.loadFont(
            LoginPanel.class.getResource( "styleSheets/font/Oswald-VariableFont_wght.ttf" ).toExternalForm(),
            10
      );
      Font.loadFont(
            LoginPanel.class.getResource( "styleSheets/font/PatrickHand-Regular.ttf" ).toExternalForm(),
            10
      );
      Font.loadFont(
            LoginPanel.class.getResource( "styleSheets/font/IndieFlower-Regular.ttf" ).toExternalForm(),
            10
      );
      Font.loadFont(
            LoginPanel.class.getResource( "styleSheets/font/Bahiana-Regular.ttf" ).toExternalForm(),
            10
      );
   }


   @Override
   public void start( Stage primaryStage ) throws Exception {
      primaryStage.setOnCloseRequest( new EventHandler< WindowEvent >() {
         @Override
         public void handle( WindowEvent event ) {
            Platform.exit();
            System.exit( 0 );
         }
      } );
   }

}
