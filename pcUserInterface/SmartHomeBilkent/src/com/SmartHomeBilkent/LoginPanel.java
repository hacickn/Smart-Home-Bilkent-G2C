package com.SmartHomeBilkent;

import animatefx.animation.FadeIn;
import com.SmartHomeBilkent.utilities.dataBase.Users;
import com.SmartHomeBilkent.utilities.dataBase.fields.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * a LoginPanel class implemented by Initializable
 * @author Hacı Çakın
 * @version 28.04.2020
 */
public class LoginPanel implements Initializable {

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

   /**
    * It is a toLogin method that control the passwords and usernames
    */
   @FXML
   private void toLogin() {
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

                        if( s.getUserType().equals( "ELDER" )
                              || s.getUserType().equals( "ÄLTERE" )
                              || s.getUserType().equals( "ANCIEN" )
                              || s.getUserType().equals( "ANCİEN" )
                              || s.getUserType().equals( "SAMBUCO" )
                              || s.getUserType().equals( "YASLI" )
                              || s.getUserType().equals( "YASLİ" ) )
                           fxmlAddress = "view/elderMainPanel.fxml";
                        else
                           fxmlAddress = "view/mainPanel.fxml";
                        s.setEnter( "true" );
                        load = new FXMLLoader( getClass().getResource( fxmlAddress ) );
                        root = load.load();
                        Platform.runLater( () -> {
                           stage[ 0 ] = new Stage();
                           stage[ 0 ].setTitle( "SMART HOME" );

                           if( fxmlAddress.equals( "view/elderMainPanel.fxml" ) )
                              stage[ 0 ].setScene( new Scene( root, 800, 600 ) );
                           else
                              stage[ 0 ].setScene( new Scene( root, 800, 800 ) );
                           stage[ 0 ].setResizable( true );
                           stage[ 0 ].setOnCloseRequest( event -> {
                              Platform.exit();
                              for(User s: Users.getInstance().getUserList()){
                                 s.setEnter( "false" );
                              }
                              System.exit( 0 );
                           } );
                           stage[ 0 ].getIcons().add( new Image(Main.class.getResourceAsStream( "styleSheets/images/smartHome.png" )) );
                           stage[ 0 ].show();
                           userNameField.getScene().getWindow().hide();
                           new FadeIn( root ).play();
                        } );

                     } catch( Exception e ) {
                        e.printStackTrace();
                        loginButton.setVisible( true );
                        loginSpinner.setVisible( false );
                     }
                  }
               } ).start();
               waningLabel.setVisible( false );
            }
         }
         waningLabel.setText( "USERNAME/PASSWORD IS WRONG, TRY AGAIN" );
      } else {
         waningLabel.setVisible( true );
         waningLabel.setText( "PLEASE ENTER BOTH PASSWORD AND USERNAME" );
      }
   }

   /**
    * It is a controlPressedCapslock method that control the conditions of capslock
    * @param event is a KeyEvent input parameter
    */
   @FXML
   private void controlPressedCapslock( KeyEvent event ) {
      if( capsLock && event.getCode() == KeyCode.CAPS ) {
         capslockOpen.setVisible( true );
         capsLock = false;
      } else if( !capsLock && event.getCode() == KeyCode.CAPS ) {
         capslockOpen.setVisible( false );
         capsLock = true;
      }
   }

   /**
    * It is a initialize method that run when this class runs
    * @param location  is a KeyEvent input parameter
    * @param resources is a KeyEvent input parameter
    */
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
   }
}
