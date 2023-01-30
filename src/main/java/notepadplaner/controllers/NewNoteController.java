package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import notepadplaner.models.Note;

/**
 * @author Mateo, Rafael
 */
public class NewNoteController extends BaseController {
    @FXML
    private HBox root;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea noteField;

    public Node getRoot() {
        return root;
    }

    /**
     * @author Mateo, Rafael
     */
    public void createNote() {
        Note.create(new Note(titleField.getText(), noteField.getText()));
        goBack();
    }

    public void cancelNote() {
        goBack();
    }

    private void goBack() {
        changeScene("controllers/NotesView.fxml");
    }
}