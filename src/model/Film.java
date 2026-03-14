package model;

import java.util.ArrayList;
import java.util.List;

public class Film extends VisualMedia {

    private final FilmType filmType;


    public Film(int itemId, String title, List<String> genre, String language, SeriesInfo seriesInfo,
                String director, List<String> actors, MediaFormat mediaFormat, TranslationInfo translationInfo, FilmType filmType) {

        super(itemId, ItemType.FILM, title, genre, language, seriesInfo, director, actors, mediaFormat, translationInfo);
        this.filmType = filmType;
    }

    public FilmType getFilmType() {
        return filmType;
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {
        return super.toString();
    }
}
