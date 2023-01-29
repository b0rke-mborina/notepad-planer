package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import notepadplaner.models.Note;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;

public class ToDoListController extends BaseController {
    public TextField titleField;
    public TextField[] itemsFieldList;
    public HBox root;

    public void initialize() {
        Platform.runLater(() -> {
            Scene currentScene = root.getScene();
            Stage currentStage = (Stage)currentScene.getWindow();
            loadToDoList(Integer.parseInt(currentStage.getUserData().toString()));
        });
    }

    private void loadToDoList(int index) {
        TodoList todoList = TodoList.get(index);

        titleField.setText(todoList.title);
        // itemsFieldList.setText(note.note);
    }

    public void saveToDoList(ActionEvent actionEvent) {
        System.out.println("Save toDoList button clicked.");
    }

    public void cancelToDoList(ActionEvent actionEvent) {
        System.out.println("Cancel toDoList button clicked.");
    }

    public void deleteToDoList(ActionEvent actionEvent) {
        System.out.println("Delete toDoList button clicked.");
    }
}
