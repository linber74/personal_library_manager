package MediaArchive.Book_Library;

import Archive_Superklass.Archive;

public class Library extends Archive {
    private final String author;
    private final String bookType;
    private final boolean seriesOrNot;
    private final String serieName;
    private String serieNumber;

    public Library(String title, String author, String genre, String language, String format, String bookType,
                   boolean seriesOrNot, String serieName, String serieNumber) {
        super(title, genre, language, format);
        this.author = author;
        this.bookType = bookType;
        this.seriesOrNot = seriesOrNot;
        this.serieName = serieName;
        this.serieNumber = serieNumber;
    }

    public String getAuthor() {
        return author;
    }

    // @Override
    //public String toString() {
      //  return
    //}

}

