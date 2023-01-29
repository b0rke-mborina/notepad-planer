package notepadplaner.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import notepadplaner.NotepadPlanerApplication;

public class NavBar {
    @FXML
    private Hyperlink notesNavLink;
    @FXML
    private Hyperlink toDoListsNavLink;
    @FXML
    private Button backButton;
    private NotepadPlanerApplication notepadPlaner = NotepadPlanerApplication.getApplicationInstance();

    public void goToNotes(ActionEvent event) {
        notepadPlaner.changeScene("/notepadplaner/controllers/NotesView.fxml", notesNavLink);
    }

    public void goToToDoLists(ActionEvent event) {
        notepadPlaner.changeScene("/notepadplaner/controllers/ToDoListsView.fxml", toDoListsNavLink);
    }

    public void returnToMainMenu(ActionEvent event) {
        notepadPlaner.changeScene("/notepadplaner/NotepadPlanerView.fxml", backButton);
    }
}
