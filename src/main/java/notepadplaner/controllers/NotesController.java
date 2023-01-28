package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import notepadplaner.components.NavBar;

public class NotesController {
    @FXML
    private Parent navBar;
    @FXML
    private NavBar navBarController;
    @FXML
    private FlowPane contentBox;

    public void initialize() {
        System.out.println("Notes controller initialized.");
    }

    public void addNewNote() {
        System.out.println("Add new note button clicked.");
    }
}
