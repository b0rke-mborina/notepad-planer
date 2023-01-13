package notepadplaner.models;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Model {

    public void toFile(String text) {
        try (PrintWriter out = new PrintWriter("notes.txt")) {
            out.println(text);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
