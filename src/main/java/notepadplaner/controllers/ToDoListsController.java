package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import notepadplaner.components.ToDoListComponent;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;
import java.util.ArrayList;

public class ToDoListsController extends BaseController {
    /**
     * FXML root element of view injection / reference. Used for saving data.
     */
    @FXML
    private HBox root;

    /**
     * FXML vbox element of view injection / reference. Used as parent (list) for adding to-do list components (items).
     */
    @FXML
    private VBox contentBox;

    /**
     * Root getter.
     *
     * @return Node
     * @author Rafael
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Loads all to-do lists from TXT file in a shape of to-do list components and adds them to content box.
     *
     * @author Mateo, Rafael
     */
    public void initialize() {
        ArrayList<TodoList> todoLists = TodoList.getAll();
        for (TodoList todoList : todoLists) {
            contentBox.getChildren().add(new ToDoListComponent(todoList, todoLists.indexOf(todoList) + 1));
        }
    }

    /**
     * Changes to view / scene for creating new note.
     * Adds new to-do list with one item and reloads scene for new to-do list component to be visible.
     *
     * @author Mateo, Rafael
     */
    public void addNewToDoList() {
        TodoList.create(new TodoList("New list", new TodoListItem[] { new TodoListItem("Task 1") }));
        changeScene("controllers/ToDoListsView.fxml");
    }
}
