package notepadplaner.controllers;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import notepadplaner.models.Note;

public class NoteController {
    public TextField titleField;
    public TextArea noteField;

    public NoteController(Note note) {
        titleField.setText(note.title);
        noteField.setText(note.note);
    }

    public void initialize() {
        System.out.println("Note scene");
    }
}
