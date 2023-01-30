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
    @FXML
    private HBox root;
    @FXML
    private FlowPane contentBox;

    public Node getRoot() {
        return root;
    }

    public void initialize() {
        ArrayList<Note> notes = Note.getAll();
        for (Note note : notes) {
            contentBox.getChildren().add(new NoteComponent(note, notes.indexOf(note) + 1));
        }
    }

    /**
     * @author Mateo, Rafael
     */
    public void addNewNote() {
        changeScene("controllers/NewNoteView.fxml");
    }
}
