package MediaArchive.Book_Library;

public class Fanfiction extends Library {

    private final String fandom;

    public Fanfiction(String title, String author, String genre, String language, String format, String bookType, String fandom,
                      boolean seriesOrNot, String serieName, String serieNumber) {
        super(title, author, genre, language, format, bookType, seriesOrNot, serieName, serieNumber);
        this.fandom = fandom;
    }
}
