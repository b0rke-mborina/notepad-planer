package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import notepadplaner.models.TodoList;
import notepadplaner.models.TodoListItem;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ToDoListController extends BaseController {
    public TextField titleField;
    public VBox itemsListBox;
    public HBox root;
    public TodoList todoList;
    private int index;

    public void initialize() {
        Platform.runLater(() -> {
            Scene currentScene = root.getScene();
            Stage currentStage = (Stage)currentScene.getWindow();
            loadToDoList(Integer.parseInt(currentStage.getUserData().toString()));
        });
    }

    private void loadToDoList(int index) {
        this.index = index - 1;
        todoList = TodoList.get(index - 1);

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
        int itemIndex = Integer.parseInt(btnNode.getUserData().toString());

        Scene currentScene = root.getScene();
        Stage currentStage = (Stage)currentScene.getWindow();
        setSceneUserDataFromNode(root, currentStage.getUserData());

        TodoList.removeItem(index, itemIndex);
        changeScene("controllers/ToDoListView.fxml", actionEvent);
    }

    public void addItem(ActionEvent actionEvent) {
        Scene currentScene = root.getScene();
        Stage currentStage = (Stage)currentScene.getWindow();
        setSceneUserDataFromNode(root, currentStage.getUserData());

        TodoList.addItem(index, new TodoListItem(""));
        changeScene("controllers/ToDoListView.fxml", actionEvent);
    }

    public void saveToDoList(ActionEvent actionEvent) {
        todoList.title = titleField.getText();
        List<Node> itemsBoxes = itemsListBox.getChildren();
        for (int i = 0; i < itemsBoxes.size(); i++) {
            TextField textField = (TextField) ((HBox) itemsBoxes.get(i)).getChildren().get(1);
            todoList.items[i].text = textField.getText();
        }

        TodoList.edit(index, todoList);
        goBack(actionEvent);
    }

    public void cancelToDoList(ActionEvent actionEvent) {
        goBack(actionEvent);
    }

    public void deleteToDoList(ActionEvent actionEvent) {
        TodoList.delete(index);
        goBack(actionEvent);
    }

    private void goBack(Event event) {
        changeScene("controllers/ToDoListsView.fxml", event);
    }
}
