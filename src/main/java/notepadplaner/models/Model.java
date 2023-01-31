package notepadplaner.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Osnovna abstraktna klasa za potporu okvira učestalih funkcionalnosti modela aplikacije.
 *
 * @author Rafael
 */
abstract class Model {
    /**
     * Sprema retke u datoteku
     *
     * @param fileName Naziv datoteke
     * @param args Lista redaka za spremanje
     * @author Rafael
     */
    protected static void saveToFile(String fileName, String[] args) {
        saveToFile(fileName, args, false);
    }

    /**
     * Sprema retke u datoteku.
     *
     * @param fileName Naziv datoteke
     * @param args Lista redaka za spremanje
     * @param override Opcija za brisanje trenutnog sadržaja datoteke. "True" -> izbriši podatke i piši na novo.
     *                 "False" -> Dodaj nove podatke na stare podatke.
     * @author Rafael
     */
    protected static void saveToFile(String fileName, String[] args, boolean override) {
        try {
            Path path = Paths.get(fileName);
            File file = new File(fileName);
            file.createNewFile();

            if (override) {
                Files.write(path, "".getBytes());
            }

            for (String str: args)
            {
                Files.write(path, str.getBytes(), StandardOpenOption.APPEND);
                Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Učitava retke datoteke.
     *
     * @param fileName Naziv datoteke
     * @return Lista redaka datoteke
     * @author Rafael
     */
    protected static ArrayList<String> loadFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        Path path = Paths.get(fileName);

        try {
            File file = new File(fileName);
            file.createNewFile();
            lines = (ArrayList<String>) Files.readAllLines(path);
        } catch (IOException err) {
            err.printStackTrace();
        }

        return lines;
    }
}
