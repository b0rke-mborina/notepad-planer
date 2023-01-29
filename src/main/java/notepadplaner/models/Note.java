package notepadplaner.models;

import java.util.ArrayList;

public class Note extends Model {
    public String title;
    public String note;

    private static final String fileName = "notes.txt";

    public Note(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public static void create(Note note) {
        encodeNewline(note);
        saveToFile(fileName, new String[] {note.title, note.note});
    }

    public static Note get(int index) {
        ArrayList<String> data = loadFile(fileName);

        Note note = new Note(data.get(2 * index), data.get(2 * index + 1));
        note.print();
        decodeNewline(note);
        note.print();
        return note;
    }

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

    public static void delete(int index) {
        ArrayList<String> data = loadFile(fileName);

        data.remove(index * 2);
        data.remove(index * 2);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public static void edit(int index, Note noteObj) {
        noteObj.print();
        encodeNewline(noteObj);
        noteObj.print();
        ArrayList<String> data = loadFile(fileName);

        data.set(index * 2, noteObj.title);
        data.set(index * 2 + 1, noteObj.note);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public void print() {
        System.out.println("[Note] Title: " + title);
        System.out.println("[Note] Note: " + note);
    }

    private static void encodeNewline(Note note) {
        note.note = note.note.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r");
        note.print();
    }

    private static void decodeNewline(Note note) {
        note.note = note.note.replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r");
    }
}
