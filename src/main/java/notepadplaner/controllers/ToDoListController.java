package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import notepadplaner.models.TodoList;

public class ToDoListController extends BaseController {
    public TextField titleField;
    public TextField[] itemsFieldList;
    public HBox root;
    private int index;

    public void initialize() {
        Platform.runLater(() -> {
            Scene currentScene = root.getScene();
            Stage currentStage = (Stage)currentScene.getWindow();
            loadToDoList(Integer.parseInt(currentStage.getUserData().toString()));
        });
    }

    private void loadToDoList(int index) {
        this.index = index - 1;
        TodoList todoList = TodoList.get(index - 1);

        titleField.setText(todoList.title);
        // itemsFieldList.setText(note.note);
    }

    public void saveToDoList(ActionEvent actionEvent) {
        System.out.println("Save toDoList button clicked.");
        goBack(actionEvent);
    }

    public void cancelToDoList(ActionEvent actionEvent) {
        goBack(actionEvent);
    }

    public void deleteToDoList(ActionEvent actionEvent) {
        TodoList.delete(index);
        goBack(actionEvent);
    }

    private void goBack(Event event) {
        changeScene("controllers/ToDoListsView.fxml", event);
    }
}
