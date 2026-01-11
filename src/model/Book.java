package model;

public class Book extends LibraryItem {

    private final String author;
    private final BookFormat bookFormat;
    private final String fandom;
    private final FanficType fanficType;

    public Book(String itemId, String title, String genre, String language,
                SeriesInfo seriesInfo, String author, BookFormat bookFormat,FanficType fanficType, String fandom) {
        super(itemId, ItemType.BOOK, title, genre, language, seriesInfo);
        this.author = author;
        this.bookFormat = bookFormat;
        if (bookFormat == BookFormat.FANFICTION) {
            this.fanficType = fanficType;
            this.fandom = (fandom == null || fandom.isBlank()) ? null : fandom;
        } else {
            this.fanficType = null;
            this.fandom = null;
        }

    }

    public String getAuthor() {
        return author;
    }
    public BookFormat getBookFormat() {
        return bookFormat;
    }
    public String getFandom() {
        return fandom;
    }
    public FanficType getFanficType() {
        return fanficType;
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {
        return "Författare: " + author + " - " + "Titel: " + getTitle();
    }
}

