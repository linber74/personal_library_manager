package repository;

import model.media.LibraryItem;

import java.util.List;

public interface LibraryRepository {

    List<LibraryItem> loadAll();

    LibraryItem findById(int itemId);

    void save(LibraryItem item);

    boolean deleteById(int itemId);

    List<String> getAllGenres();
    List<String> getAllLanguages();
    List<String> getAllFandoms();

    void addGenre(String genre);
    void addLanguage(String language);
    void addFandom(String fandom);

    void removeGenre(String genre);
    void removeLanguage(String language);
    void removeFandom(String fandom);
}
