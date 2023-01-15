package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import notepadplaner.components.NavBar;

public class NotesController {
    @FXML
    private Parent navBar;
    @FXML
    private NavBar navBarController;

    public void initialize() {
        System.out.println(navBarController);
        System.out.println(navBar);
        navBarController.foo("It works");
    }
}
