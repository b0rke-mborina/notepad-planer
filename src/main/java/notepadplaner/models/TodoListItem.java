package notepadplaner.models;

public class TodoListItem extends Model {
    public boolean checked;
    public String text;

    public TodoListItem(String text) {
        this.checked = false;
        this.text = text;
    }

    public TodoListItem(String text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }

    public String toString() {
        return checked ? "1" : "0" + text;
    }

    public void print() {
        System.out.println("[TODO ListItem] Text: " + text);
        System.out.println("[TODO ListItem] Checked: " + (checked ? "true" : "false"));
    }
}
