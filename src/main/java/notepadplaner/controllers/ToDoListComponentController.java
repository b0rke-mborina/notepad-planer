package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListComponentController extends BaseController implements Initializable {
    @FXML
    public Text titleText;
    @FXML
    public Text toDoListPercentage;
    @FXML
    public Button showButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // System.out.println("ToDoList component initialized.");
        // System.out.println(showButton.getUserData());
    }

    public void showToDoList(MouseEvent mouseEvent) {
        setSceneUserDataFromNode((Node) mouseEvent.getSource());
        changeScene("controllers/ToDoListView.fxml", mouseEvent);
    }
}
