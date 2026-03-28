package service;

import model.detail.Season;
import model.detail.SeriesInfo;
import model.enums.*;
import model.media.Book;
import model.media.Film;
import model.media.Game;
import model.media.TVSeries;

import java.util.ArrayList;
import java.util.List;

public class LibraryItemFactory {

    private final int id = 0;

    public Game createGame(String title, List<String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, String creator) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (creator == null || creator.isBlank()) {
            throw new IllegalArgumentException("Creator cannot be null or blank");
        }


       return new Game(id, title, genre, language, publishYear, seriesInfo, creator);
    }

    public Book createBook(String title, List<String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, List <String> author,
                           BookFormat bookFormat, FanficType fanficType, List<String> fandom) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        List<String> safeAuthors;
        if (author == null) {
            safeAuthors = new ArrayList<>();
        }
        else{
            safeAuthors = new ArrayList<>(author);
        }
        if (bookFormat == null) {
            throw new IllegalArgumentException("BookFormat cannot be null");
        }
        if (bookFormat == BookFormat.FANFICTION) {
            if (fanficType == null) {
                throw new IllegalArgumentException("FanficType cannot be null");
            } else if (fanficType != FanficType.ORIGINAL) {
                if (fandom == null ||  fandom.isEmpty()) {
                    throw new IllegalArgumentException("Fandom cannot be null or blank");
                }
                
            }
        }


        return new Book(id, title, genre, language, publishYear, seriesInfo, safeAuthors, bookFormat, fanficType, fandom);
    }

    public Film createFilm (String title, List<String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, String director,
                            List<String> actors, MediaFormat mediaFormat, TranslationInfo translationInfo) {

        movieTv(title, director, mediaFormat);

        List<String> safeActors;
        if (actors == null) {
            safeActors = new ArrayList<>();
        }
        else {
            safeActors = new ArrayList<>(actors);
        }


        return new Film(id, title, genre, language, seriesInfo, publishYear, director, safeActors, mediaFormat,  translationInfo);
    }

    private void movieTv(String title, String director, MediaFormat mediaFormat) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }

        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Director cannot be null or blank");
        }

        if (mediaFormat == null) {
            throw new IllegalArgumentException("MediaFormat cannot be null");
        }
    }

    public TVSeries createTVSeries (String title, List<String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, String director,
                                    List<String> actors, MediaFormat mediaFormat,
                                    TranslationInfo translationInfo, List<Season> seasons) {

        movieTv(title, director, mediaFormat);

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


        return new TVSeries(id, title, genre, language, publishYear, seriesInfo, director, safeActors, mediaFormat, translationInfo, safeSeasons);

    }
}
