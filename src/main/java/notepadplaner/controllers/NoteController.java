package notepadplaner.controllers;

import javafx.application.Platform;
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

    public void initialize() {
        Platform.runLater(() -> {
            Scene currentScene = root.getScene();
            Stage currentStage = (Stage)currentScene.getWindow();
            loadNote(Integer.parseInt(currentStage.getUserData().toString()));
        });
    }

    private void loadNote(int index) {
        Note note = Note.get(index);

        titleField.setText(note.title);
        noteField.setText(note.note);
    }
}