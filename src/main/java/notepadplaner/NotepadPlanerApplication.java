package notepadplaner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import notepadplaner.models.Note;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;
import java.util.ArrayList;

/**
 * Main application class. Extends Application class.
 *
 * @author Mateo, Rafael
 */
public class NotepadPlanerApplication extends Application {
    /**
     * Bound application instance. Used in navbar for navigation.
     */
    private static NotepadPlanerApplication applicationInstance;

    /**
     * Stage of the application. Its scene is changed on navigation.
     */
    private Stage stage;

    /**
     * FXML notes button element injection / reference. Used as one of the main options in main scene.
     */
    @FXML
    private Button notesButton;

    /**
     * FXML to-do lists button element injection / reference. Used as one of the main options in main scene.
     */
    @FXML
    private Button toDoListsButton;

    /**
     * ApplicationInstance getter.
     *
     * @return applicationInstance
     * @author Mateo
     */
    public static NotepadPlanerApplication getApplicationInstance() {
        return applicationInstance;
    }

    /**
     * NotepadPlanerApplication class initialization method. Executes on class initialization. Sets applicationInstance.
     *
     * @author Mateo
     */
    @Override
    public void init() {
        applicationInstance = this;
    }

    /**
     * Loads main view. Sets application stage and window properties.
     *
     * @author Mateo
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotepadPlanerView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1440, 720);
            this.stage.setTitle("Notepad planer");
            this.stage.setMinWidth(720);
            this.stage.setMinHeight(360);
            this.stage.setScene(scene);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Overloaded method. Changes scene. Uses button element as invocation location.
     *
     * @param url URL of view to load.
     * @param button Element from which the function was called.
     * @author Mateo
     */
    public void changeScene(String url, Button button) {
        try {
            Scene currentScene = button.getScene();
            Stage currentStage = (Stage)currentScene.getWindow();

            Parent newScene = new FXMLLoader(getClass().getResource(url)).load();
            currentStage.setScene(new Scene(newScene, currentScene.getWidth(), currentScene.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Overloaded method. Changes scene. Uses hyperlink element as invocation location.
     *
     * @param url URL of view to load.
     * @param hyperlink Element from which the function was called.
     * @author Mateo
     */
    public void changeScene(String url, Hyperlink hyperlink) {
        try {
            Scene currentScene = hyperlink.getScene();
            Stage currentStage = (Stage)currentScene.getWindow();

            Parent newScene = new FXMLLoader(getClass().getResource(url)).load();
            currentStage.setScene(new Scene(newScene, currentScene.getWidth(), currentScene.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * FXML goToNotes method injection / reference and implementation. Changes scene to list of notes by calling
     * changeScene method.
     *
     * @param event Action event, Not used in method.
     * @author Mateo
     */
    @FXML
    public void goToNotes(ActionEvent event) {
        changeScene("controllers/NotesView.fxml", notesButton);
    }

    /**
     * FXML goToToDoLists method injection / reference and implementation. Changes scene to list of to-do lists by
     * calling changeScene method.
     *
     * @param event Action event, Not used in method.
     * @author Mateo
     */
    @FXML
    public void goToToDoLists(ActionEvent event) {
        changeScene("controllers/ToDoListsView.fxml", toDoListsButton);
    }

    /**
     * Class main function. Launches the application. Also used for launching the application in production JAR.
     *
     * @param args Action event, Not used in method.
     * @author Mateo
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Primjeri CRUD operacija nad Notama
     *
     * @author Rafael
     */
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
        Note.create(note1);

        // Edit Note
        Note note3 = Note.get(0);
        note3.note = "Changed note";
        Note.edit(0, note3);

        // Delete Note
        Note.delete(0);
    }

    /**
     * Primjeri CRUD operacija nad TodoList
     *
     * @author Rafael
     */
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
        TodoList.create(todoList1);

        // Edit TodoList example
        TodoList todoList3 = TodoList.get(0);
        todoList3.title = "Edited title";
        todoList3.items[2].text = "random";
        todoList3.items[0].checked = true;
        TodoList.edit(0, todoList3);

        // Delete TodoList
        TodoList.delete(0);
    }

    /**
     * Primjeri CRUD operacija nad TodoListItem
     *
     * @author Rafael
     */
    private static void crudExampleTodoListItem() {
        // Remove item from list
        TodoList.removeItem(1, 0);
        TodoList.removeItem(2, 3);

        // Add empty item on List
        TodoList.addItem(1, new TodoListItem("Abcd", false));
        TodoList.addItem(2, new TodoListItem("Abcdee", true));
    }
}