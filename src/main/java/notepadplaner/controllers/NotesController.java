package notepadplaner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import notepadplaner.components.NavBar;
import notepadplaner.components.NoteComponent;
import notepadplaner.models.Note;

import java.util.ArrayList;


public class NotesController extends BaseController {
    @FXML
    private Parent navBar;
    @FXML
    private NavBar navBarController;
    @FXML
    private FlowPane contentBox;

    public void initialize() {
        System.out.println("Notes controller initialized.");
        ArrayList<Note> notes = Note.getAll();
        System.out.println(notes);
        for (Note note : notes) {
            System.out.println("Title: " + note.title);
            System.out.println("Body: " + note.note);
            System.out.println("Index: " + notes.indexOf(note));
            NoteComponent noteComponent = new NoteComponent(note, notes.indexOf(note) + 1);
            System.out.println(contentBox.getChildren());
            contentBox.getChildren().add(noteComponent);
        }
    }

    public void addNewNote(ActionEvent event) {
        System.out.println("Add new note button clicked.");
        // setSceneUserDataFromNode((Node) event.getSource());
        changeScene("controllers/NoteView.fxml", event);
    }

    public void showNote(MouseEvent mouseEvent) {
        setSceneUserDataFromNode((Node) mouseEvent.getSource());
        changeScene("controllers/NoteView.fxml", mouseEvent);
    }
}
