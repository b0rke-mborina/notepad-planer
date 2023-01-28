package notepadplaner.controllers;

import javafx.scene.control.TextField;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;

public class ToDoListController extends BaseController {
    public TextField titleField;
    public TextField[] itemsFieldList;

    public ToDoListController(TodoList todoList) {
        titleField.setText(todoList.title);
        // itemsFieldList
    }

    public void initialize() {
        System.out.println("TodoList scene");
    }
}
