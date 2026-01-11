package model;

public class Film extends LibraryItem {

    private String movieType;


    public Film(String itemId, ItemType itemType, String title, String genre, String language, SeriesInfo seriesInfo, String movieType) {
        super(itemId, itemType, title, genre, language, seriesInfo);
        this.movieType = movieType;
    }
}
