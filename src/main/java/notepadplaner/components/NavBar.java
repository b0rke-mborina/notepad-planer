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
        System.out.println("Notes link clicked.");
        notepadPlaner.changeScene("/notepadplaner/controllers/NotesView.fxml", notesNavLink);
    }

    public void goToToDoLists(ActionEvent event) {
        System.out.println("ToDoLists link clicked.");
        notepadPlaner.changeScene("/notepadplaner/controllers/ToDoListsView.fxml", toDoListsNavLink);
    }

    public void returnToMainMenu(ActionEvent event) {
        System.out.println("Back button clicked.");
        notepadPlaner.changeScene("/notepadplaner/NotepadPlanerView.fxml", backButton);
    }

    public void foo(String foo) {
        System.out.println(foo);
    }
}
