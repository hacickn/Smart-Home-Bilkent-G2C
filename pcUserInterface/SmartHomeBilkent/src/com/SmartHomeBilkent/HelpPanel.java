package com.SmartHomeBilkent;

import com.SmartHomeBilkent.utilities.dataBase.Users;
import com.SmartHomeBilkent.utilities.dataBase.fields.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpPanel implements Initializable {
   @FXML
   private AnchorPane helpMainPane, helpExitPane,
         helpUserProfilePane, helpMenuPane,
         helpSettingsPane, homeSettingPane,
         modsSettingPane, usersSettingPane,
         applicationSettingPane;

   @FXML
   private JFXButton helpExitButton, helpMenuButton,
         helpUserProfileButton, helpSettingsButton,
         goBackFromLogout, goBackFromUserProfile,
         goBackFromMenu, goBackFromSettings,
         applicationSettingButton, usersSettingButton,
         modsSettingButton, homeSettingButton,
         goBackFromApplicationSetting, goBackFromUsersSetting,
         goBackFromModsSetting, goBackFromHomeSetting;

   @FXML
   private JFXTextArea menuTextField, userProfileTextField,
         logoutTextField, applicationSettingTextArea,
         usersSettingTextArea, modsSettingTextArea,
         homeSettingTextArea;
   private User loginUser;

   @Override
   public void initialize( URL location, ResourceBundle resources ) {
      getLoginUser();
      if( loginUser.getPreferredLanguage().equals( "TÜRKÇE" ) ) { // türkçe
         menuTextField.setText( "" );
         userProfileTextField.setText( "" );
         logoutTextField.setText( "" );
         applicationSettingTextArea.setText( "" );
         usersSettingTextArea.setText( "" );
         modsSettingTextArea.setText( "" );
         homeSettingTextArea.setText( "" );
      } else if( loginUser.getPreferredLanguage().equals( "DEUTSCH" ) ) { //almanca
         menuTextField.setText( "" );
         userProfileTextField.setText( "" );
         logoutTextField.setText( "" );
         applicationSettingTextArea.setText( "" );
         usersSettingTextArea.setText( "" );
         modsSettingTextArea.setText( "" );
         homeSettingTextArea.setText( "" );
      } else { // ingilizce
         menuTextField.setText( "" );
         userProfileTextField.setText( "" );
         logoutTextField.setText( "" );
         applicationSettingTextArea.setText( "" );
         usersSettingTextArea.setText( "" );
         modsSettingTextArea.setText( "" );
         homeSettingTextArea.setText( "" );
      }

   }

   public User getLoginUser() {
      for( User u : Users.getInstance().getUserList() ) {
         if( u.getEnter().equals( "true" ) ) {
            loginUser = u;
            return u;
         }
      }
      return null;
   }

   @FXML
   void mainButtonOnAction( ActionEvent event ) {
      if( event.getSource() == helpExitButton ) {
         closeAllMainPanes();
         helpExitPane.setVisible( true );
      } else if( event.getSource() == helpMenuButton ) {
         closeAllMainPanes();
         helpMenuPane.setVisible( true );
      } else if( event.getSource() == helpUserProfileButton ) {
         closeAllMainPanes();
         helpUserProfilePane.setVisible( true );
      } else if( event.getSource() == helpSettingsButton ) {
         closeAllMainPanes();
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromLogout || event.getSource() == goBackFromUserProfile || event.getSource() == goBackFromMenu || event.getSource() == goBackFromSettings ) {
         closeAllMainPanes();
         helpMainPane.setVisible( true );
      }
   }

   private void closeAllMainPanes() {
      helpMainPane.setVisible( false );
      helpExitPane.setVisible( false );
      helpUserProfilePane.setVisible( false );
      helpMenuPane.setVisible( false );
      helpSettingsPane.setVisible( false );
   }

   @FXML
   void settingButtonOnAction( ActionEvent event ) {
      if( event.getSource() == applicationSettingButton ) {
         closeAllMainPanes();
         applicationSettingPane.setVisible( true );
      } else if( event.getSource() == modsSettingButton ) {
         closeAllMainPanes();
         usersSettingPane.setVisible( true );
      } else if( event.getSource() == usersSettingButton ) {
         closeAllMainPanes();
         modsSettingPane.setVisible( true );
      } else if( event.getSource() == homeSettingButton ) {
         closeAllMainPanes();
         homeSettingPane.setVisible( true );
      } else if( event.getSource() == goBackFromApplicationSetting ) {
         applicationSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromUsersSetting ) {
         usersSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromModsSetting ) {
         modsSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromHomeSetting ) {
         homeSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      }
   }

}
