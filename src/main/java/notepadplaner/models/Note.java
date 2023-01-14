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

    public void saveToFile() {
        saveToFile(fileName, new String[] {title, note});
    }

    public static Note get(int index) {
        ArrayList<String> data = loadFile(fileName);

        return new Note(data.get(index), data.get(index + 1));
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
        System.out.println("Title: " + title);
        System.out.println("Note: " + note);
    }
}
