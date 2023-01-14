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

        // Create model
        // Note myNote1 = new Note("This is title", "This is content");
        // myNote1.saveToFile();

        // Get one
        // Note myNote2 = Note.get(0);

        // Get all
        /*
        ArrayList<Note> notes = Note.getAll();
        for (Note note : notes) {
            System.out.println();
            note.print();
        }
        */

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
        // TodoList myTodoList = TodoList.get(2);

        // Edit TodoList example
        // ... to be continued

        // Delete TodoList
        // ... to be continued
    }
}