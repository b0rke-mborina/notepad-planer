package notepadplaner.models;

import java.util.ArrayList;

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

    public static ArrayList<TodoList> getAll() {
        ArrayList<TodoList> todoLists = new ArrayList<>();
        ArrayList<String> data = loadFile(fileName);
        ArrayList<TodoListItem> listItems = new ArrayList<>();
        String title = "";

        int dataIndex = 0;

        while (data.size() > dataIndex) {
            String row = data.get(dataIndex);
            if (data.get(dataIndex).charAt(0) == '-') {
                title = row.substring(1);
            }
            else {
                listItems.add(new TodoListItem(row.substring(1), row.charAt(0) == '1'));
            }
            if (data.size() - dataIndex < 2) {
                todoLists.add(new TodoList(title, listItems.toArray(new TodoListItem[0])));
            }
            else if (data.get(dataIndex + 1).charAt(0) == '-') {
                todoLists.add(new TodoList(title, listItems.toArray(new TodoListItem[0])));
                listItems = new ArrayList<>();
            }
            dataIndex++;
        }

        return todoLists;
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

        String title = data.get(dataIndex).substring(1);
        ArrayList<TodoListItem> listItems = new ArrayList<>();
        dataIndex++;

        while (data.size() >= dataIndex + 1 && data.get(dataIndex).charAt(0) != '-') {
            String row = data.get(dataIndex);
            listItems.add(new TodoListItem(row.substring(1), row.charAt(0) == '1'));
            dataIndex++;
        }

        return new TodoList(title, listItems.toArray(new TodoListItem[0]));
    }

    public static void delete(int index) {
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

        data.remove(dataIndex);

        while (data.size() > dataIndex && data.get(dataIndex).charAt(0) != '-') {
            data.remove(dataIndex);
        }

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public static void edit(int index, TodoList todoListObj) {
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

        data.set(dataIndex, "-" + todoListObj.title);
        dataIndex++;
        int startIndex = dataIndex;

        while (data.size() > dataIndex && data.get(dataIndex).charAt(0) != '-') {
            data.set(dataIndex, todoListObj.items[dataIndex - startIndex].toString());
            dataIndex++;
        }

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public static void addItem(int index, TodoListItem todoListItem) {
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

        dataIndex++;

        while (data.size() > dataIndex && data.get(dataIndex).charAt(0) != '-') {
            dataIndex++;
        }

        data.add(dataIndex, todoListItem.toString());

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public static void removeItem(int listIndex, int itemIndex) {
        ArrayList<String> data = loadFile(fileName);
        int counter = 0;
        int dataIndex = 0;
        for (String row : data) {
            if (row.charAt(0) == '-') {
                counter++;
            }

            if (counter > listIndex) {
                break;
            }

            dataIndex++;
        }

        dataIndex++;

        data.remove(dataIndex + itemIndex);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public void print() {
        System.out.println("[TODO List] Title: " + title);

        for (TodoListItem item: items) {
            item.print();
        }
    }
}
