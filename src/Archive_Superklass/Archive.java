package Archive_Superklass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Archive {
    private final String title;
    private final String genre;
    private final String language;
    protected final String format;

    private static final ArrayList<Archive> samling = new ArrayList<>();

    public Archive(String title, String genre, String language, String format) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.format = format;
    }

    public static void addBibliotek(Archive bibliotek) {
        if (bibliotek != null) {
            samling.add(bibliotek);
        }
    }

    public static void saveBibliotek(String filename) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
            for (Archive bibliotek : samling) {
                out.println(bibliotek.title + "; "
                        + bibliotek.genre + "; "
                        + bibliotek.language + "; "
                        + bibliotek.format);
            }
        } catch (IOException e) {
            System.out.println("Fel spara Bibliotek" + e.getMessage());

        }
    }
}
