package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import notepadplaner.components.NavBar;

public class ToDoListsController {
    @FXML
    private AnchorPane navBar;
    @FXML
    private NavBar navBarController;

    public void initialize() {
        System.out.println(navBarController);
        System.out.println(navBar);
        navBarController.foo("It works");
    }
}
