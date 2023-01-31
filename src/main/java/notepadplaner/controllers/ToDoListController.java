package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for modifying a to-do list. Extends BaseController. Contains FXML element and method injections /
 * references and implementations.
 *
 * @author Mateo, Rafael
 */
public class ToDoListController extends BaseController {
    /**
     * FXML root element of view injection / reference. Used for saving data.
     */
    @FXML
    private HBox root;

    /**
     * FXML to-do list title text field element injection / reference. Used for showing / editing to-do list title.
     */
    @FXML
    private TextField titleField;

    /**
     * FXML to-do list box for items element injection / reference. Used for showing / editing to-do list items.
     */
    @FXML
    private VBox itemsListBox;

    /**
     * To-do list instance storage. Used for saving to-do list data.
     */
    private TodoList todoList;

    /**
     * Index of to-do list in TXT file. Used for saving data.
     */
    private int index;

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
     * Loads data after the view is loaded.
     *
     * @author Mateo, Rafael
     */
    public void initialize() {
        Platform.runLater(() -> loadToDoList(Integer.parseInt(getStageUserData().toString())));
    }

    /**
     * Loads to-do list, saves its data, adds data to view and load elements for to-do list items.
     *
     * @param index Index of the to-do list in TXT file.
     * @author Mateo, Rafael
     */
    private void loadToDoList(int index) {
        // load to-do list by index and save its data
        this.index = index - 1;
        todoList = TodoList.get(this.index);

        // add data to FXML element and load elements for items
        titleField.setText(todoList.title);
        for (TodoListItem item : todoList.items) {
            loadElement(item, Arrays.asList(todoList.items).indexOf(item));
        }
    }

    /**
     * Creates and adds element to UI for supplied to-do list item and index of to-do list in TXT file.
     * Added element is HBox with user data (index of to-do list). Inside HBox there are Text, TextField and 2 buttons
     * respectively. Both buttons have user data (index of to-do list). First button has on-mouse-clicked listener set.
     * The second button has on-action listener set.
     *
     * @param item Item of the to-do list.
     * @param itemIndex Index of item in the to-do list.
     * @author Mateo
     */
    public void loadElement(TodoListItem item, int itemIndex) {
        // checked text
        Text checked = new Text(item.checked ? "✔" : "✖");
        checked.getStyleClass().add("fs-16");

        // item text field
        TextField itemText = new TextField(item.text);
        itemText.getStyleClass().add("text-field-area");
        itemText.getStyleClass().add("bg-orange");
        itemText.getStyleClass().add("fs-16");
        HBox.setHgrow(itemText, Priority.ALWAYS);

        // button set completed
        Button itemCompletedButton = new Button();
        itemCompletedButton.setPrefWidth(150);
        itemCompletedButton.getStyleClass().add("bg-orange");
        itemCompletedButton.setUserData(itemIndex);
        itemCompletedButton.setText(item.checked ? "Set not completed" : "Set completed");
        if (item.checked) {
            itemCompletedButton.setOnMouseClicked(e ->
                    setItemNotCompleted(((Button) e.getSource()).getUserData().toString())
            );
        } else {
            itemCompletedButton.setOnMouseClicked(e ->
                    setItemCompleted(((Button) e.getSource()).getUserData().toString())
            );
        }

        // button remove item
        Button removeItemButton = new Button("Remove item");
        removeItemButton.getStyleClass().add("bg-orange");
        removeItemButton.setUserData(itemIndex);
        removeItemButton.setOnAction(this::removeItem);

        // create box for elements
        HBox itemBox = new HBox();
        itemBox.setAlignment(Pos.CENTER);
        itemBox.setSpacing(16);
        itemBox.setUserData(itemIndex);

        // add elements to box and to UI
        itemBox.getChildren().add(checked);
        itemBox.getChildren().add(itemText);
        itemBox.getChildren().add(itemCompletedButton);
        itemBox.getChildren().add(removeItemButton);
        itemsListBox.getChildren().add(itemBox);
    }

    /**
     * Makes item completed by changing its property, and updates text and button.
     *
     * @param source Index of element in list of items. Type is String.
     * @author Mateo
     */
    public void setItemCompleted(String source) {
        // update checked (set checked to true)
        int itemIndex = Integer.parseInt(source);
        todoList.items[itemIndex].checked = true;

        // update text (set text which represents checked value to "✔")
        Node hbox = itemsListBox.getChildren().get(itemIndex);
        Text checkedText = (Text) ((HBox)hbox).getChildren().get(0);
        checkedText.setText("✔");

        // change button that it reverts changes of checked property the next time it is clicked
        Button button = (Button) ((HBox)hbox).getChildren().get(2);
        button.setText("Set not completed");
        button.onMouseClickedProperty().set(e ->
                setItemNotCompleted(((Button) e.getSource()).getUserData().toString())
        );
    }

    /**
     * Makes item not completed by changing its property, and updates text and button.
     *
     * @param source Index of element in list of items. Type is String.
     * @author Mateo
     */
    public void setItemNotCompleted(Object source) {
        // set checked to false
        int itemIndex = Integer.parseInt(source.toString());
        todoList.items[itemIndex].checked = false;

        // set text which represents checked value to "✖"
        Node hbox = itemsListBox.getChildren().get(itemIndex);
        Text checkedText = (Text) ((HBox)hbox).getChildren().get(0);
        checkedText.setText("✖");

        // change button that it reverts changes of checked property the next time it is clicked
        Button button = (Button) ((HBox)hbox).getChildren().get(2);
        button.setText("Set completed");
        button.onMouseClickedProperty().set(e ->
                setItemCompleted(((Button) e.getSource()).getUserData().toString())
        );
    }

    /**
     * Removes item from list of items of to-do list, saves changes and reloads scene.
     *
     * @param actionEvent Invocation action event.
     * @author Mateo, Rafael
     */
    private void removeItem(ActionEvent actionEvent) {
        Node btnNode = (Node) actionEvent.getSource();
        TodoList.removeItem(index, Integer.parseInt(btnNode.getUserData().toString()));
        changeScene("controllers/ToDoListView.fxml");
    }

    /**
     * Adds empty item to list of items of to-do list.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void addItem() {
        TodoList.addItem(index, new TodoListItem(""));
        changeScene("controllers/ToDoListView.fxml");
    }

    /**
     * FXML saveToDoList method injection / reference and implementation. Updates to-do list by calling TodoList edit
     * method, which edits note in TXT file, and returns to list of notes.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void saveToDoList() {
        // get data
        todoList.title = titleField.getText();
        List<Node> itemsBoxes = itemsListBox.getChildren();
        for (int i = 0; i < itemsBoxes.size(); i++) {
            TextField textField = (TextField) ((HBox) itemsBoxes.get(i)).getChildren().get(1);
            todoList.items[i].text = textField.getText();
        }

        // update to-do list
        TodoList.edit(index, todoList);
        goBack();
    }

    /**
     * FXML cancelToDoList method injection / reference and implementation. Cancels to-do list changes and changes scene
     * to list of to-do lists.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void cancelToDoList() {
        goBack();
    }

    /**
     * FXML deleteToDoList method injection / reference and implementation. Deletes to-do list by calling TodoList
     * delete method and changes scene to list of to-do lists.
     *
     * @author Mateo, Rafael
     */
    @FXML
    public void deleteToDoList() {
        TodoList.delete(index);
        goBack();
    }


    /**
     * Changes scene to list of to-do lists.
     *
     * @author Mateo, Rafael
     */
    private void goBack() {
        changeScene("controllers/ToDoListsView.fxml");
    }
}
