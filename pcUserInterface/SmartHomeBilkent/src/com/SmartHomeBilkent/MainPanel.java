package com.SmartHomeBilkent;

import com.SmartHomeBilkent.extra.connection.Arduino;
import com.SmartHomeBilkent.extra.dataBase.*;
import com.SmartHomeBilkent.extra.dataBase.fields.CommonSetting;
import com.SmartHomeBilkent.extra.dataBase.fields.User;
import com.SmartHomeBilkent.extra.speech.SpeechUtils;
import com.SmartHomeBilkent.extra.weather.WeatherForecast;
import com.SmartHomeBilkent.home.Home;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
 * a MainPanel class
 *
 * @author Hacı Çakın
 * @version 29.04.2020
 */
public class MainPanel implements Initializable {

   //properties
   /**
    * PROPERTIES
    * 1)menu variables
    * 2)user profile variables
    * 3)settings variables
    * 3.1)setting - application settings variables
    * 3.2)setting - users settings variables
    * 3.3)setting - mods settings variables
    * 3.4)setting - home settings variables
    * 3.5)setting - sub menu variables
    * 4)Some independent variables( various objects )
    */
   @FXML
   private BorderPane firstStackPane;
   @FXML
   private StackPane menuStackPane;
   @FXML
   private AnchorPane menuAnchorPane;
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
         waterSubMenuButtonActive, waterSubMenuButtonPassive,
         gardenLightSubMenuButtonActive, gardenLightSubMenuButtonPassive,
         menuBulkChange, bulkChangesSaveButton,
         dateTimeSaveButton, menuTimeConfigurationButton,
         helpButton;

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
   private BorderPane menuBorderPane;
   @FXML
   private JFXProgressBar menuElecProgress, menuGasProgress,
         menuAquariumProgress, menuWaterProgress,
         menuGardenLightProgress;
   @FXML
   private Label ghHumidityTempLabel, ghHumidityValueLabel,
         menuHomeTempLabel, ghTempValueLabel, elecSubPaneLabelPassive,
         elecSubPaneOpenLabelPassive, elecSubPaneCloseLabelPassive,
         elecSubPaneOpenValueLabelPassive, elecSubPaneCloseValueLabelPassive,
         elecSubPaneLabelActive, elecSubPaneOpenLabelActive,
         elecSubPaneCloseLabelActive, elecSubPaneOpenValueLabelActive,
         elecSubPaneCloseValueLabelActive, gasSubPaneLabelPassive,
         gasSubPaneLabelActive, aquariumSubPaneLabelPassive,
         heaterSubPaneLabelPassive, aquariumSubPaneLabelActive,
         greenHouseSubPaneLabelPassive, ghTempSubPaneLabelActive,
         menuUserProfileLabel, menuMenuLabel,
         menuSettingLabel, menuWeatherValue,
         menuHomeTempValue, menuOpenDoorLabel,
         waterSubPaneLabelActive, waterSubPaneLabelPassive,
         gardenLightSubPaneLabelActive, gardenLightSubPaneLabelPassive,
         menuBulkChangeSubLabel, timeConfigurationLabel,
         mainMenuHomeLabel, mainMenuWeatherLabel;
   @FXML
   private ImageView weatherImage, tempImage, weatherForecastImage;
   @FXML
   private Pane connectionPane, menuEmptyPane,
         menuElecPane, menuGasPane,
         menuAquariumPane, menuGreenHousePane,
         menuWaterPane, menuGardenLightPane,
         menuBulkChangePane, menuTimeConfigurationPane;
   @FXML
   private JFXToggleButton elecSubMenuToggleButton, gasSubMenuToggleButton,
         aquariumSubMenuToggleButton, waterSubMenuToggleButton,
         gardenLightSubMenuToggleButton;

   /**
    * 2)user profile variables
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
   private AnchorPane userProfileAnchorPane;
   @FXML
   private Pane userProfilePane, changeUserNormalInfoPane,
         changeUserPrivateInfoPane;
   @FXML
   private JFXTextField userProfileNameField, userProfileSurnameField,
         userProfileAgeField, userProfileGenderField,
         userProfileUserNameField, userProfilePasswordField,
         nameTextField, surnameTextField,
         userNameTextField, newPasswordTextField,
         verifyNewPasswordField, currentPasswordField;
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

   /**
    * 3)settings variables
    */
   @FXML
   private AnchorPane settingAnchorPane;
   @FXML
   private AnchorPane settingSubAnchorTop, settingSubAnchorBottom;
   @FXML
   private Pane settingMainFunctionsPane;
   @FXML
   private JFXButton applicationSettingButton, settingsUsersSettingButton,
         modsSettingButton, homeSettingButton,
         applicationSettingButtonActive, settingsUsersSettingButtonActive,
         modsSettingButtonActive, homeSettingButtonActive,
         portConnectionButton;
   @FXML
   private Label applicationSettingButtonLabel, usersSettingButtonLabel,
         modsSettingButtonLabel, homeSettingButtonLabel;

   /**
    * 3.1)setting - application settings variables
    */
   @FXML
   private Pane settingThemePane, settingLanguagePane, settingEmergencyPane, settingNotificationPane, settingConnectionPane;
   @FXML
   private JFXRadioButton darkThemeRadioButton, lightThemeRadioButton,
         smoothThemeRadioButton, cartoonThemeRadioButton;

   @FXML
   private JFXButton englishOption, germanOption,
         turkishOption;
   @FXML
   private ImageView settingThemeDarkImage,
         settingThemeLightImage,
         settingThemeSmoothImage,
         settingThemeCartoonImage;
   @FXML
   private Label englishLabel, germanLabel,
         turkishLabel, themeTopLabel,
         languageTopLabel, emergencyTopLabel,
         notificationTopLabel, connectionTopLabel,
         removeAnUser;
   @FXML
   private ToggleButton internalSirenToggle, externalSirenToggle,
         fireButtonVisualToggle, gasSensorVisualToggle,
         smokeSensorVisualToggle, fireButtonSoundToggle,
         smokeSensorSoundToggle, gasSensorSoundToggle;

   /**
    * 3.2)setting - users settings variables
    */
   @FXML
   private Pane settingUsersPane, addUserPane,
         deleteUserPane, permissionPane;
   @FXML
   private JFXTreeTableView< User > settingUserTable;
   @FXML
   private TreeTableColumn< User, String > settingUserTableName,
         settingUserTableSurname, settingUserTableBirthday,
         settingUserTableGender, settingUserTableUserName,
         settingUserTableTheme, settingUserTableLanguage;

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
         removeUserConfirm, removeUserGoBack,
         usersSettingSubPanePermissionButton, permissionPaneGoBackButton;
   @FXML
   private JFXTextField createUserNameTextField, createUserSurnameTextField,
         createUserUserNameTextField;
   @FXML
   private JFXRadioButton createUserMaleOption, createUserFemaleOption,
         createUserDarkThemeOption, createUserLightThemeOption,
         createUserSmoothThemeOption, createUserCartoonThemeOption,
         createUserEnglishOption, createUserTurkishOption,
         createUserGermanOption, createUserParentOption,
         createUserChildOption, createUserElderOption;
   @FXML
   private JFXPasswordField createUserPasswordField, createUserPasswordVerifyField,
         removeUserTextField;
   @FXML
   private ToggleButton permissionPaneElectricityToggle, permissionPaneWaterToggle,
         permissionPaneGardenLightToggle, permissionPaneAquariumToggle,
         permissionPaneNotificationToggle, permissionPaneGasToggle,
         permissionPaneSirenToggle;

   /**
    * 3.3)setting - mods settings variables
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

   /**
    * 3.4)setting - home settings variables
    */
   @FXML
   private Pane settingElecSettingPane, settingGasSettingPane,
         settingAquSettingPane, settingGreenHouseSettingPane, settingWeatherSettingPane;
   @FXML
   private Label homeElecSettingTopLabel, homeGasSettingTopLabel,
         homeAquSettingTopLabel, homeGreenHouseSettingTopLabel,
         homeWeatherSettingTopLabel, homeSubPaneELecLabel,
         homeSubPaneGasLabel, homeSubPaneAquLabel,
         homeSubPaneGreenHouseLabel, homeSubPaneWeatherLabel,
         themeSubLabel, languageSubLabel,
         emergencySubLabel, notificationSubLabel,
         connectionSubLabel, settingWeatherLocationLabel,
         settingWeatherForecastLabel, settingWeatherTemperatureLabel,
         settingWeatherHumidityLabel, settingWeatherWindLabel,
         informationTime, settingWeatherForecastLabelValue,
         settingWeatherTemperatureLabelValue, settingWeatherHumidityLabelValue,
         settingWeatherWindLabelValue, speciesOfFishLabel,
         feedingStartLabel, waterExchangeLabel,
         airMotorRunTimeLabel, greenHouseTemperatureLabel,
         greenHouseHumidityLabel, latestWaterLabel;
   @FXML
   private AnchorPane applicationSettingSubPane;
   @FXML
   private JFXButton themeSettingButton, languageSettingButton,
         emergencySettingButton, phoneNotificationSettingButton,
         smartHomeConnectionSettingButton, themeSettingButtonActive,
         languageSettingButtonActive, emergencySettingButtonActive,
         phoneNotificationSettingButtonActive, smartHomeConnectionSettingButtonActive,
         saveAquariumChangesButton;
   @FXML
   private JFXTextField settingWeatherLocationTextField;
   @FXML
   private JFXButton updateWeatherButton;
   @FXML
   private JFXToggleButton settingElectricityToggleButton, settingGasToggleButton,
         settingAquariumToggleButton;
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
   /**
    * 3.5)setting - sub menu variables
    */
   @FXML
   private AnchorPane usersSettingSubPane, modsSettingSubPane, homeSettingSubPane;
   @FXML
   private JFXButton usersSettingSubPaneAddUser, usersSettingSubPaneRemoveUser,
         usersSettingSubPaneChangeUserType, homeSettingElecButton,
         homeSettingGasButton, homeSettingAquButton,
         homeSettingGreenHouseButton, homeSettingWeatherButton,
         homeSettingElecButtonActive, homeSettingGasButtonActive,
         homeSettingAquButtonActive, homeSettingGreenHouseButtonActive,
         homeSettingWeatherButtonActive;

   @FXML
   private JFXTimePicker feedingTime, airMotorStartTime,
         waterExchangeTime;

   @FXML
   private JFXComboBox< String > waterExchangeDay;

   @FXML
   private JFXSlider airMotorRunTime;


   /**
    * 4)Some independent variables
    */
   private ResourceBundle bundle;
   private AudioClip audioClip;
   private boolean soundCheck;
   private boolean textCheck;
   private String volume;
   private SpeechUtils speechUtils;
   private LocalDate localDate;
   private LocalTime localTime;
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
   private String flowAquariumSetting;


   //initialize method(it runs before the program start to run)
   @Override
   public void initialize( URL location, ResourceBundle resources ) {
      ElectricityUsage.getInstance().getElectricityUsage();
      ElectricityUsage.getInstance().getTable( electricityUsageTable );
      GasUsage.getInstance().getGasUsage();
      GasUsage.getInstance().getTable( gasUsageTable );
      GreenHouseData.getInstance().getGreenHouseValues();
      GreenHouseData.getInstance().getTable( greenHouseValuesChart );
      FishSpecies.getInstance().getFishes();
      FishSpecies.getInstance().addFishToComboBox( checkComboBox );
      CommonSettingData.getInstance().getAllHome();

      userPreferenceUpdate( getLoginUser() );
      updateUsersTable();
      usersSettingSubPaneRemoveUser.setDisable( true );
      audioClip = new AudioClip( this.getClass().getResource( "music/suprise.mp3" ).toString() );
      audioClip.setVolume( ( ( double ) Integer.parseInt( volume ) ) / 200 );
      audioClip.setRate( 1.1 );
      speechUtils = new SpeechUtils();
      isArduinoConnect = false;

      for( int k = 1; k <= 7; k++ ) {
         waterExchangeDay.getItems().add( k + ". DAY OF WEEK" );
      }

      commonSetting = CommonSettingData.getInstance().getHomeList().get( 0 );
      sensors = CommonSettingData.getInstance().getSensors( commonSetting );
      permissions = CommonSettingData.getInstance().getPermission( commonSetting );

      for( String s : CommonSettingData.getInstance().getSelectedFishes( commonSetting ) )
         checkComboBox.getCheckModel().check( checkComboBox.getItems().indexOf( s ) );

      flowAquariumSetting = CommonSettingData.getInstance().getAquariumSettings( commonSetting );
      feedingTime.setValue( localTime.of( Integer.parseInt( flowAquariumSetting.substring( 0, 2 ) ), Integer.parseInt( flowAquariumSetting.substring( 2, 4 ) ) ) );
      waterExchangeTime.setValue( localTime.of( Integer.parseInt( flowAquariumSetting.substring( 6, 8 ) ), Integer.parseInt( flowAquariumSetting.substring( 8, 10 ) ) ) );
      waterExchangeDay.getSelectionModel().select( Integer.parseInt( flowAquariumSetting.substring( 13, 14 ) ) - 1 );
      airMotorStartTime.setValue( localTime.of( Integer.parseInt( flowAquariumSetting.substring( 14, 16 ) ), Integer.parseInt( flowAquariumSetting.substring( 16, 18 ) ) ) );
      airMotorRunTime.setValue( Integer.parseInt( flowAquariumSetting.substring( 20, 22 ) ) );

      if( loginUser.getUserType().equals( "PARENT" ) ) {
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
      } else if( loginUser.getUserType().equals( "CHILD" ) ) {
         usersSettingSubPanePermissionButton.setVisible( false );
         menuBulkChange.setVisible( false );
         menuTimeConfigurationButton.setVisible( false );
         settingElectricityToggleButton.setDisable( permissions[ 0 ].charAt( 0 ) == 'C' );
         elecSubMenuToggleButton.setDisable( permissions[ 0 ].charAt( 0 ) == 'C' );
         gasSubMenuToggleButton.setDisable( permissions[ 1 ].charAt( 0 ) == 'C' );
         settingGasToggleButton.setDisable( permissions[ 1 ].charAt( 0 ) == 'C' );
         waterSubMenuToggleButton.setDisable( permissions[ 2 ].charAt( 0 ) == 'C' );
         gardenLightSubMenuToggleButton.setDisable( permissions[ 3 ].charAt( 0 ) == 'C' );
         aquariumSubMenuToggleButton.setDisable( permissions[ 4 ].charAt( 0 ) == 'C' );
         settingAquariumToggleButton.setDisable( permissions[ 4 ].charAt( 0 ) == 'C' );
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
         menuWeatherValue.setText( weatherForecast.getWeather() + " " + weatherForecast.getTemperature() + "°C" );
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

      //example//speechUtils.SpeakText("Hello, today weather is partly cloudy and, temperature is ,8, celsius degree",true);
      refreshMenu();
      createEmergencyAnimation();
   }

   void sound( String file, Boolean check ) {
      if( ( check && !audioClip.getSource().substring( audioClip.getSource().indexOf( "com/SmartHomeBilkent/" ) + 30, ( audioClip.getSource().length() - 6 ) ).equals( file ) ) ) {
         audioClip.stop();
         audioClip = new AudioClip( this.getClass().getResource( "music/" +
               bundle.getString( "pathLang" ) +
               file + bundle.getString( "mp3Lang" ) ).toString() );
         audioClip.setRate( 1 );
         audioClip.setVolume( ( ( double ) Integer.parseInt( volume ) ) / 200 );
         audioClip.play();
      } else {
         audioClip.stop();
         audioClip = new AudioClip( this.getClass().getResource( "music/" +
               bundle.getString( "pathLang" ) +
               file + bundle.getString( "mp3Lang" ) ).toString() );
      }
   }

   public User getLoginUser() {
      for( User u : Users.getInstance().getUserList() ) {
         if( u.getEnter().equals( "true" ) ) {
            u.setEnter( "false" );
            loginUser = u;
            return u;
         }
      }
      return null;
   }

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

      if( user.getGender().equals( "MALE" ) || user.getGender().equals( "ERKEK" ) || user.getGender().equals( "MÄNNLICH" ) ) {
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

      if( loginUser.getPreferredTheme().equals( "cartoon" ) || loginUser.getPreferredTheme().equals( "karıkatur" )
            || loginUser.getPreferredTheme().equals( "çızgı fılm" ) ) {
         changeTheme( "cartoon" );
      } else if( loginUser.getPreferredTheme().equals( "light" ) || loginUser.getPreferredTheme().equals( "lıght" )
            || loginUser.getPreferredTheme().equals( "aydınlık" ) || loginUser.getPreferredTheme().equals( "lıcht" ) ) {
         changeTheme( "light" );
      } else if( loginUser.getPreferredTheme().equals( "smooth" ) || loginUser.getPreferredTheme().equals( "puruzsuz" )
            || loginUser.getPreferredTheme().equals( "glatt" ) ) {
         changeTheme( "smooth" );
      } else {
         changeTheme( "dark" );
      }

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
      }

      if( loginUser.getSound().substring( 0, loginUser.getSound().length() - 3 ).equals( "false" ) ) {
         soundCheck = false;
         soundControl( false );
      } else if( loginUser.getSound().substring( 0, loginUser.getSound().length() - 3 ).equals( "true" ) ) {
         soundCheck = true;
         soundControl( true );
         soundModeToggle.setSelected( true );
      }

      textCheck = !loginUser.getText().equals( "false" );
      textModeToggle.setSelected( loginUser.getText().equals( "true" ) );
      volume = ( loginUser.getSound().substring( loginUser.getSound().length() - 3 ) );
      soundVolumeSlider.setValue( Double.parseDouble( volume ) );
   }

   void refreshMenu() {
      SerialPort[] portNames;
      portChooser.getItems().removeAll( portChooser.getItems() );
      portNames = SerialPort.getCommPorts();

      for( int k = 0; k < portNames.length; k++ )
         portChooser.getItems().add( portNames[ k ].getSystemPortName() );
   }

   void createEmergencyAnimation() {
      rectangle = new Rectangle( 0, 0, 800, 800 );
      rectangle.setDisable( true );
      fillTransition = new FillTransition( Duration.seconds( 0.5 ), rectangle, Color.rgb( 255, 0, 0, 0 ), Color.rgb( 255, 0, 0, 0.6 ) );
      fillTransition.setCycleCount( 20 );
      fillTransition.setAutoReverse( true );
      firstStackPane.getChildren().add( rectangle );
      rectangle.setVisible( false );
   }


   void updateUsersTable() {
      //initialize column
      settingUserTableName.setCellValueFactory( param -> param.getValue().getValue().nameProperty() );
      settingUserTableSurname.setCellValueFactory( param -> param.getValue().getValue().surnameProperty() );
      settingUserTableBirthday.setCellValueFactory( param -> param.getValue().getValue().birthdayProperty() );
      settingUserTableGender.setCellValueFactory( param -> param.getValue().getValue().genderProperty() );
      settingUserTableUserName.setCellValueFactory( param -> param.getValue().getValue().userNameProperty() );
      settingUserTableTheme.setCellValueFactory( param -> param.getValue().getValue().preferredThemeProperty() );
      settingUserTableLanguage.setCellValueFactory( param -> param.getValue().getValue().preferredLanguageProperty() );
      settingUserTable.setRoot( new RecursiveTreeItem<>( Users.getInstance().getUserList()
            , RecursiveTreeObject::getChildren ) );
      settingUserTable.setShowRoot( false );
   }

   //these are helps to switch between three main pane(user profile, menu, setting)
   @FXML
   void commonButtonsOnAction( ActionEvent event ) {
      if( event.getSource() == userProfileButton ) {
         openUserProfilePane();
      } else if( event.getSource() == menuButton ) {
         openMenuPane();
      } else if( event.getSource() == settingsButton ) {
         openSettingsPane();
      } else if( event.getSource() == logoutButton ) {
         Platform.exit();
         if( isArduinoConnect )
            home.getArduino().closeConnection();
      } else if( event.getSource() == helpButton ) {
         try {
            FXMLLoader load = new FXMLLoader( getClass().getResource( "view/helpPanel.fxml" ) );
            Parent root = load.load();
            Stage stage = new Stage();
            stage.setTitle( "HELP" );
            stage.setScene( new Scene( root, 600, 600 ) );
            stage.setResizable( false );
            stage.show();
         } catch( Exception e ) {
            e.printStackTrace();
         }
      }
   }

   @FXML
   void commonButtonsOnMovement( MouseEvent event ) {
      if( event.getSource() == userProfileButton || event.getSource() == userProfileButtonActive ) {
         menuUserProfileLabel.setVisible( textCheck );
         sound( "userProfileLang", soundCheck );
      } else if( event.getSource() == menuButton || event.getSource() == menuButtonActive ) {
         menuMenuLabel.setVisible( textCheck );
         sound( "menuLang", soundCheck );
      } else if( event.getSource() == settingsButton || event.getSource() == settingsButtonActive ) {
         menuSettingLabel.setVisible( textCheck );
         sound( "settingLang", soundCheck );
      } else if( event.getSource() == logoutButton ) {
         sound( "closeAppLang", soundCheck );
      } else if( event.getSource() == tempImage ) {
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
      } else if( event.getSource() == elecSubMenuButtonPassive || event.getSource() == elecSubMenuButtonActive ) {
         sound( "elecSettingsLang", soundCheck );
      } else if( event.getSource() == gasSubMenuButtonPassive || event.getSource() == gasSubMenuButtonActive ) {
         sound( "gasSettingsLang", soundCheck );
      } else if( event.getSource() == aquariumSubMenuButtonPassive || event.getSource() == aquariumSubMenuButtonActive ) {
         sound( "aquSettingsLang", soundCheck );
      } else if( event.getSource() == greenhouseSubMenuButtonPassive || event.getSource() == greenhouseSubMenuButtonActive ) {
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

   @FXML
   void commonButtonsOnExit( MouseEvent event ) {
      if( event.getSource() == userProfileButton || event.getSource() == userProfileButtonActive ) {
         menuUserProfileLabel.setVisible( false );
      } else if( event.getSource() == menuButton || event.getSource() == menuButtonActive ) {
         menuMenuLabel.setVisible( false );
      } else if( event.getSource() == settingsButton || event.getSource() == settingsButtonActive ) {
         menuSettingLabel.setVisible( false );
      } else if( event.getSource() == doorButton ) {
         menuOpenDoorLabel.setVisible( false );
      }
      sound( "saveChangesLang", false );
   }

   //it opens menu
   void openMenuPane() {
      menuButtonActive.setVisible( true );
      menuStackPane.setVisible( true );
      menuStackPane.setDisable( false );

      FadeTransition fadeTransition = new FadeTransition();
      fadeTransition.setDuration( Duration.seconds( 0.5 ) );
      fadeTransition.setNode( menuStackPane );
      fadeTransition.setFromValue( 0 );
      fadeTransition.setToValue( 0.95 );
      fadeTransition.play();

      closeUserProfilePane();
      closeSettingsPane();
   }

   //it closes menu
   void closeMenuPane() {
      if( menuButtonActive.isVisible() ) {
         FadeTransition fadeTransition = new FadeTransition();
         fadeTransition.setDuration( Duration.seconds( 0.5 ) );
         fadeTransition.setNode( menuStackPane );
         fadeTransition.setFromValue( menuStackPane.getOpacity() );
         fadeTransition.setToValue( 0 );
         fadeTransition.play();
      }

      menuButtonActive.setVisible( false );
      menuStackPane.setVisible( false );
      menuStackPane.setDisable( true );
   }

   //it opens setting
   void openSettingsPane() {
      settingsButtonActive.setVisible( true );
      settingAnchorPane.setVisible( true );
      settingAnchorPane.setDisable( false );
      openApplicationPanes();
      FadeTransition fadeTransition = new FadeTransition();
      fadeTransition.setDuration( Duration.seconds( 0.5 ) );
      fadeTransition.setNode( settingAnchorPane );
      fadeTransition.setFromValue( 0 );
      fadeTransition.setToValue( 0.95 );
      fadeTransition.play();

      closeUserProfilePane();
      closeMenuPane();
   }

   //it closes setting
   void closeSettingsPane() {
      if( settingsButtonActive.isVisible() ) {
         FadeTransition fadeTransition = new FadeTransition();
         fadeTransition.setDuration( Duration.seconds( 0.5 ) );
         fadeTransition.setNode( settingAnchorPane );
         fadeTransition.setFromValue( settingAnchorPane.getOpacity() );
         fadeTransition.setToValue( 0 );
         fadeTransition.play();
      }

      settingsButtonActive.setVisible( false );
      settingAnchorPane.setVisible( false );
      settingAnchorPane.setDisable( true );
      closeApplicationSettingPane();
      closeAllHomeSetting();
      closeAllUsersPane();
      closeModsPane();
   }

   //it opens user profile
   void openUserProfilePane() {
      userProfileButtonActive.setVisible( true );
      userProfileStackPane.setVisible( true );
      userProfileStackPane.setDisable( false );

      FadeTransition fadeTransition = new FadeTransition();
      fadeTransition.setDuration( Duration.seconds( 0.5 ) );
      fadeTransition.setNode( userProfileStackPane );
      fadeTransition.setFromValue( 0 );
      fadeTransition.setToValue( 0.95 );
      fadeTransition.play();

      closeSettingsPane();
      closeMenuPane();
   }

   //it closes user profile
   void closeUserProfilePane() {
      if( userProfileButton.isVisible() ) {
         FadeTransition fadeTransition = new FadeTransition();
         fadeTransition.setDuration( Duration.seconds( 0.5 ) );
         fadeTransition.setNode( userProfileStackPane );
         fadeTransition.setFromValue( userProfileStackPane.getOpacity() );
         fadeTransition.setToValue( 0 );
         fadeTransition.play();
      }

      userProfileButtonActive.setVisible( false );
      userProfileStackPane.setVisible( false );
      userProfileStackPane.setDisable( true );

      toGoBackUserProfile();
   }

   //main pane

   //this opens related facility's settings pane
   @FXML
   void goToSettingFromMenuView( ActionEvent event ) {
      if( event.getSource() == elecSubMenuButtonPassive || event.getSource() == elecSubMenuButtonActive ) {
         closeMenuPane();
         openSettingsPane();
         closeApplicationSettingPane();
         openHomeSettingPane();
         settingElecSettingPane.setVisible( true );
         homeSettingElecButtonActive.setVisible( true );
      } else if( event.getSource() == gasSubMenuButtonPassive || event.getSource() == gasSubMenuButtonActive ) {
         closeMenuPane();
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingGasSettingPane.setVisible( true );
         homeSettingGasButtonActive.setVisible( true );
      } else if( event.getSource() == aquariumSubMenuButtonPassive || event.getSource() == aquariumSubMenuButtonActive ) {
         closeMenuPane();
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingAquSettingPane.setVisible( true );
         homeSettingAquButtonActive.setVisible( true );
      } else if( event.getSource() == greenhouseSubMenuButtonPassive || event.getSource() == aquariumSubMenuButtonActive ) {
         closeMenuPane();
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingGreenHouseSettingPane.setVisible( true );
         homeSettingGreenHouseButtonActive.setVisible( true );
      } else if( event.getSource() == weatherButton ) {
         closeMenuPane();
         openSettingsPane();
         closeApplicationSettingPane();
         closeAllHomeSetting();
         openHomeSettingPane();
         settingWeatherSettingPane.setVisible( true );
         homeSettingWeatherButtonActive.setVisible( true );
      } else if( event.getSource() == menuConnectionButton ) {
         closeMenuPane();
         openSettingsPane();
         closeAllApplicationSettingSubPanes();
         openSmartHomeConnectionSetting();
         connectionSubLabel.setVisible( textCheck );
      }
   }

   //this controls which pane will be visible by fallowing buttons
   @FXML
   void menuPaneButtonsOnAction( ActionEvent event ) {
      if( event.getSource() == menuElecButton ) {
         closeAllMenuPane();
         menuElecPane.setVisible( true );
      } else if( event.getSource() == menuGasButton ) {
         closeAllMenuPane();
         menuGasPane.setVisible( true );
      } else if( event.getSource() == menuAquariumButton ) {
         closeAllMenuPane();
         menuAquariumPane.setVisible( true );
      } else if( event.getSource() == menuGreenHouseButton ) {
         closeAllMenuPane();
         menuGreenHousePane.setVisible( true );
      } else if( event.getSource() == menuWaterButton ) {
         closeAllMenuPane();
         menuWaterPane.setVisible( true );
      } else if( event.getSource() == menuGardenLightButton ) {
         closeAllMenuPane();
         menuGardenLightPane.setVisible( true );
      } else if( event.getSource() == doorButton ) {
         if( isArduinoConnect )
            home.getDoor().open( true );
      } else if( event.getSource() == menuBulkChange ) {
         closeAllMenuPane();
         menuBulkChangePane.setVisible( true );
      } else if( event.getSource() == menuTimeConfigurationButton ) {
         closeAllMenuPane();
         menuTimeConfigurationPane.setVisible( true );
      } else if( event.getSource() == bulkChangesSaveButton ) {
         StringBuilder message;
         message = new StringBuilder();
         message.append( "Smart_App#" );

         if( sirenRadioButton.isSelected() )
            message.append( "B1" );
         else
            message.append( "B0" );

         if( electricityRadioButton.isSelected() )
            message.append( "E1" );
         else
            message.append( "E0" );

         if( gasRadioButton.isSelected() )
            message.append( "G1" );
         else
            message.append( "G0" );

         if( incomingWaterRadioButton.isSelected() )
            message.append( "I1" );
         else
            message.append( "I0" );

         if( outgoingWaterRadioButton.isSelected() )
            message.append( "U1" );
         else
            message.append( "U0" );

         if( aquariumRadioButton.isSelected() )
            message.append( "A1" );
         else
            message.append( "A0" );

         if( doorRadioButton.isSelected() )
            message.append( "D1" );
         else
            message.append( "D0" );

         if( waterRadioButton.isSelected() )
            message.append( "W1" );
         else
            message.append( "W0" );

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

         message.append( ":" );
         if( isArduinoConnect )
            home.adjustCollective( message.toString() );

         openGardenLight( gardenLightRadioButton.isSelected() );
         openWater( waterRadioButton.isSelected() );
         openAquarium( aquariumRadioButton.isSelected() );
         openGas( gasRadioButton.isSelected() );
         openElectricity( electricityRadioButton.isSelected() );

      } else if( event.getSource() == dateTimeSaveButton ) {
         if( menuDatePicker.getValue() == null ||
               menuTimePicker.getValue() == null ) {
         } else {
            StringBuilder message;
            message = new StringBuilder();

            if( menuTimePicker.getValue().getHour() < 10 )
               message.append( "clock#0" + menuTimePicker.getValue().getHour() );
            else
               message.append( "clock#" + menuTimePicker.getValue().getHour() );

            if( menuTimePicker.getValue().getMinute() < 10 )
               message.append( "0" + menuTimePicker.getValue().getMinute() );
            else
               message.append( menuTimePicker.getValue().getMinute() );

            message.append( "000" + menuDatePicker.getValue().getDayOfWeek().getValue() );

            if( menuDatePicker.getValue().getDayOfMonth() < 10 )
               message.append( "0" + menuDatePicker.getValue().getDayOfMonth() );
            else
               message.append( menuDatePicker.getValue().getDayOfMonth() );
            if( menuDatePicker.getValue().getMonthValue() < 10 )
               message.append( "0" + menuDatePicker.getValue().getMonthValue() );
            else
               message.append( menuDatePicker.getValue().getMonthValue() );
            message.append( menuDatePicker.getValue().getYear() + ":" );

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
      }
   }

   void closeAllMenuPane() {
      menuElecPane.setVisible( false );
      menuGasPane.setVisible( false );
      menuAquariumPane.setVisible( false );
      menuGreenHousePane.setVisible( false );
      menuWaterPane.setVisible( false );
      menuGardenLightPane.setVisible( false );
      menuBulkChangePane.setVisible( false );
      menuTimeConfigurationPane.setVisible( false );
   }

   //it controls whether facilities are opened or closed
   @FXML
   void menuPaneToggleButtonOnAction( ActionEvent event ) {
      if( event.getSource() == elecSubMenuToggleButton ) {
         openElectricity( elecSubMenuToggleButton.isSelected() );
         if( isArduinoConnect )
            home.getElectricity().open( elecSubMenuToggleButton.isSelected() );
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

   public void openGas( boolean control ) {
      gasSubPaneLabelActive.setVisible( control );
      gasSubMenuButtonActive.setVisible( control );
      menuGasProgress.setVisible( control );
      gasSubMenuToggleButton.setSelected( control );
      gasRadioButton.setSelected( control );
      settingGasToggleButton.setSelected( control );
   }

   public void openAquarium( boolean control ) {
      aquariumSubPaneLabelActive.setVisible( control );
      aquariumSubMenuButtonActive.setVisible( control );
      menuAquariumProgress.setVisible( control );
      aquariumSubMenuToggleButton.setSelected( control );
      aquariumRadioButton.setSelected( control );
      settingAquariumToggleButton.setSelected( control );
   }

   public void openWater( boolean control ) {
      waterSubPaneLabelActive.setVisible( control );
      waterSubMenuButtonActive.setVisible( control );
      menuWaterProgress.setVisible( control );
      waterSubMenuToggleButton.setSelected( control );
      waterRadioButton.setSelected( control );
   }

   public void openGardenLight( boolean control ) {
      gardenLightSubPaneLabelActive.setVisible( control );
      gardenLightSubMenuButtonActive.setVisible( control );
      menuGardenLightProgress.setVisible( control );
      gardenLightSubMenuToggleButton.setSelected( control );
      gardenLightRadioButton.setSelected( control );
   }

   //user profiles main buttons (normal info change button, priv info change button, user changer button)
   @FXML
   void userProfileButtonsOnAction( ActionEvent event ) throws SQLException {
      if( event.getSource() == changeUserNormalInfoButton ) {
         userProfilePane.setEffect( new BoxBlur( 10, 3, 3 ) );
         changeUserNormalInfoPane.setVisible( true );
         userProfilePane.setDisable( true );
      } else if( event.getSource() == changeUserPrivateInfoButton ) {
         userProfilePane.setEffect( new BoxBlur( 10, 3, 3 ) );
         changeUserPrivateInfoPane.setVisible( true );
         userProfilePane.setDisable( true );
      } else if( event.getSource() == userChangerButton ) {
         try {
            FXMLLoader load = new FXMLLoader( getClass().getResource( "view/loginPanel.fxml" ) );
            Parent root = load.load();
            Stage stage = new Stage();
            stage.setTitle( "SMART HOME" );
            stage.setScene( new Scene( root, 400, 400 ) );
            stage.setResizable( false );
            stage.show();
            firstStackPane.getScene().getWindow().hide();
            if( isArduinoConnect )
               arduino.closeConnection();
         } catch( Exception e ) {
            e.printStackTrace();
         }
      } else if( event.getSource() == saveUserNormalInfo ) {
         String gender;

         if( nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || birthdayDateField.getValue() == null ) {
            normalInfoWarning.setVisible( true );
         } else {

            if( maleRadioOption.isSelected() ) {
               gender = "MALE";
            } else {
               gender = "FEMALE";
            }

            Users.getInstance().updateUserNormalInfo( loginUser, nameTextField.getText()
                  , surnameTextField.getText(), birthdayDateField.getValue().format( DateTimeFormatter.ofPattern( "dd.MM.yyyy" ) )
                  , gender );
            userPreferenceUpdate( loginUser );
            updateUsersTable();
            toGoBackUserProfile();
         }
      } else if( event.getSource() == saveUserPrivateInfo ) {

         if( userNameTextField.getText().length() > 0 && currentPasswordField.getText().length() > 0 && newPasswordTextField.getText().length() > 0 && verifyNewPasswordField.getText().length() > 0 ) {

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
         if( maleRadioOption.isSelected() ) {
            femaleRadioOption.setSelected( false );
         } else {
            femaleRadioOption.setSelected( true );
         }
      } else if( event.getSource() == femaleRadioOption ) {
         if( femaleRadioOption.isSelected() ) {
            maleRadioOption.setSelected( false );
         } else {
            maleRadioOption.setSelected( true );
         }
      }
   }

   //user profiles main buttons' helper labels animation
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
      } else if( event.getSource() == saveUserNormalInfo || event.getSource() == saveUserPrivateInfo ) {
         sound( "saveChangesLang", soundCheck );
      } else if( event.getSource() == backToUserProfileFromNormalInfo || event.getSource() == backToUserProfileFromPrivateInfo ) {
         sound( "goBackLang", soundCheck );
      }
   }

   @FXML
   void userProfileButtonsOnExit( MouseEvent event ) {
      if( event.getSource() == userChangerButton )
         userChangerInfoLabel.setVisible( false );
      else if( event.getSource() == changeUserNormalInfoButton )
         changeUserInfoLabel.setVisible( false );
      else if( event.getSource() == changeUserPrivateInfoButton )
         changeUserPrivateInfoLabel.setVisible( false );

      sound( "gasLang", false );
   }

   //user profiles user info changer panels
   public void toGoBackUserProfile() {
      userProfilePane.setEffect( new BoxBlur( 0, 0, 0 ) );
      userProfilePane.setDisable( false );
      changeUserNormalInfoPane.setVisible( false );
      changeUserPrivateInfoPane.setVisible( false );
   }


   //settings
   //settings ---------top pane
   @FXML
   void settingsFacilitiesButtons( ActionEvent event ) throws SQLException, IOException {
      if( event.getSource() == applicationSettingButton ) {
         openApplicationPanes();
      } else if( event.getSource() == settingsUsersSettingButton ) {
         openUsersPane();
      } else if( event.getSource() == modsSettingButton ) {
         openModsPane();
      } else if( event.getSource() == homeSettingButton ) {
         openHomeSettingPane();
         settingElecSettingPane.setVisible( true );
         homeSettingElecButtonActive.setVisible( true );
      }
   }


   //all settings buttons ( app settings(theme-language-emergency-notification-connection), users settings( add-remove user), home settings())
   @FXML
   void settingsFacilitiesButtonsMov( MouseEvent event ) {
      if( event.getSource() == applicationSettingButton || event.getSource() == applicationSettingButtonActive ) {
         applicationSettingButtonLabel.setVisible( textCheck );
         sound( "applicationSettingLang", soundCheck );
      } else if( event.getSource() == settingsUsersSettingButton || event.getSource() == settingsUsersSettingButtonActive ) {
         usersSettingButtonLabel.setVisible( textCheck );
         sound( "usersSettingLang", soundCheck );
      } else if( event.getSource() == modsSettingButton || event.getSource() == modsSettingButtonActive ) {
         modsSettingButtonLabel.setVisible( textCheck );
         sound( "modsSettingLang", soundCheck );
      } else if( event.getSource() == homeSettingButton || event.getSource() == homeSettingButtonActive ) {
         homeSettingButtonLabel.setVisible( textCheck );
         sound( "homeLang", soundCheck );
      }
   }

   @FXML
   void settingsFacilitiesButtonsMovEx( MouseEvent event ) {
      if( event.getSource() == applicationSettingButton || event.getSource() == applicationSettingButtonActive )
         applicationSettingButtonLabel.setVisible( false );
      else if( event.getSource() == settingsUsersSettingButton || event.getSource() == settingsUsersSettingButtonActive )
         usersSettingButtonLabel.setVisible( false );
      else if( event.getSource() == modsSettingButton || event.getSource() == modsSettingButtonActive )
         modsSettingButtonLabel.setVisible( false );
      else if( event.getSource() == homeSettingButton || event.getSource() == homeSettingButtonActive )
         homeSettingButtonLabel.setVisible( false );
      sound( "gasLang", false );
   }

   //settings -----application settings
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
         openPhoneNotificationSetting();
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
                  if( serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE )
                     return;
                  home.getArduino().getSerialPort().setComPortTimeouts( SerialPort.TIMEOUT_NONBLOCKING, 0, 0 );
                  String out = "";
                  Scanner in = new Scanner( home.getArduino().getSerialPort().getInputStream() );

                  try {
                     while( in.hasNextLine() )
                        out = out + in.nextLine();
                  } catch( Exception e ) {
                     e.printStackTrace();
                  }
                  out = out.replaceAll( "\\s", "" );
                  out = out.replace( "\n", "" ).replace( "\r", "" );

                  if( !out.isEmpty() ) {
                     System.out.println( out );
                     if( out.equals( "FireButon" )
                           || out.equals( "SmokeAlarm" )
                           || out.equals( "GasAlarm" )
                           || out.equals( "Gas+Fire" )
                           || out.equals( "Gas+Smokealarm" )
                           || out.equals( "Gas+Smoke" )
                           || out.equals( "Gas+Smoke+Fire" ) ) {
                        if( fillTransition.getCurrentRate() == 0.0d ) {
                           fillTransition.play();
                           rectangle.setVisible( true );
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
            || event.getSource() == phoneNotificationSettingButtonActive ) {
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
      sound( "gasLang", false );
   }

   void openThemeSetting() {
      themeSettingButtonActive.setVisible( true );
      settingThemePane.setVisible( true );
   }

   void openLanguageSetting() {
      languageSettingButtonActive.setVisible( true );
      settingLanguagePane.setVisible( true );
   }

   void openEmergencySetting() {
      emergencySettingButtonActive.setVisible( true );
      settingEmergencyPane.setVisible( true );
   }

   void openPhoneNotificationSetting() {
      phoneNotificationSettingButtonActive.setVisible( true );
      settingNotificationPane.setVisible( true );
   }


   void openSmartHomeConnectionSetting() {
      smartHomeConnectionSettingButtonActive.setVisible( true );
      settingConnectionPane.setVisible( true );
      refreshMenu();
   }

   void closeAllApplicationSettingSubPanes() {
      themeSettingButtonActive.setVisible( false );
      settingThemePane.setVisible( false );
      languageSettingButtonActive.setVisible( false );
      settingLanguagePane.setVisible( false );
      emergencySettingButtonActive.setVisible( false );
      settingEmergencyPane.setVisible( false );
      phoneNotificationSettingButtonActive.setVisible( false );
      settingNotificationPane.setVisible( false );
      smartHomeConnectionSettingButtonActive.setVisible( false );
      settingConnectionPane.setVisible( false );
      themeSubLabel.setVisible( false );
      languageSubLabel.setVisible( false );
      emergencySubLabel.setVisible( false );
      notificationSubLabel.setVisible( false );
      connectionSubLabel.setVisible( false );
   }

   void closeApplicationSettingPane() {
      applicationSettingButtonActive.setVisible( false );
      applicationSettingSubPane.setVisible( false );
      closeAllApplicationSettingSubPanes();
      themeSubLabel.setVisible( textCheck );
   }

   void openApplicationPanes() {
      applicationSettingButtonActive.setVisible( true );
      applicationSettingSubPane.setVisible( true );
      openThemeSetting();
      closeAllUsersPane();
      closeModsPane();
      closeHomeSettingPane();
   }

   //settings ---------view pane----theme pane

   @FXML
   void themePaneButtonsOnAction( ActionEvent event ) throws SQLException {
      unSelectAllTheme();
      ( ( JFXRadioButton ) event.getSource() ).setSelected( true );
      saveTheme();
   }

   public void unSelectAllTheme() {
      cartoonThemeRadioButton.setSelected( false );
      lightThemeRadioButton.setSelected( false );
      smoothThemeRadioButton.setSelected( false );
      darkThemeRadioButton.setSelected( false );

      settingThemeDarkImage.setVisible( false );
      settingThemeLightImage.setVisible( false );
      settingThemeSmoothImage.setVisible( false );
      settingThemeCartoonImage.setVisible( false );
   }

   public void saveTheme() throws SQLException {
      String themeName;
      themeName = "";
      if( darkThemeRadioButton.isSelected() )
         themeName = "dark";
      else if( lightThemeRadioButton.isSelected() )
         themeName = "light";
      else if( smoothThemeRadioButton.isSelected() )
         themeName = "smooth";
      else if( cartoonThemeRadioButton.isSelected() )
         themeName = "cartoon";

      changeTheme( themeName );
      Users.getInstance().updateUsersTheme( loginUser, themeName );
      userPreferenceUpdate( loginUser );
   }

   void changeTheme( String themeName ) {
      String css;
      unSelectAllTheme();

      if( themeName.equals( "light" ) || themeName.equals( "aydınlık" ) || themeName.equals( "licht" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_light_theme.css" ).toExternalForm();
         lightThemeRadioButton.setSelected( true );
         settingThemeLightImage.setVisible( true );
      } else if( themeName.equals( "dark" ) || themeName.equals( "gece" ) || themeName.equals( "dunkel" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_dark_theme.css" ).toExternalForm();
         darkThemeRadioButton.setSelected( true );
         settingThemeDarkImage.setVisible( true );
      } else if( themeName.equals( "smooth" ) || themeName.equals( "pürüzsüz" ) || themeName.equals( "glatt" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_smooth_themee.css" ).toExternalForm();
         smoothThemeRadioButton.setSelected( true );
         settingThemeSmoothImage.setVisible( true );
      } else if( themeName.equals( "cartoon" ) || themeName.equals( "çizgi film" ) || themeName.equals( "karikatur" ) ) {
         css = this.getClass().getResource( "styleSheets/main_menu_cartoon_theme.css" ).toExternalForm();
         cartoonThemeRadioButton.setSelected( true );
         settingThemeCartoonImage.setVisible( true );
      } else
         css = "";

      try {
         firstStackPane.getStylesheets().removeAll( firstStackPane.getStylesheets() );
         firstStackPane.getStylesheets().add( css );
      } catch( Exception e ) {
         e.printStackTrace();
      }
   }

   //settings ----language pane

   //language pane buttons

   @FXML
   void languagePaneButtonsOnAction( ActionEvent event ) throws SQLException, IOException {
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
      }

      Users.getInstance().updateLanguage( loginUser, language );
      userPreferenceUpdate( loginUser );
   }

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
      } catch( Exception e ) {
         e.printStackTrace();
      }
   }

   void unSelectAllLanguage() {
      englishOption.setPrefSize( 90, 90 );
      englishOption.setLayoutX( 110 );
      englishOption.setLayoutY( 105 );
      germanOption.setPrefSize( 90, 90 );
      germanOption.setLayoutX( 304 );
      germanOption.setLayoutY( 105 );
      turkishOption.setPrefSize( 90, 90 );
      turkishOption.setLayoutX( 501 );
      turkishOption.setLayoutY( 105 );
   }

   void selectEnglishOption() {
      englishOption.setPrefSize( 150, 150 );
      englishOption.setLayoutX( 80 );
      englishOption.setLayoutY( 105 );
   }

   void selectGermanOption() {
      germanOption.setPrefSize( 150, 150 );
      germanOption.setLayoutX( 274 );
      germanOption.setLayoutY( 105 );
   }

   void selectTurkishOption() {
      turkishOption.setPrefSize( 150, 150 );
      turkishOption.setLayoutX( 471 );
      turkishOption.setLayoutY( 105 );
   }

   @FXML
   void languagePaneButtonsOnMovement( MouseEvent event ) {
      if( event.getSource() == englishOption )
         englishLabel.setVisible( textCheck );
      else if( event.getSource() == germanOption )
         germanLabel.setVisible( textCheck );
      else if( event.getSource() == turkishOption )
         turkishLabel.setVisible( textCheck );
   }

   @FXML
   void languagePaneButtonsOnExit( MouseEvent event ) {
      if( event.getSource() == englishOption )
         englishLabel.setVisible( false );
      else if( event.getSource() == germanOption )
         germanLabel.setVisible( false );
      else if( event.getSource() == turkishOption )
         turkishLabel.setVisible( false );
      sound( "gasLang", false );
   }

   //settings----users settings menu

   void closeAllUsersPane() {
      usersSettingSubPane.setVisible( false );
      settingUsersPane.setVisible( false );
      settingsUsersSettingButtonActive.setVisible( false );
      addUserPane.setVisible( false );
      deleteUserPane.setVisible( false );
      permissionPane.setVisible( false );
   }

   void openUsersPane() {
      settingUsersPane.setVisible( true );
      usersSettingSubPane.setVisible( true );
      settingsUsersSettingButtonActive.setVisible( true );
      closeApplicationSettingPane();
      closeModsPane();
      closeHomeSettingPane();
   }

   @FXML
   void usersSettingsPaneButtonsOnAction( ActionEvent event ) {
      if( event.getSource() == usersSettingSubPaneAddUser ) {
         addUserPane.setVisible( true );
         deleteUserPane.setVisible( false );
         permissionPane.setVisible( false );
         sound( "createUserLang", soundCheck );
      } else if( event.getSource() == usersSettingSubPaneRemoveUser ) {
         deleteUserPane.setVisible( true );
         addUserPane.setVisible( false );
         permissionPane.setVisible( false );
         sound( "removeAnUserLang", soundCheck );
      } else if( event.getSource() == usersSettingSubPanePermissionButton ) {
         deleteUserPane.setVisible( false );
         addUserPane.setVisible( false );
         permissionPane.setVisible( true );
         //sound
      }
   }

   @FXML
   void settingUserTableMov() {
      if( settingUserTable.getSelectionModel().isEmpty() )
         usersSettingSubPaneRemoveUser.setDisable( true );
      else if( settingUserTable.getSelectionModel().getSelectedItem().getValue().getUserType().equals( "PARENT" )
            && !( Users.getInstance().getParentNumber() > 1 ) )
         usersSettingSubPaneRemoveUser.setDisable( true );
      else
         usersSettingSubPaneRemoveUser.setDisable( false );
   }

   //settings ---------sub view pane----users settings menu---- add new user pane
   @FXML
   void createUserControlSelections( ActionEvent event ) {

   }

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
               && !createUserSmoothThemeOption.isSelected()
               && !createUserCartoonThemeOption.isSelected() )
               || ( !createUserEnglishOption.isSelected()
               && !createUserGermanOption.isSelected()
               && !createUserTurkishOption.isSelected() )
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
                  addUserGender.getText(),
                  createUserUserNameTextField.getText(),
                  createUserPasswordField.getText(),
                  addUserType.getText(),
                  addUserTheme.getText().toLowerCase(),
                  addUserLanguage.getText(),
                  "false",
                  "true",
                  "true050",
                  "Ankara"
            ) );
            updateUsersTable();
            addUserPane.setVisible( false );
            clearAddPane();

            if( ( Users.getInstance().getParentNumber() > 1 ) ) {
               usersSettingSubPaneRemoveUser.setDisable( false );
            }
         }

      } else if( event.getSource() == createUserGoBack ) {
         closeAllUsersPane();
         openUsersPane();
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
            || event.getSource() == createUserCartoonThemeOption ) {
         createUserDarkThemeOption.setSelected( false );
         createUserLightThemeOption.setSelected( false );
         createUserSmoothThemeOption.setSelected( false );
         createUserCartoonThemeOption.setSelected( false );
         ( ( JFXRadioButton ) ( event.getSource() ) ).setSelected( true );
         addUserTheme = ( JFXRadioButton ) ( event.getSource() );
      } else if( event.getSource() == createUserEnglishOption
            || event.getSource() == createUserGermanOption
            || event.getSource() == createUserTurkishOption ) {
         createUserEnglishOption.setSelected( false );
         createUserTurkishOption.setSelected( false );
         createUserGermanOption.setSelected( false );
         ( ( JFXRadioButton ) ( event.getSource() ) ).setSelected( true );
         addUserLanguage = ( JFXRadioButton ) ( event.getSource() );
      } else if( event.getSource() == createUserParentOption
            || event.getSource() == createUserChildOption
            || event.getSource() == createUserElderOption ) {
         createUserParentOption.setSelected( false );
         createUserChildOption.setSelected( false );
         ( ( JFXRadioButton ) event.getSource() ).setSelected( true );
         addUserType = ( JFXRadioButton ) event.getSource();
      }
   }

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
      createUserEnglishOption.setSelected( false );
      createUserGermanOption.setSelected( false );
      createUserTurkishOption.setSelected( false );
      createUserParentOption.setSelected( false );
      createUserChildOption.setSelected( false );
      createDatePicker.setValue( null );

      addUserGender = null;
      addUserLanguage = null;
      addUserTheme = null;
      addUserType = null;
   }


   //settings ---------sub view pane----users settings menu---- remove user pane
   @FXML
   void removeUserPaneAction( ActionEvent event ) throws SQLException {
      if( event.getSource() == removeUserGoBack ) {
         deleteUserPane.setVisible( false );
         removeUserHideWarning.setVisible( false );
      } else if( event.getSource() == removeUserConfirm ||
            event.getSource() == removeUserTextField ) {
         if( removeUserTextField.getText().isEmpty() ) {
            removeUserHideWarning.setText( bundle.getString( "removePasswordLang" ) );
            removeUserHideWarning.setVisible( true );
            sound( "removePasswordLang", soundCheck );
         } else if( loginUser.getUserType().equals( "PARENT" ) &&
               removeUserTextField.getText().equals( loginUser.getPassword() ) &&
               settingUserTable.getSelectionModel().getSelectedItem().getValue().getUserType().equals( "CHILD" ) ) {
            Users.getInstance().removeUser( settingUserTable.getSelectionModel().getSelectedItem().getValue() );
            deleteUserPane.setVisible( false );
            removeUserTextField.setText( "" );
            removeUserHideWarning.setVisible( false );
            if( !( Users.getInstance().getParentNumber() > 1 ) ) {
               usersSettingSubPaneRemoveUser.setDisable( true );
            }
         } else if( removeUserTextField.getText().equals(
               settingUserTable.getSelectionModel().getSelectedItem().getValue().getPassword() ) &&
               settingUserTable.getSelectionModel().getSelectedItem().getValue() == loginUser ) {
            try {
               Users.getInstance().removeUser( settingUserTable.getSelectionModel().getSelectedItem().getValue() );
               removeUserTextField.setText( "" );
               removeUserHideWarning.setVisible( false );

               FXMLLoader load = new FXMLLoader( getClass().getResource( "view/loginPanel.fxml" ) );
               Parent root = load.load();
               Stage stage = new Stage();
               stage.setTitle( "SMART HOME" );
               stage.setScene( new Scene( root, 400, 400 ) );
               stage.setResizable( false );
               stage.show();
               firstStackPane.getScene().getWindow().hide();
            } catch( Exception e ) {
               e.printStackTrace();
            }
         } else if( removeUserTextField.getText().equals( settingUserTable.getSelectionModel().getSelectedItem().getValue().getPassword() ) ) {
            Users.getInstance().removeUser( settingUserTable.getSelectionModel().getSelectedItem().getValue() );
            deleteUserPane.setVisible( false );
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
         permissionPane.setVisible( false );
      }
      CommonSettingData.getInstance().updatePermission( commonSetting, permissions );
   }
   //settings----mods settings menu

   public void openModsPane() {
      settingModePane.setVisible( true );
      modsSettingButtonActive.setVisible( true );
      closeApplicationSettingPane();
      closeAllUsersPane();
      closeHomeSettingPane();
   }

   public void closeModsPane() {
      settingModePane.setVisible( false );
      modsSettingButtonActive.setVisible( false );
   }

   //settings----mods settings menu

   public void openHomeSettingPane() {
      homeSettingButtonActive.setVisible( true );
      homeSettingSubPane.setVisible( true );
      closeApplicationSettingPane();
      closeAllUsersPane();
      closeModsPane();

   }

   public void closeHomeSettingPane() {
      settingElecSettingPane.setVisible( false );
      settingWeatherSettingPane.setVisible( false );
      settingGasSettingPane.setVisible( false );
      settingAquSettingPane.setVisible( false );
      settingGreenHouseSettingPane.setVisible( false );
      homeSettingButtonActive.setVisible( false );
      homeSettingSubPane.setVisible( false );
      closeAllHomeSetting();
   }

   //settings----mods settings menu
   @FXML
   void volumeAdjust() throws SQLException, IOException {
      String modsSound;

      if( soundVolumeSlider.getValue() < 10 ) {
         volume = "00" + ( int ) soundVolumeSlider.getValue();
      } else if( soundVolumeSlider.getValue() < 100 ) {
         volume = "0" + ( int ) soundVolumeSlider.getValue();
      } else {
         volume = "" + ( int ) soundVolumeSlider.getValue();
      }

      audioClip.stop();
      audioClip = new AudioClip( this.getClass().getResource( "music/" + bundle.getString( "pathLang" ) + "volumeTryLang" + bundle.getString( "mp3Lang" ) ).toString() );
      audioClip.setVolume( ( ( double ) Integer.parseInt( volume ) ) / 200 );
      audioClip.play( ( ( double ) Integer.parseInt( volume ) ) / 200 );

      modsSound = loginUser.getSound().substring( 0, loginUser.getSound().length() - 3 ) + volume;
      Users.getInstance().updateVolume( loginUser, modsSound );
      userPreferenceUpdate( loginUser );
      updateUsersTable();
   }

   @FXML
   void modsToggleButtons( ActionEvent event ) throws SQLException, IOException {
      String modsSound;
      String modsText;

      modsSound = loginUser.getSound();
      modsText = loginUser.getText();

      if( event.getSource() == textModeToggle ) {
         textCheck = textModeToggle.isSelected();
         modsText = String.valueOf( textCheck );
      } else if( event.getSource() == soundModeToggle ) {
         if( soundModeToggle.isSelected() ) {
            soundControl( true );
         } else {
            soundControl( false );
         }
         modsSound = soundCheck + volume;
      }

      Users.getInstance().updateVolume( loginUser, modsSound );
      Users.getInstance().updateText( loginUser, modsText );
      userPreferenceUpdate( loginUser );
      updateUsersTable();
   }

   public void soundControl( Boolean check ) {
      soundVolumeSlider.setVisible( check );
      soundVolumeLabel.setVisible( check );
      soundCheck = check;
   }

   //settings pane----home settings pane
   @FXML
   void homeSettingButtons( ActionEvent event ) throws SQLException {
      if( event.getSource() == homeSettingElecButton ) {
         closeAllHomeSetting();
         settingElecSettingPane.setVisible( true );
         openHomeSetting();
         homeSettingElecButtonActive.setVisible( true );
      } else if( event.getSource() == homeSettingGasButton ) {
         closeAllHomeSetting();
         settingGasSettingPane.setVisible( true );
         openHomeSetting();
         homeSettingGasButtonActive.setVisible( true );
      } else if( event.getSource() == homeSettingAquButton ) {
         closeAllHomeSetting();
         settingAquSettingPane.setVisible( true );
         openHomeSetting();
         homeSettingAquButtonActive.setVisible( true );
      } else if( event.getSource() == homeSettingGreenHouseButton ) {
         closeAllHomeSetting();
         settingGreenHouseSettingPane.setVisible( true );
         openHomeSetting();
         homeSettingGreenHouseButtonActive.setVisible( true );
      } else if( event.getSource() == homeSettingWeatherButton ) {
         closeAllHomeSetting();
         settingWeatherSettingPane.setVisible( true );
         openHomeSetting();
         homeSettingWeatherButtonActive.setVisible( true );
      } else if( event.getSource() == updateWeatherButton ) {
         if( settingWeatherLocationTextField.getText().length() > 0 ) {
            try {
               if( weatherForecast == null )
                  weatherForecast = new WeatherForecast( loginUser.getLocation() );
               else {
                  weatherForecast.findLocationXY( settingWeatherLocationTextField.getText() );
                  weatherForecast.getWeatherCase();
               }
               settingWeatherForecastLabelValue.setText( weatherForecast.getWeather() );
               settingWeatherTemperatureLabelValue.setText( weatherForecast.getTemperature() + "°C" );
               settingWeatherHumidityLabelValue.setText( weatherForecast.getHumidity() );
               settingWeatherWindLabelValue.setText( weatherForecast.getWind() );
               informationTime.setText( weatherForecast.getLocalTime() );
               Users.getInstance().updateLocation( loginUser, settingWeatherLocationTextField.getText() );
               backgroundSetup( weatherForecast.getWeather() );
            } catch( IOException exception ) {
               settingWeatherForecastLabelValue.setText( bundle.getString( "netConnectionLang" ) );
               settingWeatherTemperatureLabelValue.setText( bundle.getString( "netConnectionLang" ) );
               settingWeatherHumidityLabelValue.setText( bundle.getString( "netConnectionLang" ) );
               settingWeatherWindLabelValue.setText( bundle.getString( "netConnectionLang" ) );
               informationTime.setText( bundle.getString( "netConnectionLang" ) );
               Users.getInstance().updateLocation( loginUser, settingWeatherLocationTextField.getText() );
               backgroundSetup( "weather" );
            }
         } else {
            informationTime.setText( "Please enter the location" );
         }
      } else if( event.getSource() == saveAquariumChangesButton ) {
         if( feedingTime.getValue() == null ||
               waterExchangeTime.getValue() == null ||
               airMotorStartTime.getValue() == null ||
               waterExchangeDay.getValue() == null ) {
         } else {
            StringBuilder message;

            message = new StringBuilder( "aquarium#" );

            if( feedingTime.getValue().getHour() < 10 )
               message.append( "0" + feedingTime.getValue().getHour() );
            else
               message.append( feedingTime.getValue().getHour() );

            if( feedingTime.getValue().getMinute() < 10 )
               message.append( "0" + feedingTime.getValue().getMinute() + "00" );
            else
               message.append( feedingTime.getValue().getMinute() + "00" );

            if( waterExchangeTime.getValue().getHour() < 10 )
               message.append( "0" + waterExchangeTime.getValue().getHour() );
            else
               message.append( waterExchangeTime.getValue().getHour() );

            if( waterExchangeTime.getValue().getMinute() < 10 )
               message.append( "0" + waterExchangeTime.getValue().getMinute() + "00" );
            else
               message.append( waterExchangeTime.getValue().getMinute() + "00" );

            message.append( "0" + waterExchangeDay.getValue().charAt( 0 ) );

            if( airMotorStartTime.getValue().getHour() < 10 )
               message.append( "0" + airMotorStartTime.getValue().getHour() );
            else
               message.append( airMotorStartTime.getValue().getHour() );

            if( airMotorStartTime.getValue().getMinute() < 10 )
               message.append( "0" + airMotorStartTime.getValue().getMinute() + "00" );
            else
               message.append( airMotorStartTime.getValue().getMinute() + "00" );

            if( airMotorRunTime.getValue() < 10 )
               message.append( "0" + ( int ) airMotorRunTime.getValue() + ":" );
            else
               message.append( ( int ) airMotorRunTime.getValue() + ":" );

            if( isArduinoConnect )
               home.getAquarium().setAquariumSettings( message.toString() );
            CommonSettingData.getInstance().updateAquariumSettings( commonSetting, message.toString().substring( 9, 31 ) );
            CommonSettingData.getInstance().updateSelectedFishes( commonSetting, checkComboBox.getItems() );
         }
      }
   }

   public void backgroundSetup( String weather ) {
      if( weather.equals( "Cloudy" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/cloudy.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Partly cloudy" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/partlyCloudy1.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Sunny" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/sunny1.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Snowy" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/snowy.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Patches Of Fog" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/patchesOfFog.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Light Rain Shower" ) || weather.equalsIgnoreCase( "Patchy rain possible" )
            || weather.equalsIgnoreCase( "rain" ) || weather.equalsIgnoreCase( "Light Rain" )
            || weather.equalsIgnoreCase( "Shower In Vicinity, Rain Shower" ) || weather.equalsIgnoreCase( "light drizzle" )
            || weather.equalsIgnoreCase( "Shower In Vicinity" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/lightRain.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Mist" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/mist.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Light Rain With Thunderstorm, Rain With Thunderstorm" )
            || weather.equalsIgnoreCase( "Light Rain With Thunderstorm" )
            || weather.equalsIgnoreCase( "Rain With Thunderstorm" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/lightRainWithThunderStorm.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Moderate or heavy rain shower" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/heavyRain.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Clear" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/Clear.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "Thundery outbreaks possible" ) || weather.equalsIgnoreCase( "Overcast" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/Thundery.jpg" ) ) );
      else if( weather.equalsIgnoreCase( "weather" ) )
         weatherForecastImage.setImage( new Image( getClass().getResourceAsStream( "styleSheets/images/weather.jpg" ) ) );

   }

   @FXML
   void settingToggleButtonsAction( ActionEvent event ) {
      if( event.getSource() == settingElectricityToggleButton ) {
         if( isArduinoConnect )
            home.getElectricity().open( settingElectricityToggleButton.isSelected() );
         openElectricity( settingElectricityToggleButton.isSelected() );
      } else if( event.getSource() == settingGasToggleButton ) {
         if( isArduinoConnect )
            home.getGas().open( settingElectricityToggleButton.isSelected() );
         openGas( settingElectricityToggleButton.isSelected() );
      } else if( event.getSource() == settingAquariumToggleButton ) {
         if( isArduinoConnect )
            home.getAquarium().open( settingAquariumToggleButton.isSelected() );
         openAquarium( settingAquariumToggleButton.isSelected() );
      }
   }

   public void openHomeSetting() {
      homeSettingSubPane.setVisible( true );
      homeSettingButtonActive.setVisible( true );
   }

   public void closeAllHomeSetting() {
      settingElecSettingPane.setVisible( false );
      settingGasSettingPane.setVisible( false );
      settingAquSettingPane.setVisible( false );
      settingGreenHouseSettingPane.setVisible( false );
      settingWeatherSettingPane.setVisible( false );
      homeSettingElecButtonActive.setVisible( false );
      homeSettingGasButtonActive.setVisible( false );
      homeSettingAquButtonActive.setVisible( false );
      homeSettingGreenHouseButtonActive.setVisible( false );
      homeSettingWeatherButtonActive.setVisible( false );
      homeSettingSubPane.setVisible( false );
      homeSettingButtonActive.setVisible( false );
   }
}
