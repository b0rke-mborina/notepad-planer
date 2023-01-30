package notepadplaner.models;

/**
 * Model TodoListItem aplikacije.
 *
 * @author Rafael
 */
public class TodoListItem {
    public boolean checked;
    public String text;

    /**
     * Konstruktor TodoListItem.
     *
     * @param text Stavka
     * @author Rafael
     */
    public TodoListItem(String text) {
        this.checked = false;
        this.text = text;
    }

    /**
     * Konstruktor TodoListItem.
     *
     * @param text Stavka
     * @param checked Oznaka "gotovo"
     * @author Rafael
     */
    public TodoListItem(String text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }

    /**
     * Prilagoena toString() metoda.
     *
     * @return tekstualni zapis TodoListItem
     * @author Rafael
     */
    public String toString() {
        return (checked ? "1" : "0") + text;
    }

    /**
     * Pomoćna funkcija za ispis TodoListItem.
     *
     * @author Rafael
     */
    public void print() {
        System.out.println("[TODO ListItem] Text: " + text);
        System.out.println("[TODO ListItem] Checked: " + (checked ? "true" : "false"));
    }
}
