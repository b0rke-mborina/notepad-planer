package notepadplaner.notepadplaner;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private static int MAX_NUMBER_OF_ITEMS;

    private String[] heading;
    private List<ToDoListItem> items = new ArrayList<>();

    public String[] getHeading() {
        return heading;
    }

    public List<ToDoListItem> getItems() {
        return items;
    }

    public void setHeading(String[] heading) {
        this.heading = heading;
    }

    public void setItems(List<ToDoListItem> items) {
        this.items = items;
    }
}
