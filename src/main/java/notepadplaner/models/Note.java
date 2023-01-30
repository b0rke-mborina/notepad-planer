package notepadplaner.models;

import java.util.ArrayList;

/**
 * Model Note aplikacije sa podrškom CRUD operacija
 *
 * @author Rafael
 */
public class Note extends Model {
    public String title;
    public String note;
    private String shortNote;
    private final String shortTitle;
    private static final String fileName = "notes.txt";

    /**
     * Konstruktor note. Stvara notu i postavlja odgovarajuću verziju kratog naslova i note.
     *
     * @param title Naslov
     * @param note Nota
     * @author Rafael
     */
    public Note(String title, String note) {
        this.title = title;
        this.note = note;

        shortTitle = title.length() > 16 ? title.substring(0, 18) + "..." : title;

        shortNote = "";
        String[] rows = this.note.split("\\\\n");
        for (int i = 0; i < rows.length; i++ ) {
            if (i > 2) {
                shortNote += "...";
                break;
            }

            shortNote += (rows[i].length() > 18 ? rows[i].substring(0, 20) + "...": rows[i]) + "\n";
        }
    }

    /**
     * ShortNote getter.
     *
     * @return Kratka nota
     * @author Rafael
     */
    public String getShortNote() {
        return shortNote;
    }

    /**
     * ShortTitle getter.
     *
     * @return Kratal naslov
     * @author Rafael
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * Sprema Notu u bazu.
     *
     * @param note Nota
     * @author Rafael
     */
    public static void create(Note note) {
        encodeNewline(note);
        saveToFile(fileName, new String[] {note.title, note.note});
    }

    /**
     * Dohvaća Notu iz baze po pripadajućem indexu.
     *
     * @param index Pripadajuć index (počinje od 0)
     * @return Nota iz baze
     * @author Rafael
     */
    public static Note get(int index) {
        ArrayList<String> data = loadFile(fileName);

        Note note = new Note(data.get(2 * index), data.get(2 * index + 1));
        decodeNewline(note);
        return note;
    }

    /**
     * Dohvaća sve Note iz baze.
     *
     * @return Lista Nota iz baze
     * @author Rafael
     */
    public static ArrayList<Note> getAll() {
        ArrayList<Note> notes = new ArrayList<>();
        ArrayList<String> data = loadFile(fileName);
        Note note;
        for (int i = 0; i < data.size() / 2; i++) {
            note = new Note(data.get(i * 2), data.get(i * 2 + 1));
            decodeNewline(note);
            notes.add(note);
        }

        return notes;
    }

    /**
     * Briše Notu iz baze po pripadajućem indexu.
     *
     * @param index Pripadajuć index (počinje od 0)
     * @author Rafael
     */
    public static void delete(int index) {
        ArrayList<String> data = loadFile(fileName);

        data.remove(index * 2);
        data.remove(index * 2);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    /**
     * Uređuje Notu iz baze po pripadajućem indexu.
     *
     * @param index Pripadajuć index (počinje od 0)
     * @param noteObj Uređena Nota
     * @author Rafael
     */
    public static void edit(int index, Note noteObj) {
        encodeNewline(noteObj);
        ArrayList<String> data = loadFile(fileName);

        data.set(index * 2, noteObj.title);
        data.set(index * 2 + 1, noteObj.note);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    /**
     * Pomoćna funkcija za ispis Note.
     *
     * @author Rafael
     */
    public void print() {
        System.out.println("[Note] Title: " + title);
        System.out.println("[Note] Note: " + note);
    }

    /**
     * Enkodira nove retke u Noti u svrhu spremanja.
     *
     * @param note Nota za enkodiranje
     * @author Rofael
     */
    private static void encodeNewline(Note note) {
        note.note = note.note.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r");
    }

    /**
     * Dekodira nove retke u Noti u svrhu učitavanja.
     *
     * @param note Nota za dekodiranje
     * @author Rofael
     */
    private static void decodeNewline(Note note) {
        note.note = note.note.replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r");
    }
}
