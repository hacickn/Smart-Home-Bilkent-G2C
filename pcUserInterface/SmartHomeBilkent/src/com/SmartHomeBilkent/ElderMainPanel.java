package com.SmartHomeBilkent;

import com.SmartHomeBilkent.extra.dataBase.Users;
import com.SmartHomeBilkent.extra.dataBase.fields.User;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class ElderMainPanel implements Initializable {
   //properties
   @FXML
   private Pane mainElderPanelMenu;

   @FXML
   private Label dayMessage;

   @FXML
   private Label time;

   @FXML
   private JFXButton userProfileButtonElder;

   @FXML
   private ImageView userProfileElderActiveImage;

   @FXML
   private JFXButton settingsButtonElder;

   @FXML
   private ImageView settingsButtonElderActiveImage;

   @FXML
   private JFXButton houseMenuButtonElder;

   @FXML
   private ImageView houseMenuElderActiveImage;

   @FXML
   private Label userProfileSubLabelNotActive;

   @FXML
   private Label userProfileSubLabelActive;

   @FXML
   private Label menuSubLabelNotActive;

   @FXML
   private Label menuSubLabelActive;

   @FXML
   private Label settingsSubLabelNotActive;

   @FXML
   private Label settingsSubLabelActive;

   @FXML
   private Pane houseMenuElderPane;

   @FXML
   private JFXButton electricityButtonElder;

   @FXML
   private Pane electricityPanelElder;

   @FXML
   private Label electricityPanelElderLabel;

   @FXML
   private JFXToggleButton electricityPanelElderSwitch;

   @FXML
   private Label electricityPanelElderLastOpening;

   @FXML
   private Label electricityPanelElderLastClosure;

   @FXML
   private JFXButton electricityPanelElderCloseButton;

   @FXML
   private ImageView electricityPanelElderCloseButtonActive;

   @FXML
   private Label electricityLabelElder;

   @FXML
   private Line electricityLineElder;

   @FXML
   private JFXButton lightsButtonElder;

   @FXML
   private Pane lightsPanelElder;

   @FXML
   private Label lightsPanelElderLabel;

   @FXML
   private JFXToggleButton lightsPanelElderSwitch;

   @FXML
   private Label lightsPanelElderLastOpening;

   @FXML
   private Label lightsPanelElderLastClosure;

   @FXML
   private JFXButton lightsPanelElderCloseButton;

   @FXML
   private ImageView lightsPanelElderCloseButtonActive;

   @FXML
   private Label lightsLabelElder;

   @FXML
   private Line lightsLineElder;

   @FXML
   private JFXButton waterButtonElder;

   @FXML
   private Pane waterPanelElder;

   @FXML
   private Label waterPanelElderLabel;

   @FXML
   private JFXToggleButton waterPanelElderSwitch;

   @FXML
   private Label waterPanelElderLastOpening;

   @FXML
   private Label waterPanelElderLastClosure;

   @FXML
   private JFXButton waterPanelElderCloseButton;

   @FXML
   private ImageView waterPanelElderCloseButtonActive;

   @FXML
   private Label waterLabelElder;

   @FXML
   private Line waterLineElder;

   @FXML
   private JFXButton temperatureButtonElder;

   @FXML
   private Pane temperaturePanelElder;

   @FXML
   private Label temperaturePanelElderLabel;

   @FXML
   private JFXToggleButton temperaturePanelElderSwitch;

   @FXML
   private Label temperaturePanelElderLastOpening;

   @FXML
   private Label temperaturePanelElderLastClosure;

   @FXML
   private JFXButton temperaturePanelElderCloseButton;

   @FXML
   private ImageView temperaturePanelElderCloseButtonActive;

   @FXML
   private Label temperatureLabelElder;

   @FXML
   private Line temperatureLineElder;

   @FXML
   private JFXButton houseMenuBackButtonElder;

   @FXML
   private Label houseMenuBackButtonSubLabel;

   @FXML
   private Label houseMenuFirstLabelElder;

   @FXML
   private JFXButton nextPageHouseSettings;

   @FXML
   private Pane settingsElderPanel;

   @FXML
   private JFXButton applicationSettingsElderButton;

   @FXML
   private Label applicationSettingsElderLabel;

   @FXML
   private Line applicationSettingsElderLine;

   @FXML
   private JFXButton modsSettingsElderButton;

   @FXML
   private Label modsSettingsElderLabel;

   @FXML
   private Line modsSettingsElderLine;

   @FXML
   private JFXButton themeSettingsElderButton;

   @FXML
   private Label themeSettingsElderLabel;

   @FXML
   private Line themeSettingsElderLine;

   @FXML
   private JFXButton usersSettingsElderButton;

   @FXML
   private Label usersSettingsElderLabel;

   @FXML
   private Line usersSettingsElderLine;

   @FXML
   private JFXButton homeSettingsElderButton;

   @FXML
   private Label homeSettingsElderLabel;

   @FXML
   private Line homeSettingsElderLine;

   @FXML
   private JFXButton languageSettingsElderButton;

   @FXML
   private Label languageSettingsElderLabel;

   @FXML
   private Line languageSettingsElderLine;

   @FXML
   private JFXButton emergencySettingsElderButton;

   @FXML
   private Label emergencySettingsElderLabel;

   @FXML
   private Line emergencySettingsElderLine;

   @FXML
   private JFXButton notificationSettingsElderButton;

   @FXML
   private Label notificationSettingsElderLabel;

   @FXML
   private Line notificationSettingsElderLine;

   @FXML
   private JFXButton connectionSettingsElderButton;

   @FXML
   private Label connectionSettingsElderLabel;

   @FXML
   private Line connectionSettingsElderLine;

   @FXML
   private JFXButton settingsBackButtonElder;

   @FXML
   private Label settingsBackButtonSubLabel;

   @FXML
   private Label houseMenuFirstLabelElder1;

   @FXML
   private Pane userSettingsElderPanelMenu;

   @FXML
   private JFXButton userProfileBackButtonElder;

   @FXML
   private Label userProfileBackButtonElderSubLabel;

   @FXML
   private Label userSettingsFirstLabelElder;

   @FXML
   private Label userSettingsNameElder;

   @FXML
   private JFXTextField userSettingsNameElderText;

   @FXML
   private Label userSettingsSurnameElder;

   @FXML
   private JFXTextField userSettingsSurnameElderText;

   @FXML
   private Label userSettingsDateElder;

   @FXML
   private JFXTextField userSettingsDateElderText;

   @FXML
   private Label userSettingsGenderElder;

   @FXML
   private JFXTextField userSettingsGenderElderText;

   @FXML
   private Label userSettingsUsernameElder;

   @FXML
   private JFXTextField userSettingsUsernameElderText;

   @FXML
   private Label userSettingsPasswordElder;

   @FXML
   private JFXTextField userSettingsPasswordElderText;

   @FXML
   private JFXPasswordField userSettingsPasswordElderTextSecret;

   @FXML
   private JFXButton userSettingsChangeInfoButton;

   @FXML
   private Label userSettingsChangeInfoButtonSubLabel;

   @FXML
   private ImageView userSettingsChangeInfoButtonImage;

   @FXML
   private JFXButton userSettingsChangeUserElderButton;

   @FXML
   private JFXButton userSettingsUPChangeButton;

   @FXML
   private Label userSettingsUPChangeLabel;

   @FXML
   private ImageView userSettingsUPChangeButtonImage;

   @FXML
   private Pane userSettingsInfoChangerPanel;

   @FXML
   private Label userSettingsNameElderChange;

   @FXML
   private JFXTextField userSettingsNameElderTextChange;

   @FXML
   private Label userSettingsSurnameElderChange;

   @FXML
   private JFXTextField userSettingsSurnameElderTextChange;

   @FXML
   private Label userSettingsDateElderChange;

   @FXML
   private JFXTextField userSettingsDateElderTextChange;

   @FXML
   private Label userSettingsGenderElderChange;

   @FXML
   private JFXRadioButton userSettingsGenderMale;

   @FXML
   private JFXRadioButton userSettingsGenderFemale;

   @FXML
   private JFXButton userSettingsChangeSave;

   @FXML
   private Label userSettingsChangeSaveSubLabel;

   @FXML
   private ImageView userSettingsChangeSaveImage;

   @FXML
   private JFXButton userSettingsChangeBackButton;

   @FXML
   private Label userSettingsChangeBackButtonSubLabel;

   @FXML
   private ImageView userSettingsChangeBackButtonImage;

   @FXML
   private Pane userSettingsUIChangerPanel;

   @FXML
   private Label userSettingsUNElderChange;

   @FXML
   private JFXTextField userSettingsUNElderChangeText;

   @FXML
   private Label userSettingsPasswordChangeElderCurrent;

   @FXML
   private JFXTextField userSettingsPasswordChangeElderTextCurrent;

   @FXML
   private Label userSettingsPasswordChangeElderNew;

   @FXML
   private JFXTextField userSettingsPasswordChangeElderNewText;

   @FXML
   private Label userSettingsPasswordChangeElderNewConfirm;

   @FXML
   private JFXTextField userSettingsPasswordChangeElderNewConfirmText;

   @FXML
   private JFXButton userSettingsPanelTwoChangeSaveButton;

   @FXML
   private ImageView userSettingsPanelTwoChangeSaveButtonImage;

   @FXML
   private Label userSettingsPanelTwoChangeSaveLabel;

   @FXML
   private JFXButton userSettingsPanelTwoChangeBackButton;

   @FXML
   private ImageView userSettingsPanelTwoChangeBackButtonImage;

   @FXML
   private Label userSettingsPanelTwoChangeBackLabel;

   @FXML
   private Pane houseMenuElderPane2;

   @FXML
   private JFXButton aquariumButtonElder;

   @FXML
   private Pane aquariumPanelElder;

   @FXML
   private Label aquariumPanelElderLabel;

   @FXML
   private JFXToggleButton aquariumPanelElderSwitch;

   @FXML
   private Label aquariumPanelElderLastOpening;

   @FXML
   private Label aquariumPanelElderLastClosure;

   @FXML
   private JFXButton aquariumPanelElderCloseButton;

   @FXML
   private ImageView aquariumPanelElderCloseButtonActive;

   @FXML
   private JFXButton aquariumPanelElderSettingsButton;

   @FXML
   private Label aquariumLabelElder;

   @FXML
   private Line aquariumLineElder;

   @FXML
   private JFXButton greenhouseButtonElder;

   @FXML
   private Pane greenhousePanelElder;

   @FXML
   private Label greenhousePanelElderLabel;

   @FXML
   private JFXToggleButton greenhousePanelElderSwitch;

   @FXML
   private Label greenhousePanelElderLastOpening;

   @FXML
   private Label greenhousePanelElderLastClosure;

   @FXML
   private JFXButton greenhousePanelElderCloseButton;

   @FXML
   private ImageView greenhousePanelElderCloseButtonActive;

   @FXML
   private Label greenhouseLabelElder;

   @FXML
   private Line greenhouseLineElder;

   @FXML
   private JFXButton gasButtonElder;

   @FXML
   private Pane gasPanelElder;

   @FXML
   private Label gasPanelElderLabel;

   @FXML
   private JFXToggleButton gasPanelElderSwitch;

   @FXML
   private Label gasPanelElderLastOpening;

   @FXML
   private Label gasPanelElderLastClosure;

   @FXML
   private JFXButton gasPanelElderCloseButton;

   @FXML
   private ImageView gasPanelElderCloseButtonActive;

   @FXML
   private Label gasLabelElder;

   @FXML
   private Line gasLineElder;

   @FXML
   private JFXButton doorButtonElder;

   @FXML
   private Pane doorPanelElder;

   @FXML
   private Label doorPanelElderLabel;

   @FXML
   private JFXToggleButton doorPanelElderSwitch;

   @FXML
   private Label doorPanelElderLastOpening;

   @FXML
   private Label doorPanelElderLastClosure;

   @FXML
   private JFXButton doorPanelElderCloseButton;

   @FXML
   private ImageView doorPanelElderCloseButtonActive;

   @FXML
   private Label doorLabelElder;

   @FXML
   private Line doorLineElder;

   @FXML
   private JFXButton houseMenuBackButtonElder2;

   @FXML
   private Label houseMenuBackButtonSubLabel2;

   @FXML
   private Label houseMenuFirstLabelElder2;

   @FXML
   private JFXButton returnPageHouseSettings;

   @FXML
   private Pane aquariumSettingsPanel;

   @FXML
   private Label aquariumMenuFirstLabelElder;

   @FXML
   private JFXButton aquariumMenuBackButtonElder;

   @FXML
   private Label aquariumMenuBackButtonSubLabel;

   @FXML
   private Label speciesOfFishLabel;

   @FXML
   private ChoiceBox< ? > speciesOfFishChoice;

   @FXML
   private Label FeedingStartTimeLabel;

   @FXML
   private JFXTimePicker FeedingStartTimeTimePicker;

   @FXML
   private Label weeklyWaterExchangeDayAndTimeLabel;

   @FXML
   private ChoiceBox< ? > weeklyWaterExchangeDayAndTimeChoice;

   @FXML
   private JFXTimePicker weeklyWaterExchangeDayAndTimeTimePicker;

   @FXML
   private Label dailyAirEngineRunTimeandStartTimeLabel;

   @FXML
   private JFXTimePicker dailyAirEngineRunTimeandStartTimeTimePicker;

   @FXML
   private JFXButton aquariumSaveButton;

   @FXML
   private Label aquariumSaveButtonLabel;


   public User loginUser;
   //constructors
   //public ElderMainPanel()
   //{
   //initialize();
   //    clock();
   //}

   //methods
   @FXML
   void actionPerformed( ActionEvent event ) {
      // settings elder panel -MS 23.04.2020-
      if( event.getSource( ) == settingsButtonElder ) {
         setMainMenuInvisible( true );
         settingsElderPanel.setVisible( true );
         settingsElderPanel.setDisable( false );
      } else if( event.getSource( ) == settingsBackButtonElder ) {
         settingsElderPanel.setVisible( false );
         setMainMenuInvisible( false );
      }
      // settings elder panel END -MS 23.04.2020
      else if( event.getSource( ) == houseMenuButtonElder ) {
         setMainMenuInvisible( true );
         houseMenuElderPane.setVisible( true );
         houseMenuElderPane.setDisable( false );
      } else if( event.getSource( ) == houseMenuBackButtonElder ) {
         houseMenuElderPane.setVisible( false );
         setMainMenuInvisible( false );
      }
      // userSettingsElderPanelMenu -MS 23.04.2020-
      else if( event.getSource( ) == userProfileButtonElder ) {
         setMainMenuInvisible( true );
         userSettingsElderPanelMenu.setVisible( true );
         userSettingsElderPanelMenu.setDisable( false );
      } else if( event.getSource( ) == userProfileBackButtonElder ) {
         userSettingsElderPanelMenu.setVisible( false );
         setMainMenuInvisible( false );
      }
      // userSettingsElderPanelMenu END -MS 23.04.2020-
      else if( event.getSource( ) == electricityButtonElder ) {
         electricityButtonElder.setVisible( false );
         electricityPanelElder.setVisible( true );
         electricityPanelElder.setDisable( false );
      } else if( event.getSource( ) == electricityPanelElderCloseButton ) {
         electricityButtonElder.setVisible( true );
         electricityPanelElder.setVisible( false );
         electricityPanelElder.setDisable( true );
      } else if( event.getSource( ) == waterButtonElder ) {
         waterButtonElder.setVisible( false );
         waterPanelElder.setVisible( true );
         waterPanelElder.setDisable( false );
      } else if( event.getSource( ) == waterPanelElderCloseButton ) {
         waterButtonElder.setVisible( true );
         waterPanelElder.setVisible( false );
         waterPanelElder.setDisable( true );
      } else if( event.getSource( ) == temperatureButtonElder ) {
         temperatureButtonElder.setVisible( false );
         temperaturePanelElder.setVisible( true );
         temperaturePanelElder.setDisable( false );
      } else if( event.getSource( ) == temperaturePanelElderCloseButton ) {
         temperatureButtonElder.setVisible( true );
         temperaturePanelElder.setVisible( false );
         temperaturePanelElder.setDisable( true );
      } else if( event.getSource( ) == lightsButtonElder ) {
         lightsButtonElder.setVisible( false );
         lightsPanelElder.setVisible( true );
         //setLightsSwitchStatus();
         lightsPanelElder.setDisable( false );
      } else if( event.getSource( ) == lightsPanelElderCloseButton ) {
         lightsButtonElder.setVisible( true );
         lightsPanelElder.setVisible( false );
         lightsPanelElder.setDisable( true );
      }
      //user change info -MS 24.04.2020-
      else if( event.getSource( ) == userSettingsChangeInfoButton ) {
         userSettingsInfoChangerPanel.setVisible( true );
         userSettingsInfoChangerPanel.setDisable( false );
         userSettingsNameElderTextChange.setText( userSettingsNameElderText.getText( ) );
         userSettingsSurnameElderTextChange.setText( userSettingsSurnameElderText.getText( ) );
         userSettingsDateElderTextChange.setText( userSettingsDateElderText.getText( ) );
      } else if( event.getSource( ) == userSettingsChangeBackButton ) {
         userSettingsInfoChangerPanel.setVisible( false );
         userSettingsInfoChangerPanel.setDisable( true );
      }
      //user change info END -MS 24.04.2020-
      // user-password change -MS 26.04.2020-
      else if( event.getSource( ) == userSettingsUPChangeButton ) {
         userSettingsUIChangerPanel.setVisible( true );
         userSettingsUIChangerPanel.setDisable( false );
         userSettingsUNElderChangeText.setText( userSettingsUsernameElderText.getText( ) );
      } else if( event.getSource( ) == userSettingsPanelTwoChangeBackButton ) {
         userSettingsUIChangerPanel.setVisible( false );
         userSettingsUIChangerPanel.setDisable( true );
      }
      // user-password change END -MS 26.04.2020-
      // aquarium -MS 02.05.2020-
      else if( event.getSource( ) == aquariumButtonElder ) {
         aquariumButtonElder.setVisible( false );
         aquariumPanelElder.setVisible( true );
         aquariumPanelElder.setDisable( false );
      } else if( event.getSource( ) == aquariumPanelElderCloseButton ) {
         aquariumButtonElder.setVisible( true );
         aquariumPanelElder.setVisible( false );
         aquariumPanelElder.setDisable( true );
      }
      // aquarium END -MS 02.05.2020-
      // greenhouse -MS 02.05.2020-
      else if( event.getSource( ) == greenhouseButtonElder ) {
         greenhouseButtonElder.setVisible( false );
         greenhousePanelElder.setVisible( true );
         greenhousePanelElder.setDisable( false );
      } else if( event.getSource( ) == greenhousePanelElderCloseButton ) {
         greenhouseButtonElder.setVisible( true );
         greenhousePanelElder.setVisible( false );
         greenhousePanelElder.setDisable( true );
      }
      // greenhouse END -MS 02.05.2020-
      // gas -MS 02.05.2020-
      else if( event.getSource( ) == gasButtonElder ) {
         gasButtonElder.setVisible( false );
         gasPanelElder.setVisible( true );
         gasPanelElder.setDisable( false );
      } else if( event.getSource( ) == gasPanelElderCloseButton ) {
         gasButtonElder.setVisible( true );
         gasPanelElder.setVisible( false );
         gasPanelElder.setDisable( true );
      }
      // gas END -MS 02.05.2020-
      // door -MS 02.05.2020-
      else if( event.getSource( ) == doorButtonElder ) {
         doorButtonElder.setVisible( false );
         doorPanelElder.setVisible( true );
         doorPanelElder.setDisable( false );
      } else if( event.getSource( ) == doorPanelElderCloseButton ) {
         doorButtonElder.setVisible( true );
         doorPanelElder.setVisible( false );
         doorPanelElder.setDisable( true );
      }
      // door END -MS 02.05.2020-
      else if( event.getSource( ) == returnPageHouseSettings ) {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         houseMenuElderPane.setVisible( true );
         houseMenuElderPane.setDisable( false );
      } else if( event.getSource( ) == nextPageHouseSettings ) {
         houseMenuElderPane.setDisable( true );
         houseMenuElderPane.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      } else if( event.getSource( ) == houseMenuBackButtonElder2 ) {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         setMainMenuInvisible( false );
      }
      // aquariumSaveButton -MS 03.05.2020-
      else if( event.getSource( ) == aquariumSaveButton ) {
         //**
         aquariumSettingsPanel.setDisable( true );
         aquariumSettingsPanel.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      }
      // aquariumSaveButton END -MS 03.05.2020-
      // aquariumBackButton -MS 03.05.2020-
      else if( event.getSource( ) == aquariumMenuBackButtonElder ) {
         //**
         aquariumSettingsPanel.setDisable( true );
         aquariumSettingsPanel.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      }
      // aquariumBackButton END -MS 03.05.2020-

      // aquariumPanelElderSettingsButton -MS 03.05.2020-
      else if( event.getSource( ) == aquariumPanelElderSettingsButton ) {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         aquariumSettingsPanel.setVisible( true );
         aquariumSettingsPanel.setDisable( false );
      }
      // aquariumPanelElderSettingsButton -MS 03.05.2020-
   }

   // Make the main menu invisible or visible -MSACAKCI 03.04.2020-
   public void setMainMenuInvisible( Boolean b ) {
      if( b ) {
         mainElderPanelMenu.setVisible( false );
      } else {
         mainElderPanelMenu.setVisible( true );
      }
   }

   // When mouse entered the button area, labels will be activated. -MSACAKCI 03.04.2020-
   @FXML
   public void userProfileSubLabelActivate( MouseEvent event ) {
      userProfileSubLabelActive.setVisible( true );
      userProfileSubLabelNotActive.setVisible( false );
      userProfileElderActiveImage.setVisible( true );
   }

   @FXML
   public void userProfileSubLabelDeactivate( MouseEvent event ) {
      userProfileSubLabelActive.setVisible( false );
      userProfileSubLabelNotActive.setVisible( true );
      userProfileElderActiveImage.setVisible( false );
   }

   @FXML
   public void menuSubLabelActivate( MouseEvent event ) {
      menuSubLabelActive.setVisible( true );
      menuSubLabelNotActive.setVisible( false );
      houseMenuElderActiveImage.setVisible( true );
   }

   @FXML
   public void menuSubLabelDeactivate( MouseEvent event ) {
      menuSubLabelActive.setVisible( false );
      menuSubLabelNotActive.setVisible( true );
      houseMenuElderActiveImage.setVisible( false );
   }

   //Settings Button -MS 05.04.2020-
   @FXML
   public void settingsSubLabelActivate( MouseEvent event ) {
      settingsSubLabelActive.setVisible( true );
      settingsSubLabelNotActive.setVisible( false );
      settingsButtonElderActiveImage.setVisible( true );
   }

   @FXML
   public void settingsSubLabelDeactivate( MouseEvent event ) {
      settingsSubLabelActive.setVisible( false );
      settingsSubLabelNotActive.setVisible( true );
      settingsButtonElderActiveImage.setVisible( false );
   }
   //Setting Button END -MS 05.04.2020-

   //Electricity Button -MS 09.04.2020-
   @FXML
   public void electricityButtonElderActivate( MouseEvent event ) {
      electricityLabelElder.setVisible( true );
      electricityLineElder.setVisible( true );
   }

   @FXML
   public void electricityButtonElderDeactivate( MouseEvent event ) {
      electricityLabelElder.setVisible( false );
      electricityLineElder.setVisible( false );
   }
   //Electricity Button END -MS 09.04.2020-

   //Lights Button -MS 09.04.2020-
   @FXML
   public void lightingButtonElderActivate( MouseEvent event ) {
      lightsLabelElder.setVisible( true );
      lightsLineElder.setVisible( true );
   }

   @FXML
   public void lightingButtonElderDeactivate( MouseEvent event ) {
      lightsLabelElder.setVisible( false );
      lightsLineElder.setVisible( false );
   }
   //Lights Button -MS 09.04.2020-

   //Water Control Button -MS 09.04.2020-
   @FXML
   public void waterButtonElderActivate( MouseEvent event ) {
      waterLabelElder.setVisible( true );
      waterLineElder.setVisible( true );
   }

   @FXML
   public void waterButtonElderDeactivate( MouseEvent event ) {
      waterLabelElder.setVisible( false );
      waterLineElder.setVisible( false );
   }
   //Gas Button END -MS 09.04.2020-

   //Temperature Button -MS 09.04.2020-
   public void temperatureButtonElderActivate( MouseEvent event ) {
      temperatureLabelElder.setVisible( true );
      temperatureLineElder.setVisible( true );
   }

   public void temperatureButtonElderDeactivate( MouseEvent event ) {
      temperatureLabelElder.setVisible( false );
      temperatureLineElder.setVisible( false );
   }
   //Temperature Button END -MS 09.04.2020-

   //Back Button -MS 09.04.2020-
   @FXML
   public void houseMenuBackButtonActivate( MouseEvent event ) {
      houseMenuBackButtonSubLabel.setVisible( true );
   }

   @FXML
   public void houseMenuBackButtonDeactivate( MouseEvent event ) {
      houseMenuBackButtonSubLabel.setVisible( false );
   }
   //Back Button END -MS 09.04.2020-

   //Back 2 Button -MS 02.05.2020-
   @FXML
   public void houseMenuBackButton2Activate( MouseEvent event ) {
      houseMenuBackButtonSubLabel2.setVisible( true );
   }

   @FXML
   public void houseMenuBackButton2Deactivate( MouseEvent event ) {
      houseMenuBackButtonSubLabel2.setVisible( false );
   }
   //Back Button END -MS 02.05.2020-

   //Set the time -MS 09.04.2020-
   @FXML
   public void clock( MouseEvent event ) {
      String hourAsString;
      int hourAsInt;

      hourAsString = "";
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy/MM/dd | HH:mm" );
      LocalDateTime now = LocalDateTime.now( );
      time.setText( dtf.format( now ) );

      for( int index = time.getText( ).length( ) - 4; time.getText( ).length( ) - 6 < index; index-- ) {
         hourAsString = time.getText( ).charAt( index ) + hourAsString;
      }
      hourAsInt = Integer.valueOf( hourAsString );

      if( hourAsInt > 6 && hourAsInt < 11 ) {
         dayMessage.setText( "Good morning, " + userSettingsNameElderText.getText( ) + "!" );
      } else if( hourAsInt >= 11 && hourAsInt < 16 ) {
         dayMessage.setText( "Good afternoon, " + userSettingsNameElderText.getText( ) + "!" );
      } else if( hourAsInt >= 16 && hourAsInt < 20 ) {
         dayMessage.setText( "Good evening, " + userSettingsNameElderText.getText( ) + "!" );
      } else if( ( hourAsInt >= 20 && hourAsInt < 24 ) || ( hourAsInt >= 0 && hourAsInt <= 6 ) ) {
         dayMessage.setText( "Good night, " + userSettingsNameElderText.getText( ) + "!" );
      }

   }
   //Set the time END -MS 09.04.2020-

   //Electricity panel elder back button -MS 10.04.2020-
   @FXML
   public void electricityPanelElderCloseButtonActivate( MouseEvent event ) {
      electricityPanelElderCloseButtonActive.setVisible( true );

   }

   @FXML
   public void electricityPanelElderCloseButtonDeactivate( MouseEvent event ) {
      electricityPanelElderCloseButtonActive.setVisible( false );
   }
   //Electricity panel elder END -MS 10.04.2020-

   //Water panel elder back button -MS 10.04.2020-
   @FXML
   public void waterPanelElderCloseButtonActivate( MouseEvent event ) {
      waterPanelElderCloseButtonActive.setVisible( true );

   }

   @FXML
   public void waterPanelElderCloseButtonDeactivate( MouseEvent event ) {
      waterPanelElderCloseButtonActive.setVisible( false );
   }
   //Water panel elder END -MS 10.04.2020-

   //Temperature panel elder back button -MS 10.04.2020-
   @FXML
   public void temperaturePanelElderCloseButtonActivate( MouseEvent event ) {
      temperaturePanelElderCloseButtonActive.setVisible( true );

   }

   @FXML
   public void temperaturePanelElderCloseButtonDeactivate( MouseEvent event ) {
      temperaturePanelElderCloseButtonActive.setVisible( false );
   }
   //Temperature panel elder END -MS 10.04.2020-

   //Lights panel elder back button -MS 10.04.2020-
   @FXML
   public void lightsPanelElderCloseButtonActivate( MouseEvent event ) {
      lightsPanelElderCloseButtonActive.setVisible( true );

   }

   @FXML
   public void lightsPanelElderCloseButtonDeactivate( MouseEvent event ) {
      lightsPanelElderCloseButtonActive.setVisible( false );
   }

   @FXML
   public void setLightsSwitchStatus( ) {
      if( !electricityPanelElderSwitch.isSelected( ) )
         lightsPanelElderSwitch.setDisable( true );
      else
         lightsPanelElderSwitch.setDisable( false );
   }

   //Temperature panel elder END -MS 10.04.2020-
   @Override
   public void initialize( URL location, ResourceBundle resources ) {
      userTextFieldController( getLoginUser( ) );
   }

   // user after login panel -MS 24.03.2020-
   public User getLoginUser( ) {
      for( User u : Users.getInstance( ).getUserList( ) ) {
         if( u.getEnter( ).equals( "true" ) ) {
            u.setEnter( "false" );
            loginUser = u;
            return u;
         }
      }
      return null;
   }
   // user after login panel END -MS 24.03.2020-

   //applicationSettingsElderButton -MS 23.04.2020-
   @FXML
   public void applicationSettingsElderButtonActivate( MouseEvent event ) {
      applicationSettingsElderLabel.setVisible( true );
      applicationSettingsElderLine.setVisible( true );
   }

   @FXML
   public void applicationSettingsElderButtonDeactivate( MouseEvent event ) {
      applicationSettingsElderLabel.setVisible( false );
      applicationSettingsElderLine.setVisible( false );
   }
   //applicationSettingsElderButton -MS 23.04.2020-

   //usersSettingsElder -MS 23.04.2020-
   @FXML
   public void usersSettingsElderButtonActivate( MouseEvent event ) {
      usersSettingsElderLabel.setVisible( true );
      usersSettingsElderLine.setVisible( true );

   }

   @FXML
   public void usersSettingsElderButtonDeactivate( MouseEvent event ) {
      usersSettingsElderLabel.setVisible( false );
      usersSettingsElderLine.setVisible( false );
   }
   //usersSettingsElder -MS 23.04.2020-

   //modsSettingsElder -MS 23.04.2020-
   @FXML
   public void modsSettingsElderButtonActivate( MouseEvent event ) {
      modsSettingsElderLabel.setVisible( true );
      modsSettingsElderLine.setVisible( true );

   }

   @FXML
   public void modsSettingsElderButtonDeactivate( MouseEvent event ) {
      modsSettingsElderLabel.setVisible( false );
      modsSettingsElderLine.setVisible( false );
   }
   //modsSettingsElder -MS 23.04.2020-

   //homeSettingsElder -MS 23.04.2020-
   @FXML
   public void homeSettingsElderButtonActivate( MouseEvent event ) {
      homeSettingsElderLabel.setVisible( true );
      homeSettingsElderLine.setVisible( true );

   }

   @FXML
   public void homeSettingsElderButtonDeactivate( MouseEvent event ) {
      homeSettingsElderLabel.setVisible( false );
      homeSettingsElderLine.setVisible( false );
   }
   //homeSettingsElder -MS 23.04.2020-

   //themeSettingsElder -MS 23.04.2020-
   @FXML
   public void themeSettingsElderButtonActivate( MouseEvent event ) {
      themeSettingsElderLabel.setVisible( true );
      themeSettingsElderLine.setVisible( true );

   }

   @FXML
   public void themeSettingsElderButtonDeactivate( MouseEvent event ) {
      themeSettingsElderLabel.setVisible( false );
      themeSettingsElderLine.setVisible( false );
   }
   //themeSettingsElder -MS 23.04.2020-

   //languageSettingsElder -MS 23.04.2020-
   @FXML
   public void languageSettingsElderButtonActivate( MouseEvent event ) {
      languageSettingsElderLabel.setVisible( true );
      languageSettingsElderLine.setVisible( true );

   }

   @FXML
   public void languageSettingsElderButtonDeactivate( MouseEvent event ) {
      languageSettingsElderLabel.setVisible( false );
      languageSettingsElderLine.setVisible( false );
   }
   //languageSettingsElder -MS 23.04.2020-

   //emergencySettingsElder -MS 23.04.2020-
   @FXML
   public void emergencySettingsElderButtonActivate( MouseEvent event ) {
      emergencySettingsElderLabel.setVisible( true );
      emergencySettingsElderLine.setVisible( true );

   }

   @FXML
   public void emergencySettingsElderButtonDeactivate( MouseEvent event ) {
      emergencySettingsElderLabel.setVisible( false );
      emergencySettingsElderLine.setVisible( false );
   }
   //emergencySettingsElder -MS 23.04.2020-

   //notificationSettingsElder -MS 23.04.2020-
   @FXML
   public void notificationSettingsElderButtonActivate( MouseEvent event ) {
      notificationSettingsElderLabel.setVisible( true );
      notificationSettingsElderLine.setVisible( true );

   }

   @FXML
   public void notificationSettingsElderButtonDeactivate( MouseEvent event ) {
      notificationSettingsElderLabel.setVisible( false );
      notificationSettingsElderLine.setVisible( false );
   }
   //notificationSettingsElder -MS 23.04.2020-

   //connectionSettingsElderButton -MS 23.04.2020-
   @FXML
   public void connectionSettingsElderButtonActivate( MouseEvent event ) {
      connectionSettingsElderLabel.setVisible( true );
      connectionSettingsElderLine.setVisible( true );

   }

   @FXML
   public void connectionSettingsElderButtonDeactivate( MouseEvent event ) {
      connectionSettingsElderLabel.setVisible( false );
      connectionSettingsElderLine.setVisible( false );
   }
   //connectionSettingsElderButton -MS 23.04.2020-

   //User textFieldController -MS 24.04.2020-
   void userTextFieldController( User user ) {
      userSettingsNameElderText.setText( user.getName( ) );
      userSettingsSurnameElderText.setText( user.getSurname( ) );
      userSettingsDateElderText.setText( user.getBirthday( ) );
      userSettingsGenderElderText.setText( user.getGender( ) );
      userSettingsUsernameElderText.setText( user.getUserName( ) );
      userSettingsPasswordElderTextSecret.setText( user.getPassword( ) );
   }
   //User textFieldController END -MS 24.04.2020-

   //user settings change info panel -MS 24.04.2020-
   @FXML
   void userSettingsChangeBackButtonActivate( MouseEvent event ) {
      userSettingsChangeBackButtonSubLabel.setVisible( true );
      userSettingsChangeBackButtonImage.setVisible( true );
   }

   @FXML
   void userSettingsChangeBackButtonDeactivate( MouseEvent event ) {
      userSettingsChangeBackButtonSubLabel.setVisible( false );
      userSettingsChangeBackButtonImage.setVisible( false );
   }

   @FXML
   void userSettingsChangeSaveActivate( MouseEvent event ) {
      userSettingsChangeSaveSubLabel.setVisible( true );
      userSettingsChangeSaveImage.setVisible( true );
   }

   @FXML
   void userSettingsChangeSaveDeactivate( MouseEvent event ) {
      userSettingsChangeSaveSubLabel.setVisible( false );
      userSettingsChangeSaveImage.setVisible( false );
   }
   //user settings change info panel END -MS 24.04.2020-

   ///////////////
   @FXML
   void saveUserNormalChanges( ActionEvent event ) throws SQLException {
      String gender;
      User user;

      //for database
      if( userSettingsGenderMale.isSelected( ) ) {
         gender = "MALE";
      } else {
         gender = "FEMALE";
      }
      Users.getInstance( ).updateUserNormalInfo( loginUser, userSettingsNameElderTextChange.getText( )
              , userSettingsSurnameElderTextChange.getText( ), userSettingsDateElderTextChange.getText( )
              , gender );


      userTextFieldController( loginUser );
      userSettingsInfoChangerPanel.setVisible( false );
      userSettingsInfoChangerPanel.setDisable( true );
   }
   ///////////////

   @FXML
   void elderGenderController( ActionEvent event ) {
      if( event.getSource( ) == userSettingsGenderMale ) {
         if( userSettingsGenderFemale.isSelected( ) ) {
            userSettingsGenderFemale.setSelected( false );
         }
      } else if( event.getSource( ) == userSettingsGenderFemale ) {
         if( userSettingsGenderMale.isSelected( ) ) {
            userSettingsGenderMale.setSelected( false );
         }
      }
   }

   // userSettingsChangeInfoButton -MS 24.04.2020-
   @FXML
   void userSettingsChangeInfoButtonActivate( MouseEvent event ) {
      userSettingsChangeInfoButtonImage.setVisible( true );
      userSettingsChangeInfoButtonSubLabel.setVisible( true );
   }

   @FXML
   void userSettingsChangeInfoButtonDeactivate( MouseEvent event ) {
      userSettingsChangeInfoButtonImage.setVisible( false );
      userSettingsChangeInfoButtonSubLabel.setVisible( false );
   }
   // userSettingsChangeInfoButton END -MS 24.04.2020

   /////////////////////// -MS 26.04.2020-
   @FXML
   void toUserElderChanger( ActionEvent event ) {
      try {
         //User user;
         //user = new User(loginUser.getName(),loginUser.getSurname(),loginUser.getBirthday(),loginUser.getGender(),loginUser.getUserName(),loginUser.getPassword(),loginUser.getUserType(),loginUser.getPreferredTheme(),loginUser.getPreferredLanguage(),loginUser.getEnter());
         FXMLLoader load = new FXMLLoader( getClass( ).getResource( "view/loginPanel.fxml" ) );
         Parent root = ( Parent ) load.load( );
         Stage stage = new Stage( );
         stage.setTitle( "SMART HOME" );
         stage.setScene( new Scene( root, 400, 400 ) );
         stage.setResizable( false );
         stage.show( );
         mainElderPanelMenu.getScene( ).getWindow( ).hide( );
         //loginUser.setEnter("false");
         //Users.getInstance().updateUser(user,loginUser);
      } catch( Exception e ) {
      }
   }
   /////////////////////// END -MS 26.04.2020-

   // userSettingsUPChangeButton -MS 26.04.2020-
   @FXML
   void userSettingsUPChangeButtonActivate( MouseEvent event ) {
      userSettingsUPChangeButtonImage.setVisible( true );
      userSettingsUPChangeLabel.setVisible( true );
   }

   @FXML
   void userSettingsUPChangeButtonDeactivate( MouseEvent event ) {
      userSettingsUPChangeButtonImage.setVisible( false );
      userSettingsUPChangeLabel.setVisible( false );
   }
   // userSettingsUPChangeButton END -MS 26.04.2020-

   // userSettingsPanelTwoChangeSaveButton -MS 26.04.2020-
   @FXML
   void userSettingsPanelTwoChangeSaveButtonActivate( MouseEvent event ) {
      userSettingsPanelTwoChangeSaveButtonImage.setVisible( true );
      userSettingsPanelTwoChangeSaveLabel.setVisible( true );
   }

   @FXML
   void userSettingsPanelTwoChangeSaveButtonDeactivate( MouseEvent event ) {
      userSettingsPanelTwoChangeSaveButtonImage.setVisible( false );
      userSettingsPanelTwoChangeSaveLabel.setVisible( false );
   }
   // userSettingsPanelTwoChangeSaveButton END -MS 26.04.2020-

   // userSettingsPanelTwoChangeBackButton -MS 26.04.2020-
   @FXML
   void userSettingsPanelTwoChangeBackButtonActivate( MouseEvent event ) {
      userSettingsPanelTwoChangeBackButtonImage.setVisible( true );
      userSettingsPanelTwoChangeBackLabel.setVisible( true );
   }

   @FXML
   void userSettingsPanelTwoChangeBackButtonDeactivate( MouseEvent event ) {
      userSettingsPanelTwoChangeBackButtonImage.setVisible( true );
      userSettingsPanelTwoChangeBackLabel.setVisible( true );
   }
   // userSettingsPanelTwoChangeBackButton END -MS 26.04.2020-

   /////////////// -MS 26.04.2020-
   @FXML
   void saveUserPrivInfoChanges( ActionEvent event ) throws SQLException {
      User user;
      if( userSettingsUNElderChangeText.getText( ).length( ) > 0 && userSettingsPasswordChangeElderTextCurrent.getText( ).length( ) > 0 && userSettingsPasswordChangeElderNewText.getText( ).length( ) > 0 && userSettingsPasswordChangeElderNewConfirmText.getText( ).length( ) > 0 ) {
         //for database
         if( !userSettingsPasswordChangeElderNewText.getText( ).equals( userSettingsPasswordChangeElderNewConfirmText.getText( ) ) ) {
            //privateInfoWarning.setVisible(true);
            //privateInfoWarning.setText("PASSWORDS ARE NOT SAME");
         } else if( !userSettingsPasswordChangeElderTextCurrent.getText( ).equals( loginUser.getPassword( ) ) ) {
            //privateInfoWarning.setVisible(true);
            //privateInfoWarning.setText("CURRENT PASSWORD IS WRONG");
         } else {
            //privateInfoWarning.setVisible(false);
            Users.getInstance( ).updatePrivateInfo( loginUser, userSettingsUNElderChangeText.getText( ), userSettingsPasswordChangeElderNewText.getText( ) );

            userTextFieldController( loginUser );
            //userPreferenceUpdate(loginUser);
            userSettingsUIChangerPanel.setVisible( false );
         }
      } else {
         //privateInfoWarning.setVisible(true);
         //privateInfoWarning.setText("PLEASE ENTER ALL THE VALUES");
      }

   }
   /////////////// END -MS 26.04.2020-

   // Day message edit -MS 01.05.2020-
   @FXML
   void dayMessageEdit( ActionEvent event ) {

   }
   // Day message edit END -MS 01.05.2020-

   // userProfileBackButtonElder -MS 01.05.2020-
   @FXML
   void userProfileBackButtonElderActivate( MouseEvent event ) {
      userProfileBackButtonElderSubLabel.setVisible( true );
   }

   @FXML
   void userProfileBackButtonElderDeactivate( MouseEvent event ) {
      userProfileBackButtonElderSubLabel.setVisible( false );
   }
   // userProfileBackButtonElder END -MS 01.05.2020-

   // settingsBackButtonElder -MS 01.05.2020-
   @FXML
   void settingsBackButtonElderActivate( MouseEvent event ) {
      settingsBackButtonSubLabel.setVisible( true );
   }

   @FXML
   void settingsBackButtonElderDeactivate( MouseEvent event ) {
      settingsBackButtonSubLabel.setVisible( false );
   }
   // settingsBackButtonElder END -MS 01.05.2020-

   //aquarium Button -MS 02.05.2020-
   @FXML
   public void aquariumButtonElderActivate( MouseEvent event ) {
      aquariumLabelElder.setVisible( true );
      aquariumLineElder.setVisible( true );
   }

   @FXML
   public void aquariumButtonElderDeactivate( MouseEvent event ) {
      aquariumLabelElder.setVisible( false );
      aquariumLineElder.setVisible( false );
   }
   //aquarium Button END -MS 02.05.2020-

   // aquarium panel elder back button -MS 02.05.2020-
   @FXML
   public void aquariumPanelElderCloseButtonActivate( MouseEvent event ) {
      aquariumPanelElderCloseButtonActive.setVisible( true );

   }

   @FXML
   public void aquariumPanelElderCloseButtonDeactivate( MouseEvent event ) {
      aquariumPanelElderCloseButtonActive.setVisible( false );
   }
   // aquarium panel elder END -MS 02.05.2020-

   //greenhouse Button -MS 02.05.2020-
   @FXML
   public void greenhouseButtonElderActivate( MouseEvent event ) {
      greenhouseLabelElder.setVisible( true );
      greenhouseLineElder.setVisible( true );
   }

   @FXML
   public void greenhouseButtonElderDeactivate( MouseEvent event ) {
      greenhouseLabelElder.setVisible( false );
      greenhouseLineElder.setVisible( false );
   }
   //greenhouse Button END -MS 02.05.2020-

   // greenhouse panel elder back button -MS 02.05.2020-
   @FXML
   public void greenhousePanelElderCloseButtonActivate( MouseEvent event ) {
      greenhousePanelElderCloseButtonActive.setVisible( true );
   }

   @FXML
   public void greenhousePanelElderCloseButtonDeactivate( MouseEvent event ) {
      greenhousePanelElderCloseButtonActive.setVisible( false );
   }
   // greenhouse panel elder END -MS 02.05.2020-

   //gas Button -MS 02.05.2020-
   @FXML
   public void gasButtonElderActivate( MouseEvent event ) {
      gasLabelElder.setVisible( true );
      gasLineElder.setVisible( true );
   }

   @FXML
   public void gasButtonElderDeactivate( MouseEvent event ) {
      gasLabelElder.setVisible( false );
      gasLineElder.setVisible( false );
   }
   //gas Button END -MS 02.05.2020-

   // gas panel elder back button -MS 02.05.2020-
   @FXML
   public void gasPanelElderCloseButtonActivate( MouseEvent event ) {
      gasPanelElderCloseButtonActive.setVisible( true );
   }

   @FXML
   public void gasPanelElderCloseButtonDeactivate( MouseEvent event ) {
      gasPanelElderCloseButtonActive.setVisible( false );
   }
   // gas panel elder END -MS 02.05.2020-

   //door Button -MS 02.05.2020-
   @FXML
   public void doorButtonElderActivate( MouseEvent event ) {
      doorLabelElder.setVisible( true );
      doorLineElder.setVisible( true );
   }

   @FXML
   public void doorButtonElderDeactivate( MouseEvent event ) {
      doorLabelElder.setVisible( false );
      doorLineElder.setVisible( false );
   }
   //door Button END -MS 02.05.2020-

   // door panel elder back button -MS 02.05.2020-
   @FXML
   public void doorPanelElderCloseButtonActivate( MouseEvent event ) {
      doorPanelElderCloseButtonActive.setVisible( true );
   }

   @FXML
   public void doorPanelElderCloseButtonDeactivate( MouseEvent event ) {
      doorPanelElderCloseButtonActive.setVisible( false );
   }
   // door panel elder END -MS 02.05.2020-

   // aquariumSaveButton -MS 03.05.2020-
   @FXML
   void aquariumSaveButtonActivate( MouseEvent event ) {
      aquariumSaveButtonLabel.setVisible( true );
   }

   @FXML
   void aquariumSaveButtonDeactivate( MouseEvent event ) {
      aquariumSaveButtonLabel.setVisible( false );
   }
   // aquariumSaveButton END -MS 03.05.2020-

   //aquariumMenuBackButtonElder -MS 03.05.2020-
   @FXML
   void aquariumMenuBackButtonElderActivate( MouseEvent event ) {
      aquariumMenuBackButtonSubLabel.setVisible( true );
   }

   @FXML
   void aquariumMenuBackButtonElderDeactivate( MouseEvent event ) {
      aquariumMenuBackButtonSubLabel.setVisible( false );
   }
   //aquariumMenuBackButtonElder -MS 03.05.2020-
}
