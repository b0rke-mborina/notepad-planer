package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import notepadplaner.models.Note;

/**
 * Controller for modifying a note. Extends BaseController. Contains FXML element and method injections / references
 * and implementations.
 *
 * @author Mateo, Rafael
 */
public class NoteController extends BaseController {
    /**
     * FXML root element of view injection / reference. Used for saving data.
     */
    @FXML
    private HBox root;

    /**
     * FXML note title text field element injection / reference. Used for showing / editing note title.
     */
    @FXML
    private TextField titleField;

    /**
     * FXML note body text area element injection / reference. Used for showing / editing note body.
     */
    @FXML
    private TextArea noteField;

    /**
     * Index of note in TXT file. Used for saving data.
     */
    private int index;

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
     * Loads data after the view is loaded.
     *
     * @author Mateo, Rafael
     */
    public void initialize() {
        Platform.runLater(() -> loadNote(Integer.parseInt(getStageUserData().toString())));
    }

    /**
     * Loads note and adds its data to view.
     *
     * @param index Index of the note in TXT file.
     * @author Rafael
     */
    private void loadNote(int index) {
        // load note by index
        this.index = index - 1;
        Note note = Note.get(this.index);

        // add data to FXML elements
        titleField.setText(note.title);
        noteField.setText(note.note);
    }

    /**
     * FXML saveNote method injection / reference and implementation. Updates note by calling Note edit method, which
     * edits note in TXT file, and returns to list of notes.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void saveNote() {
        Note.edit(index, new Note(titleField.getText(), noteField.getText()));
        goBack();
    }

    /**
     * FXML cancelNote method injection / reference and implementation. Cancels note changes and changes scene to list of
     * notes.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void cancelNote() {
        goBack();
    }

    /**
     * FXML deleteNote method injection / reference and implementation. Deletes note by calling Note delete method and
     * changes scene to list of notes.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void deleteNote() {
        Note.delete(this.index);
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