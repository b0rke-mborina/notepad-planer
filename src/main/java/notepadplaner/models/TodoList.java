package notepadplaner.models;

import java.util.ArrayList;

/**
 * Model TodoList aplikacije sa podrškom CRUD operacija.
 *
 * @author Rafael
 */
public class TodoList extends Model {
    /**
     * Title of the to-do list.
     */
    public String title;

    /**
     * Items of the to-do list. Are an Array of TodoListItems.
     */
    public TodoListItem[] items;

    /**
     * Name of file which contains saved to-do lists.
     */
    private static final String fileName = "todos.txt";

    /**
     * Konstruktor TodoList.
     *
     * @param title Naslov
     * @param items Stavke
     * @author Rafael
     */
    public TodoList(String title, TodoListItem[] items) {
        this.title = title;
        this.items = items;
    }

    /**
     * Sprema TodoList u bazu.
     *
     * @param todoList TodoList
     * @author Rafael
     */
    public static void create(TodoList todoList) {
        ArrayList<String> listItemsData = new ArrayList<>();
        for (TodoListItem item: todoList.items)
        {
            listItemsData.add(item.toString());
        }
        listItemsData.add(0, "-" + todoList.title);
        saveToFile(fileName, listItemsData.toArray(new String[0]));
    }

    /**
     * Dohvaća sve TodoListe iz baze.
     *
     * @return Lista TodoList iz baze
     * @author Rafael
     */
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

    /**
     * Vraća pointer index na data.
     *
     * @param data Lista redaka
     * @param index Brojać TodoList-a
     * @return Index koji pokazuje na prvi redak nakon "index" broja TodoList-i
     * @author Rafael
     */
    private static int getDataIndex(ArrayList<String> data, int index) {
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

        return dataIndex;
    }

    /**
     * Dohvaća TodoList iz baze po pripadajućem indexu.
     *
     * @param index Pripadajuć index (počinje od 0)
     * @return TodoList iz baze
     * @author Rafael
     */
    public static TodoList get(int index) {
        ArrayList<String> data = loadFile(fileName);
        int dataIndex = getDataIndex(data, index);

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

    /**
     * Briše TodoList iz baze po pripadajućem indexu.
     *
     * @param index Pripadajuć index (počinje od 0)
     * @author Rafael
     */
    public static void delete(int index) {
        ArrayList<String> data = loadFile(fileName);
        int dataIndex = getDataIndex(data, index);

        data.remove(dataIndex);
        while (data.size() > dataIndex && data.get(dataIndex).charAt(0) != '-') {
            data.remove(dataIndex);
        }

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    /**
     * Uređuje TodoList iz baze po pripadajućem indexu.
     *
     * @param index Pripadajuć index (počinje od 0)
     * @param todoListObj Uređen TodoList
     * @author Rafael
     */
    public static void edit(int index, TodoList todoListObj) {
        ArrayList<String> data = loadFile(fileName);
        int dataIndex = getDataIndex(data, index);

        data.set(dataIndex, "-" + todoListObj.title);
        dataIndex++;
        int startIndex = dataIndex;

        while (data.size() > dataIndex && data.get(dataIndex).charAt(0) != '-') {
            data.set(dataIndex, todoListObj.items[dataIndex - startIndex].toString());
            dataIndex++;
        }

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    /**
     * Dodaje stavku na TodoList iz baze po pripadajućem indexu.
     *
     * @param index Pripadajuć index TodoList-e (počinje od 0)
     * @param todoListItem Stavka
     * @author Rafael
     */
    public static void addItem(int index, TodoListItem todoListItem) {
        ArrayList<String> data = loadFile(fileName);
        int dataIndex = getDataIndex(data, index);

        dataIndex++;
        while (data.size() > dataIndex && data.get(dataIndex).charAt(0) != '-') {
            dataIndex++;
        }

        data.add(dataIndex, todoListItem.toString());

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    /**
     * Uklanja stavku sa TodoList iz baze po pripadajućem indexu.
     *
     * @param listIndex Pripadajuć index TodoList-e (počinje od 0)
     * @param itemIndex Pripadajuć index stavke (počinje od 0)
     * @author Rafael
     */
    public static void removeItem(int listIndex, int itemIndex) {
        ArrayList<String> data = loadFile(fileName);
        int dataIndex = getDataIndex(data, listIndex);

        dataIndex++;
        data.remove(dataIndex + itemIndex);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    /**
     * Pomoćna funkcija za ispis TodoList.
     *
     * @author Rafael
     */
    public void print() {
        System.out.println("[TODO List] Title: " + title);

        for (TodoListItem item: items) {
            item.print();
        }
    }
}
