package org.openjfx;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {
    public PasswordField passwordField;
    public TextField usernameField;

    public void goRegister(ActionEvent actionEvent) {
        FXMLSwitcher.setStage(actionEvent,"registrationPage.fxml");
    }

    public void goLogin(ActionEvent actionEvent) {
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            if (FileController.isPathValid(usernameField.getText())) {
                if (FileController.readFile(usernameField.getText(), passwordField.getText())) {
                    FXMLSwitcher.setStage(actionEvent, "mainPage.fxml");
                    User.username = usernameField.getText();
                }
            }
        }
    }
}

