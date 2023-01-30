package notepadplaner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import notepadplaner.components.NavBar;
import notepadplaner.components.ToDoListComponent;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;

import java.util.ArrayList;

public class ToDoListsController extends BaseController {
    @FXML
    private HBox root;
    @FXML
    private VBox contentBox;

    public Node getRoot() {
        return root;
    }

    public void initialize() {
        ArrayList<TodoList> todoLists = TodoList.getAll();
        for (TodoList todoList : todoLists) {
            contentBox.getChildren().add(new ToDoListComponent(todoList, todoLists.indexOf(todoList) + 1));
        }
    }

    public void addNewToDoList() {
        TodoList.create(new TodoList("New list", new TodoListItem[] { new TodoListItem("Task 1") }));
        changeScene("controllers/ToDoListsView.fxml");
    }
}
