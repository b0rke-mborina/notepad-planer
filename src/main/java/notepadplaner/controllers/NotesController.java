package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import notepadplaner.components.NoteComponent;
import notepadplaner.models.Note;
import java.util.ArrayList;

/**
 * @author Mateo, Rafael
 */
public class NotesController extends BaseController {
    /**
     * FXML root element of view injection / reference. Used for saving data.
     */
    @FXML
    private HBox root;

    /**
     * FXML flow pane element of view injection / reference. Used as parent (list) for adding note components (items).
     */
    @FXML
    private FlowPane contentBox;

    /**
     * Root getter.
     *
     * @return Node
     * @author Rafael
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Loads all notes from TXT file in a shape of note components and adds them to content box.
     *
     * @author Mateo, Rafael
     */
    public void initialize() {
        ArrayList<Note> notes = Note.getAll();
        for (Note note : notes) {
            contentBox.getChildren().add(new NoteComponent(note, notes.indexOf(note) + 1));
        }
    }

    /**
     * Changes to view / scene for creating new note.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void addNewNote() {
        changeScene("controllers/NewNoteView.fxml");
    }
}
