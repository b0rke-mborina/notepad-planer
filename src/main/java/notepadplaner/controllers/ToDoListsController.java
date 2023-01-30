package notepadplaner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import notepadplaner.components.NavBar;
import notepadplaner.components.ToDoListComponent;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;

import java.util.ArrayList;

public class ToDoListsController extends BaseController {
    public HBox root;
    @FXML
    private AnchorPane navBar;
    @FXML
    private NavBar navBarController;
    @FXML
    private VBox contentBox;

    public void initialize() {
        ArrayList<TodoList> todoLists = TodoList.getAll();
        for (TodoList todoList : todoLists) {
            ToDoListComponent toDoListComponent = new ToDoListComponent(todoList, todoLists.indexOf(todoList) + 1);
            contentBox.getChildren().add(toDoListComponent);
        }
    }

    public void addNewToDoList(ActionEvent event) {
        (new TodoList("New list", new TodoListItem[] { new TodoListItem("Task 1") })).saveToFile();
        changeScene("controllers/ToDoListsView.fxml", event);
    }
}
