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

    public static void saveToFile(Note note) {
        saveToFile(fileName, new String[] {note.title, note.note});
    }

    public static Note get(int index) {
        ArrayList<String> data = loadFile(fileName);

        return new Note(data.get(2 * index), data.get(2 * index + 1));
    }

    public static ArrayList<Note> getAll() {
        ArrayList<Note> notes = new ArrayList<>();
        ArrayList<String> data = loadFile(fileName);

        for (int i = 0; i < data.size() / 2; i++) {
            notes.add(new Note(data.get(i * 2), data.get(i * 2 + 1)));
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
        ArrayList<String> data = loadFile(fileName);

        data.set(index * 2, noteObj.title);
        data.set(index * 2 + 1, noteObj.note);

        saveToFile(fileName, data.toArray(new String[0]), true);
    }

    public void print() {
        System.out.println("[Note] Title: " + title);
        System.out.println("[Note] Note: " + note);
    }
}
