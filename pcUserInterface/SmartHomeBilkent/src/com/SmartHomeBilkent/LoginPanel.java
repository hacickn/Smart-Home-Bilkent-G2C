package com.SmartHomeBilkent;

import com.SmartHomeBilkent.extra.dataBase.Users;
import com.SmartHomeBilkent.extra.dataBase.fields.User;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * a LoginPanel class implemented by Initializable
 *
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
   @FXML
   void toLogin() {
      if( userNameField.getText().length() > 0 && passwordField.getText().length() > 0 ) {
         for( User s : Users.getInstance().getUserList() ) {
            if( userNameField.getText().equals( s.getUserName() ) && passwordField.getText().equals( s.getPassword() ) ) {
               try {
                  Platform.runLater( new Runnable() {
                     @Override
                     public void run() {
                        try {
                           String fxmlAddress;
                           FXMLLoader load;
                           Parent root;
                           Stage stage;
                           if( s.getUserType().equals( "ELDER" ) || s.getUserType().equals( "ÄLTERE" ) || s.getUserType().equals( "YASLI" ) || s.getUserType().equals( "YASLİ" ) )
                              fxmlAddress = "view/elderMainPanel.fxml";
                           else
                              fxmlAddress = "view/mainPanel.fxml";

                           s.setEnter( "true" );
                           load = new FXMLLoader( getClass().getResource( fxmlAddress ) );
                           root = load.load();
                           stage = new Stage();
                           stage.setTitle( "SMART HOME" );
                           if( fxmlAddress.equals( "view/elderMainPanel.fxml" ) )
                              stage.setScene( new Scene( root, 800, 600 ) );
                           else if( fxmlAddress.equals( "view/mainPanel.fxml" ) )
                              stage.setScene( new Scene( root, 800, 800 ) );
                           stage.setResizable( true );
                           stage.show();
                        } catch( IOException exception ) {
                           exception.printStackTrace();
                        }
                        userNameField.getScene().getWindow().hide();
                     }
                  } );
               } catch( Exception e ) {
                  e.printStackTrace();
               }
            }
         }
         waningLabel.setText( "USERNAME/PASSWORD IS WRONG" );
      } else {
         waningLabel.setText( "PLEASE ENTER BOTH PASSWORD AND USERNAME" );
      }
      waningLabel.setVisible( true );
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
      if( capsLock ) {
         capslockOpen.setVisible( true );
         capsLock = false;
      } else {
         capsLock = true;
      }
   }
}
