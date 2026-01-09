package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

abstract class LibraryItem {
    private final String title;
    private final String genre;
    private final String language;
    protected final String format;
    protected boolean seriesOrNot;
    protected String serieName;
    protected String serieNumber;

    private static final ArrayList<LibraryItem> samling = new ArrayList<>();

    public LibraryItem(String title, String genre, String language, String format, boolean seriesOrNot, String serieName, String serieNumber) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.format = format;
        this.seriesOrNot = seriesOrNot;
        this.serieName = serieName;
        this.serieNumber = serieNumber;
    }

    public static void addLibary(LibraryItem bibliotek) {
        if (bibliotek != null) {
            samling.add(bibliotek);
        }
    }

    public static void saveLibary(String filename) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
            for (LibraryItem bibliotek : samling) {
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
