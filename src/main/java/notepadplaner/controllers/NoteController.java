package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import notepadplaner.models.Note;

/**
 * @author Mateo, Rafael
 */
public class NoteController extends BaseController {
    @FXML
    private HBox root;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea noteField;
    private int index;

    public Node getRoot() {
        return root;
    }

    /**
     * @author Mateo, Rafael
     */
    public void initialize() {
        Platform.runLater(() -> loadNote(Integer.parseInt(getStageUserData().toString())));
    }

    private void loadNote(int index) {
        this.index = index - 1;
        Note note = Note.get(this.index);

        titleField.setText(note.title);
        noteField.setText(note.note);
    }

    public void saveNote() {
        Note.edit(index, new Note(titleField.getText(), noteField.getText()));
        goBack();
    }

    public void cancelNote() {
        goBack();
    }

    public void deleteNote() {
        Note.delete(this.index);
        goBack();
    }

    private void goBack() {
        changeScene("controllers/NotesView.fxml");
    }
}