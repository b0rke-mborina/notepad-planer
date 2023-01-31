package notepadplaner.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import notepadplaner.controllers.ToDoListComponentController;
import notepadplaner.models.TodoList;
import java.io.IOException;
import java.util.Arrays;

/**
 * Note to-do list component class. Extends GridPane. Used for creating new to-do list component items.
 *
 * @author Mateo
 */
public class ToDoListComponent extends GridPane {
    /**
     * Loaded view of the to-do list component.
     */
    private Node view;
    /**
     * Instance of the to-do list component controller.
     */
    private ToDoListComponentController controller;

    /**
     * ToDoListComponent parameterized constructor. Loads component view and adds data to FXML (controller / view)
     * elements.
     *
     * @param toDoList TodoList data class instance.
     * @param userData Index of note in TXT file (where the to-do list is saved).
     * @author Mateo
     */
    public ToDoListComponent(TodoList toDoList, int userData) {
        // load component FXML view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ToDoListComponent.fxml"));
        fxmlLoader.setControllerFactory(param -> controller = new ToDoListComponentController());
        try {
            view = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getChildren().add(view);

        // add data to FXML (controller / view) elements
        controller.titleText.setText(toDoList.title);
        long completedItems = Arrays.stream(toDoList.items).filter(item -> item.checked).count();
        String percentage = Math.round(((double) completedItems / toDoList.items.length * 100)) + "% completed";
        controller.toDoListPercentage.setText(percentage);
        controller.showButton.setUserData(userData);
    }
}
