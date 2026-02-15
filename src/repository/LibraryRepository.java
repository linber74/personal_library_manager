package repository;

import model.LibraryItem;

import java.util.List;

public interface LibraryRepository {


    List<LibraryItem> load();

    void save(List<LibraryItem> items);
}
