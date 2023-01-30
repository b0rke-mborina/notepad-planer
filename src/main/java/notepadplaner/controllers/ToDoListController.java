package notepadplaner.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
        TodoList todoList = TodoList.get(index - 1);

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

        // buttons (Set completed, Remove item)
        // button set completed
        Button setItemCompletedButton = new Button("Set completed");
        setItemCompletedButton.setPrefWidth(150);
        setItemCompletedButton.getStyleClass().add("bg-orange");
        setItemCompletedButton.setUserData(itemIndex);
        setItemCompletedButton.setOnMouseClicked(e ->
                setItemCompleted(((Button) e.getSource()).getUserData().toString())
        );

        // button remove item
        Button removeItemButton = new Button("Remove item");
        removeItemButton.getStyleClass().add("bg-orange");
        removeItemButton.setUserData(itemIndex);
        removeItemButton.setOnMouseClicked(e ->
                removeItem(((Button) e.getSource()).getUserData().toString())
        );

        // create box for elements
        HBox itemBox = new HBox();
        itemBox.setAlignment(Pos.CENTER);
        itemBox.setSpacing(16);
        itemBox.setUserData(itemIndex);

        // add elements to box and to UI
        itemBox.getChildren().add(checked);
        itemBox.getChildren().add(itemText);
        itemBox.getChildren().add(setItemCompletedButton);
        itemBox.getChildren().add(removeItemButton);
        itemsListBox.getChildren().add(itemBox);
    }

    public void setItemCompleted(String source) {
        // change checked text
        int itemIndex = Integer.parseInt(source);
        Node hbox = itemsListBox.getChildren().get(itemIndex);
        Text checkedText = (Text) ((HBox)hbox).getChildren().get(0);
        checkedText.setText("✔");

        // change button
        Button button = (Button) ((HBox)hbox).getChildren().get(2);
        button.setText("Set not completed");
        button.onMouseClickedProperty().set(e ->
                setItemNotCompleted(((Button) e.getSource()).getUserData().toString())
        );

        System.out.println("Item completed button clicked.");
    }

    public void setItemNotCompleted(Object source) {
        int itemIndex = Integer.parseInt(source.toString());
        Node hbox = itemsListBox.getChildren().get(itemIndex);
        Text checkedText = (Text) ((HBox)hbox).getChildren().get(0);
        checkedText.setText("✖");

        Button button = (Button) ((HBox)hbox).getChildren().get(2);
        button.setText("Set completed");
        button.onMouseClickedProperty().set(e ->
                setItemCompleted(((Button) e.getSource()).getUserData().toString())
        );

        System.out.println("Item not completed button clicked.");
    }

    public void removeItem(Object source) {
        int itemIndex = Integer.parseInt(source.toString());
        List<Node> hBoxes = itemsListBox.getChildren();
        int hBoxesSize = hBoxes.size();
        if (hBoxesSize > 1) {
            hBoxes.remove(itemIndex);
            for (int i = 0; i < itemsListBox.getChildren().size(); i++) {
                HBox box = (HBox) itemsListBox.getChildren().get(i);
                box.setUserData(i);
                Node button = box.getChildren().get(3);
                button.setUserData(i);
            }
        }
    }

    public void addItem() {
        System.out.println("Item remove item button clicked.");
        int boxSize = itemsListBox.getChildren().size();
        TextField textField = (TextField) ((HBox) itemsListBox.getChildren().get(boxSize - 1)).getChildren().get(1);
        if (!textField.getText().equals("")) {
            loadElement(new TodoListItem("", false), boxSize);
        }
    }

    public void saveToDoList(ActionEvent actionEvent) {
        System.out.println("Save toDoList button clicked.");
        TodoListItem[] items = new TodoListItem[]{};
        List<Node> itemsBoxes = itemsListBox.getChildren();
        for (Node itemBox : itemsBoxes) {
            // items.app()
        }

        /*TodoList.edit(index, new TodoList(
            titleField.getText(),
            itemsListBox.getChildren()
        ));*/
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
