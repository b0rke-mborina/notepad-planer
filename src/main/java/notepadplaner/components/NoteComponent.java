package notepadplaner.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import notepadplaner.controllers.NoteComponentController;
import notepadplaner.models.Note;
import java.io.IOException;

/**
 * Note component class. Extends AnchorPane. Used for creating new note component items.
 *
 * @author Mateo
 */
public class NoteComponent extends AnchorPane {
    /**
     * Loaded view of the note component.
     */
    private Node view;

    /**
     * Instance of the note component controller.
     */
    private NoteComponentController controller;

    /**
     * NoteComponent parameterized constructor. Loads component view and adds data to FXML (controller / view) elements.
     *
     * @param note Note data class instance.
     * @param userData Index of note in TXT file (where the note is saved).
     * @author Mateo
     */
    public NoteComponent(Note note, int userData) {
        // load component FXML view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NoteComponent.fxml"));
        fxmlLoader.setControllerFactory(param -> controller = new NoteComponentController());
        try {
            view = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getChildren().add(view);

        // add data to FXML (controller / view) elements
        controller.titleText.setText(note.getShortTitle());
        controller.noteText.setText(note.getShortNote());
        controller.showButton.setUserData(userData);
    }
}
