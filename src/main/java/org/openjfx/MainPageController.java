package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class MainPageController {
    public AnchorPane sceneHolder;

    public void setCreationScene(ActionEvent actionEvent) throws IOException {
        Parent loader = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("creationScene.fxml"))));
        sceneHolder.getChildren().setAll(loader);

    }

    public void setLoginPage(ActionEvent actionEvent) {
        FXMLSwitcher.setStage(actionEvent,"scene.fxml");
    }

    public void setPasswordManagerScene(ActionEvent actionEvent) throws IOException {
        Parent loader = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("passwordManager.fxml"))));
        sceneHolder.getChildren().setAll(loader);
    }
}
