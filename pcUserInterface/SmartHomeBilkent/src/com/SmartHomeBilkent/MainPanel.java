package com.SmartHomeBilkent;

import com.SmartHomeBilkent.extra.dataBase.Users;
import com.SmartHomeBilkent.extra.speech.SpeechUtils;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainPanel implements Initializable {

    //public static Arduino arduino = new Arduino("COM3",9600);
    //String communicate;

    public User loginUser;

    @FXML
    private StackPane firstStackPane,
            menuStackPane;

    //menu variables
    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private JFXButton menuButton,
            menuButtonActive,
            userProfileButton,
            userProfileButtonActive,
            settingsButton,
            settingsButtonActive,
            logoutButton,
            menuElecButton,
            menuGasButton,
            menuWaterButton,
            menuGreenHouseButton,
            menuConnectionButton,
            gasSubMenuButtonPassive,
            gasSubMenuButtonActive,
            waterSubMenuButtonPassive,
            waterSubMenuButtonActive,
            greenhouseSubMenuButtonPassive,
            greenhouseSubMenuButtonActive,
            elecSubMenuButtonPassive,
            elecSubMenuButtonActive,
            weatherButton;

    @FXML
    private BorderPane menuBorderPane;

    @FXML
    private JFXProgressBar menuElecProgress,
            menuGasProgress,
            menuWaterProgress;

    @FXML
    private Label ghHumidityTempLabel,
            ghHumidityValueLabel,
            menuHomeTempLabel,
            ghTempValueLabel,
            menuConnectionWarningLabel,
            elecSubPaneLabelPassive,
            elecSubPaneOpenLabelPassive,
            elecSubPaneCloseLabelPassive,
            elecSubPaneOpenValueLabelPassive,
            elecSubPaneCloseValueLabelPassive,
            elecSubPaneLabelActive,
            elecSubPaneOpenLabelActive,
            elecSubPaneCloseLabelActive,
            elecSubPaneOpenValueLabelActive,
            elecSubPaneCloseValueLabelActive,
            gasSubPaneLabelPassive,
            gasSubPaneLabelActive,
            aquariumSubPaneLabelPassive,
            heaterSubPaneLabelPassive,
            aquariumSubPaneLabelActive,
            greenHouseSubPaneLabelPassive,
            ghTempSubPaneLabelActive,
            menuUserProfileLabel,
            menuMenuLabel,
            menuSettingLabel;

    @FXML
    private ImageView weatherImage,tempImage;

    @FXML
    private Pane connectionPane,
            menuEmptyPane,
            menuElecPane,
            menuGasPane,
            menuWaterPane,
            menuTempPane;

    @FXML
    private JFXToggleButton elecSubMenuToggleButton,
            gasSubMenuToggleButton,
            waterSubMenuToggleButton;

    //user profile variables
    @FXML
    private Label privateInfoWarning,
            normalInfoWarning,
            userProfileNameLabel,
            userProfileSurnameLabel,
            userProfileGenderLabel,
            userProfileAgeLabel,
            userProfileUserNameLabel,
            userProfilePasswordLabel,
            normalChangeNameLabel,
            normalChangeSurnameLabel,
            normalChangeBirthdayLabel,
            normalChangeGenderLabel,
            userNameLabel,
            newPasswordLabel,
            verifyNewPasswordLabel,
            currentPasswordLabel,
            userChangerInfoLabel,
            changeUserInfoLabel,
            changeUserPrivateInfoLabel;

    @FXML
    private StackPane userProfileStackPane;

    @FXML
    private AnchorPane userProfileAnchorPane;

    @FXML
    private Pane userProfilePane,
            changeUserNormalInfoPane,
            changeUserPrivateInfoPane;

    @FXML
    private JFXTextField userProfileNameField,
            userProfileSurnameField,
            userProfileAgeField,
            userProfileGenderField,
            userProfileUserNameField,
            userProfilePasswordField,
            nameTextField,
            surnameTextField,
            userNameTextField,
            newPasswordTextField,
            verifyNewPasswordField,
            currentPasswordField;

    @FXML
    private JFXDatePicker birthdayDateField;

    @FXML
    private JFXButton changeUserNormalInfoButton,
            changeUserPrivateInfoButton,
            userChangerButton,
            saveUserNormalInfo,
            backToUserProfileFromNormalInfo,
            saveUserPrivateInfo,
            backToUserProfileFromPrivateInfo;

    @FXML
    private JFXRadioButton maleRadioOption,
            femaleRadioOption;

    @FXML
    private ImageView userInfoBoyImage,
            userInfoGirlImage;

    //settings variables
    @FXML
    private AnchorPane settingAnchorPane;

    //settings pane ----main facilities pane
    @FXML
    private AnchorPane settingSubAnchorTop;

    @FXML
    private Pane settingMainFunctionsPane;

    @FXML
    private JFXButton applicationSettingButton,
            settingsUsersSettingButton,
            modsSettingButton,
            homeSettingButton,
            applicationSettingButtonActive,
            settingsUsersSettingButtonActive,
            modsSettingButtonActive,
            homeSettingButtonActive;

    @FXML
    private Label applicationSettingButtonLabel,
            usersSettingButtonLabel,
            modsSettingButtonLabel,
            homeSettingButtonLabel;

    //settings pane ----view pane
    @FXML
    private AnchorPane settingSubAnchorBottom;

    //settings pane ----view pane----theme setting
    @FXML
    private Pane settingThemePane;

    @FXML
    private JFXRadioButton darkThemeRadioButton,
            lightThemeRadioButton,
            smoothThemeRadioButton,
            cartoonThemeRadioButton;

    @FXML
    private JFXButton settingThemeSaveButton;

    @FXML
    private ImageView settingThemeDarkImage,
            settingThemeLightImage,
            settingThemeSmoothImage,
            settingThemeCartoonImage;

    //settings pane ----view pane----language setting
    @FXML
    private Pane settingLanguagePane;

    @FXML
    private JFXButton englishOption,
            germanOption,
            turkishOption;

    @FXML
    private Label englishLabel,
            germanLabel,
            turkishLabel,
            themeTopLabel;

    @FXML
    private JFXButton saveLanguageChangesButton;

    //settings pane ----view pane----emergency setting
    @FXML
    private Pane settingEmergencyPane;

    @FXML
    private Label languageTopLabel,
            emergencyTopLabel,
            notificationTopLabel,
            connectionTopLabel,
            removeAnUser;

    //settings pane ----view pane----notification setting
    @FXML
    private Pane settingNotificationPane;

    //settings pane ----view pane----connection setting
    @FXML
    private Pane settingConnectionPane;

    //settings pane ----view pane----user table setting

    @FXML
    private Pane settingUsersPane;

    @FXML
    private JFXTreeTableView<User> settingUserTable;

    @FXML
    private TreeTableColumn<User, String> settingUserTableName,
            settingUserTableSurname,settingUserTableBirthday,
            settingUserTableGender,settingUserTableUserName,
            settingUserTableTheme,settingUserTableLanguage;


    //settings pane ----view pane----new user add pane

    private JFXRadioButton addUserGender, addUserTheme,
    addUserLanguage, addUserType;

    @FXML
    private Pane addUserPane;

    @FXML
    private Label createUserTopLabel,createUserNameLabel,
            createUserSurnameLabel,createUserGenderLabel,
            createUserBirthdayLabel,createUserUserNameLabel,
            createUserPasswordLabel,createUserPasswordVerifyLabel,
            createUserUserTypeLabel,createUserThemeLabel,
            createUserLanguageLabel,createUserWarningLabel;

    @FXML
    private JFXDatePicker createDatePicker;

    @FXML
    private JFXButton createUserConfirmButton,createUserGoBack;

    @FXML
    private JFXTextField createUserNameTextField,createUserSurnameTextField,
            createUserUserNameTextField;

    @FXML
    private JFXRadioButton createUserMaleOption,createUserFemaleOption,
            createUserDarkThemeOption,createUserLightThemeOption,
            createUserSmoothThemeOption,createUserCartoonThemeOption,
            createUserEnglishOption,createUserTurkishOption,createUserGermanOption,
            createUserParentOption,createUserChildOption,createUserElderOption;

    @FXML
    private JFXPasswordField createUserPasswordField,createUserPasswordVerifyField;

    //settings pane ----view pane----user remove pane

    @FXML
    private Pane deleteUserPane;

    @FXML
    private JFXPasswordField removeUserTextField;

    @FXML
    private Label removeUserFirstWarning,
            removeUserHideWarning;

    @FXML
    private JFXButton removeUserConfirm,
            removeUserGoBack;

    //settings pane ----view pane----mods pane

    @FXML
    private SplitPane settingModePane;

    @FXML
    private JFXToggleButton textModeToggle,soundModeToggle,soundExtraToggle;

    @FXML
    private Label soundHelperLabel,soundVolumeLabel,
            interactiveTextModeLabel,interactiveSoundModeLabel;

    @FXML
    private JFXSlider soundVolumeSlider;

    //settings pane ----view pane----home settings pane
    @FXML
    private Pane settingElecSettingPane,settingGasSettingPane,
            settingAquSettingPane,settingGreenHouseSettingPane,settingRFIDSettingPane;
    @FXML
    private Label homeElecSettingTopLabel,homeGasSettingTopLabel,
            homeAquSettingTopLabel,homeGreenHouseSettingTopLabel,
            homeRfidSettingTopLabel,homeSubPaneELecLabel,
            homeSubPaneGasLabel,homeSubPaneAquLabel,
            homeSubPaneGreenHouseLabel, homeSubPaneRFIDLabel;

    //settings pane ----subView pane----application settings sub menus

    @FXML
    private AnchorPane applicationSettingSubPane;

    @FXML
    private JFXButton themeSettingButton,languageSettingButton,
            emergencySettingButton,phoneNotificationSettingButton,
            smartHomeConnectionSettingButton,themeSettingButtonActive,
            languageSettingButtonActive,emergencySettingButtonActive,
            phoneNotificationSettingButtonActive,smartHomeConnectionSettingButtonActive;

    @FXML
    private Label themeSubLabel,languageSubLabel,
            emergencySubLabel,notificationSubLabel,connectionSubLabel;


    //settings pane ----subView pane----users settings sub menus
    @FXML
    private AnchorPane usersSettingSubPane;

    @FXML
    private JFXButton usersSettingSubPaneAddUser,usersSettingSubPaneRemoveUser,usersSettingSubPaneChangeUserType;

    //settings pane ----subView pane----mods settings sub menus
    @FXML
    private AnchorPane modsSettingSubPane;

    //settings pane ----subView pane----homes settings sub menus
    @FXML
    private AnchorPane homeSettingSubPane;

    @FXML
    private JFXButton homeSettingElecButton,homeSettingGasButton,
            homeSettingAquButton,homeSettingGreenHouseButton,
            homeSettingRFIDButton,homeSettingElecButtonActive,
            homeSettingGasButtonActive, homeSettingAquButtonActive,
            homeSettingGreenHouseButtonActive, homeSettingRFIDButtonActive;

    @FXML
    private JFXComboBox<?> portChooser;
    private ResourceBundle bundle;
    private AudioClip audioClip;
    private Boolean soundCheck;
    private  Boolean soundCheckEx;
    private  Boolean textCheck;
    private  String volume;
    private SpeechUtils speechUtils;
    private LocalDate localDate;
    private DateTimeFormatter dateTimeFormatter;

    //initialize method(it runs before the program start to run)
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //arduino.openConnection();
        //communicate = "000000";

        //adjust user settings
        userPreferenceUpdate(getLoginUser());
        updateUsersTable();
        usersSettingSubPaneRemoveUser.setDisable(true);
        audioClip = new AudioClip(this.getClass().getResource("music/suprise.mp3").toString());
        audioClip.setVolume(((double)Integer.parseInt(volume))/200);
        audioClip.setRate(1.1);
        speechUtils = new SpeechUtils();
        //example//speechUtils.SpeakText("Hello, today weather is partly cloudy and, temperature is ,8, celsius degree",true);
    }

    void sound(String file, Boolean check){
        if(check){
         audioClip.stop();
         audioClip = new AudioClip(this.getClass().getResource("music/" + bundle.getString("pathLang") + file + bundle.getString("mp3Lang")).toString());
         audioClip.setRate(1);
         audioClip.play();
        }
    }

    public User getLoginUser(){
        for(User u: Users.getInstance().getUserList()){
            if(u.getEnter().equals("true")){
                u.setEnter("false");
                loginUser = u;
                return u;
            }
        }
        return null;
    }

    void userPreferenceUpdate(User user){
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        localDate = LocalDate.parse(user.getBirthday(),dateTimeFormatter);
        birthdayDateField.setValue(localDate);
        userProfileNameField.setText(user.getName());
        userProfileSurnameField.setText(user.getSurname());
        userProfileAgeField.setText(user.getBirthday());
        userProfileGenderField.setText(user.getGender());
        userProfileUserNameField.setText(user.getUserName());
        nameTextField.setText(user.getName());
        surnameTextField.setText(user.getSurname());
        if(user.getGender().equals("MALE") || user.getGender().equals("ERKEK") || user.getGender().equals("MÄNNLICH")){
            maleRadioOption.setSelected(true);
            femaleRadioOption.setSelected(false);
            userInfoBoyImage.setVisible(true);
            userInfoGirlImage.setVisible(false);
        }else{
            femaleRadioOption.setSelected(true);
            maleRadioOption.setSelected(false);
            userInfoBoyImage.setVisible(false);
            userInfoGirlImage.setVisible(true);
        }
        userNameTextField.setText(user.getUserName());

        if(loginUser.getPreferredTheme().equals("cartoon") || loginUser.getPreferredTheme().equals("karıkatur") || loginUser.getPreferredTheme().equals("çızgı fılm")) {
            selectCartoonTheme();
            changeTheme("cartoon");
        }
        else if(loginUser.getPreferredTheme().equals("light") || loginUser.getPreferredTheme().equals("lıght") || loginUser.getPreferredTheme().equals("aydınlık") || loginUser.getPreferredTheme().equals("lıcht")) {
            selectLightTheme();
            changeTheme("light");
        }
        else if(loginUser.getPreferredTheme().equals("smooth")|| loginUser.getPreferredTheme().equals("puruzsuz") || loginUser.getPreferredTheme().equals("glatt") ){
            selectSmoothTheme();
            changeTheme("smooth");
        }
        else {
            selectDarkTheme();
            changeTheme("dark");
        }

        if(loginUser.getPreferredLanguage().equals("ENGLISH")){
            languageSetter("en");
            selectEnglishOption();
        }else if(loginUser.getPreferredLanguage().equals("TÜRKÇE")){
            languageSetter("tr");
            selectTurkishOption();
        }else if(loginUser.getPreferredLanguage().equals("DEUTSCH")){
            languageSetter("de");
            selectGermanOption();
        }

        if(loginUser.getSound().substring(0,loginUser.getSound().length()-3).equals("false")){
            soundCheck = false;
            soundCheckEx = false;
            soundControl(false);
            soundCheckEx = false;
        }else if(loginUser.getSound().substring(0,loginUser.getSound().length()-3).equals("true")){
            soundCheck = true;
            soundCheckEx = false;
            soundControl(true);
            soundModeToggle.setSelected(true);
        }else if(loginUser.getSound().substring(0,loginUser.getSound().length()-3).equals("trueEx")){
            soundCheck = true;
            soundCheckEx = true;
            soundModeToggle.setSelected(true);
            soundExtraToggle.setSelected(true);
        }

        textCheck = !loginUser.getText().equals("false");
        textModeToggle.setSelected(loginUser.getText().equals("true"));
        volume = (loginUser.getSound().substring(loginUser.getSound().length()-3));
        soundVolumeSlider.setValue(Double.parseDouble(volume));
    }

    void updateUsersTable(){
        //initialize column
        settingUserTableName.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        settingUserTableSurname.setCellValueFactory(param -> param.getValue().getValue().surnameProperty());
        settingUserTableBirthday.setCellValueFactory(param -> param.getValue().getValue().birthdayProperty());
        settingUserTableGender.setCellValueFactory(param -> param.getValue().getValue().genderProperty());
        settingUserTableUserName.setCellValueFactory(param -> param.getValue().getValue().userNameProperty());
        settingUserTableTheme.setCellValueFactory(param -> param.getValue().getValue().preferredThemeProperty());
        settingUserTableLanguage.setCellValueFactory(param -> param.getValue().getValue().preferredLanguageProperty());

        settingUserTable.setRoot(new RecursiveTreeItem<>(Users.getInstance().getUserList(), RecursiveTreeObject::getChildren));
        settingUserTable.setShowRoot(false);
    }

    //these are helps to switch between three main pane(user profile, menu, setting)
    @FXML
    void paneChangerButtons(ActionEvent event){
        if(event.getSource() == userProfileButton){
            openUserProfilePane();
        }else if(event.getSource() == menuButton){
            openMenuPane();
        }else if(event.getSource() == settingsButton){
            openSettingsPane();
        }else if(event.getSource() == logoutButton){
            Platform.exit();
        }
    }

    public void labelOpener(Label label, Boolean b){
        if(b){
            label.setVisible(true);
        }
    }

    @FXML
    void paneChangerButtonsMov(MouseEvent event) {
        if(event.getSource() == userProfileButton || event.getSource() == userProfileButtonActive){
            labelOpener(menuUserProfileLabel,textCheck);
            sound("userProfileLang", soundCheck);
        }else if(event.getSource() == menuButton || event.getSource() == menuButtonActive){
            labelOpener(menuMenuLabel,textCheck);
            sound("menuLang", soundCheck);
        }else if(event.getSource() == settingsButton || event.getSource() == settingsButtonActive){
            labelOpener(menuSettingLabel,textCheck);
            sound("settingLang", soundCheck);
        }else if(event.getSource() == logoutButton){
            sound("closeAppLang", soundCheck);
        }else if(event.getSource() == tempImage){
            sound("homeTemperatureLang", soundCheck);
        }else if(event.getSource() == weatherImage){
            sound("weatherLang", soundCheck);
        }else if(event.getSource() == menuConnectionButton){
            sound("connectionLang", soundCheck);
        }else if(event.getSource() == menuElecButton){
            sound("elecLang", soundCheck);
        }else if(event.getSource() == menuGasButton){
            sound("gasLang", soundCheck);
        }else if(event.getSource() == menuWaterButton){
            sound("aquiarumLang", soundCheck);
        }else if(event.getSource() == menuGreenHouseButton){
            sound("greenHouseLang", soundCheck);
        }else if(event.getSource() == elecSubMenuButtonPassive || event.getSource() == elecSubMenuButtonActive){
            sound("elecSettingsLang", soundCheck);
        }else if(event.getSource() == gasSubMenuButtonPassive || event.getSource() == gasSubMenuButtonActive){
            sound("gasSettingsLang", soundCheck);
        }else if(event.getSource() == waterSubMenuButtonPassive || event.getSource() == waterSubMenuButtonActive){
            sound("aquSettingsLang", soundCheck);
        }else if(event.getSource() == greenhouseSubMenuButtonPassive || event.getSource() == greenhouseSubMenuButtonActive){
            sound("greenHouseSettingsLang", soundCheck);
        }
    }

    @FXML
    void paneChangerButtonsMovEx(MouseEvent event) {
        if(event.getSource() == userProfileButton || event.getSource() == userProfileButtonActive){
            menuUserProfileLabel.setVisible(false);
        }else if(event.getSource() == menuButton || event.getSource() == menuButtonActive){
            menuMenuLabel.setVisible(false);
        }else if(event.getSource() == settingsButton || event.getSource() == settingsButtonActive){
            menuSettingLabel.setVisible(false);
        }
    }

    //it opens menu
    void openMenuPane(){
        menuButtonActive.setVisible(true);
        menuStackPane.setVisible(true);
        menuStackPane.setDisable(false);

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setNode(menuStackPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0.95);
        fadeTransition.play();

        closeUserProfilePane();
        closeSettingsPane();
    }

    //it closes menu
    void closeMenuPane() {
        if(menuButtonActive.isVisible())
        {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setNode(menuStackPane);
        fadeTransition.setFromValue(menuStackPane.getOpacity());
        fadeTransition.setToValue(0);
        fadeTransition.play();
        }

        menuButtonActive.setVisible(false);
        menuStackPane.setVisible(false);
        menuStackPane.setDisable(true);
    }

    //it opens setting
    void openSettingsPane() {
        settingsButtonActive.setVisible(true);
        settingAnchorPane.setVisible(true);
        settingAnchorPane.setDisable(false);
        openApplicationPanes();
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setNode(settingAnchorPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0.95);
        fadeTransition.play();

        closeUserProfilePane();
        closeMenuPane();
    }

    //it closes setting
    void closeSettingsPane() {
        if(settingsButtonActive.isVisible()){
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(0.5));
            fadeTransition.setNode(settingAnchorPane);
            fadeTransition.setFromValue(settingAnchorPane.getOpacity());
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }

        settingsButtonActive.setVisible(false);
        settingAnchorPane.setVisible(false);
        settingAnchorPane.setDisable(true);
        closeAllApplicationPanes();
        closeAllHomeSetting();
        closeAllUsersPane();
        closeModsPane();
    }

    //it opens user profile
    void openUserProfilePane()  {
        userProfileButtonActive.setVisible(true);
        userProfileStackPane.setVisible(true);
        userProfileStackPane.setDisable(false);

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setNode(userProfileStackPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0.95);
        fadeTransition.play();

        closeSettingsPane();
        closeMenuPane();
    }

    //it closes user profile
    void closeUserProfilePane() {
        if(userProfileButton.isVisible()){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setNode(userProfileStackPane);
        fadeTransition.setFromValue(userProfileStackPane.getOpacity());
        fadeTransition.setToValue(0);
        fadeTransition.play();
        }

        userProfileButtonActive.setVisible(false);
        userProfileStackPane.setVisible(false);
        userProfileStackPane.setDisable(true);

        toGoBackUserProfile();
    }

    //main pane

    //this opens related facility's settings pane
    @FXML
    void goToSettingFromMenuView(ActionEvent event) {
        if(event.getSource() == elecSubMenuButtonPassive || event.getSource() == elecSubMenuButtonActive){
            closeMenuPane();
            openSettingsPane();
            closeAllApplicationPanes();
            openHomeSettingPane();
            settingElecSettingPane.setVisible(true);
            homeSettingElecButtonActive.setVisible(true);
        }else if(event.getSource() == gasSubMenuButtonPassive || event.getSource() == gasSubMenuButtonActive){
            closeMenuPane();
            openSettingsPane();
            closeAllApplicationPanes();
            closeAllHomeSetting();
            openHomeSettingPane();
            settingGasSettingPane.setVisible(true);
            homeSettingGasButtonActive.setVisible(true);
        }else if(event.getSource() == waterSubMenuButtonPassive || event.getSource() == waterSubMenuButtonActive){
            closeMenuPane();
            openSettingsPane();
            closeAllApplicationPanes();
            closeAllHomeSetting();
            openHomeSettingPane();
            settingAquSettingPane.setVisible(true);
            homeSettingAquButtonActive.setVisible(true);
        }else if(event.getSource() == greenhouseSubMenuButtonPassive || event.getSource() == waterSubMenuButtonActive){
            closeMenuPane();
            openSettingsPane();
            closeAllApplicationPanes();
            closeAllHomeSetting();
            openHomeSettingPane();
            settingGreenHouseSettingPane.setVisible(true);
            homeSettingGreenHouseButtonActive.setVisible(true);
        }else if(event.getSource() == menuConnectionButton){
            closeMenuPane();
            openSettingsPane();
            closeAllAppLabel();
            closeThemeSetting();
            closeLanguageSetting();
            closeEmergencySetting();
            closePhoneNotificationSetting();
            openSmartHomeConnectionSetting();
            labelOpener(connectionSubLabel,textCheck);
        }

    }

    //this controls which pane will be visible by fallowing buttons
    @FXML
    void openMenuPaneViewButtons(ActionEvent event) {
        if( event.getSource() == menuElecButton ){
            menuTempPane.setVisible(false);
            menuElecPane.setVisible(true);
            menuGasPane.setVisible(false);
            menuWaterPane.setVisible(false);
        }else if( event.getSource() == menuGasButton){
            menuTempPane.setVisible(false);
            menuElecPane.setVisible(false);
            menuGasPane.setVisible(true);
            menuWaterPane.setVisible(false);
        }else if( event.getSource() == menuWaterButton){
            menuTempPane.setVisible(false);
            menuElecPane.setVisible(false);
            menuGasPane.setVisible(false);
            menuWaterPane.setVisible(true);
        }
        else if( event.getSource() == menuGreenHouseButton){
            menuTempPane.setVisible(true);
            menuElecPane.setVisible(false);
            menuGasPane.setVisible(false);
            menuWaterPane.setVisible(false);
        }
    }

    //it controls whether facilities are opened or closed
    @FXML
    void openMenuPaneToggles(ActionEvent event) {
        if(event.getSource() == elecSubMenuToggleButton)
        {
            if(elecSubMenuToggleButton.isSelected()) {
                //communicate =  1 + communicate.substring(1);
                //System.out.println(communicate);
                //arduino.serialWrite(communicate);
                //String gir = arduino.serialRead(6);
                //System.out.println(gir);

                elecSubPaneLabelActive.setVisible(true);
                elecSubMenuButtonActive.setVisible(true);
                elecSubPaneOpenLabelActive.setVisible(true);
                elecSubPaneOpenValueLabelActive.setVisible(true);
                elecSubPaneCloseLabelActive.setVisible(true);
                elecSubPaneCloseValueLabelActive.setVisible(true);
                menuElecProgress.setVisible(true);
            }
            else {
                //communicate =  "00" + communicate.substring(2,4) + "00";
                //System.out.println(communicate);
                //arduino.serialWrite(communicate);
                //String gir = arduino.serialRead(6);
                //System.out.println(gir);

                elecSubPaneLabelActive.setVisible(false);
                elecSubMenuButtonActive.setVisible(false);
                elecSubPaneOpenLabelActive.setVisible(false);
                elecSubPaneOpenValueLabelActive.setVisible(false);
                elecSubPaneCloseLabelActive.setVisible(false);
                elecSubPaneCloseValueLabelActive.setVisible(false);
                menuElecProgress.setVisible(false);
            }
        }
        else if(event.getSource() == gasSubMenuToggleButton){
            if(gasSubMenuToggleButton.isSelected()) {
                //communicate = communicate.substring(0,2) + 1 + communicate.substring(3);
                //System.out.println(communicate);
                //arduino.serialWrite(communicate);
                //String gir = arduino.serialRead(6);
                //System.out.println(gir);

                gasSubPaneLabelActive.setVisible(true);
                gasSubMenuButtonActive.setVisible(true);
                menuGasProgress.setVisible(true);
            }
            else {
                //communicate = communicate.substring(0,2) + 0 + communicate.substring(3,4) + 0 + communicate.substring(5);
                //System.out.println(communicate);
                //arduino.serialWrite(communicate);
                //String gir = arduino.serialRead(6);
                //System.out.println(gir);

                gasSubPaneLabelActive.setVisible(false);
                gasSubMenuButtonActive.setVisible(false);
                menuGasProgress.setVisible(false);
            }
        }else if(event.getSource() == waterSubMenuToggleButton){
            if(waterSubMenuToggleButton.isSelected()) {
                //communicate = communicate.substring(0,3) + 1 + communicate.substring(4);
                //System.out.println(communicate);
                //arduino.serialWrite(communicate);
                //String gir = arduino.serialRead(6);
                //System.out.println(gir);

                aquariumSubPaneLabelActive.setVisible(true);
                waterSubMenuButtonActive.setVisible(true);
                menuWaterProgress.setVisible(true);
            }
            else {
                //communicate = communicate.substring(0,3) + 0 + communicate.substring(4);
                //System.out.println(communicate);
                //arduino.serialWrite(communicate);
                //String gir = arduino.serialRead(6);
                //System.out.println(gir);


                aquariumSubPaneLabelActive.setVisible(false);
                waterSubMenuButtonActive.setVisible(false);
                menuWaterProgress.setVisible(false);
            }
        }

    }

    //userProfile

    //user profiles main buttons (normal info change button, priv info change button, user changer button)
    @FXML
    void userInfoPanelBasicButtons(ActionEvent event) {
        if(event.getSource() == changeUserNormalInfoButton){
            userProfilePane.setEffect(new BoxBlur(10,3,3));
            changeUserNormalInfoPane.setVisible(true);
            userProfilePane.setDisable(true);
        }else if(event.getSource() == changeUserPrivateInfoButton){
            userProfilePane.setEffect(new BoxBlur(10,3,3));
            changeUserPrivateInfoPane.setVisible(true);
            userProfilePane.setDisable(true);
        }else if(event.getSource() == userChangerButton){
            try{
                FXMLLoader load = new FXMLLoader(getClass().getResource("loginPanel.fxml"));
                Parent root =load.load();
                Stage stage = new Stage();
                stage.setTitle("SMART HOME");
                stage.setScene(new Scene(root, 400, 400));
                stage.setResizable(false);
                stage.show();
                firstStackPane.getScene().getWindow().hide();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //user profiles main buttons' helper labels animation
    @FXML
    void userInfoPanelBasicButtonsMov(MouseEvent event) {
        if(event.getSource() == userChangerButton) {
            labelOpener(userChangerInfoLabel,textCheck);
            sound("userChangerLang", soundCheck);
        }
        else if(event.getSource() == changeUserNormalInfoButton) {
            labelOpener(changeUserInfoLabel,textCheck);
            sound("changeUserInfoLang", soundCheck);
        }
        else if(event.getSource() == changeUserPrivateInfoButton) {
            labelOpener(changeUserPrivateInfoLabel,textCheck);
            sound("changeUserInfoLang", soundCheck);
        }else if(event.getSource() == saveUserNormalInfo || event.getSource() == saveUserPrivateInfo){
            sound("saveChangesLang", soundCheck);
        }else if(event.getSource() == backToUserProfileFromNormalInfo || event.getSource() == backToUserProfileFromPrivateInfo){
            sound("goBackLang", soundCheck);
        }
    }

    @FXML
    void userInfoPanelBasicButtonsMovEx(MouseEvent event) {
        if(event.getSource() == userChangerButton)
            userChangerInfoLabel.setVisible(false);
        else if(event.getSource() == changeUserNormalInfoButton)
            changeUserInfoLabel.setVisible(false);
        else if(event.getSource() == changeUserPrivateInfoButton)
            changeUserPrivateInfoLabel.setVisible(false);
    }

    //user profiles user info changer panels
    void toGoBackUserProfile(){
        userProfilePane.setEffect(new BoxBlur(0,0,0));
        userProfilePane.setDisable(false);
        changeUserNormalInfoPane.setVisible(false);
        changeUserPrivateInfoPane.setVisible(false);
    }

    @FXML
    void changeUserInfoButtons(ActionEvent event) throws SQLException {
        if(event.getSource() == saveUserNormalInfo) {
            String gender;
            if(nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || birthdayDateField.getValue()== null){
                normalInfoWarning.setVisible(true);
            }else{

            if (maleRadioOption.isSelected()) {
                gender = "MALE";
            } else {
                gender = "FEMALE";
            }
            Users.getInstance().updateUserNormalInfo(loginUser,nameTextField.getText()
                    ,surnameTextField.getText(),birthdayDateField.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    ,gender);
            userPreferenceUpdate(loginUser);
            updateUsersTable();
            toGoBackUserProfile();
            }
        }else if (event.getSource() == saveUserPrivateInfo){
            if(userNameTextField.getText().length() > 0 && currentPasswordField.getText().length() > 0 && newPasswordTextField.getText().length() > 0 && verifyNewPasswordField.getText().length() > 0) {
                //for database
                if( !newPasswordTextField.getText().equals(verifyNewPasswordField.getText())){
                    privateInfoWarning.setVisible(true);
                    privateInfoWarning.setText(bundle.getString("passwordConflictLang"));
                }
                else if(!currentPasswordField.getText().equals(loginUser.getPassword())){
                    privateInfoWarning.setVisible(true);
                    privateInfoWarning.setText(bundle.getString("passwordMistakeLang"));
                }
                else {
                    privateInfoWarning.setVisible(false);
                    Users.getInstance().updatePrivateInfo(loginUser,userNameTextField.getText(),newPasswordTextField.getText());
                    userPreferenceUpdate(loginUser);
                    toGoBackUserProfile();
                    updateUsersTable();
                }
            }else {
                privateInfoWarning.setVisible(true);
                privateInfoWarning.setText(bundle.getString("normalInfoWarningLang"));
            }
        }else if(event.getSource() == backToUserProfileFromNormalInfo){
            toGoBackUserProfile();
            normalInfoWarning.setVisible(false);
            birthdayDateField.setValue(localDate);
        }else if( event.getSource() == backToUserProfileFromPrivateInfo){
            toGoBackUserProfile();
            privateInfoWarning.setVisible(false);
        }
    }

    @FXML
    void userInfoChangeGenderOptions(ActionEvent event) {
        if( event.getSource() == maleRadioOption){
        if (maleRadioOption.isSelected()){
            femaleRadioOption.setSelected(false);
        }else{
            femaleRadioOption.setSelected(true);
        }
        }else if(event.getSource() ==femaleRadioOption){
            if (femaleRadioOption.isSelected()){
                maleRadioOption.setSelected(false);
            }else{
                maleRadioOption.setSelected(true);
            }
        }
    }

    //settings
    //settings ---------top pane
    @FXML
    void settingsFacilitiesButtons(ActionEvent event) throws SQLException {
        if(event.getSource() == applicationSettingButton){
            openApplicationPanes();
        }else if (event.getSource() == settingsUsersSettingButton){
            openUsersPane();
        }else if(event.getSource() == modsSettingButton){
            openModsPane();
        }else if(event.getSource() == homeSettingButton){
            openHomeSettingPane();
            settingElecSettingPane.setVisible(true);
            homeSettingElecButtonActive.setVisible(true);
        }else if(event.getSource() == settingThemeSaveButton){
            String themeName;
            themeName = "";
            if(darkThemeRadioButton.isSelected())
                themeName ="dark";
            else if(lightThemeRadioButton.isSelected())
                themeName ="light";
            else if(smoothThemeRadioButton.isSelected())
                themeName ="smooth";
            else if(cartoonThemeRadioButton.isSelected())
                themeName ="cartoon";

            changeTheme(themeName);
            Users.getInstance().updateUsersTheme(loginUser,themeName);
            userPreferenceUpdate(loginUser);
        }
    }

    //all settings buttons ( app settings(theme-language-emergency-notification-connection), users settings( add-remove user), home settings())
    @FXML
    void settingsFacilitiesButtonsMov(MouseEvent event) {
        if(event.getSource() == applicationSettingButton || event.getSource() == applicationSettingButtonActive) {
            labelOpener(applicationSettingButtonLabel,textCheck);
            sound("applicationSettingLang", soundCheck);
        }
        else if (event.getSource() == settingsUsersSettingButton || event.getSource() == settingsUsersSettingButtonActive) {
            labelOpener(usersSettingButtonLabel,textCheck);
            sound("usersSettingLang", soundCheck);
        }
        else if (event.getSource() == modsSettingButton || event.getSource() == modsSettingButtonActive) {
            labelOpener(modsSettingButtonLabel,textCheck);
            sound("modsSettingLang", soundCheck);
        }
        else if (event.getSource() == homeSettingButton || event.getSource() == homeSettingButtonActive) {
            labelOpener(homeSettingButtonLabel,textCheck);
            sound("homeLang", soundCheck);
        }
    }

    @FXML
    void settingsFacilitiesButtonsMovEx(MouseEvent event) {
        if(event.getSource() == applicationSettingButton || event.getSource() == applicationSettingButtonActive)
            applicationSettingButtonLabel.setVisible(false);
        else if (event.getSource() == settingsUsersSettingButton || event.getSource() == settingsUsersSettingButtonActive)
            usersSettingButtonLabel.setVisible(false);
        else if (event.getSource() == modsSettingButton || event.getSource() == modsSettingButtonActive)
            modsSettingButtonLabel.setVisible(false);
        else if (event.getSource() == homeSettingButton || event.getSource() == homeSettingButtonActive)
            homeSettingButtonLabel.setVisible(false);
    }

    //settings ---------view pane
    //settings ---------view pane----theme pane

    @FXML
    void selectTheme(ActionEvent event) {
        if(event.getSource() == darkThemeRadioButton)
        selectDarkTheme();
        else if(event.getSource() == lightThemeRadioButton)
            selectLightTheme();
        else if(event.getSource() == smoothThemeRadioButton)
            selectSmoothTheme();
        else if(event.getSource() == cartoonThemeRadioButton)
            selectCartoonTheme();
    }

    void selectDarkTheme() {
        cartoonThemeRadioButton.setSelected(false);
        lightThemeRadioButton.setSelected(false);
        smoothThemeRadioButton.setSelected(false);
        darkThemeRadioButton.setSelected(true);

        settingThemeDarkImage.setVisible(true);
        settingThemeLightImage.setVisible(false);
        settingThemeSmoothImage.setVisible(false);
        settingThemeCartoonImage.setVisible(false);

    }

    void selectLightTheme() {
        cartoonThemeRadioButton.setSelected(false);
        smoothThemeRadioButton.setSelected(false);
        darkThemeRadioButton.setSelected(false);
        lightThemeRadioButton.setSelected(true);

        settingThemeLightImage.setVisible(true);
        settingThemeSmoothImage.setVisible(false);
        settingThemeCartoonImage.setVisible(false);
        settingThemeDarkImage.setVisible(false);
    }

    void selectSmoothTheme() {
        cartoonThemeRadioButton.setSelected(false);
        lightThemeRadioButton.setSelected(false);
        darkThemeRadioButton.setSelected(false);
        smoothThemeRadioButton.setSelected(true);

        settingThemeLightImage.setVisible(false);
        settingThemeSmoothImage.setVisible(true);
        settingThemeCartoonImage.setVisible(false);
        settingThemeDarkImage.setVisible(false);
    }

    void selectCartoonTheme() {
        lightThemeRadioButton.setSelected(false);
        smoothThemeRadioButton.setSelected(false);
        darkThemeRadioButton.setSelected(false);
        cartoonThemeRadioButton.setSelected(true);

        settingThemeLightImage.setVisible(false);
        settingThemeSmoothImage.setVisible(false);
        settingThemeCartoonImage.setVisible(true);
        settingThemeDarkImage.setVisible(false);
    }

    void  changeTheme(String themeName){
        String css;
        if(themeName.equals("light") || themeName.equals("aydınlık") || themeName.equals("licht"))
             css = this.getClass().getResource("styleSheets/main_menu_light_theme.css").toExternalForm();
        else if(themeName.equals("dark") || themeName.equals("gece")|| themeName.equals("dunkel"))
            css = this.getClass().getResource("styleSheets/main_menu_dark_theme.css").toExternalForm();
        else if(themeName.equals("smooth") || themeName.equals("pürüzsüz") || themeName.equals("glatt"))
            css = this.getClass().getResource("styleSheets/main_menu_smooth_themee.css").toExternalForm();
        else if (themeName.equals("cartoon") || themeName.equals("çizgi film") || themeName.equals("karikatur"))
            css = this.getClass().getResource("styleSheets/main_menu_cartoon_theme.css").toExternalForm();
        else
            css = "";

        try{
            firstStackPane.getStylesheets().removeAll(firstStackPane.getStylesheets());
            firstStackPane.getStylesheets().add(css);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //settings ----language pane

    //language pane buttons

    @FXML
    void selectLanguage(ActionEvent event) throws SQLException {
        String language;
        language = "";
        if(event.getSource() == englishOption) {
            selectEnglishOption();
            languageSetter("en");
            language = "ENGLISH";
        }
        else if(event.getSource() == germanOption){
            selectGermanOption();
            languageSetter("de");
            language = "DEUTSCH";
        }
        else if(event.getSource() == turkishOption) {
            selectTurkishOption();
            languageSetter("tr");
            language = "TÜRKÇE";
        }
        Users.getInstance().updateLanguage(loginUser,language);
        userPreferenceUpdate(loginUser);
    }

    private void languageSetter(String language){
        try{
        Locale locale = new Locale(language);
        bundle = ResourceBundle.getBundle("com.SmartHomeBilkent.language.lang", locale);
        menuUserProfileLabel.setText(bundle.getString("userProfileLang"));
        menuMenuLabel.setText(bundle.getString("menuLang"));
        menuSettingLabel.setText(bundle.getString("settingLang"));
        menuConnectionWarningLabel.setText(bundle.getString("connectionWarningLang"));
        elecSubPaneLabelPassive.setText(bundle.getString("elecLang"));
        elecSubPaneLabelActive.setText(bundle.getString("elecLang"));
        elecSubPaneOpenLabelPassive.setText(bundle.getString("lastOpenLang"));
        elecSubPaneOpenLabelActive.setText(bundle.getString("lastOpenLang"));
        elecSubPaneCloseLabelPassive.setText(bundle.getString("lastCloseLang"));
        elecSubPaneCloseLabelActive.setText(bundle.getString("lastCloseLang"));
        elecSubPaneOpenValueLabelPassive.setText(bundle.getString("yesterdayLang"));
        elecSubPaneOpenValueLabelActive.setText(bundle.getString("yesterdayLang"));
        elecSubPaneCloseValueLabelPassive.setText(bundle.getString("yesterday2Lang"));
        elecSubPaneCloseValueLabelActive.setText(bundle.getString("yesterday2Lang"));
        gasSubPaneLabelPassive.setText(bundle.getString("gasLang"));
        gasSubPaneLabelActive.setText(bundle.getString("gasLang"));
        aquariumSubPaneLabelPassive.setText(bundle.getString("aquiarumLang"));
        aquariumSubPaneLabelActive.setText(bundle.getString("aquiarumLang"));
        greenHouseSubPaneLabelPassive.setText(bundle.getString("greenHouse"));
        ghTempSubPaneLabelActive.setText(bundle.getString("tempLang"));
        ghHumidityTempLabel.setText(bundle.getString("humidity"));
        menuHomeTempLabel.setText(bundle.getString("tempLang"));
        userProfileNameLabel.setText(bundle.getString("nameLang"));
        userProfileSurnameLabel.setText(bundle.getString("surnameLang"));
        userProfileAgeLabel.setText(bundle.getString("birthdayLang"));
        userProfileGenderLabel.setText(bundle.getString("genderLang"));
        userProfileUserNameLabel.setText(bundle.getString("userNameLang"));
        userProfilePasswordLabel.setText(bundle.getString("passwordLang"));
        userChangerInfoLabel.setText(bundle.getString("userChangerLang"));
        changeUserInfoLabel.setText(bundle.getString("changeUserInfoLang"));
        changeUserPrivateInfoLabel.setText(bundle.getString("changeUserInfoLang"));
        normalChangeNameLabel.setText(bundle.getString("nameLang"));
        normalChangeSurnameLabel.setText(bundle.getString("surnameLang"));
        normalChangeBirthdayLabel.setText(bundle.getString("birthdayLang"));
        normalChangeGenderLabel.setText(bundle.getString("genderLang"));
        normalInfoWarning.setText(bundle.getString("normalInfoWarningLang"));
        userNameLabel.setText(bundle.getString("userNameLang"));
        newPasswordLabel.setText(bundle.getString("newPasswordLang"));
        verifyNewPasswordLabel.setText(bundle.getString("verifyNewPasswordLang"));
        currentPasswordLabel.setText(bundle.getString("currentPasswordLang"));
        applicationSettingButtonLabel.setText(bundle.getString("applicationSettingLang"));
        usersSettingButtonLabel.setText(bundle.getString("usersSettingLang"));
        modsSettingButtonLabel.setText(bundle.getString("modsSettingLang"));
        homeSettingButtonLabel.setText(bundle.getString("homeLang"));
        themeTopLabel.setText(bundle.getString("themeLang"));
        languageTopLabel.setText(bundle.getString("languageLang"));
        emergencyTopLabel.setText(bundle.getString("emergencyLang"));
        notificationTopLabel.setText(bundle.getString("notificationLang"));
        connectionTopLabel.setText(bundle.getString("connectionLang"));
        themeSubLabel.setText(bundle.getString("themeLang"));
        languageSubLabel.setText(bundle.getString("languageLang"));
        emergencySubLabel.setText(bundle.getString("emergencyLang"));
        notificationSubLabel.setText(bundle.getString("notificationLang"));
        connectionSubLabel.setText(bundle.getString("connectionLang"));
        createUserTopLabel.setText(bundle.getString("createUserLang"));
        createUserNameLabel.setText(bundle.getString("nameLang"));
        createUserSurnameLabel.setText(bundle.getString("surnameLang"));
        createUserGenderLabel.setText(bundle.getString("genderLang"));
        createUserBirthdayLabel.setText(bundle.getString("birthdayLang"));
        createUserUserNameLabel.setText(bundle.getString("userNameLang"));
        createUserPasswordLabel.setText(bundle.getString("passwordLang"));
        createUserPasswordVerifyLabel.setText(bundle.getString("passwordVerifyLang"));
        createUserUserTypeLabel.setText(bundle.getString("userTypeLang"));
        createUserThemeLabel.setText(bundle.getString("themeLang"));
        createUserLanguageLabel.setText(bundle.getString("languageLang"));
        createUserWarningLabel.setText(bundle.getString("createUserWarningLabel"));
        removeAnUser.setText(bundle.getString("removeAnUserLang"));
        removeUserFirstWarning.setText(bundle.getString("removeUserFirstWarningLang"));
        removeUserHideWarning.setText(bundle.getString("removeUserHideWarning"));
        maleRadioOption.setText(bundle.getString("maleGenderLang"));
        femaleRadioOption.setText(bundle.getString("femaleGenderLang"));
        darkThemeRadioButton.setText(bundle.getString("darkThemeLang"));
        lightThemeRadioButton.setText(bundle.getString("lightThemeLang"));
        smoothThemeRadioButton.setText(bundle.getString("smoothThemeLang"));
        cartoonThemeRadioButton.setText(bundle.getString("cartoonThemeLang"));
        portChooser.setPromptText(bundle.getString("portChooserLang"));
        settingUserTableName.setText(bundle.getString("nameLang"));
        settingUserTableSurname.setText(bundle.getString("surnameLang"));
        settingUserTableBirthday.setText(bundle.getString("birthdayLang"));
        settingUserTableGender.setText(bundle.getString("genderLang"));
        settingUserTableUserName.setText(bundle.getString("userNameLang"));
        settingUserTableTheme.setText(bundle.getString("themeLang"));
        settingUserTableLanguage.setText(bundle.getString("languageLang"));
        createUserMaleOption.setText(bundle.getString("maleGenderLang"));
        createUserFemaleOption.setText(bundle.getString("femaleGenderLang"));
        createUserDarkThemeOption.setText(bundle.getString("darkThemeLang"));
        createUserLightThemeOption.setText(bundle.getString("lightThemeLang"));
        createUserSmoothThemeOption.setText(bundle.getString("smoothThemeLang"));
        createUserCartoonThemeOption.setText(bundle.getString("cartoonThemeLang"));
        createUserParentOption.setText(bundle.getString("parentOptionLang"));
        createUserChildOption.setText(bundle.getString("childOptionLang"));
        createUserElderOption.setText(bundle.getString("elderOptionLang"));
        homeElecSettingTopLabel.setText(bundle.getString("elecSettingTopLang"));
        homeGasSettingTopLabel.setText(bundle.getString("gasSettingTopLang"));
        homeAquSettingTopLabel.setText(bundle.getString("aquSettingTopLang"));
        homeGreenHouseSettingTopLabel.setText(bundle.getString("greenHouseSettingTopLang"));
        homeRfidSettingTopLabel.setText(bundle.getString("rfidSettingTopLang"));
        homeSubPaneELecLabel.setText(bundle.getString("elecLang"));
        homeSubPaneGasLabel.setText(bundle.getString("gasLang"));
        homeSubPaneAquLabel.setText(bundle.getString("aquiarumLang"));
        homeSubPaneGreenHouseLabel.setText(bundle.getString("greenHouse"));
        homeSubPaneRFIDLabel.setText(bundle.getString("rfidLang"));
        interactiveTextModeLabel.setText(bundle.getString("interactiveTextLang"));
        interactiveSoundModeLabel.setText(bundle.getString("interactiveSoundLang"));
        soundHelperLabel.setText(bundle.getString("soundHelper"));
        soundVolumeLabel.setText(bundle.getString("soundVolume"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void selectEnglishOption() {
        englishOption.setPrefSize(150,150);
        englishOption.setLayoutX(80);
        englishOption.setLayoutY(105);

        germanOption.setPrefSize(90,90);
        turkishOption.setPrefSize(90,90);

        germanOption.setLayoutX(304);
        germanOption.setLayoutY(105);
        turkishOption.setLayoutX(501);
        turkishOption.setLayoutY(105);

    }

    void selectGermanOption() {
        germanOption.setPrefSize(150,150);
        germanOption.setLayoutX(274);
        germanOption.setLayoutY(105);

        englishOption.setPrefSize(90,90);
        turkishOption.setPrefSize(90,90);

        englishOption.setLayoutX(110);
        englishOption.setLayoutY(105);
        turkishOption.setLayoutX(501);
        turkishOption.setLayoutY(105);

    }

    void selectTurkishOption() {
        turkishOption.setPrefSize(150,150);
        turkishOption.setLayoutX(471);
        turkishOption.setLayoutY(105);

        englishOption.setPrefSize(90,90);
        germanOption.setPrefSize(90,90);

        englishOption.setLayoutX(110);
        englishOption.setLayoutY(105);
        germanOption.setLayoutX(304);
        germanOption.setLayoutY(105);
    }

    @FXML
    void selectLanguageMov(MouseEvent event) {
        if(event.getSource() == englishOption)
            labelOpener(englishLabel,textCheck);
        else if(event.getSource() == germanOption)
            labelOpener(germanLabel,textCheck);
        else if(event.getSource() == turkishOption)
            labelOpener(turkishLabel,textCheck);
    }

    @FXML
    void selectLanguageMovEx(MouseEvent event) {
        if(event.getSource() == englishOption)
            englishLabel.setVisible(false);
        else if(event.getSource() == germanOption)
            germanLabel.setVisible(false);
        else if(event.getSource() == turkishOption)
            turkishLabel.setVisible(false);
    }

    //settings----application settings menu
    @FXML
    void applicationSettingButtons(ActionEvent event) {
        if(event.getSource() == themeSettingButton) {
            closeAllAppLabel();
            openThemeSetting();
            closeLanguageSetting();
            closeEmergencySetting();
            closePhoneNotificationSetting();
            closeSmartHomeConnectionSetting();
        }else if(event.getSource() == languageSettingButton){
            closeAllAppLabel();
            closeThemeSetting();
            openLanguageSetting();
            closeEmergencySetting();
            closePhoneNotificationSetting();
            closeSmartHomeConnectionSetting();
        }else if(event.getSource() == emergencySettingButton){
            closeAllAppLabel();
            closeThemeSetting();
            closeLanguageSetting();
            openEmergencySetting();
            closePhoneNotificationSetting();
            closeSmartHomeConnectionSetting();
        }else if(event.getSource() == phoneNotificationSettingButton){
            closeAllAppLabel();
            closeThemeSetting();
            closeLanguageSetting();
            closeEmergencySetting();
            openPhoneNotificationSetting();
            closeSmartHomeConnectionSetting();
        }else if(event.getSource() == smartHomeConnectionSettingButton){
            closeAllAppLabel();
            closeThemeSetting();
            closeLanguageSetting();
            closeEmergencySetting();
            closePhoneNotificationSetting();
            openSmartHomeConnectionSetting();
        }
    }

    @FXML
    void applicationSettingButtonsMov(MouseEvent event) {
        if(event.getSource() == themeSettingButton || event.getSource() == themeSettingButtonActive) {
            labelOpener(themeSubLabel,textCheck);
            sound("themeLang", soundCheck);
        }
        else if(event.getSource() == settingThemeSaveButton){
            sound("saveChangesLang", soundCheck);
        }
        else if(event.getSource() == languageSettingButton || event.getSource() == languageSettingButtonActive ) {
            labelOpener(languageSubLabel,textCheck);
            sound("languageLang", soundCheck);
        }
        else if(event.getSource() == emergencySettingButton || event.getSource() == emergencySettingButtonActive) {
            labelOpener(emergencySubLabel,textCheck);
            sound("emergencyLang", soundCheck);
        }
        else if(event.getSource() == phoneNotificationSettingButton || event.getSource() == phoneNotificationSettingButtonActive) {
            labelOpener(notificationSubLabel,textCheck);
            sound("notificationLang", soundCheck);
        }
        else if(event.getSource() == smartHomeConnectionSettingButton || event.getSource() == smartHomeConnectionSettingButtonActive) {
            labelOpener(connectionSubLabel,textCheck);
            sound("connectionLang", soundCheck);
        }else if(event.getSource() == homeSettingElecButton || event.getSource() == homeSettingElecButtonActive) {
            sound("elecSettingsLang", soundCheck);
            homeSubPaneELecLabel.setVisible(true);
        }else if(event.getSource() == homeSettingGasButton || event.getSource() == homeSettingGasButtonActive) {
            sound("gasSettingsLang", soundCheck);
            homeSubPaneGasLabel.setVisible(true);
        }else if(event.getSource() == homeSettingAquButton || event.getSource() == homeSettingAquButtonActive) {
            sound("aquSettingsLang", soundCheck);
            homeSubPaneAquLabel.setVisible(true);
        }else if(event.getSource() == homeSettingGreenHouseButton || event.getSource() == homeSettingGreenHouseButtonActive) {
            sound("greenHouseSettingsLang", soundCheck);
            homeSubPaneGreenHouseLabel.setVisible(true);
        }else if(event.getSource() == homeSettingRFIDButton || event.getSource() == homeSettingRFIDButtonActive) {
            sound("rfidSettingsLang", soundCheck);
            homeSubPaneRFIDLabel.setVisible(true);
        }
    }

    public void closeAllAppLabel(){
        themeSubLabel.setVisible(false);
        languageSubLabel.setVisible(false);
        emergencySubLabel.setVisible(false);
        notificationSubLabel.setVisible(false);
        connectionSubLabel.setVisible(false);
    }

    @FXML
    void applicationSettingButtonsMovEx(MouseEvent event) {
        if(event.getSource() == themeSettingButton)
            themeSubLabel.setVisible(false);
        else if(event.getSource() == languageSettingButton)
            languageSubLabel.setVisible(false);
        else if(event.getSource() == emergencySettingButton)
            emergencySubLabel.setVisible(false);
        else if(event.getSource() == phoneNotificationSettingButton)
            notificationSubLabel.setVisible(false);
        else if(event.getSource() == smartHomeConnectionSettingButton)
            connectionSubLabel.setVisible(false);
        else if(event.getSource() == homeSettingElecButton || event.getSource() == homeSettingElecButtonActive)
            homeSubPaneELecLabel.setVisible(false);
        else if(event.getSource() == homeSettingGasButton || event.getSource() == homeSettingGasButtonActive)
            homeSubPaneGasLabel.setVisible(false);
        else if(event.getSource() == homeSettingAquButton || event.getSource() == homeSettingAquButtonActive)
            homeSubPaneAquLabel.setVisible(false);
        else if(event.getSource() == homeSettingGreenHouseButton || event.getSource() == homeSettingGreenHouseButtonActive)
            homeSubPaneGreenHouseLabel.setVisible(false);
        else if(event.getSource() == homeSettingRFIDButton || event.getSource() == homeSettingRFIDButtonActive)
            homeSubPaneRFIDLabel.setVisible(false);

    }

    void openThemeSetting() {
        themeSettingButtonActive.setVisible(true);
        settingThemePane.setVisible(true);
    }

    void closeThemeSetting() {
        themeSettingButtonActive.setVisible(false);
        settingThemePane.setVisible(false);
    }

    void openLanguageSetting() {
        languageSettingButtonActive.setVisible(true);
        settingLanguagePane.setVisible(true);
    }

    void closeLanguageSetting() {
        languageSettingButtonActive.setVisible(false);
        settingLanguagePane.setVisible(false);
    }

    void openEmergencySetting() {
        emergencySettingButtonActive.setVisible(true);
        settingEmergencyPane.setVisible(true);
    }

    void closeEmergencySetting() {
        emergencySettingButtonActive.setVisible(false);
        settingEmergencyPane.setVisible(false);
    }

    void openPhoneNotificationSetting() {
        phoneNotificationSettingButtonActive.setVisible(true);
        settingNotificationPane.setVisible(true);
    }

    void closePhoneNotificationSetting() {
        phoneNotificationSettingButtonActive.setVisible(false);
        settingNotificationPane.setVisible(false);
    }

    void openSmartHomeConnectionSetting( ) {
        smartHomeConnectionSettingButtonActive.setVisible(true);
        settingConnectionPane.setVisible(true);
    }

    void closeSmartHomeConnectionSetting( ) {
        smartHomeConnectionSettingButtonActive.setVisible(false);
        settingConnectionPane.setVisible(false);
    }

    void closeAllApplicationPanes(){
        closeThemeSetting();
        closeLanguageSetting();
        closeEmergencySetting();
        closePhoneNotificationSetting();
        closeSmartHomeConnectionSetting();
        applicationSettingButtonActive.setVisible(false);
        applicationSettingSubPane.setVisible(false);
        closeAllAppLabel();
        labelOpener(themeSubLabel,textCheck);
    }

    void openApplicationPanes(){
        openThemeSetting();

        applicationSettingButtonActive.setVisible(true);
        applicationSettingSubPane.setVisible(true);

        closeAllUsersPane();
        closeModsPane();
        closeHomeSettingPane();
    }

    //settings----users settings menu

    void closeAllUsersPane(){
        usersSettingSubPane.setVisible(false);
        settingUsersPane.setVisible(false);
        settingsUsersSettingButtonActive.setVisible(false);
        addUserPane.setVisible(false);
        deleteUserPane.setVisible(false);
    }

    void openUsersPane(){
        settingUsersPane.setVisible(true);
        usersSettingSubPane.setVisible(true);
        settingsUsersSettingButtonActive.setVisible(true);
        closeAllApplicationPanes();
        closeModsPane();
        closeHomeSettingPane();
    }

    @FXML
    void usersSettingSubPaneButtons(ActionEvent event) {
        if(event.getSource() == usersSettingSubPaneAddUser){
            addUserPane.setVisible(true);
            deleteUserPane.setVisible(false);
            sound("createUserLang", soundCheck);
        }else if(event.getSource() == usersSettingSubPaneRemoveUser){
            deleteUserPane.setVisible(true);
            addUserPane.setVisible(false);
            sound("removeAnUserLang", soundCheck);
        }
    }

    @FXML
    void settingUserTableMov() {
        if(settingUserTable.getSelectionModel().isEmpty())
            usersSettingSubPaneRemoveUser.setDisable(true);
        else if(settingUserTable.getSelectionModel().getSelectedItem().getValue().getUserType().equals("PARENT") && !(Users.getInstance().getParentNumber() > 1))
            usersSettingSubPaneRemoveUser.setDisable(true);
        else
            usersSettingSubPaneRemoveUser.setDisable(false);
    }

    //settings ---------sub view pane----users settings menu---- add new user pane
    @FXML
    void createUserControlSelections(ActionEvent event) {
        if(event.getSource() == createUserMaleOption || event.getSource() == createUserFemaleOption) {
            createUserMaleOption.setSelected(false);
            createUserFemaleOption.setSelected(false);
            ((JFXRadioButton) (event.getSource())).setSelected(true);
            addUserGender = (JFXRadioButton) (event.getSource());
        }else if(event.getSource() == createUserDarkThemeOption || event.getSource() == createUserLightThemeOption || event.getSource() == createUserSmoothThemeOption || event.getSource() == createUserCartoonThemeOption){
            createUserDarkThemeOption.setSelected(false);
            createUserLightThemeOption.setSelected(false);
            createUserSmoothThemeOption.setSelected(false);
            createUserCartoonThemeOption.setSelected(false);
            ((JFXRadioButton)(event.getSource())).setSelected(true);
            addUserTheme = (JFXRadioButton)(event.getSource());
        }else if(event.getSource() == createUserEnglishOption || event.getSource() == createUserGermanOption || event.getSource() == createUserTurkishOption){
            createUserEnglishOption.setSelected(false);
            createUserTurkishOption.setSelected(false);
            createUserGermanOption.setSelected(false);
            ((JFXRadioButton)(event.getSource())).setSelected(true);
            addUserLanguage = (JFXRadioButton)(event.getSource());
        }else if(event.getSource() == createUserParentOption || event.getSource() ==createUserChildOption || event.getSource() ==createUserElderOption){
            createUserParentOption.setSelected(false);
            createUserChildOption.setSelected(false);
            ((JFXRadioButton)(event.getSource())).setSelected(true);
            addUserType = (JFXRadioButton)(event.getSource());
        }
    }

    @FXML
    void createUserPaneAction(ActionEvent event) throws SQLException {
        if((event.getSource()) == createUserConfirmButton){
            if(createUserNameTextField.getText().isEmpty() || createUserSurnameTextField.getText().isEmpty()
            || createDatePicker.getValue() == null || (!createUserMaleOption.isSelected() && !createUserFemaleOption.isSelected())
            || (!createUserDarkThemeOption.isSelected() && !createUserLightThemeOption.isSelected() && !createUserSmoothThemeOption.isSelected() && !createUserCartoonThemeOption.isSelected())
            || ( !createUserEnglishOption.isSelected() && !createUserGermanOption.isSelected() && !createUserTurkishOption.isSelected())
            || createUserUserNameTextField.getText().isEmpty() || createUserPasswordField.getText().isEmpty()
            || createUserPasswordVerifyField.getText().isEmpty() || (!createUserParentOption.isSelected() && !createUserChildOption.isSelected() && !createUserElderOption.isSelected())){
                createUserWarningLabel.setText(bundle.getString("normalInfoWarningLang"));
                sound("normalInfoWarningLang", soundCheck);
            }
            else if(!createUserPasswordField.getText().equals(createUserPasswordVerifyField.getText())){
                createUserWarningLabel.setText(bundle.getString("passwordConflictLang"));
                sound("passwordConflictLang", soundCheck);
            }else{
                Users.getInstance().addUser( new User (createUserNameTextField.getText(),
                        createUserSurnameTextField.getText(),
                        createDatePicker.getValue().format(dateTimeFormatter),
                        addUserGender.getText(),
                        createUserUserNameTextField.getText(),
                        createUserPasswordField.getText(),
                        addUserType.getText(),
                        addUserTheme.getText().toLowerCase(),
                        addUserLanguage.getText(),
                        "false",
                        "true",
                        "true050"
                ));
                updateUsersTable();
                addUserPane.setVisible(false);
                clearAddPane();

                if((Users.getInstance().getParentNumber() > 1) ){
                    usersSettingSubPaneRemoveUser.setDisable(false);
                }
            }

        }else if((event.getSource()) == createUserGoBack){
            closeAllUsersPane();
            openUsersPane();
            clearAddPane();
        }
    }

    public void clearAddPane(){
        createUserNameTextField.setText("");
        createUserSurnameTextField.setText("");
        createUserMaleOption.setSelected(false);
        createUserFemaleOption.setSelected(false);
        createUserUserNameTextField.setText("");
        createUserPasswordField.setText("");
        createUserPasswordVerifyField.setText("");
        createUserDarkThemeOption.setSelected(false);
        createUserLightThemeOption.setSelected(false);
        createUserSmoothThemeOption.setSelected(false);
        createUserCartoonThemeOption.setSelected(false);
        createUserEnglishOption.setSelected(false);
        createUserGermanOption.setSelected(false);
        createUserTurkishOption.setSelected(false);
        createUserParentOption.setSelected(false);
        createUserChildOption.setSelected(false);
        createDatePicker.setValue(null);

        addUserGender = null;
        addUserLanguage = null;
        addUserTheme = null;
        addUserType = null;
    }

    //settings ---------sub view pane----users settings menu---- remove user pane
    @FXML
    void removeUserPaneAction(ActionEvent event) throws SQLException {
        if(event.getSource() == removeUserGoBack){
            deleteUserPane.setVisible(false);
            removeUserHideWarning.setVisible(false);
        }else if( event.getSource() == removeUserConfirm || event.getSource() == removeUserTextField){
            if(removeUserTextField.getText().isEmpty()){
                removeUserHideWarning.setText(bundle.getString("removePasswordLang"));
                removeUserHideWarning.setVisible(true);
                sound("removePasswordLang", soundCheck);
            }
            else if ( loginUser.getUserType().equals("PARENT") && removeUserTextField.getText().equals(loginUser.getPassword()) && (settingUserTable.getSelectionModel().getSelectedItem().getValue().getUserType().equals("CHILD"))){
                Users.getInstance().removeUser(settingUserTable.getSelectionModel().getSelectedItem().getValue());
                deleteUserPane.setVisible(false);
                removeUserTextField.setText("");
                removeUserHideWarning.setVisible(false);
                if(!(Users.getInstance().getParentNumber() > 1) ){
                    usersSettingSubPaneRemoveUser.setDisable(true);
                }
            }
            else if( removeUserTextField.getText().equals(settingUserTable.getSelectionModel().getSelectedItem().getValue().getPassword()) && settingUserTable.getSelectionModel().getSelectedItem().getValue() == loginUser ){
                try{
                    Users.getInstance().removeUser(settingUserTable.getSelectionModel().getSelectedItem().getValue());
                    removeUserTextField.setText("");
                    removeUserHideWarning.setVisible(false);

                    FXMLLoader load = new FXMLLoader(getClass().getResource("loginPanel.fxml"));
                    Parent root = load.load();
                    Stage stage = new Stage();
                    stage.setTitle("SMART HOME");
                    stage.setScene(new Scene(root, 400, 400));
                    stage.setResizable(false);
                    stage.show();
                    firstStackPane.getScene().getWindow().hide();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if ( removeUserTextField.getText().equals(settingUserTable.getSelectionModel().getSelectedItem().getValue().getPassword())){
                Users.getInstance().removeUser(settingUserTable.getSelectionModel().getSelectedItem().getValue());
                deleteUserPane.setVisible(false);
                removeUserTextField.setText("");
                removeUserHideWarning.setVisible(false);
                if(!(Users.getInstance().getParentNumber() > 1) ){
                    usersSettingSubPaneRemoveUser.setDisable(true);
                }
            }else{
                removeUserHideWarning.setText(bundle.getString("passwordMistakeLang"));
                removeUserHideWarning.setVisible(true);
                sound("passwordMistakeLang", soundCheck);
            }
        }
    }

    //settings----mods settings menu

    public void openModsPane(){
        settingModePane.setVisible(true);
        modsSettingButtonActive.setVisible(true);
        closeAllApplicationPanes();
        closeAllUsersPane();
        closeHomeSettingPane();
    }

    public void closeModsPane(){
        settingModePane.setVisible(false);
        modsSettingButtonActive.setVisible(false);
    }

    //settings----mods settings menu

    public void openHomeSettingPane(){
        homeSettingButtonActive.setVisible(true);
        homeSettingSubPane.setVisible(true);
        closeAllApplicationPanes();
        closeAllUsersPane();
        closeModsPane();

    }

    public void closeHomeSettingPane(){
        settingElecSettingPane.setVisible(false);
        settingRFIDSettingPane.setVisible(false);
        settingGasSettingPane.setVisible(false);
        settingAquSettingPane.setVisible(false);
        settingGreenHouseSettingPane.setVisible(false);
        homeSettingButtonActive.setVisible(false);
        homeSettingSubPane.setVisible(false);
        closeAllHomeSetting();
    }

    //settings----mods settings menu
    @FXML
    void volumeAdjust() throws SQLException {
        String modsSound;

        if(soundVolumeSlider.getValue() < 10){
            volume = "00" + (int)soundVolumeSlider.getValue();
        }else if(soundVolumeSlider.getValue() < 100){
            volume = "0" + (int)soundVolumeSlider.getValue();
        }else{
            volume = "" + (int)soundVolumeSlider.getValue();
        }

        audioClip.stop();
        audioClip = new AudioClip(this.getClass().getResource("music/" + bundle.getString("pathLang") + "volumeTryLang" + bundle.getString("mp3Lang")).toString());
        audioClip.setVolume(((double)Integer.parseInt(volume))/200);
        audioClip.play(((double)Integer.parseInt(volume))/200);

        modsSound = loginUser.getSound().substring(0,loginUser.getSound().length()-3) + volume;
        Users.getInstance().updateVolume(loginUser,modsSound);
        userPreferenceUpdate(loginUser);
        updateUsersTable();
    }

    @FXML
    void modsToggleButtons(ActionEvent event) throws SQLException {
        String modsSound;
        String modsText;

        modsSound = loginUser.getSound();
        modsText = loginUser.getText();

        if(event.getSource() == textModeToggle){
            textCheck = textModeToggle.isSelected();
            modsText = String.valueOf(textCheck);
        }else if(event.getSource() == soundModeToggle){
            if(soundModeToggle.isSelected()){
                soundControl(true);
            }else{
                soundControl(false);
                soundCheckEx = false;
            }
            modsSound = soundCheck + volume;
        }else if(event.getSource() == soundExtraToggle){
            if(soundExtraToggle.isSelected()){
                soundCheckEx = true;
                modsSound = "trueEx" + volume;
            }else{
                soundCheckEx = false;
                modsSound = soundCheck + volume;
            }
        }
        Users.getInstance().updateVolume( loginUser, modsSound);
        Users.getInstance().updateText( loginUser, modsText);
        userPreferenceUpdate( loginUser );
        updateUsersTable();
    }

    public void soundControl (Boolean check) {
        soundHelperLabel.setVisible(check);
        soundExtraToggle.setVisible(check);
        soundVolumeSlider.setVisible(check);
        soundVolumeLabel.setVisible(check);
        soundCheck = check;
    }
    //settings pane----home settings pane
    @FXML
    void homeSettingButtons(ActionEvent event) {
        if(event.getSource() == homeSettingElecButton){
            closeAllHomeSetting();
            settingElecSettingPane.setVisible(true);
            openHomeSetting();
            homeSettingElecButtonActive.setVisible(true);
        }else if (event.getSource() == homeSettingGasButton ){
            closeAllHomeSetting();
            settingGasSettingPane.setVisible(true);
            openHomeSetting();
            homeSettingGasButtonActive.setVisible(true);
        }else if (event.getSource() == homeSettingAquButton){
            closeAllHomeSetting();
            settingAquSettingPane.setVisible(true);
            openHomeSetting();
            homeSettingAquButtonActive.setVisible(true);
        }else if (event.getSource() == homeSettingGreenHouseButton){
            closeAllHomeSetting();
            settingGreenHouseSettingPane.setVisible(true);
            openHomeSetting();
            homeSettingGreenHouseButtonActive.setVisible(true);
        }else if (event.getSource() == homeSettingRFIDButton){
            closeAllHomeSetting();
            settingRFIDSettingPane.setVisible(true);
            openHomeSetting();
            homeSettingRFIDButtonActive.setVisible(true);
        }
    }

    public void openHomeSetting(){
        homeSettingSubPane.setVisible(true);
        homeSettingButtonActive.setVisible(true);
    }

    public void closeAllHomeSetting(){
        settingElecSettingPane.setVisible(false);
        settingGasSettingPane.setVisible(false);
        settingAquSettingPane.setVisible(false);
        settingGreenHouseSettingPane.setVisible(false);
        settingRFIDSettingPane.setVisible(false);
        homeSettingElecButtonActive.setVisible(false);
        homeSettingGasButtonActive.setVisible(false);
        homeSettingAquButtonActive.setVisible(false);
        homeSettingGreenHouseButtonActive.setVisible(false);
        homeSettingRFIDButtonActive.setVisible(false);
        homeSettingSubPane.setVisible(false);
        homeSettingButtonActive.setVisible(false);
    }
}
