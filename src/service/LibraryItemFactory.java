package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LibraryItemFactory {


    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Game createGame(String title, String genre, String language, SeriesInfo seriesInfo, String creator) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (creator == null || creator.isBlank()) {
            throw new IllegalArgumentException("Creator cannot be null or blank");
        }

       String id = generateId();
       return new Game(id, title, genre, language, seriesInfo, creator);
    }

    public Book createBook(String title, String genre, String language, SeriesInfo seriesInfo, String author,
                           BookFormat bookFormat, FanficType fanficType, String fandom) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
        if (bookFormat == null) {
            throw new IllegalArgumentException("BookFormat cannot be null");
        }
        if (bookFormat == BookFormat.FANFICTION) {
            if (fanficType == null) {
                throw new IllegalArgumentException("FanficType cannot be null");
            } else if (fanficType != FanficType.ORIGINAL) {
                if (fandom == null ||  fandom.isBlank()) {
                    throw new IllegalArgumentException("Fandom cannot be null or blank");
                }
                
            }
        }

        String id = generateId();
        return new Book(id, title, genre, language, seriesInfo, author, bookFormat, fanficType, fandom);
    }

    public Film createFilm (String title, String genre, String language, SeriesInfo seriesInfo, String director,
                           List<String> actors, MediaFormat mediaFormat, FilmType filmType, TranslationInfo translationInfo) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }

        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Director cannot be null or blank");
        }

        if (mediaFormat == null) {
            throw new IllegalArgumentException("MediaFormat cannot be null");
        }

        if (filmType == null) {
            throw new IllegalArgumentException("FilmType cannot be null");
        }

        List<String> safeActors;
        if (actors == null) {
            safeActors = new ArrayList<>();
        }
        else {
            safeActors = new ArrayList<>(actors);
        }

        String id = generateId();

        return new Film(id, title, genre, language, seriesInfo, director, safeActors, mediaFormat, filmType, translationInfo);
    }

    public TVSeries createTVSeries (String title, String genre, String language, SeriesInfo seriesInfo, String director,
                                    List<String> actors, MediaFormat mediaFormat,
                                    TranslationInfo translationInfo, List<Season> seasons) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }

        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Director cannot be null or blank");
        }
        if (mediaFormat == null) {
            throw new IllegalArgumentException("MediaFormat cannot be null");
        }

        List<String> safeActors;
        if (actors == null) {
            safeActors = new ArrayList<>();
        }
        else {
            safeActors = new ArrayList<>(actors);
        }

        List <Season> safeSeasons;
        if (seasons == null) {
            safeSeasons =new ArrayList<>();
        }
        else{
            safeSeasons = new ArrayList<>( seasons);
        }

        String id = generateId();

        return new TVSeries(id, title, genre, language, seriesInfo, director, safeActors, mediaFormat, translationInfo, safeSeasons);

    }
}
