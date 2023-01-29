package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import notepadplaner.components.NavBar;
import notepadplaner.components.NoteComponent;
import notepadplaner.components.ToDoListComponent;
import notepadplaner.models.Note;
import notepadplaner.models.TodoList;

import java.util.ArrayList;
import java.util.Arrays;

public class ToDoListsController extends BaseController {
    @FXML
    private AnchorPane navBar;
    @FXML
    private NavBar navBarController;
    @FXML
    private VBox contentBox;

    public void initialize() {
        ArrayList<TodoList> todoLists = TodoList.getAll();
        // System.out.println(todoLists);
        for (TodoList todoList : todoLists) {
            // System.out.println("Title: " + todoList.title);
            // System.out.println("Body: " + Arrays.toString(todoList.items));
            // System.out.println("Index: " + todoLists.indexOf(todoList));
            ToDoListComponent toDoListComponent = new ToDoListComponent(todoList, todoLists.indexOf(todoList) + 1);
            contentBox.getChildren().add(toDoListComponent);
        }
    }

    public void addNewToDoList() {
        System.out.println("Add new to-do list button clicked.");
    }
}
