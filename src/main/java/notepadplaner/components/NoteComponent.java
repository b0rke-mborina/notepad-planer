package notepadplaner.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import notepadplaner.controllers.NoteComponentController;
import notepadplaner.models.Note;
import java.io.IOException;

public class NoteComponent extends AnchorPane {
    private Node view;
    private NoteComponentController controller;

    public NoteComponent(Note note, int userData) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NoteComponent.fxml"));
        fxmlLoader.setControllerFactory(param -> controller = new NoteComponentController());
        try {
            view = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getChildren().add(view);
        controller.titleText.setText(note.getShortTitle());
        controller.noteText.setText(note.getShortNote());
        controller.showButton.setUserData(userData);
    }
}
