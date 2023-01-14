package notepadplaner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import notepadplaner.models.Note;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class NotepadPlanerApplication extends Application {
    // private BorderPane layout;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotepadPlanerView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1440, 720);
            stage.setTitle("Notepad planer");
            stage.setMinWidth(720);
            stage.setMinHeight(360);

            // layout = new BorderPane();
            // layout.setLeft(new BorderPane(new Button("OK")));
            // setView(viewOne);

            // FXMLLoader loaderNavBar = new FXMLLoader(getClass().getResource("components/NavBar.fxml"));
            // fxmlLoader.setController(new CustomElementController()); //Or just specify the Controller in the FXML file
            // stage.getChildren().add(fxmlLoader.load());

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private static void crudExampleNotes() {
        // Get one Note
        Note note2 = Note.get(0);
        note2.print();

        // Get all Notes
        ArrayList<Note> allNotes = Note.getAll();
        for (Note note : allNotes) {
            System.out.println();
            note.print();
        }

        // Create Note
        Note note1 = new Note("This is title", "This is content");
        Note.saveToFile(note1);

        // Edit Note
        Note note3 = Note.get(0);
        note3.note = "Changed note";
        Note.edit(0, note3);

        // Delete Note
        Note.delete(0);
    }

    private static void crudExampleTodoList() {
        // Get TodoList
        TodoList todoList2 = TodoList.get(0);
        todoList2.print();

        // Get all TodoList
        ArrayList<TodoList> allTodoLists = TodoList.getAll();
        for (TodoList todoList : allTodoLists) {
            System.out.println();
            todoList.print();
        }

        // Create TodoList
        TodoList todoList1 = new TodoList("Test title", new TodoListItem[] {
                new TodoListItem("Item 1"),
                new TodoListItem("Item 2"),
                new TodoListItem("Item 3"),
        });
        todoList1.saveToFile();

        // Edit TodoList example
        TodoList todoList3 = TodoList.get(0);
        todoList3.title = "Edited title";
        todoList3.items[2].text = "random";
        todoList3.items[0].checked = true;
        TodoList.edit(0, todoList3);

        // Delete TodoList
        TodoList.delete(0);
    }

    private static void crudExampleTodoListItem() {
        // Add item on List
        // ...

        // Edit item on List
        // ...

        // Remove item from list
        // ...

        // Todo: Create crud operation on TodoListItems
        // Todo: Error handling + custom exceptions
    }
}