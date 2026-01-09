package model;

public class Book extends LibraryItem {
    private final String author;
    private final String bookType;


    public Book(String title, String author, String genre, String language, String format, String bookType,
                boolean seriesOrNot, String serieName, String serieNumber) {
        super(title, genre, language, format,  seriesOrNot, serieName, serieNumber);
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

