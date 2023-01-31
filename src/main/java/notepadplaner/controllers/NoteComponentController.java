package notepadplaner.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Controller for note component. Extends BaseController. Contains FXML element injections / references and method
 * implementations.
 *
 * @author Mateo, Rafael
 */
public class NoteComponentController extends BaseController {
    /**
     * FXML root element of view injection / reference. Used for saving data.
     */
    @FXML
    private AnchorPane root;

    /**
     * FXML note title text element injection / reference. Used for showing note title.
     */
    @FXML
    public Text titleText;

    /**
     * FXML note body text element injection / reference. Used for showing note body.
     */
    @FXML
    public Text noteText;

    /**
     * FXML button element injection / reference. Used for showing note full data by changing scene.
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
     * Changes scene to note view.
     *
     * @param mouseEvent Invocation event.
     * @author Mateo, Rafael
     */
    @FXML
    public void showNote(MouseEvent mouseEvent) {
        changeScene("controllers/NoteView.fxml", mouseEvent);
    }
}
