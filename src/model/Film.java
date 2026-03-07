package model;

import java.util.ArrayList;
import java.util.List;

public class Film extends LibraryItem {

    private final String director;
    private final List<String> actors;
    private final MediaFormat mediaFormat;
    private final FilmType filmType;
    private final TranslationInfo translationInfo;

    public Film(int itemId, String title, List<String>genre, String language, SeriesInfo seriesInfo,
                String director, List<String>actors, MediaFormat mediaFormat, FilmType filmType, TranslationInfo translationInfo) {
        super(itemId, ItemType.FILM, title, genre, language, seriesInfo);
        this.director = director;
        if (actors != null) {
            this.actors = new ArrayList<>(actors);
        }
        else {
            this.actors = new ArrayList<>();
        }
        this.mediaFormat = mediaFormat;
        this.filmType = filmType;

        this.translationInfo = translationInfo;
    }

    public String getDirector() {
        return director;
    }
    public List<String> getActors() {
        return actors;
    }

    public MediaFormat getMediaFormat() {
        return mediaFormat;
    }

    public FilmType getFilmType() {
        return filmType;
    }

    public TranslationInfo getTranslationInfo() {
        return translationInfo;
    }

    public boolean isTranslated() {
        return translationInfo != null;
    }

    public  Film addActor(String actor) {
        actors.add(actor);
        return this;
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {
        return super.toString();
    }
}
