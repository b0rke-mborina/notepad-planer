package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ToDoListComponentController extends BaseController {
    @FXML
    private GridPane root;
    @FXML
    public Text titleText;
    @FXML
    public Text toDoListPercentage;
    @FXML
    public Button showButton;

    public Node getRoot() {
        return root;
    }

    public void showToDoList(MouseEvent mouseEvent) {
        changeScene("controllers/ToDoListView.fxml", mouseEvent);
    }
}
