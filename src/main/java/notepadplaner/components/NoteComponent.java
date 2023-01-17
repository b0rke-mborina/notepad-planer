package notepadplaner.components;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import notepadplaner.models.Note;

public class NoteComponent extends VBox {
    public Text titleText;
    public Text noteText;

    public NoteComponent(Note note) {
        titleText.setText(note.title);
        noteText.setText(note.note);
    }
}
