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

public class ToDoListController extends BaseController {
    @FXML
    private HBox root;
    @FXML
    private TextField titleField;
    @FXML
    private VBox itemsListBox;
    @FXML
    private TodoList todoList;
    private int index;

    public Node getRoot() {
        return root;
    }

    public void initialize() {
        Platform.runLater(() -> loadToDoList(Integer.parseInt(getStageUserData().toString())));
    }

    private void loadToDoList(int index) {
        this.index = index - 1;
        todoList = TodoList.get(this.index);

        titleField.setText(todoList.title);
        for (TodoListItem item : todoList.items) {
            loadElement(item, Arrays.asList(todoList.items).indexOf(item));
        }
    }

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

    public void setItemCompleted(String source) {
        // change checked text
        int itemIndex = Integer.parseInt(source);
        todoList.items[itemIndex].checked = true;

        Node hbox = itemsListBox.getChildren().get(itemIndex);
        Text checkedText = (Text) ((HBox)hbox).getChildren().get(0);
        checkedText.setText("✔");

        // change button
        Button button = (Button) ((HBox)hbox).getChildren().get(2);
        button.setText("Set not completed");
        button.onMouseClickedProperty().set(e ->
                setItemNotCompleted(((Button) e.getSource()).getUserData().toString())
        );
    }

    public void setItemNotCompleted(Object source) {
        int itemIndex = Integer.parseInt(source.toString());
        todoList.items[itemIndex].checked = false;

        Node hbox = itemsListBox.getChildren().get(itemIndex);
        Text checkedText = (Text) ((HBox)hbox).getChildren().get(0);
        checkedText.setText("✖");

        Button button = (Button) ((HBox)hbox).getChildren().get(2);
        button.setText("Set completed");
        button.onMouseClickedProperty().set(e ->
                setItemCompleted(((Button) e.getSource()).getUserData().toString())
        );
    }

    private void removeItem(ActionEvent actionEvent) {
        Node btnNode = (Node) actionEvent.getSource();
        TodoList.removeItem(index, Integer.parseInt(btnNode.getUserData().toString()));
        changeScene("controllers/ToDoListView.fxml");
    }

    public void addItem() {
        TodoList.addItem(index, new TodoListItem(""));
        changeScene("controllers/ToDoListView.fxml");
    }

    public void saveToDoList() {
        todoList.title = titleField.getText();
        List<Node> itemsBoxes = itemsListBox.getChildren();
        for (int i = 0; i < itemsBoxes.size(); i++) {
            TextField textField = (TextField) ((HBox) itemsBoxes.get(i)).getChildren().get(1);
            todoList.items[i].text = textField.getText();
        }

        TodoList.edit(index, todoList);
        goBack();
    }

    public void cancelToDoList() {
        goBack();
    }

    public void deleteToDoList() {
        TodoList.delete(index);
        goBack();
    }

    private void goBack() {
        changeScene("controllers/ToDoListsView.fxml");
    }
}
