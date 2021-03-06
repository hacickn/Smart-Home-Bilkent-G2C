package com.SmartHomeBilkent;

import animatefx.animation.*;
import com.SmartHomeBilkent.home.Home;
import com.SmartHomeBilkent.utilities.connection.Arduino;
import com.SmartHomeBilkent.utilities.dataBase.*;
import com.SmartHomeBilkent.utilities.dataBase.fields.CommonSetting;
import com.SmartHomeBilkent.utilities.dataBase.fields.User;
import com.SmartHomeBilkent.utilities.weather.WeatherForecast;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * MainPanel class
 * I put extra explanation about the order of the this class
 *
 * @author Hacı Çakın
 * @version 10.05.2020 -> javadoc is added
 */
public class MainPanel implements Initializable {

   //properties
   /*
    1)menu pane variables(76-140)
    2)user profile pane variables(145-178)
    3)settings pane variables(Line 183-193)
    3.1)setting - application settings variables(Line 198-222)
    3.2)setting - users settings variables(Line 227-273)
    3.3)setting - mods settings variables(Line 278-286)
    3.4)setting - home settings variables(Line 291-337)
    3.5)setting - sub menu variables(Line 342-357)
    4)Some independent variables( various objects )(Line 363-380)
    */

   /*
    1)menu pane variables
    */
   @FXML
   private BorderPane commonBorderPane;
   @FXML
   private StackPane menuStackPane;
   @FXML
   private JFXButton menuButton, menuButtonActive,
         userProfileButton, userProfileButtonActive,
         settingsButton, settingsButtonActive,
         logoutButton, menuElecButton,
         menuGasButton, menuAquariumButton,
         menuGreenHouseButton, menuConnectionButton,
         gasSubMenuButtonPassive, gasSubMenuButtonActive,
         aquariumSubMenuButtonPassive, aquariumSubMenuButtonActive,
         greenhouseSubMenuButtonPassive, greenhouseSubMenuButtonActive,
         elecSubMenuButtonPassive, elecSubMenuButtonActive,
         weatherButton, menuWaterButton,
         doorButton, menuGardenLightButton,
         menuBulkChange, bulkChangesSaveButton,
         dateTimeSaveButton, menuTimeConfigurationButton,
         helpButton, menuAquariumFeedButton;
   @FXML
   private JFXRadioButton electricityRadioButton, gasRadioButton,
         waterRadioButton, aquariumRadioButton,
         gardenLightRadioButton, incomingWaterRadioButton,
         sirenRadioButton, feedRadioButton,
         doorRadioButton, outgoingWaterRadioButton;
   @FXML
   private JFXDatePicker menuDatePicker;
   @FXML
   private JFXTimePicker menuTimePicker;
   @FXML
   private JFXProgressBar menuElecProgress, menuGasProgress,
         menuAquariumProgress, menuWaterProgress,
         menuGardenLightProgress;
   @FXML
   private Label ghHumidityTempLabel, timeConfigurationLabel,
         menuHomeTempLabel, mainMenuHomeLabel,
         elecSubPaneLabelPassive, elecSubPaneOpenLabelPassive,
         elecSubPaneCloseLabelPassive, elecSubPaneOpenValueLabelPassive,
         elecSubPaneCloseValueLabelPassive, elecSubPaneLabelActive,
         elecSubPaneOpenLabelActive, elecSubPaneCloseLabelActive,
         elecSubPaneOpenValueLabelActive, elecSubPaneCloseValueLabelActive,
         gasSubPaneLabelPassive, gasSubPaneLabelActive,
         aquariumSubPaneLabelPassive, mainMenuWeatherLabel,
         aquariumSubPaneLabelActive, greenHouseSubPaneLabelPassive,
         ghTempSubPaneLabelActive, menuUserProfileLabel,
         menuMenuLabel, menuSettingLabel,
         menuWeatherValue, timeLabel,
         menuOpenDoorLabel, waterSubPaneLabelActive,
         waterSubPaneLabelPassive, gardenLightSubPaneLabelActive,
         gardenLightSubPaneLabelPassive, menuBulkChangeSubLabel,
         timeConfigurationPaneWarningLabel;
   @FXML
   private ImageView tempImage, weatherForecastImage,
         availabilityImage;
   @FXML
   private Pane menuElecPane, menuGasPane,
         menuAquariumPane, menuGreenHousePane,
         menuWaterPane, menuGardenLightPane,
         menuBulkChangePane, menuTimeConfigurationPane;
   @FXML
   private JFXToggleButton elecSubMenuToggleButton, gasSubMenuToggleButton,
         aquariumSubMenuToggleButton, waterSubMenuToggleButton,
         gardenLightSubMenuToggleButton;
   @FXML
   private ProgressIndicator menuAquariumIndicator;

   /*
    2)user profile variables
    */
   @FXML
   private Label privateInfoWarning, normalInfoWarning,
         userProfileNameLabel, userProfileSurnameLabel,
         userProfileGenderLabel, userProfileAgeLabel,
         userProfileUserNameLabel, userProfilePasswordLabel,
         normalChangeNameLabel, normalChangeSurnameLabel,
         normalChangeBirthdayLabel, normalChangeGenderLabel,
         userNameLabel, newPasswordLabel,
         verifyNewPasswordLabel, currentPasswordLabel,
         userChangerInfoLabel, changeUserInfoLabel,
         changeUserPrivateInfoLabel;
   @FXML
   private AnchorPane userProfileStackPane;
   @FXML
   private Pane userProfilePane, changeUserNormalInfoPane,
         changeUserPrivateInfoPane;
   @FXML
   private JFXTextField userProfileNameField, userProfileSurnameField,
         userProfileAgeField, userProfileGenderField,
         userProfileUserNameField, nameTextField,
         surnameTextField, userNameTextField,
         newPasswordTextField, verifyNewPasswordField,
         currentPasswordField;
   @FXML
   private JFXDatePicker birthdayDateField;
   @FXML
   private JFXButton changeUserNormalInfoButton, changeUserPrivateInfoButton,
         userChangerButton, saveUserNormalInfo,
         backToUserProfileFromNormalInfo, saveUserPrivateInfo,
         backToUserProfileFromPrivateInfo;
   @FXML
   private JFXRadioButton maleRadioOption, femaleRadioOption;
   @FXML
   private ImageView userInfoBoyImage, userInfoGirlImage;

   /*
    3)settings variables
    */
   @FXML
   private AnchorPane settingAnchorPane;
   @FXML
   private JFXButton applicationSettingButton, settingsUsersSettingButton,
         modsSettingButton, homeSettingButton, applicationSettingButtonActive,
         settingsUsersSettingButtonActive, modsSettingButtonActive,
         homeSettingButtonActive, portConnectionButton;
   @FXML
   private Label applicationSettingButtonLabel, usersSettingButtonLabel,
         modsSettingButtonLabel, homeSettingButtonLabel;

   /*
    3.1)setting - application settings variables
    */
   @FXML
   private Pane settingThemePane, settingLanguagePane,
         settingEmergencyPane, settingNotificationPane, settingConnectionPane;
   @FXML
   private JFXRadioButton darkThemeRadioButton, lightThemeRadioButton,
         smoothThemeRadioButton, cartoonThemeRadioButton,
         spaceThemeRadioButton, forestThemeRadioButton,
         neonThemeRadioButton, pyramidThemeRadioButton,
         interstellarThemeRadioButton, cyberpunkThemeRadioButton,
         abstractThemeRadioButton, smartCityThemeRadioButton;

   @FXML
   private JFXButton englishOption, germanOption,
         turkishOption, italianOption,
         franceOption;
   @FXML
   private ImageView themeImage;
   @FXML
   private Label themeTopLabel,settingAuditoryWarningLabel,
         languageTopLabel, emergencyTopLabel,
         notificationTopLabel, connectionTopLabel,
         removeAnUser, settingFireButtonLabel,
         settingGasSensorLabel, settingSmokeSensorLabel,
         settingVisualWarningLabel ;
   @FXML
   private ToggleButton internalSirenToggle, externalSirenToggle,
         fireButtonVisualToggle, gasSensorVisualToggle,
         smokeSensorVisualToggle, fireButtonSoundToggle,
         smokeSensorSoundToggle, gasSensorSoundToggle;

   /*
    3.2)setting - users settings variables
    */
   @FXML
   private Pane settingUsersPane, addUserPane,
         deleteUserPane, permissionPane;
   @FXML
   private JFXTreeTableView< User > settingUserTable;
   @FXML
   private TreeTableColumn< User, String > settingUserTableName, settingUserTableSurname,
         settingUserTableBirthday, settingUserTableGender,
         settingUserTableUserName, settingUserTableTheme,
         settingUserTableLanguage;

   private JFXRadioButton addUserGender, addUserTheme,
         addUserLanguage, addUserType;
   @FXML
   private Label createUserTopLabel, createUserNameLabel,
         createUserSurnameLabel, createUserGenderLabel,
         createUserBirthdayLabel, createUserUserNameLabel,
         createUserPasswordLabel, createUserPasswordVerifyLabel,
         createUserUserTypeLabel, createUserThemeLabel,
         createUserLanguageLabel, createUserWarningLabel,
         removeUserFirstWarning, removeUserHideWarning,
         permissionPaneTopLabel;
   @FXML
   private JFXDatePicker createDatePicker;
   @FXML
   private JFXButton createUserConfirmButton, createUserGoBack,
         removeUserConfirm, removeUserGoBack, usersSettingSubPanePermissionButton, permissionPaneGoBackButton;
   @FXML
   private JFXTextField createUserNameTextField, createUserSurnameTextField,
         createUserUserNameTextField;
   @FXML
   private JFXRadioButton createUserMaleOption, createUserFemaleOption,
         createUserDarkThemeOption, createUserLightThemeOption,
         createUserSmoothThemeOption, createUserCartoonThemeOption,
         createUserEnglishOption, createUserTurkishOption,
         createUserGermanOption, createUserParentOption,
         createUserChildOption, createUserElderOption,
         createUserSpaceThemeOption, createUserForestThemeOption,
         createUserSmartCityThemeOption, createUserAbstractThemeOption,
         createUserPyramidThemeOption, createUserCyberpunkThemeOption,
         createUserNeonThemeOption, createUserInterstellarThemeOption,
         createUserItalianOption, createUserFrenchOption;
   @FXML
   private JFXPasswordField createUserPasswordField, createUserPasswordVerifyField,
         removeUserTextField;
   @FXML
   private ToggleButton permissionPaneElectricityToggle, permissionPaneWaterToggle,
         permissionPaneGardenLightToggle, permissionPaneAquariumToggle,
         permissionPaneNotificationToggle, permissionPaneGasToggle,
         permissionPaneSirenToggle;
   @FXML
   private ImageView addUserImageView;
   /*
    3.3)setting - mods settings variables
    */
   @FXML
   private SplitPane settingModePane;
   @FXML
   private JFXToggleButton textModeToggle, soundModeToggle;
   @FXML
   private Label soundVolumeLabel, interactiveTextModeLabel,
         interactiveSoundModeLabel;
   @FXML
   private JFXSlider soundVolumeSlider;

   /*
    3.4)setting - home settings variables
    */
   @FXML
   private Pane settingElecSettingPane, settingGasSettingPane,
         settingAquSettingPane, settingGreenHouseSettingPane,
         settingWeatherSettingPane;
   @FXML
   private Label homeElecSettingTopLabel, homeGasSettingTopLabel,
         homeAquSettingTopLabel, homeGreenHouseSettingTopLabel,
         homeWeatherSettingTopLabel, homeSubPaneELecLabel,
         homeSubPaneGasLabel, homeSubPaneAquLabel,
         homeSubPaneGreenHouseLabel, homeSubPaneWeatherLabel,
         themeSubLabel, languageSubLabel,
         emergencySubLabel, notificationSubLabel,
         connectionSubLabel, weatherCharacterWarningLabel,
         settingWeatherForecastLabel, settingWeatherTemperatureLabel,
         settingWeatherHumidityLabel, settingWeatherWindLabel,
         informationTime, settingWeatherForecastLabelValue,
         settingWeatherTemperatureLabelValue, settingWeatherHumidityLabelValue,
         settingWeatherWindLabelValue, speciesOfFishLabel,
         feedingStartLabel, waterExchangeLabel,
         airMotorRunTimeLabel, greenHouseTemperatureLabel,
         greenHouseHumidityLabel, latestWaterLabel,
         aquariumSettingLabel;
   @FXML
   private AnchorPane applicationSettingSubPane;
   @FXML
   private JFXButton themeSettingButton, languageSettingButton,
         emergencySettingButton, phoneNotificationSettingButton,
         smartHomeConnectionSettingButton, themeSettingButtonActive,
         languageSettingButtonActive, emergencySettingButtonActive,
         notificationSettingButtonActive, smartHomeConnectionSettingButtonActive,
         saveAquariumChangesButton, updateWeatherButton;
   @FXML
   private JFXTextField settingWeatherLocationTextField;
   @FXML
   private JFXToggleButton settingElectricityToggleButton, settingGasToggleButton;
   @FXML
   private BarChart< Number, Number > electricityUsageTable, gasUsageTable;
   @FXML
   private LineChart< Number, Number > greenHouseValuesChart;
   @FXML
   private CategoryAxis elecBarChartX, gasBarChartX;
   @FXML
   private JFXComboBox< String > portChooser;
   @FXML
   private CheckComboBox< String > checkComboBox;
   @FXML
   private JFXSpinner weatherUpdateSpinner, doorSpinner;

   /*
    3.5)setting - sub menu variables
    */
   @FXML
   private AnchorPane usersSettingSubPane, homeSettingSubPane;
   @FXML
   private JFXButton usersSettingSubPaneAddUser, usersSettingSubPaneRemoveUser,
         homeSettingWeatherButtonActive, homeSettingElecButton,
         homeSettingGasButton, homeSettingAquButton,
         homeSettingGreenHouseButton, homeSettingWeatherButton,
         homeSettingElecButtonActive, homeSettingGasButtonActive,
         homeSettingAquButtonActive, homeSettingGreenHouseButtonActive;
   @FXML
   private JFXTimePicker feedingTime, airMotorStartTime, waterExchangeTime;
   @FXML
   private JFXComboBox< String > waterExchangeDay;
   @FXML
   private JFXSlider airMotorRunTime;


   /*
    4)Some independent variables
    */
   private ResourceBundle bundle;
   private AudioClip audioClip;
   private AudioClip audioClipEmergency;
   private boolean soundCheck;
   private boolean textCheck;
   private String volume;
   private LocalDate localDate;
   private DateTimeFormatter dateTimeFormatter;
   private User loginUser;
   private WeatherForecast weatherForecast;
   private Home home;
   private Arduino arduino;
   private Rectangle rectangle;
   private FillTransition fillTransition;
   private boolean isArduinoConnect;
   private String[] sensors;
   private String[] permissions;
   private CommonSetting commonSetting;
   private boolean exit;

   //constructors

   /**
    * MainPanel is a default constructor
    */
   public MainPanel() {
   }

   //methods
   /*
   1)common methods
   2)menu pane methods
   3)user profile pane methods
   4)setting pane methods
   4.1)application setting pane methods
   4.2)users setting pane methods
   4.3)mods setting pane methods
   4.4)home setting pane methods
    */

   /*
   1)common methods
    */

   /**
    * It is a initialize method that is Overrode
    * This method work before the program runs Therefore in this method;
    * GUI is updated according to user preferences and databases by the
    * GUIUpdate method and setup some functions( for sound mod,
    * emergency situation)
    *
    * @param location  is an URL input parameter
    * @param resources is a ResourceBundle input parameter
    */
   @Override
   public void initialize( URL location, ResourceBundle resources ) {
      exit = false;
      isArduinoConnect = false;
      userPreferenceUpdate( getLoginUser() );
      ElectricityUsage.getInstance().getElectricityUsage();
      GasUsage.getInstance().getGasUsage();
      GreenHouseData.getInstance().getGreenHouseValues();
      FishSpecies.getInstance().getFishes();
      CommonSettingData.getInstance().getAllHome();
      GUIUpdate();
      updateUsersTable();
      usersSettingSubPaneRemoveUser.setDisable( true );
      audioClipEmergency = new AudioClip( this.getClass().getResource( "music/emergencySound.mp3" ).toString() );
      audioClip = new AudioClip( this.getClass().getResource( "music/suprise.mp3" ).toString() );
      audioClipEmergency.setVolume( 1.0 );
      audioClip.setVolume( ( ( double ) Integer.parseInt( volume ) ) / 500 );
      audioClipEmergency.setRate( 1.0 );
      audioClip.setRate( 1.1 );
      refreshPortList();
      createEmergencyAnimation();
   }

   /**
    * It is a getLoginUser method that gets the who enter
    * the application according the their enter properties
    *
    * @return result as an User
    */
   public User getLoginUser() {
      for( User u : Users.getInstance().getUserList() ) {
         if( u.getEnter().equals( "true" ) ) {
            loginUser = u;
            return u;
         }
      }
      return null;
   }

   /**
    * It is a GUIUpdate that is updated GUI. This method is
    * called before the program runs. It gets data from the
    * databases internet server( for weather forecast) and it
    * gets who login the app. According to them, It regulates
    * GUI and starts clock.
    */
   public void GUIUpdate() {
      String flowAquariumSetting;
      ElectricityUsage.getInstance().getTable( electricityUsageTable );
      GasUsage.getInstance().getTable( gasUsageTable );
      GreenHouseData.getInstance().getTable( greenHouseValuesChart, bundle );
      FishSpecies.getInstance().addFishToComboBox( checkComboBox );

      for( int k = 1; k <= 7; k++ ) {
         waterExchangeDay.getItems().add( k + bundle.getString( "daysOfWeekLang" ) );
      }
      commonSetting = CommonSettingData.getInstance().getHomeList().get( 0 );
      sensors = CommonSettingData.getInstance().getSensors( commonSetting );
      permissions = CommonSettingData.getInstance().getPermission( commonSetting );

      for( String s : CommonSettingData.getInstance().getSelectedFishes( commonSetting ) )
         checkComboBox.getCheckModel().check( checkComboBox.getItems().indexOf( s ) );

      flowAquariumSetting = CommonSettingData.getInstance().getAquariumSettings( commonSetting );
      feedingTime.setValue( LocalTime.of( Integer.parseInt( flowAquariumSetting.substring( 0, 2 ) ),
            Integer.parseInt( flowAquariumSetting.substring( 2, 4 ) ) ) );
      waterExchangeTime.setValue( LocalTime.of( Integer.parseInt( flowAquariumSetting.substring( 6, 8 ) ),
            Integer.parseInt( flowAquariumSetting.substring( 8, 10 ) ) ) );
      waterExchangeDay.getSelectionModel().select( Integer.parseInt( flowAquariumSetting.substring( 13, 14 ) ) - 1 );
      airMotorStartTime.setValue( LocalTime.of( Integer.parseInt( flowAquariumSetting.substring( 14, 16 ) ),
            Integer.parseInt( flowAquariumSetting.substring( 16, 18 ) ) ) );
      airMotorRunTime.setValue( Integer.parseInt( flowAquariumSetting.substring( 20, 22 ) ) );

      if( loginUser.getUserType().equals( "PARENT" )
            || loginUser.getUserType().equals( "EBEVEYN" )
            || loginUser.getUserType().equals( "ELTERNTEIL" )
            || loginUser.getUserType().equals( "GENITORE" ) ) {
         fireButtonVisualToggle.setSelected( sensors[ 0 ].charAt( 0 ) == 'O' );
         fireButtonSoundToggle.setSelected( sensors[ 0 ].charAt( 1 ) == 'O' );
         gasSensorVisualToggle.setSelected( sensors[ 1 ].charAt( 0 ) == 'O' );
         gasSensorSoundToggle.setSelected( sensors[ 1 ].charAt( 1 ) == 'O' );
         smokeSensorVisualToggle.setSelected( sensors[ 2 ].charAt( 0 ) == 'O' );
         smokeSensorSoundToggle.setSelected( sensors[ 2 ].charAt( 1 ) == 'O' );

         permissionPaneElectricityToggle.setSelected( permissions[ 0 ].charAt( 0 ) == 'O' );
         permissionPaneGasToggle.setSelected( permissions[ 1 ].charAt( 0 ) == 'O' );
         permissionPaneWaterToggle.setSelected( permissions[ 2 ].charAt( 0 ) == 'O' );
         permissionPaneGardenLightToggle.setSelected( permissions[ 3 ].charAt( 0 ) == 'O' );
         permissionPaneAquariumToggle.setSelected( permissions[ 4 ].charAt( 0 ) == 'O' );
         permissionPaneNotificationToggle.setSelected( permissions[ 5 ].charAt( 0 ) == 'O' );
         permissionPaneSirenToggle.setSelected( permissions[ 6 ].charAt( 0 ) == 'O' );
      } else  {
         usersSettingSubPane.setVisible( false );
         menuBulkChange.setVisible( false );
         menuTimeConfigurationButton.setVisible( false );
         settingElectricityToggleButton.setDisable( permissions[ 0 ].charAt( 0 ) == 'C' );
         elecSubMenuToggleButton.setDisable( permissions[ 0 ].charAt( 0 ) == 'C' );
         gasSubMenuToggleButton.setDisable( permissions[ 1 ].charAt( 0 ) == 'C' );
         settingGasToggleButton.setDisable( permissions[ 1 ].charAt( 0 ) == 'C' );
         waterSubMenuToggleButton.setDisable( permissions[ 2 ].charAt( 0 ) == 'C' );
         gardenLightSubMenuToggleButton.setDisable( permissions[ 3 ].charAt( 0 ) == 'C' );
         aquariumSubMenuToggleButton.setDisable( permissions[ 4 ].charAt( 0 ) == 'C' );
         saveAquariumChangesButton.setDisable( permissions[ 4 ].charAt( 0 ) == 'C' );
         fireButtonVisualToggle.setDisable( permissions[ 5 ].charAt( 0 ) == 'C' );
         fireButtonSoundToggle.setDisable( permissions[ 5 ].charAt( 0 ) == 'C' );
         gasSensorVisualToggle.setDisable( permissions[ 5 ].charAt( 0 ) == 'C' );
         gasSensorSoundToggle.setDisable( permissions[ 5 ].charAt( 0 ) == 'C' );
         smokeSensorVisualToggle.setDisable( permissions[ 5 ].charAt( 0 ) == 'C' );
         smokeSensorSoundToggle.setDisable( permissions[ 5 ].charAt( 0 ) == 'C' );
         internalSirenToggle.setDisable( permissions[ 6 ].charAt( 0 ) == 'C' );
         externalSirenToggle.setDisable( permissions[ 6 ].charAt( 0 ) == 'C' );
      }

      try {
         weatherForecast = new WeatherForecast( loginUser.getLocation() );
         settingWeatherForecastLabelValue.setText( weatherForecast.getWeather() );
         settingWeatherTemperatureLabelValue.setText( weatherForecast.getTemperature() + "°C" );
         settingWeatherHumidityLabelValue.setText( weatherForecast.getHumidity() );
         settingWeatherWindLabelValue.setText( weatherForecast.getWind() );
         informationTime.setText( weatherForecast.getLocalTime() );
         settingWeatherLocationTextField.setText( loginUser.getLocation() );
         backgroundSetup( weatherForecast.getWeather() );
         menuWeatherValue.setText( weatherForecast.getWeather() + " " +
               weatherForecast.getTemperature() + "°C" );
      } catch( IOException e ) {
         settingWeatherForecastLabelValue.setText( bundle.getString( "netConnectionLang" ) );
         settingWeatherTemperatureLabelValue.setText( bundle.getString( "netConnectionLang" ) );
         settingWeatherHumidityLabelValue.setText( bundle.getString( "netConnectionLang" ) );
         settingWeatherWindLabelValue.setText( bundle.getString( "netConnectionLang" ) );
         informationTime.setText( bundle.getString( "netConnectionLang" ) );
         settingWeatherLocationTextField.setText( loginUser.getLocation() );
         backgroundSetup( "weather" );
         menuWeatherValue.setText( bundle.getString( "netConnectionLang" ) );
      }

      Thread thread = new Thread( () -> {
         while( !exit ) {
            Platform.runLater( () -> timeLabel.setText( LocalDate.now().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) +
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

   /**
    * It is a userPreferenceUpdate that is updated GUI
    * according the user that login the application.
    *
    * @param user is an User input parameter
    */
   void userPreferenceUpdate( User user ) {
      dateTimeFormatter = DateTimeFormatter.ofPattern( "dd.MM.yyyy" );
      localDate = LocalDate.parse( user.getBirthday(), dateTimeFormatter );
      birthdayDateField.setValue( localDate );
      userProfileNameField.setText( user.getName() );
      userProfileSurnameField.setText( user.getSurname() );
      userProfileAgeField.setText( user.getBirthday() );
      userProfileGenderField.setText( user.getGender() );
      userProfileUserNameField.setText( user.getUserName() );
      nameTextField.setText( user.getName() );
      surnameTextField.setText( user.getSurname() );

      if( user.getGender().equals( "MALE" )
            || user.getGender().equals( "ERKEK" )
            || user.getGender().equals( "MÄNNLICH" ) ) {
         maleRadioOption.setSelected( true );
         femaleRadioOption.setSelected( false );
         userInfoBoyImage.setVisible( true );
         userInfoGirlImage.setVisible( false );
      } else {
         femaleRadioOption.setSelected( true );
         maleRadioOption.setSelected( false );
         userInfoBoyImage.setVisible( false );
         userInfoGirlImage.setVisible( true );
      }
      userNameTextField.setText( user.getUserName() );
      changeTheme( loginUser.getPreferredTheme().toLowerCase() );

      unSelectAllLanguage();

      if( loginUser.getPreferredLanguage().equals( "ENGLISH" ) ) {
         languageSetter( "en" );
         selectEnglishOption();
      } else if( loginUser.getPreferredLanguage().equals( "TÜRKÇE" ) ) {
         languageSetter( "tr" );
         selectTurkishOption();
      } else if( loginUser.getPreferredLanguage().equals( "DEUTSCH" ) ) {
         languageSetter( "de" );
         selectGermanOption();
      } else if( loginUser.getPreferredLanguage().equals( "ITALIANO" ) ) {
         languageSetter( "it" );
         selectItalianOption();
      } else if( loginUser.getPreferredLanguage().equals( "FRANCAIS" ) ) {
         languageSetter( "fr" );
         selectFrenchOption();
      }

      if( loginUser.getSound().substring( 0,
            loginUser.getSound().length() - 3 ).equals( "false" ) ) {
         soundCheck = false;
         soundControl( false );
      } else if( loginUser.getSound().substring( 0,
            loginUser.getSound().length() - 3 ).equals( "true" ) ) {
         soundCheck = true;
         soundControl( true );
         soundModeToggle.setSelected( true );
      }

      textCheck = !loginUser.getText().equals( "false" );
      textModeToggle.setSelected( loginUser.getText().equals( "true" ) );
      volume = ( loginUser.getSound().substring( loginUser.getSound().length() - 3 ) );
      soundVolumeSlider.setValue( Double.parseDouble( volume ) );
   }

   /**
    * It is a sound method that play sounds according to user preference
    *
    * @param file  is a String input parameter that is name of the sound file
    * @param check is a boolean input parameter
    */
   void sound( String file, boolean check ) {
      if( ( check && !audioClip.getSource().substring( audioClip.getSource().indexOf( "com/SmartHomeBilkent/" )
            + 30, ( audioClip.getSource().length() - 6 ) ).equals( file ) && !audioClip.isPlaying() ) ) {
         audioClip = new AudioClip( this.getClass().getResource( "music/emergencySound.mp3" ).toString() );
         audioClip.stop();
         audioClip = new AudioClip( this.getClass().getResource( "music/" +
               bundle.getString( "pathLang" ) + file +
               bundle.getString( "mp3Lang" ) ).toString() );
         audioClip.setRate( 1 );
         audioClip.setVolume( ( ( double ) Integer.parseInt( volume ) ) / 500 );
         audioClip.play();
      }
   }

   /**
    * It is a refreshPortList that is refresh the port list for combo box
    */
   void refreshPortList() {
      SerialPort[] portNames;
      portChooser.getItems().removeAll( portChooser.getItems() );
      portNames = SerialPort.getCommPorts();

      for( SerialPort portName : portNames )
         portChooser.getItems().add( portName.getSystemPortName() );
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
      commonBorderPane.getChildren().add( rectangle );
      rectangle.setVisible( false );
   }

   /**
    * It is a updateUsersTable that is added all users to users table according to database
    */
   void updateUsersTable() {
      settingUserTableName.setCellValueFactory( param -> param.getValue().getValue().nameProperty() );
      settingUserTableSurname.setCellValueFactory( param -> param.getValue().getValue().surnameProperty() );
      settingUserTableBirthday.setCellValueFactory( param -> param.getValue().getValue().birthdayProperty() );
      settingUserTableGender.setCellValueFactory( param -> param.getValue().getValue().genderProperty() );
      settingUserTableUserName.setCellValueFactory( param -> param.getValue().getValue().userNameProperty() );
      settingUserTableTheme.setCellValueFactory( param -> param.getValue().getValue().preferredThemeProperty() );
      settingUserTableLanguage.setCellValueFactory( param -> param.getValue().getValue().preferredLanguageProperty() );
      settingUserTable.setRoot( new RecursiveTreeItem<>( Users.getInstance().getUserList(), RecursiveTreeObject::getChildren ) );
      settingUserTable.setShowRoot( false );
   }

   /**
    * It is a commonButtonsOnAction that is for common buttons( userProfileButton
    * - menuButton - settingsButton - logoutButton - helpButton)
    *
    * @param event is an ActionEvent input parameter that is source of action
    */
   @FXML
   void commonButtonsOnAction( ActionEvent event ) {
      if( event.getSource() == userProfileButton ) {
         openUserProfilePane();

      } else if( event.getSource() == menuButton ) {
         openMenuPane();

      } else if( event.getSource() == settingsButton ) {
         openSettingsPane();

      } else if( event.getSource() == logoutButton ) {
         if( isArduinoConnect )
            home.getArduino().closeConnection();
         Platform.exit();
         exit = true;
         System.exit( 0 );

      } else if( event.getSource() == helpButton ) {
         try {
            FXMLLoader load = new FXMLLoader( getClass().getResource( "view/helpPanel.fxml" ) );
            Parent root = load.load();
            Stage stage = new Stage();
            stage.setTitle( "HELP" );
            stage.setScene( new Scene( root, 600, 500 ) );
            stage.getIcons().add( new Image( Main.class.getResourceAsStream( "styleSheets/images/smartHome.png" ) ) );
            stage.setResizable( false );
            stage.show();
         } catch( Exception e ) {
            e.printStackTrace();
         }
      }
   }

   /**
    * It is a commonButtonsOnMovement that is
    * triggered when there is movement on these buttons
    *
    * @param event is a MouseEvent input parameter
    *              that is source of mouse movements
    */
   @FXML
   void commonButtonsOnMovement( MouseEvent event ) {
      if( event.getSource() == userProfileButton
            || event.getSource() == userProfileButtonActive ) {
         menuUserProfileLabel.setVisible( textCheck );
         sound( "userProfileLang", soundCheck );

      } else if( event.getSource() == menuButton
            || event.getSource() == menuButtonActive ) {
         menuMenuLabel.setVisible( textCheck );
         sound( "menuLang", soundCheck );

      } else if( event.getSource() == settingsButton
            || event.getSource() == settingsButtonActive ) {
         menuSettingLabel.setVisible( textCheck );
         sound( "settingLang", soundCheck );

      } else if( event.getSource() == logoutButton ) {
         sound( "closeAppLang", soundCheck );
      }
   }

   /**
    * It is a commonButtonsOnMovement that is
    * triggered when mouse exits from these buttons
    *
    * @param event is a MouseEvent input parameter
    *              that is source of mouse movements
    */
   @FXML
   void commonButtonsOnExit( MouseEvent event ) {
      if( event.getSource() == userProfileButton
            || event.getSource() == userProfileButtonActive ) {
         menuUserProfileLabel.setVisible( false );

      } else if( event.getSource() == menuButton
            || event.getSource() == menuButtonActive ) {
         menuMenuLabel.setVisible( false );

      } else if( event.getSource() == settingsButton
            || event.getSource() == settingsButtonActive ) {
         menuSettingLabel.setVisible( false );

      } else if( event.getSource() == doorButton ) {
         menuOpenDoorLabel.setVisible( false );
      }
   }

   /**
    * It is a openMenuPane method that open the menu
    * pane with animation in GUI
    */
   void openMenuPane() {
      menuButtonActive.setVisible( true );
      menuStackPane.setDisable( false );

      if( userProfileButtonActive.isVisible() )
         new FadeInRightBig( menuStackPane ).play();
      else if( settingsButtonActive.isVisible() )
         new FadeInLeftBig( menuStackPane ).play();

      closeUserProfilePane();
      closeSettingsPane();
   }

   /**
    * It is a closeMenuPane that closes menu pane
    * with animation in GUI
    */
   void closeMenuPane() {
      if( menuButtonActive.isVisible() ) {
         if( settingsButtonActive.isVisible() )
            new FadeOutLeftBig( menuStackPane ).play();
         else if( userProfileButtonActive.isVisible() )
            new FadeOutRightBig( menuStackPane ).play();
      }
      menuButtonActive.setVisible( false );
      menuStackPane.setDisable( true );
   }

   /**
    * It is a openSettingsPane that opens setting pane
    * with animation in GUI
    */
   void openSettingsPane() {
      settingsButtonActive.setVisible( true );
      settingAnchorPane.setDisable( false );
      openApplicationPanes();
      new FadeInRightBig( settingAnchorPane ).play();
      closeUserProfilePane();
      closeMenuPane();
   }

   /**
    * It is a closeSettingsPane that closes setting pane
    * with animation in GUI
    */
   void closeSettingsPane() {
      if( settingsButtonActive.isVisible() ) {
         new FadeOutRightBig( settingAnchorPane ).play();
      }
      settingsButtonActive.setVisible( false );
      settingAnchorPane.setDisable( true );
      closeApplicationSettingPane();
      closeAllHomeSetting();
      closeAllUsersPane();
      closeModsPane();
   }

   /**
    * It is a openUserProfilePane that opens user profile
    * pane with animation in GUI
    */
   void openUserProfilePane() {
      userProfileButtonActive.setVisible( true );
      userProfileStackPane.setDisable( false );
      new FadeInLeftBig( userProfileStackPane ).play();
      closeSettingsPane();
      closeMenuPane();
   }

   /**
    * It is a closeUserProfilePane that closes user profile
    * pane with animation in GUI
    */
   void closeUserProfilePane() {
      if( userProfileButtonActive.isVisible() ) {
         new FadeOutLeftBig( userProfileStackPane ).play();
      }
      userProfileButtonActive.setVisible( false );
      toGoBackUserProfile();
      userProfileStackPane.setDisable( true );
   }

   /*
   2)menu pane methods
    */

   /**
    * It is a menuButtonsOnMovement that is run when there is
    * movement on one of these buttons
    *
    * @param event is a MouseEvent input parameter that is
    *              source of the movement
    */
   @FXML
   void menuButtonsOnMovement( MouseEvent event ) {
      if( event.getSource() == tempImage ) {
         sound( "homeTemperatureLang", soundCheck );

      } else if( event.getSource() == weatherButton ) {
         sound( "weatherLang", soundCheck );

      } else if( event.getSource() == menuConnectionButton ) {
         sound( "connectionLang", soundCheck );

      } else if( event.getSource() == menuElecButton ) {
         sound( "elecLang", soundCheck );

      } else if( event.getSource() == menuGasButton ) {
         sound( "gasLang", soundCheck );

      } else if( event.getSource() == menuAquariumButton ) {
         sound( "aquiarumLang", soundCheck );

      } else if( event.getSource() == menuGreenHouseButton ) {
         sound( "greenHouseLang", soundCheck );

      } else if( event.getSource() == elecSubMenuButtonPassive
            || event.getSource() == elecSubMenuButtonActive ) {
         sound( "elecSettingsLang", soundCheck );

      } else if( event.getSource() == gasSubMenuButtonPassive
            || event.getSource() == gasSubMenuButtonActive ) {
         sound( "gasSettingsLang", soundCheck );

      } else if( event.getSource() == aquariumSubMenuButtonPassive
            || event.getSource() == aquariumSubMenuButtonActive ) {
         sound( "aquSettingsLang", soundCheck );

      } else if( event.getSource() == greenhouseSubMenuButtonPassive
            || event.getSource() == greenhouseSubMenuButtonActive ) {
         sound( "greenHouseSettingsLang", soundCheck );

      } else if( event.getSource() == menuWaterButton ) {
         sound( "waterLang", soundCheck );

      } else if( event.getSource() == menuGardenLightButton ) {
         sound( "gardenLightLang", soundCheck );

      } else if( event.getSource() == menuBulkChange ) {
         sound( "bulkChangesLang", soundCheck );

      } else if( event.getSource() == menuTimeConfigurationButton ) {
         sound( "timeConfigurationLang", soundCheck );

      } else if( event.getSource() == doorButton ) {
         sound( "doorLang", soundCheck );
         menuOpenDoorLabel.setVisible( textCheck );
      }
   }

   /**
    * It is goToSettingFromMenuView method that run when
    * clicked one of these buttons
    * and open this button's setting pane in GUI with animation
    *
    * @param event is an ActionEvent input parameter that is source of the action
    */
   @FXML
   void goToSettingFromMenuView( ActionEvent event ) {
      if( event.getSource() == elecSubMenuButtonPassive
            || event.getSource() == elecSubMenuButtonActive ) {
         openSettingsPane();
         closeApplicationSettingPane();
         openHomeSettingPane();
         settingElecSettingPane.setDisable( false );
         homeSettingElecButtonActive.setVisible( true );

      } else if( event.getSource() == gasSubMenuButtonPassive
            || event.getSource() == gasSubMenuButtonActive ) {
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingGasSettingPane.setDisable( false );
         new FadeInUp( settingGasSettingPane ).play();
         homeSettingGasButtonActive.setVisible( true );

      } else if( event.getSource() == aquariumSubMenuButtonPassive
            || event.getSource() == aquariumSubMenuButtonActive ) {
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingAquSettingPane.setDisable( false );
         new FadeInUp( settingAquSettingPane ).play();
         homeSettingAquButtonActive.setVisible( true );

      } else if( event.getSource() == greenhouseSubMenuButtonPassive
            || event.getSource() == aquariumSubMenuButtonActive ) {
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingGreenHouseSettingPane.setDisable( false );
         new FadeInUp( settingGreenHouseSettingPane ).play();
         homeSettingGreenHouseButtonActive.setVisible( true );

      } else if( event.getSource() == weatherButton ) {
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingWeatherSettingPane.setDisable( false );
         new FadeInUp( settingWeatherSettingPane ).play();
         homeSettingWeatherButtonActive.setVisible( true );

      } else if( event.getSource() == menuConnectionButton ) {
         openSettingsPane();
         closeAllApplicationSettingSubPanes();
         openSmartHomeConnectionSetting();
         connectionSubLabel.setVisible( textCheck );
      }
   }

   /**
    * It is a menuPaneButtonsOnAction that is triggered by the menu buttons
    * and It open menu view pane of this button on GUI with animation.Also
    * some ofthe these buttons can send message to embedded system thanks
    * to serial port when they are triggered
    *
    * @param event is an ActionEvent input parameter that is source of the action
    */
   @FXML
   void menuPaneButtonsOnAction( ActionEvent event ) {
      if( event.getSource() == menuElecButton ) {
         closeAllMenuPane();
         menuElecPane.setVisible( true );
         new FadeIn( menuElecPane ).play();

      } else if( event.getSource() == menuGasButton ) {
         closeAllMenuPane();
         menuGasPane.setVisible( true );

         new FadeIn( menuGasPane ).play();
      } else if( event.getSource() == menuAquariumButton ) {
         closeAllMenuPane();
         menuAquariumPane.setVisible( true );
         new FadeIn( menuAquariumPane ).play();

      } else if( event.getSource() == menuGreenHouseButton ) {
         closeAllMenuPane();
         menuGreenHousePane.setVisible( true );
         new FadeIn( menuGreenHousePane ).play();

      } else if( event.getSource() == menuWaterButton ) {
         closeAllMenuPane();
         menuWaterPane.setVisible( true );
         new FadeIn( menuWaterPane ).play();

      } else if( event.getSource() == menuGardenLightButton ) {
         closeAllMenuPane();
         menuGardenLightPane.setVisible( true );
         new FadeIn( menuGardenLightPane ).play();

      } else if( event.getSource() == doorButton ) {
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

      } else if( event.getSource() == menuBulkChange ) {
         closeAllMenuPane();
         menuBulkChangePane.setVisible( true );
         new FadeIn( menuBulkChangePane ).play();

      } else if( event.getSource() == menuTimeConfigurationButton ) {
         closeAllMenuPane();
         menuTimeConfigurationPane.setVisible( true );
         new FadeIn( menuTimeConfigurationPane ).play();

      } else if( event.getSource() == bulkChangesSaveButton ) {
         StringBuilder message;
         message = new StringBuilder();
         message.append( "Smart_App#" );

         if( sirenRadioButton.isSelected() )
            message.append( "B1" );
         else
            message.append( "B0" );

         if( electricityRadioButton.isSelected() )
            message.append( "E0" );
         else
            message.append( "E1" );

         if( gasRadioButton.isSelected() )
            message.append( "G0" );
         else
            message.append( "G1" );

         if( incomingWaterRadioButton.isSelected() )
            message.append( "I1" );
         else
            message.append( "I0" );

         if( outgoingWaterRadioButton.isSelected() )
            message.append( "U1" );
         else
            message.append( "U0" );

         if( aquariumRadioButton.isSelected() )
            message.append( "A0" );
         else
            message.append( "A1" );

         if( doorRadioButton.isSelected() )
            message.append( "D1" );
         else
            message.append( "D0" );

         if( waterRadioButton.isSelected() )
            message.append( "W0" );
         else
            message.append( "W1" );

         if( sirenRadioButton.isSelected() )
            message.append( "X1" );
         else
            message.append( "X0" );

         if( feedRadioButton.isSelected() )
            message.append( "F1" );
         else
            message.append( "F0" );

         if( gardenLightRadioButton.isSelected() )
            message.append( "R1" );
         else
            message.append( "R0" );

         message.append( "S1N1:" );
         if( isArduinoConnect )
            home.adjustCollective( message.toString() );

         openGardenLight( gardenLightRadioButton.isSelected() );
         openWater( waterRadioButton.isSelected() );
         openAquarium( aquariumRadioButton.isSelected() );
         openGas( gasRadioButton.isSelected() );
         openElectricity( electricityRadioButton.isSelected() );

         if( feedRadioButton.isSelected() )
            new Thread( () -> {
               for( int k = 0; k < 20; k++ ) {
                  final int j = k;
                  Platform.runLater( () -> {
                     menuAquariumIndicator.setVisible( true );
                     feedRadioButton.setSelected( true );
                     if( j == 19 ) {
                        menuAquariumIndicator.setVisible( false );
                        feedRadioButton.setSelected( false );
                     }
                  } );
                  try {
                     Thread.sleep( 100 );
                  } catch( InterruptedException e ) {
                     e.printStackTrace();
                  }
               }
            } ).start();

      } else if( event.getSource() == dateTimeSaveButton ) {
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
      } else if( event.getSource() == incomingWaterRadioButton ) {
         if( incomingWaterRadioButton.isSelected() )
            outgoingWaterRadioButton.setSelected( false );

      } else if( event.getSource() == outgoingWaterRadioButton ) {
         if( outgoingWaterRadioButton.isSelected() )
            incomingWaterRadioButton.setSelected( false );

      } else if( event.getSource() == menuAquariumFeedButton ) {
         if( isArduinoConnect )
            home.getAquarium().feedingOpen( true );

         new Thread( () -> {
            for( int k = 0; k < 20; k++ ) {
               final int j = k;
               Platform.runLater( () -> {
                  menuAquariumIndicator.setVisible( true );
                  feedRadioButton.setSelected( true );
                  if( j == 19 ) {
                     menuAquariumIndicator.setVisible( false );
                     feedRadioButton.setSelected( false );
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
   }

   /**
    * It is a closeAllMenuPane that closes all menu panes
    */
   void closeAllMenuPane() {
      menuElecPane.setVisible( false );
      menuGasPane.setVisible( false );
      menuAquariumPane.setVisible( false );
      menuGreenHousePane.setVisible( false );
      menuWaterPane.setVisible( false );
      menuGardenLightPane.setVisible( false );
      menuBulkChangePane.setVisible( false );
      menuTimeConfigurationPane.setVisible( false );
      timeConfigurationPaneWarningLabel.setVisible( false );
   }

   /**
    * It is a menuPaneToggleButtonOnAction that is for controlling home features
    * to open or close. When one of the these button is triggered, send message
    * to open/close this feature according to status of toggle buttons
    *
    * @param event is an ActionEvent input parameter that is source of the action
    */
   @FXML
   void menuPaneToggleButtonOnAction( ActionEvent event ) {
      if( event.getSource() == elecSubMenuToggleButton ) {
         openElectricity( elecSubMenuToggleButton.isSelected() );
         if( isArduinoConnect )
            home.getElectricity().open( elecSubMenuToggleButton.isSelected() );

         if( elecSubMenuToggleButton.isSelected() ) {
            elecSubPaneOpenValueLabelPassive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
            elecSubPaneOpenValueLabelActive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
         }
         else {
            elecSubPaneCloseValueLabelPassive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
            elecSubPaneCloseValueLabelActive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
         }

      } else if( event.getSource() == gasSubMenuToggleButton ) {
         openGas( gasSubMenuToggleButton.isSelected() );
         if( isArduinoConnect )
            home.getGas().open( gasSubMenuToggleButton.isSelected() );

      } else if( event.getSource() == aquariumSubMenuToggleButton ) {
         openAquarium( aquariumSubMenuToggleButton.isSelected() );
         if( isArduinoConnect )
            home.getAquarium().open( aquariumSubMenuToggleButton.isSelected() );

      } else if( event.getSource() == waterSubMenuToggleButton ) {
         openWater( waterSubMenuToggleButton.isSelected() );
         if( isArduinoConnect )
            home.getWater().open( waterSubMenuToggleButton.isSelected() );

      } else if( event.getSource() == gardenLightSubMenuToggleButton ) {
         openGardenLight( gardenLightSubMenuToggleButton.isSelected() );
         if( isArduinoConnect )
            home.getGardenLight().open( gardenLightSubMenuToggleButton.isSelected() );
      }
   }

   /**
    * It is a openElectricity class that regulate GUI and send message to
    * embedded system according to boolean input parameter
    *
    * @param control is a boolean input parameter that controls the whether It should open or close
    */
   public void openElectricity( boolean control ) {
      elecSubPaneLabelActive.setVisible( control );
      elecSubMenuButtonActive.setVisible( control );
      elecSubPaneOpenLabelActive.setVisible( control );
      elecSubPaneOpenValueLabelActive.setVisible( control );
      elecSubPaneCloseLabelActive.setVisible( control );
      elecSubPaneCloseValueLabelActive.setVisible( control );
      menuElecProgress.setVisible( control );
      gardenLightSubMenuToggleButton.setDisable( !control );
      elecSubMenuToggleButton.setSelected( control );
      electricityRadioButton.setSelected( control );
      settingElectricityToggleButton.setSelected( control );

      if( !control ) {
         openGardenLight( false );
         gardenLightSubMenuToggleButton.setSelected( false );
         openGardenLight( false );
      }
   }

   /**
    * It is a openGas class that regulate GUI and send message to
    * embedded system according to boolean input parameter
    *
    * @param control is a boolean input parameter that
    *                controls the whether It should open or close
    */
   public void openGas( boolean control ) {
      gasSubPaneLabelActive.setVisible( control );
      gasSubMenuButtonActive.setVisible( control );
      menuGasProgress.setVisible( control );
      gasSubMenuToggleButton.setSelected( control );
      gasRadioButton.setSelected( control );
      settingGasToggleButton.setSelected( control );
   }

   /**
    * It is a openAquarium class that regulate GUI and send message to
    * embedded system according to boolean input parameter
    *
    * @param control is a boolean input parameter that
    *                controls the whether It should open or close
    */
   public void openAquarium( boolean control ) {
      aquariumSubPaneLabelActive.setVisible( control );
      aquariumSubMenuButtonActive.setVisible( control );
      menuAquariumProgress.setVisible( control );
      aquariumSubMenuToggleButton.setSelected( control );
      aquariumRadioButton.setSelected( control );
   }

   /**
    * It is a openWater class that regulate GUI and send message to
    * embedded system according to boolean input parameter
    *
    * @param control is a boolean input parameter that
    *                controls the whether It should open or close
    */
   public void openWater( boolean control ) {
      waterSubPaneLabelActive.setVisible( control );
      menuWaterProgress.setVisible( control );
      waterSubMenuToggleButton.setSelected( control );
      waterRadioButton.setSelected( control );
   }

   /**
    * It is a openGardenLight class that regulate GUI and send message to
    * embedded system according to boolean input parameter
    *
    * @param control is a boolean input parameter that
    *                controls the whether It should open or close
    */
   public void openGardenLight( boolean control ) {
      gardenLightSubPaneLabelActive.setVisible( control );
      menuGardenLightProgress.setVisible( control );
      gardenLightSubMenuToggleButton.setSelected( control );
      gardenLightRadioButton.setSelected( control );
   }

   /*
   3)user profile pane methods
    */

   /**
    * userProfileButtonsOnAction method that is onAction of all user profile
    * buttons that opens user info change pane , user changer pane or
    * saves user info
    *
    * @param event is a ActionEvent input parameter that source of the action
    * @throws SQLException is an Exception that is due to database connection
    *          and communication
    */
   @FXML
   void userProfileButtonsOnAction( ActionEvent event ) throws SQLException {
      if( event.getSource() == changeUserNormalInfoButton ) {
         userProfilePane.setEffect( new BoxBlur( 10, 3, 3 ) );
         new ZoomIn( changeUserNormalInfoPane ).play();
         changeUserNormalInfoPane.setDisable( false );
         userProfilePane.setDisable( true );

      } else if( event.getSource() == changeUserPrivateInfoButton ) {
         userProfilePane.setEffect( new BoxBlur( 10, 3, 3 ) );
         new ZoomIn( changeUserPrivateInfoPane ).play();
         changeUserPrivateInfoPane.setDisable( false );
         userProfilePane.setDisable( true );

      } else if( event.getSource() == userChangerButton ) {
         try {
            Users.getInstance().getUserList().get( Users.getInstance().getUserList().indexOf( loginUser ) ).setEnter( "false" );
            FXMLLoader load = new FXMLLoader( getClass().getResource( "view/loginPanel.fxml" ) );
            Parent root = load.load();
            Stage stage = new Stage();
            stage.setTitle( "SMART HOME" );
            stage.setScene( new Scene( root, 400, 400 ) );
            stage.getIcons().add( new Image( Main.class.getResourceAsStream( "styleSheets/images/smartHome.png" ) ) );
            stage.setResizable( false );
            stage.show();
            commonBorderPane.getScene().getWindow().hide();
            if( isArduinoConnect )
               arduino.closeConnection();
         } catch( Exception e ) {
            e.printStackTrace();
         }
      } else if( event.getSource() == saveUserNormalInfo ) {
         String gender;

         if( nameTextField.getText().isEmpty()
               || surnameTextField.getText().isEmpty()
               || birthdayDateField.getValue() == null ) {
            normalInfoWarning.setVisible( true );
         } else {

            if( maleRadioOption.isSelected() ) {
               gender = "MALE";
            } else {
               gender = "FEMALE";
            }

            Users.getInstance().updateUserNormalInfo( loginUser,
                  nameTextField.getText(),
                  surnameTextField.getText(),
                  birthdayDateField.getValue().format( DateTimeFormatter.ofPattern( "dd.MM.yyyy" ) ), gender );
            userPreferenceUpdate( loginUser );
            updateUsersTable();
            toGoBackUserProfile();
         }
      } else if( event.getSource() == saveUserPrivateInfo ) {

         if( userNameTextField.getText().length() > 0
               && currentPasswordField.getText().length() > 0
               && newPasswordTextField.getText().length() > 0
               && verifyNewPasswordField.getText().length() > 0 ) {

            if( !newPasswordTextField.getText().equals( verifyNewPasswordField.getText() ) ) {
               privateInfoWarning.setVisible( true );
               privateInfoWarning.setText( bundle.getString( "passwordConflictLang" ) );

            } else if( !currentPasswordField.getText().equals( loginUser.getPassword() ) ) {
               privateInfoWarning.setVisible( true );
               privateInfoWarning.setText( bundle.getString( "passwordMistakeLang" ) );

            } else {
               privateInfoWarning.setVisible( false );
               Users.getInstance().updatePrivateInfo( loginUser, userNameTextField.getText(), newPasswordTextField.getText() );
               userPreferenceUpdate( loginUser );
               toGoBackUserProfile();
               updateUsersTable();
            }
         } else {
            privateInfoWarning.setVisible( true );
            privateInfoWarning.setText( bundle.getString( "normalInfoWarningLang" ) );
         }
      } else if( event.getSource() == backToUserProfileFromNormalInfo ) {
         toGoBackUserProfile();
         normalInfoWarning.setVisible( false );
         birthdayDateField.setValue( localDate );

      } else if( event.getSource() == backToUserProfileFromPrivateInfo ) {
         toGoBackUserProfile();
         privateInfoWarning.setVisible( false );

      } else if( event.getSource() == maleRadioOption ) {
         femaleRadioOption.setSelected( !maleRadioOption.isSelected() );

      } else if( event.getSource() == femaleRadioOption ) {
         maleRadioOption.setSelected( !femaleRadioOption.isSelected() );
      }
   }

   /**
    * controlAvailability control that the new user name available
    * or not and according to this, it regulates GUI
    *
    * @param event is a KeyEvent input parameter that source of the key released
    */
   @FXML
   void controlAvailability( KeyEvent event ) {
      if( event.getSource() == userNameTextField ) {
         availabilityImage.setVisible( true );
         if( userNameTextField.getText().equals( loginUser.getUserName() ) ) {
            privateInfoWarning.setVisible( false );
            saveUserPrivateInfo.setDisable( false );
            availabilityImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/available.png" ) ) );

         } else if( !Users.getInstance().isUserNameAvailable( userNameTextField.getText() ) ) {
            availabilityImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/notAvailable.png" ) ) );
            privateInfoWarning.setVisible( true );
            privateInfoWarning.setText( bundle.getString( "userNameSimilarityLang" ) );
            saveUserPrivateInfo.setDisable( true );

         } else if( userNameTextField.getText().equals( "" ) ) {
            availabilityImage.setVisible( false );
            privateInfoWarning.setVisible( false );

         } else {
            privateInfoWarning.setVisible( false );
            saveUserPrivateInfo.setDisable( false );
            availabilityImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/available.png" ) ) );
         }
      } else if( event.getSource() == createUserUserNameTextField ) {
         addUserImageView.setVisible( true );
         if( !Users.getInstance().isUserNameAvailable( createUserUserNameTextField.getText() ) ) {
            addUserImageView.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/notAvailable.png" ) ) );
            createUserWarningLabel.setVisible( true );
            createUserWarningLabel.setText( bundle.getString( "userNameSimilarityLang" ) );
            createUserConfirmButton.setDisable( true );

         } else if( createUserUserNameTextField.getText().equals( "" ) ) {
            addUserImageView.setVisible( false );
            createUserWarningLabel.setVisible( false );
            createUserConfirmButton.setDisable( false );

         } else {
            createUserWarningLabel.setVisible( false );
            createUserConfirmButton.setDisable( false );
            addUserImageView.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/available.png" ) ) );
         }
      }
   }


   /**
    * userProfileButtonsOnMovement method regulate GUI and sound according
    * to the source of the movement
    *
    * @param event is a MouseEvent input parameter that is source of the movement
    */
   @FXML
   void userProfileButtonsOnMovement( MouseEvent event ) {
      if( event.getSource() == userChangerButton ) {
         userChangerInfoLabel.setVisible( textCheck );
         sound( "userChangerLang", soundCheck );

      } else if( event.getSource() == changeUserNormalInfoButton ) {
         changeUserInfoLabel.setVisible( textCheck );
         sound( "changeUserInfoLang", soundCheck );

      } else if( event.getSource() == changeUserPrivateInfoButton ) {
         changeUserPrivateInfoLabel.setVisible( textCheck );
         sound( "changeUserInfoLang", soundCheck );

      } else if( event.getSource() == saveUserNormalInfo
            || event.getSource() == saveUserPrivateInfo ) {
         sound( "saveChangesLang", soundCheck );

      } else if( event.getSource() == backToUserProfileFromNormalInfo
            || event.getSource() == backToUserProfileFromPrivateInfo ) {
         sound( "goBackLang", soundCheck );
      }
   }

   /**
    * userProfileButtonsOnExit method regulate GUI and sound according
    * to the source of the exit
    *
    * @param event is a MouseEvent input parameter that is source of the exit
    */
   @FXML
   void userProfileButtonsOnExit( MouseEvent event ) {
      if( event.getSource() == userChangerButton )
         userChangerInfoLabel.setVisible( false );

      else if( event.getSource() == changeUserNormalInfoButton )
         changeUserInfoLabel.setVisible( false );

      else if( event.getSource() == changeUserPrivateInfoButton )
         changeUserPrivateInfoLabel.setVisible( false );
   }

   /**
    * toGoBackUserProfile method that help to go back from set user information
    * panes with animation and blur
    */
   public void toGoBackUserProfile() {
      userProfilePane.setEffect( new BoxBlur( 0, 0, 0 ) );
      userProfilePane.setDisable( false );
      if( !changeUserNormalInfoPane.isDisable() )
         new ZoomOut( changeUserNormalInfoPane ).play();

      if( !changeUserPrivateInfoPane.isDisable() )
         new ZoomOut( changeUserPrivateInfoPane ).play();
      changeUserNormalInfoPane.setDisable( true );
      changeUserPrivateInfoPane.setDisable( true );
      userNameTextField.setText( loginUser.getUserName() );
      newPasswordTextField.setText( "" );
      verifyNewPasswordField.setText( "" );
      currentPasswordField.setText( "" );
      availabilityImage.setVisible( false );
   }


   /*
   4)setting pane methods
    */

   /**
    * settingsFacilitiesButtonsOnAction method control which pane should open
    * according to source of event
    *
    * @param event is an ActionEvent input parameter that source of action
    */
   @FXML
   void settingsFacilitiesButtonsOnAction( ActionEvent event ) {
      if( event.getSource() == applicationSettingButton )
         openApplicationPanes();

      else if( event.getSource() == settingsUsersSettingButton )
         openUsersPane();

      else if( event.getSource() == modsSettingButton )
         openModsPane();

      else if( event.getSource() == homeSettingButton ) {
         openHomeSettingPane();
         settingElecSettingPane.setVisible( true );
         homeSettingElecButtonActive.setVisible( true );
      }
   }


   //all settings buttons ( app settings(theme-language-emergency-notification-connection), users settings( add-remove user), home settings())

   /**
    * settingsFacilitiesButtonsOnAction method regulates GUI according
    * to source of event
    *
    * @param event is a MouseEvent input parameter that is source
    *              of the mouse movement
    */
   @FXML
   void settingsFacilitiesButtonsMov( MouseEvent event ) {
      if( event.getSource() == applicationSettingButton
            || event.getSource() == applicationSettingButtonActive ) {
         applicationSettingButtonLabel.setVisible( textCheck );
         sound( "applicationSettingLang", soundCheck );

      } else if( event.getSource() == settingsUsersSettingButton
            || event.getSource() == settingsUsersSettingButtonActive ) {
         usersSettingButtonLabel.setVisible( textCheck );
         sound( "usersSettingLang", soundCheck );

      } else if( event.getSource() == modsSettingButton
            || event.getSource() == modsSettingButtonActive ) {
         modsSettingButtonLabel.setVisible( textCheck );
         sound( "modsSettingLang", soundCheck );

      } else if( event.getSource() == homeSettingButton
            || event.getSource() == homeSettingButtonActive ) {
         homeSettingButtonLabel.setVisible( textCheck );
         sound( "homeLang", soundCheck );
      }
   }

   /**
    * settingsFacilitiesButtonsMovEx method regulates GUI according
    * to source of event
    *
    * @param event is a MouseEvent input parameter that is source
    *              of the mouse exit
    */
   @FXML
   void settingsFacilitiesButtonsMovEx( MouseEvent event ) {
      if( event.getSource() == applicationSettingButton
            || event.getSource() == applicationSettingButtonActive )
         applicationSettingButtonLabel.setVisible( false );

      else if( event.getSource() == settingsUsersSettingButton
            || event.getSource() == settingsUsersSettingButtonActive )
         usersSettingButtonLabel.setVisible( false );

      else if( event.getSource() == modsSettingButton
            || event.getSource() == modsSettingButtonActive )
         modsSettingButtonLabel.setVisible( false );

      else if( event.getSource() == homeSettingButton
            || event.getSource() == homeSettingButtonActive )
         homeSettingButtonLabel.setVisible( false );
   }

   /*
   4.1)application setting pane methods
    */

   /**
    * applicationSettingsButtonsOnAction method that controls the all
    * application setting panes' buttons
    *
    * @param event is an ActionEvent input parameter
    * @throws SQLException is an Exception that is due to database
    *         connection or communication failure
    */
   @FXML
   void applicationSettingsButtonsOnAction( ActionEvent event ) throws SQLException {
      if( event.getSource() == themeSettingButton ) {
         closeAllApplicationSettingSubPanes();
         openThemeSetting();

      } else if( event.getSource() == languageSettingButton ) {
         closeAllApplicationSettingSubPanes();
         openLanguageSetting();

      } else if( event.getSource() == emergencySettingButton ) {
         closeAllApplicationSettingSubPanes();
         openEmergencySetting();

      } else if( event.getSource() == phoneNotificationSettingButton ) {
         closeAllApplicationSettingSubPanes();
         openNotificationSetting();

      } else if( event.getSource() == smartHomeConnectionSettingButton ) {
         closeAllApplicationSettingSubPanes();
         openSmartHomeConnectionSetting();

      } else if( event.getSource() == portConnectionButton ) {
         arduino = new Arduino( portChooser.getValue(), 9600 );

         if( arduino.openConnection() ) {
            isArduinoConnect = true;
            portConnectionButton.setDisable( true );
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
                           openElectricity( false );
                           openGas( false );

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
                           openElectricity( false );
                           openGas( false );


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
                           openElectricity( false );
                           openGas( false );


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
                           openElectricity( false );
                           openGas( false );


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
                           openElectricity( false );
                           openGas( false );


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
                           openElectricity( false );
                           openGas( false );


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
                           openElectricity( false );
                           openGas( false );

                        }
                     }
                  }
               }
            } );
         } else {
            portChooser.setValue( "" );
         }

      } else if( event.getSource() == externalSirenToggle ) {
         if( isArduinoConnect )
            home.getSiren().open( externalSirenToggle.isSelected() );

      } else if( event.getSource() == internalSirenToggle ) {
         if( isArduinoConnect )
            home.getSiren().buzzerOpen( internalSirenToggle.isSelected() );

      } else if( event.getSource() == fireButtonVisualToggle ) {
         if( fireButtonVisualToggle.isSelected() )
            sensors[ 0 ] = "O" + sensors[ 0 ].charAt( 1 );
         else
            sensors[ 0 ] = "C" + sensors[ 0 ].charAt( 1 );
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      } else if( event.getSource() == gasSensorVisualToggle ) {
         if( gasSensorVisualToggle.isSelected() )
            sensors[ 1 ] = "O" + sensors[ 1 ].charAt( 1 );
         else
            sensors[ 1 ] = "C" + sensors[ 1 ].charAt( 1 );
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      } else if( event.getSource() == smokeSensorVisualToggle ) {
         if( smokeSensorVisualToggle.isSelected() )
            sensors[ 2 ] = "O" + sensors[ 2 ].charAt( 1 );
         else
            sensors[ 2 ] = "C" + sensors[ 2 ].charAt( 1 );
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      } else if( event.getSource() == fireButtonSoundToggle ) {
         if( fireButtonSoundToggle.isSelected() )
            sensors[ 0 ] = sensors[ 0 ].charAt( 0 ) + "O";
         else
            sensors[ 0 ] = sensors[ 0 ].charAt( 0 ) + "C";
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      } else if( event.getSource() == gasSensorSoundToggle ) {
         if( gasSensorSoundToggle.isSelected() )
            sensors[ 1 ] = sensors[ 1 ].charAt( 0 ) + "O";
         else
            sensors[ 1 ] = sensors[ 1 ].charAt( 0 ) + "C";
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );

      } else if( event.getSource() == smokeSensorSoundToggle ) {
         if( smokeSensorSoundToggle.isSelected() )
            sensors[ 2 ] = sensors[ 2 ].charAt( 0 ) + "O";
         else
            sensors[ 2 ] = sensors[ 2 ].charAt( 0 ) + "C";
         CommonSettingData.getInstance().updateSensors( commonSetting, sensors );
      }
   }

   /**
    * applicationSettingsButtonsOnMovement method that controls all movements
    * about application panes' buttons and regulate GUI according to
    * these movements
    *
    * @param event is a MouseEvent input parameter that is source of the mouse movement
    */
   @FXML
   void applicationSettingsButtonsOnMovement( MouseEvent event ) {
      if( event.getSource() == themeSettingButton
            || event.getSource() == themeSettingButtonActive ) {
         themeSubLabel.setVisible( textCheck );
         sound( "themeLang", soundCheck );
      } else if( event.getSource() == languageSettingButton
            || event.getSource() == languageSettingButtonActive ) {
         languageSubLabel.setVisible( textCheck );
         sound( "languageLang", soundCheck );
      } else if( event.getSource() == emergencySettingButton
            || event.getSource() == emergencySettingButtonActive ) {
         emergencySubLabel.setVisible( textCheck );
         sound( "emergencyLang", soundCheck );
      } else if( event.getSource() == phoneNotificationSettingButton
            || event.getSource() == notificationSettingButtonActive ) {
         notificationSubLabel.setVisible( textCheck );
         sound( "notificationLang", soundCheck );
      } else if( event.getSource() == smartHomeConnectionSettingButton
            || event.getSource() == smartHomeConnectionSettingButtonActive ) {
         connectionSubLabel.setVisible( textCheck );
         sound( "connectionLang", soundCheck );
      } else if( event.getSource() == homeSettingElecButton
            || event.getSource() == homeSettingElecButtonActive ) {
         sound( "elecSettingsLang", soundCheck );
         homeSubPaneELecLabel.setVisible( textCheck );
      } else if( event.getSource() == homeSettingGasButton
            || event.getSource() == homeSettingGasButtonActive ) {
         sound( "gasSettingsLang", soundCheck );
         homeSubPaneGasLabel.setVisible( textCheck );
      } else if( event.getSource() == homeSettingAquButton
            || event.getSource() == homeSettingAquButtonActive ) {
         sound( "aquSettingsLang", soundCheck );
         homeSubPaneAquLabel.setVisible( textCheck );
      } else if( event.getSource() == homeSettingGreenHouseButton
            || event.getSource() == homeSettingGreenHouseButtonActive ) {
         sound( "greenHouseSettingsLang", soundCheck );
         homeSubPaneGreenHouseLabel.setVisible( textCheck );
      } else if( event.getSource() == homeSettingWeatherButton
            || event.getSource() == homeSettingWeatherButtonActive ) {
         sound( "weatherForecastLang", soundCheck );
         homeSubPaneWeatherLabel.setVisible( textCheck );
      }
   }

   /**
    * applicationSettingsButtonsOnExit method that controls all exits
    * about application panes' buttons and regulate GUI according to
    * these exits
    *
    * @param event is a MouseEvent input parameter that is source
    *              of the exit movement
    */
   @FXML
   void applicationSettingsButtonsOnExit( MouseEvent event ) {
      if( event.getSource() == themeSettingButton )
         themeSubLabel.setVisible( false );
      else if( event.getSource() == languageSettingButton )
         languageSubLabel.setVisible( false );
      else if( event.getSource() == emergencySettingButton )
         emergencySubLabel.setVisible( false );
      else if( event.getSource() == phoneNotificationSettingButton )
         notificationSubLabel.setVisible( false );
      else if( event.getSource() == smartHomeConnectionSettingButton )
         connectionSubLabel.setVisible( false );
      else if( event.getSource() == homeSettingElecButton
            || event.getSource() == homeSettingElecButtonActive )
         homeSubPaneELecLabel.setVisible( false );
      else if( event.getSource() == homeSettingGasButton
            || event.getSource() == homeSettingGasButtonActive )
         homeSubPaneGasLabel.setVisible( false );
      else if( event.getSource() == homeSettingAquButton
            || event.getSource() == homeSettingAquButtonActive )
         homeSubPaneAquLabel.setVisible( false );
      else if( event.getSource() == homeSettingGreenHouseButton
            || event.getSource() == homeSettingGreenHouseButtonActive )
         homeSubPaneGreenHouseLabel.setVisible( false );
      else if( event.getSource() == homeSettingWeatherButton
            || event.getSource() == homeSettingWeatherButtonActive )
         homeSubPaneWeatherLabel.setVisible( false );
   }

   /**
    * openThemeSetting method that opens theme setting pane
    */
   void openThemeSetting() {
      themeSettingButtonActive.setVisible( true );
      settingThemePane.setDisable( false );
      new FadeInUp( settingThemePane ).play();
   }

   /**
    * openLanguageSetting method that opens language setting pane
    */
   void openLanguageSetting() {
      languageSettingButtonActive.setVisible( true );
      settingLanguagePane.setDisable( false );
      new FadeInUp( settingLanguagePane ).play();
   }

   /**
    * openEmergencySetting method that opens emergency setting pane
    */
   void openEmergencySetting() {
      emergencySettingButtonActive.setVisible( true );
      settingEmergencyPane.setDisable( false );
      new FadeInUp( settingEmergencyPane ).play();
   }

   /**
    * openNotificationSetting method that opens notification setting pane
    */
   void openNotificationSetting() {
      notificationSettingButtonActive.setVisible( true );
      settingNotificationPane.setDisable( false );
      new FadeInUp( settingNotificationPane ).play();
   }

   /**
    * openSmartHomeConnectionSetting method that opens connection setting pane
    */
   void openSmartHomeConnectionSetting() {
      smartHomeConnectionSettingButtonActive.setVisible( true );
      settingConnectionPane.setDisable( false );
      new FadeInUp( settingConnectionPane ).play();
      refreshPortList();
   }

   /**
    * closeAllApplicationSettingSubPanes method that closes all
    * application setting panes with animation
    */
   void closeAllApplicationSettingSubPanes() {
      if( !settingThemePane.isDisable() ) {
         themeSettingButtonActive.setVisible( false );
         new FadeOut( settingThemePane ).play();
         settingThemePane.setDisable( true );
      }
      if( !settingLanguagePane.isDisable() ) {
         languageSettingButtonActive.setVisible( false );
         new FadeOut( settingLanguagePane ).play();
         settingLanguagePane.setDisable( true );
      }
      if( !settingEmergencyPane.isDisable() ) {
         emergencySettingButtonActive.setVisible( false );
         new FadeOut( settingEmergencyPane ).play();
         settingEmergencyPane.setDisable( true );
      }
      if( !settingNotificationPane.isDisable() ) {
         notificationSettingButtonActive.setVisible( false );
         new FadeOut( settingNotificationPane ).play();
         settingNotificationPane.setDisable( true );
      }
      if( !settingConnectionPane.isDisable() ) {
         smartHomeConnectionSettingButtonActive.setVisible( false );
         new FadeOut( settingConnectionPane ).play();
         settingConnectionPane.setDisable( true );
      }
      themeSubLabel.setVisible( false );
      languageSubLabel.setVisible( false );
      emergencySubLabel.setVisible( false );
      notificationSubLabel.setVisible( false );
      connectionSubLabel.setVisible( false );
   }

   /**
    * closeApplicationSettingPane method that close application setting
    */
   void closeApplicationSettingPane() {
      applicationSettingButtonActive.setVisible( false );
      applicationSettingSubPane.setVisible( false );
      closeAllApplicationSettingSubPanes();
      themeSubLabel.setVisible( textCheck );
   }

   /**
    * openApplicationPanes method that open application setting
    */
   void openApplicationPanes() {
      applicationSettingButtonActive.setVisible( true );
      applicationSettingSubPane.setVisible( true );
      openThemeSetting();
      closeAllUsersPane();
      closeModsPane();
      closeHomeSettingPane();
   }


   /**
    * themePaneButtonsOnAction method that determines which theme is given
    *
    * @param event is an ActionEvent input parameter
    * @throws SQLException is an Exception that is due to database
    *          connection or communication failure
    */
   @FXML
   void themePaneButtonsOnAction( ActionEvent event ) throws SQLException {
      unSelectAllTheme();
      ( ( JFXRadioButton ) event.getSource() ).setSelected( true );
      changeTheme( ( ( JFXRadioButton ) event.getSource() ).getText().toLowerCase() );
      Users.getInstance().updateUsersTheme( loginUser,
            ( ( JFXRadioButton ) event.getSource() ).getText().toLowerCase() );
   }

   /**
    * unSelectAllTheme method make unselected all theme radio buttons.
    * The reason of this is that It save us to write code for each radio
    * button(12)
    */
   public void unSelectAllTheme() {
      cartoonThemeRadioButton.setSelected( false );
      lightThemeRadioButton.setSelected( false );
      smoothThemeRadioButton.setSelected( false );
      darkThemeRadioButton.setSelected( false );
      spaceThemeRadioButton.setSelected( false );
      forestThemeRadioButton.setSelected( false );
      cyberpunkThemeRadioButton.setSelected( false );
      neonThemeRadioButton.setSelected( false );
      interstellarThemeRadioButton.setSelected( false );
      pyramidThemeRadioButton.setSelected( false );
      smartCityThemeRadioButton.setSelected( false );
      abstractThemeRadioButton.setSelected( false );
   }

   /**
    * changeTheme method that regulate GUI according to given theme written are
    * Hacı Çakın, İlke Doğan
    * @param themeName is a String input parameter that is new theme name
    */
   void changeTheme( String themeName ) {
      String css;
      unSelectAllTheme();

      if( themeName.equals( "light" )
            || themeName.equals( "aydınlık" )
            || themeName.equals( "licht" )
            || themeName.equals( "lıght" )
            || themeName.equals( "lıcht" )
            || themeName.equals( "lumiere" )
            || themeName.equals( "lumıere" )
            || themeName.equals( "leggero" )
            || themeName.equals( "aydinlik" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_light_theme.css" ).toExternalForm();
         lightThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/lightTheme.png" ) ) );

      } else if( themeName.equals( "dark" )
            || themeName.equals( "gece" )
            || themeName.equals( "sombre" )
            || themeName.equals( "buio" )
            || themeName.equals( "buıo" )
            || themeName.equals( "dunkel" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_dark_theme.css" ).toExternalForm();
         darkThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/darkTheme.png" ) ) );

      } else if( themeName.equals( "smooth" )
            || themeName.equals( "pürüzsüz" )
            || themeName.equals( "puruzsuz" )
            || themeName.equals( "liscio" )
            || themeName.equals( "lıscıo" )
            || themeName.equals( "lısse" )
            || themeName.equals( "lisse" )
            || themeName.equals( "glatt" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_smooth_themee.css" ).toExternalForm();
         smoothThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/smoothTheme.png" ) ) );

      } else if( themeName.equals( "cartoon" )
            || themeName.equals( "çizgi film" )
            || themeName.equals( "karikatur" )
            || themeName.equals( "karıkatur" )
            || themeName.equals( "dessin anime" )
            || themeName.equals( "dessın anıme" )
            || themeName.equals( "çızgı fılm" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_cartoon_theme.css" ).toExternalForm();
         cartoonThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/cartoonTheme.png" ) ) );

      } else if( themeName.equals( "forest" )
            || themeName.equals( "orman" )
            || themeName.equals( "foret" )
            || themeName.equals( "foresta" )
            || themeName.equals( "wald" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_forest_theme.css" ).toExternalForm();
         forestThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/forestTheme.png" ) ) );

      } else if( themeName.equals( "uzay" )
            || themeName.equals( "espace" )
            || themeName.equals( "space" )
            || themeName.equals( "spazıo" )
            || themeName.equals( "spazio" )
            || themeName.equals( "platz" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_space_theme.css" ).toExternalForm();
         spaceThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/spaceTheme.png" ) ) );

      } else if( themeName.equals( "neon" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_neon_theme.css" ).toExternalForm();
         neonThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/neonTheme.png" ) ) );

      } else if( themeName.equals( "interstellar" )
            || themeName.equals( "ınterstellar" )
            || themeName.equals( "yıldızlararası" )
            || themeName.equals( "yildizlararasi" )
            || themeName.equals( "interstellare" )
            || themeName.equals( "ınterstellare" )
            || themeName.equals( "interstellaire" )
            || themeName.equals( "ınterstellaıre" )) {
         css = this.getClass().getResource( "styleSheets/main_menu_interstellar_theme.css" ).toExternalForm();
         interstellarThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/interstellerTheme.png" ) ) );
      } else if( themeName.equals( "pyramid" )
            || themeName.equals( "pyramıd" )
            || themeName.equals( "pyramide" )
            || themeName.equals( "pyramıde" )
            || themeName.equals( "piramide" )
            || themeName.equals( "pıramıde" )
            || themeName.equals( "piramit" )
            || themeName.equals( "pıramıt" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_pyramid_theme.css" ).toExternalForm();
         pyramidThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/pyramidTheme.png" ) ) );

      } else if( themeName.equals( "cyberpunk" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_cyberpunk_theme.css" ).toExternalForm();
         cyberpunkThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/cyberpunkTheme.png" ) ) );

      } else if( themeName.equals( "abstract" )
            || themeName.equals( "abstrakt" )
            || themeName.equals( "abstraite" )
            || themeName.equals( "abstraıte" )
            || themeName.equals( "astratto" )
            || themeName.equals( "soyut" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_abstract_theme.css" ).toExternalForm();
         abstractThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/abstractTheme.png" ) ) );

      } else if( themeName.equals( "smart city" )
            || themeName.equals( "smart cıty" )
            || themeName.equals( "intelligente stadt" )
            || themeName.equals( "ıntellıgente stadt" )
            || themeName.equals( "citta intelligente" )
            || themeName.equals( "cıtta ıntellıgente" )
            || themeName.equals( "ville intelligente" )
            || themeName.equals( "vılle ıntellıgente" )
            || themeName.equals( "akıllı sehır" )
            || themeName.equals( "akilli sehir" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_smart_cities_theme.css" ).toExternalForm();
         smartCityThemeRadioButton.setSelected( true );
         themeImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/smartCityTheme.png" ) ) );

      } else
         css = "";

      try {
         commonBorderPane.getStylesheets().removeAll( commonBorderPane.getStylesheets() );
         commonBorderPane.getStylesheets().add( css );
      } catch( Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * languagePaneButtonsOnAction method regulates language of application
    * according to source of event
    *
    * @param event is an ActionEvent input parameter
    * @throws SQLException is an Exception that is due to database
    *          connection or communication failure
    */
   @FXML
   void languagePaneButtonsOnAction( ActionEvent event ) throws SQLException {
      String language;
      language = "";

      unSelectAllLanguage();
      if( event.getSource() == englishOption ) {
         selectEnglishOption();
         languageSetter( "en" );
         language = "ENGLISH";

      } else if( event.getSource() == germanOption ) {
         selectGermanOption();
         languageSetter( "de" );
         language = "DEUTSCH";

      } else if( event.getSource() == turkishOption ) {
         selectTurkishOption();
         languageSetter( "tr" );
         language = "TÜRKÇE";

      }  else if( event.getSource() == italianOption ) {
         selectItalianOption();
         languageSetter( "de" );
         language = "ITALIANO";

      } else if( event.getSource() == franceOption ) {
         selectFrenchOption();
         languageSetter( "fr" );
         language = "FRANCAIS";
      }

      Users.getInstance().updateLanguage( loginUser, language );
      userPreferenceUpdate( loginUser );
   }

   /**
    * languageSetter method that regulate all strings according to given string.
    * It adjusts new language using properties files
    *
    * @param language is a String input parameter( tr, en, de)
    */
   private void languageSetter( String language ) {
      try {
         Locale locale = new Locale( language );
         bundle = ResourceBundle.getBundle( "com.SmartHomeBilkent.language.lang", locale );
         menuUserProfileLabel.setText( bundle.getString( "userProfileLang" ) );
         menuMenuLabel.setText( bundle.getString( "menuLang" ) );
         menuSettingLabel.setText( bundle.getString( "settingLang" ) );
         elecSubPaneLabelPassive.setText( bundle.getString( "elecLang" ) );
         elecSubPaneLabelActive.setText( bundle.getString( "elecLang" ) );
         elecSubPaneOpenLabelPassive.setText( bundle.getString( "lastOpenLang" ) );
         elecSubPaneOpenLabelActive.setText( bundle.getString( "lastOpenLang" ) );
         elecSubPaneCloseLabelPassive.setText( bundle.getString( "lastCloseLang" ) );
         elecSubPaneCloseLabelActive.setText( bundle.getString( "lastCloseLang" ) );
         elecSubPaneOpenValueLabelPassive.setText( bundle.getString( "yesterdayLang" ) );
         elecSubPaneOpenValueLabelActive.setText( bundle.getString( "yesterdayLang" ) );
         elecSubPaneCloseValueLabelPassive.setText( bundle.getString( "yesterday2Lang" ) );
         elecSubPaneCloseValueLabelActive.setText( bundle.getString( "yesterday2Lang" ) );
         gasSubPaneLabelPassive.setText( bundle.getString( "gasLang" ) );
         gasSubPaneLabelActive.setText( bundle.getString( "gasLang" ) );
         aquariumSubPaneLabelPassive.setText( bundle.getString( "aquiarumLang" ) );
         aquariumSubPaneLabelActive.setText( bundle.getString( "aquiarumLang" ) );
         greenHouseSubPaneLabelPassive.setText( bundle.getString( "greenHouse" ) );
         ghTempSubPaneLabelActive.setText( bundle.getString( "tempLang" ) );
         ghHumidityTempLabel.setText( bundle.getString( "humidity" ) );
         menuHomeTempLabel.setText( bundle.getString( "tempLang" ) );
         userProfileNameLabel.setText( bundle.getString( "nameLang" ) );
         userProfileSurnameLabel.setText( bundle.getString( "surnameLang" ) );
         userProfileAgeLabel.setText( bundle.getString( "birthdayLang" ) );
         userProfileGenderLabel.setText( bundle.getString( "genderLang" ) );
         userProfileUserNameLabel.setText( bundle.getString( "userNameLang" ) );
         userProfilePasswordLabel.setText( bundle.getString( "passwordLang" ) );
         userChangerInfoLabel.setText( bundle.getString( "userChangerLang" ) );
         changeUserInfoLabel.setText( bundle.getString( "changeUserInfoLang" ) );
         changeUserPrivateInfoLabel.setText( bundle.getString( "changeUserInfoLang" ) );
         normalChangeNameLabel.setText( bundle.getString( "nameLang" ) );
         normalChangeSurnameLabel.setText( bundle.getString( "surnameLang" ) );
         normalChangeBirthdayLabel.setText( bundle.getString( "birthdayLang" ) );
         normalChangeGenderLabel.setText( bundle.getString( "genderLang" ) );
         normalInfoWarning.setText( bundle.getString( "normalInfoWarningLang" ) );
         userNameLabel.setText( bundle.getString( "userNameLang" ) );
         newPasswordLabel.setText( bundle.getString( "newPasswordLang" ) );
         verifyNewPasswordLabel.setText( bundle.getString( "verifyNewPasswordLang" ) );
         currentPasswordLabel.setText( bundle.getString( "currentPasswordLang" ) );
         applicationSettingButtonLabel.setText( bundle.getString( "applicationSettingLang" ) );
         usersSettingButtonLabel.setText( bundle.getString( "usersSettingLang" ) );
         modsSettingButtonLabel.setText( bundle.getString( "modsSettingLang" ) );
         homeSettingButtonLabel.setText( bundle.getString( "homeLang" ) );
         themeTopLabel.setText( bundle.getString( "themeLang" ) );
         languageTopLabel.setText( bundle.getString( "languageLang" ) );
         emergencyTopLabel.setText( bundle.getString( "emergencyLang" ) );
         notificationTopLabel.setText( bundle.getString( "notificationLang" ) );
         connectionTopLabel.setText( bundle.getString( "connectionLang" ) );
         themeSubLabel.setText( bundle.getString( "themeLang" ) );
         languageSubLabel.setText( bundle.getString( "languageLang" ) );
         emergencySubLabel.setText( bundle.getString( "emergencyLang" ) );
         notificationSubLabel.setText( bundle.getString( "notificationLang" ) );
         connectionSubLabel.setText( bundle.getString( "connectionLang" ) );
         createUserTopLabel.setText( bundle.getString( "createUserLang" ) );
         createUserNameLabel.setText( bundle.getString( "nameLang" ) );
         createUserSurnameLabel.setText( bundle.getString( "surnameLang" ) );
         createUserGenderLabel.setText( bundle.getString( "genderLang" ) );
         createUserBirthdayLabel.setText( bundle.getString( "birthdayLang" ) );
         createUserUserNameLabel.setText( bundle.getString( "userNameLang" ) );
         createUserPasswordLabel.setText( bundle.getString( "passwordLang" ) );
         createUserPasswordVerifyLabel.setText( bundle.getString( "passwordVerifyLang" ) );
         createUserUserTypeLabel.setText( bundle.getString( "userTypeLang" ) );
         createUserThemeLabel.setText( bundle.getString( "themeLang" ) );
         createUserLanguageLabel.setText( bundle.getString( "languageLang" ) );
         createUserWarningLabel.setText( bundle.getString( "createUserWarningLabel" ) );
         removeAnUser.setText( bundle.getString( "removeAnUserLang" ) );
         removeUserFirstWarning.setText( bundle.getString( "removeUserFirstWarningLang" ) );
         removeUserHideWarning.setText( bundle.getString( "removeUserHideWarning" ) );
         maleRadioOption.setText( bundle.getString( "maleGenderLang" ) );
         femaleRadioOption.setText( bundle.getString( "femaleGenderLang" ) );
         darkThemeRadioButton.setText( bundle.getString( "darkThemeLang" ) );
         lightThemeRadioButton.setText( bundle.getString( "lightThemeLang" ) );
         smoothThemeRadioButton.setText( bundle.getString( "smoothThemeLang" ) );
         cartoonThemeRadioButton.setText( bundle.getString( "cartoonThemeLang" ) );
         portChooser.setPromptText( bundle.getString( "portChooserLang" ) );
         settingUserTableName.setText( bundle.getString( "nameLang" ) );
         settingUserTableSurname.setText( bundle.getString( "surnameLang" ) );
         settingUserTableBirthday.setText( bundle.getString( "birthdayLang" ) );
         settingUserTableGender.setText( bundle.getString( "genderLang" ) );
         settingUserTableUserName.setText( bundle.getString( "userNameLang" ) );
         settingUserTableTheme.setText( bundle.getString( "themeLang" ) );
         settingUserTableLanguage.setText( bundle.getString( "languageLang" ) );
         createUserMaleOption.setText( bundle.getString( "maleGenderLang" ) );
         createUserFemaleOption.setText( bundle.getString( "femaleGenderLang" ) );
         createUserDarkThemeOption.setText( bundle.getString( "darkThemeLang" ) );
         createUserLightThemeOption.setText( bundle.getString( "lightThemeLang" ) );
         createUserSmoothThemeOption.setText( bundle.getString( "smoothThemeLang" ) );
         createUserCartoonThemeOption.setText( bundle.getString( "cartoonThemeLang" ) );
         createUserParentOption.setText( bundle.getString( "parentOptionLang" ) );
         createUserChildOption.setText( bundle.getString( "childOptionLang" ) );
         createUserElderOption.setText( bundle.getString( "elderOptionLang" ) );
         homeElecSettingTopLabel.setText( bundle.getString( "elecSettingTopLang" ) );
         homeGasSettingTopLabel.setText( bundle.getString( "gasSettingTopLang" ) );
         homeAquSettingTopLabel.setText( bundle.getString( "aquSettingTopLang" ) );
         homeGreenHouseSettingTopLabel.setText( bundle.getString( "greenHouseSettingTopLang" ) );
         homeWeatherSettingTopLabel.setText( bundle.getString( "weatherLang" ) );
         homeSubPaneELecLabel.setText( bundle.getString( "elecLang" ) );
         homeSubPaneGasLabel.setText( bundle.getString( "gasLang" ) );
         homeSubPaneAquLabel.setText( bundle.getString( "aquiarumLang" ) );
         homeSubPaneGreenHouseLabel.setText( bundle.getString( "greenHouse" ) );
         homeSubPaneWeatherLabel.setText( bundle.getString( "weatherLang" ) );
         interactiveTextModeLabel.setText( bundle.getString( "interactiveTextLang" ) );
         interactiveSoundModeLabel.setText( bundle.getString( "interactiveSoundLang" ) );
         soundVolumeLabel.setText( bundle.getString( "soundVolume" ) );
         electricityRadioButton.setText( bundle.getString( "elecLang" ) );
         gasRadioButton.setText( bundle.getString( "gasLang" ) );
         aquariumRadioButton.setText( bundle.getString( "airMotorLang" ) );
         waterRadioButton.setText( bundle.getString( "waterLang" ) );
         gardenLightRadioButton.setText( bundle.getString( "gardenLightLang" ) );
         incomingWaterRadioButton.setText( bundle.getString( "incomingWaterLang" ) );
         sirenRadioButton.setText( bundle.getString( "sirenLang" ) );
         feedRadioButton.setText( bundle.getString( "feedLang" ) );
         doorRadioButton.setText( bundle.getString( "doorLang" ) );
         outgoingWaterRadioButton.setText( bundle.getString( "outgoingWaterLang" ) );
         bulkChangesSaveButton.setText( bundle.getString( "runLang" ) );
         dateTimeSaveButton.setText( bundle.getString( "saveLang" ) );
         waterSubPaneLabelPassive.setText( bundle.getString( "waterLang" ) );
         waterSubPaneLabelActive.setText( bundle.getString( "waterLang" ) );
         gardenLightSubPaneLabelPassive.setText( bundle.getString( "gardenLightLang" ) );
         gardenLightSubPaneLabelActive.setText( bundle.getString( "gardenLightLang" ) );
         timeConfigurationLabel.setText( bundle.getString( "timeConfigurationLang" ) );
         elecBarChartX.setLabel( bundle.getString( "hoursLang" ) );
         gasBarChartX.setLabel( bundle.getString( "hoursLang" ) );
         speciesOfFishLabel.setText( bundle.getString( "speciesOfFishLang" ) );
         feedingStartLabel.setText( bundle.getString( "feedingStartTimeLang" ) );
         waterExchangeLabel.setText( bundle.getString( "waterExchangeDayTimeLang" ) );
         airMotorRunTimeLabel.setText( bundle.getString( "airEngineRunTimeStartTimeLang" ) );
         greenHouseTemperatureLabel.setText( bundle.getString( "tempLang" ) );
         greenHouseHumidityLabel.setText( bundle.getString( "humidity" ) );
         latestWaterLabel.setText( bundle.getString( "latestWaterFromAquariumLang" ) );
         settingWeatherForecastLabel.setText( bundle.getString( "weatherForecastLang" ) + ": " );
         settingWeatherTemperatureLabel.setText( bundle.getString( "tempLang" ) + ": " );
         settingWeatherHumidityLabel.setText( bundle.getString( "humidity" ) + ": " );
         settingWeatherWindLabel.setText( bundle.getString( "windLang" ) + ": " );
         settingWeatherLocationTextField.setPromptText( bundle.getString( "locationLang" ) );
         menuOpenDoorLabel.setText( bundle.getString( "openDoorLang" ) );
         menuBulkChangeSubLabel.setText( bundle.getString( "bulkChangesLang" ) );
         mainMenuWeatherLabel.setText( bundle.getString( "weatherLang" ) );
         mainMenuHomeLabel.setText( bundle.getString( "homeLang" ) );
         removeUserTextField.setPromptText( bundle.getString( "passwordLang" ) );
         internalSirenToggle.setText( bundle.getString( "internalSirenLang" ) );
         externalSirenToggle.setText( bundle.getString( "externalSirenLang" ) );
         settingFireButtonLabel.setText( bundle.getString( "fireButtonLang" ) );
         settingGasSensorLabel.setText( bundle.getString( "gasSensorLang" ) );
         settingSmokeSensorLabel.setText( bundle.getString( "smokeSensor" ) );
         settingVisualWarningLabel.setText( bundle.getString( "visualWarningLang" ) );
         settingAuditoryWarningLabel.setText( bundle.getString( "auditoryWarning" ) );
         permissionPaneTopLabel.setText( bundle.getString( "childPermissionLang" ) );
         permissionPaneElectricityToggle.setText( bundle.getString( "elecLang" ) );
         permissionPaneWaterToggle.setText( bundle.getString( "waterLang" ) );
         permissionPaneGardenLightToggle.setText( bundle.getString( "gardenLightLang" ) );
         permissionPaneAquariumToggle.setText( bundle.getString( "aquiarumLang" ) );
         permissionPaneNotificationToggle.setText( bundle.getString( "notificationLang" ) );
         permissionPaneGasToggle.setText( bundle.getString( "gasLang" ) );
         permissionPaneSirenToggle.setText( bundle.getString( "sirenLang" ) );
         aquariumSubMenuToggleButton.setText( bundle.getString( "airMotorLang" ) );
         weatherCharacterWarningLabel.setText( bundle.getString( "latinCharacterWarningLang" ) );
         spaceThemeRadioButton.setText( bundle.getString( "spaceThemeLang" ) );
         createUserSpaceThemeOption.setText( bundle.getString( "spaceThemeLang" ) );
         forestThemeRadioButton.setText( bundle.getString( "forestThemeLang" ) );
         createUserForestThemeOption.setText( bundle.getString( "forestThemeLang" ) );
         cyberpunkThemeRadioButton.setText( bundle.getString( "cyberpunkThemeLang" ) );
         neonThemeRadioButton.setText( bundle.getString( "neonThemeLang" ) );
         interstellarThemeRadioButton.setText( bundle.getString( "interstellarThemeLang" ) );
         pyramidThemeRadioButton.setText( bundle.getString( "pyramidThemeLang" ) );
         smartCityThemeRadioButton.setText( bundle.getString( "smartCityThemeLang" ) );
         abstractThemeRadioButton.setText( bundle.getString( "abstractThemeLang" ) );
         createUserSmartCityThemeOption.setText( bundle.getString( "smartCityThemeLang" ) );
         createUserAbstractThemeOption.setText( bundle.getString( "abstractThemeLang" ) );
         createUserPyramidThemeOption.setText( bundle.getString( "pyramidThemeLang" ) );
         createUserCyberpunkThemeOption.setText( bundle.getString( "cyberpunkThemeLang" ) );
         createUserNeonThemeOption.setText( bundle.getString( "neonThemeLang" ) );
         createUserInterstellarThemeOption.setText( bundle.getString( "interstellarThemeLang" ) );
      } catch( Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * unSelectAllLanguage method that makes unselected all language button.
    * The reason of writing this is that we prevent to write twice same things
    */
   void unSelectAllLanguage() {
      englishOption.setPrefSize( 90, 90 );
      englishOption.setLayoutX( 15 );
      englishOption.setLayoutY( 135 );
      germanOption.setPrefSize( 90, 90 );
      germanOption.setLayoutX( 160 );
      germanOption.setLayoutY( 135 );
      turkishOption.setPrefSize( 90, 90 );
      turkishOption.setLayoutX( 305 );
      turkishOption.setLayoutY( 135 );
      italianOption.setPrefSize( 90, 90 );
      italianOption.setLayoutX( 450 );
      italianOption.setLayoutY( 135 );
      franceOption.setPrefSize( 90, 90 );
      franceOption.setLayoutX( 595 );
      franceOption.setLayoutY( 135 );
   }

   /**
    * selectEnglishOption method that select english and adjust
    * GUI according to this
    */
   void selectEnglishOption() {
      englishOption.setPrefSize( 140, 140 );
      englishOption.setLayoutX( 5 );
      englishOption.setLayoutY( 110 );
   }

   /**
    * selectGermanOption method that select english and adjust
    * GUI according to this
    */
   void selectGermanOption() {
      germanOption.setPrefSize( 140, 140 );
      germanOption.setLayoutX( 135 );
      germanOption.setLayoutY( 110 );
   }

   /**
    * selectTurkishOption method that select english and adjust
    * GUI according to this
    */
   void selectTurkishOption() {
      turkishOption.setPrefSize( 140, 140 );
      turkishOption.setLayoutX( 280 );
      turkishOption.setLayoutY( 110 );
   }

   /**
    * selectItalianOption method that select italian and adjust
    * GUI according to this
    */
   void selectItalianOption() {
      italianOption.setPrefSize( 140, 140 );
      italianOption.setLayoutX( 425 );
      italianOption.setLayoutY( 110 );
   }

   /**
    * selectFrenchOption method that select french and adjust
    * GUI according to this
    */
   void selectFrenchOption() {
      franceOption.setPrefSize( 140, 140 );
      franceOption.setLayoutX( 555 );
      franceOption.setLayoutY( 110 );
   }

   /*
   4.2)users setting pane methods
   */

   /**
    * closeAllUsersPane method closes the users pane with Animation
    */
   void closeAllUsersPane() {
      usersSettingSubPane.setDisable( true );
      settingUsersPane.setDisable( true );
      settingsUsersSettingButtonActive.setVisible( false );

      if( !addUserPane.isDisable() ) {
         addUserPane.setDisable( true );
         new FadeOut( addUserPane ).play();
         usersSettingSubPane.setOpacity( 0 );
         settingUsersPane.setOpacity( 0 );

      }
      if( !deleteUserPane.isDisable() ) {
         deleteUserPane.setDisable( true );
         new FadeOut( deleteUserPane ).play();
         usersSettingSubPane.setOpacity( 0 );
         settingUsersPane.setOpacity( 0 );

      }
      if( !permissionPane.isDisable() ) {
         permissionPane.setDisable( true );
         new FadeOut( permissionPane ).play();
         usersSettingSubPane.setOpacity( 0 );
         settingUsersPane.setOpacity( 0 );

      } else {
         new FadeOut( usersSettingSubPane ).play();
         new FadeOut( settingUsersPane ).play();
      }
   }

   /**
    * openUsersPane method opens users pane with Animation
    */
   void openUsersPane() {
      settingUsersPane.setDisable( false );
      usersSettingSubPane.setDisable( false );
      new FadeInUp( settingUsersPane ).play();
      new FadeInUp( usersSettingSubPane ).play();
      settingsUsersSettingButtonActive.setVisible( true );
      closeApplicationSettingPane();
      closeModsPane();
      closeHomeSettingPane();
   }

   /**
    * usersSettingsPaneButtonsOnAction method that controls GUI changes
    * when these buttons are triggered
    *
    * @param event is an ActionEvent input parameter that source of
    *              the action in users pane
    */
   @FXML
   void usersSettingsPaneButtonsOnAction( ActionEvent event ) {
      if( event.getSource() == usersSettingSubPaneAddUser ) {
         addUserPane.setDisable( false );
         deleteUserPane.setDisable( true );
         permissionPane.setDisable( true );
         new FadeInUp( addUserPane ).play();
         sound( "createUserLang", soundCheck );

      } else if( event.getSource() == usersSettingSubPaneRemoveUser ) {
         addUserPane.setDisable( true );
         deleteUserPane.setDisable( false );
         permissionPane.setDisable( true );
         new FadeInUp( deleteUserPane ).play();
         sound( "removeAnUserLang", soundCheck );

      } else if( event.getSource() == usersSettingSubPanePermissionButton ) {
         addUserPane.setDisable( true );
         deleteUserPane.setDisable( true );
         permissionPane.setDisable( false );
         new FadeInUp( permissionPane ).play();
         //sound
      }
   }

   /**
    * settingUserTableMov method controls which user is chosen from
    * table when there is a movement on the table
    */
   @FXML
   void settingUserTableMov() {
      if( settingUserTable.getSelectionModel().isEmpty() )
         usersSettingSubPaneRemoveUser.setDisable( true );
      else
         usersSettingSubPaneRemoveUser.setDisable( settingUserTable.getSelectionModel().getSelectedItem().getValue().getUserType().equals( "PARENT" )
               && !( Users.getInstance().getParentNumber() > 1 ) );
   }


   /**
    * createUserPaneAction method controls the status whether new user
    * information fields suitable or not and add user according to
    * button's action
    *
    * @param event is an ActionEvent input parameter that is source of the action
    * @throws SQLException is an Exception that is due to database
    *          connection or communication failure
    */
   @FXML
   void createUserPaneAction( ActionEvent event ) throws SQLException {
      if( event.getSource() == createUserConfirmButton ) {
         if( createUserNameTextField.getText().isEmpty()
               || createUserSurnameTextField.getText().isEmpty()
               || createDatePicker.getValue() == null
               || ( !createUserMaleOption.isSelected()
               && !createUserFemaleOption.isSelected() )
               || ( !createUserDarkThemeOption.isSelected()
               && !createUserLightThemeOption.isSelected()
               && !createUserForestThemeOption.isSelected()
               && !createUserSpaceThemeOption.isSelected()
               && !createUserSmoothThemeOption.isSelected()
               && !createUserSmartCityThemeOption.isSelected()
               && !createUserAbstractThemeOption.isSelected()
               && !createUserPyramidThemeOption.isSelected()
               && !createUserCyberpunkThemeOption.isSelected()
               && !createUserNeonThemeOption.isSelected()
               && !createUserInterstellarThemeOption.isSelected()
               && !createUserCartoonThemeOption.isSelected() )
               || ( !createUserEnglishOption.isSelected()
               && !createUserGermanOption.isSelected()
               && !createUserTurkishOption.isSelected()
               && !createUserItalianOption.isSelected()
               && !createUserFrenchOption.isSelected() )
               || createUserUserNameTextField.getText().isEmpty()
               || createUserPasswordField.getText().isEmpty()
               || createUserPasswordVerifyField.getText().isEmpty()
               || ( !createUserParentOption.isSelected()
               && !createUserChildOption.isSelected()
               && !createUserElderOption.isSelected() ) ) {
            createUserWarningLabel.setText( bundle.getString( "normalInfoWarningLang" ) );
            sound( "normalInfoWarningLang", soundCheck );

         } else if( !createUserPasswordField.getText().equals( createUserPasswordVerifyField.getText() ) ) {
            createUserWarningLabel.setText( bundle.getString( "passwordConflictLang" ) );
            sound( "passwordConflictLang", soundCheck );

         } else {
            Users.getInstance().addUser( new User( createUserNameTextField.getText(),
                  createUserSurnameTextField.getText(),
                  createDatePicker.getValue().format( dateTimeFormatter ),
                  addUserGender.getText(), createUserUserNameTextField.getText(),
                  createUserPasswordField.getText(), addUserType.getText(),
                  addUserTheme.getText().toLowerCase(), addUserLanguage.getText(),
                  "false",
                  "true",
                  "true050",
                  "Ankara" ) );
            updateUsersTable();
            addUserPane.setDisable( true );
            new FadeOut( addUserPane ).play();
            clearAddPane();

            if( ( Users.getInstance().getParentNumber() > 1 ) ) {
               usersSettingSubPaneRemoveUser.setDisable( false );
            }
         }

      } else if( event.getSource() == createUserGoBack ) {
         addUserPane.setDisable( true );
         new FadeOut( addUserPane ).play();
         clearAddPane();
      } else if( event.getSource() == createUserMaleOption
            || event.getSource() == createUserFemaleOption ) {
         createUserMaleOption.setSelected( false );
         createUserFemaleOption.setSelected( false );
         ( ( JFXRadioButton ) event.getSource() ).setSelected( true );
         addUserGender = ( JFXRadioButton ) event.getSource();

      } else if( event.getSource() == createUserDarkThemeOption
            || event.getSource() == createUserLightThemeOption
            || event.getSource() == createUserSmoothThemeOption
            || event.getSource() == createUserSpaceThemeOption
            || event.getSource() == createUserInterstellarThemeOption
            || event.getSource() == createUserSmartCityThemeOption
            || event.getSource() == createUserCyberpunkThemeOption
            || event.getSource() == createUserNeonThemeOption
            || event.getSource() == createUserPyramidThemeOption
            || event.getSource() == createUserAbstractThemeOption
            || event.getSource() == createUserForestThemeOption
            || event.getSource() == createUserCartoonThemeOption ) {
         createUserDarkThemeOption.setSelected( false );
         createUserLightThemeOption.setSelected( false );
         createUserSmoothThemeOption.setSelected( false );
         createUserCartoonThemeOption.setSelected( false );
         createUserSpaceThemeOption.setSelected( false );
         createUserForestThemeOption.setSelected( false );
         createUserSmartCityThemeOption.setSelected( false );
         createUserAbstractThemeOption.setSelected( false );
         createUserPyramidThemeOption.setSelected( false );
         createUserCyberpunkThemeOption.setSelected( false );
         createUserNeonThemeOption.setSelected( false );
         createUserInterstellarThemeOption.setSelected( false );
         ( ( JFXRadioButton ) ( event.getSource() ) ).setSelected( true );
         addUserTheme = ( JFXRadioButton ) ( event.getSource() );

      } else if( event.getSource() == createUserEnglishOption
            || event.getSource() == createUserGermanOption
            || event.getSource() == createUserTurkishOption
            || event.getSource() == createUserItalianOption
            || event.getSource() == createUserFrenchOption) {
         createUserEnglishOption.setSelected( false );
         createUserTurkishOption.setSelected( false );
         createUserGermanOption.setSelected( false );
         createUserItalianOption.setSelected( false );
         createUserFrenchOption.setSelected( false );
         ( ( JFXRadioButton ) ( event.getSource() ) ).setSelected( true );
         addUserLanguage = ( JFXRadioButton ) ( event.getSource() );

      } else if( event.getSource() == createUserParentOption
            || event.getSource() == createUserChildOption
            || event.getSource() == createUserElderOption ) {
         createUserParentOption.setSelected( false );
         createUserChildOption.setSelected( false );
         createUserElderOption.setSelected( false );
         ( ( JFXRadioButton ) event.getSource() ).setSelected( true );
         addUserType = ( JFXRadioButton ) event.getSource();
      }
   }

   /**
    * clearAddPane method that clear the all fields of add pane
    * the reason of writing twice is that we will use this code
    * more than one times
    */
   public void clearAddPane() {
      createUserNameTextField.setText( "" );
      createUserSurnameTextField.setText( "" );
      createUserMaleOption.setSelected( false );
      createUserFemaleOption.setSelected( false );
      createUserUserNameTextField.setText( "" );
      createUserPasswordField.setText( "" );
      createUserPasswordVerifyField.setText( "" );
      createUserDarkThemeOption.setSelected( false );
      createUserLightThemeOption.setSelected( false );
      createUserSmoothThemeOption.setSelected( false );
      createUserCartoonThemeOption.setSelected( false );
      createUserForestThemeOption.setSelected( false );
      createUserSpaceThemeOption.setSelected( false );
      createUserSmartCityThemeOption.setSelected( false );
      createUserAbstractThemeOption.setSelected( false );
      createUserPyramidThemeOption.setSelected( false );
      createUserCyberpunkThemeOption.setSelected( false );
      createUserNeonThemeOption.setSelected( false );
      createUserInterstellarThemeOption.setSelected( false );
      createUserEnglishOption.setSelected( false );
      createUserGermanOption.setSelected( false );
      createUserTurkishOption.setSelected( false );
      createUserItalianOption.setSelected( false );
      createUserFrenchOption.setSelected( false );
      createUserParentOption.setSelected( false );
      createUserChildOption.setSelected( false );
      addUserImageView.setVisible( false );
      createDatePicker.setValue( null );
      addUserGender = null;
      addUserLanguage = null;
      addUserTheme = null;
      addUserType = null;
   }

   /**
    * removeUserPaneAction method that control GUI information and remove user
    * according to buttons' action
    *
    * @param event is an ActionEvent inpur parameter that is source of the
    *              action in the remove user pane
    * @throws SQLException is an Exception that is due to database
    *          connection or communication failure
    */
   @FXML
   void removeUserPaneAction( ActionEvent event ) throws SQLException {

      if( event.getSource() == removeUserGoBack ) {
         deleteUserPane.setDisable( true );
         new FadeOut( deleteUserPane ).play();
         removeUserHideWarning.setVisible( false );

      } else if( event.getSource() == removeUserConfirm
            || event.getSource() == removeUserTextField ) {
         if( removeUserTextField.getText().isEmpty() ) {
            removeUserHideWarning.setText( bundle.getString( "removePasswordLang" ) );
            removeUserHideWarning.setVisible( true );
            sound( "removePasswordLang", soundCheck );

         } else if( loginUser.getUserType().equals( "PARENT" )
               && removeUserTextField.getText().equals( loginUser.getPassword() )
               && settingUserTable.getSelectionModel().getSelectedItem().getValue().getUserType().equals( "CHILD" ) ) {
            Users.getInstance().removeUser( settingUserTable.getSelectionModel().getSelectedItem().getValue() );
            deleteUserPane.setDisable( true );
            new FadeOut( deleteUserPane ).play();
            removeUserTextField.setText( "" );
            removeUserHideWarning.setVisible( false );

            if( !( Users.getInstance().getParentNumber() > 1 ) ) {
               usersSettingSubPaneRemoveUser.setDisable( true );
            }

         } else if( removeUserTextField.getText().equals( settingUserTable.getSelectionModel().getSelectedItem().getValue().getPassword() )
               && settingUserTable.getSelectionModel().getSelectedItem().getValue() == loginUser ) {

            try {
               Users.getInstance().removeUser( settingUserTable.getSelectionModel().getSelectedItem().getValue() );
               removeUserTextField.setText( "" );
               removeUserHideWarning.setVisible( false );
               FXMLLoader load = new FXMLLoader( getClass().getResource( "view/loginPanel.fxml" ) );
               Parent root = load.load();
               Stage stage = new Stage();
               stage.setTitle( "SMART HOME" );
               stage.setScene( new Scene( root, 400, 400 ) );
               stage.getIcons().add( new Image( Main.class.getResourceAsStream( "styleSheets/images/smartHome.png" ) ) );
               stage.setResizable( false );
               stage.show();
               commonBorderPane.getScene().getWindow().hide();

            } catch( Exception e ) {
               e.printStackTrace();
            }

         } else if( removeUserTextField.getText().equals( settingUserTable.getSelectionModel().getSelectedItem().getValue().getPassword() ) ) {
            Users.getInstance().removeUser( settingUserTable.getSelectionModel().getSelectedItem().getValue() );
            deleteUserPane.setDisable( true );
            new FadeOut( deleteUserPane ).play();
            removeUserTextField.setText( "" );
            removeUserHideWarning.setVisible( false );

            if( !( Users.getInstance().getParentNumber() > 1 ) ) {
               usersSettingSubPaneRemoveUser.setDisable( true );
            }

         } else {
            removeUserHideWarning.setText( bundle.getString( "passwordMistakeLang" ) );
            removeUserHideWarning.setVisible( true );
            sound( "passwordMistakeLang", soundCheck );
         }
      }
   }

   /**
    * permissionPaneButtonsOnAction method controls the permissions according
    * to status of toggle buttons
    *
    * @param event is an ActionEvent input parameter that is source of the event
    * @throws SQLException is an Exception that is due to database
    *          connection or communication failure
    */
   @FXML
   void permissionPaneButtonsOnAction( ActionEvent event ) throws SQLException {
      if( event.getSource() == permissionPaneElectricityToggle ) {
         if( permissionPaneElectricityToggle.isSelected() )
            permissions[ 0 ] = "O";
         else
            permissions[ 0 ] = "C";

      } else if( event.getSource() == permissionPaneGasToggle ) {
         if( permissionPaneGasToggle.isSelected() )
            permissions[ 1 ] = "O";
         else
            permissions[ 1 ] = "C";

      } else if( event.getSource() == permissionPaneWaterToggle ) {
         if( permissionPaneWaterToggle.isSelected() )
            permissions[ 2 ] = "O";
         else
            permissions[ 2 ] = "C";

      } else if( event.getSource() == permissionPaneGardenLightToggle ) {
         if( permissionPaneGardenLightToggle.isSelected() )
            permissions[ 3 ] = "O";
         else
            permissions[ 3 ] = "C";

      } else if( event.getSource() == permissionPaneAquariumToggle ) {
         if( permissionPaneAquariumToggle.isSelected() )
            permissions[ 4 ] = "O";
         else
            permissions[ 4 ] = "C";

      } else if( event.getSource() == permissionPaneNotificationToggle ) {
         if( permissionPaneNotificationToggle.isSelected() )
            permissions[ 5 ] = "O";
         else
            permissions[ 5 ] = "C";

      } else if( event.getSource() == permissionPaneSirenToggle ) {
         if( permissionPaneSirenToggle.isSelected() )
            permissions[ 6 ] = "O";
         else
            permissions[ 6 ] = "C";

      } else if( event.getSource() == permissionPaneGoBackButton ) {
         permissionPane.setDisable( true );
         new FadeOut( permissionPane ).play();
      }
      CommonSettingData.getInstance().updatePermission( commonSetting,
            permissions );
   }

   /*
    4.3)mods setting pane methods
    */

   /**
    * openModsPane method that opens mods pane in GUI
    */
   public void openModsPane() {
      settingModePane.setDisable( false );
      new FadeInUp( settingModePane ).play();
      modsSettingButtonActive.setVisible( true );
      closeApplicationSettingPane();
      closeAllUsersPane();
      closeHomeSettingPane();
   }

   /**
    * closeModsPane method that closes mods pane in GUI
    */
   public void closeModsPane() {
      settingModePane.setDisable( true );
      new FadeOut( settingModePane ).play();
      modsSettingButtonActive.setVisible( false );
   }

   /**
    * volumeAdjust controls the slider to get user preferred volume level
    * and send it database
    *
    * @throws SQLException is an Exception that is due to database
    *          connection or communication failure
    */
   @FXML
   void volumeAdjust() throws SQLException {
      String modsSound;

      if( soundVolumeSlider.getValue() < 10 ) {
         volume = "00" + ( int ) soundVolumeSlider.getValue();

      } else if( soundVolumeSlider.getValue() < 100 ) {
         volume = "0" + ( int ) soundVolumeSlider.getValue();

      } else {
         volume = "" + ( int ) soundVolumeSlider.getValue();
      }

      audioClip.stop();
      audioClip = new AudioClip( this.getClass().getResource( "music/" +
            bundle.getString( "pathLang" ) + "volumeTryLang" +
            bundle.getString( "mp3Lang" ) ).toString() );
      audioClip.setVolume( ( ( double ) Integer.parseInt( volume ) ) / 500 );
      audioClip.play( ( ( double ) Integer.parseInt( volume ) ) / 500 );
      modsSound = loginUser.getSound().substring( 0, loginUser.getSound().length() - 3 ) + volume;
      Users.getInstance().updateVolume( loginUser, modsSound );
      userPreferenceUpdate( loginUser );
      updateUsersTable();
   }

   /**
    * modsToggleButtons method controls whether user wants to use these
    * mods or not
    *
    * @param event is an ActionEvent input parameter that is source of the event
    * @throws SQLException is an Exception that is due to database
    *           connection or communication failure
    */
   @FXML
   void modsToggleButtons( ActionEvent event ) throws SQLException {
      String modsSound;
      String modsText;

      modsSound = loginUser.getSound();
      modsText = loginUser.getText();

      if( event.getSource() == textModeToggle ) {
         textCheck = textModeToggle.isSelected();
         modsText = String.valueOf( textCheck );
      } else if( event.getSource() == soundModeToggle ) {
         soundControl( soundModeToggle.isSelected() );
         modsSound = soundCheck + volume;
      }

      Users.getInstance().updateVolume( loginUser, modsSound );
      Users.getInstance().updateText( loginUser, modsText );
      userPreferenceUpdate( loginUser );
      updateUsersTable();
   }

   /**
    * soundControl method controls whether sound mode is active or not
    *
    * @param check is a boolean input parameter
    */
   public void soundControl( boolean check ) {
      soundVolumeSlider.setVisible( check );
      soundVolumeLabel.setVisible( check );
      soundCheck = check;
   }

   /*
 4.4)home setting pane methods
 */

   /**
    * openHomeSettingPane method opens home setting pane in GUI
    */
   public void openHomeSettingPane() {
      homeSettingButtonActive.setVisible( true );
      homeSettingSubPane.setDisable( false );
      settingElecSettingPane.setDisable( false );
      new FadeInUp( homeSettingSubPane ).play();
      new FadeInUp( settingElecSettingPane ).play();
      closeApplicationSettingPane();
      closeAllUsersPane();
      closeModsPane();
   }

   /**
    * closeHomeSettingPane method closes home setting pane in GUI
    */
   public void closeHomeSettingPane() {
      homeSettingButtonActive.setVisible( false );
      closeAllHomeSetting();
   }


   /**
    * homeSettingButtonsOnAction methods adjust GUI( open close panes, set
    * aquarium setting, weather update) according to source of the action
    *
    * @param event is an ActionEvent input parameter that source of the action
    * @throws SQLException is an Exception that is due to database
    *           connection or communication failure
    */
   @FXML
   void homeSettingButtonsOnAction( ActionEvent event ) throws SQLException {

      if( event.getSource() == homeSettingElecButton ) {
         closeAllHomeSetting();
         settingElecSettingPane.setDisable( false );
         new FadeInUp( settingElecSettingPane ).play();
         openHomeSetting();
         homeSettingElecButtonActive.setVisible( true );

      } else if( event.getSource() == homeSettingGasButton ) {
         closeAllHomeSetting();
         settingGasSettingPane.setDisable( false );
         new FadeInUp( settingGasSettingPane ).play();
         openHomeSetting();
         homeSettingGasButtonActive.setVisible( true );

      } else if( event.getSource() == homeSettingAquButton ) {
         closeAllHomeSetting();
         settingAquSettingPane.setDisable( false );
         new FadeInUp( settingAquSettingPane ).play();
         openHomeSetting();
         homeSettingAquButtonActive.setVisible( true );

      } else if( event.getSource() == homeSettingGreenHouseButton ) {
         closeAllHomeSetting();
         settingGreenHouseSettingPane.setDisable( false );
         new FadeInUp( settingGreenHouseSettingPane ).play();
         openHomeSetting();
         homeSettingGreenHouseButtonActive.setVisible( true );

      } else if( event.getSource() == homeSettingWeatherButton ) {
         closeAllHomeSetting();
         settingWeatherSettingPane.setDisable( false );
         new FadeInUp( settingWeatherSettingPane ).play();
         openHomeSetting();
         homeSettingWeatherButtonActive.setVisible( true );

      } else if( event.getSource() == updateWeatherButton
            || event.getSource() == settingWeatherLocationTextField ) {
         if( settingWeatherLocationTextField.getText().length() > 0 ) {
            weatherUpdateSpinner.setVisible( true );
            updateWeatherButton.setVisible( false );

            new Thread( () -> {
               try {
                  if( weatherForecast == null )
                     weatherForecast = new WeatherForecast(
                           loginUser.getLocation() );
                  else {
                     weatherForecast.findLocationXY(
                           settingWeatherLocationTextField.getText() );
                     weatherForecast.getWeatherCase();
                  }
               } catch( IOException exception ) {
                  settingWeatherForecastLabelValue.setText( bundle.getString(
                        "netConnectionLang" ) );
                  settingWeatherTemperatureLabelValue.setText( bundle.getString( "netConnectionLang" ) );
                  settingWeatherHumidityLabelValue.setText( bundle.getString( "netConnectionLang" ) );
                  settingWeatherWindLabelValue.setText( bundle.getString( "netConnectionLang" ) );
                  informationTime.setText( bundle.getString( "netConnectionLang" ) );
                  try {
                     Users.getInstance().updateLocation( loginUser,
                           settingWeatherLocationTextField.getText() );
                  } catch( SQLException sqlException ) {
                     sqlException.printStackTrace();
                  }
                  backgroundSetup( "weather" );
               }
               Platform.runLater( () -> {
                  weatherUpdateSpinner.setVisible( false );
                  if( weatherForecast.getLocation().length() == 0 ) {
                     weatherCharacterWarningLabel.setVisible( true );
                     settingWeatherForecastLabelValue.setText( "" );
                     settingWeatherTemperatureLabelValue.setText( "°C" );
                     settingWeatherHumidityLabelValue.setText( "" );
                     settingWeatherWindLabelValue.setText( "" );
                     informationTime.setText( "" );
                     weatherCharacterWarningLabel.setVisible( true );
                  } else {
                     try {
                        settingWeatherForecastLabelValue.setText( weatherForecast.getWeather() );
                        settingWeatherTemperatureLabelValue.setText( weatherForecast.getTemperature() + "°C" );
                        settingWeatherHumidityLabelValue.setText( weatherForecast.getHumidity() );
                        settingWeatherWindLabelValue.setText( weatherForecast.getWind() );
                        informationTime.setText( weatherForecast.getLocalTime() );
                        Users.getInstance().updateLocation( loginUser, settingWeatherLocationTextField.getText() );
                        backgroundSetup( weatherForecast.getWeather() );
                     } catch( SQLException sqlException ) {
                        sqlException.printStackTrace();
                     }
                  }
               } );
            } ).start();

         } else {
            informationTime.setText( "Please enter the location" );
         }
      } else if( event.getSource() == saveAquariumChangesButton ) {

         if( feedingTime.getValue() == null
               || waterExchangeTime.getValue() == null
               || airMotorStartTime.getValue() == null
               || waterExchangeDay.getValue() == null ) {
            aquariumSettingLabel.setText( bundle.getString( "normalInfoWarningLang" ) );
            aquariumSettingLabel.setVisible( true );
         } else {
            aquariumSettingLabel.setVisible( false );

            StringBuilder message;
            message = new StringBuilder( "aquarium#" );

            if( feedingTime.getValue().getHour() < 10 )
               message.append( "0" ).append( feedingTime.getValue().getHour() );
            else
               message.append( feedingTime.getValue().getHour() );

            if( feedingTime.getValue().getMinute() < 10 )
               message.append( "0" ).append( feedingTime.getValue().getMinute() ).append( "00" );
            else
               message.append( feedingTime.getValue().getMinute() ).append( "00" );

            if( waterExchangeTime.getValue().getHour() < 10 )
               message.append( "0" ).append( waterExchangeTime.getValue().getHour() );
            else
               message.append( waterExchangeTime.getValue().getHour() );

            if( waterExchangeTime.getValue().getMinute() < 10 )
               message.append( "0" ).append( waterExchangeTime.getValue().getMinute() ).append( "00" );
            else
               message.append( waterExchangeTime.getValue().getMinute() ).append( "00" );

            message.append( "0" ).append( waterExchangeDay.getValue().charAt( 0 ) );

            if( airMotorStartTime.getValue().getHour() < 10 )
               message.append( "0" ).append( airMotorStartTime.getValue().getHour() );
            else
               message.append( airMotorStartTime.getValue().getHour() );

            if( airMotorStartTime.getValue().getMinute() < 10 )
               message.append( "0" ).append( airMotorStartTime.getValue().getMinute() ).append( "00" );
            else
               message.append( airMotorStartTime.getValue().getMinute() ).append( "00" );

            if( airMotorRunTime.getValue() < 10 )
               message.append( "0" ).append( ( int ) airMotorRunTime.getValue() ).append( ":" );
            else
               message.append( ( int ) airMotorRunTime.getValue() ).append( ":" );

            if( isArduinoConnect )
               home.getAquarium().setAquariumSettings( message.toString() );

            CommonSettingData.getInstance().updateAquariumSettings( commonSetting, message.toString().substring( 9, 31 ) );
            CommonSettingData.getInstance().updateSelectedFishes( commonSetting, checkComboBox.getItems() );
         }
      }
   }

   /**
    * onWeatherKeyPressed method that shows the button when input has changed
    */
   @FXML
   void onWeatherKeyPressed() {
      updateWeatherButton.setVisible( true );
      weatherCharacterWarningLabel.setVisible( false );
   }

   /**
    * backgroundSetup method that changes background image according to weather
    *
    * @param weather is a String input parameter that is name of weather forecast
    * @author Hacı Çakın, İlke Doğan
    */
   public void backgroundSetup( String weather ) {
      if( weather.equalsIgnoreCase( "Cloudy" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/cloudy.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Partly cloudy" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/partlyCloudy1.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Sunny" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/sunny1.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Snowy" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/snowy.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Patches Of Fog" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/patchesOfFog.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Light Rain Shower" )
            || weather.equalsIgnoreCase( "Patchy rain possible" )
            || weather.equalsIgnoreCase( "rain" )
            || weather.equalsIgnoreCase( "Light Rain" )
            || weather.equalsIgnoreCase( "Shower In Vicinity, Rain Shower" )
            || weather.equalsIgnoreCase( "light drizzle" )
            || weather.equalsIgnoreCase( "Rain Shower" )
            || weather.equalsIgnoreCase( "Shower In Vicinity" )
            || weather.equalsIgnoreCase( "Light Rain Shower, Rain Shower" )
            || weather.equalsIgnoreCase( "Heavy rain" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/lightRain.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Mist" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/mist.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Light Rain With Thunderstorm, Rain With Thunderstorm" )
            || weather.equalsIgnoreCase( "Light Rain With Thunderstorm" )
            || weather.equalsIgnoreCase( "Rain With Thunderstorm" )
            || weather.equalsIgnoreCase( "Thunderstorm In Vicinity" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/lightRainWithThunderStorm.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Moderate or heavy rain shower" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/heavyRain.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Clear" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/Clear.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Blowing Widespread Dust" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/dust.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "Thundery outbreaks possible" ) || weather.equalsIgnoreCase( "Overcast" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/Thundery.jpg" ) ) );

      else if( weather.equalsIgnoreCase( "weather" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/weather.jpg" ) ) );

   }

   /**
    * settingToggleButtonsAction method that send message to embedded system
    * according to status of toggle buttons
    *
    * @param event is an ActionEvent input parameter that source toggle
    *              button of the action
    */
   @FXML
   void settingToggleButtonsAction( ActionEvent event ) {
      if( event.getSource() == settingElectricityToggleButton ) {
         if( isArduinoConnect )
            home.getElectricity().open( settingElectricityToggleButton.isSelected() );
         openElectricity( settingElectricityToggleButton.isSelected() );

         if( elecSubMenuToggleButton.isSelected() ) {
            elecSubPaneOpenValueLabelPassive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
            elecSubPaneOpenValueLabelActive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
         }
         else {
            elecSubPaneCloseValueLabelPassive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
            elecSubPaneCloseValueLabelActive.setText( "TODAY" +
                  LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() );
         }

      } else if( event.getSource() == settingGasToggleButton ) {
         if( isArduinoConnect )
            home.getGas().open( settingGasToggleButton.isSelected() );
         openGas( settingGasToggleButton.isSelected() );
      }
   }

   /**
    * openHomeSetting method opens home setting Pane in GUI
    */
   public void openHomeSetting() {
      homeSettingSubPane.setDisable( false );
      new FadeInDown( homeSettingSubPane ).play();
      homeSettingButtonActive.setVisible( true );
   }

   /**
    * closeAllHomeSetting method closes home setting Pane in GUI
    */
   public void closeAllHomeSetting() {
      if( !settingElecSettingPane.isDisable() ) {
         settingElecSettingPane.setDisable( true );
         new FadeOut( settingElecSettingPane ).play();
      }
      if( !settingGasSettingPane.isDisable() ) {
         settingGasSettingPane.setDisable( true );
         new FadeOut( settingGasSettingPane ).play();
      }
      if( !settingAquSettingPane.isDisable() ) {
         settingAquSettingPane.setDisable( true );
         new FadeOut( settingAquSettingPane ).play();
      }
      if( !settingGreenHouseSettingPane.isDisable() ) {
         settingGreenHouseSettingPane.setDisable( true );
         aquariumSettingLabel.setVisible( false );
         new FadeOut( settingGreenHouseSettingPane ).play();
      }
      if( !settingWeatherSettingPane.isDisable() ) {
         settingWeatherSettingPane.setDisable( true );
         new FadeOut( settingWeatherSettingPane ).play();
      }
      if( !homeSettingSubPane.isDisable() ) {
         homeSettingSubPane.setDisable( true );
         new FadeOut( homeSettingSubPane ).play();
      }
      homeSettingElecButtonActive.setVisible( false );
      homeSettingGasButtonActive.setVisible( false );
      homeSettingAquButtonActive.setVisible( false );
      homeSettingGreenHouseButtonActive.setVisible( false );
      homeSettingWeatherButtonActive.setVisible( false );
      homeSettingButtonActive.setVisible( false );
   }

}
