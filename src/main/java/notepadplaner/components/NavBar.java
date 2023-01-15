package notepadplaner.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class NavBar {
    @FXML
    private Hyperlink notesNavLink;
    @FXML
    private Hyperlink toDoListsNavLink;
    @FXML
    private Button backButton;

    public void goToNotes(ActionEvent event) {
        System.out.println("Notes link clicked.");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/notepadplaner/controllers/NotesView.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Notepad planer");
            stage.setMinWidth(720);
            stage.setMinHeight(360);
            stage.setScene(new Scene(root, 1440, 720));
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToToDoLists(ActionEvent event) {
        System.out.println("ToDoLists link clicked.");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/notepadplaner/controllers/ToDoListsView.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Notepad planer");
            stage.setMinWidth(720);
            stage.setMinHeight(360);
            stage.setScene(new Scene(root, 1440, 720));
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnToMainMenu(ActionEvent event) {
        System.out.println("ToDoLists link clicked.");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/notepadplaner/NotepadPlanerView.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Notepad planer");
            stage.setMinWidth(720);
            stage.setMinHeight(360);
            stage.setScene(new Scene(root, 1440, 720));
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void foo(String foo) {
        System.out.println(foo);
    }
}
