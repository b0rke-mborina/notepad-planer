package notepadplaner.models;

public class TodoListItem extends Model {
    public boolean checked;
    public String text;

    public TodoListItem(String text) {
        this.checked = false;
        this.text = text;
    }

    public TodoListItem(String text, boolean checked) {
        this.checked = checked;
        this.text = text;
    }

    public String toString() {
        return checked ? "1" : "0" + text;
    }
}
