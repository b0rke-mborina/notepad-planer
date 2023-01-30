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

public class NewToDoListController extends BaseController {
    public TextField titleField;
    public VBox itemsListBox;
    public HBox root;
    private int index;

    public void initialize() {
        loadToDoList();
    }

    private void loadToDoList() {
        TodoList todoList = new TodoList("", new TodoListItem[]{new TodoListItem("", false)});

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
        itemBox.getChildren().add(itemCompletedButton);
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
        int boxSize = itemsListBox.getChildren().size();
        TextField textField = (TextField) ((HBox) itemsListBox.getChildren().get(boxSize - 1)).getChildren().get(1);
        if (!textField.getText().equals("")) {
            loadElement(new TodoListItem("", false), boxSize);
        }
    }

    public void createToDoList(ActionEvent actionEvent) {
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

    private void goBack(Event event) {
        changeScene("controllers/ToDoListsView.fxml", event);
    }
}
