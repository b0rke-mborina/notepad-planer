package notepadplaner.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Model {

    protected static void saveToFile(String fileName, String[] args) {
        saveToFile(fileName, args, false);
    }

    protected static void saveToFile(String fileName, String[] args, boolean override) {
        try {
            Path path = Paths.get(fileName);
            File file = new File(fileName);
            if (file.createNewFile()) System.out.println("First file created: " + fileName);
            else System.out.println("First file already exists: " + file.getName());

            if (override) {
                Files.write(path, "".getBytes());
            }

            for (String str: args)
            {
                Files.write(path, str.getBytes(), StandardOpenOption.APPEND);
                Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    protected static ArrayList<String> loadFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        Path path = Paths.get(fileName);

        try {
            File file = new File(fileName);
            if (file.createNewFile()) System.out.println("First file created: " + fileName);
            else System.out.println("First file already exists: " + file.getName());

            lines = (ArrayList<String>) Files.readAllLines(path);
        } catch (IOException ignored) {

        }

        return lines;
    }
}
