package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Controller for to-do list component. Extends BaseController. Contains FXML element injections / references and method
 * implementations.
 *
 * @author Mateo, Rafael
 */
public class ToDoListComponentController extends BaseController {
    /**
     * FXML root element of view injection / reference. Used for saving data.
     */
    @FXML
    private GridPane root;

    /**
     * FXML to-do list title text element injection / reference. Used for showing to-do list title.
     */
    @FXML
    public Text titleText;

    /**
     * FXML to-do list percentage of completion text element injection / reference. Used for showing to-do list
     * percentage of completion.
     */
    @FXML
    public Text toDoListPercentage;

    /**
     * FXML button element injection / reference. Used for showing to-do list full data by changing scene.
     */
    @FXML
    public Button showButton;

    /**
     * Root getter.
     *
     * @return Node
     * @author Rafael
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Changes scene to to-do list view.
     *
     * @param mouseEvent Invocation event.
     * @author Mateo, Rafael
     */
    public void showToDoList(MouseEvent mouseEvent) {
        changeScene("controllers/ToDoListView.fxml", mouseEvent);
    }
}
