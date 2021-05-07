package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PasswordManagerController implements Initializable {

    public Label passLabel;
    public Label appLabel;
    public Label infoLabel;
    public Label titleLabel;
    public TextField searchBar;
    private final RSA rsa = new RSA();
    public Button passShow;
    @FXML
    private ListView<String> passTitleList;
    private final ObservableList<String> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = "C:\\Users\\Alex\\Desktop\\Password manager\\" + User.username + "\\Manager\\";
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for(File f : files){
            String filename = f.getName();
            String name = filename.replace(".txt","");
            observableList.addAll(name);
        }
        passTitleList.setItems(observableList);
    }

    public void show(ActionEvent actionEvent) {
        String item = passTitleList.getSelectionModel().getSelectedItem() + ".txt";
        passLabel.setText(rsa.decrypt(FileController.getPrivateKey(),item));
        passLabel.setText("*************");
        ArrayList<String> list = FileController.readPasswordFile(item);
        assert list != null;
        titleLabel.setText(list.get(0));
        appLabel.setText(list.get(1));
        infoLabel.setText(list.get(2));
        passShow.setText("Unhide");
    }


    public void delete(ActionEvent actionEvent) {
        int index = passTitleList.getSelectionModel().getSelectedIndex();
        String name = passTitleList.getSelectionModel().getSelectedItem() + ".txt";
        FileController.deletePasswordFile(name);
        observableList.remove(index);
    }

    public void search(KeyEvent keyEvent) {
        String searching = searchBar.getText();
        ObservableList<String> listHolder = FXCollections.observableArrayList();
        if(!observableList.isEmpty()) {
            for (String title : observableList) {
                if (title.startsWith(searching)) {
                    listHolder.addAll(title);
                    passTitleList.setItems(listHolder);
                } else {
                    passTitleList.setItems(listHolder);
                }
            }
        }
    }

    public void update(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update");
        dialog.getDialogPane().setContentText("Input new password:");
        Optional<String> newPass = dialog.showAndWait();
        TextField input = dialog.getEditor();
        if (input.getText() != null){
            FileController.updatePassword(passTitleList.getSelectionModel().getSelectedItem() + ".txt", input.getText());
        } else{
            System.out.println("No input");
        }
    }

    public void unhide(ActionEvent actionEvent) {
        if(passLabel.getText().startsWith("*************")) {
            passLabel.setText(rsa.decrypt(FileController.getPrivateKey(), passTitleList.getSelectionModel().getSelectedItem() + ".txt"));
            passShow.setText("Hide");
        } else {
            passLabel.setText("*************");
            passShow.setText("Unhide");
        }
    }

}
