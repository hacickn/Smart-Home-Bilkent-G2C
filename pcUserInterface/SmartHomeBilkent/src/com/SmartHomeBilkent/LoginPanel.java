package com.SmartHomeBilkent;

import com.SmartHomeBilkent.dataBase.Users;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPanel implements Initializable {

    //properties
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
        if (userNameField.getText().length() > 0 && passwordField.getText().length() > 0) {
            for (User s : Users.getInstance().getUserList()) {
                if (userNameField.getText().equals(s.getUserName()) && passwordField.getText().equals(s.getPassword())) {
                    try {
                        String fxmlAddress;
                        if(s.getUserType().equals("ELDER") || s.getUserType().equals("ÄLTERE") || s.getUserType().equals("YASLI") || s.getUserType().equals("YASLİ"))
                            fxmlAddress = "elderMainPanel.fxml";
                        else
                            fxmlAddress = "mainPanel.fxml";
                        s.setEnter("true");
                        FXMLLoader load = new FXMLLoader(getClass().getResource(fxmlAddress));
                        Parent root = load.load();
                        Stage stage = new Stage();
                        stage.setTitle("SMART HOME");
                        stage.setScene(new Scene(root, 800, 800));
                        stage.setResizable(false);
                        stage.show();
                        userNameField.getScene().getWindow().hide();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            waningLabel.setText("USERNAME/PASSWORD IS WRONG");
        }else{
            waningLabel.setText("PLEASE ENTER BOTH PASSWORD AND USERNAME");
        }
        waningLabel.setVisible(true);
    }

    @FXML
    void controlPressedCapslock(KeyEvent event) {
        if(capsLock && event.getCode() == KeyCode.CAPS ){
            capslockOpen.setVisible(true);
            capsLock = false;
        }
        else if (!capsLock && event.getCode() == KeyCode.CAPS  ){
            capslockOpen.setVisible(false);
            capsLock = true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        capsLock = (Toolkit.getDefaultToolkit().getLockingKeyState(java.awt.event.KeyEvent.VK_CAPS_LOCK));
        if (capsLock){
            capslockOpen.setVisible(true);
            capsLock = false;
        }else {
            capsLock = true;
        }
    }
}
