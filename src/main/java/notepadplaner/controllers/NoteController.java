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

public class NoteController extends BaseController {
    public TextField titleField;
    public TextArea noteField;
    public HBox root;
    private int index;

    public void initialize() {
        Platform.runLater(() -> {
            Scene currentScene = root.getScene();
            Stage currentStage = (Stage)currentScene.getWindow();
            loadNote(Integer.parseInt(currentStage.getUserData().toString()));
        });
    }

    private void loadNote(int index) {
        this.index = index - 1;
        Note note = Note.get(this.index);

        titleField.setText(note.title);
        noteField.setText(note.note);
    }

    public void saveNote(ActionEvent actionEvent) {
        // System.out.println("Note " + index + " saved.");
        Note.edit(index, new Note(
            titleField.getText(),
            noteField.getText()
        ));
        goBack(actionEvent);
    }

    public void cancelNote(ActionEvent actionEvent) {
        // System.out.println("Cancel note button clicked.");
        goBack(actionEvent);
    }

    public void deleteNote(ActionEvent actionEvent) {
        // System.out.println("Note " + index + " deleted.");
        Note.delete(this.index);
        goBack(actionEvent);
    }

    private void goBack(Event event) {
        changeScene("controllers/NotesView.fxml", event);
    }
}