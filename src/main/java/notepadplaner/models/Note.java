package notepadplaner.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Note extends Model {
    public String title;
    public String note;

    public Note(String title, String note) {
        this.title = title;
        this.note = note;
    }
}
