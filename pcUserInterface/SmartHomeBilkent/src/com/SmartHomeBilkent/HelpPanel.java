package com.SmartHomeBilkent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class HelpPanel {
   @FXML
   private AnchorPane helpMainPane, helpExitPane,
         helpUserProfilePane, helpMenuPane,
         helpSettingsPane;

   @FXML
   private JFXButton helpExitButton, helpMenuButton,
         helpUserProfileButton, helpSettingsButton;

   @FXML
   void mainButtonOnAction( ActionEvent event) {
      if(event.getSource() == helpExitButton){
         closeAllMainPanes();
         helpExitPane.setVisible( true );
      } else if(event.getSource() == helpMenuButton){
         closeAllMainPanes();
         helpMenuPane.setVisible( true );
      } else if(event.getSource() == helpUserProfileButton){
         closeAllMainPanes();
         helpUserProfilePane.setVisible( true );
      } else if(event.getSource() == helpSettingsButton){
         closeAllMainPanes();
         helpSettingsPane.setVisible( true );
      }
   }

   private void closeAllMainPanes(){
      helpMainPane.setVisible( false );
      helpExitPane.setVisible( false );
      helpUserProfilePane.setVisible( false );
      helpMenuPane.setVisible( false );
      helpSettingsPane.setVisible( false );
   }
}
