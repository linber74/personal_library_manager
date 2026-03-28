package service;

import model.detail.Season;
import model.detail.SeriesInfo;
import model.enums.*;
import model.media.*;
import repository.LibraryRepository;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return items.stream()
                .filter(item -> item.getItemId() == itemId )
                .findFirst()
                .orElse(null);
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

    //getbyType
    public List <LibraryItem> getByType(ItemType type) {
        return items.stream()
                .filter(item -> item.getItemType() == type)
                .collect(Collectors.toList());
    }

    //getByGenre
    public List <LibraryItem> getByGenre (String genre) {
        return items.stream()
                .filter(item -> item.getGenre().stream()
                        .anyMatch(g -> g.toLowerCase().contains(genre.toLowerCase())))
                .collect(Collectors.toList());
    }

    //getByLanguage
    public List <LibraryItem> getByLanguage(String language) {
        return items.stream()
                .filter(item -> item.getLanguage().equals(language))
                .collect(Collectors.toList());
    }

    //getByTitle
    public List <LibraryItem> getByTitle(String title) {
        return items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    //getByActor
    public List <VisualMedia> getByActor (String actor) {
        return items.stream()
                .filter(item -> item instanceof VisualMedia)
                .map(item -> (VisualMedia) item)
                .filter(vm -> vm.getActors() != null && vm.getActors().stream()
                        .anyMatch(a -> a.toLowerCase().contains(actor.toLowerCase())))
                .collect(Collectors.toList());
    }

    //getByDirector
    public List <VisualMedia> getBDirector (String director) {
        return items.stream()
                .filter(item -> item instanceof VisualMedia)
                .map(item -> (VisualMedia) item)
                .filter(vm -> vm.getDirector() != null && vm.getDirector()
                        .toLowerCase().contains(director.toLowerCase()))
                .collect(Collectors.toList());
    }

    //getByAuthor
    public List <Book> getByAuthor (String author) {
        return items.stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(book -> book.getAuthor() != null && book.getAuthor().stream()
                        .anyMatch(a -> a.toLowerCase().contains(author.toLowerCase())))
                        .collect(Collectors.toList());
    }

    //getBySeries
    public List <LibraryItem> getBySeries (String series) {
        return items.stream()
                .filter(item -> item.getSeriesInfo() != null && item.getSeriesInfo()
                        .getSeriesName().toLowerCase().contains(series.toLowerCase()))
                .collect(Collectors.toList());
    }

    //getByFandom
    public List <Book> getByFandom (String fandom) {
        return items.stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(book -> book.getFandom() != null && book.getFandom().stream()
                        .anyMatch(a -> a.toLowerCase().contains(fandom.toLowerCase())))
                .collect(Collectors.toList());
    }

    //getByPublishYear
    public List <LibraryItem> getByYear (Integer year) {
        return items.stream()
                .filter(item -> item.getPublishYear() != null
                        && item.getPublishYear().equals(year))
                        .collect(Collectors.toList());
    }

    //getByCreator
    public  List <Game> getByCreator (String creator) {
        return items.stream()
                .filter(item -> item instanceof Game)
                .map(item -> (Game) item)
                .filter(game -> game.getCreator() != null && game.getCreator()
                        .toLowerCase().contains(creator.toLowerCase()))
                .collect(Collectors.toList());
    }
    //getByBookFormat(BookFormat format) — alla ebooks, ljudböcker osv.
    public List <Book> getByBookFormat (BookFormat bookFormat) {
        return items.stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(format -> format.getBookFormat() == bookFormat)
                        .collect(Collectors.toList());
    }

    //getByMediaFormat(MediaFormat format)
    public List <VisualMedia> getByMediaFormat (MediaFormat mediaFormat) {
        return items.stream()
                .filter(item -> item instanceof VisualMedia)
                .map(item -> (VisualMedia) item)
                .filter(format -> format.getMediaFormat() == mediaFormat)
                .collect(Collectors.toList());
    }

    //getByFanficType (FanficType type)
    public List <Book> getByFanfictype (FanficType type) {
        return items.stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(book -> book.getFanficType() != null && book.getFanficType() == type)
                .collect(Collectors.toList());
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
