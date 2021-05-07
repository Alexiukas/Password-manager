package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FXMLSwitcher {
    public AnchorPane getFXML(String fileName){
        AnchorPane pane = null;
        try {
            pane  = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));

        } catch (Exception e){
            e.printStackTrace();

        }
        return pane;
    }

    public static void setStage (ActionEvent actionEvent, String fileName ){
        try{
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(FXMLSwitcher.class.getClassLoader().getResource(fileName))));
            primaryStage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
