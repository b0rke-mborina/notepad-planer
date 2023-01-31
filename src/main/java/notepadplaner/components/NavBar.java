package notepadplaner.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import notepadplaner.NotepadPlanerApplication;

/**
 * Navbar controller. Contains FXML element and method injections / references and implementations.
 *
 * @author Mateo
 */
public class NavBar {
    /**
     * FXML notes hyperlink element injection / reference. Used as one of the links in navbar.
     */
    @FXML
    private Hyperlink notesNavLink;

    /**
     * FXML to-do lists hyperlink element injection / reference. Used as one of the links in navbar.
     */
    @FXML
    private Hyperlink toDoListsNavLink;

    /**
     * FXML back button element injection / reference. Used for going back to main scene.
     */
    @FXML
    private Button backButton;

    /**
     * NotepadPlanerApplication instance of the class / controller. Used for navigation.
     */
    private NotepadPlanerApplication notepadPlaner = NotepadPlanerApplication.getApplicationInstance();

    /**
     * FXML goToNotes method injection / reference and implementation. Changes scene to list of notes by calling
     * changeScene method of private application instance.
     *
     * @param event Action event, Not used in method.
     * @author Mateo
     */
    @FXML
    public void goToNotes(ActionEvent event) {
        notepadPlaner.changeScene("/notepadplaner/controllers/NotesView.fxml", notesNavLink);
    }

    /**
     * FXML goToToDoLists method injection / reference and implementation. Changes scene to list of notes by calling
     * changeScene method of private application instance.
     *
     * @param event Action event, Not used in method.
     * @author Mateo
     */
    @FXML
    public void goToToDoLists(ActionEvent event) {
        notepadPlaner.changeScene("/notepadplaner/controllers/ToDoListsView.fxml", toDoListsNavLink);
    }

    /**
     * FXML returnToMainMenu method injection / reference and implementation. Changes scene to main menu by calling
     * changeScene method of private application instance.
     *
     * @param event Action event, Not used in method.
     * @author Mateo
     */
    @FXML
    public void returnToMainMenu(ActionEvent event) {
        notepadPlaner.changeScene("/notepadplaner/NotepadPlanerView.fxml", backButton);
    }
}
