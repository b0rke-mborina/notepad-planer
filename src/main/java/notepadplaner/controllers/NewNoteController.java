package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import notepadplaner.models.Note;

public class NewNoteController extends BaseController {
    public TextField titleField;
    public TextArea noteField;

    public void createNote(ActionEvent actionEvent) {
        Note.create(new Note(
            titleField.getText(),
            noteField.getText()
        ));
        goBack(actionEvent);
    }

    public void cancelNote(ActionEvent actionEvent) {
        goBack(actionEvent);
    }

    private void goBack(Event event) {
        changeScene("controllers/NotesView.fxml", event);
    }
}