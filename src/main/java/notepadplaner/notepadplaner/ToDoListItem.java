package notepadplaner.notepadplaner;

public class ToDoListItem {
    private boolean isChecked;
    private String[] text;

    public String[] getText() {
        return text;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
