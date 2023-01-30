package notepadplaner.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notepadplaner.NotepadPlanerApplication;
import notepadplaner.interfaces.IController;

import java.util.Objects;

/**
 * Osnovna abstraktna klasa za potporu okvira učestalih funkcionalnosti kontrolera aplikacije
 *
 * @author Rafael
 */
abstract class BaseController implements IController {
    /**
     * Vraća definirane podatke trenutnog Stage-a.
     *
     * @return Object UserData kojeg podržava JavaFX
     * @author Rafael
     */
    protected Object getStageUserData() {
        Scene currentScene = getRoot().getScene();
        Stage currentStage = (Stage)currentScene.getWindow();
        return currentStage.getUserData();
    }

    /**
     * Mijenja scenu trenutnog Stage-a, postavljajući podatke (UserData) događaja na stage.
     *
     * @param url Putanja resursa scene fxml-a
     * @param event Događaj sa podacima UserData
     * @author Rafael
     */
    protected void changeScene(String url, Event event) {
        Node node = (Node) event.getSource();
        stageSetter(url, node, node.getUserData());
    }

    /**
     * Mijenja scenu trenutnog Stage-a.
     *
     * @param url Putanja resursa scene fxml-a
     * @author Rafael
     */
    protected void changeScene(String url) {
        Node root = getRoot();
        stageSetter(url, root, getStageUserData());
    }

    /**
     * Postavlja novu scenu na trenutni Stage sa podacima
     *
     * @param url Putanja resursa scene fxml-a
     * @param node Čvor sa trenutne scene
     * @param data Podaci koji će se postaviti na Stage
     * @author Rafael
     */
    private void stageSetter(String url, Node node, Object data) {
        try {
            Scene currentScene = node.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            Parent newScene = FXMLLoader.load(Objects.requireNonNull(NotepadPlanerApplication.class.getResource(url)));

            currentStage.setScene(new Scene(newScene, currentScene.getWidth(), currentScene.getHeight()));
            currentStage.setUserData(data);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
