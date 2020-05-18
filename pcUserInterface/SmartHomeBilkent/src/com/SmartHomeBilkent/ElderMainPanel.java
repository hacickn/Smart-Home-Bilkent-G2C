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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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
   private JFXButton electricityPanelElderSettingsButton;

   @FXML
   private JFXProgressBar electricityPanelElderProgressBar1;

   @FXML
   private JFXProgressBar electricityPanelElderProgressBar2;

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
   private JFXProgressBar lightsPanelElderProgressBar2;

   @FXML
   private JFXProgressBar lightsPanelElderProgressBar1;

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
   private JFXButton timeConfigurationElderButton;

   @FXML
   private Label homeSettingsElderLabel;

   @FXML
   private Line homeSettingsElderLine;

   @FXML
   private JFXButton settingsBackButtonElder;

   @FXML
   private Label settingsBackButtonSubLabel;

   @FXML
   private Label houseMenuFirstLabelElder1;

   @FXML
   private Pane applicationElderPanel;

   @FXML
   private JFXButton applicationBackButtonElder;

   @FXML
   private Label applicationMenuFirstLabelElder;

   @FXML
   private Label applicationBackButtonSubLabel;

   @FXML
   private JFXButton languageSettingsElderButton;

   @FXML
   private Label languageSettingsElderLabel;

   @FXML
   private Line languageSettingsElderLine;

   @FXML
   private JFXButton connectionSettingsElderButton;

   @FXML
   private Label connectionSettingsElderLabel;

   @FXML
   private Line connectionSettingsElderLine;

   @FXML
   private JFXButton emergencySettingsElderButton;

   @FXML
   private Label emergencySettingsElderLabel;

   @FXML
   private Line emergencySettingsElderLine;

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
   private JFXProgressBar aquariumPanelElderProgressBar1;

   @FXML
   private JFXProgressBar aquariumPanelElderProgressBar2;

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
   private JFXButton greenhousePanelElderSettingsButton;

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
   private JFXButton gasPanelElderSettingsButton;

   @FXML
   private JFXProgressBar gasPanelElderProgressBar1;

   @FXML
   private JFXProgressBar gasPanelElderProgressBar2;

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
   private ChoiceBox<?> speciesOfFishChoice;

   @FXML
   private Label FeedingStartTimeLabel;

   @FXML
   private JFXTimePicker feedingStartTimeTimePicker;

   @FXML
   private Label weeklyWaterExchangeDayAndTimeLabel;

   @FXML
   private JFXComboBox< String > weeklyWaterExchangeDayAndTimeChoice;

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

   @FXML
   private Pane languageSettingsElderPanel;

   @FXML
   private Label languagePanelFirstLabelElder;

   @FXML
   private JFXButton languageElderPanelBackButtonElder;

   @FXML
   private Label languageElderPanelBackButtonSubLabel;

   @FXML
   private JFXButton englishElderButton;

   @FXML
   private Label englishElderButtonSubLabelPassive;

   @FXML
   private Label englishElderButtonSubLabelActive;

   @FXML
   private JFXButton turkishElderButton;

   @FXML
   private Label turkishElderButtonSubLabelPassive;

   @FXML
   private Label turkishElderButtonSubLabelActive;

   @FXML
   private Pane electiricitySettingsPanel;

   @FXML
   private Label electiricityMenuFirstLabelElder;

   @FXML
   private JFXButton electiricityMenuBackButtonElder;

   @FXML
   private Label electiricityMenuBackButtonSubLabel;

   @FXML
   private BarChart< Number, Number> electiricitySettingsPanelChart;

   @FXML
   private JFXToggleButton electiricitySettingsPanelCloseOpenSwitch;

   @FXML
   private Pane gasSettingsPanel;

   @FXML
   private Label gasMenuFirstLabelElder;

   @FXML
   private JFXButton gasMenuBackButtonElder;

   @FXML
   private Label gasMenuBackButtonSubLabel;

   @FXML
   private BarChart<Number, Number> gasSettingsPanelChart;

   @FXML
   private JFXToggleButton gasSettingsPanelCloseOpenSwitch;

   @FXML
   private Pane greenhouseSettingsPanel;

   @FXML
   private Label greenhouseMenuFirstLabelElder;

   @FXML
   private JFXButton greenhouseMenuBackButtonElder;

   @FXML
   private Label greenhouseMenuBackButtonSubLabel;

   @FXML
   private LineChart<Number, Number> greenhouseSettingsPanelChart;

   @FXML
   private Label latesWaterFromAquariumLabel;

   @FXML
   private Label latesWaterFromAquariumVariable;

   @FXML
   private Label humidityLabel;

   @FXML
   private Label humidityVariable;

   @FXML
   private Label greenhouseTemperatureLabel;

   @FXML
   private Label greenhouseTemperatureVariable;

   @FXML
   private Pane connectionSettingsElderPanel;

   @FXML
   private Label connectionPanelFirstLabelElder;

   @FXML
   private JFXButton connectionElderPanelBackButtonElder;

   @FXML
   private Label connectionElderPanelBackButtonSubLabel;

   @FXML
   private ComboBox<String> portChooserElder;

   @FXML
   private CheckComboBox<String> speciesOfFishCheckComboBox;

   @FXML
   private CheckComboBox< String > checkComboBox;

   @FXML
   private JFXSlider dailyAirEngineRunTimeandStartTimeSlider;

   @FXML
   private JFXButton elderLogoutButton;

   @FXML
   private JFXButton portConnectionButtonElder;

   @FXML
   private ToggleButton internalSirenToggle, externalSirenToggle;


   @FXML
   private Pane notificationSettingsElderPanel;

   @FXML
   private Label notificationPanelFirstLabelElder;

   @FXML
   private JFXButton notificationElderPanelBackButtonElder;

   @FXML
   private Label connectionElderPanelBackButtonSubLabel1;

   @FXML
   private JFXToggleButton fireButtonVisualToggle;

   @FXML
   private JFXToggleButton gasSensorVisualToggle;

   @FXML
   private JFXToggleButton smokeSensorVisualToggle;

   @FXML
   private JFXToggleButton fireButtonSoundToggle;

   @FXML
   private JFXToggleButton gasSensorSoundToggle;

   @FXML
   private JFXToggleButton smokeSensorSoundToggle;

   @FXML
   private JFXButton notificationSettingsElderButton;

   @FXML
   private ImageView elderLogoutButtonActive;

   @FXML
   private Label notificationSettingsElderLabel;

   @FXML
   private Label notificationElderPanelBackButtonSubLabel;

   @FXML
   private Line notificationSettingsElderLine;

   @FXML
   private Label aquariumSettingsWarningLabel;

   @FXML
   private JFXButton doorButton;

   @FXML
   private JFXSpinner doorSpinner;

   @FXML
   private JFXButton houseMenuElderBulkChange;

   @FXML
   private Pane timeConfigurationPanel;

   @FXML
   private Label timeConfigurationFirstLabel;

   @FXML
   private JFXButton timeConfigurationBackButton;

   @FXML
   private Label timeConfigurationBackButtonSubLabel;

   @FXML
   private JFXTimePicker menuTimePicker;

   @FXML
   private JFXDatePicker menuDatePicker;

   @FXML
   private JFXButton dateTimeSaveButton;

   @FXML
   private Label timeConfigurationPaneWarningLabel;

   @FXML
   private Label dateTimeSaveButtonSubLabel;


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

   @Override
   public void initialize( URL location, ResourceBundle resources )
   {
      exit = false;
      isArduinoConnect = false;
      volume = "0";
      userTextFieldController( getLoginUser( ) );
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
            while(true){
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

   //1
   /**
    * This method is responsible control main Elder panel which come out when user log in.
    * Every button which is in the main panel is processed in this method.
    * @param event
    */
   @FXML
   void actionPerformed( ActionEvent event ) throws SQLException {
      // settings elder panel -MS 23.04.2020-
      if( event.getSource( ) == settingsBackButtonElder ) {
         settingsElderPanel.setVisible( false );
         setMainMenuInvisible( false );
      }
      // settings elder panel END -MS 23.04.2020
      else if( event.getSource( ) == houseMenuBackButtonElder ) {
         houseMenuElderPane.setVisible( false );
         setMainMenuInvisible( false );
      }
      else if( event.getSource( ) == userProfileBackButtonElder ) {
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

      // applicationBackButtonElder -MS 04.05.2020-
      else if ( event.getSource() == applicationBackButtonElder )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         settingsElderPanel.setVisible( true );
         settingsElderPanel.setDisable( false );
      }
      // applicationBackButtonElder END -MS 04.05.2020-

      // applicationSettingsElderButton -MS 04.05.2020-
      else if( event.getSource() == applicationSettingsElderButton )
      {
         settingsElderPanel.setDisable( true );
         settingsElderPanel.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
      // applicationSettingsElderButton END -MS 04.05.2020-

      // languageSettingsElderButton -MS 06.05.2020-
      else if( event.getSource() == languageSettingsElderButton )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         languageSettingsElderPanel.setVisible( true );
         languageSettingsElderPanel.setDisable( false );
      }
      // languageSettingsElderButton END -MS 06.05.2020-

      // languageElderPanelBackButtonElder -MS 06.05.2020-
      else if ( event.getSource() == languageElderPanelBackButtonElder )
      {
         languageSettingsElderPanel.setDisable( true );
         languageSettingsElderPanel.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
      // languageElderPanelBackButtonElder END -MS 06.05.2020-

      // turkishElderButton -MS 06.05.2020-
      else if ( event.getSource() == turkishElderButton )
      {
         String language;
         language = "";
         englishElderButtonSubLabelActive.setVisible( false );
         turkishElderButtonSubLabelActive.setVisible( true );
         languageStatusElder = true;
         changeLanguageElder( "tr" );
         language = "TÜRKÇE";
         //Users.getInstance().updateLanguage( loginUser, language );
         //userTextFieldController( loginUser );
      }
      // turkishElderButton END -MS 06.05.2020-

      // englishElderButton -MS 06.05.2020-
      else if ( event.getSource() == englishElderButton )
      {
         String language;
         language = "";
         turkishElderButtonSubLabelActive.setVisible( false );
         englishElderButtonSubLabelActive.setVisible( true );
         languageStatusElder = false;
         changeLanguageElder( "en" );
         language = "ENGLISH";
         //Users.getInstance().updateLanguage( loginUser, language );
         //userTextFieldController( loginUser );
      }
      // englishElderButton END -MS 06.05.2020-

      // electricityPanelElderSettingsButton -MS 06.05.2020-
      else if( event.getSource() == electricityPanelElderSettingsButton )
      {
         houseMenuElderPane.setDisable( true );
         houseMenuElderPane.setVisible( false );
         electiricitySettingsPanel.setVisible( true );
         electiricitySettingsPanel.setDisable( false );
      }
      // electricityPanelElderSettingsButton END -MS 06.05.2020-

      // electiricityMenuBackButtonElder -MS 06.05.2020-
      else if( event.getSource() == electiricityMenuBackButtonElder )
      {
         electiricitySettingsPanel.setDisable( true );
         electiricitySettingsPanel.setVisible( false );
         houseMenuElderPane.setVisible( true );
         houseMenuElderPane.setDisable( false );
      }
      // electiricityMenuBackButtonElder END -MS 06.05.2020-

      // gasPanelElderSettingsButton -MS 06.05.2020-
      else if( event.getSource() == gasPanelElderSettingsButton )
      {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         gasSettingsPanel.setVisible( true );
         gasSettingsPanel.setDisable( false );
      }
      // gasPanelElderSettingsButton END -MS 06.05.2020-

      // gasMenuBackButtonElder -MS 07.05.2020-
      else if ( event.getSource() == gasMenuBackButtonElder )
      {
         gasSettingsPanel.setDisable( true );
         gasSettingsPanel.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      }
      // gasMenuBackButtonElder END -MS 07.05.2020-

      // greenhousePanelElderSettingsButton -MS 07.05.2020-
      else if ( event.getSource() == greenhousePanelElderSettingsButton )
      {
         houseMenuElderPane2.setDisable( true );
         houseMenuElderPane2.setVisible( false );
         greenhouseSettingsPanel.setVisible( true );
         greenhouseSettingsPanel.setDisable( false );
      }
      // greenhousePanelElderSettingsButton END -MS 07.05.2020-

      // greenhouseMenuBackButtonElder -MS 07.05.2020-
      else if ( event.getSource() == greenhouseMenuBackButtonElder )
      {
         greenhouseSettingsPanel.setDisable( true );
         greenhouseSettingsPanel.setVisible( false );
         houseMenuElderPane2.setVisible( true );
         houseMenuElderPane2.setDisable( false );
      }
      // greenhouseMenuBackButtonElder END -MS 07.05.2020-



      // connectionSettingsElderButton -MS 11.05.2020-
      else if ( event.getSource() == connectionSettingsElderButton )
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         connectionSettingsElderPanel.setVisible( true );
         connectionSettingsElderPanel.setDisable( false );
         refreshPortList();
      }
      // connectionSettingsElderButton END -MS 11.05.2020-

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

      // notificationSettingsElderButton Activate -MS 18.05.2020-
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

      // connectionElderPanelBackButtonElder -MS 11.05.2020-
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
      else if( event.getSource() == doorButton )
      {
         if( isArduinoConnect )
            home.getDoor().open( true );

         new Thread( () -> {
            for( int k = 0; k < 20; k++ ) {
               final int j = k;
               Platform.runLater( () -> {
                  doorSpinner.setVisible( true );
                  doorButton.setVisible( false );
                  if( j == 19 ) {
                     doorSpinner.setVisible( false );
                     doorButton.setVisible( true );
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
   }

   //  elderMainPanelOperations -MS 17.05.2020-


   public void elderMainPanelOperations( ActionEvent event )
   {

   }

   //2
   // Make the main menu invisible or visible -MSACAKCI 03.04.2020-
   public void setMainMenuInvisible( Boolean b ) {
      if( b ) {
         mainElderPanelMenu.setVisible( false );
      } else {
         mainElderPanelMenu.setVisible( true );
      }
   }


   //Set the time END -MS 09.04.2020-

   //3


   //4
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


   //5
   //User textFieldController -MS 24.04.2020-
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
         //language = "ENGLISH";

      } else if( loginUser.getPreferredLanguage().equals( "TÜRKÇE" ) ) {
         englishElderButtonSubLabelActive.setVisible( false );
         turkishElderButtonSubLabelActive.setVisible( true );
         languageStatusElder = true;
         changeLanguageElder( "tr" );
      }
   }
   //User textFieldController END -MS 24.04.2020-


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

   @FXML
   void buttonElderActivate ( MouseEvent event )
   {
      // userProfileButtonElderActivate -MS 03.04.2020-
      if ( event.getSource() == userProfileButtonElder )
      {
         userProfileSubLabelActive.setVisible( true );
         userProfileSubLabelNotActive.setVisible( false );
         userProfileElderActiveImage.setVisible( true );
      }
      // userProfileButtonElderActivate END -MS 03.04.2020-
      else if ( event.getSource() == houseMenuButtonElder )
      {
         menuSubLabelActive.setVisible( true );
         menuSubLabelNotActive.setVisible( false );
         houseMenuElderActiveImage.setVisible( true );
      }

      //Settings Button Activate -MS 05.04.2020-
      else if ( event.getSource() == settingsButtonElder )
      {
         settingsSubLabelActive.setVisible( true );
         settingsSubLabelNotActive.setVisible( false );
         settingsButtonElderActiveImage.setVisible( true );
      }
      //Settings Button Activate END -MS 05.04.2020-

      //Electricity Button Activate -MS 09.04.2020-
      else if ( ( event.getSource() == electricityButtonElder ) || ( event.getSource() == electricityLabelElder ) )
      {
         electricityLabelElder.setVisible( true );
         electricityLineElder.setVisible( true );
      }
      //Electricity Button Activate END -MS 09.04.2020-

      //Lights Button -MS 09.04.2020-
      else if ( ( event.getSource() == lightsButtonElder ) || ( event.getSource() == lightsLabelElder) )
      {
         lightsLabelElder.setVisible( true );
         lightsLineElder.setVisible( true );
      }
      //Lights Button Activate END -MS 09.04.2020-

      //Water Control Button Activate -MS 09.04.2020-
      else if ( (event.getSource() == waterButtonElder ) )
      {
         waterLabelElder.setVisible( true );
         waterLineElder.setVisible( true );
      }
      //Water Control Button Activate END -MS 09.04.2020-

      //Temperature Button Activate -MS 09.04.2020-
      else if ( event.getSource() == temperatureButtonElder )
      {
         temperatureLabelElder.setVisible( true );
         temperatureLineElder.setVisible( true );
      }
      //Temperature Button Activate END -MS 09.04.2020-

      //Back Button Activate -MS 09.04.2020-
      else if ( event.getSource() == houseMenuBackButtonElder )
      {
         houseMenuBackButtonSubLabel.setVisible( true );
      }
      //Back Button Activate END -MS 09.04.2020-

      //Back 2 Button Activate -MS 02.05.2020-
      else if ( event.getSource() == houseMenuBackButtonElder2 )
      {
         houseMenuBackButtonSubLabel2.setVisible( true );
      }
      //Back 2 Button Activate END -MS 02.05.2020-

      //Electricity panel elder back button Activate -MS 10.04.2020-
      else if ( event.getSource() == electricityPanelElderCloseButton )
      {
         electricityPanelElderCloseButtonActive.setVisible( true );
      }
      //Electricity panel elder back button Activate END -MS 10.04.2020-

      //Water panel elder back button Activate -MS 10.04.2020-
      else if ( event.getSource() == waterPanelElderCloseButton )
      {
         waterPanelElderCloseButtonActive.setVisible( true );
      }
      //Water panel elder back button Activate END -MS 10.04.2020-

      //Temperature panel elder back button Activate -MS 10.04.2020-
      else if ( event.getSource() == temperaturePanelElderCloseButton )
      {
         temperaturePanelElderCloseButtonActive.setVisible( true );
      }
      //Temperature panel elder back button Activate END -MS 10.04.2020-

      //Lights panel elder back button Activate -MS 10.04.2020-
      else if ( event.getSource() == lightsPanelElderCloseButton )
      {
         lightsPanelElderCloseButtonActive.setVisible( true );
      }
      //Lights panel elder back button Activate END -MS 10.04.2020-

      //applicationSettingsElderButton Activate -MS 23.04.2020-
      else if ( event.getSource() == applicationSettingsElderButton )
      {
         applicationSettingsElderLabel.setVisible( true );
         applicationSettingsElderLine.setVisible( true );
      }
      //applicationSettingsElderButton Activate END -MS 23.04.2020-

      //homeSettingsElder Activate -MS 23.04.2020-
      else if ( event.getSource() == timeConfigurationElderButton )
      {
         homeSettingsElderLabel.setVisible( true );
         homeSettingsElderLine.setVisible( true );
      }
      //homeSettingsElder Activate END -MS 23.04.2020-

      //languageSettingsElder Activate -MS 23.04.2020-
      else if ( event.getSource() == languageSettingsElderButton )
      {
         languageSettingsElderLabel.setVisible( true );
         languageSettingsElderLine.setVisible( true );
      }
      //languageSettingsElder Activate END -MS 23.04.2020-

      //emergencySettingsElder Activate -MS 23.04.2020-
      else if ( event.getSource() == emergencySettingsElderButton )
      {
         emergencySettingsElderLabel.setVisible( true );
         emergencySettingsElderLine.setVisible( true );
      }
      //emergencySettingsElder Activate END -MS 23.04.2020-

      //connectionSettingsElderButton Activate -MS 23.04.2020-
      else if ( event.getSource() == connectionSettingsElderButton )
      {
         connectionSettingsElderLabel.setVisible( true );
         connectionSettingsElderLine.setVisible( true );
      }
      //connectionSettingsElderButton Activate -MS 23.04.2020-

      //user settings change info panel Activate -MS 24.04.2020-
      else if ( event.getSource() == userSettingsChangeBackButton )
      {
         userSettingsChangeBackButtonSubLabel.setVisible( true );
         userSettingsChangeBackButtonImage.setVisible( true );
      }
      //user settings change info panel Activate END -MS 24.04.2020-

      else if ( event.getSource() == userSettingsChangeSave )
      {
         userSettingsChangeSaveSubLabel.setVisible( true );
         userSettingsChangeSaveImage.setVisible( true );
      }

      // userSettingsChangeInfoButton Activate -MS 24.04.2020-
      else if ( event.getSource() == userSettingsChangeInfoButton )
      {
         userSettingsChangeInfoButtonImage.setVisible( true );
         userSettingsChangeInfoButtonSubLabel.setVisible( true );
      }
      // userSettingsChangeInfoButton Activate END -MS 24.04.2020-

      // userSettingsUPChangeButton Activate -MS 26.04.2020-
      else if ( event.getSource() == userSettingsUPChangeButton )
      {
         userSettingsUPChangeButtonImage.setVisible( true );
         userSettingsUPChangeLabel.setVisible( true );
      }
      // userSettingsUPChangeButton Activate END -MS 26.04.2020-

      // userSettingsPanelTwoChangeSaveButton Activate -MS 26.04.2020-
      else if ( event.getSource() == userSettingsPanelTwoChangeSaveButton )
      {
         userSettingsPanelTwoChangeSaveButtonImage.setVisible( true );
         userSettingsPanelTwoChangeSaveLabel.setVisible( true );
      }
      // userSettingsPanelTwoChangeSaveButton Activate END -MS 26.04.2020-

      // userSettingsPanelTwoChangeBackButton Activate -MS 26.04.2020-
      else if ( event.getSource() == userSettingsPanelTwoChangeBackButton )
      {
         userSettingsPanelTwoChangeBackButtonImage.setVisible( true );
         userSettingsPanelTwoChangeBackLabel.setVisible( true );
      }
      // userSettingsPanelTwoChangeBackButton Activate END -MS 26.04.2020-

      // userProfileBackButtonElder Activate -MS 01.05.2020-
      else if ( event.getSource() == userProfileBackButtonElder )
      {
         userProfileBackButtonElderSubLabel.setVisible( true );
      }
      // userProfileBackButtonElder Activate END -MS 01.05.2020-

      // settingsBackButtonElder Activate -MS 01.05.2020-
      else if ( event.getSource() == settingsBackButtonElder )
      {
         settingsBackButtonSubLabel.setVisible( true );
      }
      // settingsBackButtonElder Activate END -MS 01.05.2020-

      //aquarium Button Activate -MS 02.05.2020-
      else if ( event.getSource() == aquariumButtonElder)
      {
         aquariumLabelElder.setVisible( true );
         aquariumLineElder.setVisible( true );
      }
      //aquarium Button Activate END -MS 02.05.2020-

      // aquarium panel elder back button Activate -MS 02.05.2020-
      else if ( event.getSource() == aquariumPanelElderCloseButton)
      {
         aquariumPanelElderCloseButtonActive.setVisible( true );
      }
      // aquarium panel elder back button Activate END -MS 02.05.2020-

      //greenhouse Button Activate -MS 02.05.2020-
      else if ( event.getSource() == greenhouseButtonElder )
      {
         greenhouseLabelElder.setVisible( true );
         greenhouseLineElder.setVisible( true );
      }
      //greenhouse Button Activate END -MS 02.05.2020-

      // greenhouse panel elder back button -MS 02.05.2020-
      else if ( event.getSource() == greenhousePanelElderCloseButton )
      {
         greenhousePanelElderCloseButtonActive.setVisible( true );
      }
      // greenhouse panel elder back button END -MS 02.05.2020-

      //gas Button Activate -MS 02.05.2020-
      else if ( event.getSource() == gasButtonElder )
      {
         gasLabelElder.setVisible( true );
         gasLineElder.setVisible( true );
      }
      //gas Button Activate END -MS 02.05.2020-

      // gas panel elder back button Activate -MS 02.05.2020-
      else if ( event.getSource() == gasPanelElderCloseButton )
      {
         gasPanelElderCloseButtonActive.setVisible( true );
      }
      // gas panel elder back button Activate END -MS 02.05.2020-

      //door Button Activate -MS 02.05.2020-
      else if ( event.getSource() == doorButtonElder )
      {
         doorLabelElder.setVisible( true );
         doorLineElder.setVisible( true );
      }
      //door Button Activate END -MS 02.05.2020-

      // door panel elder back button Activate -MS 02.05.2020-
      else if ( event.getSource() == doorPanelElderCloseButton )
      {
         doorPanelElderCloseButtonActive.setVisible( true );
      }
      // door panel elder back button Activate END -MS 02.05.2020-

      // aquariumSaveButton Activate -MS 03.05.2020-
      else if ( event.getSource() == aquariumSaveButton )
      {
         aquariumSaveButtonLabel.setVisible( true );
      }
      // aquariumSaveButton Activate END -MS 03.05.2020-

      //aquariumMenuBackButtonElder Activate -MS 03.05.2020-
      else if ( event.getSource() == aquariumMenuBackButtonElder )
      {
         aquariumMenuBackButtonSubLabel.setVisible( true );
      }
      //aquariumMenuBackButtonElder Activate END -MS 03.05.2020-

      // applicationBackButtonElder Activate  -MS 04.05.2020-
      else if ( event.getSource() == applicationBackButtonElder )
      {
         applicationBackButtonSubLabel.setVisible( true );
      }
      // applicationBackButtonElder Activate END -MS 04.05.2020-

      // applicationBackButtonElder Activate  -MS 06.05.2020-
      else if ( event.getSource() == languageElderPanelBackButtonElder )
      {
         languageElderPanelBackButtonSubLabel.setVisible( true );
      }
      // applicationBackButtonElder Activate END -MS 06.05.2020-

      // englishElderButton Activate -MS 06.05.2020-
      else if ( event.getSource() == englishElderButton )
      {
         englishElderButtonSubLabelActive.setVisible( true );
         englishElderButtonSubLabelPassive.setVisible( false );
      }
      // englishElderButton Activate END -MS 06.05.2020-

      // turkishElderButton Activate -MS 06.05.2020-
      else if ( event.getSource() == turkishElderButton )
      {
         turkishElderButtonSubLabelActive.setVisible( true );
         turkishElderButtonSubLabelPassive.setVisible( false );
      }
      // turkishElderButton Activate END -MS 06.05.2020-

      // electiricityMenuBackButtonElder Activate -MS 06.05.2020-
      else if( event.getSource() == electiricityMenuBackButtonElder )
      {
         electiricityMenuBackButtonSubLabel.setVisible( true );
      }
      // electiricityMenuBackButtonElder Activate END -MS 06.05.2020-

      // gasMenuBackButtonElder Activate -MS 07.05.2020-
      else if ( event.getSource() == gasMenuBackButtonElder )
      {
         gasMenuBackButtonSubLabel.setVisible( true );
      }
      // gasMenuBackButtonElder Activate END -MS 07.05.2020-

      // greenhouseSettingsPanel Activate -MS 07.05.2020-
      else if ( event.getSource() == greenhouseMenuBackButtonElder )
      {
         greenhouseMenuBackButtonSubLabel.setVisible( true );
      }
      // greenhouseSettingsPanel Activate END -MS 07.05.2020-

      //connectionElderPanelBackButtonElder Activate -MS 11.05.2020-
      else if ( event.getSource() == connectionElderPanelBackButtonElder )
      {
         connectionElderPanelBackButtonSubLabel.setVisible( true );
      }
      //connectionElderPanelBackButtonElder Activate END -MS 11.05.2020-

      // elderLogoutButton Activate -MS 18.05.2020-
      else if( event.getSource() == elderLogoutButton )
      {
         elderLogoutButtonActive.setVisible( true );
      }

      // notificationSettingsElderButton Activate -MS 18.05.2020-
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
   }
   // buttonElderActivate END -MS 05.05.2020-

   // buttonElderDeactivate -MS 05.05.2020-
   @FXML
   void buttonElderDeactivate ( MouseEvent event )
   {
      // userProfileButtonElderDeactivate -MS 03.04.2020-
      if ( event.getSource() == userProfileButtonElder )
      {
         userProfileSubLabelActive.setVisible( false );
         userProfileSubLabelNotActive.setVisible( true );
         userProfileElderActiveImage.setVisible( false );
      }
      // userProfileButtonElderDeactivate END -MS 03.04.2020-
      else if ( event.getSource() == houseMenuButtonElder )
      {
         menuSubLabelActive.setVisible( false );
         menuSubLabelNotActive.setVisible( true );
         houseMenuElderActiveImage.setVisible( false );
      }

      //Settings Button Deactivate -MS 05.04.2020-
      else if ( event.getSource() == settingsButtonElder )
      {
         settingsSubLabelActive.setVisible( false );
         settingsSubLabelNotActive.setVisible( true );
         settingsButtonElderActiveImage.setVisible( false );
      }
      //Settings Button Deactivate END -MS 05.04.2020-

      //Electricity Button Deactivate -MS 09.04.2020-
      else if ( ( event.getSource() == electricityButtonElder ) || ( event.getSource() == electricityLabelElder ) )
      {
         electricityLabelElder.setVisible( false );
         electricityLineElder.setVisible( false );
      }
      //Electricity Button Deactivate END -MS 09.04.2020-

      //Lights Button -MS 09.04.2020-
      else if ( ( event.getSource() == lightsButtonElder ) || ( event.getSource() == lightsLabelElder) )
      {
         lightsLabelElder.setVisible( false );
         lightsLineElder.setVisible( false );
      }
      //Lights Button Deactivate END -MS 09.04.2020-

      //Water Control Button Deactivate -MS 09.04.2020-
      else if ( (event.getSource() == waterButtonElder ) )
      {
         waterLabelElder.setVisible( false );
         waterLineElder.setVisible( false );
      }
      //Water Control Button Deactivate END -MS 09.04.2020-

      //Temperature Button Deactivate -MS 09.04.2020-
      else if ( event.getSource() == temperatureButtonElder )
      {
         temperatureLabelElder.setVisible( false );
         temperatureLineElder.setVisible( false );
      }
      //Temperature Button Deactivate END -MS 09.04.2020-

      //Back Button Deactivate -MS 09.04.2020-
      else if ( event.getSource() == houseMenuBackButtonElder )
      {
         houseMenuBackButtonSubLabel.setVisible( false );
      }
      //Back Button Deactivate END -MS 09.04.2020-

      //Back 2 Button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == houseMenuBackButtonElder2 )
      {
         houseMenuBackButtonSubLabel2.setVisible( false );
      }
      //Back 2 Button Deactivate END -MS 02.05.2020-

      //Electricity panel elder back button Deactivate -MS 10.04.2020-
      else if ( event.getSource() == electricityPanelElderCloseButton )
      {
         electricityPanelElderCloseButtonActive.setVisible( false );
      }
      //Electricity panel elder back button Deactivate END -MS 10.04.2020-

      //Water panel elder back button Deactivate -MS 10.04.2020-
      else if ( event.getSource() == waterPanelElderCloseButton )
      {
         waterPanelElderCloseButtonActive.setVisible( false );
      }
      //Water panel elder back button Deactivate END -MS 10.04.2020-

      //Temperature panel elder back button Deactivate -MS 10.04.2020-
      else if ( event.getSource() == temperaturePanelElderCloseButton )
      {
         temperaturePanelElderCloseButtonActive.setVisible( false );
      }
      //Temperature panel elder back button Deactivate END -MS 10.04.2020-

      //Lights panel elder back button Deactivate -MS 10.04.2020-
      else if ( event.getSource() == lightsPanelElderCloseButton )
      {
         lightsPanelElderCloseButtonActive.setVisible( false );
      }
      //Lights panel elder back button Deactivate END -MS 10.04.2020-

      //applicationSettingsElderButton Deactivate -MS 23.04.2020-
      else if ( event.getSource() == applicationSettingsElderButton )
      {
         applicationSettingsElderLabel.setVisible( false );
         applicationSettingsElderLine.setVisible( false );
      }
      //applicationSettingsElderButton Deactivate END -MS 23.04.2020-

      //homeSettingsElder Deactivate -MS 23.04.2020-
      else if ( event.getSource() == timeConfigurationElderButton )
      {
         homeSettingsElderLabel.setVisible( false );
         homeSettingsElderLine.setVisible( false );
      }
      //homeSettingsElder Deactivate END -MS 23.04.2020-

      //languageSettingsElder Deactivate -MS 23.04.2020-
      else if ( event.getSource() == languageSettingsElderButton )
      {
         languageSettingsElderLabel.setVisible( false );
         languageSettingsElderLine.setVisible( false );
      }
      //languageSettingsElder Deactivate END -MS 23.04.2020-

      //emergencySettingsElder Deactivate -MS 23.04.2020-
      else if ( event.getSource() == emergencySettingsElderButton )
      {
         emergencySettingsElderLabel.setVisible( false );
         emergencySettingsElderLine.setVisible( false );
      }
      //emergencySettingsElder Deactivate END -MS 23.04.2020-

      //connectionSettingsElderButton Deactivate -MS 23.04.2020-
      else if ( event.getSource() == connectionSettingsElderButton )
      {
         connectionSettingsElderLabel.setVisible( false );
         connectionSettingsElderLine.setVisible( false );
      }
      //connectionSettingsElderButton Deactivate -MS 23.04.2020-

      //user settings change info panel Deactivate -MS 24.04.2020-
      else if ( event.getSource() == userSettingsChangeBackButton )
      {
         userSettingsChangeBackButtonSubLabel.setVisible( false );
         userSettingsChangeBackButtonImage.setVisible( false );
      }
      //user settings change info panel Deactivate END -MS 24.04.2020-

      else if ( event.getSource() == userSettingsChangeSave )
      {
         userSettingsChangeSaveSubLabel.setVisible( false );
         userSettingsChangeSaveImage.setVisible( false );
      }

      // userSettingsChangeInfoButton Deactivate -MS 24.04.2020-
      else if ( event.getSource() == userSettingsChangeInfoButton )
      {
         userSettingsChangeInfoButtonImage.setVisible( false );
         userSettingsChangeInfoButtonSubLabel.setVisible( false );
      }
      // userSettingsChangeInfoButton Deactivate END -MS 24.04.2020-

      // userSettingsUPChangeButton Deactivate -MS 26.04.2020-
      else if ( event.getSource() == userSettingsUPChangeButton )
      {
         userSettingsUPChangeButtonImage.setVisible( false );
         userSettingsUPChangeLabel.setVisible( false );
      }
      // userSettingsUPChangeButton Deactivate END -MS 26.04.2020-

      // userSettingsPanelTwoChangeSaveButton Deactivate -MS 26.04.2020-
      else if ( event.getSource() == userSettingsPanelTwoChangeSaveButton )
      {
         userSettingsPanelTwoChangeSaveButtonImage.setVisible( false );
         userSettingsPanelTwoChangeSaveLabel.setVisible( false );
      }
      // userSettingsPanelTwoChangeSaveButton Deactivate END -MS 26.04.2020-

      // userSettingsPanelTwoChangeBackButton Deactivate -MS 26.04.2020-
      else if ( event.getSource() == userSettingsPanelTwoChangeBackButton )
      {
         userSettingsPanelTwoChangeBackButtonImage.setVisible( false );
         userSettingsPanelTwoChangeBackLabel.setVisible( false );
      }
      // userSettingsPanelTwoChangeBackButton Deactivate END -MS 26.04.2020-

      // userProfileBackButtonElder Deactivate -MS 01.05.2020-
      else if ( event.getSource() == userProfileBackButtonElder )
      {
         userProfileBackButtonElderSubLabel.setVisible( false );
      }
      // userProfileBackButtonElder Deactivate END -MS 01.05.2020-

      // settingsBackButtonElder Deactivate -MS 01.05.2020-
      else if ( event.getSource() == settingsBackButtonElder )
      {
         settingsBackButtonSubLabel.setVisible( false );
      }
      // settingsBackButtonElder Deactivate END -MS 01.05.2020-

      //aquarium Button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == aquariumButtonElder)
      {
         aquariumLabelElder.setVisible( false );
         aquariumLineElder.setVisible( false );
      }
      //aquarium Button Deactivate END -MS 02.05.2020-

      // aquarium panel elder back button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == aquariumPanelElderCloseButton)
      {
         aquariumPanelElderCloseButtonActive.setVisible( false );
      }
      // aquarium panel elder back button Deactivate END -MS 02.05.2020-

      //greenhouse Button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == greenhouseButtonElder )
      {
         greenhouseLabelElder.setVisible( false );
         greenhouseLineElder.setVisible( false );
      }
      //greenhouse Button Deactivate END -MS 02.05.2020-

      // greenhouse panel elder back button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == greenhousePanelElderCloseButton )
      {
         greenhousePanelElderCloseButtonActive.setVisible( false );
      }
      // greenhouse panel elder back button Deactivate END -MS 02.05.2020-

      //gas Button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == gasButtonElder )
      {
         gasLabelElder.setVisible( false );
         gasLineElder.setVisible( false );
      }
      //gas Button Deactivate END -MS 02.05.2020-

      // gas panel elder back button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == gasPanelElderCloseButton )
      {
         gasPanelElderCloseButtonActive.setVisible( false );
      }
      // gas panel elder back button Deactivate END -MS 02.05.2020-

      //door Button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == doorButtonElder )
      {
         doorLabelElder.setVisible( false );
         doorLineElder.setVisible( false );
      }
      //door Button Deactivate END -MS 02.05.2020-

      // door panel elder back button Deactivate -MS 02.05.2020-
      else if ( event.getSource() == doorPanelElderCloseButton )
      {
         doorPanelElderCloseButtonActive.setVisible( false );
      }
      // door panel elder back button Deactivate END -MS 02.05.2020-

      // aquariumSaveButton Deactivate -MS 03.05.2020-
      else if ( event.getSource() == aquariumSaveButton )
      {
         aquariumSaveButtonLabel.setVisible( false );
      }
      // aquariumSaveButton Deactivate END -MS 03.05.2020-

      //aquariumMenuBackButtonElder Deactivate -MS 03.05.2020-
      else if ( event.getSource() == aquariumMenuBackButtonElder )
      {
         aquariumMenuBackButtonSubLabel.setVisible( false );
      }
      //aquariumMenuBackButtonElder Deactivate END -MS 03.05.2020-

      // applicationBackButtonElder Deactivate  -MS 04.05.2020-
      else if ( event.getSource() == applicationBackButtonElder )
      {
         applicationBackButtonSubLabel.setVisible( false );
      }
      // applicationBackButtonElder Deactivate END -MS 04.05.2020-

      // applicationBackButtonElder Deactivate  -MS 06.05.2020-
      else if ( event.getSource() == languageElderPanelBackButtonElder )
      {
         languageElderPanelBackButtonSubLabel.setVisible( false );
      }
      // applicationBackButtonElder Deactivate END -MS 06.05.2020-

      // englishElderButton Deactivate -MS 06.05.2020-
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
      // englishElderButton Deactivate END -MS 06.05.2020-

      // turkishElderButton Deactivate -MS 06.05.2020-
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
      // turkishElderButton Deactivate END -MS 06.05.2020-

      // electiricityMenuBackButtonElder Deactivate -MS 06.05.2020-
      else if( event.getSource() == electiricityMenuBackButtonElder )
      {
         electiricityMenuBackButtonSubLabel.setVisible( false );
      }
      // electiricityMenuBackButtonElder Deactivate END -MS 06.05.2020-

      // gasMenuBackButtonElder Deactivate -MS 07.05.2020-
      else if ( event.getSource() == gasMenuBackButtonElder )
      {
         gasMenuBackButtonSubLabel.setVisible( false );
      }
      // gasMenuBackButtonElder Deactivate END -MS 07.05.2020-

      // greenhouseSettingsPanel Deactivate -MS 07.05.2020-
      else if ( event.getSource() == greenhouseMenuBackButtonElder )
      {
         greenhouseMenuBackButtonSubLabel.setVisible( false );
      }
      // greenhouseSettingsPanel Deactivate END -MS 07.05.2020-

      //connectionElderPanelBackButtonElder Deactivate -MS 11.05.2020-
      else if ( event.getSource() == connectionElderPanelBackButtonElder )
      {
         connectionElderPanelBackButtonSubLabel.setVisible( false );
      }
      //connectionElderPanelBackButtonElder Deactivate END -MS 11.05.2020-

      // elderLogoutButton Dectivate -MS 18.05.2020-
      else if( event.getSource() == elderLogoutButton )
      {
         elderLogoutButtonActive.setVisible( false );
      }

      // notificationSettingsElderButton Activate -MS 18.05.2020-
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
         dateTimeSaveButtonSubLabel.setVisible( true );
      }
   }
   // buttonElderDeactivate END -MS 05.05.2020-

   // changeLanguageElder -MS 06.05.2020-
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
         doorPanelElderLabel.setText( bundle.getString("doorLang" ));
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
      } catch( Exception e ) {
         e.printStackTrace();
      }
   }
   // changeLanguageElder END -MS 06.05.2020-


   // house settings controller -MS 11.05.2020- from mainPanel.java
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

   @FXML
   void controlElectricity ( boolean status )
   {
      electricityPanelElderProgressBar1.setVisible( status );
      electricityPanelElderProgressBar2.setVisible( status );
      electiricitySettingsPanelCloseOpenSwitch.setSelected( status );
   }
   @FXML
   void controlLights ( boolean status )
   {
      lightsPanelElderProgressBar1.setVisible( status );
      lightsPanelElderProgressBar2.setVisible( status );
      electiricitySettingsPanelCloseOpenSwitch.setSelected( status );
   }
   @FXML
   void controlGas ( boolean status )
   {
      gasPanelElderProgressBar1.setVisible( status );
      gasPanelElderProgressBar2.setVisible( status );
      gasSettingsPanelCloseOpenSwitch.setSelected( status );
   }
   @FXML
   void controlAquarium ( boolean status )
   {
      aquariumPanelElderProgressBar1.setVisible( status );
      aquariumPanelElderProgressBar2.setVisible( status );
   }
   @FXML
   void controlWater ( boolean status )
   {

   }

   // refreshPortList() -MS 11.05.2020- from mainPanel.java
   void refreshPortList() {
      SerialPort[] portNames;
      portChooserElder.getItems().removeAll( portChooserElder.getItems() );
      portNames = SerialPort.getCommPorts();

      for( SerialPort portName : portNames )
         portChooserElder.getItems().add( portName.getSystemPortName() );
   }
   //

   // GUIUpdateElder -MS 11.05.2020- from mainPanel.java
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

      Thread thread = new Thread( () -> {
         while( !exit ) {
            Platform.runLater( () -> time.setText( LocalDate.now().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) +
                    "   " + LocalTime.now().format( DateTimeFormatter.ofPattern( "HH:mm:ss" ) ) ) );
            try {
               Thread.sleep( 1000 );
            } catch( InterruptedException e ) {
               e.printStackTrace();
            }
         }
      } );
      thread.start();
   }

   public void connectionPanelOperations( ActionEvent event )
   {

   }

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

   public void notificationPanelOperation( ActionEvent event)
   {
      if( event.getSource() == notificationElderPanelBackButtonElder)
      {
         notificationSettingsElderPanel.setDisable( true );
         notificationSettingsElderPanel.setVisible( false );
         applicationElderPanel.setVisible( true );
         applicationElderPanel.setDisable( false );
      }
   }

   public void applicationPanelOperation( ActionEvent event)
   {
      if( event.getSource() == notificationSettingsElderButton)
      {
         applicationElderPanel.setDisable( true );
         applicationElderPanel.setVisible( false );
         notificationSettingsElderPanel.setVisible( true );
         notificationSettingsElderPanel.setDisable( false );
      }
   }
}
