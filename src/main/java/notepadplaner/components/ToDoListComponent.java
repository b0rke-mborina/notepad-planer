package notepadplaner.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import notepadplaner.controllers.ToDoListComponentController;
import notepadplaner.models.TodoList;

import java.io.IOException;
import java.util.Arrays;

public class ToDoListComponent extends GridPane {
    private Node view;
    private ToDoListComponentController controller;

    public ToDoListComponent(TodoList toDoList, int userData) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ToDoListComponent.fxml"));
        fxmlLoader.setControllerFactory(param -> controller = new ToDoListComponentController());
        try {
            view = (Node) fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getChildren().add(view);
        controller.titleText.setText(toDoList.title);
        controller.toDoListText.setText(Arrays.toString(toDoList.items));
        controller.showButton.setUserData(userData);
    }
}
