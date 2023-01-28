package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import notepadplaner.components.NavBar;

public class ToDoListsController extends BaseController {
    @FXML
    private AnchorPane navBar;
    @FXML
    private NavBar navBarController;
    @FXML
    private FlowPane contentBox;

    public void initialize() {
        System.out.println("ToDoLists controller initialized.");
    }

    public void addNewToDoList() {
        System.out.println("Add new to-do list button clicked.");
    }
}
