package model;

public class Film extends LibraryItem {

    private String movieType;


    public Film(String title, String genre, String language, SeriesInfo serieInfo, int itemId, ItemType itemType, String movieType) {
        super(title, genre, language, serieInfo, itemId, itemType);
        this.movieType = movieType;
    }
}
