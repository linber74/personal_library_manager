package MediaArchive.Book_Library;

import Service.Dialogtext;

public class Audiobooks extends Library {

    public Audiobooks(String title, String author, String genre, String language, String format, String bookType,
                      boolean seriesOrNot, String serieName, String serieNumber) {
        super(title, author, genre, language, format, bookType, seriesOrNot, serieName, serieNumber);
    }
}
