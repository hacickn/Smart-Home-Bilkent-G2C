package com.SmartHomeBilkent;

import com.SmartHomeBilkent.home.Home;
import com.SmartHomeBilkent.utilities.connection.Arduino;
import com.SmartHomeBilkent.utilities.dataBase.*;
import com.SmartHomeBilkent.utilities.dataBase.fields.CommonSetting;
import com.SmartHomeBilkent.utilities.dataBase.fields.User;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.*;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ElderMainPanel implements Initializable {
   //properties

   // First main elder pane's properties.
   @FXML
   private Pane mainElderPanelMenu;

   @FXML
   private Label dayMessage, time, userProfileSubLabelNotActive,
           userProfileSubLabelActive,  menuSubLabelNotActive,
           menuSubLabelActive, settingsSubLabelNotActive, settingsSubLabelActive;

   @FXML
   private JFXButton userProfileButtonElder, settingsButtonElder,
           houseMenuButtonElder, elderLogoutButton;

   @FXML
   private ImageView userProfileElderActiveImage, settingsButtonElderActiveImage,
           houseMenuElderActiveImage, elderLogoutButtonActive;

   // house menu(settings)'s first elder pane's properties
   @FXML
   private Pane houseMenuElderPane;

   @FXML
   private JFXButton electricityButtonElder, electricityPanelElderCloseButton,
           electricityPanelElderSettingsButton, lightsButtonElder,
           lightsPanelElderCloseButton, waterButtonElder,
           waterPanelElderCloseButton, temperatureButtonElder,
           temperaturePanelElderCloseButton, houseMenuBackButtonElder,
           nextPageHouseSettings, houseMenuElderBulkChange;

   @FXML
   private Pane electricityPanelElder, lightsPanelElder, waterPanelElder, temperaturePanelElder;

   @FXML
   private Label electricityPanelElderLabel, electricityPanelElderLastOpening,
           electricityPanelElderLastClosure, electricityLabelElder,
           lightsPanelElderLabel, lightsPanelElderLastOpening,
           lightsPanelElderLastClosure, lightsLabelElder,
           waterPanelElderLabel, waterPanelElderLastOpening,
           waterPanelElderLastClosure, waterLabelElder,
           temperaturePanelElderLabel, temperaturePanelElderLastOpening,
           temperaturePanelElderLastClosure, temperatureLabelElder,
           houseMenuBackButtonSubLabel, houseMenuFirstLabelElder;

   @FXML
   private JFXToggleButton electricityPanelElderSwitch, lightsPanelElderSwitch,
           waterPanelElderSwitch, temperaturePanelElderSwitch;

   @FXML
   private ImageView electricityPanelElderCloseButtonActive, lightsPanelElderCloseButtonActive,
           waterPanelElderCloseButtonActive, temperaturePanelElderCloseButtonActive;

   @FXML
   private JFXProgressBar electricityPanelElderProgressBar1, electricityPanelElderProgressBar2,
           lightsPanelElderProgressBar2, lightsPanelElderProgressBar1;

   @FXML
   private Line electricityLineElder, lightsLineElder,
           waterLineElder, temperatureLineElder;

   // settings pane's properties
   @FXML
   private Pane settingsElderPanel;

   @FXML
   private JFXButton applicationSettingsElderButton, timeConfigurationElderButton,
           settingsBackButtonElder;

   @FXML
   private Label applicationSettingsElderLabel, homeSettingsElderLabel,
           settingsBackButtonSubLabel, houseMenuFirstLabelElder1;

   @FXML
   private Line applicationSettingsElderLine, homeSettingsElderLine;

   // application pane's properties
   @FXML
   private Pane applicationElderPanel;

   @FXML
   private JFXButton applicationBackButtonElder, languageSettingsElderButton,
           connectionSettingsElderButton, emergencySettingsElderButton,
           notificationSettingsElderButton;

   @FXML
   private Label applicationMenuFirstLabelElder, applicationBackButtonSubLabel,
           languageSettingsElderLabel, connectionSettingsElderLabel,
           emergencySettingsElderLabel, notificationSettingsElderLabel;

   @FXML
   private Line languageSettingsElderLine, connectionSettingsElderLine,
           emergencySettingsElderLine, notificationSettingsElderLine;

   // user settings pane's properties
   @FXML
   private Pane userSettingsElderPanelMenu;

   @FXML
   private JFXButton userProfileBackButtonElder, userSettingsChangeInfoButton,
           userSettingsChangeUserElderButton, userSettingsUPChangeButton,
           userSettingsChangeSave, userSettingsChangeBackButton,
           userSettingsPanelTwoChangeSaveButton, userSettingsPanelTwoChangeBackButton;

   @FXML
   private Label userProfileBackButtonElderSubLabel, userSettingsFirstLabelElder,
           userSettingsNameElder, userSettingsSurnameElder,
           userSettingsDateElder, userSettingsGenderElder,
           userSettingsUsernameElder, userSettingsPasswordElder,
           userSettingsChangeInfoButtonSubLabel, userSettingsUPChangeLabel,
           userSettingsNameElderChange, userSettingsSurnameElderChange,
           userSettingsDateElderChange, userSettingsGenderElderChange,
           userSettingsChangeSaveSubLabel, userSettingsChangeBackButtonSubLabel,
           userSettingsUNElderChange, userSettingsPasswordChangeElderCurrent,
           userSettingsPasswordChangeElderNew, userSettingsPasswordChangeElderNewConfirm,
           userSettingsPanelTwoChangeSaveLabel, userSettingsPanelTwoChangeBackLabel;

   @FXML
   private JFXTextField userSettingsNameElderText, userSettingsSurnameElderText,
           userSettingsDateElderText, userSettingsGenderElderText,
           userSettingsUsernameElderText, userSettingsPasswordElderText,
           userSettingsNameElderTextChange, userSettingsSurnameElderTextChange,
           userSettingsDateElderTextChange, userSettingsUNElderChangeText,
           userSettingsPasswordChangeElderTextCurrent, userSettingsPasswordChangeElderNewText,
           userSettingsPasswordChangeElderNewConfirmText;

   @FXML
   private JFXPasswordField userSettingsPasswordElderTextSecret;

   @FXML
   private ImageView userSettingsChangeInfoButtonImage, userSettingsUPChangeButtonImage,
           userSettingsChangeSaveImage, userSettingsChangeBackButtonImage,
           userSettingsPanelTwoChangeSaveButtonImage, userSettingsPanelTwoChangeBackButtonImage;

   @FXML
   private Pane userSettingsInfoChangerPanel, userSettingsUIChangerPanel;

   @FXML
   private JFXRadioButton userSettingsGenderMale, userSettingsGenderFemale;

   // house menu's (settings) second page's properties
   @FXML
   private Pane houseMenuElderPane2;

   @FXML
   private JFXButton aquariumButtonElder, aquariumPanelElderCloseButton,
           aquariumPanelElderSettingsButton, menuAquariumFeedButton,
           greenhouseButtonElder, greenhousePanelElderCloseButton,
           greenhousePanelElderSettingsButton, gasButtonElder,
           gasPanelElderCloseButton, gasPanelElderSettingsButton,
           doorButtonElder, houseMenuBackButtonElder2,
           returnPageHouseSettings;

   @FXML
   private Pane aquariumPanelElder, greenhousePanelElder,
           gasPanelElder;

   @FXML
   private Label aquariumPanelElderLabel, aquariumLabelElder,
           greenhousePanelElderLabel, greenhousePanelElderLastOpening,
           greenhousePanelElderLastClosure, greenhouseLabelElder,
           gasPanelElderLabel, gasPanelElderLastOpening,
           gasPanelElderLastClosure, gasLabelElder,
           doorPanelElderLabel, doorLabelElder,
           houseMenuBackButtonSubLabel2, houseMenuFirstLabelElder2;

   @FXML
   private JFXToggleButton aquariumPanelElderSwitch, greenhousePanelElderSwitch,
           gasPanelElderSwitch;

   @FXML
   private ImageView aquariumPanelElderCloseButtonActive, menuAquariumFeedButtonActive,
           greenhousePanelElderCloseButtonActive, gasPanelElderCloseButtonActive;

   @FXML
   private JFXProgressBar aquariumPanelElderProgressBar1, aquariumPanelElderProgressBar2,
           gasPanelElderProgressBar1, gasPanelElderProgressBar2;

   @FXML
   private ProgressIndicator menuAquariumIndicator;

   @FXML
   private Line aquariumLineElder, greenhouseLineElder,
           gasLineElder, doorLineElder;

   @FXML
   private JFXSpinner doorSpinner;

   // Aquarium settings pane's properties
   @FXML
   private Pane aquariumSettingsPanel;

   @FXML
   private Label aquariumMenuFirstLabelElder, aquariumMenuBackButtonSubLabel,
           speciesOfFishLabel, FeedingStartTimeLabel,
           weeklyWaterExchangeDayAndTimeLabel, dailyAirEngineRunTimeandStartTimeLabel,
           aquariumSaveButtonLabel, aquariumSettingsWarningLabel;

   @FXML
   private JFXButton aquariumMenuBackButtonElder, aquariumSaveButton;

   @FXML
   private CheckComboBox<String> speciesOfFishCheckComboBox;

   @FXML
   private ChoiceBox<?> speciesOfFishChoice;

   @FXML
   private JFXTimePicker feedingStartTimeTimePicker, weeklyWaterExchangeDayAndTimeTimePicker,
           dailyAirEngineRunTimeandStartTimeTimePicker;

   @FXML
   private JFXComboBox<String> weeklyWaterExchangeDayAndTimeChoice;

   @FXML
   private JFXSlider dailyAirEngineRunTimeandStartTimeSlider;

   // Language settings pane's properties
   @FXML
   private Pane languageSettingsElderPanel;

   @FXML
   private Label languagePanelFirstLabelElder, languageElderPanelBackButtonSubLabel,
           englishElderButtonSubLabelPassive, englishElderButtonSubLabelActive,
           turkishElderButtonSubLabelPassive, turkishElderButtonSubLabelActive;

   @FXML
   private JFXButton languageElderPanelBackButtonElder, englishElderButton,
           turkishElderButton;

   // Electricity Settings Pane's properties
   @FXML
   private Pane electiricitySettingsPanel;

   @FXML
   private Label electiricityMenuFirstLabelElder, electiricityMenuBackButtonSubLabel;

   @FXML
   private JFXButton electiricityMenuBackButtonElder;

   @FXML
   private BarChart<Number, Number> electiricitySettingsPanelChart;

   @FXML
   private JFXToggleButton electiricitySettingsPanelCloseOpenSwitch;

   // Gas Settings Pane's properties
   @FXML
   private Pane gasSettingsPanel;

   @FXML
   private Label gasMenuFirstLabelElder, gasMenuBackButtonSubLabel;

   @FXML
   private JFXButton gasMenuBackButtonElder;

   @FXML
   private BarChart<Number, Number> gasSettingsPanelChart;

   @FXML
   private JFXToggleButton gasSettingsPanelCloseOpenSwitch;

   // Greenhouse Settings Pane's properties
   @FXML
   private Pane greenhouseSettingsPanel;

   @FXML
   private Label greenhouseMenuFirstLabelElder, greenhouseMenuBackButtonSubLabel,
           latesWaterFromAquariumLabel, latesWaterFromAquariumVariable,
           humidityLabel, humidityVariable, greenhouseTemperatureLabel,
           greenhouseTemperatureVariable;

   @FXML
   private JFXButton greenhouseMenuBackButtonElder;

   @FXML
   private LineChart<Number, Number> greenhouseSettingsPanelChart;

   // Connection Settings pane's properties
   @FXML
   private Pane connectionSettingsElderPanel;

   @FXML
   private Label connectionPanelFirstLabelElder, connectionElderPanelBackButtonSubLabel;

   @FXML
   private JFXButton connectionElderPanelBackButtonElder, portConnectionButtonElder, bluetoothButton;

   @FXML
   private ComboBox<String> portChooserElder;

   @FXML
   private ImageView portConnectionButtonElderImage;

   // Notification Settings Pane's properties
   @FXML
   private Pane notificationSettingsElderPanel;

   @FXML
   private Label notificationPanelFirstLabelElder, notificationElderPanelBackButtonSubLabel,
           fireSensorLabel, gasSensorLabel, smokeSensorLabel, visualWarningLabel,
           auditoryWarningLabel;

   @FXML
   private JFXButton notificationElderPanelBackButtonElder;


   @FXML
   private JFXToggleButton fireButtonVisualToggle, gasSensorVisualToggle,
           smokeSensorVisualToggle, fireButtonSoundToggle,
           gasSensorSoundToggle, smokeSensorSoundToggle;

   // Time Configuration pane's properties
   @FXML
   private Pane timeConfigurationPanel;

   @FXML
   private Label timeConfigurationFirstLabel, timeConfigurationBackButtonSubLabel,
           timeConfigurationPaneWarningLabel, dateTimeSaveButtonSubLabel;

   @FXML
   private JFXButton timeConfigurationBackButton, dateTimeSaveButton;

   @FXML
   private JFXTimePicker menuTimePicker;

   @FXML
   private JFXDatePicker menuDatePicker;

   // Emergency pane's properties
   @FXML
   private Pane elderEmergencyPane;

   @FXML
   private Label emergencyFirstLabel, emergencyBackButtonSubLabel;

   @FXML
   private JFXButton emergencyBackButton;

   @FXML
   private JFXToggleButton internalSirenToggle, externalSirenToggle;

   private boolean isArduinoConnect;

   private Home home;


   public User loginUser;
   private Boolean languageStatusElder;
   private ResourceBundle bundle;
   private CommonSetting commonSetting;
   private String[] sensors;
   private String[] permissions;
   private Boolean exit;
   private Arduino arduino;
   private FillTransition fillTransition;
   private Rectangle rectangle;
   private AudioClip audioClip;
   private AudioClip audioClipEmergency;
   private String volume;
   private Label aquariumSettingLabel;

   //constructors
   //public ElderMainPanel()

   //methods

   /**
    * Initialize method is started when an elder people log in to the interface.
    * Before the main elder panel opens, this method simply run some methods and prepare the program.
    * @param location
    * @param resources
    */
   @Override
   public void initialize( URL location, ResourceBundle resources )
   {
      exit = false;
      isArduinoConnect = false;
      volume = "0";
      userTextFieldController( getLoginUser( ) );
      lightsPanelElderSwitch.setDisable( !electricityPanelElderSwitch.isSelected() );
      turkishElderButtonSubLabelActive.setVisible( false );
      englishElderButtonSubLabelActive.setVisible( true );
      languageStatusElder = false;
      ElectricityUsage.getInstance().getElectricityUsage();
      GasUsage.getInstance().getGasUsage();
      GreenHouseData.getInstance().getGreenHouseValues();
      FishSpecies.getInstance().getFishes();
      CommonSettingData.getInstance().getAllHome();
      GUIUpdateElder();
      refreshPortList();
      audioClipEmergency = new AudioClip( this.getClass().getResource( "music/emergencySound.mp3" ).toString() );
      audioClip = new AudioClip( this.getClass().getResource( "music/suprise.mp3" ).toString() );
      audioClipEmergency.setVolume( 1.0 );
      audioClip.setVolume( ( ( double ) Integer.parseInt( volume ) ) / 500 );
      audioClipEmergency.setRate( 1.0 );
      audioClip.setRate( 1.1 );

      new Thread( new Runnable() {
         @Override
         public void run() {
            while(!exit){
               Platform.runLater( new Runnable() {
                  @Override
                  public void run() {
                     time.setText( LocalDate.now().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) +
                             "  |  " + LocalTime.now().format( DateTimeFormatter.ofPattern( "HH:mm:ss" ) ) );

                     if( LocalTime.now().getHour() > 6 && LocalTime.now().getHour() < 11 ) {
                        dayMessage.setText( "Good morning, " + userSettingsNameElderText.getText( ) + "!" );
                     } else if( LocalTime.now().getHour() >= 11 && LocalTime.now().getHour() < 16 ) {
                        dayMessage.setText( "Good afternoon, " + userSettingsNameElderText.getText( ) + "!" );
                     } else if( LocalTime.now().getHour() >= 16 && LocalTime.now().getHour() < 20 ) {
                        dayMessage.setText( "Good evening, " + userSettingsNameElderText.getText( ) + "!" );
                     } else if( ( LocalTime.now().getHour() >= 20 && LocalTime.now().getHour() < 24 )
                             || ( LocalTime.now().getHour() >= 0 && LocalTime.now().getHour() <= 6 ) ) {
                        dayMessage.setText( "Good night, " + userSettingsNameElderText.getText( ) + "!" );
                     }
                  }
               } );
               try {
                  Thread.sleep( 1000 );
               } catch( InterruptedException e ) {
                  e.printStackTrace();
               }
            }
         }

      } ).start();

      createEmergencyAnimation();
   }

   /**
    * This method is started before interface starts to work. Its function is take and set house settings.
    */
   public void GUIUpdateElder()
   {
      String flowAquariumSetting;
      ElectricityUsage.getInstance().getTable( electiricitySettingsPanelChart );
      GasUsage.getInstance().getTable( gasSettingsPanelChart );
      GreenHouseData.getInstance().getTable( greenhouseSettingsPanelChart, bundle );
      FishSpecies.getInstance().addFishToComboBox( speciesOfFishCheckComboBox );

      for( int k = 1; k <= 7; k++ ) {
         weeklyWaterExchangeDayAndTimeChoice.getItems().add( k + bundle.getString( "daysOfWeekLang" ) );
      }
      commonSetting = CommonSettingData.getInstance().getHomeList().get( 0 );
      sensors = CommonSettingData.getInstance().getSensors( commonSetting );
      permissions = CommonSettingData.getInstance().getPermission( commonSetting );

      for( String s : CommonSettingData.getInstance().getSelectedFishes( commonSetting ) )
         speciesOfFishCheckComboBox.getCheckModel().check( speciesOfFishCheckComboBox.getItems().indexOf( s ) );

      flowAquariumSetting = CommonSettingData.getInstance().getAquariumSettings( commonSetting );
      feedingStartTimeTimePicker.setValue( LocalTime.of( Integer.parseInt(
              flowAquariumSetting.substring( 0, 2 ) ),
              Integer.parseInt( flowAquariumSetting.substring( 2, 4 ) ) ) );
      weeklyWaterExchangeDayAndTimeTimePicker.setValue( LocalTime.of( Integer.parseInt(
              flowAquariumSetting.substring( 6, 8 ) ),
              Integer.parseInt( flowAquariumSetting.substring( 8, 10 ) ) ) );
      weeklyWaterExchangeDayAndTimeChoice.getSelectionModel().select( Integer.parseInt(
              flowAquariumSetting.substring( 13, 14 ) ) - 1 );
      dailyAirEngineRunTimeandStartTimeTimePicker.setValue( LocalTime.of( Integer.parseInt(
              flowAquariumSetting.substring( 14, 16 ) ),
              Integer.parseInt( flowAquariumSetting.substring( 16, 18 ) ) ) );
      dailyAirEngineRunTimeandStartTimeSlider.setValue( Integer.parseInt( flowAquariumSetting.substring( 20, 22 ) ) );

      //Language setting
      if( loginUser.getPreferredLanguage().equals( "ENGLISH" ) )
      {
         languageStatusElder = false;
         changeLanguageElder( "en" );
      }

      else if( loginUser.getPreferredLanguage().equals( "TÜRKÇE" ) )
      {
         languageStatusElder = true;
         changeLanguageElder( "tr" );
      }
   }

   /**
    * This method is responsible control main Elder panel's buttons.
    * Every button which is in the main panel is processed in this method.
    * @param event
    */
   @FXML
   void actionPerformed( ActionEvent event ) throws SQLException {

      if( event.getSource( ) == settingsBackButtonElder ) {
         settingsElderPanel.setVisible( false );
         setMainMenuInvisible( false );
      }
      else if( event.getSource( ) == houseMenuBackButtonElder ) {
         houseMenuElderPane.setVisible( false );
         setMainMenuInvisible( false );
      }
      else if( event.getSource( ) == userProfileBackButtonElder ) {
         userSettingsElderPanelMenu.setVisible( false );
         setMainMenuInvisible( false );
      }
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
         lightsPanelElder.setDisable( false );
      } else if( event.getSource( ) == lightsPanelElderCloseButton ) {
         lightsButtonElder.setVisible( true );
         lightsPanelElder.setVisible( false );
         lightsPanelElder.setDisable( true );
      }
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
      else if( event.getSource( ) == userSettingsUPChangeButton ) {
         userSettingsUIChangerPanel.setVisible( true );
         userSettingsUIChangerPanel.setDisable( false );
         userSettingsUNElderChangeText.setText( userSettingsUsernameElderText.getText( ) );
      } else if( event.getSource( ) == userSettingsPanelTwoChangeBackButton ) {
         userSettingsUIChangerPanel.setVisible( false );
         userSettingsUIChangerPanel.setDisable( true );
      }
      else if( event.getSource( ) == aquariumButtonElder ) {
         aquariumButtonElder.setVisible( false );
         aquariumPanelElder.setVisible( true );
         aquariumPanelElder.setDisable( false );
      } else if( event.getSource( ) == aquariumPanelElderCloseButton ) {
         aquariumButtonElder.setVisible( true );
         aquariumPanelElder.setVisible( false );
         aquariumPanelElder.setDisable( true );
      }
      else if( event.getSource( ) == greenhouseButtonElder ) {
         greenhouseButtonElder.setVisible( false );
         greenhousePanelElder.setVisible( true );
         greenhousePanelElder.setDisable( false );
      } else if( event.getSource( ) == greenhousePanelElderCloseButton ) {
         greenhouseButtonElder.setVisible( true );
         greenhousePanelElder.setVisible( false );
         greenhousePanelElder.setDisable( true );
      }
      else if( event.getSource( ) == gasButtonElder ) {
         gasButtonElder.setVisible( false );
         gasPanelElder.setVisible( true );
         gasPanelElder.setDisable( false );
      } else if( event.getSource( ) == gasPanelElderCloseButton ) {
         gasButtonElder.setVisible( true );
         gasPanelElder.setVisible( false );
         gasPanelElder.setDisable( true );
      }
      else if( event.getSource( ) == doorButtonElder ) {
         if( isArduinoConnect )
            home.getDoor( ).open( true );

         new Thread( ( ) -> {
            for( int k = 0; k < 20; k++ ) {
               final int j = k;
               Platform.runLater( ( ) -> {
                  doorSpinner.setVisible( true );
                  //doorButton.setVisible( false );
                  if( j == 19 ) {
                     doorSpinner.setVisible( false );
                     //doorButton.setVisible( true );
                  }
               } );
               try {
                  Thread.sleep( 100 );
               } catch( InterruptedException e ) {
                  e.printStackTrace( );
               }
            }
         } ).start( );
      }
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
      else if( event.getSource( ) == aquariumMenuBackButtonElder ) {

         aquariumSettingsPanel.setDisable( true );
         aquariumSettingsPanel.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      }
      else if( event.getSource( ) == aquariumPanelElderSettingsButton ) {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         aquariumSettingsPanel.setVisible( true );
         aquariumSettingsPanel.setDisable( false );
      }
      else if ( event.getSource() == applicationBackButtonElder )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         settingsElderPanel.setVisible( true );
         settingsElderPanel.setDisable( false );
      }
      else if( event.getSource() == applicationSettingsElderButton )
      {
         settingsElderPanel.setDisable( true );
         settingsElderPanel.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
      else if( event.getSource() == languageSettingsElderButton )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         languageSettingsElderPanel.setVisible( true );
         languageSettingsElderPanel.setDisable( false );
      }
      else if ( event.getSource() == languageElderPanelBackButtonElder )
      {
         languageSettingsElderPanel.setDisable( true );
         languageSettingsElderPanel.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
      else if ( event.getSource() == turkishElderButton )
      {
         englishElderButtonSubLabelActive.setVisible( false );
         turkishElderButtonSubLabelActive.setVisible( true );
         languageStatusElder = true;
         changeLanguageElder( "tr" );
         //Users.getInstance().updateLanguage( loginUser, language );
         //userTextFieldController( loginUser );
      }
      else if ( event.getSource() == englishElderButton )
      {
         turkishElderButtonSubLabelActive.setVisible( false );
         englishElderButtonSubLabelActive.setVisible( true );
         languageStatusElder = false;
         changeLanguageElder( "en" );
      }
      else if( event.getSource() == electricityPanelElderSettingsButton )
      {
         houseMenuElderPane.setDisable( true );
         houseMenuElderPane.setVisible( false );
         electiricitySettingsPanel.setVisible( true );
         electiricitySettingsPanel.setDisable( false );
      }
      else if( event.getSource() == electiricityMenuBackButtonElder )
      {
         electiricitySettingsPanel.setDisable( true );
         electiricitySettingsPanel.setVisible( false );
         houseMenuElderPane.setVisible( true );
         houseMenuElderPane.setDisable( false );
      }
      else if( event.getSource() == gasPanelElderSettingsButton )
      {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         gasSettingsPanel.setVisible( true );
         gasSettingsPanel.setDisable( false );
      }
      else if ( event.getSource() == gasMenuBackButtonElder )
      {
         gasSettingsPanel.setDisable( true );
         gasSettingsPanel.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      }
      else if ( event.getSource() == greenhousePanelElderSettingsButton )
      {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         greenhouseSettingsPanel.setVisible( true );
         greenhouseSettingsPanel.setDisable( false );
      }
      else if ( event.getSource() == greenhouseMenuBackButtonElder )
      {
         greenhouseSettingsPanel.setDisable( true );
         greenhouseSettingsPanel.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      }
      else if ( event.getSource() == connectionSettingsElderButton )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         connectionSettingsElderPanel.setVisible( true );
         connectionSettingsElderPanel.setDisable( false );
         refreshPortList();
      }
      else if( event.getSource() == elderLogoutButton )
      {
         Platform.exit();
         exit = true;

         if( isArduinoConnect )
            home.getArduino().closeConnection();
      }
      else if( event.getSource( ) == settingsButtonElder )
      {
         setMainMenuInvisible( true );
         settingsElderPanel.setVisible( true );
         settingsElderPanel.setDisable( false );
      }
      else if( event.getSource( ) == houseMenuButtonElder )
      {
         setMainMenuInvisible( true );
         houseMenuElderPane.setVisible( true );
         houseMenuElderPane.setDisable( false );
      }
      else if( event.getSource( ) == userProfileButtonElder ) {
         setMainMenuInvisible( true );
         userSettingsElderPanelMenu.setVisible( true );
         userSettingsElderPanelMenu.setDisable( false );
      }
      else if( event.getSource() == notificationSettingsElderButton )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         notificationSettingsElderPanel.setVisible( true );
         notificationSettingsElderPanel.setDisable( false );
      }
      else if( event.getSource() == notificationElderPanelBackButtonElder )
      {
         notificationSettingsElderPanel.setDisable( true );
         notificationSettingsElderPanel.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
      else if ( event.getSource() == connectionElderPanelBackButtonElder )
      {
         connectionSettingsElderPanel.setDisable( true );
         connectionSettingsElderPanel.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
      else if( event.getSource() == portConnectionButtonElder )
      {
         arduino = new Arduino( portChooserElder.getValue(), 9600 );

         if( arduino.openConnection() ) {
            isArduinoConnect = true;
            portConnectionButtonElder.setDisable( true );
            home = new Home( arduino );
            home.adjustCollective( "manual_on#:" );

            home.getArduino().getSerialPort().addDataListener( new SerialPortDataListener() {
               @Override
               public int getListeningEvents() {
                  return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
               }

               @Override
               public void serialEvent( SerialPortEvent serialPortEvent ) {
                  if( serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE ) {
                     home.getArduino().getSerialPort().setComPortTimeouts( SerialPort.TIMEOUT_NONBLOCKING,
                             0, 0 );
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
                     out = new StringBuilder( out.toString().replaceAll( "\\s", "" ) );
                     out = new StringBuilder( out.toString().replace( "\n", "" ).replace( "\r", "" ) );


                     if( out.length() > 0 ) {
                        System.out.println( out );

                        if( out.toString().equals( "FireButon" ) ) {
                           if( fireButtonVisualToggle.isSelected()
                                   && fillTransition.getCurrentRate() == 0.0d ) {
                              fillTransition.play();
                              rectangle.setVisible( true );
                           }

                           if( fireButtonSoundToggle.isSelected()
                                   && audioClipEmergency.isPlaying() ){
                              audioClipEmergency.play();
                           }

                        }else if( out.toString().equals( "SmokeAlarm" ) ) {
                           if( smokeSensorVisualToggle.isSelected()
                                   && fillTransition.getCurrentRate() == 0.0d ) {
                              fillTransition.play();
                              rectangle.setVisible( true );
                           }

                           if( smokeSensorSoundToggle.isSelected()
                                   && !audioClipEmergency.isPlaying() ){
                              audioClipEmergency.play();
                           }

                        }else if( out.toString().equals( "GasAlarm" ) ) {
                           if( gasSensorVisualToggle.isSelected()
                                   && fillTransition.getCurrentRate() == 0.0d ) {
                              fillTransition.play();
                              rectangle.setVisible( true );
                           }

                           if( gasSensorSoundToggle.isSelected()
                                   && !audioClipEmergency.isPlaying() ){
                              audioClipEmergency.play();
                           }

                        }else if( out.toString().equals( "Gas+Fire" ) ) {
                           if( ( gasSensorVisualToggle.isSelected()
                                   || fireButtonVisualToggle.isSelected() )
                                   && fillTransition.getCurrentRate() == 0.0d ) {
                              fillTransition.play();
                              rectangle.setVisible( true );
                           }

                           if( ( gasSensorSoundToggle.isSelected()
                                   || fireButtonSoundToggle.isSelected() )
                                   && !audioClipEmergency.isPlaying() ){
                              audioClipEmergency.play();
                           }

                        }else if( out.toString().equals( "Gas+Smokealarm" ) ) {
                           if( ( gasSensorVisualToggle.isSelected()
                                   || smokeSensorVisualToggle.isSelected() )
                                   && fillTransition.getCurrentRate() == 0.0d ) {
                              fillTransition.play();
                              rectangle.setVisible( true );
                           }

                           if( ( gasSensorSoundToggle.isSelected()
                                   || smokeSensorSoundToggle.isSelected() )
                                   && !audioClipEmergency.isPlaying() ){
                              audioClipEmergency.play();
                           }

                        }else if( out.toString().equals( "Gas+Smoke" ) ) {
                           if( ( fireButtonVisualToggle.isSelected()
                                   || smokeSensorVisualToggle.isSelected() )
                                   && fillTransition.getCurrentRate() == 0.0d ) {
                              fillTransition.play();
                              rectangle.setVisible( true );
                           }

                           if( ( smokeSensorSoundToggle.isSelected()
                                   || fireButtonSoundToggle.isSelected() )
                                   && !audioClipEmergency.isPlaying() ){
                              audioClipEmergency.play();
                           }

                        }else if( out.toString().equals( "Gas+Smoke+Fire" ) ) {
                           if( ( fireButtonVisualToggle.isSelected()
                                   || smokeSensorVisualToggle.isSelected()
                                   || gasSensorVisualToggle.isSelected())
                                   && fillTransition.getCurrentRate() == 0.0d ) {
                              fillTransition.play();
                              rectangle.setVisible( true );
                           }

                           if( ( smokeSensorSoundToggle.isSelected()
                                   || fireButtonSoundToggle.isSelected()
                                   || gasSensorSoundToggle.isSelected())
                                   && !audioClipEmergency.isPlaying() ){
                              audioClipEmergency.play();
                           }
                        }
                     }
                  }
               }
            } );
         } else {
            portChooserElder.setValue( "" );
         }
      }
      else if( event.getSource() == externalSirenToggle )
      {
         if( isArduinoConnect )
            home.getSiren().open( externalSirenToggle.isSelected() );

      }
      else if( event.getSource() == internalSirenToggle )
      {
         if( isArduinoConnect )
            home.getSiren().buzzerOpen( internalSirenToggle.isSelected() );

      }
      else if( event.getSource() == fireButtonVisualToggle )
      {
         if( fireButtonVisualToggle.isSelected() )
            sensors[ 0 ] = "O" + sensors[ 0 ].charAt( 1 );
         else
            sensors[ 0 ] = "C" + sensors[ 0 ].charAt( 1 );
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      }
      else if( event.getSource() == gasSensorVisualToggle )
      {
         if( gasSensorVisualToggle.isSelected() )
            sensors[ 1 ] = "O" + sensors[ 1 ].charAt( 1 );
         else
            sensors[ 1 ] = "C" + sensors[ 1 ].charAt( 1 );
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      }
      else if( event.getSource() == smokeSensorVisualToggle )
      {
         if( smokeSensorVisualToggle.isSelected() )
            sensors[ 2 ] = "O" + sensors[ 2 ].charAt( 1 );
         else
            sensors[ 2 ] = "C" + sensors[ 2 ].charAt( 1 );
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      }
      else if( event.getSource() == fireButtonSoundToggle )
      {
         if( fireButtonSoundToggle.isSelected() )
            sensors[ 0 ] = sensors[ 0 ].charAt( 0 ) + "O";
         else
            sensors[ 0 ] = sensors[ 0 ].charAt( 0 ) + "C";
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      }
      else if( event.getSource() == gasSensorSoundToggle )
      {
         if( gasSensorSoundToggle.isSelected() )
            sensors[ 1 ] = sensors[ 1 ].charAt( 0 ) + "O";
         else
            sensors[ 1 ] = sensors[ 1 ].charAt( 0 ) + "C";
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      }
      else if( event.getSource() == smokeSensorSoundToggle )
      {
         if( smokeSensorSoundToggle.isSelected() )
            sensors[ 2 ] = sensors[ 2 ].charAt( 0 ) + "O";
         else
            sensors[ 2 ] = sensors[ 2 ].charAt( 0 ) + "C";
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );
      }
      else if( event.getSource() == aquariumSaveButton ) {

         if( feedingStartTimeTimePicker.getValue() == null
                 || weeklyWaterExchangeDayAndTimeTimePicker.getValue() == null
                 || dailyAirEngineRunTimeandStartTimeTimePicker.getValue() == null
                 || weeklyWaterExchangeDayAndTimeChoice.getValue() == null ) {
            aquariumSettingsWarningLabel.setText( bundle.getString( "normalInfoWarningLang" ) );
            aquariumSettingsWarningLabel.setVisible( true );
         } else {
            aquariumSettingsWarningLabel.setVisible( false );

            StringBuilder message;
            message = new StringBuilder( "aquarium#" );

            if( feedingStartTimeTimePicker.getValue().getHour() < 10 )
               message.append( "0" ).append( feedingStartTimeTimePicker.getValue().getHour() );
            else
               message.append( feedingStartTimeTimePicker.getValue().getHour() );

            if( feedingStartTimeTimePicker.getValue().getMinute() < 10 )
               message.append( "0" ).append( feedingStartTimeTimePicker.getValue().getMinute() ).append( "00" );
            else
               message.append( feedingStartTimeTimePicker.getValue().getMinute() ).append( "00" );

            if( weeklyWaterExchangeDayAndTimeTimePicker.getValue().getHour() < 10 )
               message.append( "0" ).append( weeklyWaterExchangeDayAndTimeTimePicker.getValue().getHour() );
            else
               message.append( weeklyWaterExchangeDayAndTimeTimePicker.getValue().getHour() );

            if( weeklyWaterExchangeDayAndTimeTimePicker.getValue().getMinute() < 10 )
               message.append( "0" ).append( weeklyWaterExchangeDayAndTimeTimePicker.getValue().getMinute() ).append( "00" );
            else
               message.append( weeklyWaterExchangeDayAndTimeTimePicker.getValue().getMinute() ).append( "00" );

            message.append( "0" ).append( weeklyWaterExchangeDayAndTimeChoice.getValue().charAt( 0 ) );

            if( dailyAirEngineRunTimeandStartTimeTimePicker.getValue().getHour() < 10 )
               message.append( "0" ).append( dailyAirEngineRunTimeandStartTimeTimePicker.getValue().getHour() );
            else
               message.append( dailyAirEngineRunTimeandStartTimeTimePicker.getValue().getHour() );

            if( dailyAirEngineRunTimeandStartTimeTimePicker.getValue().getMinute() < 10 )
               message.append( "0" ).append( dailyAirEngineRunTimeandStartTimeTimePicker.getValue().getMinute() ).append( "00" );
            else
               message.append( dailyAirEngineRunTimeandStartTimeTimePicker.getValue().getMinute() ).append( "00" );

            if( dailyAirEngineRunTimeandStartTimeSlider.getValue() < 10 )
               message.append( "0" ).append( ( int ) dailyAirEngineRunTimeandStartTimeSlider.getValue() ).append( ":" );
            else
               message.append( ( int ) dailyAirEngineRunTimeandStartTimeSlider.getValue() ).append( ":" );

            if( isArduinoConnect )
               home.getAquarium().setAquariumSettings( message.toString() );

            CommonSettingData.getInstance().updateAquariumSettings( commonSetting, message.toString().substring( 9, 31 ) );
            CommonSettingData.getInstance().updateSelectedFishes( commonSetting, speciesOfFishCheckComboBox.getItems() );
         }
      }
      else if( event.getSource() == timeConfigurationBackButton )
      {
         timeConfigurationPanel.setDisable( true );
         timeConfigurationPanel.setVisible( false );
         settingsElderPanel.setVisible( true );
         settingsElderPanel.setDisable( false );
      }
      else if( event.getSource() == timeConfigurationElderButton)
      {
         settingsElderPanel.setDisable( true );
         settingsElderPanel.setVisible( false );
         timeConfigurationPanel.setVisible( true );
         timeConfigurationPanel.setDisable( false );
      }
      else if( event.getSource() == dateTimeSaveButton )
      {
         if( menuDatePicker.getValue() == null
                 || menuTimePicker.getValue() == null ) {
            timeConfigurationPaneWarningLabel.setVisible( true );
            timeConfigurationPaneWarningLabel.setText( bundle.getString( "normalInfoWarningLang" ) );
         } else {
            timeConfigurationPaneWarningLabel.setVisible( false );
            StringBuilder message;
            message = new StringBuilder();

            if( menuTimePicker.getValue().getHour() < 10 )
               message.append( "clock#0" ).append( menuTimePicker.getValue().getHour() );
            else
               message.append( "clock#" ).append( menuTimePicker.getValue().getHour() );

            if( menuTimePicker.getValue().getMinute() < 10 )
               message.append( "0" ).append( menuTimePicker.getValue().getMinute() );
            else
               message.append( menuTimePicker.getValue().getMinute() );

            message.append( "000" ).append( menuDatePicker.getValue().getDayOfWeek().getValue() );

            if( menuDatePicker.getValue().getDayOfMonth() < 10 )
               message.append( "0" ).append( menuDatePicker.getValue().getDayOfMonth() );
            else
               message.append( menuDatePicker.getValue().getDayOfMonth() );
            if( menuDatePicker.getValue().getMonthValue() < 10 )
               message.append( "0" ).append( menuDatePicker.getValue().getMonthValue() );
            else
               message.append( menuDatePicker.getValue().getMonthValue() );
            message.append( menuDatePicker.getValue().getYear() ).append( ":" );

            if( isArduinoConnect )
               home.adjustCollective( message.toString() );
            System.out.println( message.toString() );
         }
      }
      else if( event.getSource() == menuAquariumFeedButton ) {
         if( isArduinoConnect )
            home.getAquarium().feedingOpen( true );

         new Thread( () -> {
            for( int k = 0; k < 20; k++ ) {
               final int j = k;
               Platform.runLater( () -> {
                  menuAquariumIndicator.setVisible( true );
                  if( j == 19 ) {
                     menuAquariumIndicator.setVisible( false );
                  }
               } );
               try {
                  Thread.sleep( 100 );
               } catch( InterruptedException e ) {
                  e.printStackTrace();
               }
            }
         } ).start();
      }
      else if( event.getSource() == emergencySettingsElderButton )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         elderEmergencyPane.setVisible( true );
         elderEmergencyPane.setDisable( false );
      }
      else if( event.getSource() == emergencyBackButton )
      {
         elderEmergencyPane.setDisable( true );
         elderEmergencyPane.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
      else if( event.getSource() == externalSirenToggle ) {
         if( isArduinoConnect )
            home.getSiren().open( externalSirenToggle.isSelected() );

      } else if( event.getSource() == internalSirenToggle ) {
         if( isArduinoConnect )
            home.getSiren( ).buzzerOpen( internalSirenToggle.isSelected( ) );
      }
   }

   /**
    * This method is responsible to get user information from the User class
    * and display it on the user settings panel of elderMainPanel.java.
    * @return
    */
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

   /**
    * This method is written to fill user settings panel's texts and set user's language.
    * @param user
    */
   void userTextFieldController( User user ) {
      userSettingsNameElderText.setText( user.getName( ) );
      userSettingsSurnameElderText.setText( user.getSurname( ) );
      userSettingsDateElderText.setText( user.getBirthday( ) );
      userSettingsGenderElderText.setText( user.getGender( ) );
      userSettingsUsernameElderText.setText( user.getUserName( ) );
      userSettingsPasswordElderTextSecret.setText( user.getPassword( ) );

      if( user.getGender().equals( "MALE" )
              || user.getGender().equals( "ERKEK" )
              || user.getGender().equals( "MÄNNLICH" ) ) {
         userSettingsGenderMale.setSelected( true );
         userSettingsGenderFemale.setSelected( false );
      } else {
         userSettingsGenderFemale.setSelected( true );
         userSettingsGenderMale.setSelected( false );
      }

      if( loginUser.getPreferredLanguage().equals( "ENGLISH" ) ) {
         turkishElderButtonSubLabelActive.setVisible( false );
         englishElderButtonSubLabelActive.setVisible( true );
         languageStatusElder = false;
         changeLanguageElder( "en" );

      } else if( loginUser.getPreferredLanguage().equals( "TÜRKÇE" ) ) {
         englishElderButtonSubLabelActive.setVisible( false );
         turkishElderButtonSubLabelActive.setVisible( true );
         languageStatusElder = true;
         changeLanguageElder( "tr" );
      }
   }

   /**
    * This method basically sets user's new setting preferences.
    * @param event
    * @throws SQLException
    */
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

   /**
    * This method is written to prevent user from choice both genders, male and female.
    * @param event
    */
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

   /**
    * This method make it possible to change user. It simply close the ElderMainPanel.java and opens the loginPanel.java.
    * @param event
    */
   @FXML
   void toUserElderChanger( ActionEvent event ) {
      try {
         FXMLLoader load = new FXMLLoader( getClass( ).getResource( "view/loginPanel.fxml" ) );
         Parent root = ( Parent ) load.load( );
         Stage stage = new Stage( );
         stage.setTitle( "SMART HOME" );
         stage.setScene( new Scene( root, 400, 400 ) );
         stage.setResizable( false );
         stage.show( );
         mainElderPanelMenu.getScene( ).getWindow( ).hide( );
      } catch( Exception e ) {
      }
   }

   /**
    * This method is responsible to set user's new  account preferences.
    * @param event
    * @throws SQLException
    */
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

   /**
    * This method is responsible to activate all of the JFXButtons in the ElderMainPanel.java.
    * Activate can be in various way such as display a lable or make the button more visible and highlighted.
    * @param event
    */
   @FXML
   void buttonElderActivate ( MouseEvent event )
   {
      if ( event.getSource() == userProfileButtonElder )
      {
         userProfileSubLabelActive.setVisible( true );
         userProfileSubLabelNotActive.setVisible( false );
         userProfileElderActiveImage.setVisible( true );
      }
      else if ( event.getSource() == houseMenuButtonElder )
      {
         menuSubLabelActive.setVisible( true );
         menuSubLabelNotActive.setVisible( false );
         houseMenuElderActiveImage.setVisible( true );
      }
      else if ( event.getSource() == settingsButtonElder )
      {
         settingsSubLabelActive.setVisible( true );
         settingsSubLabelNotActive.setVisible( false );
         settingsButtonElderActiveImage.setVisible( true );
      }
      else if ( ( event.getSource() == electricityButtonElder ) || ( event.getSource() == electricityLabelElder ) )
      {
         electricityLabelElder.setVisible( true );
         electricityLineElder.setVisible( true );
      }
      else if ( ( event.getSource() == lightsButtonElder ) || ( event.getSource() == lightsLabelElder) )
      {
         lightsLabelElder.setVisible( true );
         lightsLineElder.setVisible( true );
      }
      else if ( (event.getSource() == waterButtonElder ) )
      {
         waterLabelElder.setVisible( true );
         waterLineElder.setVisible( true );
      }
      else if ( event.getSource() == temperatureButtonElder )
      {
         temperatureLabelElder.setVisible( true );
         temperatureLineElder.setVisible( true );
      }
      else if ( event.getSource() == houseMenuBackButtonElder )
      {
         houseMenuBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == houseMenuBackButtonElder2 )
      {
         houseMenuBackButtonSubLabel2.setVisible( true );
      }
      else if ( event.getSource() == electricityPanelElderCloseButton )
      {
         electricityPanelElderCloseButtonActive.setVisible( true );
      }
      else if ( event.getSource() == waterPanelElderCloseButton )
      {
         waterPanelElderCloseButtonActive.setVisible( true );
      }
      else if ( event.getSource() == temperaturePanelElderCloseButton )
      {
         temperaturePanelElderCloseButtonActive.setVisible( true );
      }
      else if ( event.getSource() == lightsPanelElderCloseButton )
      {
         lightsPanelElderCloseButtonActive.setVisible( true );
      }
      else if ( event.getSource() == applicationSettingsElderButton )
      {
         applicationSettingsElderLabel.setVisible( true );
         applicationSettingsElderLine.setVisible( true );
      }
      else if ( event.getSource() == timeConfigurationElderButton )
      {
         homeSettingsElderLabel.setVisible( true );
         homeSettingsElderLine.setVisible( true );
      }
      else if ( event.getSource() == languageSettingsElderButton )
      {
         languageSettingsElderLabel.setVisible( true );
         languageSettingsElderLine.setVisible( true );
      }
      else if ( event.getSource() == emergencySettingsElderButton )
      {
         emergencySettingsElderLabel.setVisible( true );
         emergencySettingsElderLine.setVisible( true );
      }
      else if ( event.getSource() == connectionSettingsElderButton )
      {
         connectionSettingsElderLabel.setVisible( true );
         connectionSettingsElderLine.setVisible( true );
      }
      else if ( event.getSource() == userSettingsChangeBackButton )
      {
         userSettingsChangeBackButtonSubLabel.setVisible( true );
         userSettingsChangeBackButtonImage.setVisible( true );
      }
      else if ( event.getSource() == userSettingsChangeSave )
      {
         userSettingsChangeSaveSubLabel.setVisible( true );
         userSettingsChangeSaveImage.setVisible( true );
      }
      else if ( event.getSource() == userSettingsChangeInfoButton )
      {
         userSettingsChangeInfoButtonImage.setVisible( true );
         userSettingsChangeInfoButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == userSettingsUPChangeButton )
      {
         userSettingsUPChangeButtonImage.setVisible( true );
         userSettingsUPChangeLabel.setVisible( true );
      }
      else if ( event.getSource() == userSettingsPanelTwoChangeSaveButton )
      {
         userSettingsPanelTwoChangeSaveButtonImage.setVisible( true );
         userSettingsPanelTwoChangeSaveLabel.setVisible( true );
      }
      else if ( event.getSource() == userSettingsPanelTwoChangeBackButton )
      {
         userSettingsPanelTwoChangeBackButtonImage.setVisible( true );
         userSettingsPanelTwoChangeBackLabel.setVisible( true );
      }
      else if ( event.getSource() == userProfileBackButtonElder )
      {
         userProfileBackButtonElderSubLabel.setVisible( true );
      }
      else if ( event.getSource() == settingsBackButtonElder )
      {
         settingsBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == aquariumButtonElder)
      {
         aquariumLabelElder.setVisible( true );
         aquariumLineElder.setVisible( true );
      }
      else if ( event.getSource() == aquariumPanelElderCloseButton)
      {
         aquariumPanelElderCloseButtonActive.setVisible( true );
      }
      else if ( event.getSource() == greenhouseButtonElder )
      {
         greenhouseLabelElder.setVisible( true );
         greenhouseLineElder.setVisible( true );
      }
      else if ( event.getSource() == greenhousePanelElderCloseButton )
      {
         greenhousePanelElderCloseButtonActive.setVisible( true );
      }
      else if ( event.getSource() == gasButtonElder )
      {
         gasLabelElder.setVisible( true );
         gasLineElder.setVisible( true );
      }
      else if ( event.getSource() == gasPanelElderCloseButton )
      {
         gasPanelElderCloseButtonActive.setVisible( true );
      }
      else if ( event.getSource() == doorButtonElder )
      {
         doorLabelElder.setVisible( true );
         doorLineElder.setVisible( true );
      }
      else if ( event.getSource() == aquariumSaveButton )
      {
         aquariumSaveButtonLabel.setVisible( true );
      }
      else if ( event.getSource() == aquariumMenuBackButtonElder )
      {
         aquariumMenuBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == applicationBackButtonElder )
      {
         applicationBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == languageElderPanelBackButtonElder )
      {
         languageElderPanelBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == englishElderButton )
      {
         englishElderButtonSubLabelActive.setVisible( true );
         englishElderButtonSubLabelPassive.setVisible( false );
      }
      else if ( event.getSource() == turkishElderButton )
      {
         turkishElderButtonSubLabelActive.setVisible( true );
         turkishElderButtonSubLabelPassive.setVisible( false );
      }
      else if( event.getSource() == electiricityMenuBackButtonElder )
      {
         electiricityMenuBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == gasMenuBackButtonElder )
      {
         gasMenuBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == greenhouseMenuBackButtonElder )
      {
         greenhouseMenuBackButtonSubLabel.setVisible( true );
      }
      else if ( event.getSource() == connectionElderPanelBackButtonElder )
      {
         connectionElderPanelBackButtonSubLabel.setVisible( true );
      }
      else if( event.getSource() == elderLogoutButton )
      {
         elderLogoutButtonActive.setVisible( true );
      }
      else if( event.getSource() == notificationSettingsElderButton )
      {
         notificationSettingsElderLabel.setVisible( true );
         notificationSettingsElderLine.setVisible( true );
      }
      else if( event.getSource() == notificationElderPanelBackButtonElder )
      {
         notificationElderPanelBackButtonSubLabel.setVisible( true );
      }
      else if( event.getSource() == timeConfigurationBackButton )
      {
         timeConfigurationBackButtonSubLabel.setVisible( true );
      }
      else if( event.getSource() == dateTimeSaveButton )
      {
         dateTimeSaveButtonSubLabel.setVisible( true );
      }
      else if( event.getSource() == menuAquariumFeedButton )
      {
         menuAquariumFeedButtonActive.setVisible( true );
      }
      else if( event.getSource() == emergencyBackButton )
      {
         emergencyBackButtonSubLabel.setVisible( true );
      }
      else if( event.getSource() == portConnectionButtonElder )
      {
         portConnectionButtonElderImage.setVisible( true );
      }
   }

   /**
    * This method is responsible to deactivate all of the JFXButtons in the ElderMainPanel.java.
    * @param event
    */
   @FXML
   void buttonElderDeactivate ( MouseEvent event )
   {
      if ( event.getSource() == userProfileButtonElder )
      {
         userProfileSubLabelActive.setVisible( false );
         userProfileSubLabelNotActive.setVisible( true );
         userProfileElderActiveImage.setVisible( false );
      }
      else if ( event.getSource() == houseMenuButtonElder )
      {
         menuSubLabelActive.setVisible( false );
         menuSubLabelNotActive.setVisible( true );
         houseMenuElderActiveImage.setVisible( false );
      }
      else if ( event.getSource() == settingsButtonElder )
      {
         settingsSubLabelActive.setVisible( false );
         settingsSubLabelNotActive.setVisible( true );
         settingsButtonElderActiveImage.setVisible( false );
      }
      else if ( ( event.getSource() == electricityButtonElder ) || ( event.getSource() == electricityLabelElder ) )
      {
         electricityLabelElder.setVisible( false );
         electricityLineElder.setVisible( false );
      }
      else if ( ( event.getSource() == lightsButtonElder ) || ( event.getSource() == lightsLabelElder) )
      {
         lightsLabelElder.setVisible( false );
         lightsLineElder.setVisible( false );
      }
      else if ( (event.getSource() == waterButtonElder ) )
      {
         waterLabelElder.setVisible( false );
         waterLineElder.setVisible( false );
      }
      else if ( event.getSource() == temperatureButtonElder )
      {
         temperatureLabelElder.setVisible( false );
         temperatureLineElder.setVisible( false );
      }
      else if ( event.getSource() == houseMenuBackButtonElder )
      {
         houseMenuBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == houseMenuBackButtonElder2 )
      {
         houseMenuBackButtonSubLabel2.setVisible( false );
      }
      else if ( event.getSource() == electricityPanelElderCloseButton )
      {
         electricityPanelElderCloseButtonActive.setVisible( false );
      }
      else if ( event.getSource() == waterPanelElderCloseButton )
      {
         waterPanelElderCloseButtonActive.setVisible( false );
      }
      else if ( event.getSource() == temperaturePanelElderCloseButton )
      {
         temperaturePanelElderCloseButtonActive.setVisible( false );
      }
      else if ( event.getSource() == lightsPanelElderCloseButton )
      {
         lightsPanelElderCloseButtonActive.setVisible( false );
      }
      else if ( event.getSource() == applicationSettingsElderButton )
      {
         applicationSettingsElderLabel.setVisible( false );
         applicationSettingsElderLine.setVisible( false );
      }
      else if ( event.getSource() == timeConfigurationElderButton )
      {
         homeSettingsElderLabel.setVisible( false );
         homeSettingsElderLine.setVisible( false );
      }
      else if ( event.getSource() == languageSettingsElderButton )
      {
         languageSettingsElderLabel.setVisible( false );
         languageSettingsElderLine.setVisible( false );
      }
      else if ( event.getSource() == emergencySettingsElderButton )
      {
         emergencySettingsElderLabel.setVisible( false );
         emergencySettingsElderLine.setVisible( false );
      }
      else if ( event.getSource() == connectionSettingsElderButton )
      {
         connectionSettingsElderLabel.setVisible( false );
         connectionSettingsElderLine.setVisible( false );
      }
      else if ( event.getSource() == userSettingsChangeBackButton )
      {
         userSettingsChangeBackButtonSubLabel.setVisible( false );
         userSettingsChangeBackButtonImage.setVisible( false );
      }
      else if ( event.getSource() == userSettingsChangeSave )
      {
         userSettingsChangeSaveSubLabel.setVisible( false );
         userSettingsChangeSaveImage.setVisible( false );
      }
      else if ( event.getSource() == userSettingsChangeInfoButton )
      {
         userSettingsChangeInfoButtonImage.setVisible( false );
         userSettingsChangeInfoButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == userSettingsUPChangeButton )
      {
         userSettingsUPChangeButtonImage.setVisible( false );
         userSettingsUPChangeLabel.setVisible( false );
      }
      else if ( event.getSource() == userSettingsPanelTwoChangeSaveButton )
      {
         userSettingsPanelTwoChangeSaveButtonImage.setVisible( false );
         userSettingsPanelTwoChangeSaveLabel.setVisible( false );
      }
      else if ( event.getSource() == userSettingsPanelTwoChangeBackButton )
      {
         userSettingsPanelTwoChangeBackButtonImage.setVisible( false );
         userSettingsPanelTwoChangeBackLabel.setVisible( false );
      }
      else if ( event.getSource() == userProfileBackButtonElder )
      {
         userProfileBackButtonElderSubLabel.setVisible( false );
      }
      else if ( event.getSource() == settingsBackButtonElder )
      {
         settingsBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == aquariumButtonElder)
      {
         aquariumLabelElder.setVisible( false );
         aquariumLineElder.setVisible( false );
      }
      else if ( event.getSource() == aquariumPanelElderCloseButton)
      {
         aquariumPanelElderCloseButtonActive.setVisible( false );
      }
      else if ( event.getSource() == greenhouseButtonElder )
      {
         greenhouseLabelElder.setVisible( false );
         greenhouseLineElder.setVisible( false );
      }
      else if ( event.getSource() == greenhousePanelElderCloseButton )
      {
         greenhousePanelElderCloseButtonActive.setVisible( false );
      }
      else if ( event.getSource() == gasButtonElder )
      {
         gasLabelElder.setVisible( false );
         gasLineElder.setVisible( false );
      }
      else if ( event.getSource() == gasPanelElderCloseButton )
      {
         gasPanelElderCloseButtonActive.setVisible( false );
      }
      else if ( event.getSource() == doorButtonElder )
      {
         doorLabelElder.setVisible( false );
         doorLineElder.setVisible( false );
      }
      else if ( event.getSource() == aquariumSaveButton )
      {
         aquariumSaveButtonLabel.setVisible( false );
      }
      else if ( event.getSource() == aquariumMenuBackButtonElder )
      {
         aquariumMenuBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == applicationBackButtonElder )
      {
         applicationBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == languageElderPanelBackButtonElder )
      {
         languageElderPanelBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == englishElderButton )
      {
         if( languageStatusElder) {
            englishElderButtonSubLabelActive.setVisible( false );
            englishElderButtonSubLabelPassive.setVisible( true );
         }
         else {
            englishElderButtonSubLabelActive.setVisible( true );
         }
      }
      else if ( event.getSource() == turkishElderButton )
      {
         if( !languageStatusElder) {
            turkishElderButtonSubLabelActive.setVisible( false );
            turkishElderButtonSubLabelPassive.setVisible( true );
         }
         else {
            turkishElderButtonSubLabelActive.setVisible( true );
         }
      }
      else if( event.getSource() == electiricityMenuBackButtonElder )
      {
         electiricityMenuBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == gasMenuBackButtonElder )
      {
         gasMenuBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == greenhouseMenuBackButtonElder )
      {
         greenhouseMenuBackButtonSubLabel.setVisible( false );
      }
      else if ( event.getSource() == connectionElderPanelBackButtonElder )
      {
         connectionElderPanelBackButtonSubLabel.setVisible( false );
      }
      else if( event.getSource() == elderLogoutButton )
      {
         elderLogoutButtonActive.setVisible( false );
      }
      else if( event.getSource() == notificationSettingsElderButton )
      {
         notificationSettingsElderLabel.setVisible( false );
         notificationSettingsElderLine.setVisible( false );
      }
      else if( event.getSource() == notificationElderPanelBackButtonElder )
      {
         notificationElderPanelBackButtonSubLabel.setVisible( false );
      }
      else if( event.getSource() == timeConfigurationBackButton )
      {
         timeConfigurationBackButtonSubLabel.setVisible( false );
      }
      else if( event.getSource() == dateTimeSaveButton )
      {
         dateTimeSaveButtonSubLabel.setVisible( false );
      }
      else if( event.getSource() == menuAquariumFeedButton )
      {
         menuAquariumFeedButtonActive.setVisible( false );
      }
      else if( event.getSource() == emergencyBackButton )
      {
         emergencyBackButtonSubLabel.setVisible( false );
      }
      else if( event.getSource() == portConnectionButtonElder )
      {
         portConnectionButtonElderImage.setVisible( false );
      }
   }

   /**
    * When user want to change the programs language, this method changes all labels and names on the interface.
    * @param language
    */
   public void changeLanguageElder ( String language) {

      try {
         Locale locale = new Locale( language );
         bundle = ResourceBundle.getBundle( "com.SmartHomeBilkent.language.lang", locale );
         userProfileSubLabelNotActive.setText( bundle.getString( "userProfileLang" ) );
         userProfileSubLabelActive.setText( bundle.getString( "userProfileLang" ) );
         menuSubLabelNotActive.setText( bundle.getString( "menuLang" ) );
         menuSubLabelActive.setText( bundle.getString( "menuLang" ) );
         settingsSubLabelNotActive.setText( bundle.getString( "settingLang" ) );
         settingsSubLabelActive.setText( bundle.getString( "settingLang" ) );
         electricityPanelElderLabel.setText( bundle.getString( "elecLang" ) );
         electricityLabelElder.setText( bundle.getString( "elecLang" ) );
         lightsPanelElderLabel.setText( bundle.getString( "gardenLightLang" ) );
         lightsLabelElder.setText( bundle.getString( "gardenLightLang" ) );
         waterPanelElderLabel.setText( bundle.getString( "waterLang" ) );
         waterLabelElder.setText( bundle.getString( "waterLang" ) );
         temperaturePanelElderLabel.setText( bundle.getString( "tempLang" ) );
         temperatureLabelElder.setText( bundle.getString( "tempLang" ) );
         houseMenuBackButtonElder.setText( bundle.getString( "getBackButtonLang" ) );
         houseMenuBackButtonSubLabel.setText( bundle.getString( "getBackButtonSubLabelLang" ) );
         houseMenuFirstLabelElder.setText( bundle.getString("houseMenuFirstLabelElderLang" ));
         applicationSettingsElderLabel.setText( bundle.getString("applicationSettingLang" ) );
         homeSettingsElderLabel.setText( bundle.getString("homeLang" ) );
         settingsBackButtonElder.setText( bundle.getString("getBackButtonLang" ) );
         settingsBackButtonSubLabel.setText( bundle.getString("settingsBackButtonSubLabelLang" ));
         houseMenuFirstLabelElder1.setText( bundle.getString("houseMenuFirstLabelElder1Lang" ));
         applicationBackButtonElder.setText( bundle.getString( "getBackButtonLang" ) );
         applicationMenuFirstLabelElder.setText( bundle.getString("applicationMenuFirstLabelElderLang" ));
         applicationBackButtonSubLabel.setText( bundle.getString("applicationBackButtonSubLabelLang" ));
         languageSettingsElderLabel.setText( bundle.getString("languageLang" ));
         connectionSettingsElderLabel.setText( bundle.getString("connectionLang" ));
         emergencySettingsElderLabel.setText( bundle.getString("emergencyLang" ) );
         userProfileBackButtonElder.setText( bundle.getString("getBackButtonLang" ) );
         userProfileBackButtonElderSubLabel.setText( bundle.getString("settingsBackButtonSubLabelLang" ));
         userSettingsFirstLabelElder.setText( bundle.getString("userSettingsFirstLabelElderLang" ));
         userSettingsNameElder.setText( bundle.getString( "nameLang" ));
         userSettingsSurnameElder.setText( bundle.getString("surnameLang" ));
         userSettingsDateElder.setText( bundle.getString("birthdayLang" ));
         userSettingsGenderElder.setText( bundle.getString("genderLang" ));
         userSettingsUsernameElder.setText( bundle.getString("userNameLang" ));
         userSettingsPasswordElder.setText( bundle.getString("passwordLang" ));
         userSettingsChangeInfoButtonSubLabel.setText( bundle.getString("userSettingsChangeUserElderButtonLang" ));
         userSettingsChangeUserElderButton.setText( bundle.getString("userChangerLang" ));
         userSettingsUPChangeLabel.setText( bundle.getString("userSettingsUPChangeLabelLang" ));
         userSettingsNameElderChange.setText( bundle.getString( "nameLang" ) );
         userSettingsSurnameElderChange.setText( bundle.getString("surnameLang" ) );
         userSettingsDateElderChange.setText( bundle.getString("birthdayLang" ) );
         userSettingsGenderElderChange.setText( bundle.getString("genderLang" ) );
         userSettingsGenderMale.setText( bundle.getString( "maleGenderLang" ) );
         userSettingsGenderFemale.setText( bundle.getString( "femaleGenderLang" ) );
         userSettingsChangeSaveSubLabel.setText( bundle.getString( "saveLang" ));
         userSettingsChangeBackButtonSubLabel.setText( bundle.getString( "getBackButtonLang" ) );
         userSettingsUNElderChange.setText(  bundle.getString("userNameLang" ) );
         //userSettingsPasswordChangeElderCurrent.setText( bundle.getString("currentPasswordLang:" ));
         userSettingsPasswordChangeElderNew.setText( bundle.getString( "newPasswordLang" ));
         userSettingsPasswordChangeElderNewConfirm.setText( bundle.getString("verifyNewPasswordLang" ));
         userSettingsPanelTwoChangeSaveLabel.setText( bundle.getString( "saveLang" ));
         userSettingsPanelTwoChangeBackLabel.setText( bundle.getString("getBackButtonLang" ));
         aquariumPanelElderLabel.setText( bundle.getString("aquiarumLang" ));
         aquariumLabelElder.setText( bundle.getString("aquiarumLang" ) );
         greenhousePanelElderLabel.setText( bundle.getString("greenHouse" ));
         greenhouseLabelElder.setText( bundle.getString("greenHouse" ) );
         gasPanelElderLabel.setText( bundle.getString("gasLang" ));
         gasLabelElder.setText( bundle.getString("gasLang" ) );
         //doorPanelElderLabel.setText( bundle.getString("doorLang" ));
         doorLabelElder.setText( bundle.getString("doorLang" ) );
         houseMenuBackButtonElder2.setText( bundle.getString("getBackButtonLang" ));
         houseMenuBackButtonSubLabel2.setText( bundle.getString("settingsBackButtonSubLabelLang" ));
         houseMenuFirstLabelElder2.setText( bundle.getString("houseMenuFirstLabelElderLang" ));
         aquariumMenuFirstLabelElder.setText( bundle.getString("aquariumMenuFirstLabelElderLang" ));
         aquariumMenuBackButtonElder.setText( bundle.getString("getBackButtonLang" ) );
         aquariumMenuBackButtonSubLabel.setText( bundle.getString("aquariumMenuBackButtonSubLabelLang" ));
         speciesOfFishLabel.setText( bundle.getString("speciesOfFishLang" ));
         FeedingStartTimeLabel.setText( bundle.getString("feedingStartTimeLang" ) );
         weeklyWaterExchangeDayAndTimeLabel.setText( bundle.getString("waterExchangeDayTimeLang" ));
         dailyAirEngineRunTimeandStartTimeLabel.setText( bundle.getString("airEngineRunTimeStartTimeLang" ));
         aquariumSaveButtonLabel.setText( bundle.getString("saveLang" ));
         languagePanelFirstLabelElder.setText( bundle.getString("languagePanelFirstLabelElderLang" ));
         languageElderPanelBackButtonElder.setText( bundle.getString("getBackButtonLang" ));
         englishElderButtonSubLabelPassive.setText( bundle.getString("englishElderButtonSubLabelLang" ));
         englishElderButtonSubLabelActive.setText( bundle.getString("englishElderButtonSubLabelLang" ) );
         turkishElderButtonSubLabelPassive.setText( bundle.getString("turkishElderButtonSubLabelLang" ));
         turkishElderButtonSubLabelActive.setText( bundle.getString("turkishElderButtonSubLabelLang" ) );
         languageElderPanelBackButtonSubLabel.setText( bundle.getString("languageElderPanelBackButtonSubLabelLang" ));
         homeSettingsElderLabel.setText( bundle.getString( "timeConfigurationLang" ));
         timeConfigurationFirstLabel.setText( bundle.getString( "timeConfigurationFirstLabelLang" ) );
         timeConfigurationBackButton.setText( bundle.getString( "getBackButtonLang" ) );
         dateTimeSaveButtonSubLabel.setText( bundle.getString( "saveLang" ) );
         fireSensorLabel.setText( bundle.getString( "fireButtonLang" ) );
         gasSensorLabel.setText( bundle.getString( "gasSensorLang" ) );
         smokeSensorLabel.setText( bundle.getString( "smokeSensor" ) );
         visualWarningLabel.setText( bundle.getString( "visualWarningLang" ) );
         auditoryWarningLabel.setText( bundle.getString( "auditoryWarning" ) );
         notificationElderPanelBackButtonSubLabel.setText( bundle.getString( "languageElderPanelBackButtonSubLabelLang" ) );
         notificationPanelFirstLabelElder.setText( bundle.getString( "notificationPanelFirstLabelElderLang" ) );
         connectionElderPanelBackButtonElder.setText( bundle.getString( "getBackButtonLang" ) );
         connectionElderPanelBackButtonSubLabel.setText( bundle.getString( "languageElderPanelBackButtonSubLabelLang" ) );
         connectionPanelFirstLabelElder.setText( bundle.getString( "connectionPanelFirstLabelElderLang" ) );
         greenhouseMenuFirstLabelElder.setText( bundle.getString( "greenhouseMenuFirstLabelElderLang" ) );
         greenhouseMenuBackButtonElder.setText( bundle.getString( "getBackButtonLang" ) );
         greenhouseMenuBackButtonSubLabel.setText( bundle.getString( "getBackButtonSubLabelLang" ) );
         greenhouseTemperatureLabel.setText( bundle.getString( "tempLang" ) );
         latesWaterFromAquariumLabel.setText( bundle.getString( "latestWaterFromAquariumLang" ) );
         humidityLabel.setText( bundle.getString( "humidity" ) );
         gasMenuFirstLabelElder.setText( bundle.getString( "gasMenuFirstLabelElderLang" ) );
         gasMenuBackButtonElder.setText( bundle.getString( "getBackButtonLang" ) );
         gasMenuBackButtonSubLabel.setText( bundle.getString( "getBackButtonSubLabelLang" ) );
         electiricityMenuFirstLabelElder.setText( bundle.getString( "electiricityMenuFirstLabelElderLang" ) );
         electiricityMenuBackButtonElder.setText( bundle.getString( "getBackButtonLang" ) );
         electiricityMenuBackButtonSubLabel.setText( bundle.getString( "getBackButtonSubLabelLang" ) );
         notificationSettingsElderLabel.setText( bundle.getString( "notificationLang" ) );
         portChooserElder.setPromptText( bundle.getString( "portChooserLang" ) );
         timeConfigurationBackButtonSubLabel.setText( bundle.getString( "applicationBackButtonSubLabelLang" ) );
         dateTimeSaveButtonSubLabel.setText( bundle.getString( "saveLang" ) );
         emergencyFirstLabel.setText( bundle.getString( "emergencyFirstLabelLang" ));
         emergencyBackButton.setText( bundle.getString( "getBackButtonLang" ));
         emergencyBackButtonSubLabel.setText( bundle.getString( "applicationBackButtonSubLabelLang" ));
         internalSirenToggle.setText( bundle.getString( "internalSirenLang" ) );
         externalSirenToggle.setText( bundle.getString( "externalSirenLang" ) );
      } catch( Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * This method is responsible to control house settings such as electricity contol or gas control, etc.
    * @param event
    */
   @FXML
   void houseSettingsController ( ActionEvent event )
   {
      if ( event.getSource() == electricityPanelElderSwitch )
      {
         controlElectricity( electricityPanelElderSwitch.isSelected( ) );
         if ( isArduinoConnect )
         {
            home.getElectricity( ).open( electricityPanelElderSwitch.isSelected( ) );
         }
      }
      else if( event.getSource() == gasPanelElderSwitch )
      {
         controlGas( gasPanelElderSwitch.isSelected() );
         if ( isArduinoConnect )
         {
            home.getGas( ).open( gasPanelElderSwitch.isSelected( ) );
         }
      }
      else if( event.getSource() == aquariumPanelElderSwitch ) {
         controlAquarium( aquariumPanelElderSwitch.isSelected() );
         if( isArduinoConnect )
            home.getAquarium().open( aquariumPanelElderSwitch.isSelected() );
      }
      else if( event.getSource() == waterPanelElderSwitch ) {
         //openWater( waterSubMenuToggleButton.isSelected() );
         if( isArduinoConnect )
            home.getWater().open( waterPanelElderSwitch.isSelected() );
      }
      else if( event.getSource() == lightsPanelElderSwitch ) {
         controlLights( lightsPanelElderSwitch.isSelected() );
         if( isArduinoConnect )
            home.getGardenLight().open( lightsPanelElderSwitch.isSelected() );
      }

   }

   /**
    * This method controls electricity pane which is located in the house setting pane.
    * @param status
    */
   @FXML
   void controlElectricity ( boolean status )
   {
      electricityPanelElderProgressBar1.setVisible( status );
      electricityPanelElderProgressBar2.setVisible( status );
      electiricitySettingsPanelCloseOpenSwitch.setSelected( status );
      lightsPanelElderSwitch.setDisable( !electricityPanelElderSwitch.isSelected() );
      if( !electricityPanelElderSwitch.isSelected() )
      {
         lightsPanelElderSwitch.setSelected( false );
      }
   }

   /**
    * This method controls garden light pane which is located in the house setting pane.
    * @param status
    */
   @FXML
   void controlLights ( boolean status )
   {
      lightsPanelElderProgressBar1.setVisible( status );
      lightsPanelElderProgressBar2.setVisible( status );
      electiricitySettingsPanelCloseOpenSwitch.setSelected( status );
   }

   /**
    * This method controls gas pane which is located in the house setting pane.
    * @param status
    */
   @FXML
   void controlGas ( boolean status )
   {
      gasPanelElderProgressBar1.setVisible( status );
      gasPanelElderProgressBar2.setVisible( status );
      gasSettingsPanelCloseOpenSwitch.setSelected( status );
   }

   /**
    * This method controls aquarium pane which is located in the house setting pane.
    * @param status
    */
   @FXML
   void controlAquarium ( boolean status )
   {
      aquariumPanelElderProgressBar1.setVisible( status );
      aquariumPanelElderProgressBar2.setVisible( status );
   }

   /**
    * It is a refreshPortList that is refresh the port list for combo box
    */
   void refreshPortList() {
      SerialPort[] portNames;
      portChooserElder.getItems().removeAll( portChooserElder.getItems() );
      portNames = SerialPort.getCommPorts();

      for( SerialPort portName : portNames )
         portChooserElder.getItems().add( portName.getSystemPortName() );
   }

   /**
    * It is a createEmergencyAnimation that creates animation for
    * emergency warning but it does not play when It is created
    */
   void createEmergencyAnimation() {
      rectangle = new Rectangle( 0, 0, 800, 800 );
      rectangle.setDisable( true );
      fillTransition = new FillTransition( Duration.seconds( 0.5 ),
              rectangle, Color.rgb( 255, 0, 0, 0 ),
              Color.rgb( 255, 0, 0, 0.6 ) );
      fillTransition.setCycleCount( 20 );
      fillTransition.setAutoReverse( true );
      //commonBorderPane.getChildren().add( rectangle );
      rectangle.setVisible( false );
   }

   /**
    * This method is simply responsible from first panel of the elderMain.java to make it visible or non visible.
    * @param b
    */
   public void setMainMenuInvisible( Boolean b ) {
      if( b ) {
         mainElderPanelMenu.setVisible( false );
      } else {
         mainElderPanelMenu.setVisible( true );
      }
   }

}
