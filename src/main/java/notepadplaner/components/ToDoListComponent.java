package notepadplaner.components;

import javafx.scene.text.Text;
import notepadplaner.models.TodoList;

public class ToDoListComponent {
    public Text titleField;
    public Text itemsFieldList;

    public ToDoListComponent(TodoList todoList) {
        titleField.setText(todoList.title);
        // add \n and items' texts to string
    }
}
