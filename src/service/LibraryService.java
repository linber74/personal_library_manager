package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private final Object repository;
    private final LibraryItemFactory factory;
    private final List<LibraryItem> items;

    public LibraryService(Object repository, LibraryItemFactory factory) {
        if (repository == null) {
            throw new IllegalArgumentException("repository cannot be null");
        }
        if (factory == null) {
            throw new IllegalArgumentException("factory cannot be null");
        }
        this.repository = repository;
        this.factory = factory;

        List<LibraryItem> loaded = repository.load();
        this.items = (loaded != null) ? new ArrayList<>(loaded) : new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        items.add(item);
        repository.save(items);
    }

    public List<LibraryItem> getAll() {
        return new ArrayList<>(items);
    }

    public LibraryItem getById (String itemId) {

        if (itemId == null || itemId.isBlank()) {
            return null;
        }
        for (LibraryItem item : items) {
            if (itemId.equals(item.getItemId())) {
                return item;
            }
        }
        return  null;
    }

    public boolean removeById (String itemId) {
        if (itemId == null || itemId.isBlank()) {
            return false;
        }
        boolean removed = items.removeIf(item -> itemId.equals(item.getItemId()));

        if (removed) {
            repository.save(items);
        }
        return removed;
    }

    public Game addGame (String title, String genre, String language, SeriesInfo seriesInfo, String creator) {

        Game game = factory.createGame( title, genre, language, seriesInfo, creator);
        addItem(game);
        return game;
    }

    public Book addBook(String title, String genre, String language, SeriesInfo seriesInfo, List<String> author,
                        BookFormat bookFormat, FanficType fanficType, String fandom) {

        Book book = factory.createBook(title, genre, language, seriesInfo, author, bookFormat, fanficType, fandom);
        addItem(book);
        return book;
    }

    public Film addFilm (String title, String genre, String language, SeriesInfo seriesInfo, String director,
                         List<String> actors, MediaFormat mediaFormat, FilmType filmType, TranslationInfo translationInfo) {

        Film film = factory.createFilm(title, genre, language, seriesInfo, director, actors, mediaFormat, filmType, translationInfo);
        addItem(film);
        return film;
    }

    public TVSeries addTVSeries (String title, String genre, String language, SeriesInfo seriesInfo, String director,
                                 List<String> actors, MediaFormat mediaFormat,
                                 TranslationInfo translationInfo, List<Season> seasons) {

        TVSeries tvSeries = factory.createTVSeries(title, genre, language, seriesInfo, director, actors, mediaFormat, translationInfo, seasons);
        addItem(tvSeries);
        return tvSeries;
    }
}
