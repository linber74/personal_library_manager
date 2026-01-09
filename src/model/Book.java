package model;

public class Book extends LibraryItem {

    public Book(String title, String genre, String language, SeriesInfo serieInfo, int itemId, ItemType itemType) {
        super(title, genre, language, serieInfo, itemId, itemType);
    }
}

