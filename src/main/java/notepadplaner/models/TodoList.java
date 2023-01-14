package notepadplaner.models;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class TodoList extends Model {
    public String title;
    public TodoListItem[] items;
    private static final String fileName = "todos.txt";

    public TodoList(String title, TodoListItem[] items) {
        this.title = title;
        this.items = items;
    }

    public void saveToFile() {
        ArrayList<String> listItemsData = getListItemsString();
        listItemsData.add(0, "-" + title);
        saveToFile(fileName, listItemsData.toArray(new String[0]));
    }

    private ArrayList<String> getListItemsString() {
        ArrayList<String> listItemsString = new ArrayList<>();

        for (TodoListItem item: items)
        {
            listItemsString.add(item.toString());
        }

        return listItemsString;
    }

    public static TodoList get(int index) {
        ArrayList<String> data = loadFile(fileName);
        int counter = 0;
        int dataIndex = 0;
        for (String row : data) {
            if (row.charAt(0) == '-') {
                counter++;
            }

            if (counter > index) {
                break;
            }

            dataIndex++;
        }

        System.out.println(data.get(dataIndex));

        return new TodoList("", new TodoListItem[] {});
    }
    /*
    public static void delete(int index) {
        ArrayList<String> data = loadFile(fileName);

        data.remove(index * 2);
        data.remove(index * 2);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public static void edit(int index, Note noteObj) {
        ArrayList<String> data = loadFile(fileName);

        data.set(index * 2, noteObj.title);
        data.set(index * 2 + 1, noteObj.note);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }*/

    public void print() {
        System.out.println("Title: " + title);
    }
}
