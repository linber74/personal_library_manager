package model;

public class Film extends LibraryItem {

    private String movieType;

    public Film(String title, String genre, String language, String format, String movieType,
                boolean seriesOrNot, String serieName, String serieNumber ) {
        super(title, genre, language, format, seriesOrNot, serieName, serieNumber);
        this.movieType = movieType;
    }
}
