package org.openjfx;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationPage {
    public TextField username;
    public PasswordField password;
    public PasswordField confirmPassword;
    public Button registerButton;

    public void register(ActionEvent actionEvent) {
        if(password.getText().equals(confirmPassword.getText())){
            if(!FileController.isPathValid(username.getText())){
                FileController.createUserFolder(username.getText());
                FileController.createUserFile(username.getText(), password.getText());
                FileController.createPasswordMangerFolder(username.getText());
                GenerateKeys generateKeys = new GenerateKeys(1024);
                generateKeys.saveKeys(username.getText());
                username.clear();
                password.clear();
                confirmPassword.clear();
            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        FXMLSwitcher.setStage(actionEvent,"scene.fxml");
    }
}
