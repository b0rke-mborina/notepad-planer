package notepadplaner.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notepadplaner.NotepadPlanerApplication;
import java.util.Objects;

public class BaseController {
    protected void changeScene(String url, Event event) {
        try {
            Scene currentScene = ((Node)event.getSource()).getScene();
            Stage currentStage = (Stage)currentScene.getWindow();
            Parent newScene = FXMLLoader.load(Objects.requireNonNull(NotepadPlanerApplication.class.getResource(url)));

            currentStage.setScene(new Scene(newScene, currentScene.getWidth(), currentScene.getHeight()));
            currentStage.setUserData(currentScene.getUserData());
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setSceneUserDataFromNode(Node node) {
        node.getScene().setUserData(node.getUserData());
    }
    protected void setSceneUserDataFromNode(Node node, Object data) {
        node.getScene().setUserData(data);
    }
}
