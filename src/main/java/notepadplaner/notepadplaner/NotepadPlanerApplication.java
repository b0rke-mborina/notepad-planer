package notepadplaner.notepadplaner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notepadplaner.models.Note;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;

import java.io.IOException;
import java.lang.reflect.Array;

public class NotepadPlanerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NotepadPlanerApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // launch();

        // Create model
        // Note myNote1 = new Note("This is title", "This is content");
        // myNote1.saveToFile();

        // Get
        // Note myNote2 = Note.get(0);

        // Edit
        // Note myNote3 = Note.get(0);
        // myNote3.note = "Changed note";
        // Note.edit(0, myNote3);

        // Delete
        // Note.delete(2);


        // Saving TodoList example
        /*
        TodoList myTodoList = new TodoList("Test title", new TodoListItem[] {
            new TodoListItem("Item 1"),
            new TodoListItem("Item 2"),
            new TodoListItem("Item 3"),
        });
        myTodoList.saveToFile();
        */

        // Get TodoList example
        TodoList myTodoList = TodoList.get(2);

        // Edit TodoList example
        // ... to be continued

        // Delete TodoList
        // ... to be continued
    }
}