package notepadplaner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import notepadplaner.components.NavBar;


public class NotesController extends BaseController {
    @FXML
    private Parent navBar;
    @FXML
    private NavBar navBarController;
    @FXML
    private FlowPane contentBox;

    public void initialize() {
        System.out.println("Notes controller initialized.");
    }

    public void addNewNote(ActionEvent event) {
        System.out.println("Add new note button clicked.");
        changeScene("controllers/NoteView.fxml", event);
    }

    public void showNote(MouseEvent mouseEvent) {
        Node currentNode = (Node) mouseEvent.getSource();
        currentNode.getScene().setUserData(currentNode.getUserData());

        changeScene("controllers/NoteView.fxml", mouseEvent);
    }
}
