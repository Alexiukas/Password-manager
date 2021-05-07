package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


public class CreationSceneController {
    private final RandomPasswordGenerator rpg = new RandomPasswordGenerator();
    private final RSA rsa = new RSA();
    public TextField titleField;
    public PasswordField passwordField;
    public TextField applicationField;
    public TextField informationField;
    public Label passHolder;


    public void savePassword(ActionEvent actionEvent) {
        FileController.createPasswordFile(titleField.getText(), rsa.encrypt(passwordField.getText()),applicationField.getText(), informationField.getText());
        titleField.clear();
        passwordField.clear();
        applicationField.clear();
        informationField.clear();
    }


    public void generate(ActionEvent actionEvent) {
        passHolder.setText(rpg.generatePassword());
    }

    public void copy(ActionEvent actionEvent) {
        String myString = passHolder.getText();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
