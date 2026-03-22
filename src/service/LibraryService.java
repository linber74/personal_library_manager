package service;

import model.detail.Season;
import model.detail.SeriesInfo;
import model.enums.*;
import model.media.*;
import repository.LibraryRepository;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private final LibraryRepository repository;
    private final LibraryItemFactory factory;
    private final List<LibraryItem> items;

    public LibraryService(LibraryRepository repository, LibraryItemFactory factory) {
        if (repository == null) {
            throw new IllegalArgumentException("repository cannot be null");
        }
        if (factory == null) {
            throw new IllegalArgumentException("factory cannot be null");
        }
        this.repository = repository;
        this.factory = factory;

        List<LibraryItem> loaded = repository.loadAll();
        this.items = (loaded != null) ? new ArrayList<>(loaded) : new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        items.add(item);
        repository.save(item);
    }

    public List<LibraryItem> getAll() {
        return new ArrayList<>(items);
    }

    public LibraryItem getById (int itemId) {

        if (itemId <= 0) {
            return null;
        }
        for (LibraryItem item : items) {
            if (itemId == item.getItemId()){
                return item;
            }
        }
        return  null;
    }

    public boolean removeById (int itemId) {
        if (itemId <= 0) {
            return false;
        }
        boolean removed = items.removeIf(item -> itemId == item.getItemId());

        if (removed) {
            repository.deleteById(itemId);
        }
        return removed;
    }

    public Game addGame (String title, List <String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, String creator) {

        Game game = factory.createGame( title, genre, language, publishYear, seriesInfo, creator);
        addItem(game);
        return game;
    }

    public Book addBook(String title, List <String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, List<String> author,
                        BookFormat bookFormat, FanficType fanficType, List<String> fandom) {

        Book book = factory.createBook(title, genre, language, publishYear, seriesInfo, author, bookFormat, fanficType, fandom);
        addItem(book);
        return book;
    }

    public Film addFilm (String title, List <String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, String director,
                         List<String> actors, MediaFormat mediaFormat, FilmType filmType, TranslationInfo translationInfo) {

        Film film = factory.createFilm(title, genre, language, publishYear, seriesInfo, director, actors, mediaFormat, filmType, translationInfo);
        addItem(film);
        return film;
    }

    public TVSeries addTVSeries (String title, List <String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, String director,
                                 List<String> actors, MediaFormat mediaFormat,
                                 TranslationInfo translationInfo, List<Season> seasons) {

        TVSeries tvSeries = factory.createTVSeries(title, genre, language, publishYear, seriesInfo, director, actors, mediaFormat, translationInfo, seasons);
        addItem(tvSeries);
        return tvSeries;
    }
    
    public List<String> getAvailableGenre() {
        return repository.getAllGenres();
    }

    public List<String> getAvailableLanguage() {
        return repository.getAllLanguages();
    }

    public List<String> getAvailableFandoms() {
        return repository.getAllFandoms();
    }

    public void addNewGenre(String genre) {
        repository.addGenre(genre);
    }

    public void addNewLanguage(String language) {
        repository.addLanguage(language);
    }

    public void addNewFandom(String fandom) {
        repository.addFandom(fandom);
    }

    public void removeGenre(String genre) {
        repository.removeGenre(genre);
    }

    public void removeLanguage(String language) {
        repository.removeLanguage(language);
    }

    public void removeFandom(String fandom) {
        repository.removeFandom(fandom);
    }
}
