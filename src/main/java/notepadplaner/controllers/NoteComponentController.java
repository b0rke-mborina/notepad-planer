package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class NoteComponentController extends BaseController {
    @FXML
    private AnchorPane root;
    @FXML
    public Text titleText;
    @FXML
    public Text noteText;
    @FXML
    public Button showButton;

    public Node getRoot() {
        return root;
    }

    public void showNote(MouseEvent mouseEvent) {
        changeScene("controllers/NoteView.fxml", mouseEvent);
    }
}
