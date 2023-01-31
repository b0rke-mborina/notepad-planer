package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import notepadplaner.models.Note;

/**
 * Controller for creating new note. Extends BaseController. Contains FXML element and method injections / references
 * and implementations.
 *
 * @author Mateo, Rafael
 */
public class NewNoteController extends BaseController {
    /**
     * FXML root element of view injection / reference. Used for saving data.
     */
    @FXML
    private HBox root;

    /**
     * FXML note title text field element injection / reference. Used for adding note title.
     */
    @FXML
    private TextField titleField;

    /**
     * FXML note body text area element injection / reference. Used for adding note body.
     */
    @FXML
    private TextArea noteField;

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
     * FXML createNote method injection / reference and implementation. Creates new note by calling Note create method
     * which saved note to TXT file and returns to list of notes .
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void createNote() {
        Note.create(new Note(titleField.getText(), noteField.getText()));
        goBack();
    }

    /**
     * FXML cancelNote method injection / reference and implementation. Cancels note changes and returns to list of notes
     * by calling goBack method.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void cancelNote() {
        goBack();
    }

    /**
     * Changes scene to list of notes.
     *
     * @author Mateo, Rafael
     */
    private void goBack() {
        changeScene("controllers/NotesView.fxml");
    }
}
